package org.openjfx.model.commonEntities.FiringBehavior;

import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.utilization.PositionHelper;


public class SpacecraftGun extends SimpleGun {


    private Spacecraft spacecraft;

    public SpacecraftGun(int gunPeriod, int bulletDamage, int gunTimer, double bulletVelocity, Spacecraft spacecraft) {
        super(gunPeriod, bulletDamage, gunTimer, bulletVelocity);
        this.spacecraft = spacecraft;
    }

    @Override
    public Bullet fireBullet() {
        int direction = spacecraft.isDirectionLeft() ? -10 : 10;
        PositionHelper spacecraftHelper = new PositionHelper(spacecraft);
        Location location = spacecraft.isDirectionLeft() ?
                new Location(spacecraftHelper.getLeft() - 10,
                        ((spacecraftHelper.getTop() + spacecraftHelper.getBottom()) / 2))
                :
                new Location(spacecraftHelper.getRight() + 10,
                        ((spacecraftHelper.getTop() + spacecraftHelper.getBottom()) / 2));

        return new Bullet(location, getBulletDamage(), getBulletVelocity(), direction, 0);
    }
}
