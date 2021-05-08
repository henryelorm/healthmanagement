/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.controllers.FXMLDocumentController;
import healthmanagement.controllers.FileViewController;
import healthmanagement.controllers.SearchPatientController;
import static healthmanagement.controllers.SearchPatientController.np;
import healthmanagement.entity.Patient;
import healthmanagement.entityManager.PatientManager;
import javafx.scene.control.Tab;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class OpenFolder {

    public void openFolder(EntityManager em, String patientId) {

        Tab tab = new Tab();
        PatientManager pm = new PatientManager();
        Patient patient = pm.findPatient(em, patientId);

        SearchPatientController.np.setNewPatient(patient);

        FileViewController.mapPatient.put(patient.getId(), patient);

        Tabs_And_Content tac = new Tabs_And_Content(FXMLDocumentController.tabpane_open, tab, true, (patient.getLastName() + patient.getFirstName()), patient.getId(), Paths.folder, "");

    }
}
