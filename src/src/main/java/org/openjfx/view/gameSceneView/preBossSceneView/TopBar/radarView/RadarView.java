package org.openjfx.view.gameSceneView.preBossSceneView.TopBar.radarView;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.preBossEntities.PreBossMap;

import java.util.HashMap;


public class RadarView extends Pane {

    private HashMap<Long, ImageView> radarObjects = new HashMap<>();
    private double scaleW;
    private double scaleH;

    public RadarView(double width, double height) {
        setMinSize(width, height);
        setPrefSize(width, height);
        scaleW = width / 1920;
        scaleH = height / 1080;
        setStyle("-fx-border-color : white; -fx-border-width : 0 1 1 1");
    }

    public void refresh(RadarObject obj){
        ImageView imageView = null;
        if(radarObjects.containsKey(obj.getID()) ){

            imageView = radarObjects.get(obj.getID());
            if(obj.isDead()){
                getChildren().remove(imageView);
                radarObjects.remove(obj.getID());
            }else
                imageView.setFitWidth(50*scaleW);
                imageView.setFitHeight(50*scaleH);
                imageView.setTranslateX(obj.getLocation().getPositionX()/ PreBossMap.MAP_WIDTH * getPrefWidth());
                imageView.setTranslateY(obj.getLocation().getPositionY()/ PreBossMap.MAP_HEIGHT * getPrefHeight());
        } else if(!obj.isDead()){
            if(obj.getType().equals(RadarTypes.Spacecraft))
                imageView = new ImageView(Assets.getInstance().getPreBossAssets().getSpacecraft());
            else if(obj.getType().equals(RadarTypes.Enemy))
                imageView = new ImageView(Assets.getInstance().getPreBossAssets().getTier1unevolved());
            else if(obj.getType().equals(RadarTypes.Station))
                imageView = new ImageView(Assets.getInstance().getPreBossAssets().getEnemyStation());
            imageView.setFitWidth(50*scaleW);
            imageView.setFitHeight(50*scaleH);
            imageView.setTranslateX(obj.getLocation().getPositionX()/ PreBossMap.MAP_WIDTH * getPrefWidth());
            imageView.setTranslateY(obj.getLocation().getPositionY()/ PreBossMap.MAP_HEIGHT * getPrefHeight());
            imageView.setSmooth(true);
            imageView.setCache(true);
            getChildren().add(imageView);
            radarObjects.put(obj.getID(), imageView);
        }
    }
}
