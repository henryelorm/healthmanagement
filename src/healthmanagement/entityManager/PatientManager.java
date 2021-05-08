/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entityManager;

import healthmanagement.entity.Patient;
import healthmanagement.entity.PatientImage;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elorm
 */
public class PatientManager {

    public void removePatient(EntityManager em, String id) {
        Patient patient = findPatient(em, id);
        if (patient != null) {

            em.remove(patient);
        }
    }
    
    public void removePatientImage(EntityManager em, String id) {
        PatientImage patientImage = findPatientImage(em, id);
        if (patientImage != null) {

            em.remove(patientImage);
        }
    }

    public Patient findPatient(EntityManager em, String id) {

        return em.find(Patient.class, id);
    }

    
    public PatientImage findPatientImage(EntityManager em, String id) {

        return em.find(PatientImage.class, id);
    }
    
    public List<Patient> findAllPatient(EntityManager em) {

        TypedQuery<Patient> query = em.createQuery("SELECT e FROM Patient e", Patient.class);

        return query.getResultList();
    }
}
