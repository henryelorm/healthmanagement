/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import healthmanagement.entity.Records_Patient;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.DateOfBirth;
import healthmanagement.utility.FieldCheckSensitivity;
import healthmanagement.utility.MedicationList;
import healthmanagement.utility.Paths;
import healthmanagement.utility.SaveEditedFiles;
import healthmanagement.utility.StringMerge;
import healthmanagement.utility.Table_List_Population;
import healthmanagement.utility.Templates;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class Fileview2Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private ComboBox medicBox;

    @FXML
    private TextField titleField;

    @FXML
    private DatePicker admissionDate;

    @FXML
    private DatePicker dischargeDate;

    @FXML
    private TextField findingsField;

    @FXML
    private TextArea conditionsArea;

    @FXML
    private Button conditionsEditButton;

    @FXML
    private TextArea complaintArea;

    @FXML
    private Button complaintEditButton;

    @FXML
    private Button findingsAddButton;

    @FXML
    private TextArea diagnosisArea;

    @FXML
    private Button diagnosisEditButton;

    @FXML
    private TextField treatmentField;

    @FXML
    private Button treatmentAddButton;

    @FXML
    private ListView<String> medicationListView;

    @FXML
    private TextField medication_DrugNameField;

    @FXML
    private TextField medication_DrugQuantityField;

    @FXML
    private TextField medication_FillField;

    @FXML
    private TextField medication_DosageField;

    @FXML
    private TextField medication_ApplicationField;

    @FXML
    private TextField medication_FreqField;

    @FXML
    private ComboBox medication_DaysField;

    @FXML
    private TextField medication_NotesField;

    @FXML
    private Button medicationAddButton;

    @FXML
    private TextArea recommendArea;

    @FXML
    private Button recommendEditButton;

    @FXML
    private Button medicationSendButton;

    @FXML
    private Button saveButton;

    @FXML
    private TabPane nurseLogTabPane;

    @FXML
    private Tab nurseLogTab;

    @FXML
    private HTMLEditor detailsHTMLEditor;

    @FXML
    private ListView<String> findingsListViews;

    @FXML
    private ListView<String> treatmentsListViews;

    EntityLink link = new EntityLink();

    double mouseEnterValue = 0.55;

    double mouseExitValue = 1.0;

    public static MedicationList med = new MedicationList();

    DateOfBirth date = new DateOfBirth();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        link.em();
        Templates template = new Templates();
        Records_Patient rp = FileTableController.records.getRecords();

        nurseLogTabPane.setId(rp.getId().toString());
        nurseLogTab.setId(rp.getId().toString());
        nurseLogTab.setContent(template.parentPane(Paths.dailyLogQuickViewTable));

        FileViewController.scrollpane_open.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            anchorPane1.setPrefWidth(FileViewController.scrollpane_open.getWidth() - 10);
        });

        titleField.setText(rp.getTitle());
        conditionsArea.setText(rp.getConditions());
        complaintArea.setText(rp.getComplaints());
        diagnosisArea.setText(rp.getDiagnosis());
        recommendArea.setText(rp.getRecommendations());
        detailsHTMLEditor.setHtmlText(rp.getDetails());
        medication_DaysField.setItems(DateOfBirth.medicationDays);
        Table_List_Population.medicals_loadListView(findingsListViews, rp.getFindings());
        Table_List_Population.medicals_loadListView(treatmentsListViews, rp.getTreatment());
        Table_List_Population.medicals_loadListView(medicationListView, rp.getMedication());
        admissionDate.setValue(date.fromString(rp.getAdmissionDate()));
        dischargeDate.setValue(date.fromString(rp.getDischargeDate()));

        medicationListView.setCellFactory(TextFieldListCell.forListView());
        findingsListViews.setCellFactory(TextFieldListCell.forListView());
        treatmentsListViews.setCellFactory(TextFieldListCell.forListView());

        FieldCheckSensitivity.fileView2(titleField, conditionsArea, admissionDate, dischargeDate, complaintArea, diagnosisArea, medicationListView, recommendArea, detailsHTMLEditor, saveButton, findingsListViews, treatmentsListViews);

    }

    @FXML
    void saveButton_Init(ActionEvent event) {
        saveInit();
        BugLog.PopAlert("Saved");
        saveButton.setDisable(true);
    }

    @FXML
    void addClinicalFindings_Init(ActionEvent event) {
        if (!findingsField.getText().isEmpty()) {
            findingsListViews.getItems().add(findingsField.getText());
            findingsField.clear();
        }
    }

    @FXML
    void addClinicalFindings_InitKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (!findingsField.getText().isEmpty()) {
                findingsListViews.getItems().add(findingsField.getText());
                findingsField.clear();
            }
        }
    }

    @FXML
    void treatments_Init(ActionEvent event) {
        if (!treatmentField.getText().isEmpty()) {
            treatmentsListViews.getItems().add(treatmentField.getText());
            treatmentField.clear();
        }
    }

    @FXML
    void treatments_InitKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (!treatmentField.getText().isEmpty()) {
                treatmentsListViews.getItems().add(treatmentField.getText());
                treatmentField.clear();
            }
        }
    }

    @FXML
    void medication_Init(ActionEvent event) {

        if (!medication_DrugNameField.getText().isEmpty() || !medication_DrugQuantityField.getText().isEmpty()) {
            String medication = "Name: " + medication_DrugNameField.getText() + " |Qnt: " + medication_DrugQuantityField.getText() + " |Refill: " + medication_FillField.getText()
                    + " |Dose: " + medication_DosageField.getText() + " |Appl: " + medication_ApplicationField.getText() + " |Freq: " + medication_FreqField.getText()
                    + " |Days: " + medication_DaysField.getValue().toString() + " |Notes: " + medication_NotesField.getText();

            medicationListView.getItems().add(medication);
        }
        medication_DrugNameField.clear();
        medication_DrugQuantityField.clear();
        medication_FillField.clear();
        medication_DosageField.clear();
        medication_ApplicationField.clear();
        medication_FreqField.clear();
        medication_NotesField.clear();
    }

    @FXML
    void medicationSendButton_Init(ActionEvent event) {

        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.initOwner(Mainmanagement.stage);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        Templates template = new Templates(primaryStage, Paths.selectionTemplate, "Selection Template");

    }

    @FXML
    void deleteMenuitemFindings_Init(ActionEvent event) {
        if (findingsListViews.getSelectionModel().getSelectedItem() != null) {

            findingsListViews.getItems().remove(findingsListViews.getSelectionModel().getSelectedIndex());
        } else {
            BugLog.alertAll("Pls. Select an Item", "Invalid Selection", "Select Item", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void deleteMenuitemMedication_Init(ActionEvent event) {
        if (medicationListView.getSelectionModel().getSelectedItem() != null) {

            medicationListView.getItems().remove(medicationListView.getSelectionModel().getSelectedIndex());
        } else {
            BugLog.alertAll("Pls. Select an Item", "Invalid Selection", "Select Item", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void deleteMenuitemTreatments_Init(ActionEvent event) {
        if (treatmentsListViews.getSelectionModel().getSelectedItem() != null) {

            treatmentsListViews.getItems().remove(treatmentsListViews.getSelectionModel().getSelectedIndex());
        } else {
            BugLog.alertAll("Pls. Select an Item", "Invalid Selection", "Select Item", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void saveButton_MouseEnter(MouseEvent event
    ) {

        saveButton.setEffect(new Lighting());
    }

    @FXML
    void saveButton_MouseExit(MouseEvent event
    ) {

        saveButton.setEffect(null);
    }

    @FXML
    void conditionsEditButton_Init(ActionEvent event
    ) {
        conditionsArea.setEditable(true);
        conditionsArea.setStyle("-fx-border-color: #3d6577");
    }

    @FXML
    void conditionsEditButton_MouseEnter(MouseEvent event
    ) {
        conditionsEditButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void conditionsEditButton_MouseExit(MouseEvent event
    ) {
        conditionsEditButton.setOpacity(mouseExitValue);

    }

    @FXML
    void complaintEditButton_MouseEnter(MouseEvent event
    ) {
        complaintEditButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void complaintEditButton_MouseExit(MouseEvent event
    ) {
        complaintEditButton.setOpacity(mouseExitValue);

    }

    @FXML
    void complaintEditButton_Init(ActionEvent event
    ) {
        complaintArea.setEditable(true);
        complaintArea.setStyle("-fx-border-color: #3d6577");
    }

    @FXML
    void findingsAddButton_MouseEnter(MouseEvent event
    ) {
        findingsAddButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void findingsAddButton_MouseExit(MouseEvent event
    ) {
        findingsAddButton.setOpacity(mouseExitValue);

    }

    @FXML
    void diagnosisEditButton_MouseEnter(MouseEvent event
    ) {
        diagnosisEditButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void diagnosisEditButton_MouseExit(MouseEvent event) {
        diagnosisEditButton.setOpacity(mouseExitValue);

    }

    @FXML
    void diagnosisAreaEditButton_Init(ActionEvent event) {
        diagnosisArea.setEditable(true);
        diagnosisArea.setStyle("-fx-border-color: #3d6577");
    }

    @FXML
    void treatmentAddButton_MouseEnter(MouseEvent event) {
        treatmentAddButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void treatmentAddButton_MouseExit(MouseEvent event) {
        treatmentAddButton.setOpacity(mouseExitValue);

    }

    @FXML
    void medicationAddButton_MouseEnter(MouseEvent event) {
        medicationAddButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void medicationAddButton_MouseExit(MouseEvent event) {
        medicationAddButton.setOpacity(mouseExitValue);

    }

    @FXML
    void medicationSendButton_MouseEnter(MouseEvent event) {
        medicationSendButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void medicationSendButton_MouseExit(MouseEvent event) {
        medicationSendButton.setOpacity(mouseExitValue);

    }

    @FXML
    void recommendEditButton_MouseEnter(MouseEvent event
    ) {
        recommendEditButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void recommendEditButton_MouseExit(MouseEvent event) {
        recommendEditButton.setOpacity(mouseExitValue);

    }

    @FXML
    void recommendAreaEditButton_Init(ActionEvent event) {
        recommendArea.setEditable(true);
        recommendArea.setStyle("-fx-border-color: #3d6577");
    }

    private void saveInit() {
        StringMerge merge = new StringMerge();

        Records_Patient rp = new Records_Patient();
        rp.setId(Long.parseLong(FolderViewController.gpi.getTabId()));
        rp.setTitle(titleField.getText());
        rp.setConditions(conditionsArea.getText());
        rp.setAdmissionDate(date.toString(admissionDate.getValue()));
        rp.setDischargeDate(date.toString(dischargeDate.getValue()));
        rp.setComplaints(complaintArea.getText());
        rp.setFindings(merge.merge(findingsListViews.getItems()));
        rp.setDiagnosis(diagnosisArea.getText());
        rp.setTreatment(merge.merge(treatmentsListViews.getItems()));
        rp.setMedication(merge.merge(medicationListView.getItems()));
        rp.setRecommendations(recommendArea.getText());
        rp.setDetails(detailsHTMLEditor.getHtmlText());
        SaveEditedFiles.Record_patient(link.em, rp);
    }

}
