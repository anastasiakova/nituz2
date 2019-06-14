package Controllers;
import Model.*;

public class CreateController {

    SQLModel sqlModel = SQLModel.getInstance();

//    public void CreateEvent(String headline, String initUpdate, String Police, String EMS, String FD, Date publishTime){
//        Event newEvent = new Event(headline, initUpdate, Police, EMS, FD, publishTime);
//        sqlModel.insertRecordToTable(Tables.TBL_USERS.toString().toLowerCase(), newUser);
//    }

    public void createUpdate(String username, Event event, String description){
        //create Update
        Update update =  new Update(username, description, event.getId());
        //add to event
        event.addNewUpdate(update);
        //write to db
        sqlModel.insertRecordToTable(update);
    }

    public void insertToTable(String insertValue){
        ISQLable newCategory = new Category(insertValue);
        sqlModel.insertRecordToTable(newCategory);
    }

}
