/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.controllers.FXMLDocumentController;
import healthmanagement.entityManager.PatientManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class StringSplit {

    public static List<String> splitGammaString(String string) {

        List<String> itemList = null;

        if (string != null && !"".equals(string)) {

            String parts[] = string.split("¬");

            itemList = Arrays.asList(parts);

        }

        return itemList;
    }

    public static String[] splitSpaceString(String string) {

        String parts[] = string.split(" ");

        return parts;
    }

    public static String[] splitDashString(String string) {

        String parts[] = string.split("/");

        return parts;
    }

    public static String[] splitUnderScoreString(String string) {

        String parts[] = string.split("_");

        return parts;
    }

    public static String[] splitCurlString(String string) {

        String parts[] = string.split("~");

        return parts;
    }

    public static String[] splitPercentString(String string) {

        String parts[] = string.split("%");

        return parts;
    }
    
     public static String[] splitCommaString(String string) {

        String parts[] = string.split(",");

        return parts;
    }

    public static String[] splitSemiColonString(String string) {

        String parts[] = string.split(";");
        /* String p[] = new String[parts.length];
        for (int i = 1; i < parts.length; i++) {
            System.err.println(parts[i]); 
            p[i] = parts[i];
        }
         */
        return parts;
    }

    public static String[] splitSemiColonString(EntityManager em, String string) {

        String parts[] = string.split(";");
        

        return parts;
    }

}
