package org.openjfx.model.entities.Buff;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class Buff extends LocatableObject {
    public static final int MAX_HEALTH = 1;
    public static final double WIDTH_SCALE = 0.03;
    public static final double HEIGHT_SCALE = 0.05;
    private int buffType;

    public Buff(Location location, int hitBoxWidth, int hitBoxHeight, int buffType) {
        super(location, hitBoxWidth, hitBoxHeight, MAX_HEALTH);
        this.buffType = buffType;
    }

    public int getBuffType() {
        return buffType;
    }

    public void setBuffType(int buffType) {
        this.buffType = buffType;
    }
}
