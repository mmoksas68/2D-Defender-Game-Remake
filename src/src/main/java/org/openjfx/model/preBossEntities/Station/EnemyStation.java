package org.openjfx.model.preBossEntities.Station;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.menuEntities.GameSituation;

public class EnemyStation extends Station{
    public static final int MAX_HEALTH = 75;
    public static final int SCORE_POINT = 50;
    public static final int LEVEL1_PRODUCE_PERIOD = 700;
    public static final int LEVEL2_PRODUCE_PERIOD = 600;
    public static final int LEVEL3_PRODUCE_PERIOD = 450;
    public static final int PIC_NO = 0;

    private EnemyFactory enemyFactory;
    private int produceTimer = 0;
    private int producePeriod;
    private int level;
    private GameSituation gameSituation;

    public EnemyStation(Location location) {
        super(location, MAX_HEALTH, PIC_NO);
        gameSituation = GameSituation.getInstance();
        level = gameSituation.getLevel();
        enemyFactory = new EnemyFactory();
        producePeriod = level == 1 ? LEVEL1_PRODUCE_PERIOD : (level == 2 ? LEVEL2_PRODUCE_PERIOD : (level == 3 ? LEVEL3_PRODUCE_PERIOD : 0));
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
}
