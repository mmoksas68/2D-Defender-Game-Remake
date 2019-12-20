package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FireView extends ImageView {
    public static String resource = "resources/fire.gif";
    public FireView () {
        Image image = null;
        try {
            image = new Image( new FileInputStream(resource));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setImage( image);
        setTranslateX( 30);
        setTranslateY( 50);
        setFitWidth( 20);
        setFitHeight( 20);
    }
}
