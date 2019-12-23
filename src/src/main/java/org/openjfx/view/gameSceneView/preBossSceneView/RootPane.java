package org.openjfx.view.gameSceneView.preBossSceneView;

import javafx.animation.*;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.view.gameSceneView.bossSceneView.topBar.BossTopBarView;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.TopBarView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class RootPane extends BorderPane {
    private PreBossMapView preBossMapView1;
    private PreBossMapView preBossMapView2;
    private BossMapView bossMapView;
    private TopBarView topBarView;
    private BossTopBarView bossTopBarView;

    private double height;
    private double width;

    //private BackgroundImage image;
    //image = new BackgroundImage(new Image(new FileInputStream("assets/images/backgrounds/background.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    Image image2 = Assets.getInstance().getPreBossAssets().getBackgrounds().get(GameSituation.getInstance().getLevel() - 1);
    private BackgroundImage image = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


    public RootPane ( double width, double height) {
        setMaxSize(width,height);
        setPrefSize(width,height);
        bossTopBarView = new BossTopBarView(width, height*1.5/10);
        bossMapView = new BossMapView(width, height*8.8/10);
        setCenter(this.bossMapView);
        setTop(bossTopBarView);
        Background background = new Background(image);
        setBackground(background);
    }

    public RootPane(double width, double height, boolean isSinglePlayer) {
        setMaxSize(width,height);
        setPrefSize(width,height);
        this.height = height;
        this.width = width;

        if(isSinglePlayer || GameSituation.getInstance().isTwoPlayerSingleShip())
        {
            preBossMapView1 = new PreBossMapView(width, height*8.8/10, isSinglePlayer);
            topBarView = new TopBarView(width, height*1.5/10);
            setCenter(this.preBossMapView1);
        }else{
            preBossMapView1 = new PreBossMapView(width, height*4.4/10, isSinglePlayer);
            preBossMapView2 = new PreBossMapView(width, height*4.4/10, isSinglePlayer);
            topBarView = new TopBarView(width, height*1.5/10);
            setCenter(preBossMapView1);
            setBottom(preBossMapView2);
        }
        setTop(topBarView);

        Background background = new Background(image);
        setBackground(background);
    }



    public BossMapView getBossMapView() {
        return bossMapView;
    }
    
    public void twoPlayerOneShipScreen(PreBossMapView myView){
        Timeline timeline = new Timeline();
        KeyFrame kf;
        KeyFrame kf2;
        KeyFrame kf3;
        setCenter(null);
        setBottom(null);
        if(GameSituation.getInstance().isFirstCraftDied()){
            preBossMapView2 = myView;
            setBottom(preBossMapView2);
            KeyValue keyValue = new KeyValue(preBossMapView2.getLayoutHeight(), (height*8.8/10) / PreBossMap.MAP_HEIGHT, Interpolator.EASE_IN);
            KeyValue keyValue2 = new KeyValue(preBossMapView2.translateYProperty(), (-height*4.4/10), Interpolator.EASE_IN);
            kf = new KeyFrame(Duration.seconds(2.5), keyValue);
            kf2 = new KeyFrame(Duration.seconds(2.5), keyValue2);
            preBossMapView2.setStyle(null);
        }
        else{
            preBossMapView1 = myView;
            setCenter(preBossMapView1);
            KeyValue keyValue = new KeyValue(preBossMapView1.getLayoutHeight(), (height*8.8/10) / PreBossMap.MAP_HEIGHT, Interpolator.EASE_IN);
            KeyValue keyValue2 = new KeyValue(preBossMapView1.translateYProperty(), 0, Interpolator.EASE_IN);
            kf = new KeyFrame(Duration.seconds(2.5), keyValue);
            kf2 = new KeyFrame(Duration.seconds(2.5), keyValue2);
        }

        kf3 = new KeyFrame(Duration.seconds(2.5), e -> {
        });
        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf2);
        timeline.getKeyFrames().add(kf3);
        timeline.play();
    }



    public PreBossMapView getPreBossMapView1() {
        return preBossMapView1;
    }

   public PreBossMapView getPreBossMapView2() {
        return preBossMapView2;
    }

    public void MultiplayerToSinglePlayer(){

    }

    public TopBarView getTopBarView() {
        return topBarView;
    }

    public BossTopBarView getBossTopBarView(){
        return bossTopBarView;
    }

 /*   public void setWidth(double width){
        topBarView.setWidth(width);
        preBossMapView1.setLayoutScaleWidth(width);
    }

    public void setHeight(double height){
        topBarView.setHeight(height*2.5/10);
        preBossMapView1.setLayoutScaleHeight(height*7.5/10);
    }
*/
}
