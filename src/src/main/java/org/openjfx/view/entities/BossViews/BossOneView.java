package org.openjfx.view.entities.BossViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.utilization.ModelToViewBoss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BossOneView extends ImageView {
    public  BossOneView (ModelToViewBoss modelToViewBoss)  {
        Image image = null;
        try {
            image = new Image( new FileInputStream("resources/boss2.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setImage ( image);
        setFitWidth( modelToViewBoss.getHitboxWidth());
        setFitHeight( modelToViewBoss.getHitboxHeight());
        setTranslateX( modelToViewBoss.getLocationX());
        setTranslateY( modelToViewBoss.getLocationY());
    }
    public void refresh (ModelToViewBoss modelToViewBoss) {
        setTranslateX( modelToViewBoss.getLocationX());
        setTranslateY( modelToViewBoss.getLocationY());
        setFitWidth( modelToViewBoss.getHitboxWidth());
        setFitHeight( modelToViewBoss.getHitboxHeight());
    }
}
