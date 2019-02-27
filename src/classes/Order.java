package classes;

import classes.OrderScreen.OrderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Devansh Kaloti
 * @version 1.0F
 */

public class Order extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root2 = FXMLLoader.load(getClass().getResource("OrderScreen/Order.fxml"));


        primaryStage.setScene(new Scene(root2));
        primaryStage.show();

        primaryStage.setTitle("DKDiners Order Manager");
        primaryStage.setScene(new Scene(root2, 1024, 768));
        primaryStage.setOnCloseRequest(e -> OrderController.closeMusic()); // Turn off Music
        primaryStage.show();

    }



}
