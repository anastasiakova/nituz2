package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrganizationUser extends User {
    private Organizations orgName;

    public OrganizationUser(String userString){
        this(userString.split(","));
    }



    //username, psw, name, Integer.parseInt(rank), AccountStatus, email, Integer.parseInt(warnings)
    public OrganizationUser(String[] fields) {
        super(fields[0], fields[1], fields[2], Integer.parseInt(fields[4]), fields[5].trim(), fields[6], Integer.parseInt(fields[7]));
        switch (fields[3]) {
            case "Police":
                this.orgName = Organizations.Police;
                break;
            case "EMS":
                this.orgName = Organizations.EMS;
                break;
            case "FD":
                this.orgName = Organizations.FD;
                break;
            default:
                System.out.println("shit");

        }

    }

    @Override
    public String getFieldsSQLWithValues() {
        return TblFields.enumDict.get("user").get(0) + "='" + this.getUsername() +
                "'," + TblFields.enumDict.get("user").get(1) + "='" + this.getPassword() +
                "'," + TblFields.enumDict.get("user").get(2) + "='" + this.getName() +
                "'," + TblFields.enumDict.get("user").get(3) + "='" + this.getOrganization() +
                "'," + TblFields.enumDict.get("user").get(4) + "='" + this.getRank() +
                "'," + TblFields.enumDict.get("user").get(5) + "='" + this.getAccountStatus() +
                "'," + TblFields.enumDict.get("user").get(6) + "='" + this.getEmail() +
                "'," + TblFields.enumDict.get("user").get(7) + "='" + this.getWarnings() +
                "'\n";
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, this.getUsername());
            pstmt.setString(2, this.getPassword());
            pstmt.setString(3, this.getName());
            pstmt.setString(4, this.getOrganization().toString());
            pstmt.setString(5, Integer.toString(this.getRank()));
            pstmt.setString(6, this.getAccountStatus().toString());
            pstmt.setString(7, this.getEmail());
            pstmt.setString(8, Integer.toString(this.getWarnings()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Organizations getOrganization() {
        return orgName;
    }
}
