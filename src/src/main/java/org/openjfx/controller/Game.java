package org.openjfx.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import org.openjfx.model.Map;
import org.openjfx.view.MapView;


public class Game {
    private Map map;
    private MapView mapView;
    private Scene scene;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean accelerate = false;
    private boolean spacePressed = false;


    public Game( ){
        initGame();
    }


    public void initGame(){
        map = new Map(3);
        mapView = new MapView();
        mapView.setPrefSize(400,400);

        refreshMap();

        scene = new Scene(mapView, 400, 400);

        scene.setOnKeyPressed( e -> {
            switch (e.getCode()){
                case UP:
                    upPressed = true;
                    break;
                case DOWN:
                    downPressed = true;
                    break;
                case LEFT:
                    leftPressed = true;
                    map.getSpacecraft().setDirectionLeft(true);
                    break;
                case RIGHT:
                    rightPressed = true;
                    map.getSpacecraft().setDirectionLeft(false);
                    break;
                case SPACE:
                    spacePressed = true;
                    break;
            }
        });

        scene.setOnKeyReleased( e -> {
            switch (e.getCode()){

                case UP:
                    upPressed = false;
                    break;
                case DOWN:
                    downPressed = false;
                    break;
                case LEFT:
                    leftPressed = false;
                    break;
                case RIGHT:
                    rightPressed = false;
                    break;
                case SPACE:
                    spacePressed = false;
                    break;
            }
        });

        new AnimationTimer(){

            @Override
            public void handle(long l) {
                for(var iterator : map.getBullets()){
                    iterator.moveUp(iterator.getDirectionY());
                    iterator.moveLeft(iterator.getDirectionX());
                }

                if(upPressed)
                    map.getSpacecraft().moveUp(5);
                if(downPressed)
                    map.getSpacecraft().moveDown(5);
                if(leftPressed)
                    map.getSpacecraft().moveLeft(5);
                if(rightPressed)
                    map.getSpacecraft().moveRight(5);
                if(spacePressed)
                    map.addBullet(map.getSpacecraft().fireBullet());

                refreshMap();
            }
        }.start();
    }

    public void refreshMap(){
        refreshEnemy();
        refreshSpacecraft();
        refreshBullet();
    }

    public void refreshEnemy(){
        for(var iterator : map.getEnemies()){
           mapView.refreshEnemy(iterator.getLocation().getPositionX(), iterator.getLocation().getPositionY(),
                                iterator.getHitBoxWidth(), iterator.getHitBoxHeight(), iterator.getID());
        }
    }

    public void refreshBullet(){
        for(var iterator : map.getBullets()){
            mapView.refreshBullet(iterator.getLocation().getPositionX(), iterator.getLocation().getPositionY(),
                    iterator.getHitBoxWidth(), iterator.getHitBoxHeight(), iterator.getID());
        }
    }

    public void refreshSpacecraft(){

        mapView.refreshSpacecraft(map.getSpacecraft().getLocation().getPositionX(), map.getSpacecraft().getLocation().getPositionY(),
                                  map.getSpacecraft().getHitBoxWidth(), map.getSpacecraft().getHitBoxHeight(), map.getSpacecraft().getID() );

    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
