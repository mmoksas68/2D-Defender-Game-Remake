package org.openjfx.model.bossEntities.BossAbility;

import org.openjfx.controller.bossSceneControllers.abilityBehaviourManager.DefaultAbilityAlgorithm;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class Laser extends SpecialAbility {

    private static int  damage = 1000;
    private static double hitBoxHeight = 15.0;
    private static int healthPoint = Integer.MAX_VALUE;
    private static int velocity = 0;

    public Laser(Location location, double hitBoxWidth) {
        super( location, hitBoxWidth, hitBoxHeight, healthPoint);
        setDamage( damage);
        setVelocity( velocity);
        setAbilityBehaviourAlgorithm( new DefaultAbilityAlgorithm( this));
    }

}
