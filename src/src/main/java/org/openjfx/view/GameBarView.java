package org.openjfx.view;


import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import org.openjfx.utilization.ModelToView;


public class GameBarView extends StackPane {

    public RadarMapView radarMapView;

    public GameBarView(double width, double height){
        setPrefSize(width,height);
        init(width, height);
        getChildren().add(radarMapView);
    }

    private void init(double width, double height){
        radarMapView = new RadarMapView(width, height);
    }

    public void refreshEnemy(ModelToView modelToView){
        radarMapView.refreshEnemy(modelToView);
    }

    public void refreshSpacecraft(ModelToView modelToView){
        radarMapView.refreshSpacecraft(modelToView);
    }

    public void refreshGameBarView(double width, double height) {
        radarMapView.refreshRadarMapView(width, height);
    }

}

