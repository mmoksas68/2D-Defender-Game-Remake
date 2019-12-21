package org.openjfx.view.gameSceneView.bossSceneView.topBar;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.GameInfoView;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.SpacecraftInfoView;

public class BossTopBarView extends BorderPane {
    private SpacecraftInfoView spacecraftInfoView1;
    private SpacecraftInfoView spacecraftInfoView2;
    private VBox leftView;
    private GameInfoView middleView;
    private BossInfoView rightView;


    public BossTopBarView(double width, double height) {
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
        middleView = new GameInfoView(width*3/10, height);
        rightView = new BossInfoView("BOSS", width*4/10, height);
        setCenter(middleView);
        setLeft(leftView);
        setRight(rightView);
    }

    public VBox getLeftView() {
        return leftView;
    }

    public BossInfoView getRightView() {
        return rightView;
    }

    public GameInfoView getMiddleView() {
        return middleView;
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
