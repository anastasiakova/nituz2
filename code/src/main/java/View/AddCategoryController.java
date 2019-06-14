package View;

import Controllers.SearchController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryController {

    @FXML
    public javafx.scene.control.TextField categoryTextField;
    public javafx.scene.control.Button addButton;
    public javafx.scene.control.Button backButton;
    public javafx.scene.control.ListView cetgoriesTable;
    public static final ObservableList data = FXCollections.observableArrayList();
    public  SearchController sc = new SearchController();
    public ObservableList<String> categories = sc.getAllCategories();

    public void init(){
        cetgoriesTable.setItems(categories);
    }
    public void addCategory(ActionEvent actionEvent) {
        if(!categories.contains(categoryTextField.getText()))

            categories.add(categoryTextField.getText());
        else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Category already inside! ");
            errorAlert.setContentText("the category you just made is alredy in the list :)");
            errorAlert.showAndWait();

//            ObservableList<String> items = FXCollections.observableArrayList (
//                    "A", "B", "C", "D");
//            cetgoriesTable.setItems(items);
        }
        System.out.println(categories);
    }
}
