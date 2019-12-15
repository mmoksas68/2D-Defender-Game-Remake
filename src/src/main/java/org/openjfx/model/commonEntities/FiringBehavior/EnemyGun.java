package org.openjfx.model.commonEntities.FiringBehavior;

import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.preBossEntities.Enemy.Enemy;
import org.openjfx.utilization.PositionHelper;

public class EnemyGun extends SimpleGun{
    private Enemy enemy;

    public EnemyGun(int gunPeriod, int bulletDamage, int gunTimer, double bulletVelocity, Enemy enemy) {
        super(gunPeriod, bulletDamage, gunTimer, bulletVelocity);
        this.enemy = enemy;
    }

    @Override
    public Bullet fireBullet() {
        PositionHelper enemyHelper = new PositionHelper(enemy);
        double radius = enemyHelper.findRadius();
        double x = Math.sqrt((radius*radius) / (Math.pow(enemy.getDestinationLocation().getPositionY()/enemy.getDestinationLocation().getPositionX(),2) + 1));
        double y = Math.abs(enemy.getDestinationLocation().getPositionY()/enemy.getDestinationLocation().getPositionX())*x;

        if(enemy.getDestinationLocation().getPositionX() < 0)
            x = -x;
        if(enemy.getDestinationLocation().getPositionY() < 0)
            y = -y;

        x += enemyHelper.getMiddlePointX();
        y += enemyHelper.getMiddlePointY();

        return new Bullet(new Location(x ,y), getBulletDamage(), getBulletVelocity(), enemy.getDestinationLocation().getPositionX(), -enemy.getDestinationLocation().getPositionY());
    }
}
