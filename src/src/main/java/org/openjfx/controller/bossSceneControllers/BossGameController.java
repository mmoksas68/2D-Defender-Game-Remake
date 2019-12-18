package org.openjfx.controller.bossSceneControllers;


import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.openjfx.controller.SoundController;
import org.openjfx.controller.preBossSceneControllers.SpacecraftController;
import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.utilization.*;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.view.gameSceneView.preBossSceneView.RootPane;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.radarView.RadarObject;

import java.util.ArrayList;

public class BossGameController  {

    private RootPane rootPane;
    private Scene scene;
    private double width;
    private double height;
    private GameSituation gameSituation;
    private BossMapController bossMapController;
    private BossController bossController;
    private SpacecraftController spacecraftController1;
    private SpacecraftController spacecraftController2;
    private boolean gameOn = true;
    private BooleanProperty gameOnChange = new SimpleBooleanProperty(false);
    private int scoreDecayTimer = 0;
    private final int SCORE_DECAY_PERIOD = 30000;
    private boolean isSinglePlayer;
    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            timerPulse();

        }
    };

    public BossGameController(Scene scene, double initWidth, double initHeight) {
        gameSituation = GameSituation.getInstance();
        this.scene = scene;
        rootPane = new RootPane(initWidth, initHeight);
        bossMapController = new BossMapController( new BossMap( 3, true));
        bossController = new BossController( bossMapController.getBossMap().getLevel(), bossMapController.getBossMap());
        this.width = initWidth;
        this.height = initHeight;
        isSinglePlayer = gameSituation.isSinglePlayer();
        spacecraftController1 = new SpacecraftController(bossMapController.getBossMap().getSpacecraft1(), rootPane.getBossMapView(),bossMapController.getBossMap());
        scene.setRoot( rootPane);
        initGame();
    }
    private void timerPulse() {
        refreshMap();
        spacecraftController1.getInputs();
        bossController.behave();
    }
    private void initGame() {
        refreshMap();
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    spacecraftController1.setUpKeyPressed(true);
                    break;
                case DOWN:
                    spacecraftController1.setDownKeyPressed(true);
                    break;
                case LEFT:
                    spacecraftController1.setLeftKeyPressed(true);
                    break;
                case RIGHT:
                    spacecraftController1.setRightKeyPressed(true);
                    break;
                case ALT_GRAPH:
                    spacecraftController1.setFireKeyPressed(true);
                    break;
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    spacecraftController1.setUpKeyPressed(false);
                    break;
                case DOWN:
                    spacecraftController1.setDownKeyPressed(false);
                    break;
                case LEFT:
                    spacecraftController1.setLeftKeyPressed(false);
                    break;
                case RIGHT:
                    spacecraftController1.setRightKeyPressed(false);
                    break;
                case ALT_GRAPH:
                    spacecraftController1.setFireKeyPressed(false);
                    break;
                case ESCAPE:
                    gameOnChange.set(true);
                    break;
            }
        });

        animationTimer.start();
    }

    private void refreshMap() {
        bossMapController.checkMapSituation();
        scoreCalculator();
        refreshSpacecraftGameInfo();
        refreshAndReflectBuff();
        refreshAndReflectBullet();
        refreshAndReflectMeteor();
        refreshAndReflectScore();
        refreshAndReflectSpacecraft(spacecraftController1.getSpacecraft());
        refresAndReflectBoss();
        refreshAndReflectSpecialAbility();
    }

    private void scoreCalculator () {

    }
    private void refreshAndReflectBuff() {

    }
    private void refreshAndReflectBullet() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var bullet : bossMapController.getBossMap().getBullets().values()) {
            if (bullet.isDead()) {
                toBeDeleted.add(bullet.getID());
            }
            spacecraftController1.getBossMapView().refreshBullet(new ModelToViewBullet(bullet));

        }
        for (var it : toBeDeleted) {
            bossMapController.getBossMap().deleteBullet(it);
        }
    }
    private void refreshAndReflectMeteor() {

    }
    private void refreshAndReflectScore() {
        rootPane.getTopBarView().getRightView().refresh();
    }

    private void refreshSpacecraftGameInfo(){
        if((gameSituation.isTwoPlayerSingleShip() && gameSituation.isSecondCraftDied())|| isSinglePlayer || !gameSituation.isTwoPlayerSingleShip()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView1 = new ModelToSpacecraftInfoView(spacecraftController1.getSpacecraft());
            rootPane.getTopBarView().getSpacecraftInfoView1().refresh(modelToSpacecraftInfoView1);
        }
        if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView2 = new ModelToSpacecraftInfoView(spacecraftController2.getSpacecraft());
            rootPane.getTopBarView().getSpacecraftInfoView2().refresh(modelToSpacecraftInfoView2);
        }
        if(!isSinglePlayer && gameSituation.isTwoPlayerSingleShip() && gameSituation.isFirstCraftDied()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView2 = new ModelToSpacecraftInfoView(spacecraftController1.getSpacecraft());
            rootPane.getTopBarView().getSpacecraftInfoView2().refresh(modelToSpacecraftInfoView2);
        }
    }

    private void refreshAndReflectSpacecraft(Spacecraft spacecraft) {
        rootPane.getBossMapView().refreshSpacecraftMain( new ModelToViewSpaceCraft(spacecraft));
        rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(spacecraft));
    }
    private void refresAndReflectBoss() {
        rootPane.getBossMapView().refreshBossView( new ModelToViewBoss( bossMapController.getBossMap().getBoss()));
    }

    private void refreshAndReflectSpecialAbility () {
        ArrayList <Long> toBeDeleted = new ArrayList<>();
        for (SpecialAbility specialAbility : bossMapController.getBossMap().getSpecialAbilities().values()) {
            if ( specialAbility.isDead()) {
                toBeDeleted.add( specialAbility.getID());
            }
            rootPane.getBossMapView().refreshSpecialAbilityView( new ModelToViewSpecialAbility( specialAbility));
        }
        for (Long id : toBeDeleted) {
            bossMapController.getBossMap().removeSpecialAbility(id);
        }
    }
    public Scene getScene() {
        return scene;
    }

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

 /*   double [] boundArray = { moveright, moveleft, moveup, movedown};
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
//  bossMapView.refreshExplodeAnimations();*/