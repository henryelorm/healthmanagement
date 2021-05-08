/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.logs;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import healthmanagement.*;

/**
 *
 * @author Elorm
 */
public class BugLog {

    public void Error(String error) {

        JOptionPane.showMessageDialog(null, error, "Bug", JOptionPane.ERROR_MESSAGE);
    }

    public static void Error_static(String message, String error) {

        JOptionPane.showMessageDialog(null, message, error, JOptionPane.ERROR_MESSAGE);
    }

    public static void Error_alert(String message, String error, Stage stage) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle(error);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void alertCall(String errorMessage, String title, String herderText) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(Mainmanagement.stage);
        alert.setTitle(title);
        alert.setHeaderText(herderText);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

    public static void phoneNumberCorrect(String errorMessage, String title, String herderText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(Mainmanagement.stage);
        alert.setTitle(title);
        alert.setHeaderText(herderText);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

    public static void alertAll(String errorMessage, String title, String herderText, Alert.AlertType al) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(Mainmanagement.stage);
        alert.setTitle(title);
        alert.setHeaderText(herderText);
        alert.setContentText(errorMessage);

        alert.showAndWait();

    }

    public static void alert(String message, String error, Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle(error);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void PopAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

}
