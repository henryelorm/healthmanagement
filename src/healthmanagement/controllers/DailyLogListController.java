/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import healthmanagement.entity.Records_patient_NurseLog;
import healthmanagement.entityManager.DailyRecordManager;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.DeleteFile;
import healthmanagement.utility.Paths;
import healthmanagement.utility.Records;
import healthmanagement.utility.Table_List_Population;
import healthmanagement.utility.Templates;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class DailyLogListController implements Initializable {

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
    private TableView<Records_patient_NurseLog> tableView;

    @FXML
    private TableColumn number;

    @FXML
    public TableColumn<Records_patient_NurseLog, String> dateColumn;

    public static ObservableList<Records_patient_NurseLog> dailyList = FXCollections.observableArrayList();

    public static Records records = new Records();

    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        link.em();
        DailyRecordManager drm = new DailyRecordManager();
     
        Table_List_Population.numberColumn_DailyLog(number);
        Table_List_Population.date_nurseLog(dateColumn);
        dailyList.setAll(drm.findAllDailyLogs_Nurse(link.em, SearchPatientController.np.getNewPatient()));
        tableView.setItems(dailyList);
    }

    @FXML
    void addButton_Init(ActionEvent event) {
        Stage primaryStage = new Stage();

        primaryStage.initOwner(Mainmanagement.stage);
        primaryStage.initModality(Modality.WINDOW_MODAL);

        primaryStage.setResizable(false);

        Templates templates = new Templates(primaryStage, Paths.nurseLog, Paths.nurseLog);

    }

    @FXML
    void addButton_MouseEntered(MouseEvent event) {
        addButton.setOpacity(1.0);
    }

    @FXML
    void addButton_MouseExited(MouseEvent event) {
        addButton.setOpacity(0.5);
        addButton.setEffect(new Glow(0.0));
    }

    @FXML
    void editButton_Init(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Stage primaryStage = new Stage();

            DailyRecordManager drm = new DailyRecordManager();
            Records_patient_NurseLog rpn = drm.findFileById(link.em, tableView.getSelectionModel().getSelectedItem());
            records.setRecordsNurseLog(rpn);
            primaryStage.initOwner(Mainmanagement.stage);
            primaryStage.initModality(Modality.WINDOW_MODAL);

            primaryStage.setResizable(false);

            Templates templates = new Templates(primaryStage, Paths.nurseLog, Paths.nurseLog);
        } else {
            BugLog.alertAll("Pls. Select a File", "Invalid Selection", "Select File", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void editButton_MouseEntered(MouseEvent event) {
        editButton.setOpacity(1.0);
    }

    @FXML
    void editButton_MouseExited(MouseEvent event) {
        editButton.setOpacity(0.5);
        editButton.setEffect(new Glow(0.0));
    }

    @FXML
    void deleteButton_Init(ActionEvent event) {

        if (tableView.getSelectionModel().getSelectedItem() != null) {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            DeleteFile df = new DeleteFile(link.em, tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().remove(selectedIndex);

        } else {
            BugLog.alertAll("Pls. Select a File", "Invalid Selection", "Select File", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void deleteButton_MouseEntered(MouseEvent event) {
        deleteButton.setOpacity(1.0);
    }

    @FXML
    void deleteButton_MouseExited(MouseEvent event) {
        deleteButton.setOpacity(0.5);
        deleteButton.setEffect(new Glow(0.0));
    }

    void editLoadFile() {

    }

}
