/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Staff;
import healthmanagement.entity.StaffImage;
import java.util.List;

/**
 *
 * @author Elorm
 */
public class StaffCall {

    private Staff staff;

    private StaffImage image;

    public StaffCall() {

    }

    public StaffCall(Staff staff) {
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public StaffImage getImage() {
        return image;
    }

    public void setImage(StaffImage image) {
        this.image = image;
    }

}
