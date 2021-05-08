package healthmanagement.controllers;

import healthmanagement.entity.Staff;
import healthmanagement.entity.StaffImage;
import healthmanagement.logs.BugLog;
import healthmanagement.utility.ChooseImage;
import healthmanagement.utility.DateOfBirth;
import healthmanagement.utility.FieldCheck;
import healthmanagement.utility.FieldCheckSensitivity;
import healthmanagement.utility.IDs_generator;
import healthmanagement.utility.ImageBytesConversion;
import healthmanagement.utility.NewStaff;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import healthmanagement.utility.Paths;
import healthmanagement.utility.NewStaffControlledScreen;
import healthmanagement.utility.NewStaffImage;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.Table_List_Population;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class NewStaffScreen1Controller implements Initializable, NewStaffControlledScreen {

    NewStaffScreensController myController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField otherNameField;

    @FXML
    private TextField staffIdField;

    @FXML
    private TextField phoneNoField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField houseNoField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ComboBox staffTitleBox;

    @FXML
    private ComboBox specializationBox;

    @FXML
    private ComboBox dayOfBirthBox;

    @FXML
    private ComboBox monthOfBirthBox;

    @FXML
    private ComboBox yearOfBirthBox;

    @FXML
    private Label mainCheck;

    @FXML
    private Label dobCheck;

    @FXML
    private Label staffIdCheck;

    @FXML
    private Label staffTitleCheck;

    @FXML
    private Label phoneNoCheck;

    @FXML
    private Label locationCheck;

    @FXML
    private Label passCheck1;

    @FXML
    private Label passCheck2;

    @FXML
    private Label passCheck3;

    @FXML
    private Label usernameCheck;

    @FXML
    private Button cancelButton;

    @FXML
    private Button nxtButton;

    @FXML
    private Text specializationText;

    @FXML
    private Text titleText;

    @FXML
    private ImageView imageView;

    private final double mouseEnterValue = 0.60, mouseExitValue = 1.0;

    public static NewStaff st = new NewStaff();

    public static StaffImage stfImage = new StaffImage();

    public static boolean doctor_check;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        FieldCheckSensitivity.nameFields(firstNameField, lastNameField, otherNameField, mainCheck);
        FieldCheckSensitivity.dateOfBirth(dayOfBirthBox, monthOfBirthBox, yearOfBirthBox, dobCheck, mainCheck);
        FieldCheckSensitivity.id(staffIdField, staffIdCheck, mainCheck);
        FieldCheckSensitivity.title(staffTitleBox, staffTitleCheck, mainCheck);
        FieldCheckSensitivity.phone(phoneNoField, phoneNoCheck, mainCheck);
        FieldCheckSensitivity.location(locationField, locationCheck, mainCheck);
        FieldCheckSensitivity.passwords(passwordField, confirmPasswordField, passCheck1, passCheck2, passCheck3, mainCheck);
        FieldCheckSensitivity.usernameField(usernameField, usernameCheck);
        if (doctor_check == true) {
            staffTitleBox.setValue("Doctor");
            staffTitleBox.setDisable(true);
            titleText.setOpacity(0.33);
        } else {
            specializationBox.setDisable(true);
            specializationText.setOpacity(0.33);
        }

        if (SearchDoctorController.isEditActive || DeparmentsListController.isEditActive) {
            try {
                loadStaffForEdit(st);

            } catch (IOException ex) {
                Logger.getLogger(NewStaffScreen1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            st.setNewStaff(new Staff());
            stfImage.setImage(null);
        }
    }

    @Override
    public void setScreenParent(NewStaffScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    void nxtButton_Init(ActionEvent event) throws IOException {
        Staff staff = new Staff();

        if (FieldCheck.nameCheck(firstNameField, lastNameField, otherNameField)
                == true || FieldCheck.dobCheck(dayOfBirthBox, dayOfBirthBox, yearOfBirthBox, dobCheck) == true
                || FieldCheck.staffId(staffIdField, staffIdCheck) == true || FieldCheck.staffTitle(staffTitleBox, staffTitleCheck) == true
                || FieldCheck.location(locationField, locationCheck) == true || FieldCheck.phoneNumber(phoneNoField, phoneNoCheck) == 0
                || FieldCheck.password(passwordField, confirmPasswordField, passCheck1, passCheck2, passCheck3) == true || FieldCheck.yearBoxCheck(yearOfBirthBox, dobCheck) == 0
                || FieldCheck.usernameField(usernameField, usernameCheck)) {
            mainCheck.setOpacity(1.0);

        } else {
           // System.out.println(SearchDoctorController.isEditActive + "  " + DeparmentsListController.isEditActive);
            if (SearchDoctorController.isEditActive || DeparmentsListController.isEditActive) {
                String id = st.getNewStaff().getId();
                staff.setId(id);
                stfImage.setId(id);
            } else {
                String id = IDs_generator.Staff_Id();
                staff.setId(id);
                stfImage.setId(id);
            }
            staff.setFirstName(firstNameField.getText());
            staff.setLastName(lastNameField.getText());
            staff.setOtherName(otherNameField.getText());
            staff.setStaffId(staffIdField.getText());
            staff.setTitle(staffTitleBox.getValue().toString());
            staff.setPhoneNo(phoneNoField.getText());
            staff.setEmail(emailField.getText());
            staff.setHouseNo(houseNoField.getText());
            staff.setCity(locationField.getText());
            staff.setDob(DateOfBirth.date(dayOfBirthBox.getValue().toString(), monthOfBirthBox, yearOfBirthBox.getValue().toString()));
            staff.setStaffPassword(passwordField.getText());
            staff.setUserName(usernameField.getText());
            if (specializationBox.getValue() != null) {
                staff.setSpecialization(specializationBox.getValue().toString());
            }
            ImageBytesConversion.getImagebitsForStoreEdit_staff(stfImage, imageView.getImage());
            myController.setScreen(Paths.staffScreen2ID);
            st.setNewStaff(staff);
            System.out.println(stfImage.getId() + "   " + stfImage.getImage());
            st.setNewStaffImage(stfImage);
        }
    }

    @FXML
    void imageClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            ChooseImage.getImageFile(imageView);
        }
    }

    @FXML
    void dayBoxClicked(MouseEvent event) {

        dayOfBirthBox.setItems(DateOfBirth.dayList());

    }

    @FXML
    void monthBoxClicked(MouseEvent event) {

        monthOfBirthBox.setItems(DateOfBirth.monthList);
    }

    @FXML
    void yearBoxClicked(MouseEvent event) {
        yearOfBirthBox.setItems(DateOfBirth.yearList());
    }

    @FXML
    void staffTitleBoxClicked(MouseEvent event) {
        staffTitleBox.setItems(Table_List_Population.staffTitles);
    }

    @FXML
    void cancelButton_Init(ActionEvent event) {

        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        NewStaffScreen2Controller.saveClicked = false;
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
    void nxtButton_Entered(MouseEvent event) {
        nxtButton.setEffect(new Glow(0.7));
    }

    @FXML
    void nxtButton_Exited(MouseEvent event) {
        nxtButton.setEffect(new Glow(0.0));
    }

    @FXML
    void firstNameField_Entered(MouseEvent event) {
        firstNameField.setOpacity(mouseEnterValue);
    }

    @FXML
    void firstNameField_Exited(MouseEvent event) {
        firstNameField.setOpacity(mouseExitValue);
    }

    @FXML
    void lastNameField_Entered(MouseEvent event) {
        lastNameField.setOpacity(mouseEnterValue);
    }

    @FXML
    void lastNameField_Exited(MouseEvent event) {
        lastNameField.setOpacity(mouseExitValue);
    }

    @FXML
    void otherNameField_Entered(MouseEvent event) {
        otherNameField.setOpacity(mouseEnterValue);

    }

    @FXML
    void otherNameField_Exited(MouseEvent event) {
        otherNameField.setOpacity(mouseExitValue);
    }

    @FXML
    void staffIdField_Entered(MouseEvent event) {
        staffIdField.setOpacity(mouseEnterValue);

    }

    @FXML
    void staffIdField_Exited(MouseEvent event) {
        staffIdField.setOpacity(mouseExitValue);
    }

    @FXML
    void phoneNoField_Entered(MouseEvent event) {
        phoneNoField.setOpacity(mouseEnterValue);

    }

    @FXML
    void phoneNoField_Exited(MouseEvent event) {
        phoneNoField.setOpacity(mouseExitValue);
    }

    @FXML
    void emailField_Entered(MouseEvent event) {
        emailField.setOpacity(mouseEnterValue);

    }

    @FXML
    void emailField_Exited(MouseEvent event) {
        emailField.setOpacity(mouseExitValue);
    }

    @FXML
    void houseNoField_Entered(MouseEvent event) {
        houseNoField.setOpacity(mouseEnterValue);

    }

    @FXML
    void houseNoField_Exited(MouseEvent event) {
        houseNoField.setOpacity(mouseExitValue);
    }

    @FXML
    void locationField_Entered(MouseEvent event) {
        locationField.setOpacity(mouseEnterValue);

    }

    @FXML
    void locationField_Exited(MouseEvent event) {
        locationField.setOpacity(mouseExitValue);
    }

    @FXML
    void passwordField_Entered(MouseEvent event) {
        passwordField.setOpacity(mouseEnterValue);

    }

    @FXML
    void passwordField_Exited(MouseEvent event) {
        passwordField.setOpacity(mouseExitValue);
    }

    @FXML
    void confirmPasswordField_Entered(MouseEvent event) {
        confirmPasswordField.setOpacity(mouseEnterValue);

    }

    @FXML
    void confirmPasswordField_Exited(MouseEvent event) {
        confirmPasswordField.setOpacity(mouseExitValue);
    }

    @FXML
    void staffTitleBox_Entered(MouseEvent event) {
        staffTitleBox.setOpacity(mouseEnterValue);

    }

    @FXML
    void staffTitleBox_Exited(MouseEvent event) {
        staffTitleBox.setOpacity(mouseExitValue);
    }

    @FXML
    void dayOfBirthBox_Entered(MouseEvent event) {
        dayOfBirthBox.setOpacity(mouseEnterValue);

    }

    @FXML
    void dayOfBirthBox_Exited(MouseEvent event) {
        dayOfBirthBox.setOpacity(mouseExitValue);
    }

    @FXML
    void monthOfBirthBox_Entered(MouseEvent event) {
        monthOfBirthBox.setOpacity(mouseEnterValue);

    }

    @FXML
    void monthOfBirthBox_Exited(MouseEvent event) {
        monthOfBirthBox.setOpacity(mouseExitValue);
    }

    @FXML
    void yearOfBirthBox_Entered(MouseEvent event) {
        yearOfBirthBox.setOpacity(mouseEnterValue);

    }

    @FXML
    void yearOfBirthBox_Exited(MouseEvent event) {
        yearOfBirthBox.setOpacity(mouseExitValue);
    }

    private void loadStaffForEdit(NewStaff editStaff) throws IOException {

        Staff staff = editStaff.getNewStaff();

        if (editStaff.getNewStaffImage() != null) {
            imageView.setImage(ImageBytesConversion.isFromBytestoImage_Staff(editStaff.getNewStaffImage()));
        }
        firstNameField.setText(staff.getFirstName());
        lastNameField.setText(staff.getLastName());
        otherNameField.setText(staff.getOtherName());
        dayOfBirthBox.setValue(staff.getDob().getDate());
        monthOfBirthBox.setValue(DateOfBirth.getMonth(staff.getDob().getMonth()));
        yearOfBirthBox.setValue(staff.getDob().getYear() + 1900);
        staffIdField.setText(staff.getStaffId());
        staffTitleBox.setValue(staff.getTitle());
        specializationBox.setValue(staff.getSpecialization());
        locationField.setText(staff.getCity());
        houseNoField.setText(staff.getHouseNo());
        phoneNoField.setText(staff.getPhoneNo());
        emailField.setText(staff.getEmail());
        usernameField.setText(staff.getUserName());
        passwordField.setText(staff.getStaffPassword());
        confirmPasswordField.setText(staff.getStaffPassword());

    }

}
