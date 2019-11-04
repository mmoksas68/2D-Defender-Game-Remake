package org.openjfx.model.entities.Building;

import org.openjfx.model.Location;
import org.openjfx.model.entities.Enemy.Enemy;
import org.openjfx.model.entities.Enemy.Tier1;

public class EnemyBuilding2 extends Building {
    private int tier;

    public EnemyBuilding2(Location location, int hitBoxWidth, int hitBoxHeight, int tier) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void produceEnemy(int enemyTier){

    }

    public void hostEnemy(Enemy enemy){

    }

}
