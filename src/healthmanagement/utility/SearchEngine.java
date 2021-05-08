/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Patient;
import healthmanagement.entity.Staff;
import healthmanagement.logs.BugLog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Elorm
 */
public class SearchEngine {

    /**
     * ****************************************************************************************************************************************
     * Search Engine for the Patient Table
     */
    public static void SearchField_Patient(TableView<Patient> table, TextField textfield, ObservableList<Patient> list) {

        ObservableList<Patient> backupList = FXCollections.observableArrayList();
        backupList.setAll(list);

        textfield.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                SearchEngine.search_Patient(list, backupList, newValue);
                if (textfield.getText().isEmpty()) {

                    list.clear();
                    list.setAll(backupList);
                }
            }
        });

    }

    private static void search_Patient(ObservableList<Patient> list, ObservableList<Patient> backupList, String searchFor) {

        list.clear();
        int size = backupList.size();

        for (int i = 0; i < size; i++) {

            try {

                if (searchFor.equalsIgnoreCase(backupList.get(i).getLastName().substring(0, searchFor.length()))
                        || searchFor.equalsIgnoreCase(backupList.get(i).getFirstName().substring(0, searchFor.length())) || searchFor.equalsIgnoreCase(backupList.get(i).getPatientId().substring(0, searchFor.length()))) {

                    list.add(backupList.get(i));

                }
            } catch (StringIndexOutOfBoundsException error) {

                //  BugLog.Error_static("Search Error", "Search Error");
            }

        }

    }

    /**
     * ****************************************************************************************************************************************
     * Search Engine for the Staff Table
     */
    public static void SearchField_Staff(TableView<Staff> table, TextField textfield, ObservableList<Staff> list) {

        ObservableList<Staff> backupList = FXCollections.observableArrayList();
        backupList.setAll(list);

        textfield.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                SearchEngine.search_Staff(list, backupList, newValue);
                if (textfield.getText().isEmpty()) {

                    list.clear();
                    list.setAll(backupList);
                }
            }
        });

    }

    private static void search_Staff(ObservableList<Staff> list, ObservableList<Staff> backupList, String searchFor) {

        list.clear();
        int size = backupList.size();

        for (int i = 0; i < size; i++) {

            try {

                if (searchFor.equalsIgnoreCase(backupList.get(i).getLastName().substring(0, searchFor.length()))
                        || searchFor.equalsIgnoreCase(backupList.get(i).getFirstName().substring(0, searchFor.length())) || searchFor.equalsIgnoreCase(backupList.get(i).getStaffId().substring(0, searchFor.length()))) {

                    list.add(backupList.get(i));

                }
            } catch (StringIndexOutOfBoundsException error) {

                //  BugLog.Error_static("Search Error", "Search Error");
            }

        }

    }

}
