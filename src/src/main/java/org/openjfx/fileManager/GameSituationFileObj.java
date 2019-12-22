package org.openjfx.fileManager;

import javafx.beans.property.BooleanProperty;

import java.io.Serializable;

public class GameSituationFileObj implements Serializable {
    private int level;
    private int score;
    private int spacecraft1;
    private int spacecraft2;
    private boolean isSinglePlayer;
    private boolean isPreBossFinished;
    private boolean isPreBossFinishedSuccessfully;
    private boolean isBossFinishedSuccessfully;
    private boolean isBossFinished;
    private boolean isFirstCraftDied;
    private boolean isSecondCraftDied;
    private boolean twoPlayerSingleShip;

    public GameSituationFileObj(){

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSpacecraft1() {
        return spacecraft1;
    }

    public void setSpacecraft1(int spacecraft1) {
        this.spacecraft1 = spacecraft1;
    }

    public int getSpacecraft2() {
        return spacecraft2;
    }

    public void setSpacecraft2(int spacecraft2) {
        this.spacecraft2 = spacecraft2;
    }

    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        isSinglePlayer = singlePlayer;
    }


    public boolean isPreBossFinished() {
        return isPreBossFinished;
    }

    public void setPreBossFinished(boolean preBossFinished) {
        isPreBossFinished = preBossFinished;
    }

    public boolean isPreBossFinishedSuccessfully() {
        return isPreBossFinishedSuccessfully;
    }

    public void setPreBossFinishedSuccessfully(boolean preBossFinishedSuccessfully) {
        isPreBossFinishedSuccessfully = preBossFinishedSuccessfully;
    }

    public boolean isBossFinishedSuccessfully() {
        return isBossFinishedSuccessfully;
    }

    public void setBossFinishedSuccessfully(boolean bossFinishedSuccessfully) {
        isBossFinishedSuccessfully = bossFinishedSuccessfully;
    }

    public boolean isBossFinished() {
        return isBossFinished;
    }

    public void setBossFinished(boolean bossFinished) {
        isBossFinished = bossFinished;
    }

    public boolean isFirstCraftDied() {
        return isFirstCraftDied;
    }

    public void setFirstCraftDied(boolean firstCraftDied) {
        isFirstCraftDied = firstCraftDied;
    }

    public boolean isSecondCraftDied() {
        return isSecondCraftDied;
    }

    public void setSecondCraftDied(boolean secondCraftDied) {
        isSecondCraftDied = secondCraftDied;
    }

    public boolean isTwoPlayerSingleShip() {
        return twoPlayerSingleShip;
    }

    public void setTwoPlayerSingleShip(boolean twoPlayerSingleShip) {
        this.twoPlayerSingleShip = twoPlayerSingleShip;
    }
}
