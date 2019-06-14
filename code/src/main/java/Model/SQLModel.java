package Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class SQLModel {

    private static SQLModel sqlModel;
    private String _path;

    private SQLModel(){
        Path currentPath = Paths.get("");
        TblFields.init();
        _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
        System.out.println(_path);
        try (Connection conn = DriverManager.getConnection(_path)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //TO DO
        //should be for each type of table his init function!
        initUserTbl();
        initOrganizationTbl();
        initEventTbl();
        initEventAndParticipateTbl();
        initFeedbackTbl();
        initUpdateTbl();
        initUpdateVersionTbl();
        initComplaintTbl();
        initCategoriesTbl();
        initEventAndCategoryTbl();
        initAccountMassagesTbl();
    }

    public static SQLModel getInstance(){
        if(sqlModel == null){
            sqlModel = new SQLModel();
        }

        return sqlModel;
    }

    public void initUserTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS user (\n" +
                TblFields.enumDict.get("user").get(0) + " text NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("user").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("user").get(2) + " text NOT NULL,\n" +
                TblFields.enumDict.get("user").get(3) + " text NOT NULL,\n" +
                TblFields.enumDict.get("user").get(4) + " INTEGER,\n" +
                TblFields.enumDict.get("user").get(5) + " text NOT NULL,\n" +
                TblFields.enumDict.get("user").get(6) + " text NOT NULL,\n" +
                TblFields.enumDict.get("user").get(7) + " INTEGER NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initOrganizationTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS organization (\n" +
                TblFields.enumDict.get("organization").get(0) + " INTEGER NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("organization").get(1) + " text NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initEventTbl(){
//        "username", "psw", "name", "organization",
//                "rank", "accountStatus", "email", "warnings"
        String createStatement = "CREATE TABLE IF NOT EXISTS event (\n" +
                TblFields.enumDict.get("event").get(0) + " INTEGER NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("event").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("event").get(2) + " text NOT NULL,\n" +
                TblFields.enumDict.get("event").get(3) + " INTEGER NOT NULL,\n" +
                TblFields.enumDict.get("event").get(4) + " text,\n" +
                TblFields.enumDict.get("event").get(5) + " text,\n" +
                TblFields.enumDict.get("event").get(6) + " text,\n" +
                TblFields.enumDict.get("event").get(7) + " text NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initEventAndParticipateTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS eventAndParticipate (\n" +
                TblFields.enumDict.get("eventAndParticipate").get(0) + " INTEGER NOT NULL,\n" +
                TblFields.enumDict.get("eventAndParticipate").get(1) + " text NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initFeedbackTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS feedback (\n" +
                TblFields.enumDict.get("feedback").get(0) + " INTEGER NOT NULL,\n" +
                TblFields.enumDict.get("feedback").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("feedback").get(2) + " text NOT NULL,\n" +
                TblFields.enumDict.get("feedback").get(3) + " INTEGER NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initUpdateTbl(){
        //"updateId","updateEventId", "updateDate", "publisher", "description"
        String createStatement = "CREATE TABLE IF NOT EXISTS updates (\n" +
                TblFields.enumDict.get("updates").get(0) + " INTEGER NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("updates").get(1) + " INTEGER NOT NULL,\n" +
                TblFields.enumDict.get("updates").get(2) + " text NOT NULL,\n" +
                TblFields.enumDict.get("updates").get(3) + " text NOT NULL,\n" +
                TblFields.enumDict.get("updates").get(4) + " text NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initUpdateVersionTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS updateVersion (\n" +
                TblFields.enumDict.get("updateVersion").get(0) + " INTEGER NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("updateVersion").get(1) + " INTEGER NOT NULL,\n" +
                TblFields.enumDict.get("updateVersion").get(2) + " text NOT NULL\n" +
                ");";

        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initComplaintTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS complaint (\n" +
                TblFields.enumDict.get("complaint").get(0) + " INTEGER NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("complaint").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("complaint").get(2) + " text NOT NULL,\n" +
                TblFields.enumDict.get("complaint").get(3) + " text NOT NULL,\n" +
                TblFields.enumDict.get("complaint").get(4) + " INTEGER NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initCategoriesTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS categories (\n" +
                TblFields.enumDict.get("categories").get(0) + " text NOT NULL PRIMARY KEY\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initEventAndCategoryTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS eventAndCategory (\n" +
                TblFields.enumDict.get("eventAndCategory").get(0) + " text NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("eventAndCategory").get(1) + " INTEGER NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initAccountMassagesTbl(){
        String createStatement = "CREATE TABLE IF NOT EXISTS accountMassages (\n" +
                TblFields.enumDict.get("accountMassages").get(0) + " text NOT NULL,\n" +
                TblFields.enumDict.get("accountMassages").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("accountMassages").get(2) + " text NOT NULL,\n" +
                TblFields.enumDict.get("accountMassages").get(3) + " text NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String selectFromTable(Tables table, String[] fields){
        boolean shouldGetAll = false;
        return selectFromTable(table, fields, shouldGetAll);
    }


    public String selectFromTable(Tables table, String[] fields, boolean shouldGetAll){
        switch (table) {
            case user:
                return selectFromTbl("user", fields, "user", shouldGetAll);
            case categories:
                return selectFromTbl("categories", fields, "categories", shouldGetAll);
            case eventAndParticipate:
                return selectFromTbl("eventAndParticipate", fields, "eventAndParticipate", shouldGetAll);
            default:
                return "";
        }
    }

    private String selectFromTbl(String table, String[] fields, String tblFields, boolean shouldGetAll) {
        String sql = "SELECT * FROM ";
        sql += table.toLowerCase() + "\n";
        if(!shouldGetAll) {
            sql += "WHERE ";
            boolean notFirst = false;
            for (int i = 0; i < TblFields.enumDict.get(tblFields).size(); i++) {
                if (fields[i] != "" && fields[i] != null) {
                    if (notFirst) {
                        sql += " AND ";
                    }
                    notFirst = true;
                    sql += TblFields.enumDict.get(tblFields).get(i) + "='" + fields[i] + "'";
                }
            }
        }
        sql += ";";
        String res = "";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                for (int i = 1 ; i <= TblFields.enumDict.get(tblFields).size(); i++){
                    res += rs.getString(i) + ", ";
                }
                res += '\n';
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }


    public void insertRecordToTable(ISQLable isqLable){
        String sql = "INSERT INTO " + isqLable.getTableFields();

        try (Connection conn = DriverManager.getConnection(_path);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            isqLable.insertRecordToTable(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getMaxID(Tables table, String fieldName){
        String select = "SELECT " + fieldName + "\n" +
                "FROM " + table.name() + "\n" +
                "WHERE " + fieldName + " = (SELECT COALESCE(MAX(" + fieldName + "),0) FROM " + table.name() + ");"; // COALESCE(MAX(eventId),0) deals with the case where there are no entries in event table
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            String id = stmt.executeQuery(select).getString(fieldName);
            return Integer.parseInt(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void insertParticipantsToDb(String eventID, String username){
        String sql = "INSERT INTO eventAndParticipate("+
                TblFields.enumDict.get("eventAndParticipate").get(0) +
                TblFields.enumDict.get("eventAndParticipate").get(1) +
                ") VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(_path);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, eventID);
            pstmt.setString(2, username);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
