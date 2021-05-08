/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import static healthmanagement.controllers.FolderViewController.fileList;
import static healthmanagement.controllers.FolderViewController.np;
import healthmanagement.entity.Patient;
import healthmanagement.entity.Records_Patient;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.RecordManager;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.Confirmations;
import healthmanagement.utility.DeleteFile;
import healthmanagement.utility.GetTab;
import healthmanagement.utility.Paths;
import healthmanagement.utility.Records;
import healthmanagement.utility.Table_List_Population;
import healthmanagement.utility.Tabs_And_Content;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class FileTableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Records_Patient> fileTable;

    @FXML
    public TableColumn<Records_Patient, String> timeColumn;

    @FXML
    public TableColumn<Records_Patient, String> dateColumn;

    public static Map<String, ObservableList<Records_Patient>> map = new HashMap<>();

    public static Records records = new Records();

    ObservableList<Records_Patient> fileList = FXCollections.observableArrayList();

    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        link.em();
        RecordManager rm = new RecordManager();

        Table_List_Population.timeColumn(timeColumn);
        Table_List_Population.dateColumn(dateColumn);

        fileList.setAll(rm.findAllRecordFiles(link.em, FXMLDocumentController.gpi.getTabId()));
        fileTable.setItems(fileList);
        map.put(FolderViewController.np.getNewPatient().getId(), fileList);

    }

    @FXML
    void fileDate_Time_Init(MouseEvent event) {

        if (fileTable.getSelectionModel().getSelectedItem() != null) {

            if (event.getClickCount() == 2) {

                records.setRecords(fileTable.getSelectionModel().getSelectedItem());
                FileTab(fileTable.getSelectionModel().getSelectedItem().getId().toString(), (fileTable.getSelectionModel().getSelectedItem().getCreatedTime() + "/" + fileTable.getSelectionModel().getSelectedItem().getCreatedDate()));
                System.out.println(fileTable.getSelectionModel().getSelectedItem().getId());
                     
            }
        } else {
            BugLog.alertAll("Pls. Select a File", "Invalid Selection", "Select File", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void fileDate_Time_MenuItem(ActionEvent event) {

        if (fileTable.getSelectionModel().getSelectedItem() != null) {

            records.setRecords(fileTable.getSelectionModel().getSelectedItem());
            FileTab(fileTable.getSelectionModel().getSelectedItem().getId().toString(), (fileTable.getSelectionModel().getSelectedItem().getCreatedTime() + "/" + fileTable.getSelectionModel().getSelectedItem().getCreatedDate()));

        } else {
            BugLog.alertAll("Pls. Select a File", "Invalid Selection", "Select File", Alert.AlertType.INFORMATION);
        }

    }

    private void FileTab(String fileId, String timedate) {
        Tab tab = new Tab();

        Tabs_And_Content tac = new Tabs_And_Content(FolderViewController.mapTabPane.get(FXMLDocumentController.gpi.getTabId()), tab, true, timedate, fileId, Paths.fileView, "");

    }

    @FXML
    void Delete_fileDate_Time_MenuItem(ActionEvent event) {

        if (fileTable.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = fileTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {

                Optional<ButtonType> result = Confirmations.deleteConfirmation("Are you sure you want to delete this file.", "Please confirm file removal").showAndWait();

                if (result.get() == ButtonType.OK) {

                    DeleteFile df = new DeleteFile(link.em, fileTable.getSelectionModel().getSelectedItem());
                    int tabValue = GetTab.forDelete(FolderViewController.mapTabPane.get(FXMLDocumentController.gpi.getTabId()), fileTable.getSelectionModel().getSelectedItem().getCreatedTime() + "/" + fileTable.getSelectionModel().getSelectedItem().getCreatedDate());

                    if (tabValue >= 0) {

                        FolderViewController.mapTabPane.get(FXMLDocumentController.gpi.getTabId()).getTabs().remove(tabValue);
                    }
                    fileTable.getItems().remove(selectedIndex);
                } else {

                }

            }
        } else {
            BugLog.alertAll("Pls. Select a File", "Invalid Selection", "Select File", Alert.AlertType.INFORMATION);
        }
    }

}
