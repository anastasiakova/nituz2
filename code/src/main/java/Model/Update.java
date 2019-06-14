package Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Update implements ISQLable{
    private int id;
    private String description;
    private Date publishDate;
    private String publisherUsername;
    private int event;

    private static String primaryKeyName = "updateId";
    private static int currentMaxId = SQLModel.getInstance().getMaxID(Tables.event, primaryKeyName);

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
        this.event = Integer.parseInt(splittedRow[1]);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.publishDate = formatter.parse((String) splittedRow[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.publisherUsername = splittedRow[3];
        this.description = splittedRow[4];
    }

    @Override
    public String getPrimaryKey() {
        return String.valueOf(this.id);
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public String getPuplishDateForQuery() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(this.publishDate);
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {
        ////"updateId","updateEventId", "updateDate", "publisher", "description"
        try {
            pstmt.setString(1, getPrimaryKey());
            pstmt.setString(2, String.valueOf(this.event));
            pstmt.setString(3, getPuplishDateForQuery());
            pstmt.setString(4, this.publisherUsername);
            pstmt.setString(5, this.description);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getTableFields() {
        return "updates(" +
                TblFields.enumDict.get("updates").get(0) +
                TblFields.enumDict.get("updates").get(1) +
                TblFields.enumDict.get("updates").get(2) +
                TblFields.enumDict.get("updates").get(3) +
                TblFields.enumDict.get("updates").get(4) +
                ") VALUES(?,?,?,?,?)";
    }

    @Override
    public String getFieldsSQLWithValues() {
        ////"updateId","updateEventId", "updateDate", "publisher", "description"
        return TblFields.enumDict.get("updates").get(0) + "='" + getPrimaryKey() +
                "'," + TblFields.enumDict.get("updates").get(1) + "='" + this.event +
                "'," + TblFields.enumDict.get("updates").get(2) + "='" + getPuplishDateForQuery() +
                "'," + TblFields.enumDict.get("updates").get(3) + "='" + this.publisherUsername +
                "'," + TblFields.enumDict.get("updates").get(4) + "='" + this.description +
                "'\n";
    }

    @Override
    public String getTableName() {
       return "updates";
    }
}