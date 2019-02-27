package classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Devansh Kaloti
 * @version 1.0F
 */

public class Settings {

    public void start() throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("SettingsScreen/Settings.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        stage.setTitle("DKDiners POS Settings");
        stage.setScene(new Scene(root, 975, 630));
        stage.show();


    }
}
