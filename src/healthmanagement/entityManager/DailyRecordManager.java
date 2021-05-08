/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entityManager;

import healthmanagement.entity.Patient;
import healthmanagement.entity.Records_patient_NurseLog;
import healthmanagement.utility.DateOfBirth;
import healthmanagement.utility.StringSplit;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elorm
 */
public class DailyRecordManager {

    public void removeFile(EntityManager em, Long id) {
        Records_patient_NurseLog file = findFile(em, id);
        if (file != null) {

            em.remove(file);
        }
    }

    public void removeFilesById(EntityManager em, String patientId) {

        em.getTransaction().begin();
        deleteMultipleFilesById(em, patientId);
        em.getTransaction().commit();

    }

    public Records_patient_NurseLog findFile(EntityManager em, Long id) {

        return em.find(Records_patient_NurseLog.class, id);
    }

    private int deleteMultipleFilesById(EntityManager em, String patientId) {

        // TypedQuery<Records_patient_NurseLog> query = em.createQuery("SELECT e FROM Records_patient_NurseLog e WHERE e.patient.id LIKE :id", Records_patient_NurseLog.class);
        // query.setParameter("id", patientId);
        Query query = em.createQuery("DELETE FROM Records_patient_NurseLog e WHERE e.patient.id LIKE :id", Records_patient_NurseLog.class);
        int deleteCount = query.setParameter("id", patientId).executeUpdate();

        return deleteCount;
    }

    public Records_patient_NurseLog findFileById(EntityManager em, Records_patient_NurseLog rpn) {

        TypedQuery<Records_patient_NurseLog> query = em.createQuery("SELECT e FROM Records_patient_NurseLog e WHERE e.id LIKE :id", Records_patient_NurseLog.class);
        query.setParameter("id", rpn.getId());

        return query.getSingleResult();
    }

    public List<Records_patient_NurseLog> findFileById_and_Date(EntityManager em, String patientId, String filedate) {

        ObservableList<Records_patient_NurseLog> list = FXCollections.observableArrayList();
        TypedQuery<Records_patient_NurseLog> query = em.createQuery("SELECT e FROM Records_patient_NurseLog e WHERE e.patient.id LIKE :id", Records_patient_NurseLog.class);
        query.setParameter("id", patientId);
        if (!query.getResultList().isEmpty()) {
            int size = query.getResultList().size();

            for (int i = 0; i < size; i++) {
                //  System.out.println(StringSplit.splitSpaceString(query.getResultList().get(i).getDate()).length);

                String[] array = StringSplit.splitSpaceString(query.getResultList().get(i).getDate());
                for (int x = 0; x < StringSplit.splitSpaceString(query.getResultList().get(i).getDate()).length; x++) {

                    String date = array[5] + "-" + DateOfBirth.getMonth_Integer(array[1]) + "-" + array[2];

                    if (date.equals(filedate)) {
                        list.add(query.getResultList().get(i));
                        break;
                    }
                }
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Records_patient_NurseLog> findAllDailyLogs_Nurse(EntityManager em, Patient patient) {

        String id = patient.getId();

        Query query = em.createQuery("select r from Records_patient_NurseLog r where r.patient.id = :id");
        query.setParameter("id", patient.getId());

        return query.getResultList();
    }

}
