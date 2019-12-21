package org.openjfx.utilization;


import org.openjfx.model.bossEntities.BossAbility.*;

public class ModelToViewSpecialAbility extends ModelToView{
    private double ydir;
    private double xdir;
    private boolean isLaser = false;
    private boolean isMarker = false;
    private boolean isRocket = false;
    private boolean isLittleBoss = false;
    public ModelToViewSpecialAbility (SpecialAbility specialAbility) {
        super(specialAbility);
        ydir = specialAbility.getyDir();
        xdir = specialAbility.getxDir();
        findType(specialAbility);
    }
    private void findType (SpecialAbility specialAbility) {
        if ( specialAbility instanceof Laser)
            isLaser = true;
        else if ( specialAbility instanceof Marker)
            isMarker = true;
        else if ( specialAbility instanceof Rocket)
            isRocket = true;
        else if ( specialAbility instanceof LittleBoss)
            isLittleBoss = true;
    }

    public double getYdir() {
        return ydir;
    }

    public boolean isLaser() {
        return isLaser;
    }

    public boolean isMarker() {
        return isMarker;
    }

    public boolean isRocket() {
        return isRocket;
    }

    public boolean isLittleBoss() {
        return isLittleBoss;
    }

    public double getXdir() {
        return xdir;
    }
}
