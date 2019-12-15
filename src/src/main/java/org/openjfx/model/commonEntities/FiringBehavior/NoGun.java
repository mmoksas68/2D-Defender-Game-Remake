package org.openjfx.model.commonEntities.FiringBehavior;

import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.preBossEntities.PreBossMap;

public class NoGun implements FiringBehavior{

    @Override
    public Bullet fireBullet() {
        return null;
    }

    @Override
    public void gunTimer(PreBossMap preBossMap) {

    }

}
