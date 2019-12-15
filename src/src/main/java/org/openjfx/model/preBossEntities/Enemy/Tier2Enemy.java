package org.openjfx.model.preBossEntities.Enemy;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.FiringBehavior.NoGun;

public class Tier2Enemy extends Enemy {
    public static final double WIDTH = 100;
    public static final double HEIGHT = 60;
    public static final int MAX_HEALTH = 1;
    public static final int SCORE_POINT = 1;
    public static final int CLASHING_DAMAGE = 30;
    public static final double IMPACT_RADIUS = 30;
    public static final int VELOCITY = 10;
    public static final int RADAR_RADIUS = 100;
    public static final int RUSHING_DURATION = 100;

    private int rushingTimer = 0;
    private Location rushingDestination;
    private double impactRadius;
    private boolean isRushing;


    public Tier2Enemy(Location location, boolean isEvolved) {
        super(location, WIDTH, HEIGHT, MAX_HEALTH, RADAR_RADIUS, VELOCITY, isEvolved, SCORE_POINT);
        super.setFiringBehavior( new NoGun());
        impactRadius = isEvolved ? 2*IMPACT_RADIUS : IMPACT_RADIUS;
        isRushing = false;
    }

}

