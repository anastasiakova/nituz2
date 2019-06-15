package View;

import Controllers.CreateController;
import Controllers.LogedInController;
import Controllers.SearchController;
import Model.Event;
import Model.Permission;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class WriteUpdateController {
    public LogedInController logedInController;
    public CreateController createController;
    public SearchController searchController;
    public Button sendUpdateButton;
    public ListView events;
    public ObservableList<String> eventsData;
    public javafx.scene.control.TextArea description;
    public String username = "";
    public String updateDescription;
    public Button backButton;
    String selectedEventName = "";


    public int SetControllers(LogedInController logedInController, CreateController createController, SearchController searchController) {
        this.logedInController = logedInController;
        this.createController = createController;
        this.searchController = searchController;
        eventsData = searchController.getMyEvents(logedInController.getUserNameFromUserAsStripAndCleanString());
        if (null != eventsData.get(0)) {
            events.setItems(eventsData);
            events.setOnMouseClicked(new EventHandler<MouseEvent>() {


                @Override
                public void handle(MouseEvent event) {
                    try{
                        selectedEventName = events.getSelectionModel().getSelectedItem().toString().split(", ")[0];
                        System.out.println(selectedEventName);
                    }
                    catch (Exception e){
                        System.out.println("");
                    }

                }
            });
            return eventsData.size();
        }
        else{
            return -1;
        }
    }

    public void sendUpdateAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (description.getText().equals("") || description == null) {
            alert.setContentText("Please enter description for this update");
            alert.show();
        }// TODO add check for event
        else {
            this.username = logedInController.getUserNameFromUserAsStripAndCleanString();
            this.updateDescription = description.getText();
            if (!selectedEventName.equals("")) {
                Event event = new Event(this.searchController.getSpecificEvent(selectedEventName));
                if (event.getParticipants().get(this.username) == Permission.write) {
                    createController.createUpdate(this.username, event, this.updateDescription);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setContentText("update was added successfully! ");
                    alert1.show();
                    description.setText("");
                }
                else {
                    alert.setContentText("No Write permissions were given for this event.");
                    alert.show();
                }
            } else {
                alert.setContentText("No event was chosen");
                alert.show();
            }
        }
    }

    public void closeWind(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
