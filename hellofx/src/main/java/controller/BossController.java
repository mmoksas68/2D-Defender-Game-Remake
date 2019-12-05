package controller;

import controller.behaviourManager.BossBehaviourAlgorithm;
import controller.behaviourManager.BossOneBehaviour;
import controller.behaviourManager.BossThreeBehaviour;
import controller.behaviourManager.BossTwoBehaviour;
import model.Map;
import view.MapView;

import java.io.FileNotFoundException;

public class BossController {
    private BossBehaviourAlgorithm algorithm;
    private Map map;
    private MapView mapView;
    public BossController(int level, Map map, MapView mapView) {
        this.map = map;
        this.mapView = mapView;
        selectAlgorithm( level);
    }

    private  void  selectAlgorithm ( int level) {
        switch (level) {
            case 1:
                algorithm = new BossOneBehaviour( map, mapView);
                break;
            case 2:
                algorithm = new BossTwoBehaviour( map, mapView);
                break;
            case 3:
                algorithm = new BossThreeBehaviour( map, mapView);
            default:
                break;
        }
    }
    public void behave() throws FileNotFoundException {
        if ( algorithm.getAbilityTimer() <= 0.0) {
            algorithm.moveBoss();
            algorithm.useSpecialAbility();

        }
        else {
          //  for ( GameObject specialAbility: bossController.getSpecialAbility())
             //   map.checkCollisions( specialAbility);
            algorithm.clockTick();
            System.out.println(  map.getSpaceCraft().getHealthPoint());
        }
        algorithm.shoot();
    }
}
