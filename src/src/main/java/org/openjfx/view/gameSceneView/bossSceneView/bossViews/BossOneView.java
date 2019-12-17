package org.openjfx.view.gameSceneView.bossSceneView.bossViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.utilization.ModelToViewBoss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BossOneView extends ImageView {
    public  BossOneView (ModelToViewBoss modelToViewBoss, double scaleW, double scaleH)  {
        Image image = null;
        try {
            image = new Image( new FileInputStream("resources/boss2.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setImage ( image);
        refresh(modelToViewBoss,  scaleW,  scaleH);

    }
    public void refresh (ModelToViewBoss modelToViewBoss, double scaleW, double scaleH) {
        setTranslateX( modelToViewBoss.getLocationX()*scaleW);
        setTranslateY( modelToViewBoss.getLocationY()*scaleH);
        setFitWidth( modelToViewBoss.getHitboxWidth()*scaleW);
        setFitHeight( modelToViewBoss.getHitboxHeight()*scaleH);
    }
}
