package org.openjfx.controller.preBossSceneControllers;

import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.FiringBehavior.EnemyGun;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.preBossEntities.Enemy.Enemy;
import org.openjfx.model.preBossEntities.Enemy.EnemyDestinations;
import org.openjfx.model.preBossEntities.Enemy.EnemyTypes;
import org.openjfx.model.preBossEntities.Enemy.Tier2Enemy;
import org.openjfx.model.preBossEntities.Meteor.Meteor;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.model.preBossEntities.Station.EnemyStation;
import org.openjfx.model.preBossEntities.Station.EvolvedEnemyStation;
import org.openjfx.model.preBossEntities.Station.Station;
import org.openjfx.utilization.PositionHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreBossMapController {
    private PreBossMap preBossMap;
    private List<Enemy> firingEnemies = new ArrayList<>();
    private List<Enemy> rushingEnemies = new ArrayList<>();
    private boolean isSinglePlayer;
    private GameSituation gameSituation;

    public PreBossMapController(boolean isSinglePlayer) {
        preBossMap = new PreBossMap(isSinglePlayer);
        gameSituation = GameSituation.getInstance();
        this.isSinglePlayer = isSinglePlayer;
    }

    public PreBossMapController(PreBossMap preBossMap) {
        this.preBossMap = preBossMap;
    }

    public void checkMapSituation() {
        for (var bullet : preBossMap.getBullets().values()) {
            checkCollision(bullet, preBossMap.getEnemies());
            checkCollision(bullet, preBossMap.getStations());
            checkCollision(bullet, Collections.singletonMap(preBossMap.getSpacecraft1().getID(), preBossMap.getSpacecraft1()));
            if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                checkCollision(bullet, Collections.singletonMap(preBossMap.getSpacecraft2().getID(), preBossMap.getSpacecraft2()));

            bullet.moveToDirection(bullet.getVelocity(), bullet.getDirectionX(), bullet.getDirectionY());
            bullet.setDistanceTravelled( bullet.getDistanceTravelled() + bullet.getVelocity());
            if(bullet.getDistanceTravelled() > Bullet.MAX_DISTANCE)
            {
                bullet.setDead(true);
            }
        }

        firingEnemies = new ArrayList<Enemy>();

        for (var enemy : preBossMap.getEnemies().values()) {
            enemy.setDestinationType(EnemyDestinations.RandomLocation);
            //isEnterEvolvedStation(enemy, preBossMap.getStations());
            checkCollision(enemy, Collections.singletonMap(preBossMap.getSpacecraft1().getID(), preBossMap.getSpacecraft1()));
            if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                checkCollision(enemy, Collections.singletonMap(preBossMap.getSpacecraft2().getID(), preBossMap.getSpacecraft2()));

            wonderAround(enemy);
            if(!(enemy instanceof Tier2Enemy))
                ((EnemyGun)enemy.getFiringBehavior()).setFiring(enemy.getDestinationType().equals(EnemyDestinations.Spacecraft));

            enemy.getFiringBehavior().gunTimer(preBossMap);
        }

        for (var enemyStation : preBossMap.getStations().values()){
            if(enemyStation instanceof EnemyStation)
                spawnSimpleEnemy((EnemyStation) enemyStation);
            if(enemyStation instanceof EvolvedEnemyStation)
                spawnEvolvedEnemy((EvolvedEnemyStation) enemyStation);
        }

    }


    private <T extends LocatableObject> void checkCollision(LocatableObject obj, java.util.Map<Long, T> list) {
        PositionHelper objHelper = new PositionHelper(obj);

        for (var iterator : list.values()) {
            PositionHelper iteratorHelper = new PositionHelper(iterator);

            if(obj instanceof Enemy && iterator instanceof Spacecraft && PositionHelper.isInRadar(objHelper, iteratorHelper, ((Enemy) obj).getRadarRadius())){
                ((Enemy) obj).setDestinationLocation(
                        new Location(
                                iteratorHelper.getMiddlePointX() - objHelper.getMiddlePointX(),
                                iteratorHelper.getMiddlePointY() - objHelper.getMiddlePointY()
                        ));

                ((Enemy) obj).setDestinationType(EnemyDestinations.Spacecraft);
            }

            if (PositionHelper.isThereACollision(objHelper, iteratorHelper)) {
                obj.setDead(true);

                if (obj instanceof Bullet)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((Bullet) obj).getDamage());
                else if (obj instanceof Enemy)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((EnemyGun)((Enemy) obj).getFiringBehavior()).getBulletDamage());
                else if (obj instanceof Meteor)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((Meteor) obj).getDamage());

                if (iterator.getHealthPoint() <= 0)
                    iterator.setDead(true);
            }
        }
    }

    private void wonderAround(Enemy enemy){
        if (enemy.getDestinationType().equals(EnemyDestinations.RandomLocation)) {
            double randomY;
            double randomX;
            enemy.setChangeDirectionTimer(enemy.getChangeDirectionTimer() % enemy.getChangeDirectionPeriod());
            PositionHelper enemyPosition = new PositionHelper(enemy);
            if(enemyPosition.isInside(PreBossMap.MAP_WIDTH, PreBossMap.MAP_HEIGHT)) {
                if (enemy.getChangeDirectionTimer() == 0) {
                    randomX = 5 * (Math.random() - 0.5);
                    randomY = 5 * (Math.random() - 0.5);
                    enemy.setDestinationLocation(new Location(randomX, randomY));
                }
                enemy.setChangeDirectionTimer(enemy.getChangeDirectionTimer() + 1);
                enemy.moveToDirection(enemy.getVelocity(), enemy.getDestinationLocation().getPositionX(), enemy.getDestinationLocation().getPositionY());

            }
            else {
                enemy.setChangeDirectionTimer(0);
                if (enemyPosition.getTop() < 0 | enemyPosition.getBottom() > PreBossMap.MAP_HEIGHT) {
                    enemy.setDestinationLocation(new Location(enemy.getDestinationLocation().getPositionX(), -enemy.getDestinationLocation().getPositionY()));
                    enemy.moveToDirection(enemy.getVelocity(), enemy.getDestinationLocation().getPositionX(), enemy.getDestinationLocation().getPositionY());
                }
                if (enemyPosition.getLeft() < 0 | enemyPosition.getRight() > PreBossMap.MAP_WIDTH){
                    enemy.setDestinationLocation(new Location(-enemy.getDestinationLocation().getPositionX(), enemy.getDestinationLocation().getPositionY()));
                    enemy.moveToDirection(enemy.getVelocity(), enemy.getDestinationLocation().getPositionX(), enemy.getDestinationLocation().getPositionY());
                }
                enemy.setChangeDirectionTimer(enemy.getChangeDirectionTimer() + 1);
            }
        }
    }

    private void spawnSimpleEnemy(EnemyStation enemyStation){
        enemyStation.setProduceTimer(enemyStation.getProduceTimer()+1);
        enemyStation.setProduceTimer(enemyStation.getProduceTimer() % enemyStation.getProducePeriod());
        Location location = new Location(enemyStation.getLocation().getPositionX(),enemyStation.getLocation().getPositionY());
        if(enemyStation.getProduceTimer() == 0){
            Enemy enemy = enemyStation.getEnemyFactory().produceEnemy(EnemyTypes.tier1unevolved, location);
            preBossMap.getEnemies().put(enemy.getID(), enemy);
        }
    }

    private void spawnEvolvedEnemy(EvolvedEnemyStation enemyStation){
        enemyStation.setProduceTimer(enemyStation.getProduceTimer()+1);
        enemyStation.setProduceTimer(enemyStation.getProduceTimer() % enemyStation.getProducePeriod());
        Location location = new Location(enemyStation.getLocation().getPositionX(),enemyStation.getLocation().getPositionY());
        if(enemyStation.getProduceTimer() == 0){
            Enemy enemy = enemyStation.getEnemyFactory().produceEnemy(EnemyTypes.tier1evolved, location);
            preBossMap.getEnemies().put(enemy.getID(), enemy);
        }
    }

    private  void isEnterEvolvedStation(LocatableObject obj, java.util.Map<Long, Station> list) {
        PositionHelper objHelper = new PositionHelper(obj);
        for(var station : list.values()){
            PositionHelper stationHelper = new PositionHelper(station);
            if(PositionHelper.isThereACollision(objHelper, stationHelper)){
                if(obj instanceof Enemy && station instanceof EvolvedEnemyStation){
                    ((Enemy) obj).setEvolved(true); //= ((EvolvedEnemyStation) station).getEnemyFactory().produceEnemy(EnemyTypes.tier1evolved, new Location(station.getLocation().getPositionX(), station.getLocation().getPositionY()));
                }
            }
        }
    }


    private void useSmartBomb(Spacecraft spacecraft){

    }

    private void rushIntoSpacecraft(){

    }

    public PreBossMap getPreBossMap() {
        return preBossMap;
    }

    public List<Enemy> getFiringEnemies() {
        return firingEnemies;
    }

    public List<Enemy> getRushingEnemies() {
        return rushingEnemies;
    }
}
