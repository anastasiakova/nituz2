package View;

import Controllers.CreateController;
import Controllers.LogedInController;
import Controllers.SearchController;
import Model.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


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
    String selectedEventName = "";


    public void SetControllers(LogedInController logedInController, CreateController createController, SearchController searchController){
        this.logedInController = logedInController;
        this.createController = createController;
        this.searchController = searchController;
        eventsData = searchController.getMyEvents(logedInController.getUserNameFromUserAsStripAndCleanString());
        events.setItems(eventsData);


        events.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                selectedEventName = events.getSelectionModel().getSelectedItem().toString().split(",")[0];
            }
        });
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

            Event event = new Event(this.searchController.getSpecificEvent(selectedEventName));
            createController.createUpdate(this.username,event,this.updateDescription);

        }
    }
}
