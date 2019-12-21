package org.openjfx.fileManager;

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
        GameSaveObj gameSaveObj = GameSaveObj.getInstance();
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
            oos.writeObject(gameSaveObj);
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

    public boolean loadGame(){
        try {
            //If you want to use saved game change directory
            fis = new FileInputStream(new File("gameData/gameSave.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {

            System.out.println("EXCEPTION CATCHED");
            e.printStackTrace();
        }
        try {
           GameSaveObj.setInstance((GameSaveObj) ois.readObject());
           LocatableObject.setCurrentID(GameSaveObj.getInstance().getLastSavedID());
           return true;
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
        PassedLevelInfo passedLevelInfo = PassedLevelInfo.getInstance();
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
            System.out.println("load");
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
