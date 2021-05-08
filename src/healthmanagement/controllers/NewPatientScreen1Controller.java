/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import static healthmanagement.controllers.NewStaffScreen1Controller.stfImage;
import healthmanagement.entity.Patient;
import healthmanagement.entity.PatientImage;
import healthmanagement.entityManager.PatientManager;
import healthmanagement.utility.ChooseImage;
import healthmanagement.utility.DateOfBirth;
import healthmanagement.utility.FieldCheck;
import static healthmanagement.utility.FieldCheck.statusFieldActivation;
import healthmanagement.utility.FieldCheckSensitivity;
import healthmanagement.utility.IDs_generator;
import healthmanagement.utility.ImageBytesConversion;
import healthmanagement.utility.NewPatient;
import healthmanagement.utility.NewPatientControlledScreen;
import healthmanagement.utility.Paths;
import healthmanagement.utility.Table_List_Population;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class NewPatientScreen1Controller implements Initializable, NewPatientControlledScreen {

    /**
     * Initializes the controller class.
     */
    NewPatientScreensController myController;

    @FXML
    private ComboBox genderBox;

    @FXML
    private ComboBox dayOfBirthBox;

    @FXML
    private ComboBox monthOfBirthBox;

    @FXML
    private ComboBox yearOfBirthBox;

    @FXML
    private ComboBox maritalStatusBox;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField otherNameField;

    @FXML
    private TextField nationalityField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField houseNoField;

    @FXML
    private TextField phoneNoField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField occupationField;

    @FXML
    private TextField fatherNameField;

    @FXML
    private TextField motherNameField;

    @FXML
    private TextField parentsNoField;

    @FXML
    private TextField spouseNameField;

    @FXML
    private TextField spouseNoField;

    @FXML
    private Text spouseNameText;

    @FXML
    private Text spouseNoText;

    @FXML
    private Label genderCheck;

    @FXML
    private Label mainCheck;

    @FXML
    private Label dobCheck;

    @FXML
    private Label nationalityCheck;

    @FXML
    private Label phoneNoCheck;

    @FXML
    private Label houseNoCheck;

    @FXML
    private Label locationCheck;

    @FXML
    private Label statusCheck;

    @FXML
    private ImageView imageView;

    @FXML
    private Button nxtButton;

    @FXML
    private Button cancelButtons;

    public static NewPatient newPatient = new NewPatient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        statusFieldActivation(maritalStatusBox, spouseNameText, spouseNoText, spouseNameField, spouseNoField);
        FieldCheckSensitivity.gender(genderBox, genderCheck, mainCheck);
        FieldCheckSensitivity.nameFields(firstNameField, lastNameField, otherNameField, mainCheck);
        FieldCheckSensitivity.dateOfBirth(dayOfBirthBox, monthOfBirthBox, yearOfBirthBox, dobCheck, mainCheck);
        FieldCheckSensitivity.nationality(nationalityField, nationalityCheck, mainCheck);
        FieldCheckSensitivity.location(locationField, locationCheck, mainCheck);
        FieldCheckSensitivity.houseNo(houseNoField, houseNoCheck, mainCheck);
        FieldCheckSensitivity.phone(phoneNoField, phoneNoCheck, mainCheck);
        FieldCheckSensitivity.status(maritalStatusBox, statusCheck, mainCheck);

        if (SearchPatientController.isEditActive) {
            try {
                loadPatientForEdit(newPatient.getNewPatient());
            } catch (IOException ex) {
                Logger.getLogger(NewPatientScreen1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            newPatient.setNewPatient(new Patient());
        }
    }

    @Override
    public void setScreenParent(NewPatientScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    void nxtButton_Init(ActionEvent event) throws IOException {

        Patient patients = new Patient();
        PatientImage patientImage = new PatientImage();

        if (FieldCheck.genderBox(genderBox, genderCheck) == true || FieldCheck.nameCheck(firstNameField, lastNameField, otherNameField)
                || FieldCheck.dobCheck(dayOfBirthBox, monthOfBirthBox, yearOfBirthBox, dobCheck) == true || FieldCheck.nationality(nationalityField, nationalityCheck) == true
                || FieldCheck.location(locationField, locationCheck) == true || FieldCheck.houseNo(houseNoField, houseNoCheck) == true || FieldCheck.phoneNumber(phoneNoField, phoneNoCheck) == 0
                || FieldCheck.maritalStatusBox(maritalStatusBox, statusCheck) == true || FieldCheck.yearBoxCheck(yearOfBirthBox, dobCheck) == 0) {
            mainCheck.setOpacity(1.0);
        } else {
            if (!SearchPatientController.isEditActive) {
                String id = IDs_generator.Patient_Id();
                patients.setId(id);
                patientImage.setId(id);
            } else {
                String id = newPatient.getNewPatient().getId();
                patients.setId(id);
                patientImage.setId(id);
            }
            patients.setGender(genderBox.getValue().toString());
            patients.setFirstName(firstNameField.getText());
            patients.setLastName(lastNameField.getText());
            patients.setOtherName(otherNameField.getText());
            patients.setDob(DateOfBirth.date(dayOfBirthBox.getValue().toString(), monthOfBirthBox, yearOfBirthBox.getValue().toString()));
            patients.setNationality(nationalityField.getText());
            patients.setCity(locationField.getText());
            patients.setHouseNo(houseNoField.getText());
            patients.setPhoneNo(phoneNoField.getText());
            patients.setEmail(emailField.getText());
            patients.setOccupation(occupationField.getText());
            patients.setFatherName(fatherNameField.getText());
            patients.setMotherName(motherNameField.getText());
            patients.setParentsPhone(parentsNoField.getText());
            patients.setMaritalStatus(maritalStatusBox.getValue().toString());
            patients.setSpouseName(spouseNameField.getText());
            patients.setSpouseNo(spouseNoField.getText());
            myController.setScreen(Paths.patientScreen2ID);
            ImageBytesConversion.getImagebitsForStoreEdit_patient(patientImage, imageView.getImage());

            newPatient.setNewPatient(patients);
            newPatient.setNewPatientImage(patientImage);
        }

    }

    @FXML
    void imageClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            ChooseImage.getImageFile(imageView);
        }
    }

    @FXML
    void cancelButton_Init(ActionEvent event) {
        Stage primarystage = (Stage) cancelButtons.getScene().getWindow();

        primarystage.close();
    }

    @FXML
    void genderBoxClicked(MouseEvent event) {
        genderBox.setItems(Table_List_Population.genderList);
    }

    @FXML
    void dayBoxClicked(MouseEvent event) {

        dayOfBirthBox.setItems(DateOfBirth.dayList());

    }

    @FXML
    void monthBoxClicked(MouseEvent event) {

        monthOfBirthBox.setItems(DateOfBirth.monthList);
    }

    @FXML
    void yearBoxClicked(MouseEvent event) {
        yearOfBirthBox.setItems(DateOfBirth.yearList());
    }

    @FXML
    void maritalStatusBoxClicked(MouseEvent event) {

        maritalStatusBox.setItems(Table_List_Population.maritalStatusList);
    }

    private void loadPatientForEdit(Patient patient) throws IOException {

        imageView.setImage(ImageBytesConversion.isFromBytestoImage_Staff(newPatient));
        genderBox.setValue(patient.getGender());
        firstNameField.setText(patient.getFirstName());
        lastNameField.setText(patient.getLastName());
        otherNameField.setText(patient.getOtherName());
        dayOfBirthBox.setValue(patient.getDob().getDate());
        monthOfBirthBox.setValue(DateOfBirth.getMonth(patient.getDob().getMonth()));
        yearOfBirthBox.setValue(patient.getDob().getYear() + 1900);
        nationalityField.setText(patient.getNationality());
        locationField.setText(patient.getCity());
        houseNoField.setText(patient.getHouseNo());
        phoneNoField.setText(patient.getPhoneNo());
        emailField.setText(patient.getEmail());
        occupationField.setText(patient.getOccupation());
        fatherNameField.setText(patient.getFatherName());
        motherNameField.setText(patient.getMotherName());
        parentsNoField.setText(patient.getParentsPhone());
        maritalStatusBox.setValue(patient.getMaritalStatus());
        spouseNameField.setText(patient.getSpouseName());
        spouseNoField.setText(patient.getSpouseNo());

    }
}
