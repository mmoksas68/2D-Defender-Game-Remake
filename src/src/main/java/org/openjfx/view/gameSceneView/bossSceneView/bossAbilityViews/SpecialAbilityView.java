package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.utilization.ModelToViewSpecialAbility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpecialAbilityView extends ImageView {

    public SpecialAbilityView (ModelToViewSpecialAbility modelToViewSpecialAbility, String resource, double scaleW, double scaleH) {
        Image image = null;
        try {
            image = new Image( new FileInputStream(resource));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setImage( image);
       refresh( modelToViewSpecialAbility, scaleW, scaleH);
    }
    public void refresh(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH) {
        setFitWidth( modelToViewSpecialAbility.getHitboxWidth()*scaleW);
        setFitHeight( modelToViewSpecialAbility.getHitboxHeight()*scaleH);
        setTranslateX( modelToViewSpecialAbility.getLocationX()*scaleW);
        setTranslateY(modelToViewSpecialAbility.getLocationY()*scaleH);
    }
}
