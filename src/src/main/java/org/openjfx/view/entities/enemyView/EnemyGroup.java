package org.openjfx.view.entities.enemyView;

import javafx.scene.CacheHint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.openjfx.utilization.ModelToViewEnemy;

public class EnemyGroup {
    private EnemyView enemyView;
    private Rectangle healthBar;

    public EnemyGroup(ModelToViewEnemy modelToViewEnemy){
        enemyView = new EnemyView(modelToViewEnemy.getType());
        enemyView.setCacheHint(CacheHint.SPEED);
        enemyView.setCache(true);
        enemyView.setSmooth(true);
        healthBar = new Rectangle(enemyView.getFitWidth(), enemyView.getFitHeight()*(1.0/10), Color.RED);
        refresh(modelToViewEnemy);
    }

    public void refresh(ModelToViewEnemy modelToViewEnemy){
        enemyView.setTranslateX(modelToViewEnemy.getLocationX() - modelToViewEnemy.getCurrentViewLeft());
        enemyView.setTranslateY(modelToViewEnemy.getLocationY());
        enemyView.setFitHeight(modelToViewEnemy.getHitboxHeight());
        enemyView.setFitWidth(modelToViewEnemy.getHitboxWidth());
        healthBar.setHeight(enemyView.getFitHeight()*(0.5/10));
        healthBar.setWidth((double)modelToViewEnemy.getHealth()/modelToViewEnemy.getMaxHealth()*enemyView.getFitWidth()/2);
        healthBar.setTranslateX(enemyView.getTranslateX() + enemyView.getFitWidth()/4);
        healthBar.setTranslateY(enemyView.getTranslateY() + enemyView.getFitHeight());
    }

    public EnemyView getEnemyView() {
        return enemyView;
    }

    public void setEnemyView(EnemyView enemyView) {
        this.enemyView = enemyView;
    }

    public Rectangle getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(Rectangle healthBar) {
        this.healthBar = healthBar;
    }
}
