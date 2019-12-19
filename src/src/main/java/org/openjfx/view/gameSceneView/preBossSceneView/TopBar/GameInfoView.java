package org.openjfx.view.gameSceneView.preBossSceneView.TopBar;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.utilization.ModelToGameInfoView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameInfoView extends BorderPane {

    private GameSituation gameSituation;
    private BorderPane borderPane;

    private Label score;
    private Label enemyNumber;
    private Label stationNumber;
    private ImageView scoreIcon;
    private ImageView enemyIcon;
    private ImageView stationIcon;

    private double width;
    private double height;

    private Pane left;
    private Pane center;
    private Pane right;

    public GameInfoView(double width, double height) {
        gameSituation = GameSituation.getInstance();
        this.width = width;
        this.height = height;
        setMinSize(width, height);
        setMaxSize(width, height);
        left = createEnemyNo();
        center = createScore();
        right = createStationNo();

        if(!gameSituation.isIsPreBossFinished() || !gameSituation.isIsPreBossFinishedSuccessfully()) {
            setLeft(left);
            setCenter(center);
            setRight(right);
        }else{
            setLeft(null);
            setCenter(center);
            setRight(null);
        }
        ChangeListener<Boolean> isPreFinish = (observable, oldValue, newValue) -> {
            if(gameSituation.isIsPreBossFinishedSuccessfully()){
                setLeft(null);
                setCenter(center);
                setRight(null);
            }
        };
        gameSituation.isPreBossFinishedSuccessfullyProperty().addListener(isPreFinish);


        setStyle("-fx-border-color : white; -fx-border-width : 0 0 1 0");
    }

    public void refresh(ModelToGameInfoView modelToGameInfoView){
        score.setText(modelToGameInfoView.getScore()+"");
        enemyNumber.setText(modelToGameInfoView.getEnemyCount()+"");
        stationNumber.setText(modelToGameInfoView.getStationCount()+"");
    }

    public void refresh(){
        score.setText(gameSituation.getScore()+"");
    }

    private HBox createScore(){
        HBox forScore = new HBox();
        Pane scoreIcon = new Pane();
        ImageView scoreIconView = new ImageView(Assets.getInstance().getPreBossAssets().getGameInfoIcons().get(0));
        scoreIconView.setFitWidth(width/15);
        scoreIconView.setFitHeight(width/15);
        scoreIcon.getChildren().add(scoreIconView);

        score = new Label();
        score.setText(gameSituation.getScore()+"");
        score.setFont(Font.font("Arial", 32));
        score.setTextFill(Color.web("#ffffff"));

        forScore.setPadding(new Insets(5,0,0,0));
        forScore.setSpacing(25);

        forScore.getChildren().add(scoreIcon);
        forScore.getChildren().add(score);

        forScore.setAlignment(Pos.TOP_CENTER);

        return forScore;
    }

    private HBox createEnemyNo(){
        HBox forEnemy = new HBox();
        Pane scoreIcon = new Pane();
        ImageView scoreIconView = new ImageView(Assets.getInstance().getPreBossAssets().getGameInfoIcons().get(1));
        scoreIconView.setFitWidth(width/15);
        scoreIconView.setFitHeight(width/15);
        scoreIcon.getChildren().add(scoreIconView);


        enemyNumber = new Label();
        enemyNumber.setFont(Font.font("Arial", 32));
        enemyNumber.setTextFill(Color.web("#ffffff"));
        forEnemy.setPadding(new Insets(5,0,0,0));
        forEnemy.setSpacing(25);

        forEnemy.getChildren().add(scoreIcon);
        forEnemy.getChildren().add(enemyNumber);
        return forEnemy;
    }

    private HBox createStationNo(){
        HBox forStationNo = new HBox();
        Pane scoreIcon = new Pane();
        ImageView scoreIconView = new ImageView(Assets.getInstance().getPreBossAssets().getGameInfoIcons().get(2));
        scoreIconView.setFitWidth(width/15);
        scoreIconView.setFitHeight(width/15);
        scoreIcon.getChildren().add(scoreIconView);
        forStationNo.getChildren().add(scoreIcon);


        stationNumber = new Label();
        stationNumber.setFont(Font.font("Arial", 32));
        stationNumber.setTextFill(Color.web("#ffffff"));
        forStationNo.getChildren().add(stationNumber);

        forStationNo.setPadding(new Insets(5,0,0,0));
        forStationNo.setSpacing(25);
        return forStationNo;
    }
}
