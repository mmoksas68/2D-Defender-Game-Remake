package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import org.openjfx.controller.SoundController;
import org.openjfx.model.bossEntities.Boss.BossTwo;
import org.openjfx.model.bossEntities.BossAbility.Rocket;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.bossEntities.BossAbility.Marker;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.MarkerView;

import java.util.ArrayList;

public class BossTwoBehaviour extends BossDefaultBehaviour {
    private ArrayList<Marker> markers;
    private  ArrayList<Rocket> rockets;

    public BossTwoBehaviour(BossMap bossMap) {
        super(bossMap);
        markers = new ArrayList<>();
    }

     @Override
   public void useSpecialAbility()  {
        BossTwo boss = (BossTwo) bossMap.getBoss();
        if ( Math.random() < boss.getROCKET_FREQ() ) {
            SoundController.sendRocket();
            markers = boss.markAreas( (bossMap.getBoss().getLocation().getPositionX() -2*Marker.radius),(BossMap.MAP_HEIGHT- Marker.radius));
            for ( Marker marker: markers) {
               bossMap.addSpecialAbility( marker);
            }
            rockets = boss.getRockets();
            for ( Rocket rocket : rockets) {
                bossMap.addSpecialAbility( rocket);
            }
            startAbilityTimer( 2.5);        // Don't forget to start timer!!!!!
        }

    }
    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        if ( !markers.get(0).getIsRocketFired()) {
            if (rockets.get(0).isDead()) {
                for (Marker marker : markers) {
                    marker.activate();
                }
            }
        }
        if ( abilityTimer <= 0) {
            bossMap.setFiring( false);
            for (Marker marker : markers) {
                marker.setDead(true);

            }
            for ( Rocket rocket : rockets)
                rocket.setDead( true);

             ( (BossTwo) bossMap.getBoss()).clearRockets();
        }

    }

    @Override
    public void startAbilityTimer(double time) {
        abilityTimer = time;
    }
}
