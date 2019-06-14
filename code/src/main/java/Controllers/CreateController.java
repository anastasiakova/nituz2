package Controllers;
import Model.Event;
import Model.SQLModel;
import Model.Update;

public class CreateController {

    SQLModel sqlModel;

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

    }

}
