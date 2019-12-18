package org.openjfx.model.preBossEntities.Station;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.preBossEntities.Enemy.Enemy;

import java.util.HashMap;
import java.util.Map;

public class EvolvedEnemyStation extends Station{
    public static final int MAX_HEALTH = 50;
    public static final int SCORE_POINT = 5;
    public static final int LEVEL1_PRODUCE_PERIOD = 750;
    public static final int LEVEL2_PRODUCE_PERIOD = 500;
    public static final int LEVEL3_PRODUCE_PERIOD = 250;
    public static final int LEVEL1_HOSTING_DURATION = 100;
    public static final int LEVEL2_HOSTING_DURATION = 75;
    public static final int LEVEL3_HOSTING_DURATION = 50;
    public static final int PIC_NO = 1;

    private EnemyFactory enemyFactory;
    private int produceTimer = 0;
    private int producePeriod;
    private int hostingDuration;
    private int level;
    private Map<Long, Enemy> enemiesInside = new HashMap<>();
    private Map<Long, Integer> elapsedTimes = new HashMap<>();

    public EvolvedEnemyStation(Location location, int level) {
        super(location, MAX_HEALTH, PIC_NO);
        enemyFactory = new EnemyFactory();
        producePeriod = level == 1 ? LEVEL1_PRODUCE_PERIOD : (level == 2 ? LEVEL2_PRODUCE_PERIOD : (level == 3 ? LEVEL3_PRODUCE_PERIOD : 0));
        hostingDuration =  level == 1 ? LEVEL1_HOSTING_DURATION : (level == 2 ? LEVEL2_HOSTING_DURATION : (level == 3 ? LEVEL3_HOSTING_DURATION : 0));
    }

    public EnemyFactory getEnemyFactory() {
        return enemyFactory;
    }

    public void setEnemyFactory(EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
    }

    public int getProduceTimer() {
        return produceTimer;
    }

    public void setProduceTimer(int produceTimer) {
        this.produceTimer = produceTimer;
    }

    public int getProducePeriod() {
        return producePeriod;
    }

    public void setProducePeriod(int producePeriod) {
        this.producePeriod = producePeriod;
    }

    public int getHostingDuration() {
        return hostingDuration;
    }

    public void setHostingDuration(int hostingDuration) {
        this.hostingDuration = hostingDuration;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<Long, Enemy> getEnemiesInside() {
        return enemiesInside;
    }

    public void setEnemiesInside(Map<Long, Enemy> enemiesInside) {
        this.enemiesInside = enemiesInside;
    }

    public Map<Long, Integer> getElapsedTimes() {
        return elapsedTimes;
    }

    public void setElapsedTimes(Map<Long, Integer> elapsedTimes) {
        this.elapsedTimes = elapsedTimes;
    }
}
