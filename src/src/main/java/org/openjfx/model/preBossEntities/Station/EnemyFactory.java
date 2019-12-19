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

    public Enemy randomProduction(int level, boolean isEvolved, Location location){
        Enemy enemy = null;
        double random = Math.random()*6;
        switch (level){
            case 1:
                if(isEvolved){
                    enemy = produceEnemy(EnemyTypes.tier1evolved, location);
                }
                else {
                    enemy = produceEnemy(EnemyTypes.tier1unevolved, location);
                }
                break;
            case 2:
                if(isEvolved){
                    if(random > 3)
                        enemy = produceEnemy(EnemyTypes.tier1evolved, location);
                    else
                        enemy = produceEnemy(EnemyTypes.tier2evolved, location);
                }
                else {
                    if(random < 3)
                        enemy = produceEnemy(EnemyTypes.tier1unevolved, location);
                    else
                        enemy = produceEnemy(EnemyTypes.tier2unevolved, location);
                }
                break;
            case 3:
                if(isEvolved){
                    if(random < 2)
                        enemy = produceEnemy(EnemyTypes.tier1evolved, location);
                    else if(random > 4)
                        enemy = produceEnemy(EnemyTypes.tier2evolved, location);
                    else
                        enemy = produceEnemy(EnemyTypes.tier3evolved, location);
                }
                else{
                    if(random < 2)
                        enemy = produceEnemy(EnemyTypes.tier1unevolved, location);
                    else if(random > 4)
                        enemy = produceEnemy(EnemyTypes.tier2unevolved, location);
                    else
                        enemy = produceEnemy(EnemyTypes.tier3unevolved, location);
                }
                break;
        }
        return enemy;
    }
}
