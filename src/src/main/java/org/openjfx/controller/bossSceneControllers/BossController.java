package org.openjfx.controller.bossSceneControllers;

import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossOneBehaviour;
import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossThreeBehaviour;
import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossTwoBehaviour;
import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossBehaviourAlgorithm;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;

public class BossController {
    private BossBehaviourAlgorithm algorithm;
    private BossMap bossMap;
    private BossMapView bossMapView;
    public BossController(int level, BossMap bossMap, BossMapView bossMapView) {
        this.bossMap = bossMap;
        this.bossMapView = bossMapView;
        selectAlgorithm( level);
    }

    private  void  selectAlgorithm ( int level) {
        switch (level) {
            case 1:
                algorithm = new BossOneBehaviour( bossMap, bossMapView);
                break;
            case 2:
                algorithm = new BossTwoBehaviour( bossMap, bossMapView);
                break;
            case 3:
                algorithm = new BossThreeBehaviour( bossMap, bossMapView);
                break;
            default:
                break;
        }
    }
    public void behave()  {
        if ( algorithm.getAbilityTimer() <= 0.0) {
            algorithm.moveBoss();
           // algorithm.useSpecialAbility();

        }
        else {
            //  for ( GameObject specialAbility: bossController.getSpecialAbility())
            //   map.checkCollisions( specialAbility);
           // algorithm.clockTick();
            System.out.println(  bossMap.getSpacecraft1().getHealthPoint());
        }
        algorithm.shoot();
    }
}