package org.openjfx.model.commonEntities.Buff;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class Buff extends LocatableObject {
    public static final double WIDTH = 0.03;
    public static final double HEIGHT = 0.05;
    private BuffTypes buffType;

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
}
