package org.openjfx.utilization;

import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;

public class ModelToViewSpaceCraft extends ModelToView{
    private boolean isDirectionLeft;
    private boolean isMoving;
    private int choosenPicNo;

    public ModelToViewSpaceCraft(Spacecraft spacecraft) {
        super(spacecraft);
        isDirectionLeft = spacecraft.isDirectionLeft();
        isMoving = spacecraft.isMoving();
        choosenPicNo = spacecraft.getChoosenPicNo();
    }

    public boolean isDirectionLeft() {
        return isDirectionLeft;
    }

    public void setDirectionLeft(boolean directionLeft) {
        isDirectionLeft = directionLeft;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getChoosenPicNo() {
        return choosenPicNo;
    }

    public void setChoosenPicNo(int choosenPicNo) {
        this.choosenPicNo = choosenPicNo;
    }
}
