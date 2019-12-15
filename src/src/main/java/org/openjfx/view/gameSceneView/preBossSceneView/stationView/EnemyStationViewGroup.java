package org.openjfx.view.gameSceneView.preBossSceneView.stationView;

import javafx.scene.CacheHint;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.openjfx.assetManager.Assets;
import org.openjfx.utilization.ModelToViewStation;

public class EnemyStationViewGroup {
    private ImageView enemyStationView;
    private Rectangle healthBar;

    public EnemyStationViewGroup(ModelToViewStation modelToViewStation, double viewLeft, double scaleW, double scaleH){
        Assets assets = Assets.getInstance();
        enemyStationView = new ImageView(assets.getPreBossAssets().getEnemyStation());
        enemyStationView.setCacheHint(CacheHint.SPEED);
        enemyStationView.setCache(true);
        enemyStationView.setSmooth(true);
        healthBar = new Rectangle(enemyStationView.getFitWidth(), enemyStationView.getFitHeight()*(1.0/10), Color.RED);
        refresh(modelToViewStation, viewLeft, scaleW, scaleH);
    }

    public void refresh(ModelToViewStation modelToViewBuilding, double viewLeft, double scaleW, double scaleH){
        enemyStationView.setTranslateX((modelToViewBuilding.getLocationX() - viewLeft) * scaleW);
        enemyStationView.setTranslateY((modelToViewBuilding.getLocationY()) * scaleH);
        enemyStationView.setFitHeight(modelToViewBuilding.getHitboxHeight() * scaleH);
        enemyStationView.setFitWidth(modelToViewBuilding.getHitboxWidth() * scaleW);
        healthBar.setHeight(enemyStationView.getFitHeight()*(1.0/10));
        healthBar.setWidth(((double)modelToViewBuilding.getHealth()/modelToViewBuilding.getMaxHealth()* enemyStationView.getFitWidth()));
        healthBar.setTranslateX(enemyStationView.getTranslateX());
        healthBar.setTranslateY((enemyStationView.getTranslateY() + enemyStationView.getFitHeight()));
    }

    public Rectangle getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(Rectangle healthBar) {
        this.healthBar = healthBar;
    }

    public ImageView getEnemyStationView() {
        return enemyStationView;
    }

    public void setEnemyStationView(ImageView enemyStationView) {
        this.enemyStationView = enemyStationView;
    }
}
