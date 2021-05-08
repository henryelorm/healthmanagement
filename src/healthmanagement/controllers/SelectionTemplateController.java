/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.entity.Department;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class SelectionTemplateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> departmentBox;

    @FXML
    private Label checkLabel;

    @FXML
    private Button sendButton;
    
    
    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        int size = FXMLDocumentController.departmentList.size();
        for (int i = 0; i < size; i++) {
            departmentBox.getItems().add(FXMLDocumentController.departmentList.get(i).getName());
        }

    }

    @FXML
    void sendButton_Init(ActionEvent event) {

    }

    @FXML
    void closeButton_Init(ActionEvent event) {
        Stage primaryStage = (Stage) closeButton.getScene().getWindow();
        primaryStage.close();

    }

}
