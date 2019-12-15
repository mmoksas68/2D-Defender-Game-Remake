package org.openjfx.utilization;
import org.openjfx.model.preBossEntities.Station.EnemyStation;
import org.openjfx.model.preBossEntities.Station.EvolvedEnemyStation;
import org.openjfx.model.preBossEntities.Station.Station;
import org.openjfx.model.preBossEntities.Station.StationTypes;

public class ModelToViewStation extends ModelToView{
    private StationTypes type;
    private int health;
    private int maxHealth;

    public ModelToViewStation(Station station) {
        super(station);

        this.health = station.getHealthPoint();
        if( station instanceof EnemyStation)
        {
            this.type = StationTypes.EnemyStation;
            this.maxHealth = EnemyStation.MAX_HEALTH;
        }else if( station instanceof  EvolvedEnemyStation)
        {
            this.type = StationTypes.EvolvedEnemyStation;
            this.maxHealth = EnemyStation.MAX_HEALTH;
        }

    }

    public StationTypes getType() {
        return type;
    }

    public void setType(StationTypes type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
