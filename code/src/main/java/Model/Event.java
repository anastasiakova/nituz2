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
//    private ArrayList<Update> updates;
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
//        addInCharge((String) splittedEvent[5], Organizations.Mada);
//        addInCharge((String) splittedEvent[6], Organizations.Firefighters);
//        addCreator((String)splittedEvent[7]);
//        setParticipants(this.id);
//    }
//
//    private void addInCharge(String userName, Organizations organization) {
//        ResultSet user = getUserByOrganization(userName, organization);
//        try {
//            inCharge.put(organization, new OrganizationUser(user.getString("username"), user.getString("psw"),
//                    user.getString("organization"), user.getString("rank"),
//                    user.getString("accountStatus"), user.getString("email"), user.getString("warnings")));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void addCreator(String username){
//        ResultSet user = getUserByOrganization(username, Organizations.EOC);
//        if(user == null){
//            //TO DO Exception must have creator
//        }
//        try {
//            creator = new EOCUser(user.getString("username"), user.getString("psw"),
//                    user.getString("organization"), user.getString("rank"),
//                    user.getString("accountStatus"), user.getString("email"), user.getString("warnings"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private ResultSet getUserByOrganization(String username, Organizations organization) {
//        //TO DO the path shuld come from sql singleton
//        Path currentPath = Paths.get("");
//        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
//        String select = "SELECT *\n" +
//                "FROM user\n" +
//                "WHERE username = " + username + "AND organization = " + organization + ";";
//        try (Connection conn = DriverManager.getConnection(_path);
//             Statement stmt = conn.createStatement()) {
//             ResultSet user = stmt.executeQuery(select);
//             if(user.next() == true){
//                return user;
//             }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
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
//                // if admin or bigger rank should have write permission
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
//        //TO DO the path shuld come from sql singleton
//        Path currentPath = Paths.get("");
//        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
//        String select = "SELECT *\n" +
//                "FROM user\n" +
//                "WHERE username = " + username + ";";
//        try (Connection conn = DriverManager.getConnection(_path);
//             Statement stmt = conn.createStatement()) {
//             ResultSet user = stmt.executeQuery(select);
//             if(user.next()) {
//                 return new OrganizationUser(user.getString("username"), user.getString("psw"),
//                         user.getString("organization"), user.getString("rank"),
//                         user.getString("accountStatus"), user.getString("email"), user.getString("warnings"));
//             }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
//
//    private void setUpdates(){
//        //TO DO the path should come from sql singleton
//        Path currentPath = Paths.get("");
//        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
//        String select = "SELECT *\n" +
//                "FROM updates\n" +
//                "WHERE updateEventId = " + this.id + ";";
//        try (Connection conn = DriverManager.getConnection(_path);
//             Statement stmt = conn.createStatement()) {
//            ResultSet update = stmt.executeQuery(select);
//            if(update.next()) {
//                // אני חושבת שאנחנו צריכים לשנות את הclass diagram כך שכל ארוע יחזיק עדכון 1 את העדכון האחרון שלו ואז לעדכון יש את הקודם ואפשר לרוץ אחורה, צריך לחשוב על זה
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}