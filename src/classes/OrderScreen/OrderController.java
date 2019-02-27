package classes.OrderScreen;

import classes.DKHelpers;
import classes.Login;
import classes.Product;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;


// HashMap Guide: https://www.w3schools.com/java/java_hashmap.asp

public class OrderController extends DKHelpers {

    // FXML Elements
    @FXML
    AnchorPane tab1;
    @FXML
    AnchorPane tab2;
    @FXML
    AnchorPane tab3;
    @FXML
    AnchorPane tab4;
    @FXML
    AnchorPane tab5;
    @FXML
    JFXListView cartView; // Output screen
    @FXML
    Label subtotal;
    @FXML
    Label hst;
    @FXML
    Label total;
    @FXML
    JFXButton musicButton;


    // Grid Panes, later embedded into the anchorpanes owned by each tab
    final GridPane grid0 = new GridPane();
    final GridPane grid1 = new GridPane();
    final GridPane grid2 = new GridPane();
    final GridPane grid3 = new GridPane();
    final GridPane grid4 = new GridPane();

    // HashMap: Class of @interface Map, used to store key/value pairs. They are simply amazing
    final static HashMap<Product, Integer> PRODUCTS = new HashMap<>(); // List of all products in POS System Database


    // Static Initializer: Run once when class is loaded first time, usually to initialize static properties
    static {
        // IF FROM DATABASE...
//        for (Product product: Product.getList()) {
//            PRODUCTS.put(product, product.category);
//        }

        // FOR EVALUATION PURPOSES
        for (Product product: Product.PRODUCTS) {
            PRODUCTS.put(product, product.category);
        }
    }


    /**
     * Run on launch of View Controller
     * Initialize view
     * Embeds products onto grids, organized by tabs
     */
    public void initialize() {
        Login.close();
        grid0.setPadding(new Insets(20)); grid0.setHgap(25); grid0.setVgap(20);
        grid1.setPadding(new Insets(20)); grid1.setHgap(25); grid1.setVgap(20);
        grid2.setPadding(new Insets(20)); grid2.setHgap(25); grid2.setVgap(20);
        grid3.setPadding(new Insets(20)); grid3.setHgap(25); grid3.setVgap(20);
        grid4.setPadding(new Insets(20)); grid4.setHgap(25); grid4.setVgap(20);


        displayProducts(grid0, 0);
        displayProducts(grid1, 1);
        displayProducts(grid2, 2);
        displayProducts(grid3, 3);
        displayProducts(grid4, 4);

        tab1.getChildren().addAll(grid0);
        tab2.getChildren().addAll(grid1);
        tab3.getChildren().addAll(grid2);
        tab4.getChildren().addAll(grid3);
        tab5.getChildren().addAll(grid4);


    }

    /**
     * This emebeds products from a certain category into a gridpane
     *
     * @param grid the current gridpane which will hold products from this category
     * @param category the category id that the products will be taken from
     */
    public void displayProducts(GridPane grid, int category) {


        // Coordinates in Grid
        int x = 0;
        int y = 0;

        // For each product in HashMap, by keys
        for (Product product: PRODUCTS.keySet()) { // .keySet returns keys of HashMap!
            if (PRODUCTS.get(product) != category) continue; // If product doesn't belong in category, skip!

            // Update Coordinates if new row needed.
            if (x % 3 == 0) {
                x = 0;
                y += 1;
            }


            // Layout Objects
            VBox vbox = new VBox(10);
            HBox hbox = new HBox(10);

            // Elements of VBox
            // *Image
            print(product.imageUrl);

            ImageView image = new ImageView(new Image(getClass().getResourceAsStream(product.imageUrl))); // IF LOADING STATIC FOR EVALUATION
//            ImageView image = new ImageView(new Image("file:" + product.imageUrl)); // IF FROM DATABASE
//            ImageView image = new ImageView(new Image("/classes/libs/300.png")); // IF LOADING PLACEHOLDER
            image.setFitHeight(150);
            image.setFitWidth(150);

            // * Text
            Label text = new Label(format("$%s, %s", product.price, product.description));

            // Elements of HBox
            // * Add Button
            JFXButton addBtn = new JFXButton("+");
            addBtn.setOnAction(e -> addQty(product));
            addBtn.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            // * Subtract Button
            JFXButton subtractBtn = new JFXButton("-");
            subtractBtn.setOnAction(e -> subtractQty(product));
            subtractBtn.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            // HBOX which will hold buttons
            hbox.getChildren().addAll(addBtn, subtractBtn);
            hbox.setAlignment(Pos.CENTER);

            vbox.getChildren().addAll(text, image, hbox);
            grid.add(vbox, x, y);
            x += 1;
        }

    }


    /**
     * Add quantity of chosen product
     * Update cart
     * Update user's display cart
     * @param product The product which user has chosen
     */
    private void addQty(Product product) {

        // Check if product is already in cart or not
        if (product.qty <= 0) {
            cartView.getItems().add(product);
        }

        product.qty += 1;
        cartView.refresh(); // Refresh listview
        updateSummary(); // Textbox at bottom
    }

    /**
     * Subtract quantity of chosen product
     * Update cart
     * Update user's display cart
     * @param product The product which user has chosen to remove
     */
    private void subtractQty(Product product) {
        if (product.qty <= 1) {
            product.qty = 0;
            cartView.getItems().remove(product);
        } else {
            product.qty -= 1;
        }


        cartView.refresh();
        updateSummary();

    }

    /**
     * Called once user has called the finalized order button
     * Will create a new alert box with the summary of user's order
     * Clear existing data for new customer order
     */
    public void finalizeOrder() {
        JFXAlert alert = new JFXAlert();
        alert.setTitle("Finalized Order");

        alert.setHeaderText("Order for this table has been confirmed and charged to customer's account\n \n " +
                "\n Subtotal: " + this.subtotal.getText() +
                "\n HST @ 13%: " + this.hst.getText() +
                "\n Total: " + this.total.getText());
        alert.setHeight(500);
        alert.show();

        cancelOrder();
        updateSummary();
    }

    /**
     * Reset all quantity to 0, and remove all items from cart and user's cart view
     */
    public void cancelOrder() {
        for (Product product: PRODUCTS.keySet()) {
            product.qty = 0;
        }

        cartView.getItems().removeAll();
        cartView.getItems().remove(0, cartView.getItems().size());
        updateSummary();
        cartView.refresh();

    }

    /**
     * Update the summary box at the end
     * Update the Subtotal, HST, Total Labels
     */
    private void updateSummary() {
        double subtotal = 0.00;
        for (Product product: PRODUCTS.keySet()) {
            subtotal += product.qty * product.price;
        }

        this.subtotal.setText("$" + toString(round(subtotal, 2, true)));
        this.hst.setText("$" + toString(round(subtotal*.13, 2, true)));
        this.total.setText("$" + toString(round(subtotal*1.13, 2, true)));


    }


    static AudioStream audioStream;

    /**
     * Play music upon click of button
     */
    public void playMusic() {
        try {
            InputStream INPUTSTREAM = getClass().getResourceAsStream("defaultPhotos/sound2.wav"); // Get sound file as Stream
            audioStream = new AudioStream(INPUTSTREAM); // Convert into AudioStream
            AudioPlayer.player.start(audioStream); // Start Stream

            musicButton.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
            error("Error", "Error playing music. ");
        }
    }

    /**
     * Stop music
     * Called upon user exit
     */
    public static void closeMusic() {
        AudioPlayer.player.stop(audioStream);
    }





}