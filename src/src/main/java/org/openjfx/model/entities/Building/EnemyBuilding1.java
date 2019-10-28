package org.openjfx.model.entities.Building;

import org.openjfx.model.Location;
import org.openjfx.model.entities.Enemy.Enemy;
import org.openjfx.model.entities.Enemy.Tier1;

public class EnemyBuilding1 extends Building {
    private int tier;

    public EnemyBuilding1(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint, int tier) {
        super(location, hitBoxWidth, hitBoxHeight, healthPoint);
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    public void produceEnemy(){
    }
}
