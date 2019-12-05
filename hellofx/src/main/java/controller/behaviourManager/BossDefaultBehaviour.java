package controller.behaviourManager;

import model.Boss;
import model.Bullet;
import model.Map;
import view.BulletView;
import view.MapView;

import java.io.FileNotFoundException;

public abstract class BossDefaultBehaviour {
    Map map;
    MapView mapView;
    double abilityTimer = 0.0;
    private boolean movingDown = true;
    public BossDefaultBehaviour(Map map, MapView mapView) {
        this.map = map;
        this.mapView = mapView;
    }
    public void moveBoss() {
        double bossMoveDown = 0.0;
        double bossMoveUp = 0.0;
        Boss boss = map.getBoss();
        double MOVE_OFFSET = map.getMOVE_OFFSET();
        double MAX_HEIGHT = map.getMAX_HEIGHT();

        if ( movingDown) {
            bossMoveUp = ((boss.getLocation().getPositionY() - (2 * MOVE_OFFSET)) >= 0) ? -1 : 0;
            movingDown = !(boss.getLocation().getPositionY() <= MAX_HEIGHT / 5);
            boss.move(0.0, bossMoveUp);
        } else {
            bossMoveDown = ((boss.getLocation().getPositionY() + boss.getHeight() + (2 * MOVE_OFFSET)) <= MAX_HEIGHT) ? 1 : 0;
            movingDown = boss.getLocation().getPositionY() + boss.getHeight() / 2 >= MAX_HEIGHT - MAX_HEIGHT / 4;
            boss.move(0.0, bossMoveDown);
        }
        mapView.refreshBoss(map.getBoss().getLocation().getPositionX(), map.getBoss().getLocation().getPositionY());
    }
    public void shoot () throws FileNotFoundException {
        if ( Math.random() < map.getBoss().getGunFrequency()) {
            Bullet b = map.getBoss().sendBullet();
            map.getBullets().add( b);
            BulletView bw = new BulletView( map.getBoss().getLocation().getPositionX(),map.getBoss().getLocation().getPositionY(),b.getWidth(), b.getHeight());
            mapView.addBulletView( bw);
        }
    }

    public double getAbilityTimer() {
        return abilityTimer;
    }
}
