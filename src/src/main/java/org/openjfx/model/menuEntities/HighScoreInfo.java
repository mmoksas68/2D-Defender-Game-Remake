package org.openjfx.model.menuEntities;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class HighScoreInfo implements Serializable{

    private ObservableList<HighScore> level1Scores[], level2Scores[], level3Scores[];
    private static HighScoreInfo highScoreInfo;


    private HighScoreInfo() {

        level1Scores = new ObservableList[2];
        level2Scores = new ObservableList[2];
        level3Scores = new ObservableList[3];

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

        level1Scores[0].set(1,new HighScore(5, 10));

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
}
