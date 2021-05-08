/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import static healthmanagement.controllers.NewPatientScreen1Controller.newPatient;
import healthmanagement.entity.Patient;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.utility.FieldCheck;
import healthmanagement.utility.FieldCheckSensitivity;
import healthmanagement.utility.NewPatientControlledScreen;
import healthmanagement.utility.Paths;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.StringSplit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class NewPatientScreen2Controller implements Initializable, NewPatientControlledScreen {

    NewPatientScreensController myController;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField idField;

    @FXML
    private TextField confirmIdField;

    @FXML
    private TextField nameEmergency1;

    @FXML
    private TextField phoneEmergency1;

    @FXML
    private TextField relationshipEmergency1;

    @FXML
    private TextField nameEmergency2;

    @FXML
    private TextField phoneEmergency2;

    @FXML
    private TextField relationshipEmergency2;

    @FXML
    private TextField nameEmergency3;

    @FXML
    private TextField phoneEmergency3;

    @FXML
    private TextField relationshipEmergency3;

    @FXML
    private Label idCheck;

    @FXML
    private Label idCheck1;

    @FXML
    private Label idCheck2;

    @FXML
    private Label emergencyCheck;

    @FXML
    private Button prevButton;

    @FXML
    private Button cancelButtons;

    @FXML
    private Button finButtons;

    public static boolean saveClicked = false;

    private boolean editSaveClicked = false;

    public boolean isEditSavedClicked() {
        return editSaveClicked;
    }

    public static boolean isSaveClicked() {
        return saveClicked;
    }

    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        link.em();
        saveClicked = false;
        FieldCheckSensitivity.passwords(idField, confirmIdField, idCheck1, idCheck2, idCheck, idCheck);
        FieldCheckSensitivity.emergency(nameEmergency1, phoneEmergency1, relationshipEmergency1, emergencyCheck);

        if (SearchPatientController.isEditActive) {
            loadPatientForEdit(newPatient.getNewPatient());
        }
    }

    @Override
    public void setScreenParent(NewPatientScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    void finButton_Init(ActionEvent event) {

        if (FieldCheck.password(idField, confirmIdField, idCheck, idCheck1, idCheck2) == true || FieldCheck.emergency(nameEmergency1, phoneEmergency1, relationshipEmergency1, emergencyCheck) == true) {
        } else {

            NewPatientScreen1Controller.newPatient.getNewPatient().setPatientId(idField.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyName1(nameEmergency1.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyName2(nameEmergency2.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyName3(nameEmergency3.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyPhone1(phoneEmergency1.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyPhone2(phoneEmergency2.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyPhone3(phoneEmergency3.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyRelation1(relationshipEmergency1.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyRelation2(relationshipEmergency2.getText());
            NewPatientScreen1Controller.newPatient.getNewPatient().setEmergencyRelation3(relationshipEmergency3.getText());

            Stage primaryStage = (Stage) finButtons.getScene().getWindow();
            primaryStage.close();

            saveClicked = true;
        }

    }

    @FXML
    void prevButton_Init(ActionEvent event) {
        myController.setScreen(Paths.patientScreen1ID);
    }

    @FXML
    void cancelButton_Init(ActionEvent event) {
        Stage primarystage = (Stage) cancelButtons.getScene().getWindow();
        primarystage.close();
    }

    private void loadPatientForEdit(Patient patient) {
        idField.setText(patient.getPatientId());
        confirmIdField.setText(patient.getPatientId());

        nameEmergency1.setText(patient.getEmergencyName1());
        phoneEmergency1.setText(patient.getEmergencyPhone1());
        relationshipEmergency1.setText(patient.getEmergencyRelation1());

        nameEmergency2.setText(patient.getEmergencyName2());
        phoneEmergency2.setText(patient.getEmergencyPhone2());
        relationshipEmergency2.setText(patient.getEmergencyRelation2());

        nameEmergency3.setText(patient.getEmergencyName3());
        phoneEmergency3.setText(patient.getEmergencyPhone3());
        relationshipEmergency3.setText(patient.getEmergencyRelation3());

    }

}
