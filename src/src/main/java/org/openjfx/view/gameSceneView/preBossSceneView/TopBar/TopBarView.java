package org.openjfx.view.gameSceneView.preBossSceneView.TopBar;

import javafx.scene.layout.*;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.radarView.RadarView;

public class TopBarView extends BorderPane {
    private SpacecraftInfoView spacecraftInfoView1;
    private SpacecraftInfoView spacecraftInfoView2;
    private VBox leftView;
    private RadarView middleView;
    private GameInfoView rightView;

    public TopBarView(double width, double height, double sliderLeft1, double sliderLeft2) {
        leftView = new VBox();

        if(!GameSituation.getInstance().isSinglePlayer()){
            spacecraftInfoView1 = new SpacecraftInfoView("PLAYER 1",width*3/10, height/2);
            spacecraftInfoView1.setStyle(null);
            leftView.getChildren().add(spacecraftInfoView1);
            spacecraftInfoView2 = new SpacecraftInfoView("PLAYER 2",width*3/10, height/2);
            leftView.getChildren().add(spacecraftInfoView2);
        }
        else{
            spacecraftInfoView1 = new SpacecraftInfoView("PLAYER 1",width*3/10, height);
            leftView.getChildren().add(spacecraftInfoView1);
        }
        middleView = new RadarView(width*4/10, height);
        rightView = new GameInfoView(width*3/10, height);
        setCenter(middleView);
        setLeft(leftView);
        setRight(rightView);
    }

    public VBox getLeftView() {
        return leftView;
    }

    public RadarView getMiddleView() {
        return middleView;
    }

    public GameInfoView getRightView() {
        return rightView;
    }


    public SpacecraftInfoView getSpacecraftInfoView1(){
        return spacecraftInfoView1;
    }

    public SpacecraftInfoView getSpacecraftInfoView2(){
        return spacecraftInfoView2;
    }

   public void setWidth(double width){
    }

    public void setHeight(double height){

    }
}
