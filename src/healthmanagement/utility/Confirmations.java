/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.Mainmanagement;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Elorm
 */
public class Confirmations {

    public static Alert deleteConfirmation(String header, String context) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(Mainmanagement.stage);
        alert.setTitle("Confirmation");
        alert.setHeaderText(header);
        alert.setContentText(context);

        return alert;

    }

    public static TextInputDialog editConfirmation() {

        TextInputDialog tid = new TextInputDialog();
        tid.initOwner(Mainmanagement.stage);
        tid.setTitle("New Class Name");
        tid.setContentText("Pleae input new class name: ");
        tid.setHeaderText(null);

        return tid;

    }

    public static TextInputDialog passWordRemovalConfirmation() {

        TextInputDialog tid = new TextInputDialog();
        tid.initOwner(Mainmanagement.stage);
        tid.setTitle("Remove Password");
        tid.setContentText("Pleae input current Password:");
        tid.setHeaderText(null);

        return tid;

    }


  
}
