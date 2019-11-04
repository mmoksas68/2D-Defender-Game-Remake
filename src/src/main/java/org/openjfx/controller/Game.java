package org.openjfx.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import org.openjfx.model.Map;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.model.entities.Spacecraft.Spacecraft;
import org.openjfx.utilization.ModelToView;
import org.openjfx.view.MapView;


import java.util.ArrayList;


public class Game {
    private Map map;
    private MapView mapView;
    private Scene scene;
    private double sceneWidth;
    private double sceneHeight;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;
    private double accelerationSpeed = 0;
    private int t=0;

    public Game( double sceneWidth, double sceneHeight){
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        initGame();
    }


    public void initGame(){
        map = new Map(3, sceneWidth, sceneHeight);
        mapView = new MapView();
        mapView.setPrefSize(sceneWidth,sceneHeight);

        refreshMap();

        scene = new Scene(new Group(mapView), sceneWidth, sceneHeight);

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
                if(upPressed && leftPressed){
                    motionFunction(map.getHitboxWidthScale()*(-0.04/1920), (Spacecraft.MAX_PACE/Math.sqrt(2)), -1,1 );
                } else if(upPressed && rightPressed){
                    motionFunction(map.getHitboxWidthScale()*(0.04/1920), (Spacecraft.MAX_PACE/Math.sqrt(2)), 1,1 );
                } else if(downPressed && leftPressed){
                    motionFunction(map.getHitboxWidthScale()*(-0.04/1920), (Spacecraft.MAX_PACE/Math.sqrt(2)), -1,-1 );
                } else if(downPressed && rightPressed){
                    motionFunction(map.getHitboxWidthScale()*(0.04/1920), (Spacecraft.MAX_PACE/Math.sqrt(2)), 1,-1 );
                } else if(upPressed){
                    map.getSpacecraft().moveToDirection(map.getSpacecraft().getVelocity(), 0, 1);
                } else if(downPressed){
                    map.getSpacecraft().moveToDirection(map.getSpacecraft().getVelocity(), 0, -1);
                } else if(leftPressed){
                    motionFunction(map.getHitboxWidthScale()*(-0.04/1920), Spacecraft.MAX_PACE, -1,0 );
                } else if(rightPressed){
                    motionFunction(map.getHitboxWidthScale()*(0.04/1920), Spacecraft.MAX_PACE, 1,0 );
                }

                if(!leftPressed && !rightPressed){
                    accelerationSpeed = 0;
                    if(map.getSpacecraft().getLocation().getPositionX() - map.getViewLeft() > map.getHitboxWidthScale()/2 + 1){
                        if(map.getSpacecraft().getLocation().getPositionX() - map.getViewLeft() > map.getHitboxWidthScale()/2 + 4 ){
                            map.setViewLeft( map.getViewLeft() + map.getHitboxWidthScale()*3/1920 );
                        }else
                            map.setViewLeft(map.getSpacecraft().getLocation().getPositionX() - map.getHitboxWidthScale()/2);
                    }

                    if(map.getSpacecraft().getLocation().getPositionX() - map.getViewLeft() < map.getHitboxWidthScale()/2 - 1){
                        if(map.getSpacecraft().getLocation().getPositionX() - map.getViewLeft() < map.getHitboxWidthScale()/2 - 4){
                            map.setViewLeft( map.getViewLeft() - map.getHitboxWidthScale()*3/1920);
                        }else
                            map.setViewLeft(map.getSpacecraft().getLocation().getPositionX() - map.getHitboxWidthScale()/2);
                    }
                }

                if(leftPressed){
                    if(map.getSpacecraft().getLocation().getPositionX() < map.getViewLeft() + map.getHitboxWidthScale()*(170.0/1920) ){
                        map.getSpacecraft().getLocation().setPositionX(map.getViewLeft() + map.getHitboxWidthScale()*(170.0/1920)) ;
                        accelerationSpeed -= map.getHitboxWidthScale()*(4.0/1920);
                    }
                }

                if(rightPressed){
                    if(map.getSpacecraft().getLocation().getPositionX() > map.getViewLeft() + map.getHitboxWidthScale()*(1750.0/1920) - map.getSpacecraft().getHitBoxWidth() ){
                        map.getSpacecraft().getLocation().setPositionX(map.getViewLeft() + map.getHitboxWidthScale()*(1750.0/1920) - map.getSpacecraft().getHitBoxWidth()) ;
                        accelerationSpeed += map.getHitboxWidthScale()*(4.0/1920);;
                    }
                }

                t %= map.getSpacecraft().getQunFrequency()*30;

                if(spacePressed){
                    if(t == 0)
                    {
                        Bullet bullet = map.getSpacecraft().fireBullet();
                        map.addBullet(bullet);
                    }
                    t++;
                }else{
                    if(t != 0)
                        t++;
                }

                refreshMap();

                for (var bullet : map.getBullets().values()) {
                    bullet.moveToDirection(bullet.getVelocity(), bullet.getDirectionX(), bullet.getDirectionY());
                }
            }
        }.start();

    }

    public void refreshMap(){
        refreshEnemy();
        refreshSpacecraft();
        refreshBullet();
        map.checkMapSituation();
    }

    public void refreshEnemy(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for(var enemy : map.getEnemies().values()){
            if(enemy.isDead()){
                toBeDeleted.add(enemy.getID());
            }
            mapView.refreshEnemy(new ModelToView( enemy, map.getViewLeft(), map.getViewRight()));
        }

        for(var it : toBeDeleted){
            map.deleteEnemy(it);
        }
    }

    public void refreshBullet(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for(var bullet : map.getBullets().values()) {
            if(bullet.isDead()){
                toBeDeleted.add(bullet.getID());
            }
            mapView.refreshBullet(new ModelToView(bullet, map.getViewLeft(), map.getViewRight()));
        }
        for(var it : toBeDeleted){
            map.deleteBullet(it);
        }
    }

    public void refreshSpacecraft(){

        mapView.refreshSpacecraft( new ModelToView(map.getSpacecraft(), map.getViewLeft(), map.getViewRight()));

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

    public double getSceneWidth() {
        return sceneWidth;
    }

    public void setSceneWidth(double sceneWidth) {
        this.sceneWidth = sceneWidth;
        mapView.setPrefSize(sceneWidth,sceneHeight);
        map.setHitboxWidthScale(sceneWidth);
        map.refreshMap();
    }

    public double getSceneHeight() {
        return sceneHeight;
    }

    public void setSceneHeight(double sceneHeight) {
        this.sceneHeight = sceneHeight;
        mapView.setPrefSize(sceneWidth,sceneHeight);
        map.setHitboxHeightScale(sceneHeight);
        map.refreshMap();
    }

    private void motionFunction(double accelerationSpeedChange, double constraint, double directionX, double directionY){
        accelerationSpeed += accelerationSpeedChange;

        if(accelerationSpeed < -constraint )
            accelerationSpeed = -constraint;
        if(accelerationSpeed > constraint)
            accelerationSpeed = constraint;

        map.setViewLeft(map.getViewLeft() + accelerationSpeed);
        map.getSpacecraft().moveToDirection(map.getSpacecraft().getVelocity(), directionX, directionY);
    }
}
