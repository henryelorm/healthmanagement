/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Patient;
import healthmanagement.entity.PatientImage;
import healthmanagement.entity.Records_Patient;
import healthmanagement.entity.Records_patient_NurseLog;
import healthmanagement.entity.Staff;
import healthmanagement.entity.StaffImage;
import healthmanagement.entityManager.DailyRecordManager;
import healthmanagement.entityManager.PatientManager;
import healthmanagement.entityManager.RecordManager;
import healthmanagement.entityManager.StaffManager;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Elorm
 */
public class SaveEditedFiles {
    
    public static void Patient(EntityManager em, Patient patient, PatientImage patImage) {
        
        PatientManager pam = new PatientManager();
        
        Patient p = pam.findPatient(em, patient.getId());
        PatientImage pi = pam.findPatientImage(em, patImage.getId());
        
        em.getTransaction().begin();
        p.setGender(patient.getGender());
        p.setFirstName(patient.getFirstName());
        p.setLastName(patient.getLastName());
        p.setOtherName(patient.getOtherName());
        p.setDob(patient.getDob());
        p.setNationality(patient.getNationality());
        p.setCity(patient.getCity());
        p.setHouseNo(patient.getHouseNo());
        p.setPhoneNo(patient.getPhoneNo());
        p.setEmail(patient.getEmail());
        p.setOccupation(patient.getOccupation());
        p.setFatherName(patient.getFatherName());
        p.setMotherName(patient.getMotherName());
        p.setParentsPhone(patient.getParentsPhone());
        p.setMaritalStatus(patient.getMaritalStatus());
        p.setSpouseName(patient.getSpouseName());
        p.setSpouseNo(patient.getSpouseNo());
        p.setPatientId(patient.getPatientId());
        p.setEmergencyName1(patient.getEmergencyName1());
        p.setEmergencyName2(patient.getEmergencyName2());
        p.setEmergencyName3(patient.getEmergencyName3());
        p.setEmergencyPhone1(patient.getEmergencyPhone1());
        p.setEmergencyPhone2(patient.getEmergencyPhone2());
        p.setEmergencyPhone3(patient.getEmergencyPhone3());
        p.setEmergencyRelation1(patient.getEmergencyRelation1());
        p.setEmergencyRelation2(patient.getEmergencyRelation2());
        p.setEmergencyRelation3(patient.getEmergencyRelation3());
        pi.setImage(patImage.getImage());
        em.persist(p);
        em.persist(pi);
        em.getTransaction().commit();
        
    }
    
    public static void Staff(EntityManager em, Staff staff, StaffImage staffImage) {
        
        StaffManager stm = new StaffManager();
        
        Staff st = stm.findStaff(em, staff.getId());
        StaffImage sti = stm.findStaffImage(em, staff.getId());
        
        if (st != null) {
            
            em.getTransaction().begin();
            
            st.setFirstName(staff.getFirstName());
            st.setLastName(staff.getLastName());
            st.setOtherName(staff.getOtherName());
            st.setDob(staff.getDob());
            st.setStaffId(staff.getStaffId());
            st.setTitle(staff.getTitle());
            st.setSpecialization(staff.getSpecialization());
            st.setPhoneNo(staff.getPhoneNo());
            st.setEmail(staff.getEmail());
            st.setHouseNo(staff.getHouseNo());
            st.setCity(staff.getCity());
            st.setUserName(staff.getUserName());
            st.setStaffPassword(staff.getStaffPassword());
            st.setPriority(staff.getPriority());
            
            if (sti != null) {
                sti.setImage(staffImage.getImage());
                em.persist(sti);
            } else {
                StaffImage image = new StaffImage();
                image.setId(staff.getId());
                image.setImage(staffImage.getImage());
                em.persist(image);
            }
            
            em.persist(st);
            
            em.getTransaction().commit();
        } else {
            JOptionPane.showMessageDialog(null, "Error Can't find Person In Database",
                    "Database error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public static void Record_patient(EntityManager em, Records_Patient record) {
        
        RecordManager rm = new RecordManager();
        
        Records_Patient rp = rm.findFile(em, record.getId());
        em.getTransaction().begin();
        rp.setTitle(record.getTitle());
        rp.setConditions(record.getConditions());
        rp.setAdmissionDate(record.getAdmissionDate());
        rp.setDischargeDate(record.getDischargeDate());
        rp.setComplaints(record.getComplaints());
        rp.setFindings(record.getFindings());
        rp.setDiagnosis(record.getDiagnosis());
        rp.setTreatment(record.getTreatment());
        rp.setMedication(record.getMedication());
        rp.setRecommendations(record.getRecommendations());
        rp.setDetails(record.getDetails());
        em.persist(rp);
        em.getTransaction().commit();
    }
    
    public static void Record_patient_nurseLog(EntityManager em, Records_patient_NurseLog rpn) {
        
        DailyRecordManager drm = new DailyRecordManager();
        
        Records_patient_NurseLog rpnn = drm.findFile(em, rpn.getId());
        em.getTransaction().begin();
        rpnn.setBp(rpn.getBp());
        rpnn.setHeight(rpn.getHeight());
        rpnn.setNotes(rpn.getNotes());
        rpnn.setTemp(rpn.getTemp());
        rpnn.setWeight(rpn.getWeight());
        em.persist(rpnn);
        em.getTransaction().commit();
    }
    
}
