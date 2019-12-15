package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.bossEntities.BossAbility.Marker;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.MarkerView;

import java.util.ArrayList;

public class BossTwoBehaviour extends BossDefaultBehaviour implements BossBehaviourAlgorithm {
    private ArrayList<Marker> markers;
    private ArrayList<MarkerView> markerViews;

    public BossTwoBehaviour(BossMap bossMap, BossMapView bossMapView) {
        super(bossMap, bossMapView);
        markers = new ArrayList<>();
        markerViews = new ArrayList<>();
    }

   /*  @Override
   public void useSpecialAbility()  {
        BossTwo boss = (BossTwo) bossMap.getBoss();
        if ( Math.random() < boss.getROCKET_FREQ() ) {
            markers = boss.sendRockets( 550,500);
            for ( Marker marker: markers) {
                MarkerView mw = new MarkerView( marker.getLocation().getPositionX(), marker.getLocation().getPositionY(),marker.getHitBoxWidth(),marker.getHitBoxHeight());
                bossMapView.addMarkerView( mw);
                markerViews.add( mw);
            }
            abilityTimer = 1.5;         // Don't forget to start timer!!!!!
        }

    }
    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        if ( abilityTimer <= 0) {
            for (MarkerView markerView : markerViews) {
                bossMapView.deleteMarkerView(markerView);
            }
            markerViews.clear();
        }

    }*/
}