package org.openjfx.model.entities.Building;

import org.openjfx.model.Location;

public class AllyBuilding extends Building {
    public static final int MAX_HEALTH = 500;
    private int attackingEnemyNumber = 0;
    private boolean isUnderAttack = false;

    public AllyBuilding(Location location, double hitBoxWidth, double hitBoxHeight) {
        super(location, hitBoxWidth, hitBoxHeight, MAX_HEALTH);
    }

    public int getAttackingEnemyNumber() {
        return attackingEnemyNumber;
    }

    public void setAttackingEnemyNumber(int attackingEnemyNumber) {
        this.attackingEnemyNumber = attackingEnemyNumber;
    }

    public boolean isUnderAttack() {
        return isUnderAttack;
    }

    public void setUnderAttack(boolean underAttack) {
        isUnderAttack = underAttack;
    }
}
