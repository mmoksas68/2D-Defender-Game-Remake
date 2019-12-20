package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.view.menuView.menuEntitiesView.ExitButton;
import org.openjfx.view.menuView.menuEntitiesView.RestartButton;
import org.openjfx.view.menuView.menuEntitiesView.ResumeButton;
import org.openjfx.view.menuView.menuEntitiesView.SaveButton;

import java.util.ArrayList;
import java.util.List;

public class EndGameMenuView extends VBox {
    private Scene scene;
    private ResumeButton nextLevelBtn;
    private ExitButton menuBtn;
    private RestartButton restartBtn;
    private Label successfulLabel, unsuccessfulLabel, scoreLabel;
    private GameSituation gameSituation = GameSituation.getInstance();

    public EndGameMenuView(){
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-radius: 25px");
        setAlignment(Pos.CENTER);
        getChildren().add(new Label("Paused"));

        successfulLabel = new Label("Congratulations!");
        unsuccessfulLabel = new Label("Game over!");

        scoreLabel = new Label("Score: " + gameSituation.getScore());

        if(gameSituation.isIsBossFinishedSuccessfully())
            this.getChildren().add(successfulLabel);
        else
            this.getChildren().add(unsuccessfulLabel);

        this.getChildren().add(scoreLabel);

        createButtons();
    }

    private void createButtons(){
        nextLevelBtn = new ResumeButton();
        menuBtn = new ExitButton();
        restartBtn = new RestartButton();
        FlowPane flowPane = new FlowPane(menuBtn, restartBtn, nextLevelBtn);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(10,100,10,100));
        getChildren().add(flowPane);
    }

    public Button getRestartButton(){return restartBtn;}

    public Button getNextLevelBtn(){
        return nextLevelBtn;
    }

    public Button getMenuBtn(){
        return menuBtn;
    }



}
