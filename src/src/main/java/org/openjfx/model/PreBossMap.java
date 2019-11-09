package org.openjfx.model;

import org.openjfx.model.entities.Boss.Boss;
import org.openjfx.model.entities.Boss.Laser;
import org.openjfx.model.entities.Boss.LittleBoss;
import org.openjfx.model.entities.Boss.Rocket;
import org.openjfx.model.entities.Buff.Buff;
import org.openjfx.model.entities.Building.Building;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.model.entities.Enemy.Enemy;
import org.openjfx.model.entities.Enemy.Tier1;
import org.openjfx.model.entities.Meteor.Meteor;
import org.openjfx.model.entities.Spacecraft.Spacecraft;
import org.openjfx.utilization.PositionHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PreBossMap implements Serializable {
    public static double MAX_HEIGHT;
    public static double MAX_WIDTH;

    private int level;
    private java.util.Map<Long, Enemy> enemies = new HashMap<Long, Enemy>();
    private java.util.Map<Long, Building> buildings = new HashMap<Long, Building>();
    private java.util.Map<Long, Bullet> bullets = new HashMap<Long, Bullet>();
    private java.util.Map<Long, Buff> buffs = new HashMap<Long, Buff>();
    private java.util.Map<Long, Meteor> meteors = new HashMap<Long, Meteor>();
    private List<Enemy> firingEnemies;
    private Spacecraft spacecraft;
    private Spacecraft spacecraft2;
    private double viewLeft;
    private double viewRight;
    private static double hitboxWidthScale;
    private static double hitboxHeightScale;
    private boolean isFreeze;
    private boolean isExceedUpper = false;
    private boolean isExceedSide = false;

    public PreBossMap(int level, double hitboxWidthScale, double hitboxHeightScale ) {
        this.hitboxWidthScale = hitboxWidthScale;
        this.hitboxHeightScale = hitboxHeightScale;
        MAX_WIDTH = 5*hitboxWidthScale;
        MAX_HEIGHT = hitboxHeightScale;
        viewLeft = 2*hitboxWidthScale;
        viewRight = 3*hitboxWidthScale;
        this.level = level;
        spacecraft = new Spacecraft(new Location(MAX_WIDTH/2, MAX_HEIGHT/2), (int)(hitboxWidthScale*Spacecraft.WIDTH_SCALE), (int)(hitboxHeightScale*Spacecraft.HEIGHT_SCALE), 30, 1, 1, 1, 7, 1, 1, true, true, true);
        initMap();
    }

    public void refreshMap() {
        refreshLocatableObject(Collections.singletonMap(spacecraft.getID(), spacecraft), Spacecraft.WIDTH_SCALE, Spacecraft.HEIGHT_SCALE);
        refreshLocatableObject(getEnemies(), Enemy.WIDTH_SCALE, Enemy.HEIGHT_SCALE);
        refreshLocatableObject(getBullets(), Bullet.WIDTH_SCALE, Bullet.HEIGHT_SCALE);
        viewLeft = 5*hitboxWidthScale*viewLeft / MAX_WIDTH;
        MAX_WIDTH = 5*hitboxWidthScale;
        MAX_HEIGHT = hitboxHeightScale;
    }

    public <T extends LocatableObject> void refreshLocatableObject( java.util.Map<Long, T> list, double widthScaleSpecific, double heightScaleSpecific ) {
        for(var iterator : list.values()){
            iterator.getLocation().setPositionX(5*hitboxWidthScale*iterator.getLocation().getPositionX() / MAX_WIDTH);
            iterator.getLocation().setPositionY(hitboxHeightScale*iterator.getLocation().getPositionY() / MAX_HEIGHT);
            iterator.setHitBoxWidth(hitboxWidthScale*widthScaleSpecific);
            iterator.setHitBoxHeight(hitboxHeightScale*heightScaleSpecific);
        }
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public void initMap() {
        for (int i = 0; i < 50; i++) {
            Tier1 enemy = new Tier1(new Location((int) (Math.random() * (MAX_WIDTH-40)), (int) (Math.random() * (1000-(int)(MAX_HEIGHT*Enemy.HEIGHT_SCALE)))), (int)(hitboxWidthScale*Enemy.WIDTH_SCALE), (int)(hitboxHeightScale*Enemy.HEIGHT_SCALE), 30, 50, 2, 359, 50, 5, 30);
            enemies.put(enemy.getID(), enemy);
        }
    }

    public void checkMapSituation() {

        for (var bullet : getBullets().values()) {
            if (bullet.getLocation().getPositionX() <= viewLeft || bullet.getLocation().getPositionY() <= 0 ||
                    bullet.getLocation().getPositionX() >= viewLeft + hitboxWidthScale || bullet.getLocation().getPositionY() >= MAX_HEIGHT) {
                bullet.setDead(true);
            } else {
                checkCollision(bullet, getEnemies());
                checkCollision(bullet, getBuildings());
                checkCollision(bullet, Collections.singletonMap(spacecraft.getID(), spacecraft));
            }

        }

        firingEnemies = new ArrayList<Enemy>();

        for (var enemy : getEnemies().values()) {
            checkCollision(enemy, getBuildings());
            checkCollision(enemy, Collections.singletonMap(spacecraft.getID(), spacecraft));
            wonderAround(enemy);
            if(enemy instanceof Tier1)
                firingEnemies.add(enemy);
        }
    }


    private <T extends LocatableObject> void checkCollision(LocatableObject obj, java.util.Map<Long, T> list) {
        PositionHelper objHelper = new PositionHelper(obj);

        for (var iterator : list.values()) {
            PositionHelper iteratorHelper = new PositionHelper(iterator);

            if(obj instanceof Enemy && iterator instanceof Spacecraft){
                if(PositionHelper.isInRadar(objHelper, iteratorHelper, ((Enemy) obj).getRadarRadius())){

                    ((Enemy) obj).setDestinationLocation(
                                                        new Location(
                                                        iteratorHelper.getMiddlePointX() - objHelper.getMiddlePointX(),
                                                        iteratorHelper.getMiddlePointY() - objHelper.getMiddlePointY()
                                                        ));

                    ((Enemy) obj).setDestinationType("spacecraft");
                }else{
                    ((Enemy) obj).setDestinationType("empty");
                }
            }

            if (PositionHelper.isThereACollision(objHelper, iteratorHelper)) {
                obj.setDead(true);

                if (obj instanceof Bullet)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((Bullet) obj).getDamage());
                else if (obj instanceof Enemy)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((Enemy) obj).getDamage());
                else if (obj instanceof Meteor)
                    iterator.setHealthPoint(iterator.getHealthPoint() - ((Meteor) obj).getDamage());

                if (iterator.getHealthPoint() <= 0)
                    iterator.setDead(true);
            }
        }
    }

    public void wonderAround(Enemy enemy){
        if (!enemy.getDestinationType().equals("spacecraft")) {
                double randomY;
                double randomX;
                enemy.setChangeDirectionTimer(enemy.getChangeDirectionTimer() % enemy.getChangeDirectionPeriod());
            PositionHelper enemyPosition = new PositionHelper(enemy);
            if(enemyPosition.isInside(MAX_WIDTH, MAX_HEIGHT)) {
                if (enemy.getChangeDirectionTimer() == 0) {
                    randomX = 5 * (Math.random() - 0.5);
                    randomY = 5 * (Math.random() - 0.5);
                    enemy.setDestinationLocation(new Location(randomX, randomY));
                }
                enemy.setChangeDirectionTimer(enemy.getChangeDirectionTimer() + 1);
                enemy.moveToDirection(enemy.getVelocity(), enemy.getDestinationLocation().getPositionX(), enemy.getDestinationLocation().getPositionY());
                isExceedUpper = false;
                isExceedSide = false;
            }
            else {
                enemy.setChangeDirectionTimer(0);
                if (enemyPosition.getTop() < 0 | enemyPosition.getBottom() > MAX_HEIGHT) {
                    enemy.setDestinationLocation(new Location(enemy.getDestinationLocation().getPositionX(), -enemy.getDestinationLocation().getPositionY()));
                    enemy.moveToDirection(enemy.getVelocity(), enemy.getDestinationLocation().getPositionX(), enemy.getDestinationLocation().getPositionY());
                }
                if (enemyPosition.getLeft() < 0 | enemyPosition.getRight() > MAX_HEIGHT){
                    enemy.setDestinationLocation(new Location(-enemy.getDestinationLocation().getPositionX(), enemy.getDestinationLocation().getPositionY()));
                    enemy.moveToDirection(enemy.getVelocity(), enemy.getDestinationLocation().getPositionX(), enemy.getDestinationLocation().getPositionY());
                }
                enemy.setChangeDirectionTimer(enemy.getChangeDirectionTimer() + 1);
            }
        }

    }

    public void addEnemy(Enemy enemy) {
        enemies.put(enemy.getID(), enemy);
    }

    public void deleteEnemy(long ID) {
        enemies.remove(ID);
    }

    public void addBuilding(Building building) {
    }

    public void deleteBuilding(long ID) {
    }

    public void addBullet(Bullet bullet) {
        bullets.put(bullet.getID(), bullet);
    }

    public void deleteBullet(long ID) {
        bullets.remove(ID);
    }

    public void addBuff(Buff buff) {
    }

    public void deleteBuff(long ID) {
    }

    public void addMeteor(Meteor meteor) {
    }

    public void deleteMeteor(long ID) {

    }

    public java.util.Map<Long, Enemy> getEnemies() {
        return enemies;
    }

    public java.util.Map<Long, Building> getBuildings() {
        return buildings;
    }

    public java.util.Map<Long, Bullet> getBullets() {
        return bullets;
    }

    public java.util.Map<Long, Buff> getBuffs() {
        return buffs;
    }

    public java.util.Map<Long, Meteor> getMeteors() {
        return meteors;
    }

    public double getViewLeft() {
        return viewLeft;
    }

    public void setViewLeft(double viewLeft) {
        this.viewLeft = viewLeft;
    }

    public double getViewRight() {
        return viewRight;
    }

    public void setViewRight(double viewRight) {
        this.viewRight = viewRight;
    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public void setFreeze(boolean freeze) {
        isFreeze = freeze;
    }

    public double getHitboxWidthScale() {
        return hitboxWidthScale;
    }

    public void setHitboxWidthScale(double hitboxWidthScale) {
        this.hitboxWidthScale = hitboxWidthScale;
    }

    public double getHitboxHeightScale() {
        return hitboxHeightScale;
    }

    public void setHitboxHeightScale(double hitboxHeightScale) {
        this.hitboxHeightScale = hitboxHeightScale;
    }

    public List<Enemy> getFiringEnemies() {
        return firingEnemies;
    }

    public static double getMaxHeight() {
        return MAX_HEIGHT;
    }

    public static void setMaxHeight(double maxHeight) {
        MAX_HEIGHT = maxHeight;
    }

    public static double getMaxWidth() {
        return MAX_WIDTH;
    }

    public static void setMaxWidth(double maxWidth) {
        MAX_WIDTH = maxWidth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setEnemies(java.util.Map<Long, Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setBuildings(java.util.Map<Long, Building> buildings) {
        this.buildings = buildings;
    }

    public void setBullets(java.util.Map<Long, Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setBuffs(java.util.Map<Long, Buff> buffs) {
        this.buffs = buffs;
    }

    public void setMeteors(java.util.Map<Long, Meteor> meteors) {
        this.meteors = meteors;
    }

    public void setFiringEnemies(List<Enemy> firingEnemies) {
        this.firingEnemies = firingEnemies;
    }

    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    public Spacecraft getSpacecraft2() {
        return spacecraft2;
    }

    public void setSpacecraft2(Spacecraft spacecraft2) {
        this.spacecraft2 = spacecraft2;
    }
}
