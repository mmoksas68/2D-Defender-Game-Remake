package org.openjfx.model.menuEntities;

public class GameSituation {
    private int level;
    private boolean isSinglePlayer;
    private boolean isPreBossFinished;
    private boolean isPreBossFinishedSuccessfully;
    private boolean isBossFinishedSuccessfully;
    private boolean isBossFinished;
    private static GameSituation gameSituation;

    private GameSituation() {
        isSinglePlayer = false;
        level = 1;
        isSinglePlayer = false;
        isPreBossFinished = false;
        isBossFinished = false;
        isBossFinishedSuccessfully = false;
        isPreBossFinishedSuccessfully = false;
    }

    public static GameSituation getInstance(){
        if(gameSituation == null)
            gameSituation = new GameSituation();

        return gameSituation;
    }

    public boolean isIsPreBossFinished() {
        return isPreBossFinished;
    }

    public void setIsPreBossFinished(boolean isPreBossFinished) {
        this.isPreBossFinished = isPreBossFinished;
    }

    public boolean isIsPreBossFinishedSuccessfully() {
        return isPreBossFinishedSuccessfully;
    }

    public void setIsPreBossFinishedSuccessfully(boolean isPreBossFinishedSuccessfully) {
        this.isPreBossFinishedSuccessfully = isPreBossFinishedSuccessfully;
    }

    public boolean isIsBossFinishedSuccessfully() {
        return isBossFinishedSuccessfully;
    }

    public void setIsBossFinishedSuccessfully(boolean isBossFinishedSuccessfully) {
        this.isBossFinishedSuccessfully = isBossFinishedSuccessfully;
    }

    public boolean isIsBossFinished() {
        return isBossFinished;
    }

    public void setIsBossFinished(boolean isBossFinished) {
        this.isBossFinished = isBossFinished;
    }

    public int getLevel() {
        return level;
    }

    public boolean isIsSinglePlayer() {
        return isSinglePlayer;
    }

    public void setSinglePlayer(boolean isSinglePlayer){
        this.isSinglePlayer = isSinglePlayer;
    }


}
