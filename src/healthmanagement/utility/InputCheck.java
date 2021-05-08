/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import javafx.scene.control.Label;

/**
 *
 * @author Elorm
 */
public class InputCheck {

    public static int emptyField(String text) {
        switch (text) {
            case "":
                return 0;
            case " ":
                return 0;
            default:
                return 1;
        }
    }
}
