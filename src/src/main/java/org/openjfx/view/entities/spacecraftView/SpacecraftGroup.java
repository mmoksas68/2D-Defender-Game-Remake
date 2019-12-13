package org.openjfx.view.entities.spacecraftView;

import javafx.scene.CacheHint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.openjfx.model.entities.Spacecraft.Spacecraft;
import org.openjfx.utilization.ModelToViewSpaceCraft;

public class SpacecraftGroup {
    private SpacecraftView spacecraftView;
    private Flame flame;
    private Rectangle healthBar;

    public SpacecraftGroup(ModelToViewSpaceCraft modelToViewSpaceCraft){
        spacecraftView = new SpacecraftView();
        spacecraftView.setCacheHint(CacheHint.SPEED);
        spacecraftView.setCache(true);
        spacecraftView.setSmooth(true);
        flame = new Flame();
        healthBar = new Rectangle(spacecraftView.getFitWidth()*(8.5/10), spacecraftView.getFitHeight()*(1.0/10), Color.RED);
        refresh(modelToViewSpaceCraft);
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

        healthBar.setHeight(spacecraftView.getFitHeight()*(0.5/10));
        healthBar.setWidth((double)modelToViewSpaceCraft.getHealth()/ Spacecraft.getMaxHealth() * spacecraftView.getFitWidth()*(8.0/10));
        healthBar.setTranslateX(spacecraftView.getTranslateX() + spacecraftView.getFitWidth()*(1.0/10));
        healthBar.setTranslateY(spacecraftView.getTranslateY() + spacecraftView.getFitHeight()*(8.5/10));
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

    public Rectangle getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(Rectangle healthBar) {
        this.healthBar = healthBar;
    }
}
