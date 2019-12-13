package org.openjfx.view.entities.menuEntitiesView;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExitButton extends Button {
    private final String BUTTON_UI = "-fx-background-color: transparent;" ;

    public ExitButton() {
        setPrefWidth(50);
        setPrefHeight(50);
        //setStyle(BUTTON_UI);
        Image image = null;
        try {
            image = new Image(new FileInputStream("assets/menuAssets/cross.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BackgroundImage image2 = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(image2);
        setBackground(background);
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
