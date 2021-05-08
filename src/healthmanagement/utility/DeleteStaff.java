/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Staff;
import healthmanagement.entityManager.HeapManager;
import healthmanagement.entityManager.StaffManager;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class DeleteStaff {

    public DeleteStaff(EntityManager em, Staff staff) {

        StaffManager stm = new StaffManager();
        HeapManager hm = new HeapManager();

        em.getTransaction().begin();
        hm.removeHeap(em, staff.getId());
        stm.removeStaff(em, staff.getId());
        stm.removeStaffImage(em, staff.getId());
        em.getTransaction().commit();

    }

}
