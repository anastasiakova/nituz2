package View;

import Controllers.CreateController;
import Controllers.LogedInController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import javax.swing.text.html.ListView;
import java.awt.*;

public class WriteUpdateController {
    public LogedInController logedInController;
    public CreateController createController;
    public Button sendUpdateButton;
    public ListView events;
    public TextArea description;
    public String username = "";
    public String updateDescription;


    public void SetControllers(LogedInController logedInController, CreateController createController){
        this.logedInController = logedInController;
        this.createController = createController;
    }

    public void sendUpdateAction(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(description.getText().equals("") || description == null) {
            alert.setContentText("Please enter description for this update");
            alert.show();
        }// TODO add check for event
        else{
            this.username = logedInController.getUserNameFromUserAsStripAndCleanString();
            this.updateDescription = description.getText();
        }
    }
}
