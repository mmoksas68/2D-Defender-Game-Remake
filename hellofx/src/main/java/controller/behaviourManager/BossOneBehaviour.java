package controller.behaviourManager;

import model.Boss;
import model.BossOne;
import model.Map;
import model.Laser;
import view.LaserView;
import view.MapView;

import java.io.FileNotFoundException;

public class BossOneBehaviour extends BossDefaultBehaviour implements BossBehaviourAlgorithm {
    LaserView laserView;
    public BossOneBehaviour(Map map, MapView mapView) {
        super( map, mapView);
    }
    @Override
    public void useSpecialAbility() throws FileNotFoundException {
        Boss boss = map.getBoss();
        if ( Math.random() < ((BossOne) boss).getLASER_FREQ()) {
            Laser laser = ((BossOne) boss).sendLaser();

         //   specialAbility.add( ((BossOne) boss).sendLaser());
            abilityTimer = 1.0;
            laserView = new LaserView(laser.getLocation().getPositionX(), laser.getLocation().getPositionY(), laser.getWidth(),laser.getHeight());
            mapView.addLaserView(laserView);
        }
    }

    @Override
    public void clockTick() {
        abilityTimer = abilityTimer - 0.016;
        if ( abilityTimer <= 0) {
            mapView.removeLaserView(laserView);
        }
    }
}
