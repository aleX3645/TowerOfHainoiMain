package main.StartDirecrory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Класс старта программы
 * */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/main/Menu/Menu.fxml"));
        primaryStage.setTitle("Хайнойские башни");
        primaryStage.setScene(new Scene(root, 310, 310));
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(300);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
