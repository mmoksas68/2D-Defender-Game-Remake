package org.openjfx.controller.preBossSceneControllers;

import org.openjfx.controller.SoundController;
import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.FiringBehavior.BulletTypes;
import org.openjfx.model.commonEntities.FiringBehavior.EnemyGun;
import org.openjfx.model.commonEntities.FiringBehavior.SpacecraftGun;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.menuEntities.GameSaveObj;
import org.openjfx.model.menuEntities.GameSituation;
import org.openjfx.model.preBossEntities.Enemy.*;
import org.openjfx.model.preBossEntities.Meteor.Meteor;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.model.preBossEntities.Station.EnemyStation;
import org.openjfx.model.preBossEntities.Station.EvolvedEnemyStation;
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
    private int meteorTimer = 1;
    public static final int METEOR_PERIOD = 50;

    public PreBossMapController(boolean isSinglePlayer) {
        gameSituation = GameSituation.getInstance();
        preBossMap = new PreBossMap(gameSituation.getLevel() , isSinglePlayer);
        this.isSinglePlayer = isSinglePlayer;
    }

    public PreBossMapController(PreBossMap preBossMap) {
        gameSituation = GameSituation.getInstance();
        this.preBossMap = preBossMap;
        this.isSinglePlayer = gameSituation.isSinglePlayer();
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

        meteorRain();

        for(var meteor: preBossMap.getMeteors().values()){
            checkCollision(meteor, preBossMap.getEnemies());
            checkCollision(meteor, preBossMap.getStations());
            checkCollision(meteor, Collections.singletonMap(preBossMap.getSpacecraft1().getID(), preBossMap.getSpacecraft1()));
            if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                checkCollision(meteor, Collections.singletonMap(preBossMap.getSpacecraft2().getID(), preBossMap.getSpacecraft2()));
            meteor.moveToDirection(meteor.getVelocity(), meteor.getDirectionX(), meteor.getDirectionY());
            PositionHelper helper = new PositionHelper(meteor);
            if(!helper.isInside(PreBossMap.MAP_WIDTH, PreBossMap.MAP_HEIGHT)){
                meteor.setDead(true);
            }

        }

        for(var buff: preBossMap.getBuffs().values()){
            checkCollision(buff, Collections.singletonMap(preBossMap.getSpacecraft1().getID(), preBossMap.getSpacecraft1()));
            if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                checkCollision(buff, Collections.singletonMap(preBossMap.getSpacecraft2().getID(), preBossMap.getSpacecraft2()));
        }

        firingEnemies = new ArrayList<Enemy>();

        for (var enemy : preBossMap.getEnemies().values()) {
            enemy.setDestinationType(EnemyDestinations.RandomLocation);
            checkCollision(enemy, Collections.singletonMap(preBossMap.getSpacecraft1().getID(), preBossMap.getSpacecraft1()));
            if(!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip())
                checkCollision(enemy, Collections.singletonMap(preBossMap.getSpacecraft2().getID(), preBossMap.getSpacecraft2()));
            if(!(enemy instanceof Tier2Enemy) || !((Tier2Enemy)enemy).isRushing())
                wonderAround(enemy);
            if(enemy instanceof Tier2Enemy && ((Tier2Enemy) enemy).isRushing()) {
                rushIntoSpacecraft((Tier2Enemy) enemy);
            }
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
                if(!(obj instanceof Tier2Enemy) || (!((Tier2Enemy) obj).isRushing()))
                ((Enemy) obj).setDestinationLocation(
                    new Location(
                            iteratorHelper.getMiddlePointX() - objHelper.getMiddlePointX(),
                            iteratorHelper.getMiddlePointY() - objHelper.getMiddlePointY()
                    ));

                if(!(obj instanceof Tier2Enemy)) //If obj is not tier2
                    ((Enemy) obj).setDestinationType(EnemyDestinations.Spacecraft);

                if(obj instanceof Tier2Enemy){ //if obj is tier2
                    if(!((Tier2Enemy) obj).isRushing()){
                        ((Enemy) obj).setDestinationType(EnemyDestinations.Spacecraft);
                        ((Tier2Enemy) obj).setRushing(true);
                    }

                }
            }

            if (PositionHelper.isThereACollision(objHelper, iteratorHelper)) {
                obj.setDead(true);

                if (obj instanceof Bullet)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((Bullet) obj).getDamage());
                else if (obj instanceof Tier1Enemy || obj instanceof Tier3Enemy)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((EnemyGun)((Enemy) obj).getFiringBehavior()).getBulletDamage());
                else if (obj instanceof Meteor)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((Meteor) obj).getDamage());
                else if (obj instanceof Tier2Enemy){
                    explodeTier2(obj);
                }else if(obj instanceof Buff){
                    ((Buff)obj).setOwnerID(iterator.getID());
                }
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
                    randomX = 2 * (Math.random() - 0.5);
                    randomY = 2 * (Math.random() - 0.5);
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

    private void rushIntoSpacecraft(Tier2Enemy enemy){
        enemy.setRushingTimer(enemy.getRushingTimer() + 1);
        enemy.setRushingTimer(enemy.getRushingTimer() % Tier2Enemy.RUSHING_DURATION);
        if (enemy.getRushingTimer() == 0) {
            enemy.setDestinationType(EnemyDestinations.RandomLocation);
            enemy.setRushing(false);
        }
        PositionHelper enemyHelper = new PositionHelper(enemy);
        if(enemyHelper.isInsideTurnableObj(enemy,PreBossMap.MAP_WIDTH, PreBossMap.MAP_HEIGHT)) {
            enemy.moveToDirection(enemy.getVelocity() * 2, enemy.getDestinationLocation().getPositionX(), -enemy.getDestinationLocation().getPositionY());
        }
        else {
            enemy.setRushingTimer(Tier2Enemy.RUSHING_DURATION-1);
        }
    }

    private void explodeTier2(LocatableObject obj){
        PositionHelper p = new PositionHelper(obj);
        for(var enemy : preBossMap.getEnemies().values()){
            PositionHelper enemyHelper = new PositionHelper(enemy);
            if(PositionHelper.isInRadar(p, enemyHelper, ((Tier2Enemy) obj).getImpactRadius())){
                enemy.setHealthPoint(enemy.getHealthPoint()-Tier2Enemy.CLASHING_DAMAGE);
                if(enemy.getHealthPoint() <= 0){
                    enemy.setDead(true);
                }
            }
        }
        for(var station : preBossMap.getStations().values()){
            PositionHelper stationHelper = new PositionHelper(station);
            if(PositionHelper.isInRadar(p, stationHelper, ((Tier2Enemy) obj).getImpactRadius())){
                station.setHealthPoint(station.getHealthPoint()-Tier2Enemy.CLASHING_DAMAGE);
                if(station.getHealthPoint() <= 0){
                    station.setDead(true);
                }
            }
        }
        PositionHelper p1 = new PositionHelper(preBossMap.getSpacecraft1());
        if(PositionHelper.isInRadar(p,p1,((Tier2Enemy) obj).getImpactRadius())){
            preBossMap.getSpacecraft1().setHealthPoint(preBossMap.getSpacecraft1().getHealthPoint() - Tier2Enemy.CLASHING_DAMAGE);
        }
        if (!isSinglePlayer && !gameSituation.isTwoPlayerSingleShip()){
            PositionHelper p2 = new PositionHelper(preBossMap.getSpacecraft2());
            if(PositionHelper.isInRadar(p,p2,((Tier2Enemy) obj).getImpactRadius())) {
                preBossMap.getSpacecraft2().setHealthPoint(preBossMap.getSpacecraft2().getHealthPoint() - Tier2Enemy.CLASHING_DAMAGE);
            }
        }
    }

    private void spawnSimpleEnemy(EnemyStation enemyStation){
        enemyStation.setProduceTimer(enemyStation.getProduceTimer()+1);
        enemyStation.setProduceTimer(enemyStation.getProduceTimer() % enemyStation.getProducePeriod());
        Location location = new Location(enemyStation.getLocation().getPositionX(),enemyStation.getLocation().getPositionY());
        if(enemyStation.getProduceTimer() == 0){
            Enemy enemy = enemyStation.getEnemyFactory().randomProduction(gameSituation.getLevel(), false, location);
            preBossMap.getEnemies().put(enemy.getID(), enemy);
        }
    }

    private void spawnEvolvedEnemy(EvolvedEnemyStation enemyStation){
        enemyStation.setProduceTimer(enemyStation.getProduceTimer()+1);
        enemyStation.setProduceTimer(enemyStation.getProduceTimer() % enemyStation.getProducePeriod());
        Location location = new Location(enemyStation.getLocation().getPositionX(),enemyStation.getLocation().getPositionY());
        if(enemyStation.getProduceTimer() == 0){
            Enemy enemy = enemyStation.getEnemyFactory().randomProduction(gameSituation.getLevel(), true, location);
            preBossMap.getEnemies().put(enemy.getID(), enemy);
        }
    }

    private void useSmartBomb(Spacecraft spacecraft){

    }

    public void meteorRain(){
        this.setMeteorTimer(this.getMeteorTimer() % METEOR_PERIOD);

            if (this.getMeteorTimer() == 0) {
                for (int i = 0; i < 15 ; i++){
                    preBossMap.addMeteor(new Meteor( new Location(0,0)));
                }
            }
            this.setMeteorTimer(this.getMeteorTimer() + 1);
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

    public int getMeteorTimer() {
        return meteorTimer;
    }

    public void setMeteorTimer(int meteorTimer) {
        this.meteorTimer = meteorTimer;
    }
}
