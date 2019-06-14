package View;

import Controllers.CreateController;
import Controllers.LogedInController;
import Controllers.SearchController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


public class WriteUpdateController {
    public LogedInController logedInController;
    public CreateController createController;
    public Button sendUpdateButton;
    public ListView events;
    public ObservableList<String> eventsData;
    public javafx.scene.control.TextArea description;
    public String username = "";
    public String updateDescription;


    public void SetControllers(LogedInController logedInController, CreateController createController, SearchController searchController){
        this.logedInController = logedInController;
        this.createController = createController;
        eventsData = searchController.getMyEvents(logedInController.getUserNameFromUserAsStripAndCleanString());
        events.setItems(eventsData);
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
