package org.openjfx.model.bossEntities.Boss;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.bossEntities.BossAbility.Laser;

public class BossOne extends Boss {

    private final double LASER_FREQ = 0.01;
    private final int laserDamage = 10;
    private static final double hitBoxWidth = 150;
    private static final double hitBoxHeight = 150;
    private static final int MAX_HEALTH_POINT = 10000;
    private static final double velocity = 3;
    private static int gunPower = 10;
    private static double bulletVelocity = 10.0;
    private static double gunFrequency = 0.05;

    public BossOne () {
        super ( velocity, hitBoxWidth, hitBoxHeight, MAX_HEALTH_POINT);
        setGunFrequency( gunFrequency);
        setGunPower( gunPower);
        setBulletVelocity( bulletVelocity);
    }

    public double [] sendLaserIndicator () {
        double [] values = new double[ 4];
        values [0] = getLocation().getPositionX() - 20;
        values [1] = getLocation().getPositionY() + (getHitBoxHeight() /4);
        values [2] = getHitBoxHeight()/2;
        values [3] = getHitBoxHeight()/2;
        return values;
    }
    public Laser sendLaser () {
        double laserWidth = getLocation().getPositionX();
        return new Laser( new Location( 0,getLocation().getPositionY() + 4*getHitBoxHeight()/9), laserWidth);
    }

    public double getLASER_FREQ() {
        return LASER_FREQ;
    }

    public static int getMaxHealthPoint() {
        return MAX_HEALTH_POINT;
    }

  /*  @Override
    public int getBulletDamage() {
        return 0;
    }

    @Override
    public void setBulletDamage(int bulletDamage) {

    }*/
}