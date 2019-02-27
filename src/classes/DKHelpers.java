package classes;// Devansh Kaloti


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

class DataTypes {

    public static String toString(Object string) {
        return String.valueOf(string);
    }

    public static int toInt(Object number) {
        try {
            return Integer.parseInt(number.toString());
        } catch (Exception e) {
            return 0;
        }

    }

    public static double toDouble(Object string) {
        try {
            return Double.parseDouble(string.toString());
        } catch (Exception e) {
            return -0.0;
        }
    }

    public static char toChar(Object string, int index) {
        return toString(string).charAt(index);
    }

    public static char[] toChars(Object string) {
        return toString(string).toCharArray();
    }

}


class Conditions extends DataTypes {

    public static boolean isInt(Object string) {
        boolean isInt = true;

        try {
            Integer.parseInt(string.toString());
        } catch (Exception e) {
            isInt = false;
        }
        return isInt;
    }

    public static boolean isDouble(Object string) {
        boolean isDouble = true;

        try {
            Double.parseDouble(string.toString());
        } catch (Exception e) {
            isDouble = false;
        }
        return isDouble;
    }

    public static boolean between(int value, int a, int b) {
        return a >= value && value <= b;
    }

    public static boolean largerThan(int value, int a) {
        return a >= value;
    }

    public static boolean smallerThan(int value, int b) {
        return b <= value;
    }

}


class UserOutput extends Conditions {

    public static void print(Object message, Object... vars) {
        System.out.println(String.format(message.toString(), vars));
    }

    public static String format(Object message, Object... vars) {
        return String.format(message.toString(), vars);
    }
}


class UserInput extends UserOutput {

    // Take and return string input to user
    public static String input(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        return input.next();
    }

    // Return Input to user in int, give no range, min or or min/max (Method Overloaded)
    public static int input(String message, int... range) {
        boolean isRange = range.length == 2; // Check if range is provided
        while (true) {
            String value = input(message);
            try {
                int val = toInt(value);
                if (range.length == -0) { return val; } // If no range given

                if (isRange) { // If min and max are given
                    if (between(val, range[0], range[1])) { // If number higher than range
                        print("The number must be between %s and %s", range[0], range[1]);
                        continue;
                    }
                } else {
                    if (largerThan(val, range[0])) { // If only Min is given, and condition fails
                        print("The number must be higher than %s", range[0]);
                        continue;
                    }
                }
                return val;
            } catch (NumberFormatException e) { // Error Catching
                print("Invalid number given");
            }
        }
    }

    public static double round(double value, int places, boolean currency) {
        String pattern;
        if (currency) {
            pattern = "##.00";

        } else {
            pattern = "#.";
            for (int i=1; i<=places; i++) {
                pattern += "#";
            }
        }

        DecimalFormat df = new DecimalFormat(pattern);
        return toDouble(df.format(value));
    }
//
//    public static double round(double value, int places) {
//        return round(value, places, false);
//    }

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static String removePunctuation(String string) {
        return string.replaceAll("[^a-zA-Z ]", "");
    }

}


class UserInterface extends UserInput {

    // Basic User Elements
    public static Label label(String text) {
        Label label = new Label();
        label.setText(text);
        return label;
    }


    public static Button button(String text, EventHandler<ActionEvent> action) {
        Button buttonA = new Button();
        buttonA.setOnAction(action);
        buttonA.setText(text);
        return buttonA;
    }

    public static Button enterButton(String text, TextField textField) {
        Button button = new Button();
        button.setOnAction(e -> {String returnText = textField.getText();});
        button.setText(text);
        return button;
    }

    public static TextField textfield(String text) {
        TextField textfield = new TextField();
        textfield.setText(text);
        return textfield;
    }

    public static void error(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error 101 has occurred. Please contact support: devansh@dksources.com");
        alert.setContentText(text);

        alert.showAndWait();
    }

    public static void error(String title1, String title2, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title1);
        alert.setHeaderText(title2);
        alert.setContentText(text);

        alert.showAndWait();
    }

}

class ArrayManipulation extends UserInterface {

    public static String[] reverseArray(String[] array) {


        String[] reversedArray = new String[array.length];
        int x = 0;

        for (int y=array.length - 1; y >= 0; y--) {
            reversedArray[x] = array[y];
            x++;
        }
        return reversedArray;
    }

}


public class DKHelpers extends ArrayManipulation {

}








