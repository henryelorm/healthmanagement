package healthmanagement.controllers;

import healthmanagement.entity.Heap_Staff;
import healthmanagement.entity.Staff;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.utility.FieldCheck;
import healthmanagement.utility.FieldCheckSensitivity;
import healthmanagement.utility.NewStaff;
import healthmanagement.utility.Paths;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import healthmanagement.utility.NewStaffControlledScreen;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.PriotyValue;
import healthmanagement.utility.StringSplit;
import java.io.IOException;
import java.util.Arrays;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class NewStaffScreen2Controller implements Initializable, NewStaffControlledScreen {

    NewStaffScreensController myController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private Button prevButton;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label priotyCheck;

    @FXML
    private CheckBox adminBox;

    @FXML
    private CheckBox labsBox;

    @FXML
    private CheckBox frontdeskBox;

    @FXML
    private CheckBox pharmacyBox;

    /*
    --------------------------------------------------------------------------------------------------
     */
    CheckBox[] priorityCheckArray = new CheckBox[4];

    public static boolean saveClicked = false;

    private boolean editSaveClicked = false;

    public boolean isEditSavedClicked() {
        return editSaveClicked;
    }

    public static boolean isSaveClicked() {
        return saveClicked;
    }

    /*
    -----------------------------------------------------------------------------------------------------
     */
    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        saveClicked = false;
        link.em();
        FieldCheckSensitivity.Priority(adminBox, labsBox, frontdeskBox, pharmacyBox, priotyCheck);
        priorityCheckArray[0] = adminBox;
        priorityCheckArray[1] = labsBox;
        priorityCheckArray[2] = frontdeskBox;
        priorityCheckArray[3] = pharmacyBox;

        if (SearchDoctorController.isEditActive || DeparmentsListController.isEditActive) {
            loadStaffForEdit(NewStaffScreen1Controller.st.getNewStaff());
        }
    }

    @Override
    public void setScreenParent(NewStaffScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    void prevButton_Init(ActionEvent event) {
        myController.setScreen(Paths.staffScreen1ID);
    }

    @FXML
    void okButton_Init(ActionEvent event) throws IOException {
        NewStaffScreen1Controller nssc = new NewStaffScreen1Controller();

        if (FieldCheck.priorityBox(adminBox, labsBox, frontdeskBox, pharmacyBox) == true) {
            PriotyValue prv = new PriotyValue();
            NewStaffScreen1Controller.st.getNewStaff().setPriority(prv.setValue(priorityCheckArray));
            NewStaffScreen1Controller.st.getNewStaff().setDepartment(FXMLDocumentController.gpi.getTabId());

            Stage primaryStage = (Stage) okButton.getScene().getWindow();
            primaryStage.close();

            saveClicked = true;

        } else {

            saveClicked = false;
            priotyCheck.setOpacity(1.0);
        }

    }

    @FXML
    void cancelButton_Init(ActionEvent event) {
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        saveClicked = false;
        primaryStage.close();
    }

    @FXML
    void cancelButton_Entered(MouseEvent event) {
        cancelButton.setEffect(new Glow(0.7));
    }

    @FXML
    void cancelButton_Exited(MouseEvent event) {
        cancelButton.setEffect(new Glow(0.0));
    }

    @FXML
    void okButton_Entered(MouseEvent event) {
        okButton.setEffect(new Glow(0.7));
    }

    @FXML
    void okButton_Exited(MouseEvent event) {
        okButton.setEffect(new Glow(0.0));
    }

    @FXML
    void prevButton_Entered(MouseEvent event) {
        prevButton.setEffect(new Glow(0.7));
    }

    @FXML
    void prevButton_Exited(MouseEvent event) {
        prevButton.setEffect(new Glow(0.0));
    }

    private void loadStaffForEdit(Staff staff) {

        if (!staff.getPriority().isEmpty()) {

            int size = StringSplit.splitCommaString(staff.getPriority()).length;
            for (int i = 0; i < size; i++) {

                switch (StringSplit.splitCommaString(staff.getPriority())[i]) {

                    case "Admin":
                        adminBox.setSelected(true);
                        break;

                    case "Labs":
                        labsBox.setSelected(true);
                        break;
                    case "FrontDesk":
                        frontdeskBox.setSelected(true);
                        break;

                    case "Pharmacy":
                        pharmacyBox.setSelected(true);
                        break;

                }
            }

        }

    }

}
