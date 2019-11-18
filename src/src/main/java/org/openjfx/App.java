package org.openjfx;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.openjfx.controller.preBossControllers.PreBossGameController;


/**
 * JavaFX App
 */
public class App extends Application {
    PreBossGameController preBossGameController;

    @Override
    public void start(Stage stage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        preBossGameController = new PreBossGameController(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        stage.setScene(preBossGameController.getScene());
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint(null);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
            preBossGameController.setSceneWidth(stage.getWidth());
            preBossGameController.setSceneHeight(stage.getHeight());
        };

        ChangeListener<Boolean> gameOnChangeListener = (observable, oldValue, newValue) -> {
            if (preBossGameController.gameOnChangeProperty().get()) {
                if (preBossGameController.isGameOn()) {
                    preBossGameController.getAnimationTimer().stop();
                    preBossGameController.setGameOn(false);
                } else {
                    preBossGameController.getAnimationTimer().start();
                    preBossGameController.setGameOn(true);
                }
                preBossGameController.setGameOnChange(false);
            }
        };
        preBossGameController.gameOnChangeProperty().addListener(gameOnChangeListener);
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
    }

    public static void main(String[] args) {
        launch(args);
    }

}