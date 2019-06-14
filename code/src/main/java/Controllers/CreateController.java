package Controllers;
import Model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateController {

    SQLModel sqlModel = SQLModel.getInstance();

    public void CreateEvent(String logedIn, String headline, ArrayList<Category> categories,
                            HashMap<String, String> inCharge){
        Event newEvent = new Event(logedIn, headline, categories,inCharge);

        sqlModel.insertRecordToTable(newEvent);
    }

    public void createUpdate(String username, Event event, String description){
        //create Update
        Update update =  new Update(username, description, event.getId());
        //add to event
        event.addNewUpdate(update);
        //write to db
        sqlModel.insertRecordToTable(update);
    }

    public void insertCategoryToTable(String insertValue){
        ISQLable newCategory = new Category(insertValue);
        sqlModel.insertRecordToTable(newCategory);
    }

}
