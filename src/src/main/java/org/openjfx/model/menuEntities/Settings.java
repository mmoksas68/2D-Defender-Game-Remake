package org.openjfx.model.menuEntities;

import javafx.scene.input.KeyCode;

import java.io.Serializable;

public class Settings implements Serializable {

    private static Settings settings;

    private KeyCode up;
    private KeyCode down;
    private KeyCode left;
    private KeyCode right;
    private KeyCode fire;
    private KeyCode hyperJump;
    private KeyCode smartBomb;
    private KeyCode up2;
    private KeyCode down2;
    private KeyCode left2;
    private KeyCode right2;
    private KeyCode fire2;
    private KeyCode hyperJump2;
    private KeyCode smartBomb2;
    private int background;
    private int volume;


    private Settings() {

    }

    public static Settings getInstance(){
        if(settings == null){
            settings = new Settings();
        }
        return settings;
    }

    public static void setInstance(Settings settings2){
        settings = settings2;
    }

    //################################## GETTER AND SETTER METHODS ##########################################//

    public KeyCode getUp() {
        return up;
    }

    public void setUp(KeyCode up) {
        this.up = up;
    }

    public KeyCode getDown() {
        return down;
    }

    public void setDown(KeyCode down) {
        this.down = down;
    }

    public KeyCode getRight() {
        return right;
    }

    public KeyCode getLeft() {
        return left;
    }

    public void setRight(KeyCode right) {
        this.right = right;
    }

    public void setLeft(KeyCode left) {
        this.left = left;
    }

    public KeyCode getFire() {
        return fire;
    }

    public KeyCode getHyperJump() {
        return hyperJump;
    }

    public void setFire(KeyCode fire) {
        this.fire = fire;
    }

    public void setHyperJump(KeyCode hyperJump) {
        this.hyperJump = hyperJump;
    }

    public KeyCode getSmartBomb() {
        return smartBomb;
    }

    public KeyCode getUp2() {
        return up2;
    }

    public void setSmartBomb(KeyCode smartBomb) {
        this.smartBomb = smartBomb;
    }

    public void setUp2(KeyCode up2) {
        this.up2 = up2;
    }

    public KeyCode getDown2() {
        return down2;
    }

    public void setDown2(KeyCode down2) {
        this.down2 = down2;
    }

    public KeyCode getLeft2() {
        return left2;
    }

    public void setLeft2(KeyCode left2) {
        this.left2 = left2;
    }

    public KeyCode getRight2() {
        return right2;
    }

    public void setRight2(KeyCode right2) {
        this.right2 = right2;
    }

    public KeyCode getFire2() {
        return fire2;
    }

    public void setFire2(KeyCode fire2) {
        this.fire2 = fire2;
    }

    public KeyCode getHyperJump2() {
        return hyperJump2;
    }

    public void setHyperJump2(KeyCode hyperJump2) {
        this.hyperJump2 = hyperJump2;
    }

    public KeyCode getSmartBomb2() {
        return smartBomb2;
    }

    public void setSmartBomb2(KeyCode smartBomb2) {
        this.smartBomb2 = smartBomb2;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
