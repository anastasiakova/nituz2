package View;

import Model.Category;
import Model.EOCUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class CreateEventController {

    public TextField headline;
    public TextField policePart;
    public TextField EMSPart;
    public TextField FDPart;
    public TextArea initialUpdate;
    public Button createEvent;
    public EOCUser publisher; //understand from where to get it
    public Category [] categories;




    public CreateEventController() {
    }

    public void createEventAction() throws ParseException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String headLine = headline.getText();
        String initUpdate = initialUpdate.getText();
        String policeName = policePart.getText();
        String EMSName = EMSPart.getText();
        String FDName = FDPart.getText();
        Calendar calendar = Calendar.getInstance();
        Date publishTime = calendar.getTime();


    }

}