package org.openjfx.controller.menuController;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.openjfx.fileManager.FileController;
import org.openjfx.model.menuEntities.GameSituation;

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



    public MainController(Stage stage){
        this.stage = stage;
        scene = new Scene(new Pane());
        initMainController();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        gameSituation = GameSituation.getInstance();
        stage.show();
        fileController = new FileController();
        load();
        //gameSituation =
    }

    private void load(){
        //fileController.loadHighScores();
        //fileController.loadPassedLevelInfo();
        //fileController.loadKeys();
        //fileController.loadGame();
    }

    private void initMainController(){
        menuController = new MainMenuController(scene);
        ChangeListener<Boolean> newGameListener = (observable, oldValue, newValue) ->{
            if(menuController.getIsGameStartPressed().get()) {
                menuController.setIsGameStartPressed(false);
                initGameSituationChecker(true);
                saveGame();
            }
        };
        menuController.getIsGameStartPressed().addListener(newGameListener);

        ChangeListener<Boolean> resumeListener = (observable, oldValue, newValue) ->{
            if(menuController.getIsResumePressed().get()) {
                menuController.setIsResumePressed(false);
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
                fileController.saveGame();
                stage.close();
                System.exit(0);
            }
        };
        menuController.getIsQuitPressed().addListener(quitListener);
    }

    private void initGameSituationChecker(boolean newGame){
        gameSituationChecker = new GameSituationChecker(scene, newGame);
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
                fileController.saveGame();
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
        /*
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                fileController.saveGame();
            }
        };
        timer.schedule(task, 0, 30000);

         */
    }


}
