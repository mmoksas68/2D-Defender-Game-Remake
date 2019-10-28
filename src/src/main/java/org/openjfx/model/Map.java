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

import java.util.ArrayList;

public class Map {
    public static final int MAX_HEIGHT = 880;
    public static final int MAX_WIDTH = 10000;

    private int level;
    private int[][] currentMap = new int[10000][880];
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Buff> buffs = new ArrayList<>();
    private ArrayList<Meteor> meteors = new ArrayList<>();
    private Spacecraft spacecraft;
    private Spacecraft spacecraft2;
    private int viewLeft;
    private int viewRight;
    private Boss boss;
    private Laser laser;
    private ArrayList<LittleBoss> littleBosses = new ArrayList<>();
    private ArrayList<Rocket> rockets = new ArrayList<>();
    private boolean isFreeze;

    public Map(int level) {
        this.level = level;
        spacecraft = new Spacecraft(new Location(100, 100), 30, 30, 1, 1, 1,1 ,1 , 1, 1, 1, true, true , true);
        initMap();
    }

    public void refreshMap(){

    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public void initMap(){
        enemies.add(new Tier1(new Location(50,50),50,50,50, 50, 50, 50, 50));
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void deleteEnemy(long ID){
    }

    public void addBuilding(Building building){
    }

    public void deleteBuilding(long ID){
    }

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public void deleteBullet(long ID){
    }

    public void addBuff(Buff buff){
    }

    public void deleteBuff(long ID){
    }

    public void addMeteor(Meteor meteor){
    }

    public void deleteMeteor(long ID){

    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public ArrayList<Meteor> getMeteors() {
        return meteors;
    }

    public ArrayList<LittleBoss> getLittleBosses() {
        return littleBosses;
    }

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public int getViewLeft() {
        return viewLeft;
    }

    public void setViewLeft(int viewLeft) {
        this.viewLeft = viewLeft;
    }

    public int getViewRight() {
        return viewRight;
    }

    public void setViewRight(int viewRight) {
        this.viewRight = viewRight;
    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public void setFreeze(boolean freeze) {
        isFreeze = freeze;
    }
}
