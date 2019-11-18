package org.openjfx.view;

import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewBuilding;
import org.openjfx.utilization.ModelToViewEnemy;
import org.openjfx.utilization.ModelToViewSpaceCraft;
import org.openjfx.view.animations.explodeAnimation.ExplodeAnimation;
import org.openjfx.view.entities.bulletView.BulletView;
import org.openjfx.view.entities.enemyView.EnemyGroup;
import org.openjfx.view.entities.enemyView.EnemyView;
import org.openjfx.view.entities.buildingView.BuildingGroup;
import org.openjfx.view.entities.spacecraftView.SpacecraftGroup;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapView extends Pane {

    private static BackgroundImage image;

    static {
        try {
            image = new BackgroundImage(new Image(new FileInputStream("assets/images/background.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private java.util.Map<Long, Node> currentNodes = new HashMap<Long, Node>();
    private java.util.Map<Long, SpacecraftGroup> spacecrafts = new HashMap<Long, SpacecraftGroup>();
    private java.util.Map<Long, BuildingGroup> buildings = new HashMap<Long, BuildingGroup>();
    private java.util.Map<Long, EnemyGroup> enemies = new HashMap<Long, EnemyGroup>();
    private java.util.Map<Long, ExplodeAnimation> explodeAnimations = new HashMap<Long, ExplodeAnimation>();

    public MapView(double widthSize, double heightSize) {
        setPrefSize(widthSize, heightSize);
    }

    public MapView(Node... nodes) {
        super(nodes);
    }

    public void refreshEnemy(ModelToViewEnemy modelToViewEnemy){
        EnemyGroup enemyGroup;
        if(enemies.containsKey(modelToViewEnemy.getID()) ){
            enemyGroup = enemies.get(modelToViewEnemy.getID());
            if(modelToViewEnemy.isDead()){
                getChildren().remove(enemyGroup.getEnemyView());
                getChildren().remove(enemyGroup.getHealthBar());
            }else
            enemyGroup.refresh(modelToViewEnemy);
        } else if(!modelToViewEnemy.isDead()){
            enemyGroup = new EnemyGroup(modelToViewEnemy);
            getChildren().add(enemyGroup.getEnemyView());
            getChildren().add(enemyGroup.getHealthBar());
            enemies.put(modelToViewEnemy.getID(), enemyGroup);
        }

    }

    public void refreshBullet(ModelToView modelToView){
        BulletView bullet;
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
        }
    }

    public void refreshSpacecraft(ModelToViewSpaceCraft modelToViewSpaceCraft){
        SpacecraftGroup spacecraftGroup;

        if(spacecrafts.containsKey(modelToViewSpaceCraft.getID()) ){
           spacecraftGroup = spacecrafts.get(modelToViewSpaceCraft.getID());
           spacecraftGroup.refresh(modelToViewSpaceCraft);
        } else {
            spacecraftGroup = new SpacecraftGroup(modelToViewSpaceCraft);
            getChildren().add(spacecraftGroup.getSpacecraftView());
            getChildren().add(spacecraftGroup.getFlame());
            getChildren().add(spacecraftGroup.getHealthBar());
            spacecrafts.put(modelToViewSpaceCraft.getID(), spacecraftGroup);
        }
    }

    public void refreshBuilding(ModelToViewBuilding modelToViewBuilding){
        BuildingGroup buildingGroup;
        if(buildings.containsKey(modelToViewBuilding.getID()) ){
            buildingGroup = buildings.get(modelToViewBuilding.getID());
            if(modelToViewBuilding.isDead()){
                getChildren().remove(buildingGroup.getBuildingView());
                getChildren().remove(buildingGroup.getHealthBar());
            }else
            buildingGroup.refresh(modelToViewBuilding);
        } else {
            buildingGroup = new BuildingGroup(modelToViewBuilding);
            getChildren().add(buildingGroup.getBuildingView());
            getChildren().add(buildingGroup.getHealthBar());
            buildings.put(modelToViewBuilding.getID(), buildingGroup);
        }
    }

    public void addExplodeAnimation(ModelToView modelToView){
        ExplodeAnimation explodeAnimation = new ExplodeAnimation(modelToView);
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

    public Map<Long, Node> getCurrentNodes() {
        return currentNodes;
    }

    public void setCurrentNodes(Map<Long, Node> currentNodes) {
        this.currentNodes = currentNodes;
    }

    @Override
    public void setPrefSize(double v, double v1) {
        super.setPrefSize(v, v1);
        Background background = new Background(image);
        this.setBackground(background);
    }

    public Map<Long, SpacecraftGroup> getSpacecrafts() {
        return spacecrafts;
    }

    public void setSpacecrafts(Map<Long, SpacecraftGroup> spacecrafts) {
        this.spacecrafts = spacecrafts;
    }

    public Map<Long, ExplodeAnimation> getExplodeAnimations() {
        return explodeAnimations;
    }

    public void setExplodeAnimations(Map<Long, ExplodeAnimation> explodeAnimations) {
        this.explodeAnimations = explodeAnimations;
    }
}
