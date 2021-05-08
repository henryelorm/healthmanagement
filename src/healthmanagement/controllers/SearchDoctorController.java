/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import healthmanagement.entity.Heap_Staff;
import healthmanagement.entity.Staff;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.StaffManager;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.Confirmations;
import healthmanagement.utility.DeleteStaff;
import healthmanagement.utility.IDs_generator;
import healthmanagement.utility.NewStaff;
import healthmanagement.utility.Paths;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.SaveEditedFiles;
import healthmanagement.utility.SearchEngine;
import healthmanagement.utility.Table_List_Population;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class SearchDoctorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TableView<Staff> tableView;

    @FXML
    private TableColumn number;

    @FXML
    public TableColumn<Staff, String> Id;

    @FXML
    public TableColumn<Staff, String> firstName;

    @FXML
    public TableColumn<Staff, String> lastName;

    @FXML
    public TableColumn<Staff, String> otherName;

    @FXML
    public TableColumn<Staff, String> phoneNo;

    @FXML
    public TableColumn<Staff, String> task;

    @FXML
    private Text departmentText;

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
    private MenuItem editItem;

    @FXML
    private MenuItem viewProfileItem;

    @FXML
    private MenuItem deleteItem;

    public ObservableList<Staff> data = FXCollections.observableArrayList();

    public static boolean isEditActive = false;

    EntityLink link = new EntityLink();

    public static NewStaff getSelectedStaff = new NewStaff();

    double mouseEnterValue = 0.55;

    double mouseExitValue = 1.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        link.em();
        departmentText.setText("Search For Doctor");

        StaffManager stm = new StaffManager();

        Table_List_Population.numberColumn_Doctor(number);
        Table_List_Population.ID_Doctor(Id);
        Table_List_Population.firstName_Doctor(firstName);
        Table_List_Population.lastName_Doctor(lastName);
        Table_List_Population.otherName_Doctor(otherName);
        Table_List_Population.phoneNo_Doctor(phoneNo);
        Table_List_Population.task_Doctor(task);

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    if (event.getClickCount() == 2) {
                        try {
                            viewInfo();
                        } catch (IOException ex) {
                          //  Logger.getLogger(SearchDoctorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });

        tableView.getSelectionModel().getTableView().setId(IDs_generator.searchDoctorTab_Id());
        data.setAll(stm.findAllDoctors(link.em));
        tableView.setItems(data);

        SearchEngine.SearchField_Staff(tableView, searchField, data);

    }

    @FXML
    void addButton_Init(ActionEvent event) throws IOException {

        boolean isNewStaffSaveButton_Init = newStaff("New Doctor");

        if (isNewStaffSaveButton_Init) {

            // loadStaffList.setAll(tableView.getItems().);
            PersistActions pa = new PersistActions();
            Heap_Staff hs = new Heap_Staff();

            pa.NewStaff(link.em, NewStaffScreen1Controller.st.getNewStaff());
            pa.NewStaffImage(link.em, NewStaffScreen1Controller.stfImage);

            if (NewStaffScreen1Controller.st.getNewStaff().getTitle() == "Doctor") {
                hs.setStaffId(NewStaffScreen1Controller.st.getNewStaff());
                pa.HeapSend_Staff(link.em, hs);
            }

            data.add(NewStaffScreen1Controller.st.getNewStaff());
        }

    }

    private boolean newStaff(String action) {
        NewStaffScreensController mainContainer = new NewStaffScreensController();
        NewStaffScreen1Controller.doctor_check = true;

        mainContainer.loadScreen(Paths.staffScreen1ID, Paths.staffScreen1File);
        mainContainer.loadScreen(Paths.staffScreen2ID, Paths.staffScreen2File);

        NewStaffScreen2Controller nsc = new NewStaffScreen2Controller();

        Stage primaryStage = new Stage();
        mainContainer.setScreen(Paths.staffScreen1ID);

        primaryStage.initOwner(Mainmanagement.stage);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.setResizable(false);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(action);

        primaryStage.showAndWait();

        return NewStaffScreen2Controller.isSaveClicked();
    }

    @FXML
    void viewFolder_MouseClicked(MouseEvent event) throws IOException {

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            if (event.getClickCount() == 2) {

            }
        } else {
            BugLog.alertAll("Pls. Select a Patient", "Invalid Selection", "Select Patient", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void addButton_MouseEntered(MouseEvent event) {
        addButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void addButton_MouseExited(MouseEvent event) {
        addButton.setOpacity(mouseExitValue);
    }

    @FXML
    void viewProfileItem(ActionEvent event) throws IOException {
        viewInfo();
    }

    @FXML
    void editButton_Init(ActionEvent event) {
        edit();
    }

    @FXML
    void editButton_MouseEntered(MouseEvent event) {
        editButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void editButton_MouseExited(MouseEvent event) {
        editButton.setOpacity(mouseExitValue);

    }

    @FXML
    void deleteButton_Init(ActionEvent event) {
        delete();
    }

    @FXML
    void deleteButton_MouseEntered(MouseEvent event) {
        deleteButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void deleteButton_MouseExited(MouseEvent event) {
        deleteButton.setOpacity(mouseExitValue);

    }

    @FXML
    void printerButton_Init(ActionEvent event) {

    }

    @FXML
    void printerButton_MouseEntered(MouseEvent event) {
        printerButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void printerButton_MouseExited(MouseEvent event) {
        printerButton.setOpacity(mouseExitValue);

    }

    @FXML
    void searchField_MouseEntered(MouseEvent event) {
        searchField.setOpacity(mouseEnterValue);
    }

    @FXML
    void searchField_MouseExited(MouseEvent event) {
        searchField.setOpacity(mouseExitValue);

    }

    @FXML
    void editItem_Init(ActionEvent event) {

        edit();

    }

    @FXML
    void deleteItem_Init(ActionEvent event) {
        delete();
    }

    void viewInfo() throws IOException {

        Stage primaryStage = new Stage();

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            StaffViewInfoController.theKindOfStaff = "doctor";

            getSelectedStaff.setNewStaff(tableView.getSelectionModel().getSelectedItem());

            Parent root = FXMLLoader.load(getClass().getResource(Paths.viewStaffInfo));
            root.setStyle("-fx-background-color:transparent;");

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.initOwner(Mainmanagement.stage);
            primaryStage.setResizable(false);
            primaryStage.showAndWait();

        } else {
            BugLog.alertAll("Pls. Select a Staff", "Invalid Selection", "Select Staff", Alert.AlertType.INFORMATION);
        }
    }

    void edit() {
        StaffManager sm = new StaffManager();

        if (tableView.getSelectionModel().getSelectedItem() != null) {
            isEditActive = true;
            NewStaffScreen1Controller.st.setNewStaff(tableView.getSelectionModel().getSelectedItem());

            if (sm.findStaffImage(link.em, tableView.getSelectionModel().getSelectedItem().getId()) != null) {
                NewStaffScreen1Controller.st.setNewStaffImage(sm.findStaffImage(link.em, tableView.getSelectionModel().getSelectedItem().getId()));
            }

            int index = tableView.getSelectionModel().getFocusedIndex();

            boolean isEditStaff = newStaff("Edit Doctor");

            if (isEditStaff) {
                data.set(index, NewStaffScreen1Controller.st.getNewStaff());
                tableView.getSelectionModel().select(index);

                SaveEditedFiles.Staff(link.em, NewStaffScreen1Controller.st.getNewStaff(), NewStaffScreen1Controller.st.getNewStaffImage());
                link.em.clear();
            }

        } else {
            BugLog.alertAll("Pls. Select a Staff", "Invalid Selection", "Select Staff", Alert.AlertType.INFORMATION);
        }

        isEditActive = false;
    }

    void delete() {

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {

                Optional<ButtonType> result = Confirmations.deleteConfirmation("Are you sure you want to delete this person.", "Please confirm person removal").showAndWait();

                if (result.get() == ButtonType.OK) {

                    DeleteStaff ds = new DeleteStaff(link.em, tableView.getSelectionModel().getSelectedItem());

                    tableView.getItems().remove(selectedIndex);

                } else {

                }

            }
        } else {
            BugLog.alertAll("Pls. Select a Person", "Invalid Selection", "Select Person", Alert.AlertType.INFORMATION);
        }
    }

}
