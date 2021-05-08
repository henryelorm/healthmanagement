/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.httpRequest;

import healthmanagement.entity.Department;
import healthmanagement.entity.Heap_Dep;
import healthmanagement.entity.Heap_Staff;
import healthmanagement.entity.Patient;
import healthmanagement.entity.Staff;
import healthmanagement.entityManager.HeapManager;
import healthmanagement.entityManager.HeapManager_Dep;
import healthmanagement.utility.PersistActions;
import healthmanagement.utility.StringSplit;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class Post {

    public void send(EntityManager em, Patient patient, Staff staff, Department dep) {

        HeapManager hm = new HeapManager();
        HeapManager_Dep hmd = new HeapManager_Dep();
        PersistActions pa = new PersistActions();

        if (patient != null) {

            if (dep != null) {

                Heap_Dep hd = hmd.findHeap(em, dep.getId());

                hd.setHeap(hd.getHeap() + ";" + patient.getId());
                hd.setNewSize(StringSplit.splitSemiColonString(hd.getHeap()).length - 1);
                pa.HeapSend_Department(em, hd);

                //  int size = StringSplit.splitSemiColonString(hd.getHeap()).length - 1;
            }

            if (staff != null) {

                Heap_Staff hs = hm.findHeap(em, staff.getId());
                System.out.println(staff.getId() + "   " + hs.getHeap());
                hs.setHeap(patient.getId() + ";" + hs.getHeap());
                hs.setNewSize(StringSplit.splitSemiColonString(hs.getHeap()).length - 1);
                pa.HeapSend_Staff(em, hs);

                 int size = StringSplit.splitSemiColonString(hs.getHeap()).length - 1;
            }

        }
        /*
         URL url = new URL(urlLink);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("POST");
        

         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         StringBuffer sb = new StringBuffer();
         String line;
         while ((line = in.readLine()) != null) {
         sb.append(line);
         }
         in.close();
         System.out.println(sb.toString());
         */
    }

}
