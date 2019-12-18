package org.openjfx.model.preBossEntities.Station;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.preBossEntities.Enemy.*;


public class EnemyFactory {

    public Enemy produceEnemy(EnemyTypes enemy, Location location){
        Enemy myEnemy = null;
        if(enemy == EnemyTypes.tier1unevolved){
            myEnemy = new Tier1Enemy(location, false);
        }
        else if(enemy == EnemyTypes.tier1evolved){
            myEnemy = new Tier1Enemy(location, true);
        }
        else if(enemy == EnemyTypes.tier2unevolved){
            myEnemy = new Tier2Enemy(location, false);
        }
        else if(enemy == EnemyTypes.tier2evolved){
            myEnemy = new Tier2Enemy(location, true);
        }
        else if(enemy == EnemyTypes.tier3unevolved){
            myEnemy = new Tier3Enemy(location, false);
        }
        else if(enemy == EnemyTypes.tier3evolved){
            myEnemy = new Tier3Enemy(location, true);
        }

        return myEnemy;
    }

    public Enemy randomProduction(boolean isEvolved, Location location){
        Enemy enemy = null;
        if(isEvolved){

        }
        else{

        }
        return enemy;
    }
}
