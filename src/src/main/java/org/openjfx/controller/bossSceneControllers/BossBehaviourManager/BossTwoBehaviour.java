package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

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
            markers = boss.markAreas( (bossMap.getBoss().getLocation().getPositionX() -2*Marker.radius),(BossMap.MAP_HEIGHT- Marker.radius));
            for ( Marker marker: markers) {
               bossMap.addSpecialAbility( marker);
            }
            rockets = boss.getRockets();
            for ( Rocket rocket : rockets) {
                bossMap.addSpecialAbility( rocket);
                for( Marker marker: markers)
                    marker.setIsRocketFired(true);
            }
            startAbilityTimer( 2.0);        // Don't forget to start timer!!!!!
        }

    }
    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        if ( abilityTimer <= 0) {
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
