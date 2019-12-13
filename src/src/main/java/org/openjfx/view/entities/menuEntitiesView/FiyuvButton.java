package org.openjfx.view.entities.menuEntitiesView;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FiyuvButton extends Button {

    private final String FONT_PATH = "assets/userInterfaceResources/kenvector_future.ttf";
    private Background freeBackground;
    private Background pressedBackground;

    public FiyuvButton(String text){
        Image image = null;
        try {
            image = new Image(new FileInputStream("assets/menuAssets/red_button01.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BackgroundImage pressedImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pressedBackground = new Background(pressedImage);

        try {
            image = new Image(new FileInputStream("assets/menuAssets/red_button00.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BackgroundImage freeImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        freeBackground = new Background(freeImage);
        setText(text);
        setButtonFont();
        setPrefWidth(300);
        setPrefHeight(49);
        setAlignment(Pos.CENTER_LEFT);
        setBackground(freeBackground);
        initializeButtonListeners();
    }

    private void setButtonFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPressedStyle(){


        setBackground(pressedBackground);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
    }

    private void setButtonFreeStyle(){
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
        setBackground(freeBackground);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonFreeStyle();
                }
            }
        });

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