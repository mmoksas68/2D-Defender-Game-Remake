package org.openjfx.model.menuEntities;

public class HighScore {

    private int ranking, score;

    public HighScore(int ranking, int score){
        this.ranking = ranking;
        this.score = score;
    }

    public int getScore(){
        return score;
    }


    public int getRanking() {return ranking;}

}
