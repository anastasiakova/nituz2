package Controllers;

import Model.*;

public class LogedInController {
    SQLModel sqlModel;
    User loged;

    public LogedInController() {
        this.sqlModel = SQLModel.getInstance();
    }

    public boolean tryLogIn(String username,String pwd){
        SearchController search = new SearchController();
        String ans = search.isLoginValid(username,pwd);

        if (ans != null){
            String[] valid = ans.split("\n");
            String[] userFields = valid[0].split(",");
            if(Organizations.values()[Integer.parseInt(userFields[3])].toString() == "EOC"){
                loged = new EOCUser(userFields);
            }
            else{ loged = new OrganizationUser(userFields);}
        }
        return false;
    }

}
