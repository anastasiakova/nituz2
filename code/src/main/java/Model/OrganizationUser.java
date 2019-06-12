package Model;

public class OrganizationUser extends User {
    private Organizations orgName;

    public OrganizationUser(String username, String psw, String organization, String rank, String accountStatus, String email, String warnings) {
        super();
    }

    public Organizations getOrganization() {
        return orgName;
    }
}
