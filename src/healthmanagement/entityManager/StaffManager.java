/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entityManager;

import healthmanagement.entity.Staff;
import healthmanagement.entity.StaffImage;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elorm
 */
public class StaffManager {

    public void removeStaff(EntityManager em, String id) {
        Staff staff = findStaff(em, id);
        if (staff != null) {

            em.remove(staff);
        }
    }

    public void removeStaffImage(EntityManager em, String id) {

        StaffImage staffImage = findStaffImage(em, id);
        if (staffImage != null) {

            em.remove(staffImage);
        }
    }

    public Staff findStaff(EntityManager em, String id) {

        return em.find(Staff.class, id);
    }

    public StaffImage findStaffImage(EntityManager em, String id) {

        StaffImage im = em.find(StaffImage.class, id);
        if (im != null) {
            return im;
        } else {
            StaffImage imnull = new StaffImage();

            imnull.setId(id);
            imnull.setImage(null);

            return im;
        }

    }

    public List<Staff> findAllDoctors(EntityManager em) {

        TypedQuery<Staff> query = em.createQuery("SELECT e FROM Staff e WHERE e.title LIKE :title", Staff.class);
        query.setParameter("title", "Doctor");
        return query.getResultList();
    }

    public List<Staff> findAllStaff(EntityManager em, String departmentId) {

        TypedQuery<Staff> query = em.createQuery("SELECT e FROM Staff e WHERE e.department LIKE :departmentID", Staff.class);
        query.setParameter("departmentID", departmentId);
        return query.getResultList();
    }

    public List<Staff> loginStaff(EntityManager em, String user, String pass) {

        TypedQuery<Staff> query = em.createQuery("SELECT e FROM Staff e WHERE e.userName = :USER AND e.staffPassword = :PASS ", Staff.class);
        query.setParameter("USER", user);
        query.setParameter("PASS", pass);
        
        return query.getResultList();
    }
}
