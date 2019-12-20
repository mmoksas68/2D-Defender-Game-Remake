package org.openjfx.model.menuEntities;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.Comparator;

public class HighScoreInfo implements Serializable{

    private ObservableList<HighScore> level1Scores[], level2Scores[], level3Scores[];
    private static HighScoreInfo highScoreInfo;


    private HighScoreInfo() {

        level1Scores = new ObservableList[2];
        level2Scores = new ObservableList[2];
        level3Scores = new ObservableList[2];

        for(int i = 0; i < 2; i++){
            level1Scores[i] = FXCollections.observableArrayList();
            level2Scores[i] = FXCollections.observableArrayList();
            level3Scores[i] = FXCollections.observableArrayList();
            for(int j = 0; j < 10; j++){
                level1Scores[i].add(new HighScore(j+1, 0));
                level2Scores[i].add(new HighScore(j+1, 0));
                level3Scores[i].add(new HighScore(j+1, 0));
            }

        }

    }

    public static HighScoreInfo getInstance(){
        if(highScoreInfo == null)
            highScoreInfo = new HighScoreInfo();

        return highScoreInfo;
    }

    public static void setInstance(HighScoreInfo readObject) {
        highScoreInfo = readObject;
    }

    public ObservableList<HighScore>[] getLevel1Scores(){
        return level1Scores;
    }

    public ObservableList<HighScore>[] getLevel2Scores() {
        return level2Scores;
    }

    public ObservableList<HighScore>[] getLevel3Scores() {
        return level3Scores;
    }

    public boolean updateScores(int level, boolean isSinglePlayer, int score) {
        int index = 0;
        if (!isSinglePlayer)
            index = 1;

        if(level == 1){
            if (score > level1Scores[index].get(9).getScore()) {
                HighScore highScore = addScore(index, level, score);
                if (highScore != null) {
                    Comparator<HighScore> comparator = Comparator.comparingInt(HighScore::getScore);
                    FXCollections.sort(level1Scores[index], comparator);
                    level1Scores[index].remove(10);
                    highScore.setRanking(level1Scores[index].indexOf(highScore) + 1);
                    return true;
                }
            }
        }

        else if(level == 2){
            if (score > level2Scores[index].get(9).getScore()) {
                HighScore highScore = addScore(index, level, score);
                if (highScore != null) {
                    Comparator<HighScore> comparator = Comparator.comparingInt(HighScore::getScore);
                    FXCollections.sort(level2Scores[index], comparator);
                    level2Scores[index].remove(10);
                    highScore.setRanking(level2Scores[index].indexOf(highScore) + 1);
                    return true;
                }
            }
        }
        else if(level == 3){
            if (score > level3Scores[index].get(9).getScore()) {
                HighScore highScore = addScore(index, level, score);
                if (highScore != null) {
                    Comparator<HighScore> comparator = Comparator.comparingInt(HighScore::getScore);
                    FXCollections.sort(level3Scores[index], comparator);
                    level3Scores[index].remove(10);
                    highScore.setRanking(level3Scores[index].indexOf(highScore) + 1);
                    return true;
                }
            }
        }
        return false;

    }

    private HighScore addScore(int index, int level, int score){
        HighScore highScore = new HighScore(score);
        if(level == 1){
            level1Scores[index].add(highScore);
            return highScore;
        }

        else if(level == 2){
            level2Scores[index].add(highScore);
            return highScore;
        }

        else if(level == 3){
            level3Scores[index].add(highScore);
            return highScore;
        }
        return null;
    }


}
