/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entityManager;

import healthmanagement.entity.Heap_Dep;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elorm
 */
public class HeapManager_Dep {
    
     public void removeHeap(EntityManager em, String id) {
         Heap_Dep hd = findHeap(em, id);
        if (hd != null) {
            em.remove(hd);
        }
    }

    public Heap_Dep findHeap(EntityManager em, String id) {

        return em.find(Heap_Dep.class,id);
    }

    
    public List<Heap_Dep> findAllHeap(EntityManager em) {

        TypedQuery<Heap_Dep> query = em.createQuery("SELECT e FROM Heap_Dep e", Heap_Dep.class);

        return query.getResultList();
    }
    
}
