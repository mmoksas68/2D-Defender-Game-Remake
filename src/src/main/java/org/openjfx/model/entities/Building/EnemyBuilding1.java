package org.openjfx.model.entities.Building;

import org.openjfx.model.Location;
import org.openjfx.model.entities.Enemy.Enemy;

public class EnemyBuilding1 extends Building implements ProduceEnemy {
    public static final int MAX_HEALTH = 50;
    private int tier;
    private int produceTimer = 0;
    private int getProducePeriod = 100;

    public EnemyBuilding1(Location location, int hitBoxWidth, int hitBoxHeight, int tier) {
        super(location, hitBoxWidth, hitBoxHeight, MAX_HEALTH);
        this.tier = tier;
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
}
