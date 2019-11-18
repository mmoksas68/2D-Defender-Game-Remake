package org.openjfx.model;

public class RadarMap {
    public static double MAX_HEIGHT;
    public static double MAX_WIDTH;
    private static double hitboxWidthScale;
    private static double hitboxHeightScale;

    public RadarMap(double hitboxWidthScale, double hitboxHeightScale ) {
        this.hitboxWidthScale = hitboxWidthScale;
        this.hitboxHeightScale = hitboxHeightScale;
        MAX_WIDTH = 5*hitboxWidthScale;
        MAX_HEIGHT = hitboxHeightScale;
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

    public void refreshRadarMap(double hitboxWidthScale, double hitboxHeightScale){
        MAX_WIDTH = 5*hitboxWidthScale;
        MAX_HEIGHT = hitboxHeightScale;
    }
}
