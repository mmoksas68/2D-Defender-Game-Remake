package org.openjfx.controller.preBossControllers;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.openjfx.model.GameBar;
import org.openjfx.model.preBoss.PreBossMap;
import org.openjfx.model.entities.Building.AllyBuilding;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.model.entities.Enemy.Tier1;
import org.openjfx.model.entities.Spacecraft.Spacecraft;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewBuilding;
import org.openjfx.utilization.ModelToViewEnemy;
import org.openjfx.utilization.ModelToViewSpaceCraft;
import org.openjfx.view.MapView;
import org.openjfx.view.GameBarView;
import org.openjfx.view.RadarMapView;

import java.util.ArrayList;


public class PreBossGameController {
    private PreBossMap preBossMap;
    private GameBar gameBar;
    private MapView mapView;
    private GameBarView gameBarView;
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
    private BooleanProperty gameOnChange = new SimpleBooleanProperty(false);
    private boolean isGameFinished = false;
    private boolean isGameSuccessful = false;
    private double rateGameBar = 0.12;
    private double rateMap = 0.91;


    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (upPressed && leftPressed) {
                motionFunction(PreBossMap.getHitboxWidthScale() * (-0.04 / 1920), (Spacecraft.MAX_PACE / Math.sqrt(2)), -1, 1);
            } else if (upPressed && rightPressed) {
                motionFunction(PreBossMap.getHitboxWidthScale() * (0.04 / 1920), (Spacecraft.MAX_PACE / Math.sqrt(2)), 1, 1);
            } else if (downPressed && leftPressed) {
                motionFunction(PreBossMap.getHitboxWidthScale() * (-0.04 / 1920), (Spacecraft.MAX_PACE / Math.sqrt(2)), -1, -1);
            } else if (downPressed && rightPressed) {
                motionFunction(PreBossMap.getHitboxWidthScale() * (0.04 / 1920), (Spacecraft.MAX_PACE / Math.sqrt(2)), 1, -1);
            } else if (upPressed) {
                preBossMap.getSpacecraft().moveToDirection(preBossMap.getSpacecraft().getVelocity(), 0, 1);
            } else if (downPressed) {
                preBossMap.getSpacecraft().moveToDirection(preBossMap.getSpacecraft().getVelocity(), 0, -1);
            } else if (leftPressed) {
                motionFunction(PreBossMap.getHitboxWidthScale() * (-0.04 / 1920), Spacecraft.MAX_PACE, -1, 0);
            } else if (rightPressed) {
                motionFunction(PreBossMap.getHitboxWidthScale() * (0.04 / 1920), Spacecraft.MAX_PACE, 1, 0);
            }

            if (!leftPressed && !rightPressed) {
                sliderAccelerationSpeed = 0;
                if (preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getViewLeft() > PreBossMap.getHitboxWidthScale() / 2 + 1) {
                    if (preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getViewLeft() > PreBossMap.getHitboxWidthScale() / 2 + 4) {
                        preBossMap.setViewLeft(preBossMap.getViewLeft() + PreBossMap.getHitboxWidthScale() * 3 / 1920);
                    } else
                        preBossMap.setViewLeft(preBossMap.getSpacecraft().getLocation().getPositionX() - PreBossMap.getHitboxWidthScale() / 2);
                }

                if (preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getViewLeft() < PreBossMap.getHitboxWidthScale() / 2 - 1) {
                    if (preBossMap.getSpacecraft().getLocation().getPositionX() - preBossMap.getViewLeft() < PreBossMap.getHitboxWidthScale() / 2 - 4) {
                        preBossMap.setViewLeft(preBossMap.getViewLeft() - PreBossMap.getHitboxWidthScale() * 3 / 1920);
                    } else
                        preBossMap.setViewLeft(preBossMap.getSpacecraft().getLocation().getPositionX() - PreBossMap.getHitboxWidthScale() / 2);
                }
            }

            if (leftPressed) {
                if (preBossMap.getSpacecraft().getLocation().getPositionX() < preBossMap.getViewLeft() + PreBossMap.getHitboxWidthScale() * (170.0 / 1920)) {
                    preBossMap.getSpacecraft().getLocation().setPositionX(preBossMap.getViewLeft() + PreBossMap.getHitboxWidthScale() * (170.0 / 1920));
                    sliderAccelerationSpeed -= PreBossMap.getHitboxWidthScale() * (4.0 / 1920);
                }
            }

            if (rightPressed) {
                if (preBossMap.getSpacecraft().getLocation().getPositionX() > preBossMap.getViewLeft() + PreBossMap.getHitboxWidthScale() * (1750.0 / 1920) - preBossMap.getSpacecraft().getHitBoxWidth()) {
                    preBossMap.getSpacecraft().getLocation().setPositionX(preBossMap.getViewLeft() + PreBossMap.getHitboxWidthScale() * (1750.0 / 1920) - preBossMap.getSpacecraft().getHitBoxWidth());
                    sliderAccelerationSpeed += PreBossMap.getHitboxWidthScale() * (4.0 / 1920);
                    ;
                }
            }

            bulletFire(preBossMap.getSpacecraft(), spacePressed);

            refreshMap();
            mapView.refreshExplodeAnimations();

            for (var bullet : preBossMap.getBullets().values()) {
                bullet.moveToDirection(bullet.getVelocity(), bullet.getDirectionX(), bullet.getDirectionY());
            }

        }
    };

    public PreBossGameController(double sceneWidth, double sceneHeight) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        initGame();
    }


    private void initGame(){
        gameBar = new GameBar(sceneWidth, sceneHeight*rateGameBar);
        gameBarView = new GameBarView(gameBar.getHitboxWidthScale(), gameBar.getHitboxHeightScale());
        preBossMap = new PreBossMap(3, sceneWidth, sceneHeight*rateMap);
        mapView = new MapView(sceneWidth,sceneHeight*rateMap);


        refreshMap();

        BorderPane rootPane = new BorderPane();
        rootPane.setTop(gameBarView);
        rootPane.setCenter(mapView);

        scene = new Scene(rootPane, sceneWidth, sceneHeight);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
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

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {

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
                    gameOnChange.set(true);
                    break;
            }
        });


        animationTimer.start();

    }

    private void refreshMap() {
        preBossMap.checkMapSituation();
        for (var enemy : preBossMap.getFiringEnemies()) {
            bulletFire(enemy, !enemy.getDestinationType().equals("empty"));
        }

        refreshEnemy();
        refreshSpacecraft();
        refreshBullet();
        refreshBuildings();
    }

    private void refreshEnemy() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var enemy : preBossMap.getEnemies().values()) {
            ModelToViewEnemy modelToViewEnemy = new ModelToViewEnemy(enemy, preBossMap.getViewLeft(), preBossMap.getViewRight());
            if (enemy.isDead()) {
                toBeDeleted.add(enemy.getID());
                mapView.addExplodeAnimation(new ModelToView(enemy, preBossMap.getViewLeft(), preBossMap.getViewRight()));
            }

            mapView.refreshEnemy(modelToViewEnemy);
            gameBarView.refreshEnemy(modelToViewEnemy);

        }

        for (var it : toBeDeleted) {
            preBossMap.deleteEnemy(it);
            SoundController.explosion();
        }
    }

    private void refreshBullet() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var bullet : preBossMap.getBullets().values()) {
            if (bullet.isDead()) {
                toBeDeleted.add(bullet.getID());
            }
            mapView.refreshBullet(new ModelToView(bullet, preBossMap.getViewLeft(), preBossMap.getViewRight()));

        }
        for (var it : toBeDeleted) {
            preBossMap.deleteBullet(it);
        }
    }


    private void refreshSpacecraft(){
        mapView.refreshSpacecraft( new ModelToViewSpaceCraft(preBossMap.getSpacecraft(), preBossMap.getViewLeft(), preBossMap.getViewRight(), (leftPressed || rightPressed)));
        gameBarView.refreshSpacecraft(new ModelToView(preBossMap.getSpacecraft(), preBossMap.getViewLeft(), preBossMap.getViewRight()));

    }

    private void refreshBuildings(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var building : preBossMap.getAllyBuildings().values()) {
            if (building.isDead()) {
                toBeDeleted.add(building.getID());
                mapView.addExplodeAnimation(new ModelToView(building, preBossMap.getViewLeft(), preBossMap.getViewRight()));
            }
            mapView.refreshBuilding(new ModelToViewBuilding(building, preBossMap.getViewLeft(), preBossMap.getViewRight()));
        }
        for (var it : toBeDeleted) {
            preBossMap.deleteAllyBuilding(it);
            SoundController.explosion();
        }
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

        mapView.setPrefSize(sceneWidth,sceneHeight*rateMap);
        gameBarView.setPrefSize(sceneWidth, sceneHeight*rateGameBar);
        PreBossMap.setHitboxWidthScale(sceneWidth);
        gameBar.setHitboxWidthScale(sceneWidth);
        preBossMap.refreshMap();
        gameBar.refreshGameBar();
        gameBarView.refreshGameBarView(sceneWidth, sceneHeight*rateGameBar);
    }

    public double getSceneHeight() {
        return sceneHeight;
    }

    public void setSceneHeight(double sceneHeight) {
        this.sceneHeight = sceneHeight;
        mapView.setPrefSize(sceneWidth,sceneHeight*rateMap);
        gameBarView.setPrefSize(sceneWidth, sceneHeight*rateGameBar);
        PreBossMap.setHitboxHeightScale(sceneHeight*rateMap);
        gameBar.setHitboxHeightScale(sceneHeight*rateGameBar);
        preBossMap.refreshMap();
        gameBar.refreshGameBar();
        gameBarView.refreshGameBarView(sceneWidth, sceneHeight*rateGameBar);
    }

    private void motionFunction(double accelerationSpeedChange, double constraint, double directionX, double directionY) {
        sliderAccelerationSpeed += accelerationSpeedChange;

        if (sliderAccelerationSpeed < -constraint)
            sliderAccelerationSpeed = -constraint;
        if (sliderAccelerationSpeed > constraint)
            sliderAccelerationSpeed = constraint;

        preBossMap.setViewLeft(preBossMap.getViewLeft() + sliderAccelerationSpeed);
        preBossMap.getSpacecraft().moveToDirection(preBossMap.getSpacecraft().getVelocity(), directionX, directionY);
    }

    private <T> void bulletFire(T t, boolean isFiring) {

        var current = t instanceof Tier1 ? (Tier1) t : (t instanceof Spacecraft ? (Spacecraft) t : null);

        current.setGunTimer(current.getGunTimer() % current.getGunPeriod());

        if (isFiring) {
            if (current.getGunTimer() == 0) {
                Bullet bullet = current.fireBullet();
                preBossMap.addBullet(bullet);
                if (current instanceof Spacecraft) {
                    SoundController.fireBullet();
                }
            }
            current.setGunTimer(current.getGunTimer() + 1);
        } else if (current.getGunTimer() != 0)
            current.setGunTimer(current.getGunTimer() + 1);

    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public boolean isGameOnChange() {
        return gameOnChange.get();
    }

    public BooleanProperty gameOnChangeProperty() {
        return gameOnChange;
    }

    public void setGameOnChange(boolean gameOnChange) {
        this.gameOnChange.set(gameOnChange);
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }
}

