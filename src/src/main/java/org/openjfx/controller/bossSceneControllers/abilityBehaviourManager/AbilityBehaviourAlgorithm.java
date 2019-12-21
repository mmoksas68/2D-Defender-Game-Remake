package org.openjfx.controller.bossSceneControllers.abilityBehaviourManager;

import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;

import java.io.Serializable;

public interface AbilityBehaviourAlgorithm extends Serializable {
    void moveSpecialAbility();
}
