package org.openjfx.model.bossEntities.BossAbility;

import org.openjfx.controller.bossSceneControllers.abilityBehaviourManager.LittleBossAbilityAlgorithm;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class LittleBoss extends SpecialAbility {
    private static int damage = 50;
    private static double hitBoxWidth = BossMap.MAP_WIDTH /30;
    private static double hitBoxHeight = BossMap.MAP_WIDTH /30;
    private static int healthPoint = 50;
    private static double velocity = 10.0;
    private int hitNumber;
    public LittleBoss(Location location) {
        super(location, hitBoxWidth, hitBoxHeight, healthPoint);
        setDamage( damage);
        setVelocity( velocity);
        this.hitNumber = 0;
        setxDir(- Math.random());
        if( (int)(Math.random() * 2) == 0)
            setyDir( Math.random());
        else
            setyDir( Math.random());

        setAbilityBehaviourAlgorithm( new LittleBossAbilityAlgorithm( this));
    }


    public int getHitNumber() { return hitNumber; }

    public void setHitNumber(int hitNumber) { this.hitNumber = hitNumber; }

}
