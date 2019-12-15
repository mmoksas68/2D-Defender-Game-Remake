package org.openjfx.view.menuView.menuEntitiesView;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ResumeButton extends Button {

    public ResumeButton(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("assets/menuAssets/forward.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BackgroundImage pressedImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background resume = new Background(pressedImage);
        setPrefWidth(50);
        setPrefHeight(50);
        setBackground(resume);
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
