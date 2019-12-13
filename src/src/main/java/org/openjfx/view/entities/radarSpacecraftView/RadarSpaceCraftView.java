package org.openjfx.view.entities.radarSpacecraftView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RadarSpaceCraftView extends ImageView {
        private static Image image;

        static {
            try {
                image = new Image(new FileInputStream("assets/images/radar_spacecraft.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public RadarSpaceCraftView() {
            super(image);
        }

}
