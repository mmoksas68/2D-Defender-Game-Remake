package org.openjfx.utilization;

public class ModelToGameInfoView {

    private int score;
    private int enemyCount;
    private int stationCount;

    public ModelToGameInfoView(int score,int enemyCount, int stationCount){
        this.score = score;
        this.enemyCount = enemyCount;
        this.stationCount = stationCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getStationCount() {
        return stationCount;
    }

    public void setStationCount(int stationCount) {
        this.stationCount = stationCount;
    }
}
