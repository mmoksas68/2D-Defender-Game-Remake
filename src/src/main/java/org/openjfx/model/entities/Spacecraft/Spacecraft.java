package org.openjfx.model.entities.Spacecraft;

import org.openjfx.model.FireBullets;
import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.utilization.PositionHelper;

public class Spacecraft extends LocatableObject implements FireBullets {

    public static final int MAX_PACE = 5;
    public static final int MAX_SMARTBOMB = 3;
    public static final double WIDTH_SCALE = 0.03;
    public static final double HEIGHT_SCALE = 0.05;
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

    public Spacecraft(Location location, int hitBoxWidth, int hitBoxHeight, int gunPeriod, int bulletDamage, int gunType, int bulletVelocity, double velocity, int currentBuffSlot, int smartBombStock, boolean isBatteryCharged, boolean isBuffSlotOccupied, boolean infiniteArmor) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.gunPeriod = gunPeriod;
        this.bulletDamage = bulletDamage;
        this.gunType = gunType;
        this.bulletVelocity = bulletVelocity;
        this.velocity = velocity;
        this.currentBuffSlot = currentBuffSlot;
        this.smartBombStock = smartBombStock;
        this.isBatteryCharged = isBatteryCharged;
        this.isBuffSlotOccupied = isBuffSlotOccupied;
        this.infiniteArmor = infiniteArmor;
    }

    public void useBuffSlot() {

    }

    public void useSmartBomb() {

    }

    public void useHyperJump() {

    }

    public void freezeEnemy() {

    }

    public static int getMaxPace() {
        return MAX_PACE;
    }

    public static int getMaxSmartbomb() {
        return MAX_SMARTBOMB;
    }


    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        if(Math.abs(velocity) > MAX_PACE)
            this.velocity = velocity > 0 ? MAX_PACE : -MAX_PACE;
        this.velocity = velocity;
    }

    public int getCurrentBuffSlot() {
        return currentBuffSlot;
    }

    public void setCurrentBuffSlot(int currentBuffSlot) {
        this.currentBuffSlot = currentBuffSlot;
    }

    public int getSmartBombStock() {
        return smartBombStock;
    }

    public void setSmartBombStock(int smartBombStock) {
        this.smartBombStock = smartBombStock;
    }

    public boolean isBatteryCharged() {
        return isBatteryCharged;
    }

    public void setBatteryCharged(boolean batteryCharged) {
        isBatteryCharged = batteryCharged;
    }

    public boolean isBuffSlotOccupied() {
        return isBuffSlotOccupied;
    }

    public void setBuffSlotOccupied(boolean buffSlotOccupied) {
        isBuffSlotOccupied = buffSlotOccupied;
    }

    public boolean isInfiniteArmor() {
        return infiniteArmor;
    }

    public void setInfiniteArmor(boolean infiniteArmor) {
        this.infiniteArmor = infiniteArmor;
    }

    public boolean isDirectionLeft() {
        return isDirectionLeft;
    }

    public void setDirectionLeft(boolean directionLeft) {
        isDirectionLeft = directionLeft;
    }

    public int getGunType() {
        return gunType;
    }

    public void setGunType(int gunType) {
        this.gunType = gunType;
    }

    @Override
    public int getGunPeriod() {
        return gunPeriod;
    }

    @Override
    public void setGunPeriod(int gunPeriod) {
        this.gunPeriod = gunPeriod;
    }

    @Override
    public int getGunTimer() {
        return gunTimer;
    }

    @Override
    public void setGunTimer(int gunTimer) {
        this.gunTimer = gunTimer;
    }

    @Override
    public int getBulletDamage() {
        return bulletDamage;
    }

    @Override
    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    @Override
    public int getBulletVelocity() {
        return bulletVelocity;
    }

    @Override
    public void setBulletVelocity(int bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    @Override
    public Bullet fireBullet() {
        int direction = isDirectionLeft() ? -10 : 10;
        PositionHelper spacecraftHelper = new PositionHelper(this);
        Location location = isDirectionLeft() ?
                            new Location(spacecraftHelper.getLeft() - 10,
                                    ((spacecraftHelper.getTop() + spacecraftHelper.getBottom()) / 2))
                            :
                            new Location(spacecraftHelper.getRight() + 10,
                                    ((spacecraftHelper.getTop() + spacecraftHelper.getBottom()) / 2));

        return new Bullet(location,5, 2, 30, 10, direction, 0);
    }
}
