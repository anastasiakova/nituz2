package View;

import Controllers.CreateController;
import Controllers.LogedInController;
import Controllers.SearchController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryController {

    @FXML
    public javafx.scene.control.TextField categoryTextField;
    public javafx.scene.control.Button addButton;
    public javafx.scene.control.Button backButton;
    public javafx.scene.control.ListView cetgoriesTable;
    public static final ObservableList data = FXCollections.observableArrayList();
    public CreateController cc;
    public  SearchController sc;
    public ObservableList<String> categories;

    public void SetControllers(SearchController searchController, CreateController createController) {
        this.sc = searchController;
        this.cc = createController;
        init();
    }

    public void init(){
        categories = sc.getAllCategories();
        cetgoriesTable.setItems(categories);
        cetgoriesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    System.out.println("clicked on " + cetgoriesTable.getSelectionModel().getSelectedItem());
                }
                catch (Exception e){
                    System.out.println("");
                }
            }

        });
    }
    public void addCategory(ActionEvent actionEvent) {
        if (!categories.contains(categoryTextField.getText())) {
            cc.insertCategoryToTable(categoryTextField.getText());
        }
        else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Category already inside! ");
            errorAlert.setContentText("the category you just made is alredy in the list :)");
            errorAlert.showAndWait();

        }
        init();
        System.out.println(categories);
    }

    public void closeWind(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }




}
