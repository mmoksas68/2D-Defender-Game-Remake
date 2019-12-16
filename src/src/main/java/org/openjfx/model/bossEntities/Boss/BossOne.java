package org.openjfx.model.bossEntities.Boss;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.bossEntities.BossAbility.Laser;

public class BossOne extends Boss {

    private final double LASER_FREQ = 0.01;
    private final int laserDamage = 10;
    private static final double hitBoxWidth = 150;
    private static final double hitBoxHeight = 150;
    private static final int MAX_HEALTH_POINT = 1000;
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

    public Laser sendLaserIndicator() {
        return new Laser( new Location( 0,getLocation().getPositionY()+getHitBoxHeight()/2-5),getLocation().getPositionX(),3,0);
    }
    public Laser sendLaser () {
        return new Laser( new Location( 0,getLocation().getPositionY()+getHitBoxHeight()/2-25 ) ,getLocation().getPositionX(),50,laserDamage);
    }

    public double getLASER_FREQ() {
        return LASER_FREQ;
    }

  /*  @Override
    public int getBulletDamage() {
        return 0;
    }

    @Override
    public void setBulletDamage(int bulletDamage) {

    }*/
}