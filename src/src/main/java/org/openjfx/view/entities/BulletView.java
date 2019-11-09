package org.openjfx.view.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BulletView extends ImageView {
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream("images/bullet.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BulletView() {
        super(image);
    }

}
