package org.openjfx.view.gameSceneView.commonViews.buff;

import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.assetManager.Assets;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewBuff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BuffView extends ImageView {

    public BuffView(ModelToViewBuff modelToViewBuff, double scaleW, double scaleH){
        Assets assets = Assets.getInstance();
        switch (modelToViewBuff.getType()){
            case SHIELD:
                setImage(assets.getPreBossAssets().getBuffIcons().get(0));
                break;
            case SPEED:
                setImage(assets.getPreBossAssets().getBuffIcons().get(1));
                break;
            case SMART_BOMB:
                setImage(assets.getPreBossAssets().getBuffIcons().get(2));
                break;
            case HEALTH:
                setImage(assets.getPreBossAssets().getBuffIcons().get(3));
                break;
            case HYPER_JUMP:
                setImage(assets.getPreBossAssets().getBuffIcons().get(4));
                break;
            case FIRE_RATE:
                setImage(assets.getPreBossAssets().getBuffIcons().get(5));
                break;
            case GUN_POWER:
                setImage(assets.getPreBossAssets().getBuffIcons().get(6));
                break;
            case GUN_TYPE:
                setImage(assets.getPreBossAssets().getBuffIcons().get(7));
                break;
        }

        setCacheHint(CacheHint.SPEED);
        setCache(true);
        setSmooth(true);
        refresh(modelToViewBuff, scaleW,  scaleH);

    }

    public BuffView(ModelToViewBuff modelToViewBuff, double viewLeft, double scaleW, double scaleH) {
        Assets assets = Assets.getInstance();
        switch (modelToViewBuff.getType()){
            case SHIELD:
                setImage(assets.getPreBossAssets().getBuffIcons().get(0));
                break;
            case SPEED:
                setImage(assets.getPreBossAssets().getBuffIcons().get(1));
                break;
            case SMART_BOMB:
                setImage(assets.getPreBossAssets().getBuffIcons().get(2));
                break;
            case HEALTH:
                setImage(assets.getPreBossAssets().getBuffIcons().get(3));
                break;
            case HYPER_JUMP:
                setImage(assets.getPreBossAssets().getBuffIcons().get(4));
                break;
            case FIRE_RATE:
                setImage(assets.getPreBossAssets().getBuffIcons().get(5));
                break;
            case GUN_POWER:
                setImage(assets.getPreBossAssets().getBuffIcons().get(6));
                break;
            case GUN_TYPE:
                setImage(assets.getPreBossAssets().getBuffIcons().get(7));
                break;
        }

        setCacheHint(CacheHint.SPEED);
        setCache(true);
        setSmooth(true);
        refresh(modelToViewBuff,  viewLeft,  scaleW,  scaleH);
    }

    public void refresh(ModelToViewBuff modelToViewBuff, double scaleW, double scaleH){
        setTranslateX(modelToViewBuff.getLocationX() * scaleW);
        setTranslateY(modelToViewBuff.getLocationY() * scaleH);
        setFitHeight(modelToViewBuff.getHitboxHeight() * scaleH);
        setFitWidth(modelToViewBuff.getHitboxWidth() * scaleH);
    }

    public void refresh(ModelToViewBuff modelToViewBuff , double viewLeft, double scaleW, double scaleH){
        setTranslateX((modelToViewBuff.getLocationX() - viewLeft) * scaleW);
        setTranslateY(modelToViewBuff.getLocationY() * scaleH);
        setFitHeight(modelToViewBuff.getHitboxHeight() * scaleH);
        setFitWidth(modelToViewBuff.getHitboxWidth() * scaleH);
    }
}
