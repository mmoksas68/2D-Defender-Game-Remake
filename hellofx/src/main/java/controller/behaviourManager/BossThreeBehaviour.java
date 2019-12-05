package controller.behaviourManager;

import model.Boss;
import model.BossThree;
import model.LittleBoss;
import model.Map;
import view.LittleBossView;
import view.MapView;

import java.io.FileNotFoundException;

public class BossThreeBehaviour extends BossDefaultBehaviour implements BossBehaviourAlgorithm {
    LittleBoss littleBoss;
    LittleBossView littleBossView;

    public BossThreeBehaviour(Map map, MapView mapView) {
        super( map, mapView);
    }

    @Override
    public void useSpecialAbility() throws FileNotFoundException {
        Boss boss = map.getBoss();
        if( Math.random() < ((BossThree)boss).getLITTLE_BOSS_FREQ() ) {
            littleBoss = ((BossThree)boss).sendLittleBoss();
            abilityTimer = 1.0;
            littleBossView = new LittleBossView( littleBoss.getLocation().getPositionX(), littleBoss.getLocation().getPositionY(), littleBoss.getWidth(), littleBoss.getHeight());
            map.getLittleBosses().add(littleBoss);
            mapView.addLittleBossView(littleBossView);
        }
    }

    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        //mapView.refreshLittleBoss(littleBossView, littleBoss.getLocation().getPositionX(), littleBoss.getLocation().getPositionY());
        if( littleBoss.getHitNumber() == 3) {
            map.getLittleBosses().remove(littleBoss);
            mapView.removeLittleBossView( littleBossView);
        }
        /*
        abilityTimer = abilityTimer - 0.016;
        if ( abilityTimer <= 0) {
            mapView.removeLaserView(laserView);
        }
         */
    }
}
