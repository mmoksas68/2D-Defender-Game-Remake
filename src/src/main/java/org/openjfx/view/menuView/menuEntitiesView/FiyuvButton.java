package org.openjfx.view.menuView.menuEntitiesView;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.openjfx.controller.SoundController;

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
                    SoundController.buttonClick();
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
            public void handle(MouseEvent event) {
                SoundController.buttonEntered();
            }
        });
    }

}