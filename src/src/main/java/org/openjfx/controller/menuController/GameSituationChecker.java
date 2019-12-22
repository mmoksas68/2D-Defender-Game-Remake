package org.openjfx.controller.menuController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Screen;
import org.openjfx.controller.bossSceneControllers.BossController;
import org.openjfx.controller.bossSceneControllers.BossGameController;
import org.openjfx.controller.preBossSceneControllers.PreBossGameController;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.menuEntities.GameSaveObj;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.menuEntities.PassedLevelInfo;
import org.openjfx.model.menuEntities.Settings;
import org.openjfx.model.preBossEntities.PreBossMap;

public class GameSituationChecker {

    private GameSituation gameSituation;
    private Settings settings;
    private BooleanProperty isPaused, isEnd;
    private PreBossGameController preBossGameController;
    private BossGameController bossGameController;
    private GameSaveObj gameSaveObj;
    private Scene scene;
    private PassedLevelInfo passedLevelInfo;
    private PreBossMap preBossMap;
    private BossMap bossMap;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private boolean newGame;
    private static boolean isChanged = false;
    private static boolean isChanged2 = false;

    public GameSituationChecker(Scene scene){
        this.scene = scene;
        isPaused = new SimpleBooleanProperty(false);
        isEnd = new SimpleBooleanProperty(false);
        gameSaveObj = GameSaveObj.getInstance();
        passedLevelInfo = PassedLevelInfo.getInstance();
        gameSituation = GameSituation.getInstance();
    }

    /*public GameSituationChecker(Scene scene, int bossScene){
        this.scene = scene;
        isPaused = new SimpleBooleanProperty(false);
        isEnd = new SimpleBooleanProperty(false);
        gameSaveObj = GameSaveObj.getInstance();
        passedLevelInfo = PassedLevelInfo.getInstance();
        gameSituation = GameSituation.getInstance();
        gameSituation.resetVar();
        gameSituation.setSinglePlayer(false);
        gameSituation.setIsPreBossFinishedSuccessfully(true);
        gameSituation.setIsBossFinished(false);
        bossGameController = new BossGameController(scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    } */

    public void startGame(boolean newGame){
        if(newGame){
            gameSituation.resetVar();
            preBossGameController = new PreBossGameController(scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
            ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
                preBossGameController.setWidth(scene.getWindow().getWidth());
                preBossGameController.setHeight(scene.getWindow().getHeight());
            };
            scene.getWindow().widthProperty().addListener(stageSizeListener);
            scene.getWindow().heightProperty().addListener(stageSizeListener);
            ifPausedPreBoss();
            ifEndPreBoss();
            isChanged = true;
        }
        else{
            if (!gameSituation.isIsPreBossFinished() && !gameSituation.isIsPreBossFinishedSuccessfully()) {
                preBossGameController = new PreBossGameController(GameSaveObj.getInstance().getPreBossMap(), scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
                ifPausedPreBoss();
                if(!isChanged){
                    ifEndPreBoss();
                    isChanged = true;
                }
            } else if (!gameSituation.isIsBossFinished() && !gameSituation.isIsBossFinishedSuccessfully()) {
                bossGameController = new BossGameController(GameSaveObj.getInstance().getBossMap(), scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
                ifPausedBoss();
                if(!isChanged2){
                    ifEndBoss();
                    isChanged2 = true;
                }

            }
        }

    }
        private void ifPausedBoss() {
            if (!(bossGameController == null) && !gameSituation.isIsBossFinished()) {
                ChangeListener<Boolean> gameOnChangeListener2 = (observable, oldValue, newValue) -> {
                    if (bossGameController.gameOnChangeProperty().get()) {
                        if (bossGameController.isGameOn()) {
                            bossGameController.getAnimationTimer().stop();
                            bossGameController.setGameOn(false);
                            bossGameController.getScene().getRoot().setEffect(new GaussianBlur());
                            isPaused.setValue(true);
                        }
                        bossGameController.setGameOnChange(false);
                    }
                };
                bossGameController.getGameOnChange().addListener(gameOnChangeListener2);
            }
        }

        private void ifPausedPreBoss () {
            if (!(preBossGameController == null) && !gameSituation.isIsPreBossFinished()) {
                ChangeListener<Boolean> gameOnChangeListener = (observable, oldValue, newValue) -> {
                    if (preBossGameController.gameOnChangeProperty().get()) {
                        if (preBossGameController.isGameOn()) {
                            preBossGameController.getAnimationTimer().stop();
                            preBossGameController.getScoreTimeline().stop();
                            preBossGameController.setGameOn(false);
                            preBossGameController.getScene().getRoot().setEffect(new GaussianBlur());
                            isPaused.setValue(true);
                        }
                        preBossGameController.setGameOnChange(false);
                    }
                };
                preBossGameController.gameOnChangeProperty().addListener(gameOnChangeListener);
            }
        }
    private void ifEndPreBoss() {
        ChangeListener<Boolean> isEndListener = (observable, oldValue, newValue) -> {
            if (gameSituation.isIsPreBossFinished() ){//&& preBossGameController.gameOnChangeProperty().get()) {
                //if(preBossGameController.isGameOn()){
                    preBossGameController.getAnimationTimer().stop();
                    /*preBossGameController.getScoreTimeline().stop();
                    preBossGameController.setGameOn(false); */
                    isEnd.set(true);
                
            }
        };
        gameSituation.isPreBossFinishedProperty().addListener(isEndListener);


        ChangeListener<Boolean> isSuccesfullyEndListener = ((observableValue, aBoolean, t1) -> {
            if(gameSituation.isIsPreBossFinishedSuccessfully()){
                preBossGameController.getAnimationTimer().stop();
                changePreBossToBoss();
                gameSituation.setIsBossFinishedSuccessfully(false);
            }
        });
        gameSituation.isPreBossFinishedSuccessfullyProperty().addListener(isSuccesfullyEndListener);
    }

    private void ifEndBoss() {
        ChangeListener<Boolean> isSuccesfullyEndBossListener = ((observableValue, aBoolean, t1) -> {
            if (gameSituation.isIsBossFinishedSuccessfully()) {
                bossGameController.getAnimationTimer().stop();
                isEnd.set(true);
            }
        });
        gameSituation.isBossFinishedSuccessfullyProperty().addListener(isSuccesfullyEndBossListener);

        ChangeListener<Boolean> isEndBossListener = ((observableValue, aBoolean, t1) -> {
            if (gameSituation.isIsBossFinished()) {
                bossGameController.getAnimationTimer().stop();
                isEnd.set(true);
            }
        });
        gameSituation.isBossFinishedProperty().addListener(isEndBossListener);
    }

    private void changePreBossToBoss(){
        if(bossGameController != null){
            bossGameController.getAnimationTimer().stop();
            bossGameController = null;
        }
        this.bossGameController = new BossGameController(scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        initBossListeners();
        isChanged2 = true;
    }


    private void pauseGame(){

    }

    private void endGame(){
        
    }
    
    private void initBossListeners(){
        ifEndBoss();
        ifPausedBoss();
    }

    private void initPreBossListener(){
        ifEndPreBoss();
        ifPausedPreBoss();
    }
    
    public BooleanProperty getIsPaused(){
        return isPaused;
    }

    public BooleanProperty getIsEnd(){
        return isEnd;
    }


    public PreBossGameController getPreBossGameController(){
        return preBossGameController;
    }

    public BossGameController getBossGameController(){
        return bossGameController;
    }

    public void restartTheLevel(){

    }

    public void startNextLevel(){
    }

    public void setIsPaused(boolean b) {
        isPaused.set(b);
    }

    public void setIsEnd(boolean b) {
        isEnd.set(b);
    }

    public PreBossMap getPreBossMap(){
        if(preBossGameController != null)
            return preBossGameController.getPreBossMapController().getPreBossMap();
        return null;
    }

    public BossMap getBossMap(){
        if(bossGameController != null)
            return bossGameController.getBossMapController().getBossMap();
        return null;
    }


    public boolean isNewGame() {
        return newGame;
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }
}
