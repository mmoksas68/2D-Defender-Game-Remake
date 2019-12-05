package model;

import java.util.ArrayList;

public class BossTwo extends Boss {
    private final double ROCKET_FREQ = 0.01;
    private final int MAX_ROCKET_NUMBER = 5;
    private double rocketDamage;
    private double rocketRadius;
    public BossTwo () {
        super ( 3, 150,150);
        setHealthPoint(10000);
        setBulletVelocity( 10);
        setGunPower( 5);
        setGunFrequency( 0.07);
        rocketDamage = 5;
        rocketRadius = 100;
    }

    private Marker createRocket (double maxX, double maxY) {
        double rocketX = Math.random() * maxX;
        double rocketY = Math.random() * maxY;
        Marker marker = new Marker( new Location( rocketX, rocketY), rocketRadius, rocketDamage);
        return marker;
    }
    public ArrayList<Marker>  sendRockets (double maxX, double maxY) {
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
}
