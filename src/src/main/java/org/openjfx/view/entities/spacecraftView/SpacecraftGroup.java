package org.openjfx.view.entities.spacecraftView;

import javafx.scene.CacheHint;
import org.openjfx.utilization.ModelToViewSpaceCraft;

public class SpacecraftGroup {
    private SpacecraftView spacecraftView;
    private Flame flame;

    public SpacecraftGroup(ModelToViewSpaceCraft modelToViewSpaceCraft){
        spacecraftView = new SpacecraftView();
        spacecraftView.setTranslateX(modelToViewSpaceCraft.getLocationX() - modelToViewSpaceCraft.getCurrentViewLeft());
        spacecraftView.setTranslateY(modelToViewSpaceCraft.getLocationY());
        spacecraftView.setFitHeight(modelToViewSpaceCraft.getHitboxHeight());
        spacecraftView.setFitWidth(modelToViewSpaceCraft.getHitboxWidth());
        spacecraftView.setCacheHint(CacheHint.SPEED);
        spacecraftView.setCache(true);
        spacecraftView.setSmooth(true);
        flame = new Flame();
        flame.setTranslateX(spacecraftView.getTranslateX());
        flame.setTranslateY(spacecraftView.getTranslateY());
        flame.setFitHeight(spacecraftView.getFitHeight() + spacecraftView.getFitHeight()/2);
        flame.setFitWidth(spacecraftView.getFitWidth());
    }

    public void refresh(ModelToViewSpaceCraft modelToViewSpaceCraft){
        spacecraftView.setTranslateX(modelToViewSpaceCraft.getLocationX() - modelToViewSpaceCraft.getCurrentViewLeft());
        spacecraftView.setTranslateY(modelToViewSpaceCraft.getLocationY());
        spacecraftView.setFitHeight(modelToViewSpaceCraft.getHitboxHeight());
        spacecraftView.setFitWidth(modelToViewSpaceCraft.getHitboxWidth());

        if(modelToViewSpaceCraft.isDirectionLeft()){
            spacecraftView.setRotate(-90);
            flame.setRotate(-90);
            flame.setTranslateX(spacecraftView.getTranslateX() + spacecraftView.getFitWidth()/2);

        }
        else{
            spacecraftView.setRotate(90);
            flame.setRotate(90);
            flame.setTranslateX(spacecraftView.getTranslateX() - spacecraftView.getFitWidth()/2);

        }
        flame.setTranslateY(spacecraftView.getTranslateY());
        flame.setFitHeight(spacecraftView.getFitHeight());
        flame.setFitWidth(spacecraftView.getFitWidth());

        if(modelToViewSpaceCraft.isMoving())
            flame.setVisible(true);
        else
            flame.setVisible(false);
    }

    public SpacecraftView getSpacecraftView() {
        return spacecraftView;
    }

    public void setSpacecraftView(SpacecraftView spacecraftView) {
        this.spacecraftView = spacecraftView;
    }

    public Flame getFlame() {
        return flame;
    }

    public void setFlame(Flame flame) {
        this.flame = flame;
    }
}
