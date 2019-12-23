package org.openjfx.fileManager;

import javafx.beans.property.BooleanProperty;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.menuEntities.*;
import org.openjfx.model.preBossEntities.PreBossMap;

import java.io.*;

public class FileController {
    private boolean isGameSaveExist;
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;


    public FileController(){

    }

    //##################################################################################################//
    public void saveGame(){
        saveGameSaveObj();
        saveGameSituation();
    }

    public boolean loadGame(){

        loadGameSituation();
        return  loadGameSaveObj();
    }


    public void saveGameSaveObj(){

        try {
            fos = new FileOutputStream(new File("gameData/gameSave.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(GameSaveObj.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadGameSaveObj(){

        try {
            fis = new FileInputStream(new File("gameData/gameSave.txt"));
        } catch (FileNotFoundException e) {
            return false;
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            System.out.println("EXCEPTION CATCHED");
            return false;
        }
        try {
            GameSaveObj.setInstance((GameSaveObj) ois.readObject());
            if(GameSaveObj.getInstance().getPreBossMap() == null && GameSaveObj.getInstance().getBossMap() == null){
                return false;
            }
            LocatableObject.setCurrentID(GameSaveObj.getInstance().getLastSavedID());
        } catch (IOException e) {
            System.out.println("no saved game");
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void saveGameSituation() {
        GameSituation gameSituation = GameSituation.getInstance();
        GameSituationFileObj gsfo = new GameSituationFileObj();
        gsfo.setLevel(gameSituation.getLevel());
        gsfo.setScore(gameSituation.getScore());
        gsfo.setSpacecraft1(gameSituation.getSpacecraft1());
        gsfo.setSpacecraft2(gameSituation.getSpacecraft2());
        gsfo.setSinglePlayer(gameSituation.isSinglePlayer());
        gsfo.setPreBossFinished(gameSituation.isIsPreBossFinished());
        gsfo.setPreBossFinishedSuccessfully(gameSituation.isIsPreBossFinishedSuccessfully());
        gsfo.setBossFinished(gameSituation.isIsBossFinished());
        gsfo.setBossFinishedSuccessfully(gameSituation.isIsBossFinishedSuccessfully());
        gsfo.setFirstCraftDied(gameSituation.isFirstCraftDied());
        gsfo.setSecondCraftDied(gameSituation.isSecondCraftDied());
        gsfo.setTwoPlayerSingleShip(gameSituation.isTwoPlayerSingleShip());
        try {
            fos = new FileOutputStream(new File("gameData/gameSituation.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(gsfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGameSituation(){
        try {
            fis = new FileInputStream(new File("gameData/GameSituation.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            GameSituation gameSituation = GameSituation.getInstance();
            GameSituationFileObj gsfo = ((GameSituationFileObj) ois.readObject());
            gameSituation.setLevel(gsfo.getLevel());
            gameSituation.setScore(gsfo.getScore());
            gameSituation.setSpacecraft1(gsfo.getSpacecraft1());
            gameSituation.setSpacecraft2(gsfo.getSpacecraft2());
            gameSituation.setSinglePlayer(gsfo.isSinglePlayer());
            gameSituation.setIsPreBossFinished(gsfo.isPreBossFinished());
            gameSituation.setIsPreBossFinishedSuccessfully(gsfo.isPreBossFinishedSuccessfully());
            gameSituation.setIsBossFinished(gsfo.isBossFinished());
            gameSituation.setIsBossFinishedSuccessfully(gsfo.isBossFinishedSuccessfully());
            gameSituation.setIsFirstCraftDied(gsfo.isFirstCraftDied());
            gameSituation.setIsSecondCraftDied(gsfo.isSecondCraftDied());
            gameSituation.setTwoPlayerSingleShip(gsfo.isTwoPlayerSingleShip());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveKeys() {
        Settings settings = Settings.getInstance();

        try {
            fos = new FileOutputStream(new File("gameData/setting.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadKeys(){
        try {
            fis = new FileInputStream(new File("gameData/setting.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Settings settings = Settings.getInstance();
            settings.setInstance((Settings) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void savePassedLevelInfo(){
        PassedLevelInfo passedLevelInfo = PassedLevelInfo.getInstance();
        try {
            fos = new FileOutputStream(new File("gameData/levelInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(passedLevelInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPassedLevelInfo(){
        try {
            fis = new FileInputStream(new File("gameData/levelInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PassedLevelInfo.setInstance((PassedLevelInfo) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveHighScores(){
        HighScoreInfo highScoreInfo = HighScoreInfo.getInstance();
        try {
            fos = new FileOutputStream(new File("gameData/highScores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(highScoreInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadHighScores(){
        try {
            fis = new FileInputStream(new File("gameData/highScores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (EOFException e) {
            System.out.println("exception!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            HighScoreInfo.setInstance((HighScoreInfo) ois.readObject());
        } catch (EOFException e) {
            System.out.println("exception!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //##################################################################################################//

    //##################################################################################################//

    public File getFile(String address){
        return new File(address);
    }


    //##################################################################################################//

    //##################################################################################################//

    public boolean getIsGameSaveExist(){
        return isGameSaveExist;
    }

    public void setIsGameSaveExist(boolean isGameSaveExist){
        this.isGameSaveExist = isGameSaveExist;
    }

    //##################################################################################################//

    public static FileInputStream getFileStream( String url) throws FileNotFoundException {
        return new FileInputStream(url);
    }
}
