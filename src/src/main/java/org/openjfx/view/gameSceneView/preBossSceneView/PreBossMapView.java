package org.openjfx.view.gameSceneView.preBossSceneView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.menuEntities.GameSituation;
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

    Image image2 = Assets.getInstance().getPreBossAssets().getBackgrounds().get(GameSituation.getInstance().getLevel() - 1);
    private BackgroundImage image = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);



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
    private DoubleProperty layoutScaleHeight;
    private double sliderAccelerationSpeed = 0;
    private double widthSize;
    private DoubleProperty heightSize;

    public PreBossMapView(double widthSize, double heightSize, boolean isSinglePlayer) {

        this.heightSize = new SimpleDoubleProperty();
        this.widthSize = widthSize;
        this.heightSize.set(heightSize);
        setPrefSize(widthSize, this.heightSize.get());
        layoutScaleWidth = widthSize / 1920.0;
        layoutScaleHeight = new SimpleDoubleProperty();
        layoutScaleHeight.set(this.heightSize.get() / (PreBossMap.MAP_HEIGHT));
        setStyle("-fx-border-color : white; -fx-border-width : 0 0 1 0");
    }

    public PreBossMapView(Node... nodes) {
        super(nodes);
    }

    public void refreshScale(){
        layoutScaleWidth = widthSize / 1920.0;
        layoutScaleHeight.setValue(heightSize.get() / (PreBossMap.MAP_HEIGHT));
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
            enemyViewGroup.refresh(modelToViewEnemy, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
        } else if(!modelToViewEnemy.isDead()){
            enemyViewGroup = new EnemyViewGroup(modelToViewEnemy, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
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
                bullet.refresh(modelToViewBullet, sliderLeft, layoutScaleWidth, layoutScaleHeight.get() );
            }
        } else if(!modelToViewBullet.isDead()){
            bullet = new BulletView(modelToViewBullet, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
            bullets.put(modelToViewBullet.getID(), bullet);
            getChildren().add(bullet);
        }
    }

    public void refreshSpacecraftMain(ModelToViewSpaceCraft modelToViewSpaceCraft){
        SpacecraftViewGroup spacecraftViewGroup;
        if(spacecraftViewGroup1 != null){
           spacecraftViewGroup = spacecraftViewGroup1;
           spacecraftViewGroup.refresh(modelToViewSpaceCraft, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
            if(modelToViewSpaceCraft.isDead()){
                getChildren().remove(spacecraftViewGroup.getFlame());
                getChildren().remove(spacecraftViewGroup.getSpacecraftView());
            }
        } else {
            spacecraftViewGroup = new SpacecraftViewGroup(modelToViewSpaceCraft, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
            spacecraftViewGroup1 = spacecraftViewGroup;
            getChildren().add(spacecraftViewGroup.getSpacecraftView());
            getChildren().add(spacecraftViewGroup.getFlame());
        }
    }
    public void refreshSpacecraftSecondary(ModelToViewSpaceCraft modelToViewSpaceCraft){
        SpacecraftViewGroup spacecraftViewGroup;
        if(spacecraftViewGroup2 != null){
           spacecraftViewGroup = spacecraftViewGroup2;
           spacecraftViewGroup.refresh(modelToViewSpaceCraft, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
            if(modelToViewSpaceCraft.isDead()){
                getChildren().remove(spacecraftViewGroup.getFlame());
                getChildren().remove(spacecraftViewGroup.getSpacecraftView());
            }
        } else {
            spacecraftViewGroup = new SpacecraftViewGroup(modelToViewSpaceCraft, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
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
            enemyStationViewGroup.refresh(modelToViewStation, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
        } else {
            enemyStationViewGroup = new EnemyStationViewGroup(modelToViewStation, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
            getChildren().add(enemyStationViewGroup.getEnemyStationView());
            getChildren().add(enemyStationViewGroup.getHealthBar());
            stations.put(modelToViewStation.getID(), enemyStationViewGroup);
        }
    }

    public void addExplodeAnimation(ModelToView modelToView){
        ExplodeAnimation explodeAnimation = new ExplodeAnimation(modelToView, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
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

    public void refreshBuff(ModelToViewBuff modelToViewBuff) {
        BuffView buffView;
        if(buffs.containsKey(modelToViewBuff.getID())){
            buffView = buffs.get(modelToViewBuff.getID());
            if(modelToViewBuff.isDead()){
                getChildren().remove(buffView);
                buffs.remove(modelToViewBuff.getID());
            }else {
                buffView.refresh(modelToViewBuff, sliderLeft, layoutScaleWidth, layoutScaleHeight.get() );
            }
        } else if(!modelToViewBuff.isDead()){
            buffView = new BuffView(modelToViewBuff, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
            buffs.put(modelToViewBuff.getID(), buffView);
            getChildren().add(buffView);
        }
    }


    public void refreshMeteor(ModelToViewMeteor modelToViewMeteor) {
        MeteorView meteorView;
        if(meteors.containsKey(modelToViewMeteor.getID())){
            meteorView = meteors.get(modelToViewMeteor.getID());
            if(modelToViewMeteor.isDead()){
                getChildren().remove(meteorView);
                meteors.remove(modelToViewMeteor.getID());
            }else {
                meteorView.refresh(modelToViewMeteor, sliderLeft, layoutScaleWidth, layoutScaleHeight.get() );
            }
        } else if(!modelToViewMeteor.isDead()){
            meteorView = new MeteorView(modelToViewMeteor, sliderLeft, layoutScaleWidth, layoutScaleHeight.get());
            meteors.put(modelToViewMeteor.getID(), meteorView);
            getChildren().add(meteorView);
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
        return layoutScaleHeight.get();
    }

    public DoubleProperty getLayoutHeight(){
        return layoutScaleHeight;
    }

    public void setLayoutScaleHeight(double layoutScaleHeight) {
        this.layoutScaleHeight.set(layoutScaleHeight / PreBossMap.MAP_HEIGHT);
        setPrefWidth(layoutScaleHeight);
        //Background background = new Background(image);
      //  this.setBackground(background);
    }

    public double getSliderAccelerationSpeed() {
        return sliderAccelerationSpeed;
    }

    public void setSliderAccelerationSpeed(double sliderAccelerationSpeed) {
        this.sliderAccelerationSpeed = sliderAccelerationSpeed;
    }

    public void setWidthSize(double widthSize){
        this.widthSize = widthSize;
    }

    public void setHeightSize(double heightSize){
        this.heightSize.set(heightSize);
    }

    public DoubleProperty heightSize(){
        return heightSize;
    }


}
