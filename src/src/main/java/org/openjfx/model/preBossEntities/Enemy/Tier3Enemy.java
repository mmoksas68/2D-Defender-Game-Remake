package org.openjfx.model.preBossEntities.Enemy;

import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.FiringBehavior.EnemyGun;

import java.util.ArrayList;

public class Tier3Enemy extends Enemy{

        public static final double WIDTH = 100;
        public static final double HEIGHT = 60;
        public static final int MAX_HEALTH = 1;
        public static final int SCORE_POINT = 6;
        public static final int BULLET_VELOCITY = 10;
        public static final int BULLET_DAMAGE = 30;
        public static final int GUN_PERIOD = 100;
        public static final int VELOCITY = 10;
        public static final int RADAR_RADIUS = 1000;

        public Tier3Enemy(Location location, boolean isEvolved) {
             super(location, WIDTH, HEIGHT, MAX_HEALTH, VELOCITY, RADAR_RADIUS, isEvolved, SCORE_POINT);
             super.setFiringBehavior( new EnemyGun(isEvolved ? 2*GUN_PERIOD/3 : GUN_PERIOD, isEvolved ? 2*BULLET_DAMAGE : BULLET_DAMAGE, 0, isEvolved ? 4*BULLET_VELOCITY/3 : BULLET_VELOCITY, this));
        }

        public ArrayList<Enemy> divide(){
            ArrayList<Enemy> newEnemies = new ArrayList<>();
            newEnemies.add(new Tier1Enemy(new Location(getLocation().getPositionX() - Tier1Enemy.WIDTH, getLocation().getPositionY()), isEvolved()));
            newEnemies.add(new Tier2Enemy(new Location(getLocation().getPositionX() + Tier2Enemy.WIDTH, getLocation().getPositionY()), isEvolved()));
            return newEnemies;
        }
}


