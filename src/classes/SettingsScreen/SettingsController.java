

// PLEASE DO NOT MARK
// AS DATABASE WAS NOT BEING LOADED INTO JAR FILE


package classes.SettingsScreen;

import classes.DKHelpers;
import classes.Login;
import classes.Product;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.File;
import java.net.URLEncoder;
import classes.libs.Files;

import javax.swing.filechooser.FileSystemView;

/**
 * @author Devansh Kaloti
 * @version 1.0*IN
 *
 * Settings controller, for user to manage products and categories
*/
public class SettingsController extends DKHelpers {
    @FXML
    JFXTextField pName;
    @FXML
    JFXTextField pPrice;
    @FXML
    JFXComboBox pCategory;
    @FXML
    JFXComboBox pNameDelete;
    @FXML
    Label message;
    @FXML
    JFXButton upload;

    String filePath = null; // Filepath of picture upload

    /**
     * Run when launched
     */
    public void initialize() {
        Login.close();
        pCategory.getItems().addAll(0, 1, 2, 3, 4, 5);

    }

    /**
     * Add product into database
     */
    public void addProduct() {
        if (filePath.isEmpty()) {
            error("Error", "Please select/upload a image!");
        }


        if (isDouble(pPrice.getText())) { // Make sure price is double
            Product product = new Product(pName.getText(), pCategory.getSelectionModel().getSelectedIndex(), toDouble(pPrice.getText()), URLEncoder.encode(filePath));
            product.create();
            message.setText("Product Added Successfully!");
        } else {
            error("Error", "Please type a valid price!");
        }

    }


    /**
     * Save picture path to be saved into database
     * Copy picture into accessible directory
     */
    public void uploadPicture() {
        boolean result = false;

        try {
            File dir = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/DKDinersPOS");

            // If folder doesn't exist, create
            if (!dir.exists()) {
                try{
                    dir.mkdir();
                    result = true;
                }
                catch(SecurityException se) {
                    error("Security Error", "Security Error has occurred while creating new folder 'DKDinersPOS' in User Home");
                } // catch end

            } else {
                result = true; // Folder exists
            }

            // If folder is created/exists
            if (result) {
                File chosenFile = new File(Files.fileChooser()); // Open file chooser, choose file

                Files.copyFileUsingStream(chosenFile, new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/DKDinersPOS/" + chosenFile.getName())); // Copy
                filePath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/DKDinersPOS/" + chosenFile.getName(); // Get path
            }
        } catch (Exception e) {
            error("Error", e.toString());
            print("Error");
        }
    }



}