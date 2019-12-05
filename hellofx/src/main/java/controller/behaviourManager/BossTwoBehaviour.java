package controller.behaviourManager;

import model.BossTwo;
import model.Map;
import model.Marker;
import view.MapView;
import view.MarkerView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BossTwoBehaviour extends BossDefaultBehaviour implements BossBehaviourAlgorithm {
    private ArrayList<Marker> markers;
    private ArrayList<MarkerView> markerViews;
    public BossTwoBehaviour(Map map, MapView mapView) {
        super(map, mapView);
        markers = new ArrayList<>();
        markerViews = new ArrayList<>();
    }

    @Override
    public void useSpecialAbility() throws FileNotFoundException {
        BossTwo boss = (BossTwo) map.getBoss();
        if ( Math.random() < boss.getROCKET_FREQ() ) {
            markers = boss.sendRockets( 550,500);
            for ( Marker marker: markers) {
                MarkerView mw = new MarkerView( marker.getLocation().getPositionX(), marker.getLocation().getPositionY(),marker.getWidth(),marker.getHeight());
                mapView.addMarkerView( mw);
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
                mapView.deleteMarkerView(markerView);
            }
            markerViews.clear();
        }

    }
}
