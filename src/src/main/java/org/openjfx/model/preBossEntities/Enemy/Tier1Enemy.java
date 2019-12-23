package org.openjfx.model.preBossEntities.Enemy;

import org.openjfx.model.commonEntities.FiringBehavior.NoGun;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.FiringBehavior.EnemyGun;

import java.io.Serializable;

public class Tier1Enemy extends Enemy{
    public static final double WIDTH = 100;
    public static final double HEIGHT = 50;
    public static final int MAX_HEALTH = 10;
    public static final int SCORE_POINT = 2;
    public static final int BULLET_VELOCITY = 10;
    public static final int BULLET_DAMAGE = 5;
    public static final int GUN_PERIOD = 100;
    public static final int VELOCITY = 7;
    public static final int RADAR_RADIUS = 400;


    public Tier1Enemy(Location location, boolean isEvolved) {
        super(location, WIDTH, HEIGHT, MAX_HEALTH, VELOCITY, RADAR_RADIUS, isEvolved, SCORE_POINT);
        super.setFiringBehavior(new EnemyGun(isEvolved ? 2*GUN_PERIOD/3 : GUN_PERIOD, isEvolved ? 2*BULLET_DAMAGE : BULLET_DAMAGE, 0, isEvolved ? 4*BULLET_VELOCITY/3 : BULLET_VELOCITY, this));
    }

}
