package org.openjfx.model.entities.Spacecraft;

import org.openjfx.model.FireBullets;
import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;
import org.openjfx.model.entities.Bullet.Bullet;

public class Spacecraft extends LocatableObject implements FireBullets {

    public static final int MAX_PACE = 30;
    public static final int MAX_SMARTBOMB = 3;
    private int qunFrequency;
    private int gunPower;
    private int gunType;
    private int bulletVelocity;
    private int velocity;
    private int healthPoint;
    private int currentBuffSlot;
    private int smartBombStock;
    private boolean isBatteryCharged;
    private boolean isBuffSlotOccupied;
    private boolean infiniteArmor;
    private boolean isDirectionLeft;

    public Spacecraft(Location location, int hitBoxWidth, int hitBoxHeight, int qunFrequency, int gunPower, int gunType, int bulletVelocity, int velocity, int healthPoint, int currentBuffSlot, int smartBombStock, boolean isBatteryCharged, boolean isBuffSlotOccupied, boolean infiniteArmor) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.qunFrequency = qunFrequency;
        this.gunPower = gunPower;
        this.gunType = gunType;
        this.bulletVelocity = bulletVelocity;
        this.velocity = velocity;
        this.healthPoint = healthPoint;
        this.currentBuffSlot = currentBuffSlot;
        this.smartBombStock = smartBombStock;
        this.isBatteryCharged = isBatteryCharged;
        this.isBuffSlotOccupied = isBuffSlotOccupied;
        this.infiniteArmor = infiniteArmor;
    }

    public void useBuffSlot(){

    }

    public void useSmartBomb(){

    }

    public void useHyperJump(){

    }

    public void freezeEnemy(){

    }

    public static int getMaxPace() {
        return MAX_PACE;
    }

    public static int getMaxSmartbomb() {
        return MAX_SMARTBOMB;
    }

    public int getQunFrequency() {
        return qunFrequency;
    }

    public void setQunFrequency(int qunFrequency) {
        this.qunFrequency = qunFrequency;
    }

    public int getGunPower() {
        return gunPower;
    }

    public void setGunPower(int gunPower) {
        this.gunPower = gunPower;
    }

    public int getGunType() {
        return gunType;
    }

    public void setGunType(int gunType) {
        this.gunType = gunType;
    }

    public int getBulletVelocity() {
        return bulletVelocity;
    }

    public void setBulletVelocity(int bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
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

    @Override
    public Bullet fireBullet() {
        int direction = isDirectionLeft() ? 10 : -10;
        return new Bullet(new Location(this.getLocation().getPositionX(), this.getLocation().getPositionY()), 5,2, 5, 5, direction, 0);
    }
}
