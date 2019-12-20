package org.openjfx.model.bossEntities;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.Boss.BossOne;
import org.openjfx.model.bossEntities.Boss.BossThree;
import org.openjfx.model.bossEntities.Boss.BossTwo;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BossMap {
    private static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    public static double MAP_HEIGHT = primaryScreenBounds.getHeight();
    public static double MAP_WIDTH = primaryScreenBounds.getWidth();

    private int level;

    private boolean isSinglePlayer;
    private Boss boss;
    private Spacecraft spacecraft1;
    private Spacecraft spacecraft2;
    private java.util.Map<Long, Bullet> bullets = new HashMap<Long, Bullet>();
    private java.util.Map <Long, SpecialAbility> specialAbilities = new HashMap<>();


    public BossMap(int level, boolean isSinglePlayer) {
        this.level = level;
        this.isSinglePlayer = isSinglePlayer;
        initMap();
    }
    private void initMap() {
        spacecraft1 = new Spacecraft( new Location( 0 ,200));
        spacecraft1.setChoosenPicNo( 0);
        if ( !isSinglePlayer) {
            spacecraft2 = new Spacecraft(new Location(0, 600));
            spacecraft2.setChoosenPicNo( 1);
        }
        switch ( level) {
            case 1:
                boss = new BossOne();
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


    public Spacecraft getSpacecraft1() {
        return spacecraft1;
    }

    public Spacecraft getSpacecraft2() {
        return spacecraft2;
    }

    /* public void checkBoundry ( double [] array, LocatableObject gameObject) {
             double MOVE_OFFSET = 10.0;

            if ( gameObject instanceof Spacecraft) {
                if (spacecraft.getLocation().getPositionX() >= MAX_WIDTH / 2 - 5 * MOVE_OFFSET) array[0] = 0;
                if (spacecraft.getLocation().getPositionX() <= 3100 +2 * MOVE_OFFSET) array[1] = 0;
                if (spacecraft.getLocation().getPositionY() <= 2 * MOVE_OFFSET) array[2] = 0;
                if (spacecraft.getLocation().getPositionY() >= MAX_HEIGHT - spacecraft.getHitBoxHeight() - 2 * MOVE_OFFSET)
                    array[3] = 0;
            }

        }*/
    public java.util.Map<Long, Bullet> getBullets() {
        return bullets;
    }
    public void deleteBullet(long ID) {
        bullets.remove(ID);
    }
    public void addBullet(Bullet bullet) {
        bullets.put(bullet.getID(), bullet);
    }
    public void addSpecialAbility ( SpecialAbility specialAbility) {
        specialAbilities.put( specialAbility.getID(), specialAbility);
    }
    public void removeSpecialAbility ( long ID) {
        specialAbilities.remove( ID);
    }

    public Map<Long, SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    public Boss getBoss() {
        return boss;
    }

    public int getLevel() {
        return level;
    }

    public void setSpacecraft1(Spacecraft spacecraft1) {
        this.spacecraft1 = spacecraft1;
    }

    public void setSpacecraft2(Spacecraft spacecraft2) {
        this.spacecraft2 = spacecraft2;
    }

    public boolean isSinglePlayer() { return isSinglePlayer; }

}

