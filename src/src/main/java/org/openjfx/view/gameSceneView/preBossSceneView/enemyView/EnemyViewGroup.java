package org.openjfx.view.gameSceneView.preBossSceneView.enemyView;

import javafx.scene.CacheHint;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.openjfx.assetManager.Assets;
import org.openjfx.utilization.ModelToViewEnemy;

public class EnemyViewGroup {
    private ImageView enemyView;
    private Rectangle healthBar;

    public EnemyViewGroup(ModelToViewEnemy modelToViewEnemy, double viewLeft, double scaleW, double scaleH){
        Assets assets = Assets.getInstance();
        enemyView = new ImageView(assets.getPreBossAssets().getTier1unevolved());
        enemyView.setCacheHint(CacheHint.SPEED);
        enemyView.setCache(true);
        enemyView.setSmooth(true);
        healthBar = new Rectangle(enemyView.getFitWidth(), enemyView.getFitHeight()*(1.0/10), Color.RED);
        refresh(modelToViewEnemy,  viewLeft,  scaleW,  scaleH);
    }

    public void refresh(ModelToViewEnemy modelToViewEnemy , double viewLeft, double scaleW, double scaleH){
        enemyView.setTranslateX((modelToViewEnemy.getLocationX() - viewLeft) * scaleW);
        enemyView.setTranslateY(modelToViewEnemy.getLocationY() * scaleH);
        enemyView.setFitHeight(modelToViewEnemy.getHitboxHeight() * scaleH);
        enemyView.setFitWidth(modelToViewEnemy.getHitboxWidth() * scaleH);
        healthBar.setHeight(enemyView.getFitHeight()*(0.5/10));
        healthBar.setWidth(((double)modelToViewEnemy.getHealth()/modelToViewEnemy.getMaxHealth()*enemyView.getFitWidth()/2));
        healthBar.setTranslateX((enemyView.getTranslateX() + enemyView.getFitWidth()/4));
        healthBar.setTranslateY((enemyView.getTranslateY() + enemyView.getFitHeight()));
    }

    public ImageView getEnemyView() {
        return enemyView;
    }

    public void setEnemyView(ImageView enemyView) {
        this.enemyView = enemyView;
    }

    public Rectangle getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(Rectangle healthBar) {
        this.healthBar = healthBar;
    }

}
