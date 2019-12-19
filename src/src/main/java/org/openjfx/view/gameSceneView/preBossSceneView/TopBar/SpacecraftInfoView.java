package org.openjfx.view.gameSceneView.preBossSceneView.TopBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.utilization.ModelToSpacecraftInfoView;


public class SpacecraftInfoView extends BorderPane {

    private double width;
    private double height;

    private HBox hbox;
    private VBox vbox;

    private ProgressBar healthBar;
    private ProgressBar hyperJump;

    private Pane smartBombStock;
    private ImageView smartBomb;

    public SpacecraftInfoView(String str, double width, double height) {
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
        createHyperJumpBar();
        createBombStock();
        this.setLeft(hbox);
    }

    public void refresh(ModelToSpacecraftInfoView modelToSpacecraftInfoView){
        double health = (double) modelToSpacecraftInfoView.getHP() /Spacecraft.MAX_HEALTH;
        healthBar.setProgress(health);
        double energy = (double) modelToSpacecraftInfoView.getHyperJumpBattery() / Spacecraft.MAX_HYPERJUMP_ENERGY;
        hyperJump.setProgress(energy);
        int bombCount = modelToSpacecraftInfoView.getSmartBombCount();
        //smartBombStock.setText(bombCount+"");
        if(hyperJump.getProgress() > 0.25){
            hyperJump.setStyle("-fx-background-color: white;");
        }
        else{
            hyperJump.setStyle("-fx-background-color: white; -fx-accent: grey;");
        }
        if(modelToSpacecraftInfoView.getSmartBombCount() <= 3){
            if(smartBombStock.getChildren().size() != 0) {
                smartBombStock.getChildren().set(0, new ImageView(Assets.getInstance().getPreBossAssets().getSmartBombImg().get(modelToSpacecraftInfoView.getSmartBombCount())));
                if(smartBombStock.getChildren() instanceof ImageView)
                ((ImageView) smartBombStock.getChildren().get(0)).setFitWidth(width/15);
                ((ImageView) smartBombStock.getChildren().get(0)).setFitWidth(width/15);
                ((ImageView) smartBombStock.getChildren().get(0)).setY(smartBomb.getY() - width/150);
            }
        }
    }

    private void createLabel(String str){
        Label player1Label;
        player1Label = new Label(str);
        player1Label.setTextFill(Color.WHITE);
        this.setTop(player1Label);
        player1Label.setAlignment(Pos.CENTER);
    }

    private void createHealthBar(){
        healthBar = new ProgressBar();
        healthBar.setStyle("-fx-background-color: white; -fx-accent: red;");
        healthBar.setMinSize(width/3, height/8);
        hbox.getChildren().add(healthBar);
    }


    private void createHyperJumpBar(){
        hyperJump = new ProgressBar();
        hyperJump.setStyle("-fx-background-color: white; -fx-accent: grey;");
        hyperJump.setMinSize(width/3, height/8);
        hbox.getChildren().add(hyperJump);
    }

    private void createBombStock(){
        smartBombStock = new Pane();
        smartBombStock.setMinSize(width/15,width/15);
        smartBombStock.setMaxSize(width/15,width/15);
        smartBomb = new ImageView(Assets.getInstance().getPreBossAssets().getSmartBombImg().get(0));
        smartBombStock.getChildren().add(smartBomb);

        hbox.getChildren().add(smartBombStock);
        smartBombStock.setStyle("-fx-border-color: white");
    }
}
