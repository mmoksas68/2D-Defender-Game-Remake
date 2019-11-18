package model;

public class BossOne extends Boss {

    private int laserDamage;
    public BossOne () {
        super ( 3, 150,150);
        setHealthPoint(10000);
        setBulletVelocity( 30);
        laserDamage = 1000;
    }
    public Laser sendLaserIndicator() {
        return new Laser( 0,getY()+getHeight()/2-5,getX(),3,0);
    }
    public Laser sendLaser () {
        return new Laser( 0, getY()+getHeight()/2-25 , getX(),50,laserDamage);
    }

}
