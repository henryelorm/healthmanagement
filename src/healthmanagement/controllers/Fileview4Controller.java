/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.entity.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class Fileview4Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text emailText;

    @FXML
    private Text cityText;

    @FXML
    private Text houseNoText;

    @FXML
    private Text nationalityText;

    @FXML
    private Text dobText;

    @FXML
    private Text occupationText;

    @FXML
    private Text statusText;

    @FXML
    private Text spouseNameText;

    @FXML
    private Text spouseNoText;

    @FXML
    private Text fatherText;

    @FXML
    private Text motherText;

    @FXML
    private Text parentsNoText;

    @FXML
    private Text emergency1Text;

    @FXML
    private Text emergency2Text;

    @FXML
    private Text emergency3Text;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Patient p = FileViewController.mapPatient.get(FXMLDocumentController.gpi.getTabId());

        emailText.setText(p.getEmail());
        cityText.setText(p.getCity());
        houseNoText.setText(p.getHouseNo());
        nationalityText.setText(p.getNationality());
        dobText.setText(p.getDob().toString());
        occupationText.setText(p.getOccupation());
        statusText.setText(p.getMaritalStatus());
        spouseNameText.setText(p.getSpouseName());
        spouseNoText.setText(p.getSpouseNo());
        fatherText.setText(p.getFatherName());
        motherText.setText(p.getMotherName());
        parentsNoText.setText(p.getParentsPhone());
        emergency1Text.setText(p.getEmergencyName1() + " " + p.getEmergencyPhone1() + " " + p.getEmergencyRelation1());
        emergency2Text.setText(p.getEmergencyName2() + " " + p.getEmergencyPhone2() + " " + p.getEmergencyRelation2());
        emergency3Text.setText(p.getEmergencyName3() + " " + p.getEmergencyPhone3() + " " + p.getEmergencyRelation3());

    }

}
