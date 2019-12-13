package org.openjfx.view.entities.menuEntitiesView;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SaveButton extends Button {


    public SaveButton(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("assets/menuAssets/save.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BackgroundImage pressedImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background save = new Background(pressedImage);
        setPrefWidth(50);
        setPrefHeight(50);
        setBackground(save);
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
