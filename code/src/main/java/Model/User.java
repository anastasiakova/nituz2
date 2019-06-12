package Model;

public class User {
    private String username;
    private String name;
    private int rank;
    private AccountStatus accountStatus;
    private String lastaName;
    private String psw;
    private int warnings;
    private String email;

    enum AccountStatus {
        enabled,
        disabled,
        locked
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

    public String getLastaName() {
        return lastaName;
    }

    public String getPsw() {
        return psw;
    }

    public int getWarnings() {
        return warnings;
    }

    public String getEmail() {
        return email;
    }
}
