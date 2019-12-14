package org.openjfx.controller.bossSceneController;


import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.openjfx.controller.SoundController;
import org.openjfx.model.Boss.BossMap;
import org.openjfx.model.GameBar;
import org.openjfx.model.entities.Boss.Boss;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.model.entities.Spacecraft.Spacecraft;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewBoss;
import org.openjfx.utilization.ModelToViewSpaceCraft;
import org.openjfx.view.BossMapView;
import org.openjfx.view.GameBarView;

import java.util.ArrayList;

public class BossGameController  {
    private GameBar gameBar;
    private GameBarView gameBarView;
    private double rateGameBar = 0.12;
    private double rateMap = 0.91;
    private BossMap bossMap;
    private BossMapView bossMapView;
    private Scene scene;
    private double  moveleft = 0, moveright = 0;
    private double  moveup = 0, movedown = 0;
    private boolean spacePressed = false;
    private BooleanProperty gameOnChange = new SimpleBooleanProperty(false);
    private boolean gameOn = true;
    private double sceneWidth;
    private double sceneHeight;
    private BossController bossController;
    private double sliderAccelerationSpeed = 0;
    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {

            double [] boundArray = { moveright, moveleft, moveup, movedown};
            bossMap.checkBoundry( boundArray, bossMap.getSpacecraft());

            double xDirection = boundArray [0] + boundArray [1]; // moveright + moveleft
            double yDirection = boundArray [2] + boundArray[3];  // moveup + movedown
            double multiplier = 1 / Math.sqrt( Math.pow( xDirection,2) + Math.pow( yDirection, 2));
            if ( xDirection != 0 || yDirection != 0) {
                bossMap.getSpacecraft().moveToDirection(bossMap.getSpacecraft().getVelocity(),xDirection * multiplier, yDirection * multiplier);
            }
            bulletFire( bossMap.getSpacecraft(), spacePressed);
            bossController.behave();
            for (var bullet : bossMap.getBullets().values()) {
                bullet.moveToDirection(bullet.getVelocity(), bullet.getDirectionX(), bullet.getDirectionY());
            }
            refreshMap();
          //  bossMapView.refreshExplodeAnimations();
        }
    };

    public BossGameController(Scene newScene, double sceneWidth, double sceneHeight) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        scene = newScene;
        initGame();
    }
    private void initGame() {
        gameBar = new GameBar(sceneWidth, sceneHeight*rateGameBar);
        gameBarView = new GameBarView(gameBar.getHitboxWidthScale(), gameBar.getHitboxHeightScale());
        bossMap = new BossMap(1, sceneWidth, sceneHeight*rateMap);
        bossMapView = new BossMapView (sceneWidth,sceneHeight*rateMap);
        bossController = new BossController(1, bossMap, bossMapView);

        refreshMap();
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(gameBarView);
        rootPane.setCenter(bossMapView);
        scene.setRoot(rootPane);
        //scene = new Scene(rootPane, sceneWidth, sceneHeight);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    moveup = 1;
                    break;
                case DOWN:
                    movedown = -1;
                    break;
                case LEFT:
                    moveleft = -1;
                    break;
                case RIGHT:
                    moveright = 1;
                    break;
                case SPACE:
                    spacePressed = true;
                    break;
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {

                case UP:
                    moveup = 0;
                    break;
                case DOWN:
                    movedown = 0;
                    break;
                case LEFT:
                    moveleft = 0;
                    break;
                case RIGHT:
                    moveright = 0;
                    break;
                case SPACE:
                    spacePressed = false;
                    break;
                case ESCAPE:
                    gameOnChange.set(true);
                    break;
            }
        });

        animationTimer.start();
    }
    private <T> void bulletFire(T t, boolean isFiring) {


        var current = t instanceof Boss ? (Boss) t : (t instanceof Spacecraft ? (Spacecraft) t : null);

        current.setGunTimer(current.getGunTimer() % current.getGunPeriod());

        if (isFiring) {
            if (current.getGunTimer() == 0) {
                Bullet bullet = current.fireBullet();
                bossMap.addBullet(bullet);
                if (current instanceof Spacecraft) {
                    SoundController.fireBullet();
                    System.out.println( bossMap.getBoss().getLocation().getPositionY());
                }
            }
            current.setGunTimer(current.getGunTimer() + 1);
        } else if (current.getGunTimer() != 0)
            current.setGunTimer(current.getGunTimer() + 1);

    }

    private void refreshMap() {
        refreshSpacecraft();
        refreshBullet();
        refreshBoss();
    }
    private void refreshBoss() {
        bossMapView.refreshBossView( new ModelToViewBoss( bossMap.getBoss()));
    }
    private void refreshSpacecraft() {
        boolean flame = moveleft + moveright != 0;
        bossMapView.refreshSpacecraft( new ModelToViewSpaceCraft(bossMap.getSpacecraft(), bossMap.getViewLeft(), bossMap.getViewRight(), flame));
        gameBarView.refreshSpacecraft(new ModelToView(bossMap.getSpacecraft(), bossMap.getViewLeft(), bossMap.getViewRight()));
    }
    private void refreshBullet() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var bullet : bossMap.getBullets().values()) {
            if (bullet.isDead()) {
                toBeDeleted.add(bullet.getID());
            }
            bossMapView.refreshBullet(new ModelToView(bullet, bossMap.getViewLeft(), bossMap.getViewRight()));

        }
        for (var it : toBeDeleted) {
            bossMap.deleteBullet(it);
        }
    }

    public Scene getScene() {
        return scene;
    }
    public void setSceneWidth(double sceneWidth) {
        this.sceneWidth = sceneWidth;

        bossMapView.setPrefSize(sceneWidth,sceneHeight*rateMap);
        gameBarView.setPrefSize(sceneWidth, sceneHeight*rateGameBar);
        BossMap.setHitboxWidthScale(sceneWidth);
        gameBar.setHitboxWidthScale(sceneWidth);
        bossMap.refreshMap();
        gameBar.refreshGameBar();
        gameBarView.refreshGameBarView(sceneWidth, sceneHeight*rateGameBar);
    }
    public void setSceneHeight(double sceneHeight) {

        this.sceneHeight = sceneHeight;
        bossMapView.setPrefSize(sceneWidth,sceneHeight*rateMap);
        gameBarView.setPrefSize(sceneWidth, sceneHeight*rateGameBar);
        BossMap.setHitboxHeightScale(sceneHeight*rateMap);
        gameBar.setHitboxHeightScale(sceneHeight*rateGameBar);
        bossMap.refreshMap();
        gameBar.refreshGameBar();
        gameBarView.refreshGameBarView(sceneWidth, sceneHeight*rateGameBar);
    }
  /*  private void motionFunction(double accelerationSpeedChange, double constraint, double directionX, double directionY) {
        sliderAccelerationSpeed += accelerationSpeedChange;

        if (sliderAccelerationSpeed < -constraint)
            sliderAccelerationSpeed = -constraint;
        if (sliderAccelerationSpeed > constraint)
            sliderAccelerationSpeed = constraint;

        bossMap.setViewLeft(bossMap.getViewLeft() + sliderAccelerationSpeed);
        bossMap.getSpacecraft().moveToDirection(bossMap.getSpacecraft().getVelocity(), directionX, directionY);
    }*/
    public BooleanProperty gameOnChangeProperty() {
        return gameOnChange;
    }
    public boolean isGameOn() {
        return gameOn;
    }
    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }
    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }
    public void setGameOnChange(boolean gameOnChange) {
        this.gameOnChange.set(gameOnChange);
    }

    public BooleanProperty getGameOnChange() {
        return gameOnChange;
    }
}