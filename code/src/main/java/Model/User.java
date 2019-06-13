package Model;

import java.sql.PreparedStatement;

public abstract class User implements ISQLable {
    private String username;
    private String name;
    private int rank;
    private AccountStatus accountStatus;
    private String password;
    private int warnings;
    private String email;

    private String tableFields = "tbl_users("
            + TblFields.enumDict.get("user").get(0) +
            TblFields.enumDict.get("user").get(1) +
            TblFields.enumDict.get("user").get(2) +
            TblFields.enumDict.get("user").get(3) +
            TblFields.enumDict.get("user").get(4) +
            TblFields.enumDict.get("user").get(5) +
            TblFields.enumDict.get("user").get(6) +
            TblFields.enumDict.get("user").get(7) +
            ") VALUES(?,?,?,?,?,?,?,?)";

    private String primaryKeyName = "username";
    private String tableName = "tbl_users";


    public enum AccountStatus {
        enabled,
        disabled,
        locked
    }

    public User(String fields) {

    }

    public User(String username, String password, String name, int rank, String accountStatus, String email, int warnings) {
        this.username = username;
        this.name = name;
        this.rank = rank;
        this.password = password;
        this.warnings = warnings;
        this.email = email;
        switch (accountStatus) {
            case "enabled":
                this.accountStatus = AccountStatus.enabled;
                break;
            case "disabled":
                this.accountStatus = AccountStatus.disabled;
            case "locked":
                this.accountStatus = AccountStatus.locked;
        }

    }


    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public String getPassword() {
        return password;
    }

    public int getWarnings() {
        return warnings;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getPrimaryKey() {
        return getUsername();
    }

    @Override
    public String getPrimaryKeyName() {
        return username;
    }

    @Override
    public abstract void insertRecordToTable(PreparedStatement pstmt);

    @Override
    public String getTableFields() {
        return tableFields;
    }

    @Override
    public abstract String getFieldsSQLWithValues();


    @Override
    public String getTableName() {
        return tableName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
