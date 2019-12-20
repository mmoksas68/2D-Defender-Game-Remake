package org.openjfx.model.bossEntities.BossAbility;

import org.openjfx.controller.bossSceneControllers.abilityBehaviourManager.DefaultAbilityAlgorithm;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class Marker extends SpecialAbility {
    public static double radius = BossMap.MAP_WIDTH /15;
    private static int healthPoint = Integer.MAX_VALUE;
    private static int damage = 50;
    private boolean isRocketFired;
    public Marker(Location location) {
        super( location, radius, radius, healthPoint);
        setAbilityBehaviourAlgorithm( new DefaultAbilityAlgorithm( this));

        isRocketFired = false;
    }

    public boolean getIsRocketFired() {
        return isRocketFired;
    }

    public void activate () {
        isRocketFired = true;
        setDamage( damage);
    }

}
