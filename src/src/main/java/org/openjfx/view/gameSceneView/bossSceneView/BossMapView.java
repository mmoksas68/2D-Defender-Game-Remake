package org.openjfx.view.gameSceneView.bossSceneView;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.utilization.*;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.*;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.fireAnimation.FireAnimation;
import org.openjfx.view.gameSceneView.bossSceneView.bossViews.BossView;
import org.openjfx.view.gameSceneView.commonViews.bulletView.BulletView;
import org.openjfx.view.gameSceneView.commonViews.spacecraftView.SpacecraftViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class BossMapView extends AnchorPane {
    private static BackgroundImage image;
    private java.util.Map<Long, BulletView> bullets = new HashMap<Long, BulletView>();
    private java.util.Map <Long, ImageView> specialAbilities= new HashMap<>();
    private SpacecraftViewGroup spacecraftViewGroup1 = null;
    private SpacecraftViewGroup spacecraftViewGroup2 = null;
    private java.util.Map<Long, FireAnimation> fireAnimations = new HashMap<Long, FireAnimation>();
    private LaserIndicatorView laserIndicatorView = null;
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
            if(modelToViewSpaceCraft.isDead()){
                getChildren().remove(spacecraftViewGroup.getFlame());
                getChildren().remove(spacecraftViewGroup.getSpacecraftView());
            }
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
        BossView bossView;
        if ( currentNodes.containsKey( modelToViewBoss.getID())) {
            bossView = (BossView) currentNodes.get( modelToViewBoss.getID());
            bossView.refresh( modelToViewBoss, layoutScaleWidth, layoutScaleHeight);
            if(modelToViewBoss.isDead()){
                getChildren().remove(bossView);
            }
        }
        else {
            bossView = new BossView( modelToViewBoss, layoutScaleWidth, layoutScaleHeight);
            currentNodes.put( modelToViewBoss.getID(), bossView);
            getChildren().add( bossView);
        }
    }

    public void refreshSpecialAbilityView( ModelToViewSpecialAbility modelToViewSpecialAbility) {
        SpecialAbilityView specialAbilityView;
        if ( currentNodes.containsKey( modelToViewSpecialAbility.getID())) {
            specialAbilityView = getType( modelToViewSpecialAbility);
            if ( modelToViewSpecialAbility.isDead()) {
                getChildren().remove(specialAbilityView);
                specialAbilities.remove(specialAbilityView);
            } else {
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
    public void addFireAnimation(ModelToViewSpecialAbility modelToView) {
        FireAnimation fireAnimation = new FireAnimation(modelToView, layoutScaleWidth, layoutScaleHeight);
        fireAnimations.put(modelToView.getID(), fireAnimation);
        fireAnimation.setImageViewTimer(1);
        getChildren().add(fireAnimation.getImageViewList()[0]);
    }
    public void refreshFireAnimation(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for(var fireAnimation : fireAnimations.values()){
            fireAnimation.setImageViewTimer(fireAnimation.getImageViewTimer() % fireAnimation.getImageViewPeriod());
            if(fireAnimation.getImageViewTimer() == 0 && fireAnimation.getCurrent() < 7){
                getChildren().remove(fireAnimation.getImageViewList()[fireAnimation.getCurrent()]);
                getChildren().add(fireAnimation.getImageViewList()[fireAnimation.getCurrent()+1]);
                fireAnimation.setCurrent(fireAnimation.getCurrent()+1);
            }
            if(fireAnimation.getCurrent() >= 7){
                toBeDeleted.add(fireAnimation.getID());
                getChildren().remove(fireAnimation.getImageViewList()[7]);
            }
            fireAnimation.setImageViewTimer(fireAnimation.getImageViewTimer()+1);

        }
        for(var currentID : toBeDeleted){
            fireAnimations.remove(currentID);
        }
    }
    public void addLaserIndicator (double [] array) {

        if ( laserIndicatorView == null) {
            laserIndicatorView = new LaserIndicatorView( array, layoutScaleWidth, layoutScaleHeight);
            getChildren().add( laserIndicatorView);
        }
    }
    public void removeLaserIndicator () {
        if ( laserIndicatorView != null) {
            getChildren().remove( laserIndicatorView);
            laserIndicatorView = null;
        }
    }
}

