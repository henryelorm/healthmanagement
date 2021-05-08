/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entityManager;

import healthmanagement.controllers.FXMLDocumentController;
import healthmanagement.entity.Records_Patient;
import healthmanagement.utility.GetTab;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elorm
 */
public class RecordManager {

    public void removeFile(EntityManager em, Long id) {
        Records_Patient file = findFile(em, id);
        if (file != null) {

            em.remove(file);
        }
    }

    public void removeFilesById(EntityManager em, String patientId) {

        em.getTransaction().begin();
        deleteMultipleFilesById(em, patientId);
        em.getTransaction().commit();

        
        // to delete open patient tab
        int index = GetTab.forDelete_id(FXMLDocumentController.tabpane_open, patientId);
       
        if (index > -1) {
            FXMLDocumentController.tabpane_open.getTabs().remove(index);
        }
    }

    private int deleteMultipleFilesById(EntityManager em, String patientId) {

        Query query = em.createQuery("DELETE FROM Records_Patient e WHERE e.patientID LIKE :patientID", Records_Patient.class);
        int deleteCount = query.setParameter("patientID", patientId).executeUpdate();

        return deleteCount;
    }

    public Records_Patient findFile(EntityManager em, Long id) {

        return em.find(Records_Patient.class, id);
    }

    public List<Records_Patient> findAllRecordFiles(EntityManager em, String patientId) {

        TypedQuery<Records_Patient> query = em.createQuery("SELECT e FROM Records_Patient e WHERE e.patientID LIKE :patientID", Records_Patient.class);
        query.setParameter("patientID", patientId);

        return query.getResultList();
    }
}
