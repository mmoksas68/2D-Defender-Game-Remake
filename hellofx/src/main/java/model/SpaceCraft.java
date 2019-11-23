package model;

public class SpaceCraft extends GameObject implements FireBullets{
    public static final int MAX_HEALTH = 100;
    public static final int MAX_PACE = 5;
    public static final int MAX_SMARTBOMB = 3;
    public static final double WIDTH_SCALE = 90;
    public static final double HEIGHT_SCALE = 120;
    private int gunPeriod;
    private int gunTimer = 0;
    private int bulletDamage;
    private int gunType;
    private int bulletVelocity;
    private double velocity;
    private int currentBuffSlot;
    private int smartBombStock;
    private boolean isBatteryCharged;
    private boolean isBuffSlotOccupied;
    private boolean infiniteArmor;
    private boolean isDirectionLeft;
    private double gunPower;
    private int healthPoint;

    public SpaceCraft () {
        super( new Location( 50,280), 10,80,40);
        healthPoint = 1000;
        bulletVelocity = 30;
        gunPower = 30;
    }

  /*  public Bullet fire () {
        return new Bullet(getID(),getX() + getWidth(),getY() + getHeight()/2,bulletVelocity);
    }*/

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public double getGunPower() {
        return gunPower;
    }

    public void setGunPower(double gunPower) {
        this.gunPower = gunPower;
    }

    @Override
    public Bullet fireBullet() {
        return new Bullet(getID(),new Location(getLocation().getPositionX() + getWidth(),getLocation().getPositionY() + getHeight()/2),bulletVelocity);
    }

    @Override
    public int getBulletVelocity() {
        return 0;
    }

    @Override
    public void setBulletVelocity(int bulletVelocity) {

    }

    @Override
    public int getBulletDamage() {
        return 0;
    }

    @Override
    public void setBulletDamage(int bulletDamage) {

    }

    @Override
    public int getGunPeriod() {
        return 0;
    }

    @Override
    public void setGunPeriod(int gunPeriod) {

    }

    @Override
    public int getGunTimer() {
        return 0;
    }

    @Override
    public void setGunTimer(int gunTimer) {

    }
}
