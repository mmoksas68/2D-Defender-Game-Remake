package org.openjfx.view.menuView.menuEntitiesView;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.openjfx.assetManager.Assets;
import org.openjfx.assetManager.MenuAssets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RestartButton extends Button {

    private Image image = Assets.getInstance().getMenuAssets().getRestart();
    public RestartButton(){
        BackgroundImage pressedImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background restart = new Background(pressedImage);
        setPrefWidth(50);
        setPrefHeight(50);
        setBackground(restart);
        initializeButtonListeners();
    }

    private void initializeButtonListeners(){
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }
}
