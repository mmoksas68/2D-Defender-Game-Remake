package org.openjfx.model.bossEntities.Boss;

import org.openjfx.controller.bossSceneControllers.BossBehaviourManager.BossThreeBehaviour;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.bossEntities.BossAbility.LittleBoss;

public class BossThree extends Boss {
    private final double LITTLE_BOSS_FREQ = 0.01;
    private static final int hitBoxWidth = 150;
    private static final int hitBoxHeight = 150;
    private static final int MAX_HEALTH_POINT = 1000;
    private static int gunPower = 10;
    private static double bulletVelocity = 10.0;
    private static double gunFrequency = 0.05;

    public BossThree (BossMap bossMap) {
        super ( 3, hitBoxWidth, hitBoxHeight, MAX_HEALTH_POINT);
        setGunFrequency( gunFrequency);
        setGunPower( gunPower);
        setBulletVelocity( bulletVelocity);
        setBehaviourAlgorithm( new BossThreeBehaviour( bossMap));
    }

    public double getLITTLE_BOSS_FREQ() {
        return LITTLE_BOSS_FREQ;
    }

    public LittleBoss sendLittleBoss() {
        return new LittleBoss( new Location( getLocation().getPositionX(),getLocation().getPositionY() + getHitBoxHeight()/2));
    }

    public static int getMaxHealthPoint() {
        return MAX_HEALTH_POINT;
    }

   /* @Override
    public int getBulletDamage() {
        return 0;
    }

    @Override
    public void setBulletDamage(int bulletDamage) {

    }*/
}
