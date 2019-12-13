package org.openjfx.utilization;
import org.openjfx.model.entities.Building.AllyBuilding;
import org.openjfx.model.entities.Building.Building;
import org.openjfx.model.entities.Building.EnemyBuilding1;
import org.openjfx.model.entities.Building.EnemyBuilding2;
import org.openjfx.model.entities.Enemy.Enemy;

enum BuildingClassCheck{
    AllyBuilding, EnemyBuilding1, EnemyBuilding2
}

public class ModelToViewBuilding {
    private double locationX;
    private double locationY;
    private double hitboxWidth;
    private double hitboxHeight;
    private long ID;
    private double currentViewLeft;
    private double currentViewRight;
    private boolean isDead;
    private int type;
    private int health;
    private int maxHealth;

    public ModelToViewBuilding(Building building, double currentViewLeft, double currentViewRight) {
        this.health = building.getHealthPoint();
        this.locationX = building.getLocation().getPositionX();
        this.locationY = building.getLocation().getPositionY();
        this.hitboxWidth = building.getHitBoxWidth();
        this.hitboxHeight = building.getHitBoxHeight();
        this.ID = building.getID();
        this.isDead = building.isDead();
        this.currentViewLeft = currentViewLeft;
        this.currentViewRight = currentViewRight;

        BuildingClassCheck buildingClassCheck = BuildingClassCheck.valueOf(building.getClass().getSimpleName());
        switch (buildingClassCheck){
            case AllyBuilding:
                this.type = 1;
                this.maxHealth = AllyBuilding.MAX_HEALTH;
                break;
            case EnemyBuilding1:
                this.type = ((EnemyBuilding1)building).getTier() + 1;
                this.maxHealth = EnemyBuilding1.MAX_HEALTH;
                break;
            case EnemyBuilding2:
                this.type = ((EnemyBuilding2)building).getTier() + 4;
                this.maxHealth = EnemyBuilding2.MAX_HEALTH;
                break;
        }
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public double getHitboxWidth() {
        return hitboxWidth;
    }

    public void setHitboxWidth(double hitboxWidth) {
        this.hitboxWidth = hitboxWidth;
    }

    public double getHitboxHeight() {
        return hitboxHeight;
    }

    public void setHitboxHeight(double hitboxHeight) {
        this.hitboxHeight = hitboxHeight;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public double getCurrentViewLeft() {
        return currentViewLeft;
    }

    public void setCurrentViewLeft(double currentViewLeft) {
        this.currentViewLeft = currentViewLeft;
    }

    public double getCurrentViewRight() {
        return currentViewRight;
    }

    public void setCurrentViewRight(double currentViewRight) {
        this.currentViewRight = currentViewRight;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
