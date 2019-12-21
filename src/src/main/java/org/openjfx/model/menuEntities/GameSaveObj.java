package org.openjfx.model.menuEntities;


import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.preBossEntities.PreBossMap;

import java.io.Serializable;

public class GameSaveObj implements Serializable {

    private static GameSaveObj gameSaveObj;

    private PreBossMap preBossMap;
    private BossMap bossMap;
    private long lastSavedID;


    private GameSaveObj(){

    }

    public static GameSaveObj getInstance(){
        if(gameSaveObj == null){
            gameSaveObj = new GameSaveObj();
        }
        return gameSaveObj;
    }


    public static void setInstance(GameSaveObj gameSaveObj2){
        gameSaveObj = gameSaveObj2;
    }

    public BossMap getBossMap() {
        return bossMap;
    }

    public void setBossMap(BossMap bossMap) {
        this.bossMap = bossMap;
    }

    public PreBossMap getPreBossMap() {
        return preBossMap;
    }

    public void setPreBossMap(PreBossMap preBossMap) {
        this.preBossMap = preBossMap;
    }

    public long getLastSavedID() {
        return lastSavedID;
    }

    public void setLastSavedID(long lastSavedID) {
        this.lastSavedID = lastSavedID;

    }
}
