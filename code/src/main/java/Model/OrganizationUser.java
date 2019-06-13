package Model;

public class OrganizationUser extends User {
    private Organizations orgName;

    public OrganizationUser(String userString){
        this(userString.split(","));
    }

    //username, psw, name, Integer.parseInt(rank), AccountStatus.values()[Integer.parseInt(accountStatus)], email, Integer.parseInt(warnings)
    public OrganizationUser(String[] fields) {
        super(fields[0], fields[1], fields[2], Integer.parseInt(fields[4]), AccountStatus.values()[Integer.parseInt(fields[5])], fields[6], Integer.parseInt(fields[7]));
        this.orgName = Organizations.values()[Integer.parseInt(fields[3])];
    }

    public Organizations getOrganization() {
        return orgName;
    }
}
