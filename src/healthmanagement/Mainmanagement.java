/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import healthmanagement.utility.Paths;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 *
 * @author Elorm
 */
public class Mainmanagement {

    public static Stage stage;

    public void start(Stage stage) throws Exception {
        
        Mainmanagement.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource(Paths.mainInterface));
        //  root.setStyle("-fx-background-color:transparent;");

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        //   stage.setFullScreen(true);
        stage.initStyle(StageStyle.UNIFIED);
        stage.show();
    }
}
