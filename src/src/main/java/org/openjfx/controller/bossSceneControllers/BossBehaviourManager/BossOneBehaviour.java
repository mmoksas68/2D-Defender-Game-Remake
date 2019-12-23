package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import org.openjfx.controller.SoundController;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.Boss.BossOne;
import org.openjfx.model.bossEntities.BossAbility.Laser;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.LaserView;

public class BossOneBehaviour extends BossDefaultBehaviour {
    Laser laser;
    boolean laserUsed = false;
    boolean notifyController = false;
    private double timerLaser = 0.5;
    public BossOneBehaviour(BossMap bossMap) {
        super( bossMap);
    }
    @Override
    public void useSpecialAbility()  {
        Boss boss = bossMap.getBoss();
        if ( Math.random() < ((BossOne) boss).getLASER_FREQ()) {
              startAbilityTimer(1.0);
              laser = ((BossOne) boss).sendLaser();
              laserUsed = true;
              notifyController = true;
        }
    }

    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        if ( abilityTimer <= 0) {
            laser.setDead( true);
            notifyController = false;
        } else if ( abilityTimer <= timerLaser && laserUsed) {
            SoundController.sendLaser();
            bossMap.addSpecialAbility( laser);
            laserUsed = false;
        }
    }

    @Override
    public void startAbilityTimer(double time) {
        abilityTimer = time;
    }
    public boolean isNotifyController() {
        return notifyController;
    }
}
