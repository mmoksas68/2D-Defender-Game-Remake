package org.openjfx.utilization;

import org.openjfx.model.preBossEntities.Enemy.*;


public class ModelToViewEnemy extends ModelToView{
    private EnemyTypes type;
    private double destinationX;
    private double destinationY;
    private int health;
    private int maxHealth;
    private boolean isEvolved; //for picture

    public ModelToViewEnemy(Enemy enemy) {
        super(enemy);

        this.health = enemy.getHealthPoint();
        this.destinationX = enemy.getDestinationLocation().getPositionX();
        this.destinationY = enemy.getDestinationLocation().getPositionY();
        if( enemy instanceof Tier1Enemy)
        {
            this.type = enemy.isEvolved() ?  EnemyTypes.tier1evolved  : EnemyTypes.tier1unevolved ;
            this.maxHealth = enemy.isEvolved() ? Tier1Enemy.MAX_HEALTH*2 : Tier1Enemy.MAX_HEALTH;
            this.isEvolved = enemy.isEvolved();
        }else if( enemy instanceof Tier2Enemy)
        {
            this.type = enemy.isEvolved() ?  EnemyTypes.tier2evolved  : EnemyTypes.tier2unevolved ;
            this.maxHealth = enemy.isEvolved() ? Tier2Enemy.MAX_HEALTH*2 : Tier2Enemy.MAX_HEALTH;
            this.isEvolved = enemy.isEvolved();
        }else if( enemy instanceof Tier3Enemy)
        {
            this.type = enemy.isEvolved() ?  EnemyTypes.tier3evolved  : EnemyTypes.tier3unevolved ;
            this.maxHealth = enemy.isEvolved() ? Tier3Enemy.MAX_HEALTH*2 : Tier3Enemy.MAX_HEALTH;
            this.isEvolved = enemy.isEvolved();
        }

    }

    public EnemyTypes getType() {
        return type;
    }

    public void setType(EnemyTypes type) {
        this.type = type;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
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


    public boolean getIsEvolved() {
        return isEvolved;
    }

    public void setIsEvolved(boolean isEvolved) {
        this.isEvolved = isEvolved;
    }
}
