package View;

import Model.Organizations;
import Model.SQLModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.ParseException;
import java.util.Calendar;

import static javafx.application.Application.launch;

public class orenMain extends Application {


        @Override
        public void start(Stage primaryStage) throws Exception{
            SQLModel sqlModel = SQLModel.getInstance();
            FXMLLoader fxmlControl = new FXMLLoader();
            Parent root = fxmlControl.load(getClass().getResource(("CreateEvent.fxml")).openStream());
            primaryStage.setTitle("VACATION 4 U ");
            CreateEventController view = fxmlControl.getController();
            //view.initButtons();
            primaryStage.setScene(new Scene(root, 950, 620));
            //primaryStage.getScene().getStylesheets().add("/openWindowCss.css");
            primaryStage.show();


        }

        public static void main (String[]args){launch(args);}
    }

//public class orenMain {
//    public static void main(String[] args) {
//
//        System.out.println(Organizations.values()[0]);
//    }


//}



