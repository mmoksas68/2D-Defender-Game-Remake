package org.openjfx.model.entities.Enemy;

import org.openjfx.model.Location;

public class Tier2 extends Enemy implements SimpleEnemy {
    public static final double WIDTH_SCALE = 0.03;
    public static final double HEIGHT_SCALE = 0.05;
    public static final int MAX_HEALTH = 5;
    private int impactRadius;

    public Tier2(Location location, double hitBoxWidth, double hitBoxHeight, int damage, int velocity, int radarRadius, int impactRadius, boolean isEvolved) {
        super(location, hitBoxWidth, hitBoxHeight, damage, velocity, radarRadius, isEvolved, MAX_HEALTH);
        this.impactRadius = impactRadius;
    }

    public int getImpactRadius() {
        return impactRadius;
    }

    public void setImpactRadius(int impactRadius) {
        this.impactRadius = impactRadius;
    }

    public void crush(){

    }

    public void runIntoEnemy(){

    }

}

