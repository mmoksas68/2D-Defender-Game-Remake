package org.openjfx.view.gameSceneView.bossSceneView.topBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.openjfx.utilization.ModelToViewBossInfoView;

public class BossInfoView extends BorderPane {

    private double width;
    private double height;

    private HBox hbox;
    private ProgressBar healthBar;

    public BossInfoView(String str, double width, double height) {
        this.height = height;
        this.width = width;
        setMinSize(width, height);
        setPrefSize(width, height);
        setStyle("-fx-border-color : white; -fx-border-width : 0 0 1 0");
        this.setPadding(new Insets(10,10,10,20));
        createLabel(str);
        hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        createHealthBar();
        this.setLeft(hbox);
    }

    public void refresh(ModelToViewBossInfoView modelToViewBossInfoView){
        double health = (double) modelToViewBossInfoView.getHP() / modelToViewBossInfoView.getMAX_HP();
        healthBar.setProgress(health);
    }

    private void createLabel(String str){
        Label bossLabel;
        bossLabel = new Label(str);
        bossLabel.setTextFill(Color.WHITE);
        this.setTop(bossLabel);
        bossLabel.setAlignment(Pos.CENTER);
    }

    private void createHealthBar(){
        healthBar = new ProgressBar();
        healthBar.setStyle("-fx-background-color: white; -fx-accent: red;");
        healthBar.setMinSize(width/3, height/8);
        hbox.getChildren().add(healthBar);
    }

}
