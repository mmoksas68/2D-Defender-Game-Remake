package org.openjfx.controller.bossSceneControllers.abilityBehaviourManager;

import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;

public  class DefaultAbilityAlgorithm implements AbilityBehaviourAlgorithm {
    SpecialAbility specialAbility;
   public DefaultAbilityAlgorithm (SpecialAbility specialAbility) {
       this.specialAbility = specialAbility;
   }
    public void moveSpecialAbility() {
        specialAbility.moveWithVelocityVector( specialAbility.getVelocity(), specialAbility.getxDir(), specialAbility.getyDir());
    }
}
