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

    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    public GameSituationChecker(Scene scene, boolean newGame){
        this.scene = scene;
        isPaused = new SimpleBooleanProperty(false);
        isEnd = new SimpleBooleanProperty(false);
        gameSaveObj = GameSaveObj.getInstance();
        passedLevelInfo = PassedLevelInfo.getInstance();
        gameSituation = GameSituation.getInstance();
        initGameListeners();
        startGame(newGame);
    }

    public GameSituationChecker(Scene scene, int bossScene){
        this.scene = scene;
        isPaused = new SimpleBooleanProperty(false);
        isEnd = new SimpleBooleanProperty(false);
        gameSaveObj = GameSaveObj.getInstance();
        passedLevelInfo = PassedLevelInfo.getInstance();
        gameSituation = GameSituation.getInstance();
        gameSituation.setSinglePlayer(false);
        gameSituation.setIsPreBossFinishedSuccessfully(true);
        gameSituation.setIsBossFinished(false);
        bossGameController = new BossGameController(scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        initGameListeners();
        ifPaused();
        ifEnd();
    }



    private void startGame(boolean newGame){
        if(newGame){
            preBossGameController = new PreBossGameController(scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
            ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
                preBossGameController.setWidth(scene.getWindow().getWidth());
                preBossGameController.setHeight(scene.getWindow().getHeight());
            };
            scene.getWindow().widthProperty().addListener(stageSizeListener);
            scene.getWindow().heightProperty().addListener(stageSizeListener);
        }
        else{
            if(!gameSituation.isIsPreBossFinished())
                preBossGameController = new PreBossGameController(GameSaveObj.getInstance().getPreBossMap(), scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
            else if(!gameSituation.isIsBossFinished())
                bossGameController = new BossGameController(GameSaveObj.getInstance().getBossMap(), scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        }
        ifPaused();
        ifEnd();
    }


    private void ifPaused(){
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
        if(!(bossGameController == null) && !gameSituation.isIsBossFinished()) {
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

    private void ifEnd() {
        if (!(preBossGameController == null)) {
            ChangeListener<Boolean> isEndListener = (observable, oldValue, newValue) -> {
                if (gameSituation.isIsPreBossFinished()) {
                    isEnd.set(true);
                }
            };
            gameSituation.isPreBossFinishedProperty().addListener(isEndListener);
        }
    }



    private void changePreBossToBoss(){


    }


    private void pauseGame(){

    }

    private void endGame(){
        
    }
    
    private void initGameListeners(){
        
        
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


}
