/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entityManager;

import healthmanagement.entity.Heap_Staff;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elorm
 */
public class HeapManager {
    
     public void removeHeap(EntityManager em, String id) {
         Heap_Staff hs = findHeap(em, id);
        if (hs != null) {

            em.remove(hs);
        }
    }

    public Heap_Staff findHeap(EntityManager em, String id) {

        return em.find(Heap_Staff.class,id);
    }

    
    public List<Heap_Staff> findAllHeap(EntityManager em) {

        TypedQuery<Heap_Staff> query = em.createQuery("SELECT e FROM Heap_staff e", Heap_Staff.class);

        return query.getResultList();
    }
    
}
