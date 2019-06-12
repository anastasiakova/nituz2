package View;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class OpenWindowController {

    public String userName = "";
    public String password = "";
    public TextField userText;
    public TextField passText;

//    public void logINButtonAction(ActionEvent actionEvent) {
//        userName = userText.getText();
//        password = passText.getText();
//        //validate user name & password
//        boolean loginSuccessful = false;
//        if (userName != "" && password != "") {
//            //controller search
//            this.logedInController = new LogedInController();
////            loginSuccessful = searchController.isLoginValid(userText.getText(), passText.getText());
//            loginSuccessful = logedInController.tryLogIn(userName, password);
//            if (loginSuccessful) {
//                userModeOn = true;
//                initialize();
//                loginButtonsMaker();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setContentText("You Suck!");
//                alert.show();
//            }
//
//        }
//    }



    public void logOut(ActionEvent actionEvent) {
    }

    public void editUpdate(ActionEvent actionEvent) {
    }

    public void writeUpdate(ActionEvent actionEvent) {
    }

    public void createEvent(ActionEvent actionEvent) {
    }
}
