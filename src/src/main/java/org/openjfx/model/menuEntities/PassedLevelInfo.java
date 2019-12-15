package org.openjfx.model.menuEntities;

import java.io.Serializable;

public class PassedLevelInfo implements Serializable {

    private static PassedLevelInfo passedLevelInfo;

    private boolean level1;
    private boolean level2;
    private boolean level3;

    private PassedLevelInfo(){
    }

    public static PassedLevelInfo getInstance(){
        if (passedLevelInfo == null){
            passedLevelInfo = new PassedLevelInfo();
        }
        return passedLevelInfo;
    }

    public static void setInstance(PassedLevelInfo passedLevelInfo2){
        passedLevelInfo = passedLevelInfo2;
    }


    public boolean isLevel1() {
        return level1;
    }

    public void setLevel1(boolean level1) {
        this.level1 = level1;
    }

    public boolean isLevel2() {
        return level2;
    }

    public void setLevel2(boolean level2) {
        this.level2 = level2;
    }

    public boolean isLevel3() {
        return level3;
    }

    public void setLevel3(boolean level3) {
        this.level3 = level3;
    }
}
