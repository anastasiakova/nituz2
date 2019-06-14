package View;

import Controllers.CreateController;
import Controllers.LogedInController;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class CreateEventController {

    public TextField headline;
    public TextField policePart;
    public TextField EMSPart;
    public TextField FDPart;
    public TextArea initialUpdate;
    public Button createEvent;
    public String publisher; //understand from where to get it
    public ArrayList<Category> categories;
    public CreateController createController;
    public LogedInController logedInController;


    public CreateEventController() {
        policePart.setText("");
        EMSPart.setText("");
        FDPart.setText("");
    }

    public void SetControllers(LogedInController logedInController, CreateController createController){
        this.logedInController = logedInController;
        this.createController = createController;
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
            this.publisher = logedInController.getUserNameFromUserAsStripAndCleanString();
            String headLine = headline.getText();
            String initUpdate = initialUpdate.getText();
            String policeName = policePart.getText();
            String EMSName = EMSPart.getText();
            String FDName = FDPart.getText();
            HashMap<String, String> inCharge = new HashMap<String, String>();
            inCharge.put("Police", policeName);
            inCharge.put("EMS", EMSName);
            inCharge.put("FD", FDName);
            this.createController.CreateEvent(publisher, headLine, categories, inCharge);

        }

    }

}
