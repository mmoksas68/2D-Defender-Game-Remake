package org.openjfx;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.openjfx.controller.Game;


/**
 * JavaFX App
 */
public class App extends Application {
    Game game;

    @Override
    public void start(Stage stage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        game = new Game(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        stage.setScene(game.getScene());
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint(null);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
            game.setSceneWidth(stage.getWidth());
            game.setSceneHeight(stage.getHeight());
        };

        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
    }

    public static void main(String[] args) {
        launch(args);
    }

}