package Controllers;

import Model.Event;
import Model.SQLModel;
import Model.Tables;
import Model.User;

import java.util.Date;

public class CreateController {

    SQLModel sqlModel;

    public void CreateEvent(String headline, String initUpdate, String Police, String EMS, String FD, Date publishTime){
        Event newEvent = new Event(headline, initUpdate, Police, EMS, FD, publishTime);
        sqlModel.insertRecordToTable(Tables.TBL_USERS.toString().toLowerCase(), newUser);
    }
}
