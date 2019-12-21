package org.openjfx.view.menuView.menuEntitiesView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.openjfx.controller.SoundController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FiyuvButton extends Button {

    public FiyuvButton(String text){
        setText(text);
        this.getStyleClass().add("fiyuv-button");
        initializeButtonListeners();
    }

    private void setButtonPressedStyle(){
        setLayoutY(getLayoutY()+4);
    }

    private void setButtonFreeStyle(){
        setLayoutY(getLayoutY() - 4);
    }

    public double getSize(){
        return this.getWidth();
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
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SoundController.buttonEntered();
            }
        });
    }

}