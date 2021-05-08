/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Department;
import healthmanagement.entity.Heap_Dep;
import healthmanagement.entity.Heap_Staff;
import healthmanagement.entity.Patient;
import healthmanagement.entity.PatientImage;
import healthmanagement.entity.Records_Patient;
import healthmanagement.entity.Records_patient_NurseLog;
import healthmanagement.entity.Staff;
import healthmanagement.entity.StaffImage;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class PersistActions {

    public void NewDepartment(EntityManager em, Department de) {

        em.getTransaction().begin();
        em.persist(de);
        em.getTransaction().commit();

    }

    public void NewStaff(EntityManager em, Staff staff) {

        em.getTransaction().begin();
        em.persist(staff);
        em.getTransaction().commit();
        em.clear();
    }

    public void NewStaffImage(EntityManager em, StaffImage stfImage) {

        em.getTransaction().begin();
        em.persist(stfImage);
        em.getTransaction().commit();
        em.clear();
    }

    public void NewPatient(EntityManager em, Patient patient) {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

    public void NewPatientImage(EntityManager em, PatientImage patientImage) {
        em.getTransaction().begin();
        em.persist(patientImage);
        em.getTransaction().commit();
    }

    public void NurseLog(EntityManager em, Records_patient_NurseLog rpn) {
        em.getTransaction().begin();
        em.persist(rpn);
        em.getTransaction().commit();
    }

    public void FileDate(EntityManager em, Records_Patient rp) {
        em.getTransaction().begin();
        em.persist(rp);
        em.getTransaction().commit();
    }

    /*
    Heap: database location for storing patient to doctor / department  data request: Acts as a push notify upon scanning 
    Basically serves as a push notifier by scanning tables every few seconds in database. If table is updated the software notifiers user.
     */
    public void HeapSend_Staff(EntityManager em, Heap_Staff hs) {

        em.getTransaction().begin();
        em.persist(hs);
        em.getTransaction().commit();
    }

    public void HeapSend_Department(EntityManager em, Heap_Dep hd) {
        em.getTransaction().begin();
        em.persist(hd);
        em.getTransaction().commit();
    }

}
