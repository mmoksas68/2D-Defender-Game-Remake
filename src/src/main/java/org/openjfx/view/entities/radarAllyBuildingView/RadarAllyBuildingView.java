package org.openjfx.view.entities.radarAllyBuildingView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RadarAllyBuildingView extends ImageView {
        private static Image image;

        static {
            try {
                image = new Image(new FileInputStream("assets/images/allyBuilding3.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public RadarAllyBuildingView() {
            super(image);
        }
}
