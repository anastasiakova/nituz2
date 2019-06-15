package View;

import Controllers.CreateController;
import Controllers.LogedInController;
import Controllers.SearchController;
import Model.Category;
import Model.EOCUser;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    public Button backButton;
    public javafx.scene.control.ListView categoriesLostView;
    public String publisher; //understand from where to get it
    public ArrayList<Category> categories;
    public CreateController createController;
    public LogedInController logedInController;
    public SearchController searchController;
    public ObservableList<String> categoriesFormDB;


    public CreateEventController() {

    }

    public void SetControllers(LogedInController logedInController, CreateController createController, SearchController searchController){
        this.categories = new ArrayList<>();
        this.logedInController = logedInController;
        this.createController = createController;
        this.searchController = searchController;
        init();
    }

    public void init(){
        categoriesFormDB = searchController.getAllCategories();
        categoriesLostView.setItems(categoriesFormDB);
        categoriesLostView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    categories.add(new Category(categoriesLostView.getSelectionModel().getSelectedItem().toString()));
                    System.out.println("clicked on " + categoriesLostView.getSelectionModel().getSelectedItem());
                }
                catch (Exception e){
                    System.out.println("");
                }
            }
        });
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
            this.createController.CreateEvent(publisher, headLine, categories, inCharge, initUpdate);

            Alert goodAlert = new Alert(Alert.AlertType.CONFIRMATION);
            goodAlert.setContentText("Your Event Has Been Created");
            goodAlert.show();


        }

    }

    public void closeWind(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
