package org.openjfx.view.gameSceneView.bossSceneView;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.openjfx.model.bossEntities.BossAbility.LittleBoss;
import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.utilization.*;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.*;
import org.openjfx.view.gameSceneView.bossSceneView.bossViews.BossOneView;
import org.openjfx.view.gameSceneView.commonViews.bulletView.BulletView;
import org.openjfx.view.gameSceneView.commonViews.spacecraftView.SpacecraftViewGroup;

import java.util.HashMap;

public class BossMapView extends AnchorPane {
    private static BackgroundImage image;
    private java.util.Map<Long, BulletView> bullets = new HashMap<Long, BulletView>();
    private java.util.Map <Long, ImageView> specialAbilities= new HashMap<>();
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

    public void refreshSpacecraftSecondary(ModelToViewSpaceCraft modelToViewSpaceCraft){
        SpacecraftViewGroup spacecraftViewGroup;
        if(spacecraftViewGroup2 != null){
            spacecraftViewGroup = spacecraftViewGroup2;
            spacecraftViewGroup.refresh(modelToViewSpaceCraft, layoutScaleWidth, layoutScaleHeight);
            if(modelToViewSpaceCraft.isDead()){
                getChildren().remove(spacecraftViewGroup.getFlame());
                getChildren().remove(spacecraftViewGroup.getSpacecraftView());
            }
        } else {
            spacecraftViewGroup = new SpacecraftViewGroup(modelToViewSpaceCraft, layoutScaleWidth, layoutScaleHeight);
            spacecraftViewGroup2 = spacecraftViewGroup;
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
            bossOneView.refresh( modelToViewBoss, layoutScaleWidth, layoutScaleHeight);
        }
        else {
            bossOneView = new BossOneView( modelToViewBoss, layoutScaleWidth, layoutScaleHeight);
            currentNodes.put( modelToViewBoss.getID(), bossOneView);
            getChildren().add( bossOneView);
        }
    }
    public void refreshSpecialAbilityView( ModelToViewSpecialAbility modelToViewSpecialAbility) {
        SpecialAbilityView specialAbilityView;
        if ( currentNodes.containsKey( modelToViewSpecialAbility.getID())) {
            specialAbilityView = getType( modelToViewSpecialAbility);
            if ( modelToViewSpecialAbility.isDead()) {
                getChildren().remove( specialAbilityView);
                specialAbilities.remove( specialAbilityView);
            }else {
                specialAbilityView.refresh(modelToViewSpecialAbility, layoutScaleWidth, layoutScaleHeight);
            }
        }
        else {
            specialAbilityView = createView( modelToViewSpecialAbility);
            currentNodes.put( modelToViewSpecialAbility.getID(), specialAbilityView);
            getChildren().add( specialAbilityView);
        }
    }
    private SpecialAbilityView getType(ModelToViewSpecialAbility modelToViewSpecialAbility) {
        if ( modelToViewSpecialAbility.isLaser())
            return (LaserView) currentNodes.get( modelToViewSpecialAbility.getID() );
        else  if ( modelToViewSpecialAbility.isMarker())
            return (MarkerView) currentNodes.get( modelToViewSpecialAbility.getID());
        else if ( modelToViewSpecialAbility.isRocket())
            return (RocketView) currentNodes.get( modelToViewSpecialAbility.getID());
        else if ( modelToViewSpecialAbility.isLittleBoss())
            return  (LittleBossView) currentNodes.get( modelToViewSpecialAbility.getID());

        return null;
    }
    private SpecialAbilityView createView (ModelToViewSpecialAbility modelToViewSpecialAbility) {
        if ( modelToViewSpecialAbility.isLaser())
            return new LaserView( modelToViewSpecialAbility, layoutScaleWidth, layoutScaleHeight);
        else  if ( modelToViewSpecialAbility.isMarker())
            return new MarkerView( modelToViewSpecialAbility, layoutScaleWidth, layoutScaleHeight);
        else if ( modelToViewSpecialAbility.isRocket())
               return new RocketView( modelToViewSpecialAbility, layoutScaleWidth, layoutScaleHeight);
        else if ( modelToViewSpecialAbility.isLittleBoss())
            return  new LittleBossView( modelToViewSpecialAbility, layoutScaleWidth, layoutScaleHeight);
        return null;
    }
}

