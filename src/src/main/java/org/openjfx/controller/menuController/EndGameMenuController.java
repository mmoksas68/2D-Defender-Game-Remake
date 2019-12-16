package org.openjfx.controller.menuController;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import org.openjfx.view.menuView.MainMenu;

public class EndGameMenuController {

    private Scene scene;
    private MainMenu mainMenuView;

    private BooleanProperty isHighScoreChanged, isPassedLevelInfoChanged, isRestartPressed, isNextLevelPressed;

    public EndGameMenuController(Scene scene, MainMenu mainMenuView){
        this.scene= scene;
        this.mainMenuView = mainMenuView;
        isHighScoreChanged = new SimpleBooleanProperty(false);
        isPassedLevelInfoChanged = new SimpleBooleanProperty(false);
        isRestartPressed = new SimpleBooleanProperty(false);
        isNextLevelPressed = new SimpleBooleanProperty(false);
        initEndGameMenuListeners();
    }

    private void updateHighScores(){
    }

    private void updatePassedLevelInfo(){

    }

    private void restart(){

    }

    private void exit(){

    }

    private void nextLevel(){

    }

    private void initEndGameMenuListeners(){


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
    }

    public void setIsRestartPressed(boolean b) {
    }

    public void setIsHighScoreChanged(boolean b) {
    }

    public void setIsPassedLevelInfoChanged(boolean b) {
    }
}
