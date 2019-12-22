package org.openjfx.controller.preBossSceneControllers;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.Buff.BuffTypes;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.menuEntities.GameSaveObj;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.menuEntities.Settings;
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
    Settings settings;
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
    private BooleanProperty gameIsFinish = new SimpleBooleanProperty(false);
    private int scoreDecayTimer = 0;

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
                 timerPulse();
        }
    };

    public PreBossGameController(Scene scene, double initWidth, double initHeight) {
        settings = Settings.getInstance();
        gameSituation = GameSituation.getInstance();
        this.scene = scene;
        rootPane = new RootPane(initWidth, initHeight, gameSituation.isSinglePlayer());
        this.width = initWidth;
        this.height = initHeight;
        preBossMapController = new PreBossMapController(gameSituation.isSinglePlayer());
        spacecraftController1 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft1(), rootPane.getPreBossMapView1(), preBossMapController.getPreBossMap());
        if(!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft2(), rootPane.getPreBossMapView2(), preBossMapController.getPreBossMap());
        scene.setRoot(rootPane);
        initGame();
        initListeners();
    }

    public PreBossGameController(PreBossMap preBossMap, Scene scene, double initWidth, double initHeight) {
        settings = Settings.getInstance();
        this.scene = scene;
        gameSituation = GameSituation.getInstance();
        rootPane = new RootPane(initWidth,initHeight, gameSituation.isSinglePlayer());
        this.width = initWidth;
        this.height = initHeight;
        preBossMapController = new PreBossMapController(preBossMap);

        spacecraftController1 = new SpacecraftController(preBossMapController.getPreBossMap().getSpacecraft1(), rootPane.getPreBossMapView1(), preBossMapController.getPreBossMap());
        if(!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
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
        if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
            spacecraftController2.checkInputs();

    }

    private void initGame(){
        if(gameSituation.isSinglePlayer()|| gameSituation.isSecondCraftDied()){
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
        rootPane.getTopBarView().getMiddleView().refreshSlider(spacecraftController1.getPreBossMapView().getSliderLeft(), 1);

        if(!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
        {
            refreshAndReflectSpacecraft(spacecraftController2.getSpacecraft());
            rootPane.getTopBarView().getMiddleView().refreshSlider(spacecraftController2.getPreBossMapView().getSliderLeft(), 2);

        }
        updateHyperJumpBattery();
        checkEndGame();


    }


    private void refreshAndReflectEnemy() {
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var enemy : preBossMapController.getPreBossMap().getEnemies().values()) {
            ModelToViewEnemy modelToViewEnemy = new ModelToViewEnemy(enemy);
            if (enemy.isDead()) {
                toBeDeleted.add(enemy.getID());
                spacecraftController1.getPreBossMapView().addExplodeAnimation(new ModelToView(enemy));
                if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
                    spacecraftController2.getPreBossMapView().addExplodeAnimation(new ModelToView(enemy));
            }

            spacecraftController1.getPreBossMapView().refreshEnemy(modelToViewEnemy);
            if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
                spacecraftController2.getPreBossMapView().refreshEnemy(modelToViewEnemy);
            rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(enemy));
        }

        for (var it : toBeDeleted) {

            if(!preBossMapController.getPreBossMap().getEnemies().get(it).getBuffType().equals(BuffTypes.EMPTY))
            {
                PositionHelper helper = new PositionHelper(preBossMapController.getPreBossMap().getEnemies().get(it));
                preBossMapController.getPreBossMap().addBuff(
                        new Buff(new Location(helper.getMiddlePointX() - Buff.WIDTH/2, helper.getMiddlePointY() - Buff.HEIGHT/2 ),
                                 preBossMapController.getPreBossMap().getEnemies().get(it).getBuffType())
                );
            }
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
            if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
                spacecraftController2.getPreBossMapView().refreshBullet(new ModelToViewBullet(bullet));

        }
        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteBullet(it);
        }
    }


    private void refreshAndReflectSpacecraft(Spacecraft spacecraft){
        if(!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip()){

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
                gameIsFinishProperty().set(true);
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
                if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
                    spacecraftController2.getPreBossMapView().addExplodeAnimation(new ModelToView(station));
            }
            spacecraftController1.getPreBossMapView().refreshStations(new ModelToViewStation(station));
            if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
                spacecraftController2.getPreBossMapView().refreshStations(new ModelToViewStation(station));
            rootPane.getTopBarView().getMiddleView().refresh(new RadarObject(station));
        }

        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteStation(it);
            //SoundController.explosion();
        }
    }

    private void refreshAndReflectBuff(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var buff : preBossMapController.getPreBossMap().getBuffs().values()) {
            if (buff.isDead()) {
                toBeDeleted.add(buff.getID());
                if(buff.getOwnerID() == spacecraftController1.getSpacecraft().getID()){
                    spacecraftController1.applyBuff(buff.getBuffType());
                }
                if( !gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip() &&
                    buff.getOwnerID() == spacecraftController2.getSpacecraft().getID()){
                    spacecraftController2.applyBuff(buff.getBuffType());
                }
            }
            spacecraftController1.getPreBossMapView().refreshBuff(new ModelToViewBuff(buff));
            if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
                spacecraftController2.getPreBossMapView().refreshBuff(new ModelToViewBuff(buff));

        }
        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteBuff(it);
        }
    }

    private void refreshAndReflectMeteor(){
        ArrayList<Long> toBeDeleted = new ArrayList<>();
        for (var meteor : preBossMapController.getPreBossMap().getMeteors().values()) {
            if (meteor.isDead()) {
                toBeDeleted.add(meteor.getID());
            }
            spacecraftController1.getPreBossMapView().refreshMeteor(new ModelToViewMeteor(meteor));
            if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip())
                spacecraftController2.getPreBossMapView().refreshMeteor(new ModelToViewMeteor(meteor));
        }
        for (var it : toBeDeleted) {
            preBossMapController.getPreBossMap().deleteMeteor(it);
        }
    }

    private void refreshSpacecraftGameInfo(){
        if((gameSituation.isTwoPlayerSingleShip() && gameSituation.isSecondCraftDied())|| gameSituation.isSinglePlayer() || !gameSituation.isTwoPlayerSingleShip()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView1 = new ModelToSpacecraftInfoView(spacecraftController1.getSpacecraft());
            rootPane.getTopBarView().getSpacecraftInfoView1().refresh(modelToSpacecraftInfoView1);
        }
        if(!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView2 = new ModelToSpacecraftInfoView(spacecraftController2.getSpacecraft());
            rootPane.getTopBarView().getSpacecraftInfoView2().refresh(modelToSpacecraftInfoView2);
        }
        if(!gameSituation.isSinglePlayer() && gameSituation.isTwoPlayerSingleShip() && gameSituation.isFirstCraftDied()){
            ModelToSpacecraftInfoView modelToSpacecraftInfoView2 = new ModelToSpacecraftInfoView(spacecraftController1.getSpacecraft());
            rootPane.getTopBarView().getSpacecraftInfoView2().refresh(modelToSpacecraftInfoView2);
        }
    }

    private void refreshAndReflectGameInfo() {
        increaseScore();
        rootPane.getTopBarView().getRightView().refresh(new ModelToGameInfoView(gameSituation.getScore(),
                preBossMapController.getPreBossMap().getEnemies().size(), preBossMapController.getPreBossMap().getStations().size()));
    }

    private void checkEndGame(){
        if(preBossMapController.getPreBossMap().getEnemies().size() == 0 && preBossMapController.getPreBossMap().getStations().size() == 0){
            gameIsFinishProperty().set(true);
            gameSituation.setIsPreBossFinishedSuccessfully(true);
            animationTimer.stop();
        }
    }

    private void updateHyperJumpBattery(){
        Spacecraft spacecraft1 = spacecraftController1.getSpacecraft();
        spacecraft1.setBatteryTimer(spacecraft1.getBatteryTimer() + 1);
        spacecraft1.setBatteryTimer(spacecraft1.getBatteryTimer() % Spacecraft.HYPERJUMP_PERIOD);
        if(spacecraft1.getHyperJumpBattery() < Spacecraft.MAX_HYPERJUMP_ENERGY && spacecraft1.getBatteryTimer() == 0){
            spacecraft1.setHyperJumpBattery(spacecraft1.getHyperJumpBattery() + 1);
        }
        if (!gameSituation.isSinglePlayer() && !gameSituation.isTwoPlayerSingleShip()){
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
            if (e.getCode().equals(Settings.getInstance().getUp())) {
                spacecraftController1.setUpKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getDown())) {
                spacecraftController1.setDownKeyPressed(true);
            }
            if (e.getCode().equals(Settings.getInstance().getLeft())) {
                spacecraftController1.setLeftKeyPressed(true);
                spacecraftController1.getSpacecraft().setDirectionLeft(true);
            }
            if (e.getCode().equals(Settings.getInstance().getRight())) {
                spacecraftController1.setRightKeyPressed(true);
                spacecraftController1.getSpacecraft().setDirectionLeft(false);
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
            if (e.getCode().equals(KeyCode.ESCAPE))
                gameOnChange.set(true);
            if (e.getCode().equals(Settings.getInstance().getSmartBomb()))
                spacecraftController1.activateSmartBomb();
            if (e.getCode().equals(Settings.getInstance().getHyperJump()))
                spacecraftController1.doHyperJump();

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
                spacecraftController1.getSpacecraft().setDirectionLeft(true);
            }
            if (e.getCode().equals(Settings.getInstance().getRight2())) {
                spacecraftController1.setRightKeyPressed(true);
                spacecraftController1.getSpacecraft().setDirectionLeft(false);
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
            if (e.getCode().equals(KeyCode.ESCAPE))
                gameOnChange.set(true);
            if (e.getCode().equals(Settings.getInstance().getSmartBomb2()))
                spacecraftController1.activateSmartBomb();
            if (e.getCode().equals(Settings.getInstance().getHyperJump2()))
                spacecraftController1.doHyperJump();
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
                spacecraftController1.getSpacecraft().setDirectionLeft(true);
            }
            if (e.getCode().equals(Settings.getInstance().getRight())) {
                spacecraftController1.setRightKeyPressed(true);
                spacecraftController1.getSpacecraft().setDirectionLeft(false);
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
                spacecraftController2.getSpacecraft().setDirectionLeft(true);
            }
            if (e.getCode().equals(Settings.getInstance().getRight2())) {
                spacecraftController2.setRightKeyPressed(true);
                spacecraftController2.getSpacecraft().setDirectionLeft(false);
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
            if (e.getCode().equals(Settings.getInstance().getHyperJump()))
                spacecraftController1.doHyperJump();
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
            if (e.getCode().equals(KeyCode.ESCAPE))
                gameOnChange.set(true);
            if (e.getCode().equals(Settings.getInstance().getSmartBomb2()))
                spacecraftController2.activateSmartBomb();
            if (e.getCode().equals(Settings.getInstance().getHyperJump2()))
                spacecraftController2.doHyperJump();

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

