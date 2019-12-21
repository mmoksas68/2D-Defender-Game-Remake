package org.openjfx.controller.bossSceneControllers.abilityBehaviourManager;

import org.openjfx.model.bossEntities.BossAbility.Marker;
import org.openjfx.model.bossEntities.BossAbility.Rocket;
import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;

public class RocketAbilityAlgorithm implements AbilityBehaviourAlgorithm {
    SpecialAbility specialAbility;
   public RocketAbilityAlgorithm (SpecialAbility specialAbility) {
        this.specialAbility = specialAbility;
    }
    @Override
    public void moveSpecialAbility() {
        Rocket rocket = (Rocket) specialAbility;
        if ( rocket.getLocation().getPositionX() <= rocket.getDestinationX())
            rocket.setDead( true);
        else
        rocket.moveWithVelocityVector( rocket.getVelocity(), rocket.getxDir(),rocket.getyDir());

    }
}
