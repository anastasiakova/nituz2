package Controllers;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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


    public ObservableList<String> getAllCategories(){
        String[] fields = new String[TblFields.enumDict.get("categories").size()];
        String answer = sqlModel.selectFromTable(Tables.categories, fields, true);
        return FXCollections.observableList(Arrays.asList(answer.split(", \n")));
    }

    public ObservableList<String> getMyEvents(String username){
        String[] fields = new String[TblFields.enumDict.get("eventAndParticipate").size()];
        fields[1] = username;
        String answer = sqlModel.selectFromTable(Tables.eventAndParticipate, fields);
        return FXCollections.observableList(Arrays.asList(answer.split("\n")));
    }

    public String getSpecificEvent(String title){
        String[] fields = new String[TblFields.enumDict.get("event").size()];
        fields[1] = title;
        String answer = sqlModel.selectFromTable(Tables.event, fields);
        return answer.split("\n")[0];
    }

}
