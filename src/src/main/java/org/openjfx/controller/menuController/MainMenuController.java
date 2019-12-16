package org.openjfx.controller.menuController;

import javafx.application.Platform;
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
import org.openjfx.model.menuEntities.GameSaveObj;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.menuEntities.PassedLevelInfo;
import org.openjfx.view.menuView.*;

public class MainMenuController {

    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    private Stage stage;
    private Scene scene;
    private MainMenu mainMenu;
    private SpacecraftSelection spacecraftSelection;
    private LevelSelection levelSelection;
    private HowToPlay howToPlay;
    private Settings settings;
    private Credits credits;
    private PauseMenu pauseMenu;

    private PreBossGameController preBossGameController;
    private BossGameController bossGameController;
    private PauseMenuController pauseMenuController;
    private FileController fileController;

    public MainMenuController(Stage stage) {
        this.stage = stage;
        mainMenu = new MainMenu();
        scene = new Scene(mainMenu, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
        fileController = new FileController();
        initMainController();
    }

    private void start(){
        GameSituation.getInstance().resetVar();
        GameSituation.getInstance().resetScore();
        preBossGameController = new PreBossGameController(scene,primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight());
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
            preBossGameController.setWidth(stage.getWidth());
            preBossGameController.setHeight(stage.getHeight());
        };
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
    }

    private void startBoss() {
        bossGameController = new BossGameController(scene, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

    }

    private void saveGame(){
        fileController.loadGame();
        GameSaveObj gameSaveObj = GameSaveObj.getInstance();
        System.out.println(gameSaveObj.getPreBossMap());
        //preBossGameController = new PreBossGameController(scene,primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight(),
                //GameSaveObj.getInstance().getPreBossMap());
    }

    private void passBossScene(){
    }

    private void finishGame(){
    }

    private void ifPaused(){
        //gameInfodan bakarak dinleyecek hangi scenede oldupğumuza karar verip şimdilik böyle koydum
        if (preBossGameController != null) {
            ChangeListener<Boolean> gameOnChangeListener = (observable, oldValue, newValue) -> {
                if (preBossGameController.gameOnChangeProperty().get()) {
                    if (preBossGameController.isGameOn()) {
                        preBossGameController.getAnimationTimer().stop();
                        preBossGameController.setGameOn(false);
                        preBossGameController.getScene().getRoot().setEffect(new GaussianBlur());
                        pauseMenuController = new PauseMenuController(scene, preBossGameController, mainMenu);
                    }
                    preBossGameController.setGameOnChange(false);
                }
            };
            preBossGameController.gameOnChangeProperty().addListener(gameOnChangeListener);
        }
        if(bossGameController != null) {
            ChangeListener<Boolean> gameOnChangeListener2 = (observable, oldValue, newValue) -> {
                if (bossGameController.gameOnChangeProperty().get()) {
                    if (bossGameController.isGameOn()) {
                        bossGameController.getAnimationTimer().stop();
                        bossGameController.setGameOn(false);
                        bossGameController.getScene().getRoot().setEffect(new GaussianBlur());
                        pauseMenuController = new PauseMenuController(scene, bossGameController, mainMenu);
                    }
                    bossGameController.setGameOnChange(false);
                }
            };
            bossGameController.getGameOnChange().addListener(gameOnChangeListener2);
        }
    }

/*
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
ON CLICK
--------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------
 */

    private void initMainController(){
        mainMenu.getSinglePlayerBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start();
                ifPaused();
            }
        });

        mainMenu.getTwoPlayersBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startBoss();
                ifPaused();
            }
        });

        mainMenu.getHowToPlayBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passBossScene();
            }
        });

        mainMenu.getCreditsBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        mainMenu.getExitBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }

}
