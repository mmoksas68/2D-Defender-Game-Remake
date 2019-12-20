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

    public boolean updateScores(int level, boolean isSinglePlayer, int score) {
        int playerNumber = 1;
        if (!isSinglePlayer)
            playerNumber = 2;

        int number = 1;
        if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;
        else if(score > getScore(playerNumber, level, 2))
            return setScore(playerNumber, level, 2, score) ;
        else if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;
        else if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score);
        else if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;
        else if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;
        else if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;
        else if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score);
        else if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;
        else if (score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;
        else if(score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;
        else if (score > getScore(playerNumber, level, 1))
            return setScore(playerNumber, level, 1, score) ;

        return false;

    }


    private int getScore(int playerNumber, int level, int number){
        if(level == 1){
            return level1Scores[playerNumber - 1].get(number - 1).getScore();
        }

        else if(level == 2){
            return level2Scores[playerNumber - 1].get(number - 1).getScore();
        }

        else if(level == 3){
            return level3Scores[playerNumber - 1].get(number - 1).getScore();
        }
        return 0;
    }

    private boolean setScore(int playerNumber, int level, int number, int score){
        if(level == 1){
            level1Scores[playerNumber - 1].set(number - 1,  new HighScore(number, score));
            return true;
        }

        else if(level == 2){
            level2Scores[playerNumber - 1 ].set(number - 1,  new HighScore(number, score));
            return true;
        }

        else if(level == 3){
            level3Scores[playerNumber - 1].set(number - 1, new HighScore(number, score));
            return true;
        }
        return false;
    }


}
