package org.openjfx.view;


import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewBuilding;
import org.openjfx.utilization.ModelToViewEnemy;
import org.openjfx.view.entities.RadarMapView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GameBarView extends StackPane {

    private static BackgroundImage image;

    public RadarMapView radarMapView;
    private Background background;

    static {
        try {
            image = new BackgroundImage(new Image(new FileInputStream("assets/images/background.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public GameBarView(double width, double height){
        setPrefSize(width,height);
        init(width, height);
        getChildren().add(radarMapView);
        background = new Background(image);
        setBackground(background);
        setStyle("-fx-border-color : white; -fx-border-width : 0 0 1 0");
    }

    private void init(double width, double height){
        radarMapView = new RadarMapView(width, height);
    }

    public void refreshEnemy(ModelToViewEnemy modelToView){
        radarMapView.refreshEnemy(modelToView);
    }

    public void refreshSpacecraft(ModelToView modelToView){
        radarMapView.refreshSpacecraft(modelToView);
    }

    public void refreshGameBarView(double width, double height) {
        radarMapView.refreshRadarMapView(width, height);
    }

    public void refreshRadarBuilding(ModelToViewBuilding modelToViewBuilding){ radarMapView.refreshBuilding(modelToViewBuilding);}
}

