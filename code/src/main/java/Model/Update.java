package Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;

public class Update implements ISQLable{
    private int id;
    private String description;
    private Date publishDate;
    private String publisherUsername;
    private int event;
    private static int currentMaxId = getCurrentMaxUpdateID();

    private static int getCurrentMaxUpdateID() {
        //TODO the path should come from sql singleton
        Path currentPath = Paths.get("");
        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
        String select = "SELECT updateId\n" +
                "FROM updates\n" +
                "WHERE updateId = (SELECT COALESCE(MAX(updateId),0) FROM updates);"; // COALESCE(MAX(eventId),0) deals with the case where there are no entries in event table
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
             String id = stmt.executeQuery(select).getString("updateId");
             return Integer.parseInt(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Update(String username, String description, int eventId) {
            this.id = currentMaxId == 0 ? 0 : currentMaxId + 1;
            currentMaxId ++;
            this.description = description;
            this.publishDate = new Date();
            this.publisherUsername = username;
            this.event = eventId;
    }

    public Update(String updateRow) {
        ////"updateId","updateEventId", "updateDate", "publisher", "description"
        String[] splittedRow = updateRow.split(", ");
        this.id = Integer.parseInt(splittedRow[0]);

    }

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
}