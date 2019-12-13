package org.openjfx.view.entities;

import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.openjfx.utilization.ModelToView;

import org.openjfx.utilization.ModelToViewBuilding;
import org.openjfx.utilization.ModelToViewEnemy;
import org.openjfx.view.entities.buildingView.BuildingGroup;
import org.openjfx.view.entities.bulletView.BulletView;
import org.openjfx.view.entities.radarAllyBuildingView.RadarAllyBuildingView;
import org.openjfx.view.entities.radarSpacecraftView.RadarSpaceCraftView;
;


import java.util.ArrayList;
import java.util.HashMap;

public class RadarMapView extends Pane {


    private java.util.Map<Long, Node> currentNodes = new HashMap<Long, Node>();
    private ArrayList<Rectangle> arr = new ArrayList<Rectangle>();
    private double widthScale = 0.07;
    private double heightScale = 0.12;
    private double height;
    private double currentWidth;

    public RadarMapView(double width, double height){
        this.currentWidth = width*widthScale;
        this.height = height;
        setMaxSize(currentWidth*5,height);
        setStyle("-fx-border-color : white; -fx-border-width : 0 1 0 1");
    }

    @Override
    public void setPrefSize(double v, double v1) {
        super.setPrefSize(v, v1);
    }

    public void refreshSpacecraft(ModelToView modelToView) {
         RadarSpaceCraftView spacecraftRadarView;
        if(currentNodes.containsKey(modelToView.getID())){
            spacecraftRadarView = (RadarSpaceCraftView) currentNodes.get(modelToView.getID());
            spacecraftRadarView.setTranslateX(modelToView.getLocationX()*widthScale);
            spacecraftRadarView.setTranslateY(modelToView.getLocationY()*heightScale);
            spacecraftRadarView.setFitWidth(modelToView.getHitboxWidth()*widthScale);
            spacecraftRadarView.setFitHeight(modelToView.getHitboxHeight()*heightScale);

        } else {
            spacecraftRadarView = new RadarSpaceCraftView();
            spacecraftRadarView.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
            spacecraftRadarView.setTranslateY(modelToView.getLocationY());
            spacecraftRadarView.setFitWidth(modelToView.getHitboxWidth());
            spacecraftRadarView.setFitHeight(modelToView.getHitboxHeight());
            currentNodes.put(modelToView.getID(), spacecraftRadarView);
            getChildren().add(spacecraftRadarView);
        }
        refreshCurrentRect(modelToView);
    }

    public void refreshEnemy(ModelToViewEnemy modelToView){
        BulletView enemyRadarView;
        if(currentNodes.containsKey(modelToView.getID())){
            enemyRadarView = (BulletView) currentNodes.get(modelToView.getID());
            if (modelToView.isDead()){
                getChildren().remove(enemyRadarView);
            }
            else {
                enemyRadarView.setTranslateX(modelToView.getLocationX()*widthScale);
                enemyRadarView.setTranslateY(modelToView.getLocationY()*heightScale);
                enemyRadarView.setFitWidth(modelToView.getHitboxWidth()*widthScale);
                enemyRadarView.setFitHeight(modelToView.getHitboxHeight()*heightScale);
            }
        } else {
            enemyRadarView = new BulletView();
            enemyRadarView.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
            enemyRadarView.setTranslateY(modelToView.getLocationY());
            enemyRadarView.setFitWidth(modelToView.getHitboxWidth());
            enemyRadarView.setFitHeight(modelToView.getHitboxHeight());
            enemyRadarView.setCacheHint(CacheHint.SPEED);
            enemyRadarView.setCache(true);
            enemyRadarView.setSmooth(true);
            currentNodes.put(modelToView.getID(), enemyRadarView);
            getChildren().add(enemyRadarView);
        }
    }

    public void refreshBuilding(ModelToViewBuilding modelToViewBuilding) {
        RadarAllyBuildingView allyBuilding;
        if (currentNodes.containsKey(modelToViewBuilding.getID())) {
            allyBuilding = (RadarAllyBuildingView) currentNodes.get(modelToViewBuilding.getID());
            if (modelToViewBuilding.isDead()) {
                getChildren().remove(allyBuilding);
            }
            else{
                allyBuilding.setFitWidth(modelToViewBuilding.getHitboxWidth()*widthScale);
                allyBuilding.setFitHeight(modelToViewBuilding.getHitboxHeight()*heightScale);
                allyBuilding.setTranslateX(modelToViewBuilding.getLocationX()*widthScale);
                allyBuilding.setTranslateY(modelToViewBuilding.getLocationY()*heightScale);
            }
        } else {
            allyBuilding = new RadarAllyBuildingView();
            allyBuilding.setFitWidth(modelToViewBuilding.getHitboxWidth()*widthScale);
            allyBuilding.setFitHeight(modelToViewBuilding.getHitboxHeight()*heightScale);
            allyBuilding.setTranslateX(modelToViewBuilding.getLocationX() - modelToViewBuilding.getCurrentViewLeft());
            allyBuilding.setTranslateY(modelToViewBuilding.getLocationY());
            currentNodes.put(modelToViewBuilding.getID(), allyBuilding);
            getChildren().add(allyBuilding);
        }
    }

    public void refreshCurrentRect(ModelToView modelToView) {
        Rectangle rect;
        if(arr.size() > 0){
            rect = arr.get(0);
            rect.setTranslateX(modelToView.getCurrentViewLeft()*widthScale);
        } else {
            rect = new Rectangle();
            rect.setFill(Color.TRANSPARENT);
            rect.setStroke(Color.WHITE);
            rect.setHeight(height);
            rect.setWidth(currentWidth);
            rect.setTranslateX(modelToView.getLocationX()*widthScale);
            rect.setTranslateY(modelToView.getLocationY()*heightScale-height/2 +5.5);
            arr.add(rect);
            getChildren().add(rect);
        }
    }

    public void refreshRadarMapView(double hitboxWidthScale, double hitboxHeightScale){
        setMaxSize(hitboxWidthScale*widthScale*5, hitboxHeightScale);
        currentWidth = hitboxWidthScale*widthScale;
        if (arr.size() > 0)
            arr.get(0).setWidth(currentWidth);
    }
}
