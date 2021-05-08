/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import java.util.UUID;

/**
 *
 * @author Elorm
 */
public class IDs_generator {

    public static String Department_Id() {

        return UUID.randomUUID().toString();
    }

    public static String Staff_Id() {
        return UUID.randomUUID().toString() + "_Staff";
    }

    public static String Patient_Id() {
        return UUID.randomUUID().toString() + "_Patient";
    }

    public static String searchPatientTab_Id() {
        return "searchpatient001";
    }
    
     public static String searchDoctorTab_Id() {
        return "searchdoctor001";
    }


}
