package org.openjfx.model.preBossEntities;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.preBossEntities.Enemy.Tier1Enemy;
import org.openjfx.model.preBossEntities.Enemy.Tier2Enemy;
import org.openjfx.model.preBossEntities.Enemy.Tier3Enemy;
import org.openjfx.model.preBossEntities.Station.EnemyStation;
import org.openjfx.model.preBossEntities.Station.EvolvedEnemyStation;
import org.openjfx.model.preBossEntities.Station.Station;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.preBossEntities.Enemy.Enemy;
import org.openjfx.model.preBossEntities.Meteor.Meteor;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.utilization.PositionHelper;

import java.io.Serializable;
import java.util.*;

public class PreBossMap implements Serializable {
    private static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    public static double MAP_HEIGHT = primaryScreenBounds.getHeight();
    public static double MAP_WIDTH = 10000;
    private int level;

    private java.util.Map<Long, Enemy> enemies = new HashMap<Long, Enemy>();
    private java.util.Map<Long, Station> stations = new HashMap<Long, Station>();
    private java.util.Map<Long, Bullet> bullets = new HashMap<Long, Bullet>();
    private java.util.Map<Long, Buff> buffs = new HashMap<Long, Buff>();
    private java.util.Map<Long, Meteor> meteors = new HashMap<Long, Meteor>();

    private int initialTier1unevolvedEnemyCount = 0;
    private int initialTier2unevolvedEnemyCount = 0;
    private int initialTier3unevolvedEnemyCount = 0;

    private int initialTier1evolvedEnemyCount = 0;
    private int initialTier2evolvedEnemyCount = 0;
    private int initialTier3evolvedEnemyCount = 0;

    private int initialUnevolvedEnemyStationCount = 0;
    private int initialEvolvedEnemyStationCount = 0;

    private boolean isSinglePlayer;
    private Spacecraft spacecraft1;
    private Spacecraft spacecraft2;

    public PreBossMap(boolean isSinglePlayer){
        this.isSinglePlayer = isSinglePlayer;
        initMap();
    }

    public PreBossMap(int level, boolean isSinglePlayer) {
        this.level = level;
        this.isSinglePlayer = isSinglePlayer;
        switch (level) {
            case 1:
                initialTier1unevolvedEnemyCount = 25;
                initialTier1evolvedEnemyCount = 5;
                initialUnevolvedEnemyStationCount = 3;
                initialEvolvedEnemyStationCount = 2;
                break;
            case 2:
                initialTier1unevolvedEnemyCount = 1;
                initialTier1evolvedEnemyCount = 0;
                initialTier2unevolvedEnemyCount = 0;
                initialTier2evolvedEnemyCount = 0;
                initialUnevolvedEnemyStationCount = 0;
                initialEvolvedEnemyStationCount = 0;
                break;
            case 3:
                initialTier1unevolvedEnemyCount = 5;
                initialTier1evolvedEnemyCount = 5;
                initialTier2unevolvedEnemyCount = 10;
                initialTier2evolvedEnemyCount = 5;
                initialTier3unevolvedEnemyCount = 10;
                initialTier3evolvedEnemyCount = 5;
                initialUnevolvedEnemyStationCount = 5;
                initialEvolvedEnemyStationCount = 4;
                break;
        }
        initMap();
    }

    private void initMap() {
        spacecraft1 = new Spacecraft(new Location(4960, MAP_HEIGHT/2 -65));
        spacecraft1.setChoosenPicNo(GameSituation.getInstance().getSpacecraft1()); //constructora koyabiliriz.
        if(!isSinglePlayer) {
            spacecraft2 = new Spacecraft(new Location(4960, MAP_HEIGHT/2 + 65));
            spacecraft2.setChoosenPicNo(GameSituation.getInstance().getSpacecraft2()); //aynı şekilde constructora koyabiliriz
        }
        for (int i=0; i < initialTier1unevolvedEnemyCount ; i++){
            Tier1Enemy enemy = new Tier1Enemy(PositionHelper.createLocationInsideMap(), false);
            PositionHelper helper = new PositionHelper(enemy);
            enemy.setLocation(PositionHelper.fixLocationBoundry(helper));
            addEnemy(enemy);
        }
        for (int i = 0; i < initialTier2unevolvedEnemyCount; i++){
            Tier2Enemy enemy = new Tier2Enemy(PositionHelper.createLocationInsideMap(), false);
            PositionHelper helper = new PositionHelper(enemy);
            enemy.setLocation(PositionHelper.fixLocationBoundry(helper));
            addEnemy(enemy);
        }
        for (int i = 0; i < initialTier3unevolvedEnemyCount; i++){
            Tier3Enemy enemy = new Tier3Enemy(PositionHelper.createLocationInsideMap(), false);
            PositionHelper helper = new PositionHelper(enemy);
            enemy.setLocation(PositionHelper.fixLocationBoundry(helper));
            addEnemy(enemy);
        }
        for (int i=0; i < initialTier1evolvedEnemyCount ; i++){
            Tier1Enemy enemy = new Tier1Enemy(PositionHelper.createLocationInsideMap(), true);
            PositionHelper helper = new PositionHelper(enemy);
            enemy.setLocation(PositionHelper.fixLocationBoundry(helper));
            addEnemy(enemy);
        }
        for (int i = 0; i < initialTier2evolvedEnemyCount; i++){
            Tier2Enemy enemy = new Tier2Enemy(PositionHelper.createLocationInsideMap(), true);
            PositionHelper helper = new PositionHelper(enemy);
            enemy.setLocation(PositionHelper.fixLocationBoundry(helper));
            addEnemy(enemy);
        }
        for (int i = 0; i < initialTier3evolvedEnemyCount; i++){
            Tier3Enemy enemy = new Tier3Enemy(PositionHelper.createLocationInsideMap(), true);
            PositionHelper helper = new PositionHelper(enemy);
            enemy.setLocation(PositionHelper.fixLocationBoundry(helper));
            addEnemy(enemy);
        }
        for (int i=0; i < initialUnevolvedEnemyStationCount; i++){
            EnemyStation enemyStation = new EnemyStation(PositionHelper.createLocationInsideMap());
            PositionHelper helper = new PositionHelper(enemyStation);
            enemyStation.setLocation(PositionHelper.fixLocationBoundry(helper));
            addStation(enemyStation);
        }
        for (int i=0; i < initialEvolvedEnemyStationCount ; i++){
            Station evolvedEnemyStation = new EvolvedEnemyStation(PositionHelper.createLocationInsideMap(), GameSituation.getInstance().getLevel());
            PositionHelper helper = new PositionHelper(evolvedEnemyStation);
            evolvedEnemyStation.setLocation(PositionHelper.fixLocationBoundry(helper));
            addStation(evolvedEnemyStation);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.put(enemy.getID(), enemy);
    }

    public void deleteEnemy(long ID) {
        enemies.remove(ID);
    }

    public void deleteSpacecraft1(){
        spacecraft1 = null;
    }

    public void addStation(Station station) {
        stations.put(station.getID(), station);
    }

    public void deleteStation(long ID) {
        stations.remove(ID);
    }

    public void addBullet(Bullet bullet) {
        if(bullet != null)
            bullets.put(bullet.getID(), bullet);
    }

    public void deleteBullet(long ID) {
        bullets.remove(ID);
    }

    public void addBuff(Buff buff) {
        buffs.put(buff.getID(), buff);
    }

    public void deleteBuff(long ID) {
        buffs.remove(ID);
    }

    public void addMeteor(Meteor meteor) {
        meteors.put(meteor.getID(), meteor);
    }

    public void deleteMeteor(long ID) {
        meteors.remove(ID);
    }


    public int getLevel() {
        return level;
    }

    public Map<Long, Enemy> getEnemies() {
        return enemies;
    }

    public Map<Long, Station> getStations() {
        return stations;
    }

    public Map<Long, Bullet> getBullets() {
        return bullets;
    }

    public Map<Long, Buff> getBuffs() {
        return buffs;
    }

    public Map<Long, Meteor> getMeteors() {
        return meteors;
    }

    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }

    public Spacecraft getSpacecraft1() {
        return spacecraft1;
    }

    public Spacecraft getSpacecraft2() {
        return spacecraft2;
    }

    public void setSpacecraft1(Spacecraft spacecraft1) {
        this.spacecraft1 = spacecraft1;
    }

    public void setSpacecraft2(Spacecraft spacecraft2) {
        this.spacecraft2 = spacecraft2;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        isSinglePlayer = singlePlayer;
    }

    public int getInitialTier1unevolvedEnemyCount() {
        return initialTier1unevolvedEnemyCount;
    }

    public int getInitialTier2unevolvedEnemyCount() {
        return initialTier2unevolvedEnemyCount;
    }

    public int getInitialTier1evolvedEnemyCount() {
        return initialTier1evolvedEnemyCount;
    }

    public int getInitialTier2evolvedEnemyCount() {
        return initialTier2evolvedEnemyCount;
    }

    public int getInitialUnevoledEnemyStationCount() {
        return initialUnevolvedEnemyStationCount;
    }

    public int getInitialEvolvedEnemyStationCount() {
        return initialEvolvedEnemyStationCount;
    }
}
