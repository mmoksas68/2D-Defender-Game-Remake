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
import org.openjfx.controller.preBossSceneControllers.PreBossGameController;
import org.openjfx.controller.bossSceneControllers.BossGameController;
import org.openjfx.model.menuEntities.GameSaveObj;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.view.menuView.MainMenu;
import org.openjfx.view.menuView.PauseMenu;

public class PauseMenuController {

    private PauseMenu pauseMenu;
    private Scene scene;
    private Stage stage;
    private Scene primaryScene;
    private PreBossGameController preBossGameController;
    private BossGameController bossGameController;
    private MainMenu mainMenu;
    private BooleanProperty isSavePressed;
    private GameSituation gameSituation;

    PauseMenuController(Scene scene, PreBossGameController preBossGameController, MainMenu mainMenu){
        this.mainMenu = mainMenu;
        this.preBossGameController = preBossGameController;
        gameSituation = GameSituation.getInstance();
        isSavePressed = new SimpleBooleanProperty(false);
        pauseMenu = new PauseMenu();
        primaryScene = scene;
        stage = new Stage();
        stage.initOwner(scene.getWindow());
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.scene = new Scene(pauseMenu,350,150);
        stage.setScene(this.scene);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.initOwner(primaryScene.getWindow());

        stage.show();
        initPauseMenuController();
    }

    PauseMenuController(Scene scene, BossGameController bossGameController, MainMenu mainMenu){
        this.mainMenu = mainMenu;
        this.bossGameController = bossGameController;
        gameSituation = GameSituation.getInstance();
        isSavePressed = new SimpleBooleanProperty(false);
        pauseMenu = new PauseMenu();
        primaryScene = scene;
        stage = new Stage();
        stage.initOwner(scene.getWindow());
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.scene = new Scene(pauseMenu,350,150);
        stage.setScene(this.scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.initOwner(primaryScene.getWindow());
        stage.show();
        initPauseMenuController();
    }

    private void resume() {
        stage.close();
        if (!(preBossGameController == null) && !gameSituation.isIsPreBossFinished()){ //game infodan bakarak karar verecek şimdilik gameInfoyu koymadım diye böyle
            preBossGameController.getScene().getRoot().setEffect(null);
            preBossGameController.getAnimationTimer().start();
            preBossGameController.getScoreTimeline().play();
            preBossGameController.setGameOn(true);
        }
        else if (!gameSituation.isIsBossFinished()){ //game infodan bakarak karar verecek şimdilik gameInfoyu koymadım diye böyle
            bossGameController.getScene().getRoot().setEffect(null);
            bossGameController.getAnimationTimer().start();
            bossGameController.setGameOn(true);
        }
    }

    private void mainMenu(){
        stage.close();
        primaryScene.setRoot(mainMenu);
        preBossGameController = null;
        bossGameController = null;
    }

    private void save(){
        GameSaveObj gameSaveObj = GameSaveObj.getInstance();
        if(gameSituation.isIsPreBossFinishedSuccessfully()){ // if preboss finished and save pressed, saves bossmap
            gameSaveObj.setPreBossMap(null);
            gameSaveObj.setBossMap(bossGameController.getBossMapController().getBossMap());
        }
        else { // if preboss has not finished yet, saves preboss map
            gameSaveObj.setBossMap(null);
            gameSaveObj.setPreBossMap(preBossGameController.getPreBossMapController().getPreBossMap());
        }
        gameSaveObj.setGameSituation(gameSituation);
        isSavePressed.setValue(true);
    }

    public BooleanProperty getIsSavePressed(){
        return isSavePressed;
    }


    /* ----------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------
    ------------------ On click ----------------------------------------------------------------
    -------------------------------------------------------------------------------------------
     */

    private void initPauseMenuController(){
        pauseMenu.getResumeBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resume();
            }
        });

        pauseMenu.getMainMenuBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainMenu();
            }
        });

        pauseMenu.getSaveBtn().setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event){
               save();
           }
        });

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                save();
                System.exit(0);
            }
        });
    }

    public void setIsSavePressed(boolean b) {
        isSavePressed.setValue(b);
    }
}
