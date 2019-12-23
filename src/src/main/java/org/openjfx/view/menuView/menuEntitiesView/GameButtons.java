package org.openjfx.view.menuView.menuEntitiesView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.openjfx.controller.SoundController;

public class GameButtons extends Button {
    public GameButtons(){

        initializeListener();
    }

    private void initializeListener(){
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SoundController.buttonEntered();
            }
        });

        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SoundController.buttonClick();
            }
        });

    }
}
