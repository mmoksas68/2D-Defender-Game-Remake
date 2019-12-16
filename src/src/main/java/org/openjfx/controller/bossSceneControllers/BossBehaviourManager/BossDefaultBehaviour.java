package org.openjfx.controller.bossSceneControllers.BossBehaviourManager;

import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;

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
        double MOVE_OFFSET = 50; //bossMap.getMOVE_OFFSET();
        double MAX_HEIGHT = BossMap.MAP_HEIGHT; //bossMap.getMAX_HEIGHT();
        if ( movingDown) {
           if ( boss.getLocation().getPositionY() + boss.getHitBoxWidth()+ MOVE_OFFSET > MAX_HEIGHT)
               movingDown = false;
           else
               boss.moveToDirection( boss.getVelocity(), 0.0, -1.0);
        } else {
            if ( boss.getLocation().getPositionY() - MOVE_OFFSET < 0 )
                movingDown = true;
            else
                boss.moveToDirection( boss.getVelocity(), 0.0, 1.0);
        }

    }
    public void shoot ()  {

        if ( Math.random() < bossMap.getBoss().getGunFrequency()) {
            Bullet b = bossMap.getBoss().fireBullet();
            bossMap.addBullet( b);
        }

    }

    public double getAbilityTimer() {
        return abilityTimer;
    }
}
