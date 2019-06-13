package View;

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

    ArrayList<String> categories = new ArrayList<String>();
    public void addCategory(ActionEvent actionEvent) {
        if(!categories.contains(categoryTextField.getText()))
            categories.add(categoryTextField.getText());
        else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Category already inside! ");
            errorAlert.setContentText("the category you just made is alredy in the list :)");
            errorAlert.showAndWait();
        }
        System.out.println(categories);
    }
}
