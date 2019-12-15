package org.openjfx.model.menuEntities;

public class GameSituation {
    private static int level;
    private static boolean isSinglePlayer;
    private static boolean isPreBossFinished;
    private static boolean isPreBossFinishedSuccessfully;
    private static boolean isBossFinishedSuccessfully;
    private static boolean isBossFinished;

    public GameSituation(int level, boolean isSinglePlayer) {
        this.level = level;
        this.isSinglePlayer = isSinglePlayer;
        isPreBossFinished = false;
        isBossFinished = false;
        isBossFinishedSuccessfully = false;
        isPreBossFinishedSuccessfully = false;
    }

    public static boolean isIsPreBossFinished() {
        return isPreBossFinished;
    }

    public static void setIsPreBossFinished(boolean isPreBossFinished) {
        GameSituation.isPreBossFinished = isPreBossFinished;
    }

    public static boolean isIsPreBossFinishedSuccessfully() {
        return isPreBossFinishedSuccessfully;
    }

    public static void setIsPreBossFinishedSuccessfully(boolean isPreBossFinishedSuccessfully) {
        GameSituation.isPreBossFinishedSuccessfully = isPreBossFinishedSuccessfully;
    }

    public static boolean isIsBossFinishedSuccessfully() {
        return isBossFinishedSuccessfully;
    }

    public static void setIsBossFinishedSuccessfully(boolean isBossFinishedSuccessfully) {
        GameSituation.isBossFinishedSuccessfully = isBossFinishedSuccessfully;
    }

    public static boolean isIsBossFinished() {
        return isBossFinished;
    }

    public static void setIsBossFinished(boolean isBossFinished) {
        GameSituation.isBossFinished = isBossFinished;
    }

    public static int getLevel() {
        return level;
    }

    public static boolean isIsSinglePlayer() {
        return isSinglePlayer;
    }
}
