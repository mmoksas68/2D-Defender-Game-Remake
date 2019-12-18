package org.openjfx.controller.menuController;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.openjfx.controller.bossSceneControllers.BossGameController;
import org.openjfx.controller.preBossSceneControllers.PreBossGameController;
import org.openjfx.fileManager.FileController;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.menuEntities.PassedLevelInfo;
import org.openjfx.model.menuEntities.*;
import org.openjfx.view.menuView.*;

public class MainMenuController {

    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    private Scene scene;
    private MenuSceneContainer menuSceneContainer;
    private PreBossGameController preBossGameController;
    private BossGameController bossGameController;

    private HighScoreInfo highScore;
    private GameSituation gameSituation;
    private Settings settings;
    private PassedLevelInfo passedLevelInfo = PassedLevelInfo.getInstance();
    private BooleanProperty isGameStartPressed, isSaveSettingsPressed, isQuitPressed, isResumePressed;


    public MainMenuController(Scene scene) {
        menuSceneContainer = new MenuSceneContainer();
        this.scene = scene;
        scene.setRoot(menuSceneContainer.getMainMenu());
        isGameStartPressed = new SimpleBooleanProperty(false);
        isSaveSettingsPressed = new SimpleBooleanProperty(false);
        isQuitPressed = new SimpleBooleanProperty(false);
        isResumePressed = new SimpleBooleanProperty(false);
        initButtonListeners();
        gameSituation = GameSituation.getInstance();
    }

    private void playSinglePlayer(){
        gameSituation.setSinglePlayer(true);
        scene.setRoot(menuSceneContainer.getSpacecraftSelection1());
    }

    private void playTwoPlayer(){
        gameSituation.setSinglePlayer(false);
        scene.setRoot(menuSceneContainer.getSpacecraftSelection1());
    }


    private void nextInSpacecraftScreen(){
        if(gameSituation.isSinglePlayer())
            scene.setRoot(menuSceneContainer.getLevelSelection());
        else
            scene.setRoot(menuSceneContainer.getSpacecraftSelection2());

        gameSituation.setSpacecraft1(menuSceneContainer.getSpacecraftSelection1().getSelectedItem());
    }

    private void backInSpacecraftScreen2(){
        scene.setRoot(menuSceneContainer.getSpacecraftSelection1());
    }

    private void nextInSpacecraftScreen2(){
        scene.setRoot(menuSceneContainer.getLevelSelection());

        gameSituation.setSpacecraft2(menuSceneContainer.getSpacecraftSelection2().getSelectedItem());
    }

    private void backInLevelScreen(){
        if(gameSituation.isSinglePlayer())
            scene.setRoot(menuSceneContainer.getSpacecraftSelection1());
        else
            scene.setRoot(menuSceneContainer.getSpacecraftSelection2());
    }

    private void start(){
        isGameStartPressed.setValue(true);
    }

    private void howToPlay(){
        scene.setRoot(menuSceneContainer.getHowToPlay());
    }

    private void settings(){
        scene.setRoot(menuSceneContainer.getSettings());
    }

    private void saveSettings(){
        isSaveSettingsPressed.setValue(true);
        settings.setVolume(menuSceneContainer.getSettings().getVolume());
    }

    private void highScores(){
        scene.setRoot(menuSceneContainer.getHighScoresView());
    }


    private void credits(){
        scene.setRoot(menuSceneContainer.getCredits());
    }

    private void quit(){
        isQuitPressed.setValue(true);
    }

    private void backToMenu(){
        scene.setRoot(menuSceneContainer.getMainMenu());
    }

   /* private void startBoss() {
        bossGameController = new BossGameController(scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    }
    */

   private void resume(){
       isResumePressed.setValue(true);
   }



/*
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
ON CLICK
--------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------
 */

    private void initButtonListeners(){
        menuSceneContainer.getMainMenu().getSinglePlayerBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playSinglePlayer();
            }
        });

        menuSceneContainer.getMainMenu().getTwoPlayersBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playTwoPlayer();
            }
        });

        menuSceneContainer.getMainMenu().getHowToPlayBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                howToPlay();
            }
        });

        menuSceneContainer.getMainMenu().getCreditsBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                credits();
            }
        });

        menuSceneContainer.getMainMenu().getScoresBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //fileController.saveHighScore();
                //System.out.println("saved");
                //fileController.loadHighScore();
                highScores();
            }
        });


        menuSceneContainer.getMainMenu().getExitBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                quit();
            }
        });


        menuSceneContainer.getMainMenu().getSettingsBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settings();
            }
        });

        menuSceneContainer.getCredits().getMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backToMenu();
            }
        });

        menuSceneContainer.getHowToPlay().getMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backToMenu();
            }
        });

        menuSceneContainer.getHighScoresView().getMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backToMenu();
            }
        });

        menuSceneContainer.getLevelSelection().getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backInLevelScreen();
            }
        });

        menuSceneContainer.getSpacecraftSelection1().getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backToMenu();
            }
        });


        menuSceneContainer.getSpacecraftSelection2().getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backInSpacecraftScreen2();
            }
        });

        menuSceneContainer.getLevelSelection().getNextButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start();
            }
        });

        menuSceneContainer.getSpacecraftSelection1().getNextButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextInSpacecraftScreen();
            }
        });

        menuSceneContainer.getSpacecraftSelection2().getNextButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextInSpacecraftScreen2();
            }
        });


        menuSceneContainer.getMainMenu().getResumeBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resume();
            }
        });

        menuSceneContainer.getSettings().getSaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveSettings();
            }
        });

        menuSceneContainer.getSettings().getMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backToMenu();
            }
        });

    }

    public MainMenu getMainMenu(){
        return menuSceneContainer.getMainMenu();
    }

    public void enablePassedLevels(){
        if(passedLevelInfo.isLevel1())
            menuSceneContainer.getLevelSelection().enableLevel2();
        if(passedLevelInfo.isLevel2())
            menuSceneContainer.getLevelSelection().enableLevel3();
    }

    public BooleanProperty getIsGameStartPressed(){
        return isGameStartPressed;
    }

    public BooleanProperty getIsSaveSettingsPressed(){
        return isSaveSettingsPressed;
    }

    public BooleanProperty getIsQuitPressed(){
        return isQuitPressed;
    }

    public BooleanProperty getIsResumePressed(){
        return isResumePressed;
    }

    public void setIsQuitPressed(boolean b) {
        isQuitPressed.setValue(b);
    }

    public void setIsSaveSettingsPressed(boolean b) {
        isSaveSettingsPressed.setValue(b);
    }


    public void setIsGameStartPressed(boolean b) {
        isGameStartPressed.setValue(b);
    }

    public void setIsResumePressed(boolean b) {
        isResumePressed.setValue(b);
    }
}
