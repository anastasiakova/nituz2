package Controllers;

import Model.*;

public class LogedInController {
    SQLModel sqlModel;
    User loged;

    public LogedInController() {
        this.sqlModel = SQLModel.getInstance();
    }

    public boolean tryLogIn(String username, String pwd) {
        SearchController search = new SearchController();
        String ans = search.isLoginValid(username, pwd);

        if (ans != null) {
            String[] valid = ans.split("\n");
            String[] userFields = valid[0].split(", ");
            String currOrg = userFields[3];
            String eoc = Organizations.EOC.toString();
            if (currOrg.equals(eoc)) {
                loged = new EOCUser(userFields);
            } else {
                loged = new OrganizationUser(userFields);
            }
            return true;
        }
        else{
            return false;
        }

    }

}
