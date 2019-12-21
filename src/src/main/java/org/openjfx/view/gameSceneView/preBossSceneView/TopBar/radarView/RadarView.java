package org.openjfx.view.gameSceneView.preBossSceneView.TopBar.radarView;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.preBossEntities.PreBossMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RadarView extends Pane {
    private HashMap<Long, ImageView> radarObjects = new HashMap<>();
    private List<Rectangle> boundries = new ArrayList<>();
    private double scaleW;
    private double scaleH;
    private double sliderLeft;
    private double sliderLeft2;
    Rectangle rectangle;
    Rectangle rectangle2;

    public RadarView(double width, double height, double sliderLeft, double sliderLeft2) {
        this.sliderLeft = sliderLeft;
        rectangle = new Rectangle(0 , 0, width*0.1920, height); // musab buraya göz atarsan iyi olur
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.WHITE);
        this.getChildren().add(rectangle);
        if(!GameSituation.getInstance().isSinglePlayer() && !GameSituation.getInstance().isTwoPlayerSingleShip()) {
            this.sliderLeft2 = sliderLeft2;
            rectangle2 = new Rectangle(0 , 0, width*0.1920, height); // musab buraya göz atarsan iyi olur
            rectangle2.setFill(Color.TRANSPARENT);
            rectangle2.setStroke(Color.RED);
            this.getChildren().add(rectangle2);
        }
        setMinSize(width, height);
        setPrefSize(width, height);
        scaleW = width / 1920;
        scaleH = height / 1080;
        setStyle("-fx-border-color : white; -fx-border-width : 0 1 1 1;");
    }

    public void refresh(RadarObject obj){
        ImageView imageView = null;
        /*if (obj.getType().equals(RadarTypes.Spacecraft)){
            getChildren().add(rectangle);
        } */
        if(radarObjects.containsKey(obj.getID()) ){
            imageView = radarObjects.get(obj.getID());
            if(obj.isDead()){
                getChildren().remove(imageView);
                radarObjects.remove(obj.getID());
            }else {
                imageView.setFitWidth(25 * scaleW);
                imageView.setFitHeight(25 * scaleH);
                imageView.setTranslateX(obj.getLocation().getPositionX() / PreBossMap.MAP_WIDTH * getPrefWidth());
                imageView.setTranslateY(obj.getLocation().getPositionY() / PreBossMap.MAP_HEIGHT * getPrefHeight());
            }
        } else if(!obj.isDead()){
            if(obj.getType().equals(RadarTypes.Spacecraft))
                imageView = new ImageView(Assets.getInstance().getPreBossAssets().getRadarSpacecraft());
            else if(obj.getType().equals(RadarTypes.Enemy))
                imageView = new ImageView(Assets.getInstance().getPreBossAssets().getRadarEnemy());
            else if(obj.getType().equals(RadarTypes.Station))
                imageView = new ImageView(Assets.getInstance().getPreBossAssets().getRadarEnemySpaceStation());

            imageView.setFitWidth(25*scaleW);
            imageView.setFitHeight(25*scaleH);
            imageView.setTranslateX(obj.getLocation().getPositionX()/ PreBossMap.MAP_WIDTH * getPrefWidth());
            imageView.setTranslateY(obj.getLocation().getPositionY()/ PreBossMap.MAP_HEIGHT * getPrefHeight());
            imageView.setSmooth(true);
            imageView.setCache(true);
            getChildren().add(imageView);
            radarObjects.put(obj.getID(), imageView);
        }

        rectangle.setTranslateX(sliderLeft*0.1920);
        if(!GameSituation.getInstance().isSinglePlayer() && !GameSituation.getInstance().isTwoPlayerSingleShip()){
            rectangle2.setTranslateX(sliderLeft2*0.1920);
        }
        else if(!GameSituation.getInstance().isSinglePlayer() && GameSituation.getInstance().isTwoPlayerSingleShip()){
            if(GameSituation.getInstance().isFirstCraftDied()){
                getChildren().remove(rectangle);
            }
            getChildren().remove(rectangle2);
        }
    }

    public void setSliderLeft(double sliderLeft){
        this.sliderLeft = sliderLeft;
    }

    public void setSliderLeft2(double sliderLeft){
        this.sliderLeft2 = sliderLeft;
    }

}
