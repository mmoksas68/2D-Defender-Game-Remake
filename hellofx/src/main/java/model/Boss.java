package model;

import java.util.ArrayList;

public abstract class Boss extends GameObject {
    private double healthPoint;
    private double gunPower;
    private double gunFrequency;
    private double bulletVelocity;
    private ArrayList<String> dialogues;
    public Boss(double velocity, double width, double height) {
        super(600, 100,velocity,width,height);
    }

    public double getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(double healthPoint) {
        this.healthPoint = healthPoint;
    }

    public double getGunPower() {
        return gunPower;
    }

    public void setGunPower(double gunPower) {
        this.gunPower = gunPower;
    }

    public double getGunFrequency() {
        return gunFrequency;
    }

    public void setGunFrequency(double gunFrequency) {
        this.gunFrequency = gunFrequency;
    }

    public double getBulletVelocity() {
        return bulletVelocity;
    }

    public void setBulletVelocity(double bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public void setDialogues(ArrayList<String> dialogues) {
        this.dialogues = dialogues;
    }
    public Bullet sendBullet() {
        return new Bullet( getID(), getX(), getY(), getBulletVelocity());
    }
}
