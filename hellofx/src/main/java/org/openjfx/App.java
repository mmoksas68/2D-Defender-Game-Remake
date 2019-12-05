package org.openjfx;
import controller.BossGameController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */

public class App extends Application {


        public static void main (String[]args){
            launch(args);
        }

        @Override
    public void start(Stage stage) throws Exception {
            BossGameController bossGameController = new BossGameController(   3);
            stage = bossGameController.getGameStage();
            stage.show();
    }
}