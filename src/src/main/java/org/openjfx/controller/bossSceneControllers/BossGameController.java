package org.openjfx.controller.bossSceneControllers;


import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import org.openjfx.controller.SoundController;
import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossOneBehaviour;
import org.openjfx.controller.preBossSceneControllers.SpacecraftController;
import org.openjfx.model.bossEntities.Boss.BossOne;
import org.openjfx.model.bossEntities.Boss.BossThree;
import org.openjfx.model.bossEntities.Boss.BossTwo;
import org.openjfx.model.bossEntities.BossAbility.Laser;
import org.openjfx.model.bossEntities.BossAbility.Marker;
import org.openjfx.model.bossEntities.BossAbility.Rocket;
import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.menuEntities.Settings;
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
    private SpacecraftController spacecraftController1;
    private SpacecraftController spacecraftController2;
    private boolean gameOn = true;
    private BooleanProperty gameOnChange = new SimpleBooleanProperty(false);
    private BooleanProperty gameIsFinish = new SimpleBooleanProperty(false);
    private int scoreDecayTimer = 0;
    private final int SCORE_DECAY_PERIOD = 30000;
    private int level;
    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            timerPulse();

        }
    };

    public BossGameController(Scene scene, double initWidth, double initHeight) {
        System.out.println("boş constructor");
        gameSituation = GameSituation.getInstance();
        level = 1;
        this.scene = scene;
        rootPane = new RootPane(initWidth, initHeight);

        bossMapController = new BossMapController( new BossMap( level, gameSituation.isSinglePlayer()));

        Boss boss = null;
        switch ( level) {
            case 1:
                boss = new BossOne(bossMapController.getBossMap());
                break;
            case 2:
                boss = new BossTwo(bossMapController.getBossMap());
                break;
            case 3:
                boss = new BossThree(bossMapController.getBossMap());
                break;
            default:
                break;
        }
        bossMapController.getBossMap().setBoss( boss);
        //bossController = new BossController(bossMapController.getBossMap().getLevel(), bossMapController.getBossMap(), rootPane.getBossMapView());
        this.width = initWidth;
        this.height = initHeight;
        spacecraftController1 = new SpacecraftController(bossMapController.getBossMap().getSpacecraft1(), rootPane.getBossMapView(),bossMapController.getBossMap());
        if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2 = new SpacecraftController( bossMapController.getBossMap().getSpacecraft2(), rootPane.getBossMapView(), bossMapController.getBossMap());
        scene.setRoot( rootPane);
        initGame();
        initListeners();
    }

    public BossGameController(BossMap bossMap, Scene scene, double initWidth, double initHeight) {
        System.out.println("dolu constructor");
        this.scene = scene;
        rootPane = new RootPane(initWidth, initHeight);
        bossMapController = new BossMapController(bossMap);
       // bossController = new BossController( bossMapController.getBossMap().getLevel(), bossMapController.getBossMap(), rootPane.getBossMapView());
        this.width = initWidth;
        this.height = initHeight;
        gameSituation = GameSituation.getInstance();
        spacecraftController1 = new SpacecraftController(bossMapController.getBossMap().getSpacecraft1(), rootPane.getBossMapView(),bossMapController.getBossMap());
        System.out.println(spacecraftController1);
        if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
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
            };
            gameSituation.twoPlayerSingleShipProperty().addListener(isFirstDied);
        }

    private void timerPulse() {
        refreshMap();
        spacecraftController1.getInputs();
        if ( !gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2.getInputs();
        bossMapController.getBossMap().getBoss().behave();

    }
    private void initGame() {

        if(gameSituation.isSinglePlayer() || gameSituation.isSecondCraftDied()){
            keysFor1();
        }
        else if(gameSituation.isFirstCraftDied()){
            keysFor2();
        }
        else {
            keysForBoth();
        }

        refreshMap();
        animationTimer.start();
        //decreaseScore();
      //  animationTimer.start();
    }

    private void refreshMap() {
        bossMapController.checkMapSituation();
        refreshAndReflectBuff();
        refreshAndReflectBullet();
        refreshAndReflectMeteor();
        refreshAndReflectScore();
        refreshAndReflectSpacecraft(spacecraftController1.getSpacecraft());
        if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
            refreshAndReflectSpacecraft( spacecraftController2.getSpacecraft());
        refresAndReflectBoss();
        refreshAndReflectSpecialAbility();
        checkEndGame();
        refreshAndReflectGameInfo();
        refreshSpacecraftGameInfo();
    }

    private void refreshAndReflectBuff() {

    }

    private void refreshAndReflectGameInfo(){
        increaseScore();
        rootPane.getBossTopBarView().getMiddleView().refresh( new ModelToGameInfoView(gameSituation.getScore(),
                0, 0));
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
        if((gameSituation.isTwoPlayerSingleShip() && gameSituation.isSecondCraftDied())|| gameSituation.isSinglePlayer() || !gameSituation.isTwoPlayerSingleShip()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView1 = new ModelToSpacecraftInfoView(spacecraftController1.getSpacecraft());
            rootPane.getBossTopBarView().getSpacecraftInfoView1().refresh(modelToSpacecraftInfoView1);
        }
        if(!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView2 = new ModelToSpacecraftInfoView(spacecraftController2.getSpacecraft());
            rootPane.getBossTopBarView().getSpacecraftInfoView2().refresh(modelToSpacecraftInfoView2);
        }
        if(!gameSituation.isSinglePlayer() && gameSituation.isTwoPlayerSingleShip() && gameSituation.isFirstCraftDied()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView2 = new ModelToSpacecraftInfoView(spacecraftController1.getSpacecraft());
            rootPane.getBossTopBarView().getSpacecraftInfoView2().refresh(modelToSpacecraftInfoView2);
        }
    }

    private void refreshAndReflectSpacecraft(Spacecraft spacecraft) {
        if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip()) {
            if (spacecraft.getID() == spacecraftController1.getSpacecraft().getID()) {
                spacecraftController1.getBossMapView().refreshSpacecraftMain(new ModelToViewSpaceCraft(spacecraft));
                if (spacecraft.isDead()) {
                    gameSituation.setIsFirstCraftDied(true);
                }
            }
            if (spacecraft.getID() == spacecraftController2.getSpacecraft().getID()) {
                spacecraftController2.getBossMapView().refreshSpacecraftSecondary(new ModelToViewSpaceCraft(spacecraft));
                if (spacecraft.isDead()) {
                    gameSituation.setIsSecondCraftDied(true);
                }
            }
            if (gameSituation.isFirstCraftDied() || gameSituation.isSecondCraftDied()) {
                gameSituation.setTwoPlayerSingleShip(true);
            }
        }
        else {
            if(gameSituation.isFirstCraftDied())
                spacecraftController1.getBossMapView().refreshSpacecraftSecondary(new ModelToViewSpaceCraft(spacecraft));
            else
                spacecraftController1.getBossMapView().refreshSpacecraftMain(new ModelToViewSpaceCraft(spacecraft));
            if (spacecraft.isDead()) {
                gameSituation.setIsFirstCraftDied(true);
                gameIsFinishProperty().set(true);
                gameSituation.setIsBossFinished(true);
                animationTimer.stop();
            }
        }
    }

    private void checkEndGame(){
        if(bossMapController.getBossMap().getBoss().isDead()){
            gameIsFinishProperty().set(true);
            gameSituation.setIsBossFinishedSuccessfully(true);
            animationTimer.stop();
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
            if ( specialAbility instanceof Laser) {
                if (((BossOneBehaviour) bossMapController.getBossMap().getBoss().getBehaviourAlgorithm()).isNotifyController()) {
                    rootPane.getBossMapView().addLaserIndicator ( ((BossOne) bossMapController.getBossMap().getBoss()).sendLaserIndicator());
                }
                if ( bossMapController.getBossMap().getBoss().getBehaviourAlgorithm().getAbilityTimer() <= 0.0) {
                   rootPane.getBossMapView().removeLaserIndicator();
                }
            }
        }
        for (Long id : toBeDeleted) {
            bossMapController.getBossMap().removeSpecialAbility(id);
        }
        rootPane.getBossMapView().refreshFireAnimation();

    }

    //#############################################################################################################

    private void keysFor1(){

        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(Settings.getInstance().getUp())) {
                spacecraftController1.setUpKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getDown())) {
                spacecraftController1.setDownKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft())) {
                spacecraftController1.setLeftKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getRight())) {
                spacecraftController1.setRightKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getFire())) {
                spacecraftController1.setFireKeyPressed(true);
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode().equals(Settings.getInstance().getUp())) {
                spacecraftController1.setUpKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getDown())) {
                spacecraftController1.setDownKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft())) {
                spacecraftController1.setLeftKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getRight())) {
                spacecraftController1.setRightKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getFire())) {
                spacecraftController1.setFireKeyPressed(false);
            }
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                animationTimer.stop();
                gameOnChange.set(true);
            }
            if (e.getCode().equals(Settings.getInstance().getSmartBomb()))
                spacecraftController1.activateSmartBomb();

        });

    }

    private void keysFor2() {
        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(Settings.getInstance().getUp2())) {
                spacecraftController1.setUpKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getDown2())) {
                spacecraftController1.setDownKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft2())) {
                spacecraftController1.setLeftKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getRight2())) {
                spacecraftController1.setRightKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getFire2())) {
                spacecraftController1.setFireKeyPressed(true);
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode().equals(Settings.getInstance().getUp2())) {
                spacecraftController1.setUpKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getDown2())) {
                spacecraftController1.setDownKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft2())) {
                spacecraftController1.setLeftKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getRight2())) {
                spacecraftController1.setRightKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getFire2())) {
                spacecraftController1.setFireKeyPressed(false);
            }
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                animationTimer.stop();
                gameOnChange.set(true);
            }
            if (e.getCode().equals(Settings.getInstance().getSmartBomb2()))
                spacecraftController1.activateSmartBomb();

        });
    }

    private void keysForBoth(){
        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(Settings.getInstance().getUp())) {
                spacecraftController1.setUpKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getDown())) {
                spacecraftController1.setDownKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft())) {
                spacecraftController1.setLeftKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getRight())) {
                spacecraftController1.setRightKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getFire())) {
                spacecraftController1.setFireKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getUp2())) {
                spacecraftController2.setUpKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getDown2())) {
                spacecraftController2.setDownKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft2())) {
                spacecraftController2.setLeftKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getRight2())) {
                spacecraftController2.setRightKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getFire2())) {
                spacecraftController2.setFireKeyPressed(true);
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode().equals(Settings.getInstance().getUp())) {
                spacecraftController1.setUpKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getDown())) {
                spacecraftController1.setDownKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft())) {
                spacecraftController1.setLeftKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getRight())) {
                spacecraftController1.setRightKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getFire())) {
                spacecraftController1.setFireKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getSmartBomb()))
                spacecraftController1.activateSmartBomb();

            if (e.getCode().equals(Settings.getInstance().getUp2())) {
                spacecraftController2.setUpKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getDown2())) {
                spacecraftController2.setDownKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft2())) {
                spacecraftController2.setLeftKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getRight2())) {
                spacecraftController2.setRightKeyPressed(false);
            }
            if (e.getCode().equals(Settings.getInstance().getFire2())) {
                spacecraftController2.setFireKeyPressed(false);
            }
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                animationTimer.stop();
                gameOnChange.set(true);
            }
            if (e.getCode().equals(Settings.getInstance().getSmartBomb2()))
                spacecraftController2.activateSmartBomb();


        });
    }


    //#############################################################################################################

    private void increaseScore() {
        if( bossMapController.isBossHit() == true) {
            gameSituation.setScore(gameSituation.getScore() + Boss.SCORE_POINT);
            bossMapController.setBossHit(false);
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

    public BossMapController getBossMapController() {return bossMapController;}

    public boolean isGameIsFinish() {
        return gameIsFinish.get();
    }

    public BooleanProperty gameIsFinishProperty() {
        return gameIsFinish;
    }

    public void setGameIsFinish(boolean gameIsFinish) {
        this.gameIsFinish.set(gameIsFinish);
    }
}

