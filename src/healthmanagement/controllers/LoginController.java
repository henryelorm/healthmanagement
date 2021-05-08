/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.Mainmanagement;
import healthmanagement.entity.Staff;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.utility.FieldCheckSensitivity;
import healthmanagement.utility.LoginCheckUser;
import healthmanagement.utility.StaffCall;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label fieldCheckLabel;

    public static StaffCall sc = new StaffCall();

    Stage stage = new Stage();

    EntityLink link = new EntityLink();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        link.em();

        FieldCheckSensitivity.userpassFields(usernameField, passwordField, fieldCheckLabel);
    }

    @FXML
    void loginButtonInit(ActionEvent event) throws Exception {

        Stage primaryStage = (Stage) loginButton.getScene().getWindow();
        Mainmanagement m = new Mainmanagement();

        if (LoginCheckUser.isLoginAvailable(link.em, usernameField.getText(), passwordField.getText()) != null) {

            Staff staff = LoginCheckUser.isLoginAvailable(link.em, usernameField.getText(), passwordField.getText());

            sc.setStaff(staff);
            primaryStage.close();

            m.start(stage);

        } else {
            fieldCheckLabel.setOpacity(1.0);
        }

    }

}
