package org.openjfx.controller.menuController;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.menuEntities.HighScoreInfo;
import org.openjfx.model.menuEntities.PassedLevelInfo;
import org.openjfx.view.menuView.EndGameMenuView;
import org.openjfx.view.menuView.MainMenu;

public class EndGameMenuController {

    private Scene primaryScene, scene;
    private Stage stage;
    private MainMenu mainMenuView;
    private BooleanProperty isHighScoreChanged, isPassedLevelInfoChanged, isRestartPressed, isNextLevelPressed;
    private GameSituation gameSituation = GameSituation.getInstance();
    private PassedLevelInfo passedLevelInfo = PassedLevelInfo.getInstance();
    private EndGameMenuView endGame;

    public EndGameMenuController(Scene scene, MainMenu mainMenuView){
        this.primaryScene= scene;
        this.mainMenuView = mainMenuView;
        isHighScoreChanged = new SimpleBooleanProperty(false);
        isPassedLevelInfoChanged = new SimpleBooleanProperty(false);
        isRestartPressed = new SimpleBooleanProperty(false);
        isNextLevelPressed = new SimpleBooleanProperty(false);
        endGame = new EndGameMenuView();

        stage = new Stage();
        stage.initOwner(primaryScene.getWindow());
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.scene = new Scene(endGame,350,150);
        stage.setScene(this.scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.initOwner(primaryScene.getWindow());

        stage.show();
        initEndGameMenuListeners();
    }

    private void updateHighScores(){
        if(gameSituation.isIsBossFinishedSuccessfully()){
            boolean updated = HighScoreInfo.getInstance().updateScores(gameSituation.getLevel(), gameSituation.isSinglePlayer(), gameSituation.getScore());
            if(updated)
                isHighScoreChanged.setValue(true);
        }
    }

    private void updatePassedLevelInfo(){
        if(gameSituation.isIsBossFinishedSuccessfully()){
            if(passedLevelInfo.getIsLevelPassed(gameSituation.getLevel())) {
                passedLevelInfo.setLevelPassed(gameSituation.getLevel(), true);
                isPassedLevelInfoChanged.setValue(true);
            }
        }
    }

    private void restart(){
        isRestartPressed.setValue(true);
    }

    private void exit(){
        scene.setRoot(mainMenuView);
        stage.close();
    }

    private void nextLevel(){
        isNextLevelPressed.setValue(true);
    }

    private void initEndGameMenuListeners(){
        endGame.getNextLevelBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextLevel();
            }
        });

        endGame.getMenuBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exit();
            }
        });

        endGame.getRestartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                restart();
            }
        });
    }

    public BooleanProperty getIsHighScoreChanged(){
        return isHighScoreChanged;
    }

    public BooleanProperty getIsPassedLevelInfoChanged(){
        return isPassedLevelInfoChanged;
    }

    public BooleanProperty getIsRestartPressed(){
        return isRestartPressed;
    }

    public BooleanProperty getIsNextLevelPressed(){
        return isNextLevelPressed;
    }

    public void setIsNextLevelPressed(boolean b) {
        isNextLevelPressed.setValue(b);
    }

    public void setIsRestartPressed(boolean b) {
        isRestartPressed.setValue(b);
    }

    public void setIsHighScoreChanged(boolean b) {
        isHighScoreChanged.setValue(b);
    }

    public void setIsPassedLevelInfoChanged(boolean b) {
        isPassedLevelInfoChanged.setValue(b);
    }
}
