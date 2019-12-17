package org.openjfx.controller.bossSceneControllers;

import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossOneBehaviour;
import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossBehaviourAlgorithm;
import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossThreeBehaviour;
import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossTwoBehaviour;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;

public class BossController {
    private BossBehaviourAlgorithm algorithm;
    private BossMap bossMap;
    public BossController(int level, BossMap bossMap) {
        this.bossMap = bossMap;
        selectAlgorithm( level);
    }

    private  void  selectAlgorithm ( int level) {
        switch (level) {
            case 1:
                algorithm = new BossOneBehaviour( bossMap);
                break;
            case 2:
                algorithm = new BossTwoBehaviour( bossMap);
                break;
            case 3:
                algorithm = new BossThreeBehaviour( bossMap);
                break;
            default:
                break;
        }
    }
    public void behave()  {
        if ( algorithm.getAbilityTimer() <= 0.0) {
            algorithm.moveBoss();
            algorithm.useSpecialAbility();

        }
        else {
            //  for ( GameObject specialAbility: bossController.getSpecialAbility())
            //   map.checkCollisions( specialAbility);
            algorithm.clockTick();
        }
        algorithm.shoot();
    }
}