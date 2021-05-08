/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import healthmanagement.entity.Heap_Staff;
import healthmanagement.entity.Patient;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.PatientManager;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.Confirmations;
import healthmanagement.utility.DeletePatient;
import healthmanagement.utility.NewPatient;
import healthmanagement.utility.Paths;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.RedundancyCheck;
import healthmanagement.utility.SaveEditedFiles;
import healthmanagement.utility.SearchEngine;
import healthmanagement.utility.Table_List_Population;
import healthmanagement.utility.Tabs_And_Content;
import healthmanagement.utility.Templates;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class SearchPatientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button printerButton;

    @FXML
    private TextField searchField;

    @FXML
    public TableView<Patient> tableView;

    @FXML
    private TableColumn number;

    @FXML
    public TableColumn<Patient, String> Id;

    @FXML
    public TableColumn<Patient, String> firstName;

    @FXML
    public TableColumn<Patient, String> lastName;

    @FXML
    public TableColumn<Patient, String> otherName;

    @FXML
    public TableColumn<Patient, String> phoneNo;

    @FXML
    private MenuItem viewFolderItem;

    @FXML
    private MenuItem editInfoItem;

    @FXML
    private MenuItem nurseLogItem;

    @FXML
    private MenuItem sendFolderItem;

    @FXML
    private MenuItem deletePatientItem;

    EntityLink link = new EntityLink();

    PersistActions pa = new PersistActions();

    public ObservableList<Patient> data = FXCollections.observableArrayList();

    public static boolean isEditActive = false;

    public static NewPatient np = new NewPatient();

    public static ArrayList<NewPatient> nplist = new ArrayList<>();

    public static ObservableList<NewPatient> npList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        link.em();
        PatientManager pm = new PatientManager();

        Table_List_Population.numberColumn_patients(number);
        Table_List_Population.ID_patient(Id);
        Table_List_Population.lastName_patient(lastName);
        Table_List_Population.firstName_patient(firstName);
        Table_List_Population.otherName_patient(otherName);
        Table_List_Population.phoneNo_patient(phoneNo);

        String departmentId = FXMLDocumentController.gpi.getTabId();

        tableView.getSelectionModel().getTableView().setId(departmentId);
        data.setAll(pm.findAllPatient(link.em));
        tableView.setItems(data);
        SearchEngine.SearchField_Patient(tableView, searchField, data);

    }

    @FXML
    void addButton_Init(ActionEvent event) {
        addButton.setEffect(new Glow(1.0));

        boolean isNewPatientSavedButton_Init = newPatient();

        if (isNewPatientSavedButton_Init) {
            data.add(NewPatientScreen1Controller.newPatient.getNewPatient());
            pa.NewPatient(link.em, NewPatientScreen1Controller.newPatient.getNewPatient());
            pa.NewPatientImage(link.em, NewPatientScreen1Controller.newPatient.getNewPatientImage());

            link.em.clear();
        }

    }

    @FXML
    void editInfoItem_Init(ActionEvent event) {
        edit();
    }

    public boolean newPatient() {

        NewPatientScreensController mainContainer = new NewPatientScreensController();
        mainContainer.loadScreen(Paths.patientScreen1ID, Paths.patientScreen1File);
        mainContainer.loadScreen(Paths.patientScreen2ID, Paths.patientScreen2File);

        Stage primaryStage = new Stage();
        mainContainer.setScreen(Paths.patientScreen1ID);

        primaryStage.initOwner(Mainmanagement.stage);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.setResizable(false);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("New Patient");

        primaryStage.showAndWait();

        return NewPatientScreen2Controller.isSaveClicked();
    }

    @FXML
    void deletePatientItem_Init(ActionEvent event) {
        delete();
    }

    @FXML
    void nurseLog_Init(ActionEvent event) {

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            String name = tableView.getSelectionModel().getSelectedItem().getLastName() + " "
                    + tableView.getSelectionModel().getSelectedItem().getFirstName() + " " + tableView.getSelectionModel().getSelectedItem().getOtherName();
            Stage primaryStage = new Stage();
            primaryStage.setTitle(name);
            primaryStage.initOwner(Mainmanagement.stage);
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.setResizable(false);
            np.setNewPatient(tableView.getSelectionModel().getSelectedItem());
            Templates templates = new Templates(primaryStage, Paths.dailyLogList, Paths.dailyLogList);

        } else {
            BugLog.alertAll("Pls. Select a Patient", "Invalid Selection", "Select Patient", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void viewFolder_Init(ActionEvent event) {

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            Tab tab = new Tab();
            np.setNewPatient(tableView.getSelectionModel().getSelectedItem());
            //   npList.setAll(RedundancyCheck.checkrepeatition(npList, np));

            FileViewController.mapPatient.put(tableView.getSelectionModel().getSelectedItem().getId(), tableView.getSelectionModel().getSelectedItem());

            Tabs_And_Content tac = new Tabs_And_Content(FXMLDocumentController.tabpane_open, tab, true, (tableView.getSelectionModel().getSelectedItem().getLastName() + tableView.getSelectionModel().getSelectedItem().getFirstName()), tableView.getSelectionModel().getSelectedItem().getId(), Paths.folder, "");

        } else {
            BugLog.alertAll("Pls. Select a Patient", "Invalid Selection", "Select Patient", Alert.AlertType.INFORMATION);

        }
    }
    int index = 0;

    @FXML
    void viewFolder_MouseClicked(MouseEvent event) {

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            if (event.getClickCount() == 2) {
                Tab tab = new Tab();
                np.setNewPatient(tableView.getSelectionModel().getSelectedItem());
                //  nplist.add(index, np);
                //   npList.setAll(RedundancyCheck.checkrepeatition(npList, np));
                FileViewController.mapPatient.put(tableView.getSelectionModel().getSelectedItem().getId(), tableView.getSelectionModel().getSelectedItem());
                Tabs_And_Content tac = new Tabs_And_Content(FXMLDocumentController.tabpane_open, tab, true, (tableView.getSelectionModel().getSelectedItem().getLastName() + tableView.getSelectionModel().getSelectedItem().getFirstName()), tableView.getSelectionModel().getSelectedItem().getId(), Paths.folder, "");

            }
        } else {
            BugLog.alertAll("Pls. Select a Patient", "Invalid Selection", "Select Patient", Alert.AlertType.INFORMATION);
        }
        index++;
    }

    @FXML
    void sendItem_Init(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Send File to Doctor or/and to Department");
            primaryStage.initOwner(Mainmanagement.stage);
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.setResizable(false);

            np.setNewPatient(tableView.getSelectionModel().getSelectedItem());
            Templates templates = new Templates(primaryStage, Paths.sendTemplate, Paths.sendTemplate);

        } else {
            BugLog.alertAll("Pls. Select a Patient", "Invalid Selection", "Select Patient", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void addButton_MouseEntered(MouseEvent event
    ) {
        addButton.setOpacity(0.55);
    }

    @FXML
    void addButton_MouseExited(MouseEvent event
    ) {
        addButton.setOpacity(1.0);
        addButton.setEffect(new Glow(0.0));
    }

    @FXML
    void editButton_Init(ActionEvent event
    ) {
        edit();
    }

    @FXML
    void editButton_MouseEntered(MouseEvent event
    ) {
        editButton.setOpacity(0.55);
    }

    @FXML
    void editButton_MouseExited(MouseEvent event
    ) {
        editButton.setOpacity(1.0);
        editButton.setEffect(new Glow(0.0));
    }

    @FXML
    void deleteButton_Init(ActionEvent event
    ) {
        delete();
    }

    @FXML
    void deleteButton_MouseEntered(MouseEvent event
    ) {
        deleteButton.setOpacity(0.55);
    }

    @FXML
    void deleteButton_MouseExited(MouseEvent event
    ) {
        deleteButton.setOpacity(1.0);
        deleteButton.setEffect(new Glow(0.0));
    }

    @FXML
    void printerButton_Init(ActionEvent event
    ) {

    }

    @FXML
    void printerButton_MouseEntered(MouseEvent event
    ) {
        printerButton.setOpacity(0.55);
    }

    @FXML
    void printerButton_MouseExited(MouseEvent event
    ) {
        printerButton.setOpacity(1.0);
        printerButton.setEffect(new Glow(0.0));
    }

    @FXML
    void searchField_MouseEntered(MouseEvent event
    ) {
        searchField.setOpacity(0.8);
    }

    @FXML
    void searchField_MouseExited(MouseEvent event
    ) {
        searchField.setOpacity(1.0);
        searchField.setEffect(new Glow(0.0));
    }

    void edit() {
        PatientManager pm = new PatientManager();

        if (tableView.getSelectionModel().getSelectedItem() != null) {
            isEditActive = true;
            NewPatientScreen1Controller.newPatient.setNewPatient(tableView.getSelectionModel().getSelectedItem());
            NewPatientScreen1Controller.newPatient.setNewPatientImage(pm.findPatientImage(link.em, tableView.getSelectionModel().getSelectedItem().getId()));
            int index = tableView.getSelectionModel().getFocusedIndex();

            boolean isEditPatient = newPatient();

            if (isEditPatient) {
                data.set(index, NewPatientScreen1Controller.newPatient.getNewPatient());
                tableView.getSelectionModel().select(index);

                SaveEditedFiles.Patient(link.em, NewPatientScreen1Controller.newPatient.getNewPatient(), NewPatientScreen1Controller.newPatient.getNewPatientImage());
                link.em.clear();
            }

        } else {
            BugLog.alertAll("Pls. Select a Patient", "Invalid Selection", "Select Patient", Alert.AlertType.INFORMATION);
        }

        isEditActive = false;
    }

    void delete() {

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {

                Optional<ButtonType> result = Confirmations.deleteConfirmation("Are you sure you want to delete this file.", "Please confirm file removal").showAndWait();

                if (result.get() == ButtonType.OK) {

                    DeletePatient dp = new DeletePatient(link.em, tableView.getSelectionModel().getSelectedItem());

                    tableView.getItems().remove(selectedIndex);
                } else {

                }

            }
        } else {
            BugLog.alertAll("Pls. Select a Patient", "Invalid Selection", "Select patient", Alert.AlertType.INFORMATION);
        }
    }

}
