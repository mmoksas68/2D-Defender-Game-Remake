package org.openjfx.controller.preBossSceneControllers;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.util.Duration;
import org.openjfx.model.menuEntities.GameSaveObj;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.preBossEntities.Enemy.Tier1Enemy;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.preBossEntities.Station.EnemyStation;
import org.openjfx.utilization.*;
import org.openjfx.view.gameSceneView.preBossSceneView.RootPane;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.radarView.RadarObject;

import java.util.ArrayList;

public class PreBossGameController {
    private final int SCORE_DECAY_SECOND = 10;
    private final int SCORE_DECREASE = 5;
    private Timeline scoreTimeline;

    private RootPane rootPane;
    private Scene scene;
    private double width;
    private double height;
    private GameSituation gameSituation;
    private PreBossMapController preBossMapController;
    private SpacecraftController spacecraftController1;
    private SpacecraftController spacecraftController2;
    private boolean gameOn = true;
    private BooleanProperty gameOnChange = new SimpleBooleanProperty(false);
    private int scoreDecayTimer = 0;
    private boolean isSinglePlayer;

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
                 timerPulse();
        }
    };

    public PreBossGameController(Scene scene, double initWidth, double initHeight) {
        gameSituation = GameSituation.getInstance();
        isSinglePlayer = gameSituation.isSinglePlayer();
        this.scene = scene;
        rootPane = new RootPane(initWidth, initHeight, isSinglePlayer);
        this.width = initWidth;
        this.height = initHeight;
        preBossMapController = new PreBossMapController(isSinglePlayer);
        spacecraftController1 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft1(), rootPane.getPreBossMapView1(), preBossMapController.getPreBossMap());
        if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft2(), rootPane.getPreBossMapView2(), preBossMapController.getPreBossMap());
        scene.setRoot(rootPane);
        initGame();
        initListeners();
    }
    public PreBossGameController(PreBossMap preBossMap, Scene scene, double initWidth, double initHeight) {
        this.scene = scene;
        gameSituation = GameSituation.getInstance();
        isSinglePlayer = gameSituation.isSinglePlayer();
        rootPane = new RootPane(initWidth,initHeight,isSinglePlayer);
        this.width = initWidth;
        this.height = initHeight;
        preBossMapController = new PreBossMapController(preBossMap);
        isSinglePlayer = gameSituation.isSinglePlayer();
        spacecraftController1 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft1(), rootPane.getPreBossMapView1(), preBossMapController.getPreBossMap());
        if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft2(), rootPane.getPreBossMapView2(), preBossMapController.getPreBossMap());
        scene.setRoot(rootPane);
        initGame();
        initListeners();
    }

    public void initListeners(){
        ChangeListener<Boolean> isFirstDied = (observable, oldValue, newValue) -> {
            if(gameSituation.isTwoPlayerSingleShip()) {
                if (gameSituation.isFirstCraftDied()) {
                    spacecraftController2.getPreBossMap().setSpacecraft1(spacecraftController2.getPreBossMap().getSpacecraft2());
                    spacecraftController2.getPreBossMap().setSpacecraft2(null);
                    spacecraftController1 = spacecraftController2;
                    spacecraftController2 = null;
                    keysFor2();
                }
                if (gameSituation.isSecondCraftDied()) {
                    spacecraftController2 = null;
                    keysFor1();
                }
                rootPane.twoPlayerOneShipScreen(spacecraftController1.getPreBossMapView());
            }
        };
        gameSituation.twoPlayerSingleShipProperty().addListener(isFirstDied);
    }

    private void timerPulse(){

        refreshMap();
        spacecraftController1.checkInputs();
        if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2.checkInputs();

    }

    private void initGame(){
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
        decreaseScore();
    }



    private void refreshMap() {
        preBossMapController.checkMapSituation();
        refreshAndReflectGameInfo();
        refreshAndReflectBuff();
        refreshAndReflectBullet();
        refreshAndReflectEnemy();
        refreshAndReflectMeteor();
        refreshAndReflectStations();
        refreshSpacecraftGameInfo();
        refreshAndReflectSpacecraft(spacecraftController1.getSpacecraft());
        if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
            refreshAndReflectSpacecraft(spacecraftController2.getSpacecraft());
        updateHyperJumpBattery();
    }


    private void refreshAndReflectEnemy() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var enemy : preBossMapController.getPreBossMap().getEnemies().values()) {
            ModelToViewEnemy modelToViewEnemy = new ModelToViewEnemy(enemy);
            if (enemy.isDead()) {
                toBeDeleted.add(enemy.getID());
                spacecraftController1.getPreBossMapView().addExplodeAnimation(new ModelToView(enemy));
                if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                    spacecraftController2.getPreBossMapView().addExplodeAnimation(new ModelToView(enemy));
            }

            spacecraftController1.getPreBossMapView().refreshEnemy(modelToViewEnemy);
            if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                spacecraftController2.getPreBossMapView().refreshEnemy(modelToViewEnemy);
            rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(enemy));
        }

        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteEnemy(it);
            //SoundController.explosion();
        }
    }

    private void refreshAndReflectBullet() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var bullet : preBossMapController.getPreBossMap().getBullets().values()) {
            if (bullet.isDead()) {
                toBeDeleted.add(bullet.getID());
            }
            spacecraftController1.getPreBossMapView().refreshBullet(new ModelToViewBullet(bullet));
            if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                spacecraftController2.getPreBossMapView().refreshBullet(new ModelToViewBullet(bullet));

        }
        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteBullet(it);
        }
    }


    private void refreshAndReflectSpacecraft(Spacecraft spacecraft){
        if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip()){

            if(spacecraft.getID() == spacecraftController1.getSpacecraft().getID())
            {
                spacecraftController2.getPreBossMapView().refreshSpacecraftSecondary( new ModelToViewSpaceCraft(spacecraft));
                spacecraftController1.getPreBossMapView().refreshSpacecraftMain( new ModelToViewSpaceCraft(spacecraft));
                if(spacecraft.isDead()){
                    gameSituation.setIsFirstCraftDied(true);
                }
            }

            if (spacecraft.getID() == spacecraftController2.getSpacecraft().getID()) {
                spacecraftController1.getPreBossMapView().refreshSpacecraftSecondary(new ModelToViewSpaceCraft(spacecraft));
                spacecraftController2.getPreBossMapView().refreshSpacecraftMain(new ModelToViewSpaceCraft(spacecraft));
                if (spacecraft.isDead()) {
                    gameSituation.setIsSecondCraftDied(true);
                }
            }
            if(gameSituation.isFirstCraftDied() || gameSituation.isSecondCraftDied()){
                gameSituation.setTwoPlayerSingleShip(true);
            }

        }

        else{
            spacecraftController1.getPreBossMapView().refreshSpacecraftMain( new ModelToViewSpaceCraft(spacecraft));
            if(spacecraftController1.getPreBossMap().getSpacecraft1().isDead()){
                gameSituation.setIsPreBossFinished(true);
                animationTimer.stop();
            }
        }


        rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(spacecraft));
    }

    private void refreshAndReflectStations(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();

        for (var station : preBossMapController.getPreBossMap().getStations().values()) {
            if (station.isDead()) {
                toBeDeleted.add(station.getID());

                spacecraftController1.getPreBossMapView().addExplodeAnimation(new ModelToView(station));
                if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                    spacecraftController2.getPreBossMapView().addExplodeAnimation(new ModelToView(station));
            }
            spacecraftController1.getPreBossMapView().refreshStations(new ModelToViewStation(station));
            if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                spacecraftController2.getPreBossMapView().refreshStations(new ModelToViewStation(station));
            rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(station));
        }

        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteStation(it);
            //SoundController.explosion();
        }
    }

    private void refreshAndReflectBuff(){

    }

    private void refreshAndReflectMeteor(){

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

    private void refreshAndReflectGameInfo() {
        increaseScore();
        rootPane.getTopBarView().getRightView().refresh(new ModelToGameInfoView(gameSituation.getScore(),
                preBossMapController.getPreBossMap().getEnemies().size(), preBossMapController.getPreBossMap().getStations().size()));
    }

    public void resume(){

    }

    public void pause(){

    }

    private void updateHyperJumpBattery(){
        Spacecraft spacecraft1 = spacecraftController1.getSpacecraft();
        spacecraft1.setBatteryTimer(spacecraft1.getBatteryTimer() + 1);
        spacecraft1.setBatteryTimer(spacecraft1.getBatteryTimer() % Spacecraft.HYPERJUMP_PERIOD);
        if(spacecraft1.getHyperJumpBattery() < Spacecraft.MAX_HYPERJUMP_ENERGY && spacecraft1.getBatteryTimer() == 0){
            spacecraft1.setHyperJumpBattery(spacecraft1.getHyperJumpBattery() + 1);
        }
        if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip()){
            Spacecraft spacecraft2 = spacecraftController2.getSpacecraft();
            spacecraft2.setBatteryTimer(spacecraft2.getBatteryTimer()+1);
            spacecraft2.setBatteryTimer(spacecraft2.getBatteryTimer() % Spacecraft.HYPERJUMP_PERIOD);
            if(spacecraft2.getHyperJumpBattery() < Spacecraft.MAX_HYPERJUMP_ENERGY && spacecraft2.getBatteryTimer() == 0){
                spacecraft2.setHyperJumpBattery(spacecraft2.getHyperJumpBattery()+1);
            }
        }

    }

    private void increaseScore() {
        for (var station : preBossMapController.getPreBossMap().getStations().values()) {
            if (station.isDead()) {
                gameSituation.setScore(gameSituation.getScore() + EnemyStation.SCORE_POINT);
            }
        }
        for (var enemy : preBossMapController.getPreBossMap().getEnemies().values()) {
            if (enemy.isDead()) {
                gameSituation.setScore(gameSituation.getScore() + Tier1Enemy.SCORE_POINT);
            }
        }
    }

    private void decreaseScore(){

            scoreTimeline = new Timeline(new KeyFrame(Duration.seconds(SCORE_DECAY_SECOND), ae -> {
                if (gameSituation.getScore() > SCORE_DECREASE)
                    gameSituation.setScore(gameSituation.getScore() - SCORE_DECREASE);
                else
                    gameSituation.setScore(0);
            }));
            scoreTimeline.setCycleCount(Timeline.INDEFINITE);
            scoreTimeline.play();
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
                    spacecraftController1.getSpacecraft().setDirectionLeft(true);
                    break;
                case RIGHT:
                    spacecraftController1.setRightKeyPressed(true);
                    spacecraftController1.getSpacecraft().setDirectionLeft(false);
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
                case NUMPAD1:
                    spacecraftController1.doHyperJump();
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
                    spacecraftController1.getSpacecraft().setDirectionLeft(true);
                    break;
                case D:
                    spacecraftController1.setRightKeyPressed(true);
                    spacecraftController1.getSpacecraft().setDirectionLeft(false);
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
                case Z:
                    spacecraftController1.doHyperJump();
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
                    spacecraftController2.getSpacecraft().setDirectionLeft(true);
                    break;
                case D:
                    spacecraftController2.setRightKeyPressed(true);
                    spacecraftController2.getSpacecraft().setDirectionLeft(false);
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
                    spacecraftController1.getSpacecraft().setDirectionLeft(true);
                    break;
                case RIGHT:
                    spacecraftController1.setRightKeyPressed(true);
                    spacecraftController1.getSpacecraft().setDirectionLeft(false);
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
                case Z:
                    spacecraftController2.doHyperJump();
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
                case NUMPAD1:
                    spacecraftController1.doHyperJump();
                    break;
                case NUMPAD2:
                    spacecraftController1.activateSmartBomb();
                    break;
            }
        });
    }


    //#############################################################################################################

    /*public BorderPane getRootPane() {
        return rootPane;
    } */

    public void setRootPane(RootPane rootPane) {
        this.rootPane = rootPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
      //  rootPane.setWidth(width);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
     //   rootPane.setHeight(height);
    }

    public Timeline getScoreTimeline(){
        return scoreTimeline;
    }

    public GameSituation getGameSituation() {
        return gameSituation;
    }

    public void setGameSituation(GameSituation gameSituation) {
        this.gameSituation = gameSituation;
    }

    public PreBossMapController getPreBossMapController() {
        return preBossMapController;
    }

    public void setPreBossMapController(PreBossMapController preBossMapController) {
        this.preBossMapController = preBossMapController;
    }

    public SpacecraftController getSpacecraftController1() {
        return spacecraftController1;
    }

    public void setSpacecraftController1(SpacecraftController spacecraftController1) {
        this.spacecraftController1 = spacecraftController1;
    }

    public SpacecraftController getSpacecraftController2() {
        return spacecraftController2;
    }

    public void setSpacecraftController2(SpacecraftController spacecraftController2) {
        this.spacecraftController2 = spacecraftController2;
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

    public int getScoreDecayTimer() {
        return scoreDecayTimer;
    }

    public void setScoreDecayTimer(int scoreDecayTimer) {
        this.scoreDecayTimer = scoreDecayTimer;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }



}

