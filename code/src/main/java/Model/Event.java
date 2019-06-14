//package Model;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.sql.*;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedList;
//
//public class Event{
//    private int id;
//    private String title;
//    private Status status;
//    private Date date;
//    private ArrayList<Category> categories;
//    private HashMap<Organizations, OrganizationUser> inCharge;
//    private HashMap<String, Permission> participants;
//    private EOCUser creator;
//    private LinkedList<Update> updates = new LinkedList<>();
//
//    enum Status {
//        inTreatment,
//        treatmentOver
//    }
//    enum Permission{
//        read,
//        write
//    }
//    //    when user creates the event
//    public Event(EOCUser creator, String title, ArrayList<Category> categories, HashMap<Organizations, OrganizationUser> inCharge){
//        this.title = title;
//        this.status = Status.inTreatment;
//        this.date = new Date();
//        this.categories = categories;
//        this.creator = creator;
//        this.inCharge = inCharge;
//        setNextEventID();
//    }
//
//    //    when load from table
//    public Event(String event) {
//        this(event.split(", "));
//    }
//
//    public Event(Object[] splittedEvent) {
//        this.id = (int) splittedEvent[0];
//        this.title = (String) splittedEvent[1];
//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            this.date = formatter.parse((String) splittedEvent[2]);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        this.status = Status.values()[(int) splittedEvent[3]];
//        inCharge = new HashMap<>();
//        addInCharge((String) splittedEvent[4], Organizations.Police);
//        addInCharge((String) splittedEvent[5], Organizations.EMS);
//        addInCharge((String) splittedEvent[6], Organizations.FD);
//        addCreator((String)splittedEvent[7]);
//        setParticipants(this.id);
//    }
//
//    private void addInCharge(String userName, Organizations organization) {
//        OrganizationUser user = getUserByOrganization(userName, organization);
//
//        inCharge.put(organization, user);
//    }
//
//    private void addCreator(String username){
//        SQLModel sql = SQLModel.getInstance();
//
//        String[] fields = new String[TblFields.enumDict.get("user").size()];
//        fields[0] = username;
//
//        boolean shouldGetAllFields = true;
//        String userStrRep = sql.selectFromTable(Tables.user, fields, shouldGetAllFields);
//
//        creator = new EOCUser(userStrRep);
//    }
//
//    private OrganizationUser getUserByOrganization(String username, Organizations organization) {
//
//        SQLModel sql = SQLModel.getInstance();
//
//        String[] fields = new String[TblFields.enumDict.get("user").size()];
//        fields[0] = username;
//        fields[3] = organization.toString();
//
//        boolean shouldGetAllFields = true;
//        String userStrRep = sql.selectFromTable(Tables.user, fields, shouldGetAllFields);
//
//        return new OrganizationUser(userStrRep);
//    }
//
//    private void setNextEventID(){
//        //TO DO the path shuld come from sql singleton
//        Path currentPath = Paths.get("");
//        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
//        String select = "SELECT eventId\n" +
//                "FROM event\n" +
//                "WHERE eventId = (SELECT MAX(eventId) FROM event);";
//        try (Connection conn = DriverManager.getConnection(_path);
//             Statement stmt = conn.createStatement()) {
//            String id = stmt.executeQuery(select).getString("eventId");
//            this.id = Integer.parseInt(id) + 1;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void setParticipants(int eventId) {
//        //TO DO the path shuld come from sql singleton
//        Path currentPath = Paths.get("");
//        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
//        String select = "SELECT *\n" +
//                "FROM eventAndParticipate\n" +
//                "WHERE participateEventId = " + eventId + ";";
//        try (Connection conn = DriverManager.getConnection(_path);
//             Statement stmt = conn.createStatement()) {
//            ResultSet participantsRows = stmt.executeQuery(select);
//            while (participantsRows.next()) {
//                String username = participantsRows.getString("participateUsername");
//                OrganizationUser user = getUser(username);
//                // if admin or higher rank should have write permission
//                if(user != null) {
//                    if (user.getRank() == -1 || user.getRank() >= inCharge.get(user.getOrganization()).getRank()) {
//                        participants.put(username, Permission.write);
//                    } else {
//                        participants.put(username, Permission.read);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private OrganizationUser getUser(String username) {
//        SQLModel sql = SQLModel.getInstance();
//
//        String[] fields = new String[TblFields.enumDict.get("user").size()];
//        fields[0] = username;
//
//        boolean shouldGetAllFields = true;
//        String userStrRep = sql.selectFromTable(Tables.user, fields, shouldGetAllFields);
//
//        return new OrganizationUser(userStrRep);
//    }
//
//    private void AddNewUpdate(Update updateToAdd){
//        updates.addFirst(updateToAdd);
//    }
//
//    private void getAllUpdatesFromDb(){
//        //TODO implement getAllUpdatesFromDb()
//        SQLModel sql = SQLModel.getInstance();
//
//        String[] fields = new String[TblFields.enumDict.get("updates").size()];
//        fields[0] = username;
//
//        boolean shouldGetAllFields = true;
//        String userStrRep = sql.selectFromTable(Tables.user, fields, shouldGetAllFields);
//
//        return new OrganizationUser(userStrRep);
//    }
//}