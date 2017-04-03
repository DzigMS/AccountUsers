package ua.dzms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("User Interface");
        Pane myPane = FXMLLoader.load(getClass().getResource("view/userList.fxml"));
        primaryStage.setScene(new Scene(myPane));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
