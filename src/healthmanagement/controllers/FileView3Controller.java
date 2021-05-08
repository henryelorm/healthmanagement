/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class FileView3Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        FileViewController.scrollpane_openLab.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            anchorPane2.setPrefWidth(FileViewController.scrollpane_openLab.getWidth() - 10);
        });

    }

}
