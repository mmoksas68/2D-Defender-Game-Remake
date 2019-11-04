package org.openjfx.model.entities.Boss;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

import java.util.ArrayList;

public class Boss extends LocatableObject {
    private int level;
    private ArrayList<String> dialogues;

    public Boss(Location location, int hitBoxWidth, int hitBoxHeight, int level, ArrayList<String> dialogues) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.level = level;
        this.dialogues = dialogues;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<String> getDialogues() {
        return dialogues;
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
