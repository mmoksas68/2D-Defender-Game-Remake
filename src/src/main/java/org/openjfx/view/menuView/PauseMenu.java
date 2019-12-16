package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.openjfx.view.menuView.menuEntitiesView.ExitButton;
import org.openjfx.view.menuView.menuEntitiesView.ResumeButton;
import org.openjfx.view.menuView.menuEntitiesView.SaveButton;

import java.util.ArrayList;
import java.util.List;

public class PauseMenu extends VBox {

    private Scene scene;

    private static final int MENU_BTN_START_X = 100;
    private static final int MENU_BTN_START_Y = 100;

    List<Button> menuButtons;

    private ResumeButton resumeBtn;
    private ExitButton exitBtn;
    private SaveButton saveBtn;

    public PauseMenu(){
        menuButtons = new ArrayList<>();
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-radius: 25px");
        setAlignment(Pos.CENTER);
        getChildren().add(new Label("Paused"));
        createButtons();
    }

    private void createButtons(){
        resumeBtn = new ResumeButton();
        exitBtn = new ExitButton();
        saveBtn = new SaveButton();
        FlowPane flowPane = new FlowPane(resumeBtn, saveBtn, exitBtn);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(10,100,10,100));
        getChildren().add(flowPane);
    }

    public Button getResumeBtn(){
        return resumeBtn;
    }

    public Button getMainMenuBtn(){
        return exitBtn;
    }

    public Button getSaveBtn(){
        return saveBtn;
    }


}
