package View;

import Controllers.CreateController;
import Controllers.LogedInController;
import Controllers.SearchController;
import Model.EOCUser;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class OpenWindowController {

    public String userName = "";
    public String password = "";
    public TextField userText;
    public TextField passText;
    public Button loginButton;
    public Button logOutButton;
    public Button createEventButton;
    public Button writeUpdateButton;
    public Button addCategoryButton;
    public Label welcomeLabel;
    public LogedInController logedInController = new LogedInController();
    public CreateController createController = new CreateController();
    public SearchController searchController = new SearchController();
    public boolean userModeOn;
    public Label useLabel;
    public Label passLabel;

    public void setController(LogedInController logedInController) {
        this.logedInController = logedInController;
    }

    public void initButtons() {
        this.logOutButton.setVisible(false);
        this.welcomeLabel.setVisible(false);
        this.createEventButton.setDisable(true);
        this.writeUpdateButton.setDisable(true);
        this.addCategoryButton.setDisable(true);
    }

    public void logINButtonAction(ActionEvent actionEvent) {
        userName = userText.getText();
        password = passText.getText();
        //validate user name & password
        boolean loginSuccessful = false;
        if (userName != "" && password != "") {
            //controller search
            //this.logedInController = new LogedInController();
//            loginSuccessful = searchController.isLoginValid(userText.getText(), passText.getText());
            loginSuccessful = logedInController.tryLogIn(userName, password);
            User loged = logedInController.loged;
            if (loginSuccessful) {
                userModeOn = true;
                loginButtonsMaker();
                if (loged instanceof EOCUser && loged.getRank() == -1)
                    this.addCategoryButton.setDisable(false);
                if (loged instanceof EOCUser)
                    this.createEventButton.setDisable(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("You Suck!");
                alert.show();
            }

        }
    }


    public void loginButtonsMaker() {
        this.userText.setVisible(false);
        this.loginButton.setVisible(false);
        this.passText.setVisible(false);
        this.useLabel.setVisible(false);
        this.passLabel.setVisible(false);
        this.logOutButton.setVisible(true);
        this.welcomeLabel.setText("Welcome " + userName + "!");
        this.welcomeLabel.setVisible(true);
        this.writeUpdateButton.setDisable(false);

    }

    public void logOut(ActionEvent actionEvent) {
        this.userText.setVisible(true);
        this.userText.setText("");
        this.passText.setText("");
        this.loginButton.setVisible(true);
        this.passText.setVisible(true);
        this.useLabel.setVisible(true);
        this.passLabel.setVisible(true);
        this.logOutButton.setVisible(false);
        this.welcomeLabel.setVisible(false);
        this.logedInController.deleteUser();
        this.userModeOn = false;
        this.createEventButton.setDisable(true);
        this.writeUpdateButton.setDisable(true);
        this.addCategoryButton.setDisable(true);
    }


    public void addCategory(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Add Category");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("AddCategory.fxml").openStream());
        AddCategoryController addCategoryController = fxmlLoader.getController();
        addCategoryController.SetControllers(this.searchController, this.createController);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();
    }

    public void createEvent(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Create Event");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("CreateEvent.fxml").openStream());
        CreateEventController addCategoryController = fxmlLoader.getController();
        addCategoryController.SetControllers(this.logedInController, this.createController, this.searchController);
        Scene scene = new Scene(root, 450, 670);
        stage.setScene(scene);
        stage.show();
    }

    public void writeUpdate(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Write Update:");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("WriteUpdate.fxml").openStream());
        WriteUpdateController updateController = fxmlLoader.getController();
        updateController.SetControllers(this.logedInController, this.createController, this.searchController);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //window.getScene().getStylesheets().add("/regPages.css");
        //updateController.init();
//            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
        stage.show();
    }
}
