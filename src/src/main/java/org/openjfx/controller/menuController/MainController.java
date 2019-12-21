package org.openjfx.controller.menuController;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.openjfx.controller.bossSceneControllers.BossGameController;
import org.openjfx.fileManager.FileController;
import org.openjfx.model.menuEntities.GameSaveObj;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.menuEntities.Settings;
import org.openjfx.model.menuEntities.PassedLevelInfo;


import java.util.Timer;
import java.util.TimerTask;

public class MainController {

    private Stage stage;
    private Scene scene;
    private GameSituationChecker gameSituationChecker;
    private MainMenuController menuController;
    private FileController fileController;
    private PauseMenuController pauseMenuController;
    private GameSituation gameSituation;
    private EndGameMenuController endGameMenuController;
    private PassedLevelInfo passedLevelInfo;
    private GameSaveObj gameSaveObj;



    public MainController(Stage stage){
        this.stage = stage;
        scene = new Scene(new Pane());
        fileController = new FileController();


        loadInitialElements();
        initMainController();

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        gameSituation = GameSituation.getInstance();
        stage.show();
    }

    private void loadInitialElements(){
        fileController.loadHighScores();
        fileController.loadKeys();
        fileController.loadPassedLevelInfo();
    }

    private void loadGameElements() {
        fileController.loadGame(); //Burayı kaldır Doğukan
    }

    private void initMainController(){
        menuController = new MainMenuController(scene);
        ChangeListener<Boolean> newGameListener = (observable, oldValue, newValue) ->{
            if(menuController.getIsGameStartPressed().get()) {
                gameSituation = GameSituation.getInstance();
                System.out.println(gameSituation.isSinglePlayer() + "in main");
                passedLevelInfo = PassedLevelInfo.getInstance();
                gameSaveObj = GameSaveObj.getInstance();
                menuController.setIsGameStartPressed(false);
                initGameSituationChecker(true);
            }
        };
        menuController.getIsGameStartPressed().addListener(newGameListener);

        ChangeListener<Boolean> resumeListener = (observable, oldValue, newValue) ->{
            if(menuController.getIsResumePressed().get()) {
                gameSituation = GameSituation.getInstance();
                menuController.setIsResumePressed(false);
                loadGameElements();
                initGameSituationChecker(false);
            }
        };
        menuController.getIsResumePressed().addListener(resumeListener);

        ChangeListener<Boolean> saveSettingsListener = (observable, oldValue, newValue) ->{
            if(menuController.getIsSaveSettingsPressed().get()) {
                menuController.setIsSaveSettingsPressed(false);
                fileController.saveKeys();
            }
        };
        menuController.getIsSaveSettingsPressed().addListener(saveSettingsListener);

        ChangeListener<Boolean> quitListener = (observable, oldValue, newValue) ->{
            if(menuController.getIsQuitPressed().get()) {
                menuController.setIsQuitPressed(false);
                //fileController.saveGame();  //burayı da kaldır
                stage.close();
                System.exit(0);
            }
        };
        menuController.getIsQuitPressed().addListener(quitListener);

        ChangeListener<Boolean> bossSceneListener = (observable, oldValue, newValue) ->{
            if(menuController.getIsBossScene().get()) {
                menuController.setIsBossScene(false);
                initGameSituationChecker(0);
            }
        };
        menuController.getIsBossScene().addListener(bossSceneListener);


    }

    private void initGameSituationChecker(int bossScene){
        gameSituationChecker = new GameSituationChecker(scene, bossScene);
        ChangeListener<Boolean> endGameListener = (observable, oldValue, newValue) ->{
            if(gameSituationChecker.getIsEnd().get()) {
                gameSituationChecker.setIsEnd(false);
                initEndGameMenuController();
            }
        };
        gameSituationChecker.getIsEnd().addListener(endGameListener);

        ChangeListener<Boolean> pauseGameListener = (observable, oldValue, newValue) ->{
            if(gameSituationChecker.getIsPaused().get()) {
                gameSituationChecker.setIsPaused(false);
                initPauseMenuController();
            }
        };
        gameSituationChecker.getIsPaused().addListener(pauseGameListener);
    }



    private void initGameSituationChecker(boolean newGame){
        gameSituationChecker = new GameSituationChecker(scene, newGame);
        //saveGame(); //burayı da kaldır
        ChangeListener<Boolean> endGameListener = (observable, oldValue, newValue) ->{
            if(gameSituationChecker.getIsEnd().get()) {
                gameSituationChecker.setIsEnd(false);
                initEndGameMenuController();
            }
        };
        gameSituationChecker.getIsEnd().addListener(endGameListener);

        ChangeListener<Boolean> pauseGameListener = (observable, oldValue, newValue) ->{
            if(gameSituationChecker.getIsPaused().get()) {
                gameSituationChecker.setIsPaused(false);
                initPauseMenuController();
            }
        };
        gameSituationChecker.getIsPaused().addListener(pauseGameListener);
    }
    private void initPauseMenuController(){
        if(gameSituation.isIsPreBossFinishedSuccessfully()) {
            pauseMenuController = new PauseMenuController(scene, gameSituationChecker.getBossGameController(), menuController.getMainMenu());
        }
        else
            pauseMenuController = new PauseMenuController(scene, gameSituationChecker.getPreBossGameController(), menuController.getMainMenu());

        ChangeListener<Boolean> saveGameListener = (observable, oldValue, newValue) ->{
            if (pauseMenuController.getIsSavePressed().get()) {
                pauseMenuController.setIsSavePressed(false);
                fileController.saveGame(); //burayı da
            }
        };
        pauseMenuController.getIsSavePressed().addListener(saveGameListener);
    }

    private void initEndGameMenuController(){
        endGameMenuController = new EndGameMenuController(scene, menuController.getMainMenu());
        ChangeListener<Boolean> highScoreListener = (observable, oldValue, newValue) ->{
            if(endGameMenuController.getIsHighScoreChanged().get()) {
                endGameMenuController.setIsHighScoreChanged(false);
                fileController.saveHighScores();
            }
        };
        endGameMenuController.getIsHighScoreChanged().addListener(highScoreListener);

        ChangeListener<Boolean> passedLevelListener = (observable, oldValue, newValue) ->{
            if(endGameMenuController.getIsPassedLevelInfoChanged().get()) {
                endGameMenuController.setIsPassedLevelInfoChanged(false);
                fileController.savePassedLevelInfo();
            }
        };
        endGameMenuController.getIsPassedLevelInfoChanged().addListener(passedLevelListener);

        ChangeListener<Boolean> restartListener = (observable, oldValue, newValue) ->{
            if(endGameMenuController.getIsRestartPressed().get()) {
                endGameMenuController.setIsRestartPressed(false);
                gameSituationChecker.restartTheLevel();
                menuController.enablePassedLevels();
            }
        };
        endGameMenuController.getIsRestartPressed().addListener(restartListener);

        ChangeListener<Boolean> nextLevelListener = (observable, oldValue, newValue) ->{
            if(endGameMenuController.getIsNextLevelPressed().get()) {
                endGameMenuController.setIsNextLevelPressed(false);
                gameSituationChecker.startNextLevel();
            }
        };
        endGameMenuController.getIsNextLevelPressed().addListener(nextLevelListener);
    }

    private void saveGame(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                GameSaveObj.getInstance().setPreBossMap(gameSituationChecker.getPreBossMap());
                GameSaveObj.getInstance().setBossMap(gameSituationChecker.getBossMap());
                fileController.saveGame();
            }
        };
        timer.schedule(task, 0, 30000);
    }
}
