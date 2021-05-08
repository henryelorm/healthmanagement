/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Records_Patient;
import healthmanagement.entity.Records_patient_NurseLog;
import healthmanagement.entityManager.DailyRecordManager;
import healthmanagement.entityManager.RecordManager;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class DeleteFile {

    public DeleteFile(EntityManager em, Records_Patient rp) {

        RecordManager rm = new RecordManager();

        em.getTransaction().begin();
        rm.removeFile(em, rp.getId());
        em.getTransaction().commit();

    }

    public DeleteFile(EntityManager em, Records_patient_NurseLog rpn) {

        DailyRecordManager drm = new DailyRecordManager();

        em.getTransaction().begin();
        drm.removeFile(em, rpn.getId());
        em.getTransaction().commit();

    }

}
