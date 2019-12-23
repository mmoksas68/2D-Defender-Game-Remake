package org.openjfx.model.commonEntities.FiringBehavior;

import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.preBossEntities.PreBossMap;

public class BossGun implements FiringBehavior {
    Boss boss;
    public BossGun (BossMap bossMap) {
        this.boss = bossMap.getBoss();
    }
    @Override
    public Bullet fireBullet() {
        if ( boss == null)
            System.out.println( "iiiiiiiiiiiiiiiiiiiiii");
        Bullet b = new Bullet( new Location(boss.getLocation().getPositionX(), boss.getLocation().getPositionY()), boss.getGunPower(),boss.getBulletVelocity(),-1.0,0.0 );
        if ( b == null)
            System.out.println( "sadasdasdasdasd");
        return b;
    }

    @Override
    public void gunTimer(PreBossMap preBossMap) {

    }

    @Override
    public void gunTimer(BossMap bossMap) {

    }
}
