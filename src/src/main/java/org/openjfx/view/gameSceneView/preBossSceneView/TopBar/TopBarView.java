package org.openjfx.view.gameSceneView.preBossSceneView.TopBar;

import javafx.scene.layout.*;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.radarView.RadarView;

public class TopBarView extends BorderPane {
    private SpacecraftInfoView leftView;
    private RadarView middleView;
    private GameInfoView rightView;

    public TopBarView(double width, double height) {
        leftView = new SpacecraftInfoView(width*3/10, height);
        middleView = new RadarView(width*4/10, height);
        rightView = new GameInfoView(width*3/10, height);
        setCenter(middleView);
        setLeft(leftView);
        setRight(rightView);
    }

    public SpacecraftInfoView getLeftView() {
        return leftView;
    }

    public RadarView getMiddleView() {
        return middleView;
    }

    public GameInfoView getRightView() {
        return rightView;
    }


   public void setWidth(double width){
    }

    public void setHeight(double height){

    }
}
