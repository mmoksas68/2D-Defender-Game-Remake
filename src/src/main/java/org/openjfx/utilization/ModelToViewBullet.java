package org.openjfx.utilization;

import org.openjfx.model.commonEntities.Bullet.Bullet;

public class ModelToViewBullet extends ModelToView{
    private double directionX;
    private double directionY;

    public ModelToViewBullet(Bullet bullet) {
        super(bullet);
        this.directionX = bullet.getDirectionX();
        this.directionY = bullet.getDirectionY();
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
