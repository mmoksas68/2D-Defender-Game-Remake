package org.openjfx.model.bossEntities.BossAbility;

import org.openjfx.controller.bossSceneControllers.abilityBehaviourManager.RocketAbilityAlgorithm;
import org.openjfx.model.commonEntities.Location;

public class Rocket extends SpecialAbility{
    private static double hitBoxHeight = 25;
    private static double hitBoxWidth = 25;
    private static int healthPoint = 200;
    private static int damage = 50;

    private double destinationX;
    private double destinationY;
    public Rocket (Location location) {
        super( location, hitBoxWidth, hitBoxHeight, healthPoint);
        setDamage( damage);
        setAbilityBehaviourAlgorithm( new RocketAbilityAlgorithm(this));
    }

    public double getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
    }
}
