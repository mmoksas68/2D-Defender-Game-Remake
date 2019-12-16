package org.openjfx.view.gameSceneView.preBossSceneView;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.utilization.*;
import org.openjfx.view.gameSceneView.commonViews.buff.BuffView;
import org.openjfx.view.gameSceneView.commonViews.explodeAnimation.ExplodeAnimation;
import org.openjfx.view.gameSceneView.commonViews.bulletView.BulletView;
import org.openjfx.view.gameSceneView.preBossSceneView.enemyView.EnemyViewGroup;
import org.openjfx.view.gameSceneView.preBossSceneView.meteor.MeteorView;
import org.openjfx.view.gameSceneView.preBossSceneView.stationView.EnemyStationViewGroup;
import org.openjfx.view.gameSceneView.commonViews.spacecraftView.SpacecraftViewGroup;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreBossMapView extends Pane {

    private static BackgroundImage image;

    static {
        try {
            image = new BackgroundImage(new Image(new FileInputStream("assets/images/background.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private java.util.Map<Long, MeteorView> meteors = new HashMap<Long, MeteorView>();
    private java.util.Map<Long, BuffView> buffs = new HashMap<Long, BuffView>();
    private java.util.Map<Long, BulletView> bullets = new HashMap<Long, BulletView>();
    private java.util.Map<Long, EnemyStationViewGroup> stations = new HashMap<Long, EnemyStationViewGroup>();
    private java.util.Map<Long, EnemyViewGroup> enemies = new HashMap<Long, EnemyViewGroup>();
    private java.util.Map<Long, ExplodeAnimation> explodeAnimations = new HashMap<Long, ExplodeAnimation>();
    private SpacecraftViewGroup spacecraftViewGroup1 = null;
    private SpacecraftViewGroup spacecraftViewGroup2 = null;
    private double sliderLeft = 4000;
    private double layoutScaleWidth;
    private double layoutScaleHeight;
    private double sliderAccelerationSpeed = 0;

    public PreBossMapView(double widthSize, double heightSize, boolean isSinglePlayer) {
        setPrefSize(widthSize, heightSize);
        setMinSize(widthSize, heightSize);

        if(isSinglePlayer){
            layoutScaleWidth = widthSize / 1920.0;
            layoutScaleHeight = heightSize / (PreBossMap.MAP_HEIGHT+ 10);
        }else{
            layoutScaleWidth = widthSize / 1920.0;
            layoutScaleHeight = heightSize / (PreBossMap.MAP_HEIGHT+ 10);
        }

        setStyle("-fx-border-color : white; -fx-border-width : 0 0 1 0");
    }

    public PreBossMapView(Node... nodes) {
        super(nodes);
    }

    public void refreshEnemy(ModelToViewEnemy modelToViewEnemy){
        EnemyViewGroup enemyViewGroup;
        if(enemies.containsKey(modelToViewEnemy.getID()) ){
            enemyViewGroup = enemies.get(modelToViewEnemy.getID());
            if(modelToViewEnemy.isDead()){
                getChildren().remove(enemyViewGroup.getEnemyView());
                getChildren().remove(enemyViewGroup.getHealthBar());
                enemies.remove(modelToViewEnemy.getID());
            }else
            enemyViewGroup.refresh(modelToViewEnemy, sliderLeft, layoutScaleWidth, layoutScaleHeight);
        } else if(!modelToViewEnemy.isDead()){
            enemyViewGroup = new EnemyViewGroup(modelToViewEnemy, sliderLeft, layoutScaleWidth, layoutScaleHeight);
            getChildren().add(enemyViewGroup.getEnemyView());
            getChildren().add(enemyViewGroup.getHealthBar());
            enemies.put(modelToViewEnemy.getID(), enemyViewGroup);
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
                bullet.refresh(modelToViewBullet, sliderLeft, layoutScaleWidth, layoutScaleHeight );
            }
        } else if(!modelToViewBullet.isDead()){
            bullet = new BulletView(modelToViewBullet, sliderLeft, layoutScaleWidth, layoutScaleHeight);
            bullets.put(modelToViewBullet.getID(), bullet);
            getChildren().add(bullet);
        }
    }

    public void refreshSpacecraftMain(ModelToViewSpaceCraft modelToViewSpaceCraft){
        SpacecraftViewGroup spacecraftViewGroup;
        if(spacecraftViewGroup1 != null){
           spacecraftViewGroup = spacecraftViewGroup1;
           spacecraftViewGroup.refresh(modelToViewSpaceCraft, sliderLeft, layoutScaleWidth, layoutScaleHeight);
        } else {
            spacecraftViewGroup = new SpacecraftViewGroup(modelToViewSpaceCraft, sliderLeft, layoutScaleWidth, layoutScaleHeight);
            spacecraftViewGroup1 = spacecraftViewGroup;
            getChildren().add(spacecraftViewGroup.getSpacecraftView());
            getChildren().add(spacecraftViewGroup.getFlame());
        }
    }
    public void refreshSpacecraftSecondary(ModelToViewSpaceCraft modelToViewSpaceCraft){
        SpacecraftViewGroup spacecraftViewGroup;
        if(spacecraftViewGroup2 != null){
           spacecraftViewGroup = spacecraftViewGroup2;
           spacecraftViewGroup.refresh(modelToViewSpaceCraft, sliderLeft, layoutScaleWidth, layoutScaleHeight);
        } else {
            spacecraftViewGroup = new SpacecraftViewGroup(modelToViewSpaceCraft, sliderLeft, layoutScaleWidth, layoutScaleHeight);
            spacecraftViewGroup2 = spacecraftViewGroup;
            getChildren().add(spacecraftViewGroup.getSpacecraftView());
            getChildren().add(spacecraftViewGroup.getFlame());
        }
    }



    public void refreshStations(ModelToViewStation modelToViewStation){
        EnemyStationViewGroup enemyStationViewGroup;
        if(stations.containsKey(modelToViewStation.getID()) ){
            enemyStationViewGroup = stations.get(modelToViewStation.getID());
            if(modelToViewStation.isDead()){
                getChildren().remove(enemyStationViewGroup.getEnemyStationView());
                getChildren().remove(enemyStationViewGroup.getHealthBar());
                stations.remove(modelToViewStation.getID());
            }else
            enemyStationViewGroup.refresh(modelToViewStation, sliderLeft, layoutScaleWidth, layoutScaleHeight);
        } else {
            enemyStationViewGroup = new EnemyStationViewGroup(modelToViewStation, sliderLeft, layoutScaleWidth, layoutScaleHeight);
            getChildren().add(enemyStationViewGroup.getEnemyStationView());
            getChildren().add(enemyStationViewGroup.getHealthBar());
            stations.put(modelToViewStation.getID(), enemyStationViewGroup);
        }
    }

    public void addExplodeAnimation(ModelToView modelToView){
        ExplodeAnimation explodeAnimation = new ExplodeAnimation(modelToView, sliderLeft, layoutScaleWidth, layoutScaleHeight);
        explodeAnimations.put(modelToView.getID(), explodeAnimation);
        explodeAnimation.setImageViewTimer(1);
        getChildren().add(explodeAnimation.getImageViewList()[0]);
    }

    public void refreshExplodeAnimations(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for(var explodeAnimation : getExplodeAnimations().values()){
            explodeAnimation.setImageViewTimer(explodeAnimation.getImageViewTimer() % explodeAnimation.getImageViewPeriod());
            if(explodeAnimation.getImageViewTimer() == 0 && explodeAnimation.getCurrent() < 10){
                getChildren().remove(explodeAnimation.getImageViewList()[explodeAnimation.getCurrent()]);
                getChildren().add(explodeAnimation.getImageViewList()[explodeAnimation.getCurrent()+1]);
                explodeAnimation.setCurrent(explodeAnimation.getCurrent()+1);
            }
            if(explodeAnimation.getCurrent() >= 10){
                toBeDeleted.add(explodeAnimation.getID());
                getChildren().remove(explodeAnimation.getImageViewList()[10]);
            }
            explodeAnimation.setImageViewTimer(explodeAnimation.getImageViewTimer()+1);

        }
        for(var currentID : toBeDeleted){
            getExplodeAnimations().remove(currentID);
        }
    }

    /*@Override
    public void setPrefSize(double v, double v1) {
        super.setPrefSize(v, v1);
        Background background = new Background(image);
        this.setBackground(background);
    }*/

    public Map<Long, MeteorView> getMeteors() {
        return meteors;
    }

    public void setMeteors(Map<Long, MeteorView> meteors) {
        this.meteors = meteors;
    }

    public Map<Long, BuffView> getBuffs() {
        return buffs;
    }

    public void setBuffs(Map<Long, BuffView> buffs) {
        this.buffs = buffs;
    }

    public Map<Long, BulletView> getBullets() {
        return bullets;
    }

    public void setBullets(Map<Long, BulletView> bullets) {
        this.bullets = bullets;
    }

    public Map<Long, EnemyStationViewGroup> getStations() {
        return stations;
    }

    public void setStations(Map<Long, EnemyStationViewGroup> stations) {
        this.stations = stations;
    }

    public Map<Long, EnemyViewGroup> getEnemies() {
        return enemies;
    }

    public void setEnemies(Map<Long, EnemyViewGroup> enemies) {
        this.enemies = enemies;
    }

    public Map<Long, ExplodeAnimation> getExplodeAnimations() {
        return explodeAnimations;
    }

    public void setExplodeAnimations(Map<Long, ExplodeAnimation> explodeAnimations) {
        this.explodeAnimations = explodeAnimations;
    }

    public SpacecraftViewGroup getSpacecraftViewGroup1() {
        return spacecraftViewGroup1;
    }

    public void setSpacecraftViewGroup1(SpacecraftViewGroup spacecraftViewGroup1) {
        this.spacecraftViewGroup1 = spacecraftViewGroup1;
    }

    public SpacecraftViewGroup getSpacecraftViewGroup2() {
        return spacecraftViewGroup2;
    }

    public void setSpacecraftViewGroup2(SpacecraftViewGroup spacecraftViewGroup2) {
        this.spacecraftViewGroup2 = spacecraftViewGroup2;
    }

    public double getSliderLeft() {
        return sliderLeft;
    }

    public void setSliderLeft(double sliderLeft) {
        this.sliderLeft = sliderLeft;
    }

    public double getLayoutScaleWidth() {
        return layoutScaleWidth;
    }

    public void setLayoutScaleWidth(double layoutScaleWidth) {
        this.layoutScaleWidth = layoutScaleWidth / 1920;
    }

    public double getLayoutScaleHeight() {
        return layoutScaleHeight;
    }

    public void setLayoutScaleHeight(double layoutScaleHeight) {
        this.layoutScaleHeight = layoutScaleHeight / 1080;
        setPrefWidth(layoutScaleHeight);
        Background background = new Background(image);
        this.setBackground(background);
    }

    public double getSliderAccelerationSpeed() {
        return sliderAccelerationSpeed;
    }

    public void setSliderAccelerationSpeed(double sliderAccelerationSpeed) {
        this.sliderAccelerationSpeed = sliderAccelerationSpeed;
    }
}
