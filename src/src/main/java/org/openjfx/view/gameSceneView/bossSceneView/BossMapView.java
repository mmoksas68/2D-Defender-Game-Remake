package org.openjfx.view.gameSceneView.bossSceneView;

import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewBoss;
import org.openjfx.utilization.ModelToViewSpaceCraft;
import org.openjfx.view.gameSceneView.bossSceneView.bossViews.BossOneView;
import org.openjfx.view.gameSceneView.commonViews.bulletView.BulletView;
import org.openjfx.view.gameSceneView.commonViews.spacecraftView.SpacecraftViewGroup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class BossMapView extends AnchorPane {
    private static BackgroundImage image;

    static {
        try {
            image = new BackgroundImage(new Image(new FileInputStream("assets/images/background.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private java.util.Map<Long, Node> currentNodes = new HashMap<Long, Node>();
    private java.util.Map<Long, SpacecraftViewGroup> spacecrafts = new HashMap<Long, SpacecraftViewGroup>();
    public BossMapView(double widthSize, double heightSize) {
        setPrefSize(widthSize, heightSize);
    }

    public void refreshSpacecraft(ModelToViewSpaceCraft modelToViewSpaceCraft){
        /*SpacecraftViewGroup spacecraftViewGroup;

        if(spacecrafts.containsKey(modelToViewSpaceCraft.getID()) ){
            spacecraftViewGroup = spacecrafts.get(modelToViewSpaceCraft.getID());
            spacecraftViewGroup.refresh(modelToViewSpaceCraft);
        } else {
            spacecraftViewGroup = new SpacecraftViewGroup(modelToViewSpaceCraft);
            getChildren().add(spacecraftViewGroup.getSpacecraftView());
            getChildren().add(spacecraftViewGroup.getFlame());
            getChildren().add(spacecraftViewGroup.getHealthBar());
            spacecrafts.put(modelToViewSpaceCraft.getID(), spacecraftViewGroup);
        }*/
    }

    public void refreshBullet(ModelToView modelToView){
       /* BulletView bullet;
        if(currentNodes.containsKey(modelToView.getID())){
            bullet = (BulletView) currentNodes.get(modelToView.getID());
            if(modelToView.isDead()){
                getChildren().remove(bullet);
            }else {
                bullet.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
                bullet.setTranslateY(modelToView.getLocationY());
                bullet.setFitHeight(modelToView.getHitboxHeight());
                bullet.setFitWidth(modelToView.getHitboxWidth());
                if(bullet.getTranslateX() < -500 || bullet.getTranslateX() > 2300)
                    bullet.setVisible(false);
                else
                    bullet.setVisible(true);

            }

        } else if(!modelToView.isDead()){
            bullet = new BulletView();
            bullet.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
            bullet.setTranslateY(modelToView.getLocationY());
            bullet.setFitHeight(modelToView.getHitboxHeight());
            bullet.setFitWidth(modelToView.getHitboxWidth());
            bullet.setCacheHint(CacheHint.SPEED);
            bullet.setCache(true);
            currentNodes.put(modelToView.getID(), bullet);
            getChildren().add(bullet);
        }*/
    }
    public void refreshBossView(ModelToViewBoss modelToViewBoss) {
        BossOneView bossOneView;
        if ( currentNodes.containsKey( modelToViewBoss.getID())) {
            bossOneView = (BossOneView) currentNodes.get( modelToViewBoss.getID());
            bossOneView.refresh( modelToViewBoss);
        }
        else {
            bossOneView = new BossOneView( modelToViewBoss);
            currentNodes.put( modelToViewBoss.getID(), bossOneView);
            getChildren().add( bossOneView);
        }
    }

}

