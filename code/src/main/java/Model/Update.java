package Model;

import java.sql.PreparedStatement;
import java.util.Date;

public class Update implements ISQLable{
    private int id;
    private String description;
    private Date publishDate;
    private String publisherUsername;
    private int event;


    public Update(String username, String description, int eventId) {

    }

    public Update(String updateRow) {

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