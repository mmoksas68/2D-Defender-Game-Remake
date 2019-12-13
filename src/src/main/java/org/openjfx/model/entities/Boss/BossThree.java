package org.openjfx.model.entities.Boss;

import org.openjfx.model.Location;
import org.openjfx.model.entities.BossAbility.LittleBoss;

public class BossThree extends Boss {
    private final double LITTLE_BOSS_FREQ = 0.01;
    private static final int hitBoxWidth = 150;
    private static final int hitBoxHeight = 150;
    private static final int MAX_HEALTH_POINT = 1000;

    public BossThree () {
        super ( 3, hitBoxWidth, hitBoxHeight, MAX_HEALTH_POINT);
    }

    public double getLITTLE_BOSS_FREQ() {
        return LITTLE_BOSS_FREQ;
    }

    public LittleBoss sendLittleBoss() {
        return new LittleBoss( new Location( getLocation().getPositionX(),getLocation().getPositionY() + getHitBoxHeight()/2));
    }

    @Override
    public int getBulletDamage() {
        return 0;
    }

    @Override
    public void setBulletDamage(int bulletDamage) {

    }
}
