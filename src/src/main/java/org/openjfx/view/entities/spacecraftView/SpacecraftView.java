package org.openjfx.view.entities.spacecraftView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpacecraftView extends ImageView {

    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream("assets/images/Spaceship_01_BLUE.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SpacecraftView() {
        super(image);
    }

}
