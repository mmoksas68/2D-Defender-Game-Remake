package org.openjfx.model.bossEntities.BossAbility;

import org.openjfx.controller.bossSceneControllers.abilityBehaviourManager.AbilityBehaviourAlgorithm;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

import java.io.Serializable;

public class SpecialAbility extends LocatableObject{
    private double velocity;
    private double xDir;
    private double yDir;
    private int damage;
    private AbilityBehaviourAlgorithm abilityBehaviourAlgorithm;
    public SpecialAbility (Location location, double hitBoxWidth, double hitBoxHeight, int healthPoint) {
        super(location, hitBoxWidth,hitBoxHeight, healthPoint );

    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getxDir() {
        return xDir;
    }

    public void setxDir(double xDir) {
        this.xDir = xDir;
    }

    public double getyDir() {
        return yDir;
    }

    public void setyDir(double yDir) {
        this.yDir = yDir;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAbilityBehaviourAlgorithm(AbilityBehaviourAlgorithm abilityBehaviourAlgorithm) {
        this.abilityBehaviourAlgorithm = abilityBehaviourAlgorithm;
    }

    public AbilityBehaviourAlgorithm getAbilityBehaviourAlgorithm() {
        return abilityBehaviourAlgorithm;
    }
}
