package Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Event implements ISQLable{
    private int id;
    private String title;
    private Status status;
    private Date date;
    private ArrayList<Category> categories;
    private HashMap<Organizations, OrganizationUser> inCharge;
    private HashMap<String, Permission> participants;
    private EOCUser creator;
    private LinkedList<Update> updates = new LinkedList<>();


    private static int currentMaxId = getCurrentMaxEventID();

    @Override
    public String getPrimaryKey() {
        return null;
    }

    @Override
    public String getPrimaryKeyName() {
        return null;
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {

    }

    @Override
    public String getTableFields() {
        return null;
    }

    @Override
    public String getFieldsSQLWithValues() {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    enum Status {
        inTreatment,
        treatmentOver
    }
    enum Permission{
        read,
        write
    }
    //    when user creates the event
    public Event(EOCUser creator, String title, ArrayList<Category> categories, HashMap<Organizations, OrganizationUser> inCharge){
        this.title = title;
        this.status = Status.inTreatment;
        this.date = new Date();
        this.categories = categories;
        this.creator = creator;
        this.inCharge = inCharge;
        this.id = currentMaxId + 1;

        currentMaxId++;
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
        addCreator((String)splittedEvent[7]);
        setParticipants(this.id);
        loadAllUpdatesFromDb();
    }

    private void addInCharge(String userName, Organizations organization) {
        OrganizationUser user = getUserByOrganization(userName, organization);

        inCharge.put(organization, user);
    }

    private void addCreator(String username){
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

    private static int getCurrentMaxEventID(){
        //TODO the path should come from sql singleton
        Path currentPath = Paths.get("");
        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
        String select = "SELECT eventId\n" +
                "FROM event\n" +
                "WHERE eventId = (SELECT COALESCE(MAX(eventId),0) FROM event);"; // COALESCE(MAX(eventId),0) deals with the case where there are no entries in event table
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            String id = stmt.executeQuery(select).getString("eventId");
            return Integer.parseInt(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private void setParticipants(int eventId) {

        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("eventAndParticipate").size()];
        fields[0] = String.valueOf(this.id);

        String[] participantsStrRep = sql.selectFromTable(Tables.user, fields).split("\n");

        for (int i = 0; i < participantsStrRep.length; i++) {

        }
        //TODO the path should come from sql singleton
        Path currentPath = Paths.get("");
        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
        String select = "SELECT *\n" +
                "FROM eventAndParticipate\n" +
                "WHERE participateEventId = " + eventId + ";";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            ResultSet participantsRows = stmt.executeQuery(select);
            while (participantsRows.next()) {
                String username = participantsRows.getString("participateUsername");
                OrganizationUser user = getUser(username);
                // if admin or higher rank should have write permission
                if(user != null) {
                    if (user.getRank() == -1 || user.getRank() >= inCharge.get(user.getOrganization()).getRank()) {
                        participants.put(username, Permission.write);
                    } else {
                        participants.put(username, Permission.read);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private OrganizationUser getUser(String username) {
        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("user").size()];
        fields[0] = username;

        String userStrRep = sql.selectFromTable(Tables.user, fields);

        return new OrganizationUser(userStrRep);
    }

    private void AddNewUpdate(Update updateToAdd){
        updates.addFirst(updateToAdd);
    }

    private void loadAllUpdatesFromDb(){
        SQLModel sql = SQLModel.getInstance();

        String[] fields = new String[TblFields.enumDict.get("updates").size()];
        fields[2] = String.valueOf(this.id);

        String[] updatesStr = sql.selectFromTable(Tables.updates, fields).split("\n");

        for (int i = 0; i < updatesStr.length; i++) {
            AddNewUpdate(new Update(updatesStr[i]));
        }

    }
}