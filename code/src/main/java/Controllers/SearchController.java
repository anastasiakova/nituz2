package Controllers;

import Model.SQLModel;
import Model.Tables;
import Model.TblFields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchController {
    SQLModel sqlModel;

    public SearchController() {
        this.sqlModel = SQLModel.getInstance();
    }

    public String isLoginValid(String username,String pwd){

        String[] fields = new String[TblFields.enumDict.get("user").size()];
        fields[0] = username;
        fields[1] = pwd;
        String user = sqlModel.selectFromTable(Tables.user, fields);
        if(user.equals("")){
            return null;
        }
        return user;
    }

    public List<String> getAllCategories(){
        String[] fields = new String[TblFields.enumDict.get("categories").size()];
        String answer = sqlModel.selectFromTable(Tables.categories, fields, true);
        return Arrays.asList(answer.split("\n"));
    }

}
