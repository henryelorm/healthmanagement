/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.Mainmanagement;
import healthmanagement.controllers.FXMLDocumentController;
import healthmanagement.entity.Heap_Staff;
import healthmanagement.entity.Staff;
import healthmanagement.entityManager.EntityLink;
import healthmanagement.entityManager.HeapManager;
import healthmanagement.entityManager.PatientManager;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.persistence.EntityManager;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Elorm
 */
public class HeapProbe {

    HeapManager hm = new HeapManager();

    int i;

    public HeapProbe(EntityManager em, Stage stage, Staff staff) {

        final String loginId = staff.getId();

        if (loginId != "") {
            i = -1;
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(9000),
                    ae -> recieve(em, stage, loginId)));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    private void recieve(EntityManager em, Stage stage, String loginId) {

        Heap_Staff hs = hm.findHeap(em, loginId);

        if (hs != null) {
            int diff = probeDiff(hs);
            //  System.out.println(diff);
            if (diff > 0) {

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
                        ae -> Notification(em, stage, getPatientId(em, hs, diff))));
                timeline.setCycleCount(diff);
                timeline.play();

            }

            em.refresh(hs);
        }
    }

    public int probeDiff(Heap_Staff hs) throws NullPointerException {

        int diff = hs.getNewSize() - hs.getLastSize();

        return diff;
    }

    String p[] = null;

    private String getPatientId(EntityManager em, Heap_Staff hs, int diff) throws NullPointerException {

        PersistActions pa = new PersistActions();

        if (i < diff) {
            i++;
        } else if (i > diff) {
            i = 0;
        }

        p = StringSplit.splitSemiColonString(hs.getHeap());

        for (int i = 0; i < diff; i++) {

            if (new PatientManager().findPatient(em, p[i]) != null) {

                FXMLDocumentController.folderPatientList.add(new PatientMessage(p[i],
                        (new PatientManager().findPatient(em, p[i]).getLastName()
                        + " " + new PatientManager().findPatient(em, p[i]).getFirstName() + " "
                        + new PatientManager().findPatient(em, p[i]).getOtherName())));

            }
        }

        hs.setLastSize(hs.getNewSize());
        pa.HeapSend_Staff(em, hs);

        return p[i];

    }

    private void Notification(EntityManager em, Stage primaryStage, String Id) {

        final OpenFolder of = new OpenFolder();

        Notifications notify = Notifications.create()
                .text("New Folder")
                .title("New Folder Available")
                .graphic(null)
                .owner(primaryStage)
                .hideAfter(Duration.seconds(8))
                .position(Pos.TOP_RIGHT)
                .hideCloseButton()
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        removeFileFromList(Id);
                        of.openFolder(em, Id);
                    }
                });

        notify.showConfirm();
    }

    private void removeFileFromList(String Id) {

        if (!FXMLDocumentController.folderPatientList.isEmpty()) {
            int size = FXMLDocumentController.folderPatientList.size();
            for (int i = 0; i < size; i++) {

                if (FXMLDocumentController.folderPatientList.get(i).getId().equals(Id)) {
                    FXMLDocumentController.folderPatientList.remove(i);
                }
            }
        }

    }

}
