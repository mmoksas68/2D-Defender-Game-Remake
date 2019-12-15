package org.openjfx.model.bossEntities.Boss;

import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.bossEntities.BossAbility.Marker;

import java.util.ArrayList;

public class BossTwo extends Boss {

    private final double ROCKET_FREQ = 0.01;
    private final int MAX_ROCKET_NUMBER = 5;
    private final double rocketDamage = 5;
    private double rocketRadius = 100;
    private static final int hitBoxWidth = 150;
    private static final int hitBoxHeight = 150;
    private static final int MAX_HEALTH_POINT = 1000;

    public BossTwo () {
        super ( 3, hitBoxWidth, hitBoxHeight, MAX_HEALTH_POINT);
    }

    private Marker createRocket (double maxX, double maxY) {
        double rocketX = Math.random() * maxX;
        double rocketY = Math.random() * maxY;
        Marker marker = new Marker( new Location( rocketX, rocketY), rocketRadius, rocketDamage);
        return marker;
    }
    public ArrayList<Marker> sendRockets (double maxX, double maxY) {
        ArrayList <Marker> markers = new ArrayList<>();
        for ( int i = 0; i < MAX_ROCKET_NUMBER; i++) {
            Marker marker = createRocket( maxX, maxY);
            markers.add(marker);
        }
        return markers;
    }

    public int getMAX_ROCKET_NUMBER() {
        return MAX_ROCKET_NUMBER;
    }

    public double getROCKET_FREQ() {
        return ROCKET_FREQ;
    }
/*
    @Override
    public int getBulletDamage() {
        return 0;
    }

    @Override
    public void setBulletDamage(int bulletDamage) {

    }*/
}
