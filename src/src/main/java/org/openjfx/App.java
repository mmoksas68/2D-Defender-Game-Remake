package org.openjfx;


import javafx.application.Application;
import javafx.stage.Stage;
import org.openjfx.controller.bossSceneControllers.BossGameController;
import org.openjfx.controller.menuController.MainMenuController;


/**
 * JavaFX App**
 */
public class App extends Application {

    public static void main (String[]args){
        launch(args);
    }

    //@Override
  /*  public void start(Stage stage) throws Exception {
        BossGameController bossGameController = new BossGameController(   2);
        stage = bossGameController.getGameStage();
        stage.show();
    }*/






  //  PreBossGameController preBossGameController;
    BossGameController bossGameController;
    @Override
    public void start(Stage stage) {
        MainMenuController mainController = new MainMenuController(stage);
    }
}