package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

public interface BossBehaviourAlgorithm {
    void clockTick ();
    void startAbilityTimer( double time);
    void useSpecialAbility() ;
    void moveBoss();
    void shoot();
    double getAbilityTimer();
}
