package org.openjfx.view.gameSceneView.preBossSceneView.TopBar.radarView;

import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.preBossEntities.Enemy.Enemy;
import org.openjfx.model.preBossEntities.Enemy.Tier1Enemy;
import org.openjfx.model.preBossEntities.Meteor.Meteor;
import org.openjfx.model.preBossEntities.Station.EnemyStation;
import org.openjfx.model.preBossEntities.Station.EvolvedEnemyStation;
import org.openjfx.model.preBossEntities.Station.Station;

enum RadarTypes{
    Spacecraft,
    Station,
    Enemy
}

public class RadarObject {
    private long ID;
    private RadarTypes type;
    private Location location;
    private boolean isDead;

    public RadarObject(LocatableObject obj) {
        ID = obj.getID();
        location = obj.getLocation();
        isDead = obj.isDead();
        if(obj instanceof Spacecraft)
            type = RadarTypes.Spacecraft;
        else if(obj instanceof Enemy)
            type = RadarTypes.Enemy;
        else if(obj instanceof Station)
                type = RadarTypes.Station;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public RadarTypes getType() {
        return type;
    }

    public void setType(RadarTypes type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
