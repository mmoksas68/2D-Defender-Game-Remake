package org.openjfx.model;

import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.model.entities.Enemy.Tier1;
import org.openjfx.model.entities.Spacecraft.Spacecraft;

import java.util.Collections;

public class GameBar {
    public static double MAX_HEIGHT;
    public static double MAX_WIDTH;
    private static double hitboxWidthScale;
    private static double hitboxHeightScale;

    private RadarMap radarMap;
    //private Score score;
    //private int humanNo;
    //private int enemy no;

    public GameBar(double hitboxWidthScale, double hitboxHeightScale ) {
        this.hitboxWidthScale = hitboxWidthScale;
        this.hitboxHeightScale = hitboxHeightScale;
        radarMap = new RadarMap(hitboxWidthScale/3, hitboxHeightScale);
    }

    public void setHitboxWidthScale(double widthScale){
        hitboxWidthScale = widthScale;
    }

    public double getHitboxWidthScale(){
        return hitboxWidthScale;
    }

    public void setHitboxHeightScale(double heightScale){
        hitboxHeightScale = heightScale;
    }

    public double getHitboxHeightScale(){
        return hitboxHeightScale;
    }

    public RadarMap getRadarMap(){
        return radarMap;
    }

    public void refreshGameBar() {
        radarMap.refreshRadarMap(hitboxWidthScale, hitboxHeightScale);
    }
}
