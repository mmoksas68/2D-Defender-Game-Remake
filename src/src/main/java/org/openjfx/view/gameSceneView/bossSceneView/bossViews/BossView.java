package org.openjfx.view.gameSceneView.bossSceneView.bossViews;

import javafx.scene.image.ImageView;
import org.openjfx.assetManager.Assets;
import org.openjfx.utilization.ModelToViewBoss;

public class BossView extends ImageView {
    public BossView (ModelToViewBoss modelToViewBoss, double scaleW, double scaleH)  {
        Assets assets = Assets.getInstance();
        if( modelToViewBoss.getBossType() == 1)
            setImage(assets.getBossAssets().getBossOne());
        else if( modelToViewBoss.getBossType() == 2)
            setImage(assets.getBossAssets().getBossTwo());
        else if( modelToViewBoss.getBossType() == 3)
            setImage(assets.getBossAssets().getBossThree());

        refresh(modelToViewBoss,  scaleW,  scaleH);
    }
    public void refresh (ModelToViewBoss modelToViewBoss, double scaleW, double scaleH) {
        setTranslateX( modelToViewBoss.getLocationX()*scaleW);
        setTranslateY( modelToViewBoss.getLocationY()*scaleH);
        setFitWidth( modelToViewBoss.getHitboxWidth()*scaleW);
        setFitHeight( modelToViewBoss.getHitboxHeight()*scaleH);
    }
}
