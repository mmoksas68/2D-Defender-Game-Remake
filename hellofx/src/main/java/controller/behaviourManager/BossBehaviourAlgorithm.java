package controller.behaviourManager;

import java.io.FileNotFoundException;

public interface BossBehaviourAlgorithm {
    void clockTick ();
    void useSpecialAbility() throws FileNotFoundException;
    void moveBoss();
    void shoot() throws FileNotFoundException;
    double getAbilityTimer();
}
