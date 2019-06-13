package Model;

public class User {
    private String username;


    private String name;
    private int rank;
    private AccountStatus accountStatus;
    private String psw;
    private int warnings;
    private String email;

    public enum AccountStatus {
        enabled,
        disabled,
        locked
    }

    public User(String fields){

    }

    public User(String username, String psw, String name, int rank, AccountStatus accountStatus, String email, int warnings) {
        this.username = username;
        this.name = name;
        this.rank = rank;
        this.accountStatus = accountStatus;
        this.psw = psw;
        this.warnings = warnings;
        this.email = email;
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

    public String getPsw() {
        return psw;
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

}
