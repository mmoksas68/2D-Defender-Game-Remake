package org.openjfx.model.entities.Enemy;

import org.openjfx.model.Location;

public class Tier2 extends Enemy implements SimpleEnemy {
    private int impactRadius;

    public Tier2(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint, int damage, int velocity, int radarRadius, int impactRadius) {
        super(location, hitBoxWidth, hitBoxHeight, healthPoint, damage, velocity, radarRadius);
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

    @Override
    public void wonderAround() {

    }

    @Override
    public void evolve() {

    }

    @Override
    public void radarSearch() {

    }
}

