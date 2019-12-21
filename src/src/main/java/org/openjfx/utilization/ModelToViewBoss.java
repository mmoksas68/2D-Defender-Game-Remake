package org.openjfx.utilization;

import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.Boss.BossOne;
import org.openjfx.model.bossEntities.Boss.BossThree;
import org.openjfx.model.bossEntities.Boss.BossTwo;

public class ModelToViewBoss {
    private double locationX;
    private double locationY;
    private double hitboxWidth;
    private double hitboxHeight;
    private long ID;
    private boolean isDead;
    private int bossType;
    public ModelToViewBoss (Boss boss) {
        locationX = boss.getLocation().getPositionX();
        locationY = boss.getLocation().getPositionY();
        hitboxHeight = boss.getHitBoxHeight();
        hitboxWidth = boss.getHitBoxWidth();
        ID = boss.getID();
        isDead = boss.isDead();
        findType( boss);
    }

    private void findType (Boss boss) {
        if( boss instanceof BossOne)
            bossType = 1;
        else if( boss instanceof BossTwo)
            bossType = 2;
        else if( boss instanceof BossThree)
            bossType = 3;
    }
    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public double getHitboxWidth() {
        return hitboxWidth;
    }

    public double getHitboxHeight() {
        return hitboxHeight;
    }

    public long getID() {
        return ID;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getBossType() {
        return bossType;
    }
}
