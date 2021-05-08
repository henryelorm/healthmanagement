/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.controllers;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class ActionLoaderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ProgressIndicator  actionProgress;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        actionProgress.setProgress(0);
    }    
    
}
