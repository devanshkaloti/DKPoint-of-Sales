package classes.LoginScreen;


import classes.DKHelpers;
import classes.Login;
import classes.Order;
import classes.Settings;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;


/**
 * @author Devansh Kaloti
 * @version 1.0F
 *
 * LoginController is the controller class for the Login Screen
 * It controls the view
 * Extends DKHelpers
 */
public class LoginController extends DKHelpers {

    @FXML
    JFXTextField username;
    @FXML
    JFXPasswordField password;


    /**
     * Initializer for View
     * Run at launch
     */
    public void initialize() {

        // Change font color
        username.setStyle("-fx-text-inner-color: white;");
        password.setStyle("-fx-text-inner-color: white;");


    }

    /**
     * Authenticate user with supplied username/password
     * Launch OrderScreen
     */
    public void authenticate() {
        // For evaluation purposes, credentials are not checked
        // Launch Order Screen by default
            try {
                new Order().start(Login.window);

            } catch (Exception e) { // Any exception could occur
                print(e);
            }
    }

    /**
     * Launch Settings View if requested
     */
    public void settings() {
        try {
            new Settings().start();
        } catch (Exception e) {
            print(e);
        }
    }
}
