/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.PatientImage;
import healthmanagement.entity.StaffImage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class ImageBytesConversion extends ChooseImage {

    public static void getImagebitsForStore(StaffImage sti) throws IOException {

        if (getimage != null) {
            BufferedImage bimage = SwingFXUtils.fromFXImage(getimage, null);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(bimage, "jpg", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();

                sti.setImage(imageInByte);
            }

        } else {

            sti.setImage(null);
        }
    }

    public static void getImagebitsForStoreEdit_staff(StaffImage stdi, Image getTheImage) throws IOException {

        if (getTheImage != null) {

            BufferedImage bimage = SwingFXUtils.fromFXImage(getTheImage, null);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(bimage, "jpg", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                stdi.setImage(imageInByte);
            }

        } else {

            stdi.setImage(null);
        }
    }

    public static void getImagebitsForStoreEdit_patient(PatientImage padi, Image getTheImage) throws IOException {

        if (getTheImage != null) {

            BufferedImage bimage = SwingFXUtils.fromFXImage(getTheImage, null);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(bimage, "jpg", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                padi.setImage(imageInByte);
            }

        } else {

            padi.setImage(null);
        }
    }

    public static Image isFromBytestoImage_Staff(NewPatient pati) throws IOException {
        Image i = new Image("healthmanagement/images/noImage.gif");

        if (pati.getNewPatientImage().getImage() != null) {
            ByteArrayInputStream bais = new ByteArrayInputStream(pati.getNewPatientImage().getImage());

            while (bais.available() > 0) {
                BufferedImage buffImage = ImageIO.read(bais);

                final Image image = SwingFXUtils.toFXImage(buffImage, null);

                return image;
            }

        }

        return i;
    }

    public static Image isFromBytestoImage_Staff(StaffImage sm) throws IOException {
        Image i = new Image("healthmanagement/images/noImage.gif");

//        System.out.println(sm.getImage());
//        if (sm.getImage() != null) {
//            ByteArrayInputStream bais = new ByteArrayInputStream(sm.getImage());
//
//            while (bais.available() > 0) {
//                BufferedImage buffImage = ImageIO.read(bais);
//
//                final Image image = SwingFXUtils.toFXImage(buffImage, null);
//
//                return image;
//            }
//
//        }

        return i;
    }

}
