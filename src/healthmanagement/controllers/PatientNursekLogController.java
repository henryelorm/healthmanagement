/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.entity.PatientImage;
import healthmanagement.entity.Records_patient_NurseLog;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.PatientManager;
import healthmanagement.utility.ImageBytesConversion;
import healthmanagement.utility.NewPatient;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.SaveEditedFiles;
import healthmanagement.utility.Table_List_Population;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class PatientNursekLogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text nameText;

    @FXML
    private Text patientIdText;

    @FXML
    private Text genderText;

    @FXML
    private TextField bpField;

    @FXML
    private TextField tempField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private ComboBox tempBox;

    @FXML
    private ComboBox heightBox;

    @FXML
    private ComboBox weightBox;

    @FXML
    private TextArea noteArea;

    @FXML
    private ImageView imageView;

    @FXML
    private Button cancelButton;

    @FXML
    private Button finButton;

    EntityLink link = new EntityLink();
    NewPatient np = SearchPatientController.np;
    Records_patient_NurseLog rpn_edit;
    boolean edit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rpn_edit = DailyLogListController.records.getRecordsNurseLog();
        DailyLogListController.records.setRecordsNurseLog(null);
        edit = false;
        try {
            // TODO
            link.em();

            PatientManager pm = new PatientManager();
            np.setNewPatientImage(pm.findPatientImage(link.em, np.getNewPatient().getId()));

            nameText.setText(np.getNewPatient().getLastName() + " " + np.getNewPatient().getFirstName()
                    + " " + np.getNewPatient().getOtherName());
            patientIdText.setText(np.getNewPatient().getPatientId());
            genderText.setText(np.getNewPatient().getGender());
            imageView.setImage(ImageBytesConversion.isFromBytestoImage_Staff(np));

            if (rpn_edit != null) {
                edit = true;
                bpField.setText(rpn_edit.getBp());
                tempField.setText(rpn_edit.getTemp());
                weightField.setText(rpn_edit.getWeight());
                heightField.setText(rpn_edit.getHeight());
                noteArea.setText(rpn_edit.getNotes());

            }
        } catch (IOException ex) {
            Logger.getLogger(PatientNursekLogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void finButton_Init(ActionEvent event) {
        PersistActions pa = new PersistActions();

        if (edit != true) {
            Records_patient_NurseLog rpn = new Records_patient_NurseLog();
            loadEditIntoFields(rpn);
            pa.NurseLog(link.em, rpn);
            Table_List_Population.addDailyLog_to_List(DailyLogListController.dailyList, rpn);
            link.em.clear();
        } else if (rpn_edit != null) {
            loadEditIntoFields(rpn_edit);
            SaveEditedFiles.Record_patient_nurseLog(link.em, rpn_edit);
        }

        Stage primaryStage = (Stage) finButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void cancelButton_Init(ActionEvent event) {
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        primaryStage.close();
    }

    private void loadEditIntoFields(Records_patient_NurseLog rpn) {
        rpn.setPatient(np.getNewPatient());
        rpn.setBp(bpField.getText());
        rpn.setTemp(tempField.getText());
        rpn.setWeight(weightField.getText());
        rpn.setHeight(heightField.getText());
        rpn.setNotes(noteArea.getText());
        rpn.setDate(new Date(System.currentTimeMillis()).toString());
    }

}
