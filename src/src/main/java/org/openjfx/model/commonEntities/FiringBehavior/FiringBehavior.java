package org.openjfx.model.commonEntities.FiringBehavior;

import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.preBossEntities.PreBossMap;

import java.io.Serializable;


public interface FiringBehavior extends Serializable {

    public Bullet fireBullet();
    public void gunTimer(PreBossMap preBossMap);
    public void gunTimer(BossMap bossMap);
}
