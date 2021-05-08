 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement;

import healthmanagement.utility.Paths;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Elorm
 */
public class HealthManagement extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Mainmanagement.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource(Paths.loginInterface));
        //  root.setStyle("-fx-background-color:transparent;");

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        //   stage.setFullScreen(true);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
