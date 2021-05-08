/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import healthmanagement.entity.Department;
import healthmanagement.entity.Patient;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.StaffManager;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.Confirmations;
import healthmanagement.utility.DeleteDepartment;
import healthmanagement.utility.GetProgramIDs;
import healthmanagement.utility.HeapProbe;
import healthmanagement.utility.IDs_generator;
import healthmanagement.utility.ImageBytesConversion;
import healthmanagement.utility.OpenFolder;
import healthmanagement.utility.Paths;
import healthmanagement.utility.PatientMessage;
import healthmanagement.utility.StaffCall;
import healthmanagement.utility.Table_List_Population;
import healthmanagement.utility.Tabs_And_Content;
import healthmanagement.utility.Templates;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Elorm
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TabPane tabpane;

    @FXML
    private AnchorPane anchorPane_tabs;

    @FXML
    private AnchorPane accountPane;

    @FXML
    private ImageView accountImage;

    @FXML
    private Pane tabpaneContainer;

    @FXML
    private Label label;

    @FXML
    private Button addDepart_Button;

    @FXML
    private Button searchPButton;

    @FXML
    private Button searchDButton;

    @FXML
    private Text userText;

    @FXML
    private ListView<String> depatmentListview;

    @FXML
    private TableView<PatientMessage> folderRecievedTableView;

    @FXML
    private TableColumn<PatientMessage, String> receivedColumn;

    public static ObservableList<Department> departmentList = FXCollections.observableArrayList();

    public static ObservableList<PatientMessage> folderPatientList = FXCollections.observableArrayList();
    /*
    -------------------------------------------------------------------------------------------------------------------------------
     */
    Tab tab;

    double x = 100;

    public static GetProgramIDs gpi = new GetProgramIDs();

    private StaffManager sm = new StaffManager();

    public static TabPane tabpane_open;

    EntityLink link = new EntityLink();

    double mouseEnterValue = 0.55;

    double mouseExitValue = 1.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) throws NullPointerException {
        // TODO

        link.em();

        tabpane_open = tabpane;

        /* 
        
       In order to run straight into the main console without the Login process. First comment
       
        1. the statement below
        2. the heaprobe statement below this Method/function
        3. the Login check in the LoginController Class at the loginButtonInit method.
       
         */
        userText.setText(LoginController.sc.getStaff().getUserName());

        try {
            accountImage.setImage(ImageBytesConversion.isFromBytestoImage_Staff(sm.findStaffImage(link.em, LoginController.sc.getStaff().getId())));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Account Image not able to load");
        }

        Table_List_Population.loadDepartments(link.em, depatmentListview);

        Table_List_Population.folderReceived(receivedColumn);

        folderRecievedTableView.setItems(folderPatientList);
        //tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        departmentList.addListener((ListChangeListener.Change<? extends Department> c) -> {
            depatmentListview.getItems().add(c.getList().get(0).getName());
        });

        tabpane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                if (oldValue != null) {
                    oldValue.setStyle("");
                }
                gpi.setTabId(tabpane.getSelectionModel().getSelectedItem().getId());

                newValue.setStyle("-fx-background-color: #ea5809");
            }
        });

        Mainmanagement.stage.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            tabpane.setPrefWidth(Mainmanagement.stage.getWidth() - 187);
            accountPane.setLayoutX(Mainmanagement.stage.getWidth() - 350);

        });
        Mainmanagement.stage.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {

            tabpane.setPrefHeight(Mainmanagement.stage.getHeight() - 160);

        });

        HeapProbe probe = new HeapProbe(link.em, Mainmanagement.stage, LoginController.sc.getStaff());
    }

    /*
    -------------------------------------------------------------------------------------------------------------------------------
    *Department Function 
     */
    int departmentArray = 0;
    //   Tabs_And_Content[] tac = new Tabs_And_Content[50];

    public static String departmentName = "";

    @FXML
    void addDepart_Init(ActionEvent event) {

        Stage primaryStageDepartment = new Stage();
        addDepart_Button.setEffect(new Glow(1.0));

        primaryStageDepartment.setTitle("Add A New Department");
        primaryStageDepartment.initOwner(Mainmanagement.stage);
        primaryStageDepartment.setResizable(false);
        primaryStageDepartment.initModality(Modality.WINDOW_MODAL);

        Templates tem = new Templates(primaryStageDepartment, Paths.addNewDepartments, "addNewDepartments");

    }

    @FXML
    void deleteDepartment_Item(ActionEvent event) {

        if (depatmentListview.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = depatmentListview.getSelectionModel().getSelectedIndex();

            Optional<ButtonType> result = Confirmations.deleteConfirmation("Are you sure you want to delete the Selected Department.", "Please confirm removal of Department Files").showAndWait();

            if (result.get() == ButtonType.OK) {

                //DeletePatient dp = new DeletePatient(link.em, tableView.getSelectionModel().getSelectedItem());
                int size = departmentList.size();
                for (int i = 0; i < size; i++) {
                    if (depatmentListview.getSelectionModel().getSelectedItem().equals(departmentList.get(i).getName())) {

                        DeleteDepartment ddp = new DeleteDepartment(link.em, departmentList.get(i));

                        break;
                    }
                }

                depatmentListview.getItems().remove(selectedIndex);
            }

        }
    }

    /*
    -------------------------------------------------------------------------------------------------------------------------------
         Message Received table
     */
    @FXML
    void selectFolderMessage(MouseEvent event) {

        OpenFolder of = new OpenFolder();

        if (folderRecievedTableView.getSelectionModel().getSelectedItem() != null) {

            if (event.getClickCount() == 2) {

                of.openFolder(link.em, folderRecievedTableView.getSelectionModel().getSelectedItem().getId());
            }
        }
    }

    @FXML
    void selectDepartment_Init(MouseEvent event) {

        if (event.getClickCount() == 2) {
            // System.out.println(depatmentListview.getSelectionModel().getSelectedItem());

            int size = departmentList.size();
            for (int i = 0; i < size; i++) {
                if (depatmentListview.getSelectionModel().getSelectedItem().equals(departmentList.get(i).getName())) {

                    departmentName = departmentList.get(i).getName();
                    Tabs_And_Content tac = new Tabs_And_Content(tabpane, tab, true, depatmentListview.getSelectionModel().getSelectedItem(), departmentList.get(i).getId(), Paths.departmentlist, "");

                    departmentArray++;
                    break;
                }
            }
        }
    }

    @FXML
    void addDepart_MouseEnter(MouseEvent event) {
        addDepart_Button.setOpacity(mouseEnterValue);

    }

    @FXML
    void addDepart_MouseExit(MouseEvent event) {
        addDepart_Button.setEffect(new Glow(0.0));
        addDepart_Button.setOpacity(mouseExitValue - 0.21);

    }

    /*
    -------------------------------------------------------------------------------------------------------------------------------
      * Search Doctor Functions
     */
    @FXML
    void searchDButton_init(ActionEvent event) throws IOException {
        searchDButton.setEffect(new Glow(1.0));
        Tabs_And_Content tac = new Tabs_And_Content(tabpane, tab, true, "Search Doctor", IDs_generator.searchDoctorTab_Id(), Paths.searchDoctor, "");

    }

    @FXML
    void searchDButton_MouseEnter(MouseEvent event) {
        searchDButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void searchDButton_MouseExit(MouseEvent event) {
        searchDButton.setEffect(new Glow(0.0));
        searchDButton.setOpacity(mouseExitValue - 0.09);
    }

    /*
    -------------------------------------------------------------------------------------------------------------------------------
      * Search patient Functions
     */
    @FXML
    void searchPButton_init(ActionEvent event) throws IOException {
        searchPButton.setEffect(new Glow(1.0));

        Tabs_And_Content tac = new Tabs_And_Content(tabpane, tab, true, "Search Patient", IDs_generator.searchPatientTab_Id(), Paths.searchPatient, "");

    }

    @FXML
    void searchPButton_MouseEnter(MouseEvent event) {
        searchPButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void searchPButton_MouseExit(MouseEvent event) {
        searchPButton.setEffect(new Glow(0.0));
        searchPButton.setOpacity(mouseExitValue - 0.09);
    }

}
