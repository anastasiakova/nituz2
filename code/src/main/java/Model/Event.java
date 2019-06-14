package Model;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Event implements ISQLable {
    private int id;
    private String title;
    private Status status;
    private Date date;
    private ArrayList<Category> categories;
    private HashMap<Organizations, OrganizationUser> inCharge;
    private HashMap<String, Permission> participants;
    private EOCUser creator;
    private LinkedList<Update> updates = new LinkedList<>();
    private String initialUpdate;

    private static String primaryKeyName = "eventId";
    private static int currentMaxId = SQLModel.getInstance().getMaxID(Tables.event, primaryKeyName);
    private String tableFields = "event("
            + TblFields.enumDict.get("event").get(0) + ", " +
            TblFields.enumDict.get("event").get(1) + ", " +
            TblFields.enumDict.get("event").get(2) + ", " +
            TblFields.enumDict.get("event").get(3) + ", " +
            TblFields.enumDict.get("event").get(4) + ", " +
            TblFields.enumDict.get("event").get(5) + ", " +
            TblFields.enumDict.get("event").get(6) + ", " +
            TblFields.enumDict.get("event").get(7) +
            ") VALUES(?,?,?,?,?,?,?,?)";

    @Override
    public String getPrimaryKey() {
        return String.valueOf(id);
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, Integer.toString(this.getId()));
            pstmt.setString(2, this.getTitle());
            pstmt.setString(3, this.getDate());
            pstmt.setString(4, this.getStatus().toString());
            pstmt.setString(5, this.getInCharge().get(Organizations.Police) == null ? "" : this.getInCharge().get(Organizations.Police).getUsername());
            //pstmt.setString(5, this.getInCharge().get(Organizations.Police).getUsername());
            pstmt.setString(6, this.getInCharge().get(Organizations.EMS) == null ? "" : this.getInCharge().get(Organizations.EMS).getUsername());
            //pstmt.setString(6, this.getInCharge().get(Organizations.EMS).getUsername());
            pstmt.setString(7, this.getInCharge().get(Organizations.FD) == null ? "" : this.getInCharge().get(Organizations.FD).getUsername());
            //pstmt.setString(7, this.getInCharge().get(Organizations.FD).getUsername());
            pstmt.setString(8, this.getCreator().getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getTableFields() {
        return tableFields;
    }

    @Override
    public String getFieldsSQLWithValues() {
        return TblFields.enumDict.get("event").get(0) + "='" + (this.getId()) +
                "'," + TblFields.enumDict.get("event").get(1) + "='" + this.getTitle() +
                "'," + TblFields.enumDict.get("event").get(2) + "='" + this.getDate() +
                "'," + TblFields.enumDict.get("event").get(3) + "='" + this.getStatus().toString() +
                "'," + TblFields.enumDict.get("event").get(4) + "='" + this.getInCharge().get(Organizations.Police).getUsername() +
                "'," + TblFields.enumDict.get("event").get(5) + "='" + this.getInCharge().get(Organizations.EMS).getUsername() +
                "'," + TblFields.enumDict.get("event").get(6) + "='" + this.getInCharge().get(Organizations.FD).getUsername() +
                "'," + TblFields.enumDict.get("event").get(7) + "='" + this.getCreator().getUsername() +
                "'\n";
    }

    @Override
    public String getTableName() {
        return "event";
    }

    enum Status {
        inTreatment,
        treatmentOver
    }

    enum Permission {
        read,
        write
    }

    //    when user creates the event
    public Event(String creator, String headline, ArrayList<Category> categories,
                 HashMap<String, String> inCharge, String initialUpdate) {
        this.title = headline;
        this.status = Status.inTreatment;
        this.date = new Date();
        this.categories = categories;
        this.creator = getEOCUser(creator);
        this.initialUpdate = initialUpdate;

        String organization, username;
        this.inCharge = new HashMap<>();
        this.participants =new HashMap<>();
        for (HashMap.Entry<String, String> entry : inCharge.entrySet()) {
            organization = entry.getKey();
            username = entry.getValue();
            if (!username.equals("")) {
                Organizations o = Organizations.valueOf(organization);
                OrganizationUser ou = getOrganizationUser(username);
                this.inCharge.put(o, ou);
            }

        }
        populateParticipants();
        this.id = currentMaxId + 1;
        currentMaxId++;
    }

    private void populateParticipants() {
        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("user").size()];
        boolean shouldGetEntireTable = true;
        String[] userStrRep = sql.selectFromTable(Tables.user, fields, shouldGetEntireTable).split("\n");

        for (String usr : userStrRep) {
            boolean isUserOrganizationParticipating = inCharge.get(Organizations.valueOf(usr.split(", ")[3])) != null;

            if (isUserOrganizationParticipating) {
                OrganizationUser userInChargeInOrganization = inCharge.get(Organizations.valueOf(usr.split(", ")[3]));
                int userRank = Integer.parseInt(usr.split(", ")[4]);
                String username = usr.split(", ")[0];
                Permission permission = (userRank >= userInChargeInOrganization.getRank() || userRank == -1) ?
                        Permission.write : Permission.read;

                participants.put(username, permission);
            }
        }
    }

    public void insertParticipantsToDb() {
        //Get current participants from db
        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("eventAndParticipate").size()];
        fields[0] = String.valueOf(this.id);

        String[] participantsStrRep = sql.selectFromTable(Tables.eventAndParticipate, fields).split("\n");


        //Don't write existing participants again.
        HashMap<String, Permission> addedParticipants = new HashMap<>(participants);

        for (String participant : participantsStrRep) {
            String username = participant.split(", ")[1];
            if (addedParticipants.containsKey(username)) {
                //remove participants that already appear on disk
                addedParticipants.remove(username);
            }
        }

        //write only new participants to disk
        for (HashMap.Entry<String, Permission> entry : addedParticipants.entrySet()) {
            sql.insertParticipantsToDb(Integer.toString(getId()), entry.getKey());
        }
    }

    private EOCUser getEOCUser(String username) {
        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("user").size()];
        fields[0] = username;

        String userStrRep = sql.selectFromTable(Tables.user, fields);

        return new EOCUser(userStrRep);
    }

    //    when load from table
    public Event(String event) {
        this(event.split(", "));
    }

    public Event(Object[] splittedEvent) {
        this.id = (int) splittedEvent[0];
        this.title = (String) splittedEvent[1];
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = formatter.parse((String) splittedEvent[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.status = Status.values()[(int) splittedEvent[3]];
        inCharge = new HashMap<>();
        addInCharge((String) splittedEvent[4], Organizations.Police);
        addInCharge((String) splittedEvent[5], Organizations.EMS);
        addInCharge((String) splittedEvent[6], Organizations.FD);
        addCreator((String) splittedEvent[7]);
        loadParticipantsFromDb();
        loadAllUpdatesFromDb();
    }

    private void addInCharge(String userName, Organizations organization) {
        OrganizationUser user = getUserByOrganization(userName, organization);

        inCharge.put(organization, user);
    }

    private void addCreator(String username) {
        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("user").size()];
        fields[0] = username;

        String userStrRep = sql.selectFromTable(Tables.user, fields);

        creator = new EOCUser(userStrRep);
    }

    private OrganizationUser getUserByOrganization(String username, Organizations organization) {

        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("user").size()];
        fields[0] = username;
        fields[3] = organization.toString();

        String userStrRep = sql.selectFromTable(Tables.user, fields);

        return new OrganizationUser(userStrRep);
    }

    private void loadParticipantsFromDb() {

        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("eventAndParticipate").size()];
        fields[0] = String.valueOf(this.id);

        String[] participantsStrRep = sql.selectFromTable(Tables.eventAndParticipate, fields).split("\n");

        for (int i = 0; i < participantsStrRep.length; i++) {
            String username = participantsStrRep[i].split(", ")[1];
            OrganizationUser user = getOrganizationUser(username);
            // if admin or higher rank should have write permission
            if (user != null) {
                if (user.getRank() == -1 || user.getRank() >= inCharge.get(user.getOrganization()).getRank()) {
                    participants.put(username, Permission.write);
                } else {
                    participants.put(username, Permission.read);
                }
            }
        }
    }

    private OrganizationUser getOrganizationUser(String username) {
        if (username.equals("") || username == null) {
            return null;
        }

        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("user").size()];
        fields[0] = username;

        String userStrRep = sql.selectFromTable(Tables.user, fields);

        return new OrganizationUser(userStrRep);
    }

    public void addNewUpdate(Update updateToAdd) {
        updates.addFirst(updateToAdd);
    }

    private void loadAllUpdatesFromDb() {
        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("updates").size()];
        fields[2] = String.valueOf(this.id);

        String[] updatesStr = sql.selectFromTable(Tables.updates, fields).split("\n");

        for (int i = 0; i < updatesStr.length; i++) {
            addNewUpdate(new Update(updatesStr[i]));
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public String getDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        return formatter.format(this.date);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public HashMap<Organizations, OrganizationUser> getInCharge() {
        return inCharge;
    }

    public HashMap<String, Permission> getParticipants() {
        return participants;
    }

    public EOCUser getCreator() {
        return creator;
    }

    public LinkedList<Update> getUpdates() {
        return updates;
    }
}