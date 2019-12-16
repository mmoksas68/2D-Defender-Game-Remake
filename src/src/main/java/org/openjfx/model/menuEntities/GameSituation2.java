package org.openjfx.model.menuEntities;

public class GameSituation2 {
    private static int level;
    private static int score;
    private static boolean isSinglePlayer;
    private static boolean isPreBossFinished;
    private static boolean isPreBossFinishedSuccessfully;
    private static boolean isBossFinishedSuccessfully;
    private static boolean isBossFinished;

    public GameSituation2(int level, boolean isSinglePlayer) {
        this.level = level;
        this.isSinglePlayer = isSinglePlayer;
        isPreBossFinished = false;
        isBossFinished = false;
        isBossFinishedSuccessfully = false;
        isPreBossFinishedSuccessfully = false;
        score = 0;
    }

    public static boolean isIsPreBossFinished() {
        return isPreBossFinished;
    }

    public static void setIsPreBossFinished(boolean isPreBossFinished) {
        GameSituation2.isPreBossFinished = isPreBossFinished;
    }

    public static boolean isIsPreBossFinishedSuccessfully() {
        return isPreBossFinishedSuccessfully;
    }

    public static void setIsPreBossFinishedSuccessfully(boolean isPreBossFinishedSuccessfully) {
        GameSituation2.isPreBossFinishedSuccessfully = isPreBossFinishedSuccessfully;
    }

    public static boolean isIsBossFinishedSuccessfully() {
        return isBossFinishedSuccessfully;
    }

    public static void setIsBossFinishedSuccessfully(boolean isBossFinishedSuccessfully) {
        GameSituation2.isBossFinishedSuccessfully = isBossFinishedSuccessfully;
    }

    public static boolean isIsBossFinished() {
        return isBossFinished;
    }

    public static void setIsBossFinished(boolean isBossFinished) {
        GameSituation2.isBossFinished = isBossFinished;
    }

    public static int getLevel() {
        return level;
    }

    public static boolean isIsSinglePlayer() {
        return isSinglePlayer;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameSituation2.score = score;
    }
}
