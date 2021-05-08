/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Elorm
 */
public class StringMerge {

    public String merge(ObservableList<String> list) {

        int size = list.size();
        String listMerge = "";

        for (int i = 0; i < size; i++) {
            listMerge = listMerge + "¬" + list.get(i);
        }

        return (listMerge);
    }
}
