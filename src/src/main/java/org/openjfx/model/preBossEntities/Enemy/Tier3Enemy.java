package org.openjfx.model.preBossEntities.Enemy;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.FiringBehavior.EnemyGun;

import java.util.ArrayList;

public class Tier3Enemy extends Enemy{

        public static final double WIDTH = 100;
        public static final double HEIGHT = 60;
        public static final int MAX_HEALTH = 1;
        public static final int SCORE_POINT = 6;
        public static final int BULLET_VELOCITY = 10;
        public static final int BULLET_DAMAGE = 1;
        public static final int GUN_PERIOD = 100;
        public static final int VELOCITY = 10;
        public static final int RADAR_RADIUS = 1000;

        public Tier3Enemy(Location location, boolean isEvolved) {
            super(location, WIDTH, HEIGHT, MAX_HEALTH, RADAR_RADIUS, VELOCITY, isEvolved, SCORE_POINT);
            super.setFiringBehavior( new EnemyGun(GUN_PERIOD, BULLET_DAMAGE, 0, BULLET_VELOCITY, this));
        }

        public ArrayList<Enemy> divide(){
            return  null;
        }
}


