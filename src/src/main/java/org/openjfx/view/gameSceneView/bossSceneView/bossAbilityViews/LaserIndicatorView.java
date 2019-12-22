package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews;

import javafx.scene.image.ImageView;
import org.openjfx.assetManager.Assets;

import java.io.Serializable;

public class LaserIndicatorView extends ImageView  {

   public LaserIndicatorView (double [] array, double scaleW, double scaleH) {
       Assets assets = Assets.getInstance();
       setImage(assets.getBossAssets().getLaserIndicator());
       setTranslateX( array[0] * scaleW);
       setTranslateY( array [1]*scaleH);
       setFitWidth( array[2]*scaleW);
       setFitHeight( array [3]*scaleH);
    }
}
