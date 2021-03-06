package View;

import Controllers.SearchController;
import Model.Category;
import Model.Organizations;
import Model.SQLModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.smartcardio.CardTerminal;
import java.text.ParseException;
import java.util.Calendar;

import static javafx.application.Application.launch;

public class orenMain extends Application {


        @Override
        public void start(Stage primaryStage) throws Exception{
            SQLModel sqlModel = SQLModel.getInstance();
            FXMLLoader fxmlControl = new FXMLLoader();
            Parent root = fxmlControl.load(getClass().getResource(("OpenWindow.fxml")).openStream());
            primaryStage.setTitle("Emer-Agency");
            OpenWindowController view = fxmlControl.getController();
            view.initButtons();
            primaryStage.setScene(new Scene(root, 600, 300));
            primaryStage.show();


        }

        public static void main (String[]args){launch(args);}
    }

//public class orenMain {
//    public static void main(String[] args) {
//        SearchController sc = new SearchController();
//        System.out.println(sc.getAllCategories());
//    }
//}



