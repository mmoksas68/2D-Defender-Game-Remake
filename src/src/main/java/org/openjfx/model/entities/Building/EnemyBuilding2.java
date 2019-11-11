package org.openjfx.model.entities.Building;

import org.openjfx.model.Location;
import org.openjfx.model.entities.Enemy.Enemy;
import org.openjfx.model.entities.Enemy.Tier1;

import java.util.HashMap;
import java.util.Map;

public class EnemyBuilding2 extends Building implements ProduceEnemy {
    public static final int MAX_HEALTH = 50;
    private int tier;
    private int produceTimer = 0;
    private int getProducePeriod = 100;
    private Map<Long, Enemy> enemiesInside = new HashMap<>();
    private Map<Long, Integer> elapsedTimes = new HashMap<>();

    public EnemyBuilding2(Location location, int hitBoxWidth, int hitBoxHeight, int tier) {
        super(location, hitBoxWidth, hitBoxHeight, MAX_HEALTH);
        this.tier = tier;
    }

    public void hostEnemy(Enemy enemy){
        enemiesInside.put(enemy.getID(), enemy);
    }

    public void releaseEnemy(Long ID){
        enemiesInside.remove(ID);
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public Enemy produceEnemy() {
        return null;
    }

    @Override
    public int getProduceTimer() {
        return produceTimer;
    }

    @Override
    public void setProduceTimer(int produceTimer) {
        this.produceTimer = produceTimer;
    }

    @Override
    public int getGetProducePeriod() {
        return getProducePeriod;
    }

    @Override
    public void setGetProducePeriod(int getProducePeriod) {
        this.getProducePeriod = getProducePeriod;
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
