package org.openjfx.view.gameSceneView.bossSceneView;

import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewBoss;
import org.openjfx.utilization.ModelToViewBullet;
import org.openjfx.utilization.ModelToViewSpaceCraft;
import org.openjfx.view.gameSceneView.bossSceneView.bossViews.BossOneView;
import org.openjfx.view.gameSceneView.commonViews.bulletView.BulletView;
import org.openjfx.view.gameSceneView.commonViews.spacecraftView.SpacecraftViewGroup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class BossMapView extends AnchorPane {
    private static BackgroundImage image;
    private java.util.Map<Long, BulletView> bullets = new HashMap<Long, BulletView>();
    private SpacecraftViewGroup spacecraftViewGroup1 = null;
    private SpacecraftViewGroup spacecraftViewGroup2 = null;
    private double layoutScaleWidth;
    private double layoutScaleHeight;

  /*  static {
        try {
            image = new BackgroundImage(new Image(new FileInputStream("assets/images/background.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    private java.util.Map<Long, Node> currentNodes = new HashMap<Long, Node>();
    private java.util.Map<Long, SpacecraftViewGroup> spacecrafts = new HashMap<Long, SpacecraftViewGroup>();
    public BossMapView(double widthSize, double heightSize) {
        setPrefSize(widthSize, heightSize);
        layoutScaleWidth = widthSize / (BossMap.MAP_WIDTH);
        layoutScaleHeight = heightSize / (BossMap.MAP_HEIGHT);
    }

    public void refreshSpacecraftMain(ModelToViewSpaceCraft modelToViewSpaceCraft){
        SpacecraftViewGroup spacecraftViewGroup;
        if(spacecraftViewGroup1 != null){
            spacecraftViewGroup = spacecraftViewGroup1;
            spacecraftViewGroup.refresh(modelToViewSpaceCraft, layoutScaleWidth, layoutScaleHeight);
        } else {
            spacecraftViewGroup = new SpacecraftViewGroup(modelToViewSpaceCraft, layoutScaleWidth, layoutScaleHeight);
            spacecraftViewGroup1 = spacecraftViewGroup;
            getChildren().add(spacecraftViewGroup.getSpacecraftView());
            getChildren().add(spacecraftViewGroup.getFlame());
        }
    }

    public void refreshBullet(ModelToViewBullet modelToViewBullet){
        BulletView bullet;
        if(bullets.containsKey(modelToViewBullet.getID())){
            bullet = bullets.get(modelToViewBullet.getID());
            if(modelToViewBullet.isDead()){
                getChildren().remove(bullet);
                bullets.remove(modelToViewBullet.getID());
            }else {
                bullet.refresh(modelToViewBullet, layoutScaleWidth, layoutScaleHeight );
            }
        } else if(!modelToViewBullet.isDead()){
            bullet = new BulletView(modelToViewBullet , layoutScaleWidth, layoutScaleHeight);
            bullets.put(modelToViewBullet.getID(), bullet);
            getChildren().add(bullet);
        }
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

