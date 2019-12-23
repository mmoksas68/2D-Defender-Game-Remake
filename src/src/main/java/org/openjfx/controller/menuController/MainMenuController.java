package org.openjfx.controller.menuController;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.menuEntities.PassedLevelInfo;
import org.openjfx.model.menuEntities.*;
import org.openjfx.view.menuView.*;

public class MainMenuController {

    private Scene scene;
    private MenuSceneContainer menuSceneContainer;
    private GameSituation gameSituation;
    private Settings settings;
    private PassedLevelInfo passedLevelInfo = PassedLevelInfo.getInstance();
    private BooleanProperty isGameStartPressed, isSaveSettingsPressed, isQuitPressed, isResumePressed, isBossScene, isAutoSavePressed;;
    private int theme;



    public MainMenuController(Scene scene) {
        menuSceneContainer = new MenuSceneContainer();
        this.scene = scene;
        scene.setRoot(menuSceneContainer.getMainMenu());
        isGameStartPressed = new SimpleBooleanProperty(false);
        isSaveSettingsPressed = new SimpleBooleanProperty(false);
        isQuitPressed = new SimpleBooleanProperty(false);
        isResumePressed = new SimpleBooleanProperty(false);
        isAutoSavePressed = new SimpleBooleanProperty(false);
        settings = Settings.getInstance();
        initButtonListeners();
        gameSituation = GameSituation.getInstance();
        settings = Settings.getInstance();
        isBossScene = new SimpleBooleanProperty(false);
        setTheme();
    }

    private void setTheme(){
        theme = settings.getTheme();
        if(theme == 0) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:cssFiles/theme1");
        }
        else if(theme == 1) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:cssFiles/theme2");
        }
        else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:cssFiles/theme3.css");
        }
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
        gameSituation.setLevel(menuSceneContainer.getLevelSelection().getSelectedItem());
        isGameStartPressed.setValue(true);
    }

    private void howToPlay(){
        menuSceneContainer.getHowToPlay().setImage();
        scene.setRoot(menuSceneContainer.getHowToPlay());
    }

    private void settings(){
        menuSceneContainer.getSettings().initializeSettingsView();
        scene.setRoot(menuSceneContainer.getSettings());
    }

    private void saveSettings(){

        if(menuSceneContainer.getSettings().isAllKeysDistinct()) {
            settings.setVolume(menuSceneContainer.getSettings().getVolume());
            settings.setUp(menuSceneContainer.getSettings().getUp());
            settings.setDown(menuSceneContainer.getSettings().getDown());
            settings.setRight(menuSceneContainer.getSettings().getRight());
            settings.setLeft(menuSceneContainer.getSettings().getLeft());
            settings.setHyperJump(menuSceneContainer.getSettings().getHyperJump());
            settings.setSmartBomb(menuSceneContainer.getSettings().getSmartBomb());
            settings.setUp2(menuSceneContainer.getSettings().getUp2());
            settings.setDown2(menuSceneContainer.getSettings().getDown2());
            settings.setRight2(menuSceneContainer.getSettings().getRight2());
            settings.setLeft2(menuSceneContainer.getSettings().getLeft2());
            settings.setHyperJump2(menuSceneContainer.getSettings().getHyperJump2());
            settings.setSmartBomb2(menuSceneContainer.getSettings().getSmartBomb2());
            settings.setFire(menuSceneContainer.getSettings().getFire());
            settings.setFire2(menuSceneContainer.getSettings().getFire2());
            settings.setTheme(menuSceneContainer.getSettings().getSelectedTheme());
            setTheme();
            isSaveSettingsPressed.setValue(true);
        }

        else
            menuSceneContainer.getSettings().giveError();

    }

    private void highScores(){
        scene.setRoot(menuSceneContainer.getHighScoresView());
    }

    private void autoSave(){
        isAutoSavePressed.setValue(true);
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

        menuSceneContainer.getMainMenu().getBossSceneButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isBossScene.set(true);
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

        menuSceneContainer.getSettings().getDefaultSettings().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuSceneContainer.getSettings().setDefaultKeys();
            }
        });

        if(menuSceneContainer.getMainMenu().getAutoSaveBtn() != null){
            menuSceneContainer.getMainMenu().getAutoSaveBtn().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    autoSave();
                }
            });
        }

    }



    public MainMenu getMainMenu(){
        return menuSceneContainer.getMainMenu();
    }

    public void enablePassedLevels(){
        if(passedLevelInfo.getIsLevelPassed(1))
            menuSceneContainer.getLevelSelection().enableLevel2();
        if(passedLevelInfo.getIsLevelPassed(2))
            menuSceneContainer.getLevelSelection().enableLevel3();
    }

    public BooleanProperty getIsAutoSavePressed(){
        return  isAutoSavePressed;
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

    public void setIsAutoSavePressed(boolean value){
        isAutoSavePressed.set(value);
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


    public void setIsBossScene(boolean b){isBossScene.set(b);}

    public BooleanProperty getIsBossScene(){return isBossScene;}


    public void enableResumeBtn(){
        menuSceneContainer.getMainMenu().enableResumeButton();
    }

    public void disableResumeBtn(){
        menuSceneContainer.getMainMenu().disableResumeButton();
    }

    public void enableAutoSaveBtn(){
        menuSceneContainer.getMainMenu().enableAutoSaveBtn();
    }

    public void disableAutoSaveBtn(){
        menuSceneContainer.getMainMenu().disableAutoSaveBtn();
    }
}
