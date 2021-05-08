/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Patient;
import healthmanagement.entity.PatientImage;
import healthmanagement.entity.Records_Patient;
import healthmanagement.entityManager.DailyRecordManager;
import healthmanagement.entityManager.PatientManager;
import healthmanagement.entityManager.RecordManager;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class DeletePatient {

    public DeletePatient(EntityManager em, Patient patient) {

        PatientManager pm = new PatientManager();
        RecordManager rm = new RecordManager();
        DailyRecordManager drm = new DailyRecordManager();

        drm.removeFilesById(em, patient.getId());
        rm.removeFilesById(em, patient.getId());
        
        em.getTransaction().begin();
        pm.removePatientImage(em, patient.getId());
        pm.removePatient(em, patient.getId());
        em.getTransaction().commit();
        
    }

}
