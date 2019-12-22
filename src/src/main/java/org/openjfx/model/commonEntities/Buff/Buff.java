package org.openjfx.model.commonEntities.Buff;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class Buff extends LocatableObject {
    public static final double WIDTH = 30;
    public static final double HEIGHT = 30;
    public static final double BOSSMAP_VELOCITY = 10;
    private BuffTypes buffType;
    private double velocity = BOSSMAP_VELOCITY;
    private long ownerID;

    public Buff(Location location, BuffTypes buffType) {
        super(location, WIDTH, HEIGHT, 1);
        this.buffType = buffType;
    }

    public BuffTypes getBuffType() {
        return buffType;
    }

    public void setBuffType(BuffTypes buffType) {
        this.buffType = buffType;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }
}