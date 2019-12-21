package org.openjfx.model.menuEntities;

import java.io.Serializable;

public class HighScore implements Serializable {

    private int ranking, score;

    public HighScore(int ranking, int score){
        this.ranking = ranking;
        this.score = score;
    }

    public HighScore(int score){
        this.score = score;
    }


    public int getScore(){
        return score;
    }


    public int getRanking() {return ranking;}


    public  void setRanking(int ranking){
        this.ranking = ranking;
    }

}
