/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.entity.Department;
import healthmanagement.entity.Heap_Dep;
import healthmanagement.entity.Heap_Staff;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.utility.IDs_generator;
import healthmanagement.utility.InputCheck;
import healthmanagement.utility.Paths;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.RedundancyCheck;
import healthmanagement.utility.Table_List_Population;
import healthmanagement.utility.Templates;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class AddDepartmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField newDepartField;

    @FXML
    private Text fillSpaceText;
    /*
    -------------------------------------------------------------------------------------------------------------------------------
     */
    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        link.em();

        newDepartField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            fillSpaceText.setOpacity(0);
        });
    }

    @FXML
    void enterPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            initialize();
        }
    }

    @FXML
    void okButton_Init(ActionEvent event) {
        initialize();
    }

    @FXML
    void okButton_MouseEntered(MouseEvent event) {
        okButton.setOpacity(1.0);
    }

    @FXML
    void okButton_MouseExited(MouseEvent event) {
        okButton.setOpacity(0.7);
        okButton.setEffect(new Glow(0.0));
    }

    @FXML
    void cancelButton_Init(ActionEvent event) {
        cancelButton.setEffect(new Glow(1.0));
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void cancelButton_MouseEntered(MouseEvent event) {
        cancelButton.setOpacity(1.0);
    }

    @FXML
    void cancelButton_MouseExited(MouseEvent event) {
        cancelButton.setOpacity(0.7);
        cancelButton.setEffect(new Glow(0.0));
    }

    private void initialize() {
        okButton.setEffect(new Glow(1.0));
        Heap_Dep hd = new Heap_Dep();

        if (InputCheck.emptyField(newDepartField.getText()) == 1) {
            Department de = new Department();
            de.setId(IDs_generator.Department_Id());
            de.setName(newDepartField.getText());
            int size = FXMLDocumentController.departmentList.size();
            if (!RedundancyCheck.isDapartmentNameDuplicated(FXMLDocumentController.departmentList, de.getName(), size)) {
                Table_List_Population.addDepartment_to_List(FXMLDocumentController.departmentList, de);

                //persist the department to database       
                PersistActions pa = new PersistActions();
                pa.NewDepartment(link.em, de);
                System.err.println(de);
                hd.setDepId(de);
                pa.HeapSend_Department(link.em, hd);
                Stage primaryStage = (Stage) okButton.getScene().getWindow();
                primaryStage.close();
            } else {
                fillSpaceText.setText("Name Already Exist");
                fillSpaceText.setOpacity(1.0);
            }
        } else {
            fillSpaceText.setText("Please Fill Blank Space");
            fillSpaceText.setOpacity(1.0);
        }
    }

}
