package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.openjfx.view.menuView.menuEntitiesView.*;

import java.util.ArrayList;
import java.util.List;

public class PauseMenu extends VBox {

    private Scene scene;


    List<Button> menuButtons;

    private FiyuvButton resumeBtn;
    private Button menuBtn;
    private Button saveBtn;
    private Button settingsButton;

    public PauseMenu(){

        setAlignment(Pos.CENTER);

        ImageView image = new ImageView();
        image.getStyleClass().add("pause-image");
        image.setFitWidth(150);
        image.setFitHeight(40);
        getChildren().add(image);
        createButtons();
    }

    private void createButtons(){
        resumeBtn = new FiyuvButton("Resume");

        resumeBtn.setPrefWidth(125);
        resumeBtn.setPrefHeight(30);


        menuBtn = new GameButtons();
        saveBtn = new GameButtons();
        settingsButton = new GameButtons();

        menuBtn.getStyleClass().add("menu-button");
        saveBtn.getStyleClass().add("save-button");
        settingsButton.getStyleClass().add("settings-button");


        HBox hbox = new HBox(menuBtn, settingsButton, saveBtn);
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        getChildren().addAll(hbox, resumeBtn);
    }

    public Button getResumeBtn(){
        return resumeBtn;
    }

    public Button getMainMenuBtn(){
        return menuBtn;
    }

    public Button getSaveBtn(){
        return saveBtn;
    }

    public Button getSettingsButton(){return settingsButton;}


}
