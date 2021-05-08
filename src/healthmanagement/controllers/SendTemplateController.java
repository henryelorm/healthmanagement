/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import static healthmanagement.controllers.NewStaffScreen2Controller.saveClicked;
import healthmanagement.entity.Department;
import healthmanagement.entity.Patient;
import healthmanagement.entity.Staff;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.StaffManager;
import healthmanagement.httpRequest.Post;
import healthmanagement.utility.Paths;
import healthmanagement.utility.RedundancyCheck;
import healthmanagement.utility.StaffCall;
import healthmanagement.utility.Table_List_Population;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class SendTemplateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Patient patient = SearchPatientController.np.getNewPatient();

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private ComboBox<String> nameBox;

    @FXML
    public TableView<Staff> tableView_Doctor;

    @FXML
    public TableView<Department> tableView_Department;

    @FXML
    public TableColumn<Staff, String> firstName_Doctor;

    @FXML
    public TableColumn<Staff, String> lastName_Doctor;

    @FXML
    public TableColumn<Staff, String> otherName_Doctor;

    @FXML
    public TableColumn<Department, String> name_department;

    private final double mouseEnterValue = 0.60, mouseExitValue = 1.0;

    private ObservableList<Staff> doctorList = FXCollections.observableArrayList();

    EntityLink link = new EntityLink();

    Post post = new Post();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        link.em();
        StaffManager stm = new StaffManager();
        RedundancyCheck redcheck = new RedundancyCheck();

        System.out.println(LoginController.sc.getStaff());

        nameBox.setPromptText("Select Department");

        nameBox.setValue(patient.getLastName() + " " + patient.getFirstName() + " " + patient.getOtherName());

        Table_List_Population.lastName_sendDoctor(lastName_Doctor);
        Table_List_Population.firstName_sendDoctor(firstName_Doctor);
        Table_List_Population.otherName_sendDoctor(otherName_Doctor);
        Table_List_Population.department_send(name_department);

        doctorList.setAll(redcheck.removeLoginStaff(stm.findAllDoctors(link.em), LoginController.sc.getStaff()));

        tableView_Doctor.setItems(doctorList);
        tableView_Department.setItems(FXMLDocumentController.departmentList);

    }

    @FXML
    void okButton_Init(ActionEvent event) throws IOException {
        ImageView icon = new ImageView();

        if (!tableView_Department.getSelectionModel().isEmpty() || !tableView_Doctor.getSelectionModel().isEmpty()) {

            post.send(link.em, patient, tableView_Doctor.getSelectionModel().getSelectedItem(), tableView_Department.getSelectionModel().getSelectedItem());
            Notifications notify = Notifications.create()
                    .text("Folder Sent")
                    .title("Folder Transfer SuccessFul")
                    .graphic(icon)
                    .owner(Mainmanagement.stage)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

            notify.showInformation();
        }

        Stage primaryStage = (Stage) okButton.getScene().getWindow();
        primaryStage.close();

    }

    @FXML
    void unselectDoctor_Init(ActionEvent event) {

        if (tableView_Doctor.getSelectionModel().getSelectedItem() != null) {
            tableView_Doctor.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void unselectDepartment_Init(ActionEvent event) {
        if (tableView_Department.getSelectionModel().getSelectedItem() != null) {
            tableView_Department.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void cancelButton_Init(ActionEvent event) {
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();

        primaryStage.close();
    }

    @FXML
    void cancelButton_Entered(MouseEvent event) {
        cancelButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void cancelButton_Exited(MouseEvent event) {
        cancelButton.setOpacity(mouseExitValue);
    }

    @FXML
    void okButton_Entered(MouseEvent event) {
        okButton.setOpacity(mouseEnterValue);
    }

    @FXML
    void okButton_Exited(MouseEvent event) {
        okButton.setOpacity(mouseExitValue);
    }

}
