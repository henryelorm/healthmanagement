/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

/**
 *
 * @author Elorm
 */
public class MedicationList {

    private int index;

    private String item;

    public MedicationList(int index, String item) {
        this.index = index;
        this.item = item;
    }

    public MedicationList() {

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
