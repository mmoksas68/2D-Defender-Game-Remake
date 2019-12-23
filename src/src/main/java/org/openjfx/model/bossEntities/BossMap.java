package org.openjfx.model.bossEntities;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.openjfx.model.bossEntities.BossAbility.Rocket;
import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;
import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.Boss.BossOne;
import org.openjfx.model.bossEntities.Boss.BossThree;
import org.openjfx.model.bossEntities.Boss.BossTwo;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BossMap implements Serializable {
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
    private java.util.Map<Long, Buff> buffs = new HashMap<Long, Buff>();
    private boolean isFiring = false;
    private boolean isIndicator = false;

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

    }


    public Spacecraft getSpacecraft1() {
        return spacecraft1;
    }

    public Spacecraft getSpacecraft2() {
        return spacecraft2;
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

    public void addSpecialAbility ( SpecialAbility specialAbility) {
        specialAbilities.put( specialAbility.getID(), specialAbility);
    }
    public void removeSpecialAbility ( long ID) {
        specialAbilities.remove( ID);
    }

    public Map<Long, SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    public boolean isFiring() {
        return isFiring;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
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

    public boolean isIndicator() {
        return isIndicator;
    }

    public void addBuff(Buff buff) {
        buffs.put(buff.getID(), buff);
    }

    public void deleteBuff(long ID) {
        buffs.remove(ID);
    }

    public Map<Long, Buff> getBuffs() {
        return buffs;
    }
}

