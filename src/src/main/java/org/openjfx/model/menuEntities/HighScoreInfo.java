package org.openjfx.model.menuEntities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighScoreInfo implements Serializable{

    private ArrayList<HighScore> level1Scores[], level2Scores[], level3Scores[];
    private static HighScoreInfo highScoreInfo;


    private HighScoreInfo() {

        level1Scores = new ArrayList[2];
        level2Scores = new ArrayList[2];
        level3Scores = new ArrayList[2];

        for(int i = 0; i < 2; i++){
            level1Scores[i] = new ArrayList<>();
            level2Scores[i] = new ArrayList<>();
            level3Scores[i] = new ArrayList<>();
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
        ObservableList<HighScore>[] level1 = new ObservableList[2];
        level1[0] = FXCollections.observableList(level1Scores[0]);
        level1[1] = FXCollections.observableList(level1Scores[1]);

        return level1;
    }

    public ObservableList<HighScore>[] getLevel2Scores() {
        ObservableList<HighScore>[] level2 = new ObservableList[2];

        level2[0] = FXCollections.observableList(level1Scores[0]);
        level2[1] = FXCollections.observableList(level1Scores[1]);

        return level2;
    }

    public ObservableList<HighScore>[] getLevel3Scores() {
        ObservableList<HighScore>[] level3 = new ObservableList[2];

        level3[0] = FXCollections.observableList(level1Scores[0]);
        level3[1] = FXCollections.observableList(level1Scores[1]);

        return level3;
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
                    Collections.sort(level1Scores[index], comparator);
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
                    Collections.sort(level2Scores[index], comparator);
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
                    Collections.sort(level3Scores[index], comparator);
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
