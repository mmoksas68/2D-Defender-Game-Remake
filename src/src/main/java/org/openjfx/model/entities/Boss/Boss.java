package org.openjfx.model.entities.Boss;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

import java.util.ArrayList;

public class Boss extends LocatableObject {
    private int level;
    private ArrayList<String> dialogues;
    private int healthPoint;

    public Boss(Location location, int hitBoxWidth, int hitBoxHeight, int level, ArrayList<String> dialogues, int healthPoint) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.level = level;
        this.dialogues = dialogues;
        this.healthPoint = healthPoint;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void useSpecialPower(){
        switch (getLevel())
        {
            case 1:
                fireLaser();
                break;
            case 2:
                fireRockets();
                break;
            case 3:
                fireLittleBosses();
                break;
        }
    }

    private void fireRockets() {
    }

    private void fireLaser() {
    }

    private void fireLittleBosses(){

    }
}
