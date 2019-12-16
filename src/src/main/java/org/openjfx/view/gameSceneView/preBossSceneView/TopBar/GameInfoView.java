package org.openjfx.view.gameSceneView.preBossSceneView.TopBar;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.openjfx.model.menuEntities.GameSituation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameInfoView extends Pane {

    private GameSituation gameSituation;
    private BorderPane borderPane;

    private Label score;
    private Label enemyNumber;
    private Label stationNumber;
    private ImageView scoreIcon;
    private ImageView enemyIcon;
    private ImageView stationIcon;


    public GameInfoView(double width, double height) {
        gameSituation = GameSituation.getInstance();

        setMinSize(width, height);
        setPrefSize(width, height);

        borderPane = new BorderPane();
        borderPane.setPrefSize(width,height);

        score = new Label();
        score.setText(gameSituation.getScore()+"");
        score.setFont(Font.font("Arial", 16));
        score.setTextFill(Color.web("#ffffff"));

        borderPane.setCenter(score);
        getChildren().add(borderPane);
        System.out.println(gameSituation.getScore());

        setStyle("-fx-border-color : white; -fx-border-width : 0 0 1 0");
    }

    public void refresh(){
        score.setText(gameSituation.getScore()+"");
    }
}
