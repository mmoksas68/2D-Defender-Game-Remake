package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.Boss.BossThree;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.bossEntities.BossAbility.LittleBoss;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.LittleBossView;

public class BossThreeBehaviour  extends BossDefaultBehaviour {
    LittleBoss littleBoss;

    public BossThreeBehaviour(BossMap bossMap) {
        super( bossMap);
    }

     @Override
    public void useSpecialAbility()  {
        Boss boss = bossMap.getBoss();
        if( Math.random() < ((BossThree)boss).getLITTLE_BOSS_FREQ() ) {
            littleBoss = ((BossThree)boss).sendLittleBoss();
            bossMap.addSpecialAbility( littleBoss);
        }

    }

    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        //mapView.refreshLittleBoss(littleBossView, littleBoss.getLocation().getPositionX(), littleBoss.getLocation().getPositionY());
        if( littleBoss.getHitNumber() == 3) {
            littleBoss.setDead( true);
        }
    }

    @Override
    public void startAbilityTimer(double time) {
        abilityTimer = 1.0;
    }
}