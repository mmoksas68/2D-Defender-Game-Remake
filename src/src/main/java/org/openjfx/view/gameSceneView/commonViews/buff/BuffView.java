package org.openjfx.view.gameSceneView.commonViews.buff;

import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.assetManager.Assets;
import org.openjfx.utilization.ModelToViewBuff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BuffView extends ImageView {
    public BuffView(ModelToViewBuff modelToViewBuff, double viewLeft, double scaleW, double scaleH) {
        Assets assets = Assets.getInstance();
        setImage(assets.getPreBossAssets().getEnemyStation());
        setCacheHint(CacheHint.SPEED);
        setCache(true);
        setSmooth(true);
        refresh(modelToViewBuff,  viewLeft,  scaleW,  scaleH);
    }

    public void refresh(ModelToViewBuff modelToViewBuff , double viewLeft, double scaleW, double scaleH){
        setTranslateX((modelToViewBuff.getLocationX() - viewLeft) * scaleW);
        setTranslateY(modelToViewBuff.getLocationY() * scaleH);
        setFitHeight(modelToViewBuff.getHitboxHeight() * scaleH);
        setFitWidth(modelToViewBuff.getHitboxWidth() * scaleH);
    }
}
