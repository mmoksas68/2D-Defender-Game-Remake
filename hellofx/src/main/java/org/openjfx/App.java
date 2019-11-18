package org.openjfx;
import controller.MapManager;
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
            MapManager viewManager = new MapManager();
            stage = viewManager.getGameStage();
            stage.show();
    }
}