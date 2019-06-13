package Controllers;

import Model.SQLModel;
import Model.Tables;
import Model.TblFields;

public class SearchController {
    SQLModel sqlModel;

    public SearchController() {
        this.sqlModel = SQLModel.getInstance();
    }

    public String isLoginValid(String username,String pwd){

        String[] fields = new String[TblFields.enumDict.get("user").size()];
        fields[0] = username;
        fields[1] = pwd;
        String user = sqlModel.selectFromTable(Tables.TBL_USERS, fields);
        if(user.equals("")){
            return null;
        }


        return user;
    }

}
