/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Elorm
 */
public class PriotyValue {

    public String setValue(CheckBox[] prioArray) {
        int size = prioArray.length;
        String prioriyValue = "";
        for (int i = 0; i < size; i++) {

            if (prioArray[i].isSelected()) {
                prioriyValue = prioriyValue + prioArray[i].getText() + ",";

            }
        }
        return prioriyValue;
    }

}
