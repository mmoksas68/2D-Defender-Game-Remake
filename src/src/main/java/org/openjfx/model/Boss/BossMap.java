package org.openjfx.model.Boss;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;
import org.openjfx.model.entities.Boss.Boss;
import org.openjfx.model.entities.Boss.BossOne;
import org.openjfx.model.entities.Boss.BossThree;
import org.openjfx.model.entities.Boss.BossTwo;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.model.entities.Enemy.Tier1;
import org.openjfx.model.entities.Spacecraft.Spacecraft;
import org.openjfx.model.preBoss.PreBossMap;

import java.util.Collections;
import java.util.HashMap;

public class BossMap {
    public static double MAX_HEIGHT;
    public static double MAX_WIDTH;
    private static double hitboxWidthScale;
    private static double hitboxHeightScale;
    private double viewLeft;
    private double viewRight;
    private Boss boss;
    private int level;

    private Spacecraft spacecraft;
    private Spacecraft spacecraft2;
    private java.util.Map<Long, Bullet> bullets = new HashMap<Long, Bullet>();

    public BossMap(int level,  double hitboxWidthScale, double hitboxHeightScale) {
        BossMap.hitboxWidthScale = hitboxWidthScale;
        BossMap.hitboxHeightScale = hitboxHeightScale;
        MAX_WIDTH = 5 * hitboxWidthScale;
        MAX_HEIGHT = hitboxHeightScale;
        viewLeft = 2 * hitboxWidthScale;
        viewRight = 3 * hitboxWidthScale;
        this.level = level;
        spacecraft = new Spacecraft(new Location(MAX_WIDTH / 2, MAX_HEIGHT / 2), (hitboxHeightScale / 1080 * Spacecraft.WIDTH_SCALE), (hitboxHeightScale / 1080 * Spacecraft.HEIGHT_SCALE), 30, 1, 1, 1, 7, 1, 1, true, true, true);
        initMap();
    }
    private void initMap() {
        switch ( level) {
            case 1:
                boss = new BossOne();
                System.out.println( "Boss Created");
                break;
            case 2:
                boss = new BossTwo();
                break;
            case 3:
                boss = new BossThree();
                break;
            default:
                break;
        }
    }

    public double getViewLeft() {
        return viewLeft;
    }

    public double getViewRight() {
        return viewRight;
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }
    public void refreshMap() {
        refreshLocatableObject(Collections.singletonMap(spacecraft.getID(), spacecraft), Spacecraft.WIDTH_SCALE, Spacecraft.HEIGHT_SCALE);
      //  refreshLocatableObject(getEnemies(), Tier1.WIDTH_SCALE, Tier1.HEIGHT_SCALE);
        refreshLocatableObject(getBullets(), Bullet.WIDTH_SCALE, Bullet.HEIGHT_SCALE);
        viewLeft = 5*hitboxWidthScale*viewLeft / MAX_WIDTH;
        MAX_WIDTH = 5*hitboxWidthScale;
        MAX_HEIGHT = hitboxHeightScale;
    }

    public <T extends LocatableObject> void refreshLocatableObject(java.util.Map<Long, T> list, double widthScaleSpecific, double heightScaleSpecific ) {
        for(var iterator : list.values()){
            iterator.getLocation().setPositionX(5*hitboxWidthScale*iterator.getLocation().getPositionX() / MAX_WIDTH);
            iterator.getLocation().setPositionY(hitboxHeightScale*iterator.getLocation().getPositionY() / MAX_HEIGHT);
            iterator.setHitBoxWidth(hitboxHeightScale/1080*widthScaleSpecific);
            iterator.setHitBoxHeight(hitboxHeightScale/1080*heightScaleSpecific);
        }
    }
    public void setViewLeft(double viewLeft) {
        this.viewLeft = viewLeft;
    }

    public void setViewRight(double viewRight) {
        this.viewRight = viewRight;
    }

    public static double getHitboxWidthScale() {
        return hitboxWidthScale;
    }
    public static void setHitboxWidthScale(double hitboxWidthScale) {
        BossMap.hitboxWidthScale = hitboxWidthScale;
    }
    public static void setHitboxHeightScale(double hitboxHeightScale) {
        BossMap.hitboxHeightScale = hitboxHeightScale;
    }
    public void checkBoundry ( double [] array, LocatableObject gameObject) {
         double MOVE_OFFSET = 10.0;

        if ( gameObject instanceof Spacecraft) {
            if (spacecraft.getLocation().getPositionX() >= MAX_WIDTH / 2 - 5 * MOVE_OFFSET) array[0] = 0;
            if (spacecraft.getLocation().getPositionX() <= 3100 +2 * MOVE_OFFSET) array[1] = 0;
            if (spacecraft.getLocation().getPositionY() <= 2 * MOVE_OFFSET) array[2] = 0;
            if (spacecraft.getLocation().getPositionY() >= MAX_HEIGHT - spacecraft.getHitBoxHeight() - 2 * MOVE_OFFSET)
                array[3] = 0;
        }

    }
    public java.util.Map<Long, Bullet> getBullets() {
        return bullets;
    }
    public void deleteBullet(long ID) {
        bullets.remove(ID);
    }
    public void addBullet(Bullet bullet) {
        bullets.put(bullet.getID(), bullet);
    }
    public static double getHitboxHeightScale() {
        return hitboxHeightScale;
    }
    public Boss getBoss() {
        return boss;
    }
}

