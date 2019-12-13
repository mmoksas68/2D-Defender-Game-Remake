package org.openjfx.view.entities.BossAbilityViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LittleBossView extends ImageView {

    public LittleBossView( double x, double y, double width, double height) throws FileNotFoundException {
        Image image = new Image( new FileInputStream("resources/boss1.png"));

        setImage( image);
        setFitHeight( width);
        setFitHeight( height);
        setTranslateX( x);
        setTranslateY( y);
    }

    public void refreshLittleBoss (double currentX, double currentY) {
        setTranslateX( currentX);
        setTranslateY( currentY);
    }

}
