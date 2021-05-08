/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.entity.Patient;
import healthmanagement.entity.Records_Patient;
import healthmanagement.utility.Paths;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class FileViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane scrollpane_medicals;

    @FXML
    private ScrollPane scrollpane_labReport;

    @FXML
    private ScrollPane scrollpane_OtherInfo;

    public static ScrollPane scrollpane_open;

    public static ScrollPane scrollpane_openLab;

    public static ScrollPane scrollpane_openOtherInfo;

    public static Map<String, Patient> mapPatient = new HashMap<>();

    public static Map<String, HashMap<String, Records_Patient>> mapRecord = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            scrollpane_open = scrollpane_medicals;
            scrollpane_openLab = scrollpane_labReport;
            scrollpane_openOtherInfo = scrollpane_OtherInfo;

            Parent root1 = FXMLLoader.load(getClass().getResource(Paths.fileView2));
            Parent root2 = FXMLLoader.load(getClass().getResource(Paths.fileView3));
            Parent root3 = FXMLLoader.load(getClass().getResource(Paths.fileView4));

            scrollpane_medicals.setContent(root1);
            scrollpane_labReport.setContent(root2);
            scrollpane_OtherInfo.setContent(root3);

            scrollpane_medicals.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                //   anchorPane1.setPrefWidth(scrollpane.getWidth() - 10);
            });
            scrollpane_medicals.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                //   anchorPane1.setPrefHeight(scrollpane.getHeight() - 10);
            });
        } catch (IOException ex) {
            Logger.getLogger(FileViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
