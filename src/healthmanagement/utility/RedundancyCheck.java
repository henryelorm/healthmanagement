/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Department;
import healthmanagement.entity.Staff;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Elorm
 */
public class RedundancyCheck {

    private static int tabselect = 0;

    public static void mainTabpane(TabPane tabpane, Tab tab, String tabText) {
        int tabSize = tabpane.getTabs().size();

        if (tabpane.getTabs().isEmpty()) {
            tabpane.getTabs().add(tab);
        } else if (tabSize > 0) {
            if (!tabExist(tabpane, tab, tabSize)) {

                tabpane.getTabs().add(tab);
                tabpane.getSelectionModel().select(tab);

            } else {

                tabpane.getSelectionModel().select(tabpane.getTabs().get(tabselect));
            }
        }
    }
// check if a tab exist on the tabpane ...if tab exist, return true, otherwise false

    private static boolean tabExist(TabPane tabpane, Tab tab, int tabSize) {
        for (int i = 0; i < tabSize; i++) {

            if (tab.getId().equals(tabpane.getTabs().get(i).getId())) {
                tabselect = i;
                return true;
            }
        }
        return false;
    }

    //if name is duplicated = true || if name  not duplicated = false
    public static boolean isDapartmentNameDuplicated(ObservableList<Department> deList, String name, int size) {

        for (int i = 0; i < size; i++) {
            if (deList.get(i).getName().equals(name)) {

                return true;
            }
        }
        return false;
    }

    //Removes logedin staff from List of staffs (Doctors)
    public List<Staff> removeLoginStaff(List<Staff> staffList, Staff logedinStaff) {

        for (int i = 0; i < staffList.size(); i++) {

            if (staffList.get(i).getId().equals(logedinStaff.getId())) {
                staffList.remove(i);
                break;
            }
        }
        return staffList;
    }

    /*
     *Check if the patient is in the list, if not add if exists delete and add ; used for the maintab indexing
     */
    public static List<NewPatient> checkrepeatition(List<NewPatient> patientsList, NewPatient selectedPatient) {

        if (!isPatientExist(patientsList, selectedPatient)) {
            patientsList.add(selectedPatient);
        }

        return patientsList;
    }

    private static boolean isPatientExist(List<NewPatient> patientsList, NewPatient selectedPatient) {

        for (int i = 0; i < patientsList.size(); i++) {
            if (patientsList.get(i).getNewPatient().getId().equals(selectedPatient.getNewPatient().getId())) {

                patientsList.remove(i);
                patientsList.add(selectedPatient);
                return true;
            }
        }
        return false;
    }

}
