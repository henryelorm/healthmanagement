/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.entity.Records_Patient;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.utility.FieldCheck;
import healthmanagement.utility.FieldCheckSensitivity;
import healthmanagement.utility.GetProgramIDs;
import healthmanagement.utility.Records;
import healthmanagement.utility.Table_List_Population;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class FileviewConfirmationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField timeField;

    @FXML
    private Label fieldCheck;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    public static boolean okClicked = false;

    public static boolean isOkClicked() {
        return okClicked;
    }

    public static Records records = new Records();

    public static Map<String, ObservableList<Records_Patient>> map = new HashMap<>();

    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        link.em();
        datePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd-MM-yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {

                DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                datePicker.setValue(fromString(dateFormat1.format(new Date())));
                timeField.setText(sdf.format(cal.getTime()));
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        okClicked = false;
        FieldCheckSensitivity.timeField(timeField, fieldCheck);
    }

    @FXML
    void okButton_Init(ActionEvent event) {
        Records_Patient rp = new Records_Patient();

        if (FieldCheck.timeField(timeField, fieldCheck) == true) {
            fieldCheck.setOpacity(1.0);
        } else {
            rp.setPatientID(FXMLDocumentController.gpi.getTabId());
            rp.setCreatedDate(datePicker.getValue().toString());
            rp.setCreatedTime(timeField.getText());

            records.setRecords(rp);
            okClicked = true;
            Stage primaryStage = (Stage) okButton.getScene().getWindow();
            primaryStage.close();
        }
    }

    @FXML
    void datePickerField_MouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            datePicker.setEditable(true);
        }
    }

    @FXML
    void datePickerField_MouseExited(MouseEvent event) {
        datePicker.setEditable(false);
    }

    @FXML
    void timeField_MouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            timeField.setEditable(true);
        }
    }

    @FXML
    void timeField_MouseExited(MouseEvent event) {
        timeField.setEditable(false);
    }

    @FXML
    void okButton_MouseEntered(MouseEvent event) {
        okButton.setOpacity(1.0);
    }

    @FXML
    void okButton_MouseExited(MouseEvent event) {
        okButton.setOpacity(0.7);
        okButton.setEffect(new Glow(0.0));

    }

    @FXML
    void cancelButton_Init(ActionEvent event) {
        cancelButton.setEffect(new Glow(1.0));
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void cancelButton_MouseEntered(MouseEvent event) {
        cancelButton.setOpacity(1.0);
    }

    @FXML
    void cancelButton_MouseExited(MouseEvent event) {
        cancelButton.setOpacity(0.7);
        cancelButton.setEffect(new Glow(0.0));
    }

}
