package org.openjfx.model.commonEntities.FiringBehavior;

import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.utilization.PositionHelper;

import java.io.Serializable;

public class SpacecraftGun extends SimpleGun implements Serializable {


    private Spacecraft spacecraft;
    BulletTypes bulletType;

    public SpacecraftGun(int gunPeriod, int bulletDamage, int gunTimer, double bulletVelocity, Spacecraft spacecraft) {
        super(gunPeriod, bulletDamage, gunTimer, bulletVelocity);
        this.spacecraft = spacecraft;
        bulletType = BulletTypes.Single;
    }

    @Override
    public Bullet fireBullet() {
        int directionX = spacecraft.isDirectionLeft() ? -10 : 10;
        int directionY = 0;
        PositionHelper spacecraftHelper = new PositionHelper(spacecraft);
        double yPosition = 0;
        switch (bulletType){
            case Single:
                    yPosition =  spacecraftHelper.getMiddlePointY();
                break;
            case DoubleFirst:
                    yPosition =  spacecraftHelper.getMiddlePointY() - Spacecraft.HEIGHT/4;
                    break;
            case DoubleSecond:
                    yPosition =  spacecraftHelper.getMiddlePointY() + Spacecraft.HEIGHT/4;
                break;
            case TripleFirst:
                    yPosition =  spacecraftHelper.getMiddlePointY() - Spacecraft.HEIGHT*4/16;
                    directionY = 1;
                break;
            case TripleSecond:
                    yPosition =  spacecraftHelper.getMiddlePointY();
                break;
            case TripleThird:
                    yPosition =  spacecraftHelper.getMiddlePointY() + Spacecraft.HEIGHT*4/16;
                    directionY = -1;
                break;
        }

        Location location = spacecraft.isDirectionLeft() ?
                new Location(spacecraftHelper.getLeft() - Bullet.WIDTH - 3,yPosition) :
                new Location(spacecraftHelper.getRight() + 3, yPosition);

        return new Bullet(location, getBulletDamage(), getBulletVelocity(), directionX, directionY);
    }


    public void increaseFrequency() {
        if(!(getGunPeriod() <= Spacecraft.MIN_GUNPERIOD))
            super.setGunPeriod(getGunPeriod() - Spacecraft.GUNPERIOD_BUFF);
    }

    public void increaseDamage(){
        if(!(getBulletDamage() >= Spacecraft.MAX_BULLET_DAMAGE))
            super.setBulletDamage(getBulletDamage() + Spacecraft.INIT_BULLET_DAMAGE);
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    public BulletTypes getBulletType() {
        return bulletType;
    }

    public void setBulletType(BulletTypes bulletType) {
        this.bulletType = bulletType;
    }
}
