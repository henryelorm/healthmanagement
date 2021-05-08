/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.logs;

/**
 *
 * @author Elorm
 */
public class PageError {
    
    public static  String page_notFound(String par){
          System.err.println("The page " + par +" cannot be found.  Error: 101");
        return "The page " + par +" cannot be found.  Error: 101";
    }
    
}
