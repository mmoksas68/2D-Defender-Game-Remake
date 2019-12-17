package org.openjfx.view.gameSceneView.preBossSceneView;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.view.gameSceneView.preBossSceneView.TopBar.TopBarView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RootPane extends BorderPane {
    private PreBossMapView preBossMapView1;
    private PreBossMapView preBossMapView2;
    private BossMapView bossMapView;
    private TopBarView topBarView;

    private double height;
    private double width;

    private static BackgroundImage image;

    static {
        try {
            image = new BackgroundImage(new Image(new FileInputStream("assets/images/background.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public RootPane ( double width, double height) {
        setMaxSize(width,height);
        setPrefSize(width,height);
        topBarView = new TopBarView(width, height*1.5/10);

        bossMapView = new BossMapView(width, height*8.8/10);
        setCenter(this.bossMapView);

        setTop(topBarView);
       /* Background background = new Background(image);
        setBackground(background);*/
    }

    public RootPane(double width, double height, boolean isSinglePlayer) {
        setMaxSize(width,height);
        setPrefSize(width,height);
        this.height = height;
        this.width = width;
        topBarView = new TopBarView(width, height*1.5/10);
        if(isSinglePlayer || GameSituation.getInstance().isTwoPlayerSingleShip())
        {
            preBossMapView1 = new PreBossMapView(width, height*8.8/10, isSinglePlayer);
            setCenter(this.preBossMapView1);
        }else{
            preBossMapView1 = new PreBossMapView(width, height*4.4/10, isSinglePlayer);
            preBossMapView2 = new PreBossMapView(width, height*4.4/10, isSinglePlayer);
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
    
    public void twoPlayerOneShipScreen(PreBossMapView myView, AnimationTimer animationTimer){
        animationTimer.stop();
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(preBossMapView1.translateYProperty(), 8.8, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(10), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        setCenter(null);
        setBottom(null);
        preBossMapView1 = myView;
        preBossMapView1.setWidthSize(width);
        preBossMapView1.setHeightSize(height*8.8/10);
        preBossMapView1.refreshScale();
        setCenter(preBossMapView1);
        preBossMapView2 = null;
        animationTimer.start();
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
