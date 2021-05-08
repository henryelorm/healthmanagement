/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Elorm
 */

public class EntityLink {
    
    public EntityManager em;

    public EntityManager em() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HealthManagementPU");
        em = emf.createEntityManager();
        return em;
    }
    
    public void entitiyClose(EntityManager em){
        em.close();
    }

}
