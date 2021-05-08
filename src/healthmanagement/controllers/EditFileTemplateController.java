/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class EditFileTemplateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField field1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        field1.setText(Fileview2Controller.med.getItem());

    }

    @FXML
    void saveButton_Init(ActionEvent event) {
       
    }

    @FXML
    void saveButton_MouseEnter(MouseEvent event) {

        saveButton.setOpacity(0.5);
    }

    @FXML
    void saveButton_MouseExit(MouseEvent event) {

        saveButton.setOpacity(1.0);
    }

    @FXML
    void closeButton_Init(ActionEvent event) {
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void closeButton_MouseEnter(MouseEvent event) {

        cancelButton.setOpacity(0.5);
    }

    @FXML
    void closeButton_MouseExit(MouseEvent event) {

        cancelButton.setOpacity(1.0);
    }

}
