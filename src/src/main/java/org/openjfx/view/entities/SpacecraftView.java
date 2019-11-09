package org.openjfx.view.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpacecraftView extends ImageView {

    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream("images/spacecraft.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SpacecraftView() {
        super(image);
    }

}
