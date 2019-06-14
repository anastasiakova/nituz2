package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Category implements ISQLable{
    private String name;

    private String tableFields = "categories("
            + TblFields.enumDict.get("categories").get(0) + ") VALUES(?)";

    private String primaryKeyName = "name";
    private String tableName = "categories";

    public Category(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPrimaryKey() {
        return getName();
    }

    @Override
    public String getPrimaryKeyName() {
        return name;
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, this.getName());
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
        return TblFields.enumDict.get("user").get(0) + "='" + this.getName() + "'\n";
    }

    @Override
    public String getTableName() {
        return tableName;
    }
}
