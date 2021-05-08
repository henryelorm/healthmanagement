/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import static healthmanagement.controllers.SearchPatientController.isEditActive;
import healthmanagement.entity.Patient;

import healthmanagement.entity.Records_Patient;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.PatientManager;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.GetProgramIDs;
import healthmanagement.utility.ImageBytesConversion;
import healthmanagement.utility.NewPatient;
import healthmanagement.utility.Paths;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.SaveEditedFiles;
import healthmanagement.utility.Table_List_Population;
import healthmanagement.utility.Templates;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class FolderViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TabPane mainTabpane;

    @FXML
    private TabPane subTabpane;

    @FXML
    private Tab subTab;

    @FXML
    private Button editButton;

    @FXML
    private Button createFileButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Text nameText;

    @FXML
    private Text idText;

    @FXML
    private Text genderText;

    @FXML
    private Text phoneText;

    @FXML
    private Text dobText;

    @FXML
    private Text documentText;

    @FXML
    private Text prescriptionText;

    public static NewPatient np = SearchPatientController.np;

    public static ObservableList<Records_Patient> fileList = FXCollections.observableArrayList();

    public static Map<String, TabPane> mapTabPane = new HashMap<>();

    public static GetProgramIDs gpi = new GetProgramIDs();

    private SearchPatientController spc = new SearchPatientController();

    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mapTabPane.put(np.getNewPatient().getId(), mainTabpane);

        try {
            // TODO
            link.em();
            PatientManager pm = new PatientManager();

            np.setNewPatientImage(pm.findPatientImage(link.em, np.getNewPatient().getId()));

            nameText.setText(np.getNewPatient().getLastName() + ", " + np.getNewPatient().getFirstName() + " " + np.getNewPatient().getOtherName());
            idText.setText(np.getNewPatient().getPatientId());
            genderText.setText(np.getNewPatient().getGender());
            phoneText.setText(np.getNewPatient().getPhoneNo());
            dobText.setText(np.getNewPatient().getDob().toString());
            imageView.setImage(ImageBytesConversion.isFromBytestoImage_Staff(np));

            // fileTable.getSelectionModel().getTableView().setId(np.getNewPatient().getId());
            subTabpane.setId(np.getNewPatient().getId());
            subTab.setId(np.getNewPatient().getId());
            Parent root = FXMLLoader.load(getClass().getResource(Paths.fileTable));
            root.setId(np.getNewPatient().getId());
            subTab.setContent(root);

        } catch (IOException ex) {
            Logger.getLogger(FolderViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mainTabpane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            if (oldValue != null) {
                oldValue.setStyle("");
            }
            if (!mainTabpane.getTabs().isEmpty()) {
                gpi.setTabId(mainTabpane.getSelectionModel().getSelectedItem().getId());
                newValue.setStyle("-fx-background-color:#7889ab");
            }
        });
    }

    @FXML
    void createFileButton_Init(ActionEvent event) {
        PersistActions pa = new PersistActions();
        boolean isCreateNewFile = createFile();

        if (isCreateNewFile) {
            // fileList.add(FileviewConfirmationController.records.getRecords());
            FileTableController.map.get(FXMLDocumentController.gpi.getTabId()).add(FileviewConfirmationController.records.getRecords());
            pa.FileDate(link.em, FileviewConfirmationController.records.getRecords());
            link.em.clear();
        }
    }

    private boolean createFile() {

        Stage primaryStageDepartment = new Stage();

        primaryStageDepartment.setTitle("Confirm Time and Date");
        primaryStageDepartment.initOwner(Mainmanagement.stage);
        primaryStageDepartment.setResizable(false);
        primaryStageDepartment.initModality(Modality.WINDOW_MODAL);

        Templates tem = new Templates(primaryStageDepartment, Paths.fileConfirmation, "fileConfirmation");

        return FileviewConfirmationController.isOkClicked();
    }

    @FXML
    void documentText_Clicked(MouseEvent event) {

    }

    @FXML
    void documentText_MouseEntered(MouseEvent event) {
        documentText.setUnderline(true);
        documentText.setStyle("-fx-background-color: black");
    }

    @FXML
    void documentText_MouseExited(MouseEvent event) {
        documentText.setUnderline(false);
        documentText.setStyle("-fx-background-color: #043b6b");
    }

    @FXML
    void prescriptionText_MouseEntered(MouseEvent event) {
        prescriptionText.setUnderline(true);
        prescriptionText.setStyle("-fx-background-color: black");
    }

    @FXML
    void prescriptionText_MouseExited(MouseEvent event) {
        prescriptionText.setUnderline(false);
        prescriptionText.setStyle("-fx-background-color: #043b6b");
    }

    @FXML
    void createFileButton_MouseEntered(MouseEvent event) {
        createFileButton.setOpacity(0.5);
    }

    @FXML
    void createFileButton_MouseExited(MouseEvent event) {
        createFileButton.setOpacity(1.0);
        createFileButton.setEffect(new Glow(0.0));
    }

    @FXML
    void editButton_Init(ActionEvent event) {
        edit();
    }

    @FXML
    void editButton_MouseEntered(MouseEvent event) {
        editButton.setOpacity(0.5);
    }

    @FXML
    void editButton_MouseExited(MouseEvent event) {
        editButton.setOpacity(1.0);
        editButton.setEffect(new Glow(0.0));
    }

    void edit() {

        PatientManager pm = new PatientManager();

        String id = FXMLDocumentController.tabpane_open.getSelectionModel().getSelectedItem().getId();
        Patient patient = pm.findPatient(link.em, id);

        System.out.println(FXMLDocumentController.tabpane_open.getSelectionModel().getSelectedItem().getId());

        isEditActive = true;
        NewPatientScreen1Controller.newPatient.setNewPatient(patient);
        NewPatientScreen1Controller.newPatient.setNewPatientImage(pm.findPatientImage(link.em, patient.getId()));

        boolean isEditPatient = spc.newPatient();

        if (isEditPatient) {

            SaveEditedFiles.Patient(link.em, NewPatientScreen1Controller.newPatient.getNewPatient(), NewPatientScreen1Controller.newPatient.getNewPatientImage());
            link.em.clear();
        } else {
            BugLog.alertAll("Pls. Select a Patient", "Invalid Selection", "Select Patient", Alert.AlertType.INFORMATION);
        }

        isEditActive = false;
    }
}
