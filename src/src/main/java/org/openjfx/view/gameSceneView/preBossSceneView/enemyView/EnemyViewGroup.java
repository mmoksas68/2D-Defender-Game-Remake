package org.openjfx.view.gameSceneView.preBossSceneView.enemyView;

import javafx.scene.CacheHint;
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

    public EnemyViewGroup(ModelToViewEnemy modelToViewEnemy, double viewLeft, double scaleW, double scaleH){
        chooseEnemyView(modelToViewEnemy);
        enemyView.setCacheHint(CacheHint.SPEED);
        enemyView.setCache(true);
        enemyView.setSmooth(true);
        healthBar = new Rectangle(enemyView.getFitWidth(), enemyView.getFitHeight()*(1.0/10), Color.RED);
        refresh(modelToViewEnemy,  viewLeft,  scaleW,  scaleH);
    }

    public void refresh(ModelToViewEnemy modelToViewEnemy , double viewLeft, double scaleW, double scaleH){

        enemyView.setRotate(-Math.toDegrees(Math.atan(modelToViewEnemy.getDestinationX()/-modelToViewEnemy.getDestinationY())));
        enemyView.setTranslateX((modelToViewEnemy.getLocationX() - viewLeft) * scaleW);
        enemyView.setTranslateY(modelToViewEnemy.getLocationY() * scaleH);
        enemyView.setFitHeight(modelToViewEnemy.getHitboxHeight() * scaleH);
        enemyView.setFitWidth(modelToViewEnemy.getHitboxWidth() * scaleH);
        healthBar.setHeight(enemyView.getFitHeight()*(0.5/10));
        healthBar.setWidth(((double)modelToViewEnemy.getHealth()/modelToViewEnemy.getMaxHealth()*enemyView.getFitWidth()/2));
        healthBar.setTranslateX((enemyView.getTranslateX() + enemyView.getFitWidth()/4));
        healthBar.setTranslateY((enemyView.getTranslateY() + enemyView.getFitHeight()));
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
        switch (modelToViewEnemy.getType()){
            case tier1unevolved:
                enemyView = new ImageView(assets.getPreBossAssets().getTier1unevolved());
                break;

            case tier1evolved:
                enemyView = new ImageView(assets.getPreBossAssets().getTier1evolved());
            break;

            case tier2unevolved:
                enemyView = new ImageView(assets.getPreBossAssets().getTier2unevolved());
                break;

            case tier2evolved:
                enemyView = new ImageView(assets.getPreBossAssets().getTier2evolved());
                break;

            case tier3unevolved:
                enemyView = new ImageView(assets.getPreBossAssets().getTier3unevolved());
                break;

            case tier3evolved:
                enemyView = new ImageView(assets.getPreBossAssets().getTier3evolved());
                break;

        }

    }

    private void changeDirectionOfView(ModelToViewEnemy modelToViewEnemy){

            double x = modelToViewEnemy.getDestinationX();
            double y = modelToViewEnemy.getDestinationY();
            if(((modelToViewEnemy.getType().equals(EnemyTypes.tier2unevolved) || modelToViewEnemy.getType().equals(EnemyTypes.tier2evolved))
                    &&!modelToViewEnemy.isRushing()) || modelToViewEnemy.getDestination().equals(EnemyDestinations.RandomLocation)){
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
