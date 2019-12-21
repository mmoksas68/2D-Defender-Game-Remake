package org.openjfx.view.menuView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.view.menuView.menuEntitiesView.GameButtons;


public class EndGameMenuView extends VBox {
    private Button nextLevelBtn;
    private Button menuBtn;
    private Button restartBtn;
    private Label scoreLabel;
    private GameSituation gameSituation = GameSituation.getInstance();

    public EndGameMenuView(){
        setAlignment(Pos.CENTER);
        ImageView image1 = new ImageView();
        image1.getStyleClass().add("header-win-image");

        ImageView image2 = new ImageView();
        image2.getStyleClass().add("header-lose-image");

        scoreLabel = new Label("Score: " + gameSituation.getScore());

        scoreLabel.getStyleClass().add("score-label");


        if(gameSituation.isIsBossFinishedSuccessfully())
            this.getChildren().add(image1);
        else
            this.getChildren().add(image2);

        this.getChildren().add(scoreLabel);
        createButtons();
    }

    private void createButtons(){
        nextLevelBtn = new GameButtons();
        menuBtn = new GameButtons();
        restartBtn = new GameButtons();
        HBox hbox = new HBox(menuBtn, restartBtn);


        nextLevelBtn.getStyleClass().add("next-level-button");
        menuBtn.getStyleClass().add("menu-button");
        restartBtn.getStyleClass().add("restart-button");

        if(gameSituation.isIsBossFinishedSuccessfully())
            hbox.getChildren().add(nextLevelBtn);

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        //hbox.setPadding(new Insets(10,100,10,100));
        getChildren().add(hbox);
    }

    public Button getRestartButton(){return restartBtn;}

    public Button getNextLevelBtn(){
        return nextLevelBtn;
    }

    public Button getMenuBtn(){
        return menuBtn;
    }



}
