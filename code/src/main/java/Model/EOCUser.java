package Model;

public class EOCUser extends User {

    public EOCUser(String userString){
        this(userString.split(","));
    }

    //username, psw, name, Integer.parseInt(rank), AccountStatus.values()[Integer.parseInt(accountStatus)], email, Integer.parseInt(warnings)
    public EOCUser(String[] fields) {
        super(fields[0], fields[1], fields[2], Integer.parseInt(fields[4]), AccountStatus.values()[Integer.parseInt(fields[5])], fields[6], Integer.parseInt(fields[7]));
    }
}
