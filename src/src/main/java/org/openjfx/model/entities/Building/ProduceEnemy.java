package org.openjfx.model.entities.Building;

import org.openjfx.model.entities.Enemy.Enemy;

public interface ProduceEnemy {
    Enemy produceEnemy();

    int getProduceTimer();

    void setProduceTimer(int produceTimer);

    int getGetProducePeriod();

    void setGetProducePeriod(int getProducePeriod);

}
