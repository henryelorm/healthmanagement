/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.controllers.DeparmentsListController;
import healthmanagement.entity.Department;
import healthmanagement.entity.Staff;
import healthmanagement.entityManager.DepartmentManager;
import healthmanagement.entityManager.HeapManager;
import healthmanagement.entityManager.StaffManager;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class DeleteDepartment {

    /*
    department
    staff
    heap_dep
    staffImage
     */
    public DeleteDepartment(EntityManager em, Department department) {

        DepartmentManager dm = new DepartmentManager();
        StaffManager sm = new StaffManager();
        HeapManager hm = new HeapManager();

        List<Staff> li = sm.findAllStaff(em, department.getId());
        int size = li.size();
        em.getTransaction().begin();
        for (int i = 0; i < size; i++) {

            // System.out.println(li.get(i).getId());
            // em.getTransaction().begin();
            sm.removeStaffImage(em, li.get(i).getId());
            sm.removeStaff(em, li.get(i).getId());
            //   em.getTransaction().commit();
        }

        //  System.out.println(department.getId());
        hm.removeHeap(em, department.getId());
        dm.removeDepartment(em, department.getId());
        em.getTransaction().commit();

    }

}
