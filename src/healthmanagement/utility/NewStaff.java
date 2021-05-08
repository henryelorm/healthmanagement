/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Staff;
import healthmanagement.entity.StaffImage;
import javafx.collections.ObservableList;

/**
 *
 * @author Elorm
 */
public class NewStaff {

    private Staff newStaff;

    private StaffImage newStaffImage;

    public NewStaff() {

    }

    public Staff getNewStaff() {
        return newStaff;
    }

    public void setNewStaff(Staff newStaff) {
        this.newStaff = newStaff;
    }

    public StaffImage getNewStaffImage() {
        return newStaffImage;
    }

    public void setNewStaffImage(StaffImage newStaffImage) {
        this.newStaffImage = newStaffImage;
    }

}
