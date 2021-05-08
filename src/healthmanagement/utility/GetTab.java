/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import javafx.scene.control.TabPane;

/**
 *
 * @author Elorm
 */
public class GetTab {

    public static int forDelete(TabPane tabpane, String tabText) {

        int size = tabpane.getTabs().size();

        for (int i = 0; i < size; i++) {
            if (tabpane.getTabs().get(i).getText().equals(tabText)) {
               // System.out.println("in: " + i);
                return i;
            }
        }
        return -1;
    }

    public static int forDelete_id(TabPane tabpane, String id) {

        int size = tabpane.getTabs().size();

        for (int i = 0; i < size; i++) {
            if (tabpane.getTabs().get(i).getId().equals(id)) {
                System.out.println("in: " + i);
                return i;
            }
        }
        return -1;
    }
}
