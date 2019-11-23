package model;

public interface FireBullets {
    Bullet fireBullet();

    int getBulletVelocity();

    void setBulletVelocity(int bulletVelocity);

    int getBulletDamage();

    void setBulletDamage(int bulletDamage);

    int getGunPeriod();

    void setGunPeriod(int gunPeriod);

    int getGunTimer() ;

    void setGunTimer(int gunTimer);
}
