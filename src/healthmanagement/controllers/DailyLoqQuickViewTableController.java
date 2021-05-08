/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import healthmanagement.entity.Records_Patient;
import healthmanagement.entity.Records_patient_NurseLog;
import healthmanagement.entityManager.DailyRecordManager;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.Paths;
import healthmanagement.utility.Records;
import healthmanagement.utility.Table_List_Population;
import healthmanagement.utility.Templates;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class DailyLoqQuickViewTableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Records_patient_NurseLog> tableView;

    @FXML
    private TableColumn number;

    @FXML
    public TableColumn<Records_patient_NurseLog, String> dateColumn;

    @FXML
    private MenuItem nurseLogItem;

    public static Map<String, ObservableList<Records_patient_NurseLog>> map_daily = new HashMap<>();

    public static ObservableList<Records_patient_NurseLog> dailyList = FXCollections.observableArrayList();

    public static Records record = new Records();

    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        link.em();
        DailyRecordManager drm = new DailyRecordManager();
        Records_Patient rp = FileTableController.records.getRecords();
        tableView.setId(rp.getId().toString());
        Table_List_Population.numberColumn_DailyLog_fileView2(number);
        Table_List_Population.date_nurseLog_fileView2(dateColumn);

        dailyList.setAll(drm.findFileById_and_Date(link.em, rp.getPatientID(), rp.getCreatedDate()));

        map_daily.put(rp.getId().toString(), dailyList);
        tableView.setItems(map_daily.get(rp.getId().toString()));

    }

    @FXML
    void nurseLogItem_Init(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Stage primaryStage = new Stage();
            record.setRecordsNurseLog(tableView.getSelectionModel().getSelectedItem());

            primaryStage.initOwner(Mainmanagement.stage);
            primaryStage.initModality(Modality.WINDOW_MODAL);

            primaryStage.setResizable(false);

            Templates templates = new Templates(primaryStage, Paths.dailyLogQuickView, Paths.dailyLogQuickView);

        } else {
            BugLog.alertAll("Pls. Select an Item", "Invalid Selection", "Select Item", Alert.AlertType.INFORMATION);
        }
    }
    /*
    @FXML
    void nurseLogItem_Init(MouseEvent event) {
        
        if (event.getClickCount() == 2) {

            Stage primaryStage = new Stage();
            record.setRecordsNurseLog(tableView.getSelectionModel().getSelectedItem());

            primaryStage.initOwner(Mainmanagement.stage);
            primaryStage.initModality(Modality.WINDOW_MODAL);

            primaryStage.setResizable(false);

            Templates templates = new Templates(primaryStage, Paths.dailyLogQuickView, Paths.dailyLogQuickView);

        }
        
    }
     */
}
