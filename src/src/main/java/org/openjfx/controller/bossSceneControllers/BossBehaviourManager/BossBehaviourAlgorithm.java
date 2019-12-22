package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import java.io.Serializable;

public interface BossBehaviourAlgorithm extends Serializable {
    void clockTick ();
    void startAbilityTimer( double time);
    void useSpecialAbility() ;
    void moveBoss();
    void shoot();
    double getAbilityTimer();
}
