package org.openjfx.model.preBossEntities.Enemy;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.FiringBehavior.NoGun;

import java.io.Serializable;

public class Tier2Enemy extends Enemy {
    public static final double WIDTH = 110;
    public static final double HEIGHT = 80;
    public static final int MAX_HEALTH = 10;
    public static final int SCORE_POINT = 4;
    public static final int CLASHING_DAMAGE = 15;
    public static final double IMPACT_RADIUS = 200;
    public static final int VELOCITY = 6;
    public static final int RADAR_RADIUS = 300;
    public static final int RUSHING_DURATION = 80;

    private int rushingTimer = 0;
    private Location rushingDestination;
    private double impactRadius;
    private boolean isRushing;


    public Tier2Enemy(Location location, boolean isEvolved) {
        super(location, WIDTH, HEIGHT, MAX_HEALTH, VELOCITY, RADAR_RADIUS, isEvolved, SCORE_POINT);
        super.setFiringBehavior( new NoGun());
        impactRadius = isEvolved ? 2*IMPACT_RADIUS : IMPACT_RADIUS;
        isRushing = false;
    }

    public boolean isRushing() {
        return isRushing;
    }

    public void setRushing(boolean rushing) {
        isRushing = rushing;
    }

    public double getImpactRadius() {
        return impactRadius;
    }

    public void setImpactRadius(double impactRadius) {
        this.impactRadius = impactRadius;
    }

    public Location getRushingDestination() {
        return rushingDestination;
    }

    public void setRushingDestination(Location rushingDestination) {
        this.rushingDestination = rushingDestination;
    }

    public int getRushingTimer() {
        return rushingTimer;
    }

    public void setRushingTimer(int rushingTimer) {
        this.rushingTimer = rushingTimer;
    }
}

