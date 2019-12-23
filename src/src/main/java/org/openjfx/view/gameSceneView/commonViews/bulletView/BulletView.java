package org.openjfx.view.gameSceneView.commonViews.bulletView;

import javafx.scene.CacheHint;
import javafx.scene.image.ImageView;
import org.openjfx.assetManager.Assets;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewBullet;

public class BulletView extends ImageView {

    public BulletView(ModelToViewBullet modelToViewBullet, double viewLeft, double scaleW, double scaleH) {
        Assets assets = Assets.getInstance();
        setImage(assets.getPreBossAssets().getBullet());
        setCacheHint(CacheHint.SPEED);
        setCache(true);
        setSmooth(true);
        refresh(modelToViewBullet,  viewLeft,  scaleW,  scaleH);
        setRotate(-Math.toDegrees(Math.atan(modelToViewBullet.getDirectionX()/-modelToViewBullet.getDirectionY())) + 90);
    }
    public BulletView (ModelToViewBullet modelToViewBullet, double scaleW, double scaleH) {
        Assets assets = Assets.getInstance();
        setImage(assets.getPreBossAssets().getBullet());
        setCacheHint(CacheHint.SPEED);
        setCache(true);
        setSmooth(true);
        refresh(modelToViewBullet, scaleW,  scaleH);
    }
    public void refresh( ModelToViewBullet modelToViewBullet, double scaleW, double scaleH) {
        setTranslateX((modelToViewBullet.getLocationX()) * scaleW);
        setTranslateY(modelToViewBullet.getLocationY() * scaleH);
        setFitHeight(modelToViewBullet.getHitboxHeight() * scaleH);
        setFitWidth(modelToViewBullet.getHitboxWidth() * scaleH);
    }

    public void refresh(ModelToViewBullet modelToViewBullet , double viewLeft, double scaleW, double scaleH){
        setTranslateX((modelToViewBullet.getLocationX() - viewLeft) * scaleW);
        setTranslateY(modelToViewBullet.getLocationY() * scaleH);
        setFitHeight(modelToViewBullet.getHitboxHeight() * scaleH);
        setFitWidth(modelToViewBullet.getHitboxWidth() * scaleH);
    }

}
