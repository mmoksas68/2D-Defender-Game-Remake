package org.openjfx.controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.util.Duration;
import org.openjfx.model.PreBossMap;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.model.entities.Enemy.Tier1;
import org.openjfx.model.entities.Spacecraft.Spacecraft;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewSpaceCraft;
import org.openjfx.view.MapView;


import java.util.ArrayList;


public class PreBossGameController {
    private PreBossMap preBossMap;
    private MapView mapView;
    private Scene scene;
    private double sceneWidth;
    private double sceneHeight;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;
    private double sliderAccelerationSpeed = 0;
    private boolean gameOn = true;
    private boolean gameOnChange = false;
    private boolean isGameFinished = false;
    private boolean isGameSuccessful = false;

    private AnimationTimer animationTimer = new AnimationTimer(){
        @Override
        public void handle(long l) {
            if(upPressed && leftPressed){
                motionFunction(preBossMap.getHitboxWidthScale()*(-0.04/1920), (Spacecraft.MAX_PACE/Math.sqrt(2)), -1,1 );
            } else if(upPressed && rightPressed){
                motionFunction(preBossMap.getHitboxWidthScale()*(0.04/1920), (Spacecraft.MAX_PACE/Math.sqrt(2)), 1,1 );
            } else if(downPressed && leftPressed){
                motionFunction(preBossMap.getHitboxWidthScale()*(-0.04/1920), (Spacecraft.MAX_PACE/Math.sqrt(2)), -1,-1 );
            } else if(downPressed && rightPressed){
                motionFunction(preBossMap.getHitboxWidthScale()*(0.04/1920), (Spacecraft.MAX_PACE/Math.sqrt(2)), 1,-1 );
            } else if(upPressed){
                preBossMap.getSpacecraft().moveToDirection(preBossMap.getSpacecraft().getVelocity(), 0, 1);
            } else if(downPressed){
                preBossMap.getSpacecraft().moveToDirection(preBossMap.getSpacecraft().getVelocity(), 0, -1);
            } else if(leftPressed){
                motionFunction(preBossMap.getHitboxWidthScale()*(-0.04/1920), Spacecraft.MAX_PACE, -1,0 );
            } else if(rightPressed){
                motionFunction(preBossMap.getHitboxWidthScale()*(0.04/1920), Spacecraft.MAX_PACE, 1,0 );
            }

            if(!leftPressed && !rightPressed){
                sliderAccelerationSpeed = 0;
                if(preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getViewLeft() > preBossMap.getHitboxWidthScale()/2 + 1){
                    if(preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getViewLeft() > preBossMap.getHitboxWidthScale()/2 + 4 ){
                        preBossMap.setViewLeft( preBossMap.getViewLeft() + preBossMap.getHitboxWidthScale()*3/1920 );
                    }else
                        preBossMap.setViewLeft(preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getHitboxWidthScale()/2);
                }

                if(preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getViewLeft() < preBossMap.getHitboxWidthScale()/2 - 1){
                    if(preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getViewLeft() < preBossMap.getHitboxWidthScale()/2 - 4){
                        preBossMap.setViewLeft( preBossMap.getViewLeft() - preBossMap.getHitboxWidthScale()*3/1920);
                    }else
                        preBossMap.setViewLeft(preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getHitboxWidthScale()/2);
                }
            }

            if(leftPressed){
                if(preBossMap.getSpacecraft().getLocation().getPositionX() < preBossMap.getViewLeft() + preBossMap.getHitboxWidthScale()*(170.0/1920) ){
                    preBossMap.getSpacecraft().getLocation().setPositionX(preBossMap.getViewLeft() + preBossMap.getHitboxWidthScale()*(170.0/1920)) ;
                    sliderAccelerationSpeed -= preBossMap.getHitboxWidthScale()*(4.0/1920);
                }
            }

            if(rightPressed){
                if(preBossMap.getSpacecraft().getLocation().getPositionX() > preBossMap.getViewLeft() + preBossMap.getHitboxWidthScale()*(1750.0/1920) - preBossMap.getSpacecraft().getHitBoxWidth() ){
                    preBossMap.getSpacecraft().getLocation().setPositionX(preBossMap.getViewLeft() + preBossMap.getHitboxWidthScale()*(1750.0/1920) - preBossMap.getSpacecraft().getHitBoxWidth()) ;
                    sliderAccelerationSpeed += preBossMap.getHitboxWidthScale()*(4.0/1920);;
                }
            }

            bulletFire(preBossMap.getSpacecraft(), spacePressed);

            refreshMap();

            for (var bullet : preBossMap.getBullets().values()) {
                bullet.moveToDirection(bullet.getVelocity(), bullet.getDirectionX(), bullet.getDirectionY());
            }

        }
    };

    public PreBossGameController(double sceneWidth, double sceneHeight){
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        initGame();
    }


    private void initGame(){
        preBossMap = new PreBossMap(3, sceneWidth, sceneHeight);
        mapView = new MapView(sceneWidth,sceneHeight);

        refreshMap();

        scene = new Scene(mapView, sceneWidth, sceneHeight);

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
                    preBossMap.getSpacecraft().setDirectionLeft(true);
                    break;
                case RIGHT:
                    rightPressed = true;
                    preBossMap.getSpacecraft().setDirectionLeft(false);
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
                case ESCAPE:
                    gameOnChange = true;
                    break;
            }
        });


        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameOnChange){
                    if(gameOn){
                        animationTimer.stop();
                        gameOn = false;
                    }else{
                        animationTimer.start();
                        gameOn = true;
                    }
                    gameOnChange = false;
                }
            }
        }));

        timeline.playFromStart();
        animationTimer.start();

    }

    private void refreshMap(){
        preBossMap.checkMapSituation();

        for( var enemy : preBossMap.getFiringEnemies() ){
            bulletFire(enemy, enemy.getDestinationType().equals("spacecraft"));
        }

        refreshEnemy();
        refreshSpacecraft();
        refreshBullet();
    }

    private void refreshEnemy(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for(var enemy : preBossMap.getEnemies().values()){
            if(enemy.isDead()){
                toBeDeleted.add(enemy.getID());
            }
            mapView.refreshEnemy(new ModelToView( enemy, preBossMap.getViewLeft(), preBossMap.getViewRight()));
        }

        for(var it : toBeDeleted){
            preBossMap.deleteEnemy(it);
        }
    }

    private void refreshBullet(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for(var bullet : preBossMap.getBullets().values()) {
            if(bullet.isDead()){
                toBeDeleted.add(bullet.getID());
            }
            mapView.refreshBullet(new ModelToView(bullet, preBossMap.getViewLeft(), preBossMap.getViewRight()));
        }
        for(var it : toBeDeleted){
            preBossMap.deleteBullet(it);
        }
    }

    private void refreshSpacecraft(){

        mapView.refreshSpacecraft( new ModelToViewSpaceCraft(preBossMap.getSpacecraft(), preBossMap.getViewLeft(), preBossMap.getViewRight()));

    }

    public PreBossMap getPreBossMap() {
        return preBossMap;
    }

    public void setPreBossMap(PreBossMap preBossMap) {
        this.preBossMap = preBossMap;
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
        preBossMap.setHitboxWidthScale(sceneWidth);
        preBossMap.refreshMap();
    }

    public double getSceneHeight() {
        return sceneHeight;
    }

    public void setSceneHeight(double sceneHeight) {
        this.sceneHeight = sceneHeight;
        mapView.setPrefSize(sceneWidth,sceneHeight);
        preBossMap.setHitboxHeightScale(sceneHeight);
        preBossMap.refreshMap();
    }

    private void motionFunction(double accelerationSpeedChange, double constraint, double directionX, double directionY){
        sliderAccelerationSpeed += accelerationSpeedChange;

        if(sliderAccelerationSpeed < -constraint )
            sliderAccelerationSpeed = -constraint;
        if(sliderAccelerationSpeed > constraint)
            sliderAccelerationSpeed = constraint;

        preBossMap.setViewLeft(preBossMap.getViewLeft() + sliderAccelerationSpeed);
        preBossMap.getSpacecraft().moveToDirection(preBossMap.getSpacecraft().getVelocity(), directionX, directionY);
    }

    private <T> void bulletFire(T t, boolean isFiring){
        var current = t instanceof Tier1 ? (Tier1)t : (t instanceof Spacecraft ? (Spacecraft)t : null);

        current.setGunTimer(current.getGunTimer() % current.getGunPeriod());

        if(isFiring){
            if(current.getGunTimer() == 0)
            {
                Bullet bullet = current.fireBullet();
                preBossMap.addBullet(bullet);
            }
            current.setGunTimer(current.getGunTimer() + 1);
        }else if(current.getGunTimer() != 0)
            current.setGunTimer(current.getGunTimer() + 1);

    }
}
