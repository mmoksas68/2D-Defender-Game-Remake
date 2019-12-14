package org.openjfx.controller.menuController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.openjfx.controller.PreBossGameController;
import org.openjfx.controller.bossSceneController.BossGameController;
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


    PauseMenuController(Scene scene, PreBossGameController preBossGameController, MainMenu mainMenu){
        this.mainMenu = mainMenu;
        this.preBossGameController = preBossGameController;
        pauseMenu = new PauseMenu();
        primaryScene = scene;
        stage = new Stage();
        this.scene = new Scene(pauseMenu,350,150);
        stage.setScene(this.scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        initPauseMenuController();
    }

    PauseMenuController(Scene scene, BossGameController bossGameController, MainMenu mainMenu){
        this.mainMenu = mainMenu;
        this.bossGameController = bossGameController;
        pauseMenu = new PauseMenu();
        primaryScene = scene;
        stage = new Stage();
        this.scene = new Scene(pauseMenu,350,150);
        stage.setScene(this.scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        initPauseMenuController();
    }

    private void resume() {
        stage.close();
        if (preBossGameController != null){ //game infodan bakarak karar verecek şimdilik gameInfoyu koymadım diye böyle
            preBossGameController.getScene().getRoot().setEffect(null);
        preBossGameController.getAnimationTimer().start();
        preBossGameController.setGameOn(true);
        }
        if (bossGameController != null){ //game infodan bakarak karar verecek şimdilik gameInfoyu koymadım diye böyle
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
}
