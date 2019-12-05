package model;

public class BossThree extends Boss {

    private final double LITTLE_BOSS_FREQ = 0.01;

    public BossThree( ) {
        super(3, 150,150);
        setHealthPoint(10000);
        setBulletVelocity( 10);
        setGunFrequency( 0.07);
    }

    public double getLITTLE_BOSS_FREQ() {
        return LITTLE_BOSS_FREQ;
    }

    public LittleBoss sendLittleBoss() {
        return new LittleBoss( new Location( getLocation().getPositionX(),getLocation().getPositionY() + getHeight()/2));
    }
}
