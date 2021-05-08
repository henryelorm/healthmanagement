/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import healthmanagement.entity.Records_patient_NurseLog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class DailyLogQuickViewController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private Label bpText;

    @FXML
    private Label weightText;

    @FXML
    private Label heightText;

    @FXML
    private Label tempText;

    @FXML
    private TextArea noteArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Records_patient_NurseLog rpn = DailyLoqQuickViewTableController.record.getRecordsNurseLog();
        DailyLoqQuickViewTableController.record.setRecordsNurseLog(null);
        bpText.setText(rpn.getBp());
        weightText.setText(rpn.getWeight());
        heightText.setText(rpn.getHeight());
        tempText.setText(rpn.getTemp());
        noteArea.setText(rpn.getNotes());
    }

}
