/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.logs.BugLog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author Elorm
 */
public class FieldCheck {

    public static boolean nameCheck(TextField firstName, TextField lastName, TextField otherName) {

        boolean namech = (lastName.getText().isEmpty() || firstName.getText().isEmpty()
                || otherName.getText().isEmpty());

        return namech;

    }

    public static int phoneNumber(TextField field, Label fl) {

        RestrictTextField rtfNumber = new RestrictTextField();
        rtfNumber.setRestrict("[0-9]");
        rtfNumber.setMaxLength(10);
        rtfNumber.setText(field.getText());
        if (field.getText().equals("")) {
            return 1;
        } else if (rtfNumber.getText().equals("") || rtfNumber.getText().length() != 10) {
            fl.setOpacity(1);

            BugLog.phoneNumberCorrect("Please input correct phone number: eg. 024******* \n with 10 numbers for each field",
                    "Incorrect Number Format", null);

            return 0;
        } else {
            return 1;
        }
    }

    public static boolean location(TextField location, Label locate) {

        boolean locatee = location.getText().isEmpty();
        if (locatee) {
            locate.setOpacity(1.0);
        }
        return locatee;
    }

    public static boolean nationality(TextField nationality, Label nation) {

        boolean natione = nationality.getText().isEmpty();
        if (natione) {
            nation.setOpacity(1.0);
        }
        return natione;
    }

    public static boolean houseNo(TextField houseno, Label house) {

        boolean housee = houseno.getText().isEmpty();
        if (housee) {
            house.setOpacity(1.0);
        }
        return housee;
    }

    //used: (password: staffSreen1) and (id check: patientScreen2)
    public static boolean password(TextField password, TextField confirmPass, Label pass1, Label pass2, Label pass3) {
        boolean passe = ((password.getText().isEmpty()) || (confirmPass.getText().isEmpty())) || (password.getText() == null ? confirmPass.getText() != null : !password.getText().equals(confirmPass.getText()));
        if (passe) {
            pass1.setOpacity(1.0);
            pass2.setOpacity(1.0);
            pass3.setOpacity(1.0);
        }

        return passe;
    }

    public static boolean dobCheck(ComboBox dayBox, ComboBox monthBox, ComboBox yearBox, Label dob) {

        boolean dobe = dayBox.getValue() == null || monthBox.getValue() == null || yearBox.getValue() == null;
        if (dobe) {
            dob.setOpacity(1.0);
        }

        return dobe;
    }

    public static int yearBoxCheck(ComboBox yr, Label dob) {

        RestrictTextField rtfYear = new RestrictTextField();
        rtfYear.setRestrict("[0-9]");
        rtfYear.setMaxLength(10);

        rtfYear.setText(yr.getValue().toString());

        if (rtfYear.getText().equals("")) {

            dob.setOpacity(1.0);
            return 0;

        } else {
            return 1;
        }

    }

    public static boolean genderBox(ComboBox box, Label genderCheck) {

        boolean gen = (box.getValue() == null);
        if (gen) {
            genderCheck.setOpacity(1.0);
        }

        return gen;

    }

    public static boolean maritalStatusBox(ComboBox box, Label statusCheck) {

        boolean status = (box.getValue() == null);
        if (status) {
            statusCheck.setOpacity(1.0);
        }

        return status;

    }

    public static void statusFieldActivation(ComboBox status, Text name, Text phone, TextField nameF, TextField phoneF) {
        status.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (((newValue != "Married") == true) && ((newValue != "Living common law") == true)) {
                    phone.setStrikethrough(true);
                    phone.setOpacity(0.4);
                    name.setStrikethrough(true);
                    name.setOpacity(0.4);
                    nameF.setDisable(true);
                    phoneF.setDisable(true);
                } else {
                    phone.setStrikethrough(false);
                    phone.setOpacity(0.8);
                    name.setStrikethrough(false);
                    name.setOpacity(0.8);
                    nameF.setDisable(false);
                    phoneF.setDisable(false);
                }
            }
        });

    }

    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
       fileViewConfirmation date functions
     */
    public static boolean timeField(TextField location, Label timeCheck) {

        boolean time = location.getText().isEmpty();
        if (time) {
            timeCheck.setOpacity(1.0);
        }
        return time;
    }

    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     strictly emergency contacts function
     */
    public static boolean emergency(TextField name, TextField phone, TextField relationship, Label emergeCheck) {

        boolean emerge = name.getText().isEmpty() || phone.getText().isEmpty() || relationship.getText().isEmpty();
        if (emerge) {
            emergeCheck.setText("Ps provide at least one emrgency contact (Fill Name, Phone, Relationship)");
            emergeCheck.setOpacity(1.0);
        }
        return emerge;
    }

    /*
    -------------------------------------------------------------------------------------------------------------------------------
      strictly staff functions
     */
    public static boolean staffId(TextField id, Label staffid) {
        boolean ide = id.getText().isEmpty();
        if (ide) {
            staffid.setOpacity(1.0);
        }
        return ide;
    }

    public static boolean staffTitle(ComboBox title, Label stafftitle) {

        if (title.getValue() == null) {
            stafftitle.setOpacity(1.0);
        }
        return title.getValue() == null;
    }

    public static boolean priorityBox(CheckBox admin, CheckBox lab, CheckBox frontD, CheckBox pharm) {
        boolean isSelected = admin.isSelected() || lab.isSelected() || frontD.isSelected() || pharm.isSelected();

        return isSelected;
    }

    /*
    -------------------------------------------------------------------------------------------------------------------------------
     username 
     */
    
    public static boolean usernameField(TextField location, Label userCheck) {

        boolean user = location.getText().isEmpty();
        if (user) {
            userCheck.setOpacity(1.0);
        }
        return user;
    }
}
