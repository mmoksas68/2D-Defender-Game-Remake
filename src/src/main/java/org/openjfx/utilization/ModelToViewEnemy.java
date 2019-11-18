package org.openjfx.utilization;

import org.openjfx.model.entities.Enemy.Enemy;
import org.openjfx.model.entities.Enemy.Tier1;
import org.openjfx.model.entities.Enemy.Tier2;
import org.openjfx.model.entities.Enemy.Tier3;

enum EnemyClassCheck {
    Tier1, Tier2, Tier3
}

public class ModelToViewEnemy {
    private double locationX;
    private double locationY;
    private double hitboxWidth;
    private double hitboxHeight;
    private long ID;
    private double currentViewLeft;
    private double currentViewRight;
    private boolean isDead;
    private int health;
    private int maxHealth;
    private int type;

    public ModelToViewEnemy(Enemy enemy, double currentViewLeft, double currentViewRight) {
        this.health = enemy.getHealthPoint();
        this.locationX = enemy.getLocation().getPositionX();
        this.locationY = enemy.getLocation().getPositionY();
        this.hitboxWidth = enemy.getHitBoxWidth();
        this.hitboxHeight = enemy.getHitBoxHeight();
        this.ID = enemy.getID();
        this.isDead = enemy.isDead();
        this.currentViewLeft = currentViewLeft;
        this.currentViewRight = currentViewRight;

        EnemyClassCheck enemyClassCheck = EnemyClassCheck.valueOf(enemy.getClass().getSimpleName());
        switch (enemyClassCheck){
            case Tier1:
                this.type = enemy.isEvolved() ? 2 : 1;
                this.maxHealth = enemy.isEvolved() ? Tier1.MAX_HEALTH*2 : Tier1.MAX_HEALTH ;
                break;
            case Tier2:
                this.type = enemy.isEvolved() ? 4 : 3;
                this.maxHealth = enemy.isEvolved() ? Tier2.MAX_HEALTH*2: Tier2.MAX_HEALTH;
                break;
            case Tier3:
                this.type = enemy.isEvolved() ? 6 : 5;
                this.maxHealth = enemy.isEvolved() ? Tier3.MAX_HEALTH*2 : Tier3.MAX_HEALTH ;
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
