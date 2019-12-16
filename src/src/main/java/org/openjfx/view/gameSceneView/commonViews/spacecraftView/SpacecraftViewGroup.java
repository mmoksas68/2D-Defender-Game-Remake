package org.openjfx.view.gameSceneView.commonViews.spacecraftView;

import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.utilization.ModelToViewSpaceCraft;

import java.util.List;

public class SpacecraftViewGroup {
    private ImageView spacecraftView;
    private Flame flame;

    public SpacecraftViewGroup(ModelToViewSpaceCraft modelToViewSpaceCraft, double viewLeft, double scaleW, double scaleH){
        Assets assets = Assets.getInstance();
        List<Image> spacecraft = assets.getPreBossAssets().getSpacecraft();
        spacecraftView = new ImageView(spacecraft.get(modelToViewSpaceCraft.getChoosenPicNo()));
        spacecraftView.setCacheHint(CacheHint.SPEED);
        spacecraftView.setCache(true);
        spacecraftView.setSmooth(true);
        flame = new Flame();
        refresh(modelToViewSpaceCraft,  viewLeft,  scaleW,  scaleH);
    }

    public void refresh(ModelToViewSpaceCraft modelToViewSpaceCraft, double viewLeft, double scaleW, double scaleH){
        spacecraftView.setTranslateX((modelToViewSpaceCraft.getLocationX() - viewLeft) * scaleW);
        spacecraftView.setTranslateY(modelToViewSpaceCraft.getLocationY() * scaleH);
        spacecraftView.setFitHeight(modelToViewSpaceCraft.getHitboxHeight() * scaleH);
        spacecraftView.setFitWidth(modelToViewSpaceCraft.getHitboxWidth() * scaleW);

        if(modelToViewSpaceCraft.isDirectionLeft()){
            spacecraftView.setRotate(-180);
            flame.setRotate(-90);
            flame.setTranslateX((spacecraftView.getTranslateX() + spacecraftView.getFitWidth()/3));
        }
        else{
            spacecraftView.setRotate(0);
            flame.setRotate(90);
            flame.setTranslateX((spacecraftView.getTranslateX() - spacecraftView.getFitWidth()/3));

        }
        flame.setTranslateY(spacecraftView.getTranslateY());
        flame.setFitHeight(spacecraftView.getFitHeight());
        flame.setFitWidth(spacecraftView.getFitWidth());

        if(modelToViewSpaceCraft.isMoving())
            flame.setVisible(true);
        else
            flame.setVisible(false);
    }

    public ImageView getSpacecraftView() {
        return spacecraftView;
    }

    public void setSpacecraftView(ImageView spacecraftView) {
        this.spacecraftView = spacecraftView;
    }

    public Flame getFlame() {
        return flame;
    }

    public void setFlame(Flame flame) {
        this.flame = flame;
    }
}
