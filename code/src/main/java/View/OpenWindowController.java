package View;

import Controllers.LogedInController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;

public class OpenWindowController {

    public String userName = "";
    public String password = "";
    public TextField userText;
    public TextField passText;
    public Button loginButton;
    public Button logOutButton;
    public Label welcomeLabel;
    public LogedInController logedInController;
    public boolean userModeOn;
    public Label useLabel;
    public Label passLabel;

    public void setController(LogedInController logedInController) {
        this.logedInController = logedInController;
    }

    public void initButtons(){
        this.logOutButton.setVisible(false);
        this.welcomeLabel.setVisible(false);

    }

    public void logINButtonAction(ActionEvent actionEvent) {
        userName = userText.getText();
        password = passText.getText();
        //validate user name & password
        boolean loginSuccessful = false;
        if (userName != "" && password != "") {
            //controller search
            this.logedInController = new LogedInController();
//            loginSuccessful = searchController.isLoginValid(userText.getText(), passText.getText());
            loginSuccessful = logedInController.tryLogIn(userName, password);
            if (loginSuccessful) {
                userModeOn = true;
                loginButtonsMaker();
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
        this.welcomeLabel.setText("Welcome "+userName+"!");
        this.welcomeLabel.setVisible(true);

    }



    public void logOut(ActionEvent actionEvent) {
    }

    public void editUpdate(ActionEvent actionEvent) {
    }

    public void writeUpdate(ActionEvent actionEvent) {
    }

    public void createEvent(ActionEvent actionEvent) {
    }
}
