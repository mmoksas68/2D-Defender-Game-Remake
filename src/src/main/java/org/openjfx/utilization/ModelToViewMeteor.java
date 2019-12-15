package org.openjfx.utilization;

import org.openjfx.model.preBossEntities.Meteor.Meteor;

public class ModelToViewMeteor extends ModelToView {
    private double directionX;
    private double directionY;

    public ModelToViewMeteor(Meteor meteor) {
        super(meteor);
        this.directionX = meteor.getDirectionX();
        this.directionY = meteor.getDirectionY();
    }

    public double getDirectionX() {
        return directionX;
    }

    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }
}



