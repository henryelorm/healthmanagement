/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;

/**
 *
 * @author Elorm
 */
public class FieldCheckSensitivity {

    public static void fileView2(TextField title, TextArea condition, DatePicker admission,
            DatePicker discharge, TextArea complaint, TextArea diagnosis, ListView medication,
            TextArea recommendation, HTMLEditor detail, Button saveButton, ListView findings, ListView treatments) {

        DoubleProperty hPropertyFindings = new SimpleDoubleProperty();

        title.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            saveButton.setDisable(false);
        });

        condition.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            saveButton.setDisable(false);
        });

        admission.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            saveButton.setDisable(false);
        });

        discharge.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            saveButton.setDisable(false);
        });

        complaint.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            saveButton.setDisable(false);
        });

        diagnosis.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            saveButton.setDisable(false);
        });

        medication.itemsProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            saveButton.setDisable(false);
        });
        recommendation.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            saveButton.setDisable(false);
        });
        medication.getItems().addListener((ListChangeListener.Change c) -> {
            saveButton.setDisable(false);
        });
        findings.getItems().addListener((ListChangeListener.Change c) -> {
            saveButton.setDisable(false);
        });
        treatments.getItems().addListener((ListChangeListener.Change c) -> {
            saveButton.setDisable(false);
        });
    }

    public static void nameFields(TextField firstName, TextField lastName, TextField otherName, Label mainCheck) {

        firstName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                mainCheck.setOpacity(0);
            }
        });

        lastName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                mainCheck.setOpacity(0);
            }
        });

        otherName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                mainCheck.setOpacity(0);
            }
        });
    }

    public static void dateOfBirth(ComboBox day, ComboBox month, ComboBox year, Label dobCheck, Label mainCheck) {

        day.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                dobCheck.setOpacity(0);
                mainCheck.setOpacity(0);

            }
        });

        month.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                dobCheck.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });

        year.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                dobCheck.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });
    }

    public static void gender(ComboBox gender, Label genderCheck, Label mainCheck) {

        gender.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                genderCheck.setOpacity(0);
                mainCheck.setOpacity(0);

            }
        });

    }

    public static void status(ComboBox status, Label statusCheck, Label mainCheck) {

        status.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                statusCheck.setOpacity(0);
                mainCheck.setOpacity(0);

            }
        });

    }

    public static void nationality(TextField nationality, Label nationalityCheck, Label mainCheck) {
        nationality.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                nationalityCheck.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });
    }

    public static void houseNo(TextField houseNo, Label houseNoCheck, Label mainCheck) {
        houseNo.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                houseNoCheck.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });
    }

    public static void id(TextField id, Label idCheck, Label mainCheck) {
        id.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                idCheck.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });
    }

    public static void title(ComboBox title, Label titleCheck, Label mainCheck) {
        title.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                titleCheck.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });
    }

    public static void phone(TextField phone, Label phoneCheck, Label mainCheck) {
        phone.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                phoneCheck.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });
    }

    public static void location(TextField location, Label locateCheck, Label mainCheck) {
        location.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                locateCheck.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });
    }

//used: (password: staffSreen1) and (id check: patientScreen2)
    public static void passwords(TextField password, TextField confirmPass, Label passCheck1, Label passCheck2, Label passCheck3, Label mainCheck) {
        password.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                passCheck1.setOpacity(0);
                passCheck2.setOpacity(0);
                passCheck3.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });

        confirmPass.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {

                passCheck1.setOpacity(0);
                passCheck2.setOpacity(0);
                passCheck3.setOpacity(0);
                mainCheck.setOpacity(0);
            }
        });
    }

    public static void emergency(TextField name, TextField phone, TextField relationship, Label emerge) {

        name.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                emerge.setOpacity(0);
            }
        });

        phone.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                emerge.setOpacity(0);
            }
        });

        relationship.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                emerge.setOpacity(0);
            }
        });
    }

    public static void Priority(CheckBox admin, CheckBox lab, CheckBox frontD, CheckBox pharm, Label prioCheck) {
        admin.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!Objects.equals(oldValue, newValue)) {
                prioCheck.setOpacity(0);
            }
        });

        lab.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!Objects.equals(oldValue, newValue)) {
                prioCheck.setOpacity(0);
            }
        });
        frontD.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!Objects.equals(oldValue, newValue)) {
                prioCheck.setOpacity(0);
            }
        });
        pharm.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!Objects.equals(oldValue, newValue)) {
                prioCheck.setOpacity(0);
            }
        });

    }

    public static void usernameField(TextField usernameField, Label usernameCheck) {

        usernameField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                usernameCheck.setOpacity(0);
            }
        });

    }

    //used in fileConfirmation
    public static void timeField(TextField timeField, Label fieldCheck) {

        timeField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                fieldCheck.setOpacity(0);
            }
        });

    }

    // Login Fields
    public static void userpassFields(TextField userField, TextField passField, Label checkLabel) {
        userField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                checkLabel.setOpacity(0);
            }
        });

        passField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
                checkLabel.setOpacity(0);
            }
        });
    }

}
