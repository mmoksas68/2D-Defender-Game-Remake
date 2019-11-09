package org.openjfx.model;

import org.openjfx.model.entities.Bullet.Bullet;

public interface FireBullets {
    Bullet fireBullet();

    int getBulletVelocity();

    void setBulletVelocity(int bulletVelocity);

    int getBulletDamage();

    void setBulletDamage(int bulletDamage);

    int getGunPeriod();

    void setGunPeriod(int gunPeriod);

    public int getGunTimer() ;

    void setGunTimer(int gunTimer);
}
