package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.Boss.BossOne;
import org.openjfx.model.bossEntities.BossAbility.Laser;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.LaserView;

public class BossOneBehaviour extends BossDefaultBehaviour {
    Laser laser;
    public BossOneBehaviour(BossMap bossMap) {
        super( bossMap);
    }
    @Override
    public void useSpecialAbility()  {
        Boss boss = bossMap.getBoss();
        if ( Math.random() < ((BossOne) boss).getLASER_FREQ()) {
            laser = ((BossOne) boss).sendLaser();
            bossMap.addSpecialAbility( laser);
            startAbilityTimer( 1.0);
        }
    }

    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        if ( abilityTimer <= 0) {
            laser.setDead( true);
        }
    }

    @Override
    public void startAbilityTimer(double time) {
        abilityTimer = time;
    }
}
