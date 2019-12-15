package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.LaserView;

public class BossOneBehaviour extends BossDefaultBehaviour implements BossBehaviourAlgorithm {
    LaserView laserView;
    public BossOneBehaviour(BossMap bossMap, BossMapView bossMapView) {
        super( bossMap, bossMapView);
    }
  /*  @Override
    public void useSpecialAbility()  {
        Boss boss = bossMap.getBoss();
        if ( Math.random() < ((BossOne) boss).getLASER_FREQ()) {
            Laser laser = ((BossOne) boss).sendLaser();

            //   specialAbility.add( ((BossOne) boss).sendLaser());
            abilityTimer = 1.0;
            laserView = new LaserView(laser.getLocation().getPositionX(), laser.getLocation().getPositionY(), laser.getHitBoxWidth(),laser.getHitBoxHeight());
            bossMapView.addLaserView(laserView);
        }
    }

    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        if ( abilityTimer <= 0) {
            bossMapView.removeLaserView(laserView);
        }
    }*/
}
