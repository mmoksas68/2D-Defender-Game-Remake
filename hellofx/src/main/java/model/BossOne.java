package model;

public class BossOne extends Boss {

    private final double LASER_FREQ = 0.01;
    private int laserDamage;
    public BossOne () {
        super ( 3, 150,150);
        setHealthPoint(10000000);
        setBulletVelocity( 10);
        setGunFrequency( 0.07);
        laserDamage = 1000;
    }
    public Laser sendLaserIndicator() {
        return new Laser( new Location( 0,getLocation().getPositionY()+getHeight()/2-5),getLocation().getPositionX(),3,0);
    }
    public Laser sendLaser () {
        return new Laser( new Location( 0,getLocation().getPositionY()+getHeight()/2-25 ) ,getLocation().getPositionX(),50,laserDamage);
    }

    public double getLASER_FREQ() {
        return LASER_FREQ;
    }
}
