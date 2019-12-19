package org.openjfx.model.preBossEntities;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.preBossEntities.Enemy.Tier1Enemy;
import org.openjfx.model.preBossEntities.Enemy.Tier2Enemy;
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
    }

    private void initMap() {
            spacecraft1 = new Spacecraft(new Location(4960, 390));
            spacecraft1.setChoosenPicNo(GameSituation.getInstance().getSpacecraft1());
        if(!isSinglePlayer) {
            spacecraft2 = new Spacecraft(new Location(4960, 520));
            spacecraft2.setChoosenPicNo(GameSituation.getInstance().getSpacecraft2());
        }
        double x, y;
        for (int i=0; i < 0 ; i++){
            x = Math.random()*PreBossMap.MAP_WIDTH;
            y = Math.random()*PreBossMap.MAP_HEIGHT;
            Tier1Enemy enemy = new Tier1Enemy(new Location(x,y), false);
            PositionHelper helper = new PositionHelper(enemy);
            enemy.setLocation(PositionHelper.createLocation(helper));
            addEnemy(enemy);
        }
        for (int i = 0; i < 10; i++){
            x = Math.random()*PreBossMap.MAP_WIDTH;
            y = Math.random()*PreBossMap.MAP_HEIGHT;
            Tier2Enemy enemy = new Tier2Enemy(new Location(x,y), false);
            PositionHelper helper = new PositionHelper(enemy);
            enemy.setLocation(PositionHelper.createLocation(helper));
            addEnemy(enemy);
        }
        for (int i=0; i < 5; i++){
            x = Math.random()*PreBossMap.MAP_WIDTH;
            y = Math.random()*PreBossMap.MAP_HEIGHT;
            EnemyStation enemyStation = new EnemyStation(new Location(x,y));
            PositionHelper helper = new PositionHelper(enemyStation);
            enemyStation.setLocation(PositionHelper.createLocation(helper));
            addStation(enemyStation);
        }
        for (int i=0; i < 2 ; i++){
            x = Math.random()*PreBossMap.MAP_WIDTH;
            y = Math.random()*PreBossMap.MAP_HEIGHT;
            Station evolvedEnemyStation = new EvolvedEnemyStation(new Location(x,y), GameSituation.getInstance().getLevel());
            PositionHelper helper = new PositionHelper(evolvedEnemyStation);
            evolvedEnemyStation.setLocation(PositionHelper.createLocation(helper));
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
}
