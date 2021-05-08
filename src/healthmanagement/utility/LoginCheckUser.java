/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Staff;
import healthmanagement.entityManager.StaffManager;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class LoginCheckUser {

    public static Staff isLoginAvailable(EntityManager em, String user, String password) {

        StaffManager sm = new StaffManager();

        if (!sm.loginStaff(em, user, password).isEmpty()) {
            Staff staff = sm.loginStaff(em, user, password).get(0);
            
            return staff;
        } else {
            return null;
        }

    }

}
