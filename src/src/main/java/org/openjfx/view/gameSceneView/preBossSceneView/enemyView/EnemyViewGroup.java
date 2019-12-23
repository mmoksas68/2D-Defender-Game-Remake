package org.openjfx.view.gameSceneView.preBossSceneView.enemyView;

import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.transform.Rotate;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.preBossEntities.Enemy.EnemyDestinations;
import org.openjfx.model.preBossEntities.Enemy.EnemyTypes;
import org.openjfx.utilization.ModelToViewEnemy;

public class EnemyViewGroup {
    private ImageView enemyView;
    private Rectangle healthBar;
    private Assets assets = Assets.getInstance();

    private ImageView evolvedTier1 = new ImageView(assets.getPreBossAssets().getTier1evolved());
    private ImageView unevolvedTier1 = new ImageView(assets.getPreBossAssets().getTier1unevolved());
    private ImageView unevolvedTier2 = new ImageView(assets.getPreBossAssets().getTier2unevolved());
    private ImageView evolvedTier2 = new ImageView(assets.getPreBossAssets().getTier2evolved());
    private ImageView unevolvedTier3 = new ImageView(assets.getPreBossAssets().getTier3unevolved());
    private ImageView evolvedTier3 = new ImageView(assets.getPreBossAssets().getTier3evolved());

    public EnemyViewGroup(ModelToViewEnemy modelToViewEnemy, double viewLeft, double scaleW, double scaleH){
        chooseEnemyView(modelToViewEnemy);
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
        chooseEnemyView(modelToViewEnemy);
        changeDirectionOfView(modelToViewEnemy);
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

    private void chooseEnemyView(ModelToViewEnemy modelToViewEnemy){
        if(modelToViewEnemy.getType().equals(EnemyTypes.tier1unevolved)){
            enemyView = unevolvedTier1;
        }
        else if(modelToViewEnemy.getType().equals(EnemyTypes.tier1evolved)){
            enemyView = evolvedTier1;
        }
        else if(modelToViewEnemy.getType().equals(EnemyTypes.tier2unevolved)){
            enemyView = unevolvedTier2;
        }
        else if(modelToViewEnemy.getType().equals(EnemyTypes.tier2evolved)){
            enemyView = evolvedTier2;
        }
        else if(modelToViewEnemy.getType().equals(EnemyTypes.tier3unevolved)){
            enemyView = unevolvedTier3;
        }
        else if(modelToViewEnemy.getType().equals(EnemyTypes.tier3evolved)){
            enemyView = evolvedTier3;
        }
    }

    private void changeDirectionOfView(ModelToViewEnemy modelToViewEnemy){
        if(modelToViewEnemy.getType().equals(EnemyTypes.tier2unevolved) || modelToViewEnemy.getType().equals(EnemyTypes.tier2evolved)){
            double x = modelToViewEnemy.getDestinationX();
            double y = modelToViewEnemy.getDestinationY();
            if(!modelToViewEnemy.isRushing()){
                x = 0;
                y = 0;
            }
            double angle = Math.atan(x/y);
            Double ang = Double.valueOf(angle);
            if(!ang.isNaN() && !ang.isInfinite()){
                if(ang*180/Math.PI == 0 || ang*180/Math.PI == 90 || ang*180/Math.PI == -90 || ang*180/Math.PI == 180){
                    enemyView.setRotate(90);
                }
                else {
                    enemyView.setRotate(90 - (ang * 180 / Math.PI));
                }
            }
            else{
                enemyView.setRotate(0);
            }
        }
    }

}
