/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

/**
 *
 * @author Elorm
 */
import healthmanagement.Mainmanagement;
import healthmanagement.controllers.FXMLDocumentController;
import healthmanagement.logs.BugLog;
import healthmanagement.logs.PageError;
import healthmanagement.utility.Paths;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Templates {

    public Templates() {
    }

    public Templates(Stage primaryStage, String path, String page) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNIFIED);
            primaryStage.showAndWait();
        } catch (IOException ex) {
            BugLog.Error_static("Page Not Found", page);
        }
    }

    public Parent searchP(Tab tab, String url) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
            tab.setContent(root);
        } catch (IOException ex) {
            PageError.page_notFound(url);

        }

        return root;
    }

    public Parent parentPane(String url) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));

        } catch (IOException ex) {
            PageError.page_notFound(url);

        }
        return root;
    }

    public void templateNODeco(Stage primaryStage, String url) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource(url));

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.initModality(Modality.NONE);
            //   primaryStage.initOwner(FXMLDocumentController.primaryStageDepartment);
            primaryStage.showAndWait();
        } catch (IOException ex) {
            PageError.page_notFound(url);
        }
    }

}
