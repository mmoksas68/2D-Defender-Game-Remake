package org.openjfx.view.gameSceneView.preBossSceneView.meteor;

import javafx.scene.CacheHint;
import javafx.scene.image.ImageView;
import org.openjfx.assetManager.Assets;
import org.openjfx.utilization.ModelToViewMeteor;

public class MeteorView extends ImageView {
    public MeteorView(ModelToViewMeteor modelToViewMeteor, double viewLeft, double scaleW, double scaleH) {
        Assets assets = Assets.getInstance();
        setImage(assets.getPreBossAssets().getMeteor());
        setCacheHint(CacheHint.SPEED);
        setCache(true);
        setSmooth(true);
        refresh(modelToViewMeteor,  viewLeft,  scaleW,  scaleH);
        setRotate(-Math.toDegrees(Math.atan(modelToViewMeteor.getDirectionX()/-modelToViewMeteor.getDirectionY())));
    }

    public void refresh(ModelToViewMeteor modelToViewMeteor , double viewLeft, double scaleW, double scaleH){
        setTranslateX((modelToViewMeteor.getLocationX() - viewLeft) * scaleW);
        setTranslateY(modelToViewMeteor.getLocationY() * scaleH);
        setFitHeight(modelToViewMeteor.getHitboxHeight() * scaleH);
        setFitWidth(modelToViewMeteor.getHitboxWidth() * scaleH);
    }
}
