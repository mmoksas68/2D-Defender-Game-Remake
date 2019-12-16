package org.openjfx.model.menuEntities;

import java.io.Serializable;

public class GameSituation implements Serializable {
    private int level;
    private int score;
    private int spacecraft1;
    private int spacecraft2;
    private boolean isSinglePlayer;
    private boolean isPreBossFinished;
    private boolean isPreBossFinishedSuccessfully;
    private boolean isBossFinishedSuccessfully;
    private boolean isBossFinished;

    private static GameSituation gameSituation;

    private GameSituation(){
        //şimdilik böyle normalde menüden setlenekcek bu değerler
        level = 1;
        score = 0;
        spacecraft1 = 0;
        spacecraft2 = 1;
    }

    public static GameSituation getInstance(){
        if(gameSituation == null){
            gameSituation = new GameSituation();
        }
        return gameSituation;
    }

    public static void setInstance(GameSituation gameSituation){
        GameSituation.gameSituation = gameSituation;
    }

    public void resetVar(){
        this.isPreBossFinished = false;
        this.isPreBossFinishedSuccessfully = false;
        this.isBossFinished = false;
        this.isBossFinishedSuccessfully = false;
    }

    public void resetScore(){
        this.score = 0;
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
}
