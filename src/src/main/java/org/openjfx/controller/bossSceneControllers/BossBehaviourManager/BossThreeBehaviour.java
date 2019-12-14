package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import org.openjfx.model.Boss.BossMap;
import org.openjfx.model.entities.BossAbility.LittleBoss;
import org.openjfx.view.BossMapView;
import org.openjfx.view.entities.BossAbilityViews.LittleBossView;

public class BossThreeBehaviour  extends BossDefaultBehaviour implements BossBehaviourAlgorithm {
    LittleBoss littleBoss;
    LittleBossView littleBossView;

    public BossThreeBehaviour(BossMap bossMap, BossMapView bossMapView) {
        super( bossMap, bossMapView);
    }

 /*   @Override
    public void useSpecialAbility()  {
        Boss boss = bossMap.getBoss();
        if( Math.random() < ((BossThree)boss).getLITTLE_BOSS_FREQ() ) {
            littleBoss = ((BossThree)boss).sendLittleBoss();
            abilityTimer = 1.0;
            littleBossView = new LittleBossView( littleBoss.getLocation().getPositionX(), littleBoss.getLocation().getPositionY(), littleBoss.getHitBoxWidth(), littleBoss.getHitBoxHeight());
            bossMap.getLittleBosses().add(littleBoss);
            bossMapView.addLittleBossView(littleBossView);
        }
    }

    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        //mapView.refreshLittleBoss(littleBossView, littleBoss.getLocation().getPositionX(), littleBoss.getLocation().getPositionY());
        if( littleBoss.getHitNumber() == 3) {
            bossMap.getLittleBosses().remove(littleBoss);
            bossMapView.removeLittleBossView( littleBossView);
        }
    }*/
}