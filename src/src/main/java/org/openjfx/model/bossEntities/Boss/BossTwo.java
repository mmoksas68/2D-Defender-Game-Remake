package org.openjfx.model.bossEntities.Boss;

import org.openjfx.model.bossEntities.BossAbility.Rocket;
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
    private static double velocity = 3;
    private static int gunPower = 10;
    private static double bulletVelocity = 10.0;
    private static double gunFrequency = 0.05;

    private ArrayList <Rocket> rockets = new ArrayList<>();
    public BossTwo () {
        super ( velocity, hitBoxWidth, hitBoxHeight, MAX_HEALTH_POINT);
        setGunFrequency( gunFrequency);
        setGunPower( gunPower);
        setBulletVelocity( bulletVelocity);
    }

    private Marker createMarker (double maxX, double maxY) {
        double markerX = Math.random() * maxX;
        double markerY = Math.random() * maxY;
        Marker marker = new Marker( new Location( markerX, markerY));
        Rocket rocket = new Rocket( new Location( getLocation().getPositionX() + getHitBoxWidth()/2, getLocation().getPositionY() + getHitBoxHeight()/2 ), marker);
        double yLength =  markerY - rocket.getLocation().getPositionY();
        double xLength =  markerX - rocket.getLocation().getPositionX();
        double destinationLength = Math.sqrt( Math.pow( xLength, 2.0) + Math.pow( yLength,2.0) ) ;
        double multiplier = 1 / destinationLength;
        rocket.setxDir( xLength * multiplier);
        rocket.setyDir( -yLength * multiplier);
        rocket.setDestinationX( markerX);
        rocket.setVelocity( destinationLength / 50);
        rockets.add( rocket);
        return marker;
    }
    public ArrayList<Marker> markAreas (double maxX, double maxY) {
        ArrayList <Marker> markers = new ArrayList<>();
        for ( int i = 0; i < MAX_ROCKET_NUMBER; i++) {
            Marker marker = createMarker( maxX, maxY);
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

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }
    public void clearRockets() {
        rockets.clear();
    }

    public static int getMaxHealthPoint() {
        return MAX_HEALTH_POINT;
    }
}
