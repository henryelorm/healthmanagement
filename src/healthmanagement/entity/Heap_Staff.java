/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;

/**
 *
 * @author Elorm
 */
@Entity
@Cache(coordinationType = CacheCoordinationType.SEND_NEW_OBJECTS_WITH_CHANGES)
public class Heap_Staff implements Serializable {

    @Id
    private Staff staffId;

    @Column(name = "LASTSIZE")
    @Basic(fetch = FetchType.LAZY)
    private int lastSize;

    @Column(name = "NEWSIZE")
    @Basic(fetch = FetchType.LAZY)
    private int newSize;

    @Column(name = "HEAP", length = 5000)
    @Basic(fetch = FetchType.LAZY)

    private String heap;

    public Heap_Staff() {

    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    public int getLastSize() {
        return lastSize;
    }

    public void setLastSize(int lastSize) {
        this.lastSize = lastSize;
    }

    public int getNewSize() {
        return newSize;
    }

    public void setNewSize(int newSize) {
        this.newSize = newSize;
    }

    public String getHeap() {
        return heap;
    }

    public void setHeap(String heap) {
        this.heap = heap;
    }

}
