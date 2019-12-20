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

    public boolean getIsLevelPassed(int level){
        if(level == 1)
            return level1;
        else if(level == 2)
            return level2;
        else if(level == 3)
            return level3;

        return false;

    }

    public void setLevelPassed(int level, boolean isPassed)
    {
        if(level == 1){
            this.level1 = isPassed;
        }
        else if(level == 2){
            this.level2 = isPassed;
        }
        else if(level == 3){
            this.level3 = isPassed;
        }
    }


}
