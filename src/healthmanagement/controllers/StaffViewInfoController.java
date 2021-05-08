/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.entity.Staff;
import healthmanagement.entity.StaffImage;
import healthmanagement.entityManager.DepartmentManager;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.StaffManager;
import healthmanagement.utility.ImageBytesConversion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class StaffViewInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView staffImageView;

    @FXML
    private Text staffIdText;

    @FXML
    private Text titleText;

    @FXML
    private Text nameText;

    @FXML
    private Text phoneText;

    @FXML
    private Text departmentText;

    @FXML
    private Text specializationText;

    @FXML
    private Text usernameText;

    @FXML
    private Text emailText;

    @FXML
    private Text dobText;

    @FXML
    private Text houseNoText;

    @FXML
    private Text cityText;

    @FXML
    private Text priorityText;

    @FXML
    private Button closeButton;

    EntityLink link = new EntityLink();

    public static String theKindOfStaff = "";

    Staff staff = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        link.em();
        StaffManager sm = new StaffManager();
        DepartmentManager dm = new DepartmentManager();

        switch (theKindOfStaff) {

            case "department":

                staff = DeparmentsListController.getSelectedStaff.getNewStaff();

                break;

            case "doctor":

                staff = SearchDoctorController.getSelectedStaff.getNewStaff();

                break;
            default:
                JOptionPane.showMessageDialog(null, "Staff Selection Error");
                break;
        }

        try {
            StaffImage staffImage = sm.findStaffImage(link.em, staff.getId());
            staffImageView.setImage(ImageBytesConversion.isFromBytestoImage_Staff(staffImage));
        } catch (IOException ex) {
            //  Logger.getLogger(StaffViewInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        staffIdText.setText(staff.getStaffId());
        titleText.setText(staff.getTitle());
        nameText.setText(staff.getLastName() + " " + staff.getFirstName() + " " + staff.getOtherName());
        phoneText.setText(staff.getPhoneNo());

        if (staff.getDepartment().equals("searchdoctor001")) {
            departmentText.setText("Doctor");
        } else {
            departmentText.setText(dm.findDepartment(link.em, staff.getDepartment()).getName());
        }
        specializationText.setText(staff.getSpecialization());
        usernameText.setText(staff.getUserName());
        emailText.setText(staff.getEmail());
        dobText.setText(staff.getDob().toString());
        houseNoText.setText(staff.getHouseNo());
        cityText.setText(staff.getCity());
        priorityText.setText(staff.getPriority());

    }

    @FXML
    void closeButton_Init(ActionEvent event) {

        Stage primaryStage = (Stage) closeButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void closeButtonEntered(MouseEvent event) {
        closeButton.setOpacity(0.5);
    }

    @FXML
    void closeButtonExited(MouseEvent event) {
        closeButton.setOpacity(1.0);
    }

}
