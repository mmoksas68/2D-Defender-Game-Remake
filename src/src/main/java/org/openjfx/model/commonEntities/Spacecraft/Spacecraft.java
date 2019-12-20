package org.openjfx.model.commonEntities.Spacecraft;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.FiringBehavior.FiringBehavior;
import org.openjfx.model.commonEntities.FiringBehavior.SpacecraftGun;

enum GunTypes{
    SINGLE,
    DOUBLE,
    TRIPLE
}


public class Spacecraft extends LocatableObject {
    public static final int MAX_HEALTH = 1000000;
    public static final double WIDTH = 120;
    public static final double HEIGHT = 90;
    public static final int MAX_SMARTBOMB = 3;
    public static final int SMARTBOMB_RADIUS = 5;
    public static final double MAX_VELOCITY = 90;
    public static final double SHIELD_DURATION = 90;
    public static final double BULLET_VELOCITY = 30;
    public static final double MAX_BULLET_DAMAGE = 120;
    public static final double MIN_GUNPERIOD = 120;
    public static final int INIT_GUNPERIOD = 15;
    public static final double INIT_VELOCITY = 10;
    public static final int INIT_BULLET_DAMAGE = 10;

    private double velocity;
    private int smartBombStock;
    private boolean isShieldActive;
    private int shieldTimer;
    private boolean isDirectionLeft;
    private int hyperJumpBattery;
    private int batteryTimer;
    private boolean isMoving;
    private FiringBehavior spacecraftGun;
    private GunTypes gunTypes;
    private int choosenPicNo;

    public Spacecraft(Location location) {
        super(location, WIDTH, HEIGHT, MAX_HEALTH);
        velocity = INIT_VELOCITY;
        smartBombStock = MAX_SMARTBOMB;
        isShieldActive = false;
        shieldTimer = 0;
        isDirectionLeft = false;
        hyperJumpBattery = 100;
        batteryTimer = 0;
        isMoving = false;
        spacecraftGun = new SpacecraftGun(INIT_GUNPERIOD, INIT_BULLET_DAMAGE, 0, BULLET_VELOCITY, this);
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public int getShieldTimer() {
        return shieldTimer;
    }

    public void setShieldTimer(int shieldTimer) {
        this.shieldTimer = shieldTimer;
    }

    public int getSmartBombStock() {
        return smartBombStock;
    }

    public void setSmartBombStock(int smartBombStock) {
        this.smartBombStock = smartBombStock;
    }

    public boolean isShieldActive() {
        return isShieldActive;
    }

    public void setShieldActive(boolean shieldActive) {
        isShieldActive = shieldActive;
    }

    public boolean isDirectionLeft() {
        return isDirectionLeft;
    }

    public void setDirectionLeft(boolean directionLeft) {
        isDirectionLeft = directionLeft;
    }

    public int getHyperJumpBattery() {
        return hyperJumpBattery;
    }

    public void setHyperJumpBattery(int hyperJumpBattery) {
        this.hyperJumpBattery = hyperJumpBattery;
    }

    public int getBatteryTimer() {
        return batteryTimer;
    }

    public void setBatteryTimer(int batteryTimer) {
        this.batteryTimer = batteryTimer;
    }

    public FiringBehavior getSpacecraftGun() {
        return spacecraftGun;
    }

    public void setSpacecraftGun(FiringBehavior spacecraftGun) {
        this.spacecraftGun = spacecraftGun;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getChoosenPicNo() {
        return choosenPicNo;
    }

    public void setChoosenPicNo(int choosenPicNo) {
        this.choosenPicNo = choosenPicNo;
    }
}
