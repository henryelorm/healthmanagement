/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entityManager;

import healthmanagement.entity.Department;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elorm
 */
public class DepartmentManager {
    
     public void removeDepartment(EntityManager em, String id) {
         Department department = findDepartment(em, id);
        if (department != null) {

            em.remove(department);
        }
    }

    public Department findDepartment(EntityManager em, String id) {

        return em.find(Department.class, id);
    }

    public List<Department> findAllDepartment(EntityManager em) {

        TypedQuery<Department> query = em.createQuery("SELECT e FROM Department e", Department.class);
 
        return query.getResultList();
    }
    
}
