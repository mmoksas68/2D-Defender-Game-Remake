package org.openjfx.model.bossEntities.Boss;

import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossBehaviourAlgorithm;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.Buff.BuffTypes;
import org.openjfx.model.commonEntities.FiringBehavior.BossGun;
import org.openjfx.model.commonEntities.FiringBehavior.FiringBehavior;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;


public abstract class Boss extends LocatableObject {

    public static final int SCORE_POINT = 10;
    private static double initialX = BossMap.MAP_WIDTH - BossMap.MAP_WIDTH /8;
    private static double initialY = BossMap.MAP_HEIGHT /2;
    private double velocity;
    private double bullet_prob;
    private int gunPower;
    private double gunFrequency;
    private int gunPeriod;
    private int gunTimer;
    private double bulletVelocity;
    private BossBehaviourAlgorithm algorithm;
    private FiringBehavior firingBehavior;
    private BuffTypes buffType;

    public Boss(double velocity, double hitBoxWidth, double hitBoxHeight, int healthPoint) {
        super( new Location(initialX,initialY), hitBoxWidth, hitBoxHeight, healthPoint);
        this.velocity = velocity;
        buffType = BuffTypes.EMPTY;
    }

    public void generateBuff(){
        double random = (Math.random()*22) -10;
        if(random < 8)
            buffType = BuffTypes.EMPTY;
        else if(random < 8.5)
            buffType = BuffTypes.HEALTH;
        else if(random < 9.5)
            buffType = BuffTypes.GUN_POWER;
        else if(random < 10)
            buffType = BuffTypes.GUN_TYPE;
        else if(random < 10.5)
            buffType = BuffTypes.FIRE_RATE;
        else if(random < 11.00)
            buffType = BuffTypes.SPEED;
        else if(random < 11.5)
            buffType = BuffTypes.HYPER_JUMP;
        else if(random < 12)
            buffType = BuffTypes.SMART_BOMB;
        else
            buffType = BuffTypes.EMPTY;
    }

    public BuffTypes getBuffType() {
        return buffType;
    }

    public double getVelocity() { return velocity; }

    public int getGunPower() {
        return gunPower;
    }

    public void setGunPower(int gunPower) {
        this.gunPower = gunPower;
    }

    public int getGunPeriod() {
        return gunPeriod;
    }

    public void setGunPeriod(int gunPeriod) {
        this.gunPeriod = gunPeriod;
    }

    public double getBulletVelocity() {
        return bulletVelocity;
    }

    public void setBulletVelocity(double bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }


    public Bullet fireBullet() {
        return new Bullet( new Location( getLocation().getPositionX(), getLocation().getPositionY()), gunPower,bulletVelocity,-1.0,0.0 );
    }

    public double getBullet_prob() {
        return bullet_prob;
    }

    public void setBullet_prob(double bullet_prob) {
        this.bullet_prob = bullet_prob;
    }

    public int getGunTimer() {
        return gunTimer;
    }

    public void setGunTimer(int gunTimer) {
        this.gunTimer = gunTimer;
    }

    public double getGunFrequency() {
        return gunFrequency;
    }

    public void setGunFrequency(double gunFrequency) {
        this.gunFrequency = gunFrequency;
    }

    public BossBehaviourAlgorithm getBehaviourAlgorithm() {
        return algorithm;
    }

    public void setBehaviourAlgorithm(BossBehaviourAlgorithm behaviourAlgorithm) {
        this.algorithm = behaviourAlgorithm;
    }

    public FiringBehavior getFiringBehavior() {
        return firingBehavior;
    }

    public void setFiringBehavior(FiringBehavior firingBehavior) {
        this.firingBehavior = firingBehavior;
    }

    public void behave()  {
        if ( algorithm.getAbilityTimer() <= 0.0) {
            algorithm.moveBoss();
            algorithm.useSpecialAbility();

        }
        else {
            algorithm.clockTick();
        }
        algorithm.shoot();
    }

}
