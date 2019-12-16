package org.openjfx.model.menuEntities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;

public class GameSituation implements Serializable {
    private int level;
    private int score;
    private int spacecraft1;
    private int spacecraft2;
    private boolean isSinglePlayer;
    private BooleanProperty isPreBossFinished;
    private BooleanProperty isPreBossFinishedSuccessfully;
    private BooleanProperty isBossFinishedSuccessfully;
    private BooleanProperty isBossFinished;
    private BooleanProperty isFirstCraftDied;
    private BooleanProperty isSecondCraftDied;
    private BooleanProperty twoPlayerSingleShip;

    private static GameSituation gameSituation;

    private GameSituation(){
        //şimdilik böyle normalde menüden setlenekcek bu değerler
        level = 1;
        score = 0;
        spacecraft1 = 1;
        spacecraft2 = 0;
        isSinglePlayer = false;
    }

    public static GameSituation getInstance(){
        if(gameSituation == null){
            gameSituation = new GameSituation();
        }
        return gameSituation;
    }

    public static void setInstance(GameSituation gameSituation){
        GameSituation.gameSituation = gameSituation;
    }

    public void resetVar(){
        this.isPreBossFinished = new SimpleBooleanProperty(false);
        this.isPreBossFinishedSuccessfully = new SimpleBooleanProperty(false);
        this.isBossFinished = new SimpleBooleanProperty(false);
        this.isBossFinishedSuccessfully = new SimpleBooleanProperty(false);
        this.isFirstCraftDied = new SimpleBooleanProperty(false);
        this.isSecondCraftDied = new SimpleBooleanProperty(false);
        this.twoPlayerSingleShip = new SimpleBooleanProperty(false);
    }

    public void resetScore(){
        this.score = 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSpacecraft1() {
        return spacecraft1;
    }

    public void setSpacecraft1(int spacecraft1) {
        this.spacecraft1 = spacecraft1;
    }

    public int getSpacecraft2() {
        return spacecraft2;
    }

    public void setSpacecraft2(int spacecraft2) {
        this.spacecraft2 = spacecraft2;
    }

    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        isSinglePlayer = singlePlayer;
    }


    public boolean isIsBossFinished() {
        return isBossFinished.get();
    }

    public BooleanProperty isBossFinishedProperty() {
        return isBossFinished;
    }

    public void setIsBossFinished(boolean isBossFinished) {
        this.isBossFinished.set(isBossFinished);
    }

    public boolean isIsBossFinishedSuccessfully() {
        return isBossFinishedSuccessfully.get();
    }

    public BooleanProperty isBossFinishedSuccessfullyProperty() {
        return isBossFinishedSuccessfully;
    }

    public void setIsBossFinishedSuccessfully(boolean isBossFinishedSuccessfully) {
        this.isBossFinishedSuccessfully.set(isBossFinishedSuccessfully);
    }

    public boolean isIsPreBossFinishedSuccessfully() {
        return isPreBossFinishedSuccessfully.get();
    }

    public BooleanProperty isPreBossFinishedSuccessfullyProperty() {
        return isPreBossFinishedSuccessfully;
    }

    public void setIsPreBossFinishedSuccessfully(boolean isPreBossFinishedSuccessfully) {
        this.isPreBossFinishedSuccessfully.set(isPreBossFinishedSuccessfully);
    }

    public boolean isIsPreBossFinished() {
        return isPreBossFinished.get();
    }

    public BooleanProperty isPreBossFinishedProperty() {
        return isPreBossFinished;
    }

    public void setIsPreBossFinished(boolean isPreBossFinished) {
        this.isPreBossFinished.set(isPreBossFinished);
    }


    public boolean isFirstCraftDied() {
        return isFirstCraftDied.get();
    }

    public BooleanProperty isFirstCraftDiedProperty() {
        return isFirstCraftDied;
    }

    public void setIsFirstCraftDied(boolean isFirstCraftDied) {
        this.isFirstCraftDied.set(isFirstCraftDied);
    }

    public boolean isSecondCraftDied() {
        return isSecondCraftDied.get();
    }

    public BooleanProperty isSecondCraftDiedProperty() {
        return isSecondCraftDied;
    }

    public void setIsSecondCraftDied(boolean isSecondCraftDied) {
        this.isSecondCraftDied.set(isSecondCraftDied);
    }

    public boolean isTwoPlayerSingleShip() {
        return twoPlayerSingleShip.get();
    }

    public BooleanProperty twoPlayerSingleShipProperty() {
        return twoPlayerSingleShip;
    }

    public void setTwoPlayerSingleShip(boolean twoPlayerSingleShip) {
        this.twoPlayerSingleShip.set(twoPlayerSingleShip);
    }
}
