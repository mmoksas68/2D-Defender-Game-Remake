package controller;

import controller.collisionManager.CollisionAlgorithm;
import model.GameObject;
import model.Map;

import java.util.ArrayList;


public class BossMapController {
    Map map;
    CollisionAlgorithm algorithm;
    public BossMapController (Map map) {
        this.map = map;
    }
    public void setCollisionAlgorithm (CollisionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
    public void checkBossMapSituation() {

    }


}
