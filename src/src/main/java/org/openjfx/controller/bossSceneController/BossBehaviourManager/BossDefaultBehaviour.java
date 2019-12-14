package org.openjfx.controller.bossSceneController.BossBehaviourManager;

import org.openjfx.model.Boss.BossMap;
import org.openjfx.model.entities.Boss.Boss;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.view.BossMapView;

public abstract class BossDefaultBehaviour {
    BossMap bossMap;
    BossMapView bossMapView;
    double abilityTimer = 0.0;
    private boolean movingDown = true;

    public BossDefaultBehaviour(BossMap bossMap, BossMapView bossMapView) {
        this.bossMap = bossMap;
        this.bossMapView = bossMapView;
    }

    public void moveBoss() {
        double bossMoveDown = 0.0;
        double bossMoveUp = 0.0;
        Boss boss = bossMap.getBoss();
        double MOVE_OFFSET = 10; //bossMap.getMOVE_OFFSET();
        double MAX_HEIGHT = 1300; //bossMap.getMAX_HEIGHT();

        if ( movingDown) {
            bossMoveUp = ((boss.getLocation().getPositionY() - (2 * MOVE_OFFSET)) >= 0) ? -1 : 0;
            if ( boss.getLocation().getPositionY() > 600)
                movingDown = false;
           // movingDown = !(boss.getLocation().getPositionY() <= MAX_HEIGHT / 5);
            boss.moveToDirection(boss.getVelocity(),0.0, bossMoveUp);
        } else {
            bossMoveDown = ((boss.getLocation().getPositionY() + boss.getHitBoxHeight() + (2 * MOVE_OFFSET)) <= MAX_HEIGHT) ? 1 : 0;
           // movingDown = boss.getLocation().getPositionY() + boss.getHitBoxHeight() / 2 >= MAX_HEIGHT - MAX_HEIGHT / 4;
            if ( boss.getLocation().getPositionY() < 100)
                movingDown= true;
            boss.moveToDirection(boss.getVelocity(),0.0, bossMoveDown);
        }
      //  bossMapView.refreshBossView(bossMap.getBoss().getLocation().getPositionX(), bossMap.getBoss().getLocation().getPositionY());
    }
    public void shoot ()  {
        if ( Math.random() < -10) {
            Bullet b = bossMap.getBoss().fireBullet();
            bossMap.addBullet( b);
           /* BulletView bw = new BulletView();
            bossMapView.addBulletView( bw);*/
        }
    }

    public double getAbilityTimer() {
        return abilityTimer;
    }
}
