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

public class Login extends Application {
    public static Stage window;

    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Parent root = FXMLLoader.load(this.getClass().getResource("LoginScreen/Login.fxml"));
        window.setTitle("DKDiners Login");
        window.setScene(new Scene(root, 975.0D, 630.0D));
        window.setOnCloseRequest((e) -> {
            OrderController.closeMusic();
        });
        window.show();
    }

    public static void close() {
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}