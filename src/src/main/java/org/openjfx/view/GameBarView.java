package org.openjfx.view;


import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;


public class TopBoard extends StackPane {

    public MapRadarView mapRadarView;

    public TopBoard(double width, double height){
        setPrefSize(width*0.1,height*0.1);
        init(width, height);
        getChildren().add(mapRadarView);
        setAlignment(mapRadarView, Pos.CENTER);
    }

    private void init(double width, double height){
        mapRadarView = new MapRadarView(width, height);
    }
}

