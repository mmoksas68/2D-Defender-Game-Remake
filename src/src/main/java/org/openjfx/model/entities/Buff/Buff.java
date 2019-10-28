package org.openjfx.model.entities.Buff;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class Buff extends LocatableObject {
    private int buffType;

    public Buff(Location location, int hitBoxWidth, int hitBoxHeight, int buffType) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.buffType = buffType;
    }

    public int getBuffType() {
        return buffType;
    }

    public void setBuffType(int buffType) {
        this.buffType = buffType;
    }
}
