package org.openjfx.view.entities.buildingView;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.CacheHint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.openjfx.utilization.ModelToViewBuilding;


public class BuildingGroup {
    private BuildingView buildingView;
    private Rectangle healthBar;

    public BuildingGroup(ModelToViewBuilding modelToViewBuilding){
        buildingView = new BuildingView(modelToViewBuilding.getType());
        buildingView.setCacheHint(CacheHint.SPEED);
        buildingView.setCache(true);
        buildingView.setSmooth(true);
        healthBar = new Rectangle(buildingView.getFitWidth(), buildingView.getFitHeight()*(1.0/10), Color.RED);
        refresh(modelToViewBuilding);
    }

    public void refresh(ModelToViewBuilding modelToViewBuilding){
        buildingView.setTranslateX(modelToViewBuilding.getLocationX() - modelToViewBuilding.getCurrentViewLeft());
        buildingView.setTranslateY(modelToViewBuilding.getLocationY());
        buildingView.setFitHeight(modelToViewBuilding.getHitboxHeight());
        buildingView.setFitWidth(modelToViewBuilding.getHitboxWidth());
        healthBar.setHeight(buildingView.getFitHeight()*(1.0/10));
        healthBar.setWidth((double)modelToViewBuilding.getHealth()/modelToViewBuilding.getMaxHealth()*buildingView.getFitWidth());
        healthBar.setTranslateX(buildingView.getTranslateX());
        healthBar.setTranslateY(buildingView.getTranslateY() + buildingView.getFitHeight());
    }

    public Rectangle getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(Rectangle healthBar) {
        this.healthBar = healthBar;
    }

    public BuildingView getBuildingView() {
        return buildingView;
    }

    public void setBuildingView(BuildingView buildingView) {
        this.buildingView = buildingView;
    }
}
