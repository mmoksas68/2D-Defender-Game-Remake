package org.openjfx.model.preBossEntities.Enemy;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Buff.BuffTypes;
import org.openjfx.model.commonEntities.FiringBehavior.FiringBehavior;

import java.io.Serializable;


public abstract class Enemy extends LocatableObject{
    private double velocity;
    private int radarRadius;
    private Location destinationLocation;
    private EnemyDestinations destinationType;
    private BuffTypes buffType;
    private boolean isEvolved;
    private int changeDirectionPeriod = 40;
    private int changeDirectionTimer = 0;
    private FiringBehavior firingBehavior;
    private int scorePoint;

    public Enemy(Location location, double hitBoxWidth, double hitBoxHeight, int healthPoint, double velocity, int radarRadius, boolean isEvolved, int scorePoint) {
        super(location, hitBoxWidth, hitBoxHeight, isEvolved ? 2*healthPoint : healthPoint);
        this.isEvolved = isEvolved;
        this.velocity = velocity;
        this.radarRadius = isEvolved ? 2*radarRadius : radarRadius;
        this.scorePoint = isEvolved ? 2*scorePoint : scorePoint;
        this.destinationType = EnemyDestinations.RandomLocation;
        this.destinationLocation = new Location(0,0 );
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public int getRadarRadius() {
        return radarRadius;
    }

    public void setRadarRadius(int radarRadius) {
        this.radarRadius = radarRadius;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(Location destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public EnemyDestinations getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(EnemyDestinations destinationType) {
        this.destinationType = destinationType;
    }

    public BuffTypes getBuffType() {
        return buffType;
    }

    public void setBuffType(BuffTypes buffType) {
        this.buffType = buffType;
    }

    public boolean isEvolved() {
        return isEvolved;
    }

    public void setEvolved(boolean evolved) {
        isEvolved = evolved;
    }

    public int getChangeDirectionPeriod() {
        return changeDirectionPeriod;
    }

    public void setChangeDirectionPeriod(int changeDirectionPeriod) {
        this.changeDirectionPeriod = changeDirectionPeriod;
    }

    public int getChangeDirectionTimer() {
        return changeDirectionTimer;
    }

    public void setChangeDirectionTimer(int changeDirectionTimer) {
        this.changeDirectionTimer = changeDirectionTimer;
    }

    public FiringBehavior getFiringBehavior() {
        return firingBehavior;
    }

    public void setFiringBehavior(FiringBehavior firingBehavior) {
        this.firingBehavior = firingBehavior;
    }

    public int getScorePoint() {
        return scorePoint;
    }

    public void setScorePoint(int scorePoint) {
        this.scorePoint = scorePoint;
    }
}
