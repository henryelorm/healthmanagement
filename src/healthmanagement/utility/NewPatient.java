/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Patient;
import healthmanagement.entity.PatientImage;

/**
 *
 * @author Elorm
 */
public class NewPatient {

    private Patient newPatient;

    private PatientImage newPatientImage;

    public Patient getNewPatient() {
        return newPatient;
    }

    public void setNewPatient(Patient newPatient) {
        this.newPatient = newPatient;
    }

    public PatientImage getNewPatientImage() {
        return newPatientImage;
    }

    public void setNewPatientImage(PatientImage newPatientImage) {
        this.newPatientImage = newPatientImage;
    }

}
