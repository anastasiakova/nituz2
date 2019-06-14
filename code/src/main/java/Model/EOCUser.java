package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EOCUser extends User {

    public EOCUser(String userString){
        this(userString.split(", "));
    }

    @Override
    public String getFieldsSQLWithValues() {
        return TblFields.enumDict.get("user").get(0) + "='" + this.getUsername() +
                "'," + TblFields.enumDict.get("user").get(1) + "='" + this.getPassword() +
                "'," + TblFields.enumDict.get("user").get(2) + "='" + this.getName() +
                "'," + TblFields.enumDict.get("user").get(3) + "='" + Organizations.EOC +
                "'," + TblFields.enumDict.get("user").get(4) + "='" + this.getRank() +
                "'," + TblFields.enumDict.get("user").get(5) + "='" + this.getAccountStatus() +
                "'," + TblFields.enumDict.get("user").get(6) + "='" + this.getEmail() +
                "'," + TblFields.enumDict.get("user").get(6) + "='" + this.getWarnings() +
                "'\n";
    }
    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, this.getUsername());
            pstmt.setString(2, this.getPassword());
            pstmt.setString(3, this.getName());
            pstmt.setString(4, Organizations.EOC.toString());
            pstmt.setString(5, Integer.toString(this.getRank()));
            pstmt.setString(6, this.getAccountStatus().toString());
            pstmt.setString(7, this.getEmail());
            pstmt.setString(8, Integer.toString(this.getWarnings()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //username, psw, name, Integer.parseInt(rank), AccountStatus.values()[Integer.parseInt(accountStatus)], email, Integer.parseInt(warnings)
    public EOCUser(String[] fields) {
        super(fields[0], fields[1], fields[2], Integer.parseInt(fields[4]), fields[5].trim(), fields[6], Integer.parseInt(fields[7]));

    }
}
