package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.assetManager.Assets;
import org.openjfx.utilization.ModelToViewSpecialAbility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpecialAbilityView extends ImageView {

    public SpecialAbilityView (ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH) {
        Assets assets = Assets.getInstance();
        if( modelToViewSpecialAbility.isLaser() )
            setImage(assets.getBossAssets().getLaser());
        else if( modelToViewSpecialAbility.isMarker())
            setImage(assets.getBossAssets().getMarker());
        else if( modelToViewSpecialAbility.isRocket())
            setImage(assets.getBossAssets().getRocket());
        else if( modelToViewSpecialAbility.isLittleBoss())
            setImage(assets.getBossAssets().getLittleBoss());

        refresh( modelToViewSpecialAbility, scaleW, scaleH);
    }
    public void refresh(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH) {
        setFitWidth( modelToViewSpecialAbility.getHitboxWidth()*scaleW);
        setFitHeight( modelToViewSpecialAbility.getHitboxHeight()*scaleH);
        setTranslateX( modelToViewSpecialAbility.getLocationX()*scaleW);
        setTranslateY(modelToViewSpecialAbility.getLocationY()*scaleH);
    }
}