package org.openjfx.view.gameSceneView.preBossSceneView.TopBar;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameInfoView extends Pane {


    public GameInfoView(double width, double height) {
        setMinSize(width, height);
        setPrefSize(width, height);
        setStyle("-fx-border-color : white; -fx-border-width : 0 0 1 0");
    }
}
