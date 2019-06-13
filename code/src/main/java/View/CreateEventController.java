package View;

import Controllers.CreateController;
import Model.Category;
import Model.EOCUser;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckComboBox;


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
    public Category[] categories;
    public CreateController createController;


    public CreateEventController() {
    }

    public void createEventAction() throws ParseException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(headline.getText().equals("") || headline == null){
            alert.setContentText("Please enter headline for this event");
            alert.show();
        }else if(initialUpdate.getText().equals("") || initialUpdate == null){
            alert.setContentText("Please enter your first update");
            alert.show();
        }else if((policePart.getText().equals("") && EMSPart.getText().equals("") && FDPart.getText().equals("")) || (policePart == null && EMSPart == null && FDPart == null)){
            alert.setContentText("Please enter at least one candidate");
            alert.show();
        }else {
            String headLine = headline.getText();
            String initUpdate = initialUpdate.getText();
            String policeName = policePart.getText();
            String EMSName = EMSPart.getText();
            String FDName = FDPart.getText();
            Calendar calendar = Calendar.getInstance();
            Date publishTime = calendar.getTime();
//            this.createController.CreateUser(userText.getText(), passText.getText(), date, fNameText.getText()
//                    , lNameText.getText(), cityText.getText(), idNumber.getText());

        }

    }

}
