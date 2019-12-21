package org.openjfx.controller.bossSceneControllers;


import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.openjfx.controller.SoundController;
import org.openjfx.controller.preBossSceneControllers.SpacecraftController;
import org.openjfx.model.bossEntities.BossAbility.Marker;
import org.openjfx.model.bossEntities.BossAbility.Rocket;
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
    private int level = 1;
    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            timerPulse();

        }
    };

    public BossGameController(Scene scene, double initWidth, double initHeight) {
        gameSituation = GameSituation.getInstance();
        isSinglePlayer = gameSituation.isSinglePlayer();
        this.scene = scene;
        rootPane = new RootPane(initWidth, initHeight);
        bossMapController = new BossMapController( new BossMap( level, isSinglePlayer));
        bossController = new BossController( bossMapController.getBossMap().getLevel(), bossMapController.getBossMap(), rootPane.getBossMapView());
        this.width = initWidth;
        this.height = initHeight;

        
        spacecraftController1 = new SpacecraftController(bossMapController.getBossMap().getSpacecraft1(), rootPane.getBossMapView(),bossMapController.getBossMap());
        if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2 = new SpacecraftController( bossMapController.getBossMap().getSpacecraft2(), rootPane.getBossMapView(), bossMapController.getBossMap());
        scene.setRoot( rootPane);
        initGame();
        initListeners();
    }

    public BossGameController(BossMap bossMap, Scene scene, double initWidth, double initHeight) {
        this.scene = scene;
        rootPane = new RootPane(initWidth, initHeight);
        bossMapController = new BossMapController(bossMap);
        bossController = new BossController( bossMapController.getBossMap().getLevel(), bossMapController.getBossMap(), rootPane.getBossMapView());
        this.width = initWidth;
        this.height = initHeight;

        spacecraftController1 = new SpacecraftController(bossMapController.getBossMap().getSpacecraft1(), rootPane.getBossMapView(),bossMapController.getBossMap());
        if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2 = new SpacecraftController( bossMapController.getBossMap().getSpacecraft2(), rootPane.getBossMapView(), bossMapController.getBossMap());
        scene.setRoot(rootPane);
        initGame();
        initListeners();
    }


        public void initListeners(){
            ChangeListener<Boolean> isFirstDied = (observable, oldValue, newValue) -> {
                if(gameSituation.isFirstCraftDied()){
                    spacecraftController2.getBossMap().setSpacecraft1(spacecraftController2.getBossMap().getSpacecraft2());
                    spacecraftController2.getBossMap().setSpacecraft2(null);
                    spacecraftController1 = spacecraftController2;
                    spacecraftController2 = null;
                    keysFor2();
                }
                if(gameSituation.isSecondCraftDied()){
                    spacecraftController2 = null;
                    keysFor1();
                }
                rootPane.twoPlayerOneShipScreen(spacecraftController1.getPreBossMapView());
            };
            gameSituation.twoPlayerSingleShipProperty().addListener(isFirstDied);
        }

    private void timerPulse() {
        refreshMap();
        spacecraftController1.getInputs();
        if ( !isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2.getInputs();
        bossController.behave();

    }
    private void initGame() {
        refreshMap();
        if(isSinglePlayer || gameSituation.isSecondCraftDied()){
            keysFor1();
        }
        else if(gameSituation.isFirstCraftDied()){
            keysFor2();
        }
        else {
            keysForBoth();
        }
        animationTimer.start();
        //decreaseScore();
      //  animationTimer.start();
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
        if ( !isSinglePlayer)
            refreshAndReflectSpacecraft( spacecraftController2.getSpacecraft());
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
            rootPane.getBossMapView().refreshBullet(new ModelToViewBullet(bullet));

        }
        for (var it : toBeDeleted) {
            bossMapController.getBossMap().deleteBullet(it);
        }
    }
    private void refreshAndReflectMeteor() {

    }
    private void refreshAndReflectScore() {
        rootPane.getBossTopBarView().getMiddleView().refresh();
    }

    private void refreshSpacecraftGameInfo(){
        if((gameSituation.isTwoPlayerSingleShip() && gameSituation.isSecondCraftDied())|| isSinglePlayer || !gameSituation.isTwoPlayerSingleShip()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView1 = new ModelToSpacecraftInfoView(spacecraftController1.getSpacecraft());
            rootPane.getBossTopBarView().getSpacecraftInfoView1().refresh(modelToSpacecraftInfoView1);
        }
        if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView2 = new ModelToSpacecraftInfoView(spacecraftController2.getSpacecraft());
            rootPane.getBossTopBarView().getSpacecraftInfoView2().refresh(modelToSpacecraftInfoView2);
        }
        if(!isSinglePlayer && gameSituation.isTwoPlayerSingleShip() && gameSituation.isFirstCraftDied()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView2 = new ModelToSpacecraftInfoView(spacecraftController1.getSpacecraft());
            rootPane.getBossTopBarView().getSpacecraftInfoView2().refresh(modelToSpacecraftInfoView2);
        }
    }

    private void refreshAndReflectSpacecraft(Spacecraft spacecraft) {
        if ( spacecraft.getID() == spacecraftController1.getSpacecraft().getID()) {
            rootPane.getBossMapView().refreshSpacecraftMain(new ModelToViewSpaceCraft(spacecraft));
        }
        else if ( spacecraft.getID() == spacecraftController2.getSpacecraft().getID()) {
            rootPane.getBossMapView().refreshSpacecraftSecondary( new ModelToViewSpaceCraft( spacecraft));
        }
    }

    private void refresAndReflectBoss() {
        rootPane.getBossMapView().refreshBossView( new ModelToViewBoss( bossMapController.getBossMap().getBoss()));
        rootPane.getBossTopBarView().getRightView().refresh( new ModelToViewBossInfoView(bossMapController.getBossMap().getBoss()));
    }

    private void refreshAndReflectSpecialAbility () {
        ArrayList <Long> toBeDeleted = new ArrayList<>();
        for (SpecialAbility specialAbility : bossMapController.getBossMap().getSpecialAbilities().values()) {
            if ( specialAbility.isDead()) {
                toBeDeleted.add( specialAbility.getID());
                if ( specialAbility instanceof Rocket)
                    rootPane.getBossMapView().addFireAnimation(new ModelToViewSpecialAbility(((Rocket) specialAbility).getDestinationMarker()));
            }
            rootPane.getBossMapView().refreshSpecialAbilityView( new ModelToViewSpecialAbility( specialAbility));

        }
        for (Long id : toBeDeleted) {
            bossMapController.getBossMap().removeSpecialAbility(id);
        }
        rootPane.getBossMapView().refreshFireAnimation();

    }

    //#############################################################################################################

    private void keysFor1(){
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
                case NUMPAD2:
                    spacecraftController1.activateSmartBomb();
                    break;
            }
        });

    }

    private void keysFor2(){
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    spacecraftController1.setUpKeyPressed(true);
                    break;
                case S:
                    spacecraftController1.setDownKeyPressed(true);
                    break;
                case A:
                    spacecraftController1.setLeftKeyPressed(true);
                    break;
                case D:
                    spacecraftController1.setRightKeyPressed(true);
                    break;
                case SPACE:
                    spacecraftController1.setFireKeyPressed(true);
                    break;

            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W:
                    spacecraftController1.setUpKeyPressed(false);
                    break;
                case S:
                    spacecraftController1.setDownKeyPressed(false);
                    break;
                case A:
                    spacecraftController1.setLeftKeyPressed(false);
                    break;
                case D:
                    spacecraftController1.setRightKeyPressed(false);
                    break;
                case SPACE:
                    spacecraftController1.setFireKeyPressed(false);
                    break;
                case ESCAPE:
                    gameOnChange.set(true);
                    break;
                case X:
                    spacecraftController1.activateSmartBomb();
                    break;
            }
        });
    }

    private void keysForBoth(){
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    spacecraftController2.setUpKeyPressed(true);
                    break;
                case S:
                    spacecraftController2.setDownKeyPressed(true);
                    break;
                case A:
                    spacecraftController2.setLeftKeyPressed(true);
                    break;
                case D:
                    spacecraftController2.setRightKeyPressed(true);
                    break;
                case SPACE:
                    spacecraftController2.setFireKeyPressed(true);
                    break;

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
                case W:
                    spacecraftController2.setUpKeyPressed(false);
                    break;
                case S:
                    spacecraftController2.setDownKeyPressed(false);
                    break;
                case A:
                    spacecraftController2.setLeftKeyPressed(false);
                    break;
                case D:
                    spacecraftController2.setRightKeyPressed(false);
                    break;
                case SPACE:
                    spacecraftController2.setFireKeyPressed(false);
                    break;
                case X:
                    spacecraftController2.activateSmartBomb();
                    break;
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
                case NUMPAD2:
                    spacecraftController1.activateSmartBomb();
                    break;
            }
        });
    }


    //#############################################################################################################

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


    public BossMapController getBossMapController() {return bossMapController;}
}

