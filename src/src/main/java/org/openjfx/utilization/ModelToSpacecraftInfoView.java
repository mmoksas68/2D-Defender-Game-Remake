package org.openjfx.utilization;

import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.menuEntities.GameSituation;

public class ModelToSpacecraftInfoView {
    private int hyperJumpBattery;
    private int smartBombCount;
    private int HP;

    public ModelToSpacecraftInfoView(Spacecraft spacecraft){
        if(!GameSituation.getInstance().isIsBossFinishedSuccessfully() || !GameSituation.getInstance().isIsPreBossFinished()) {
            hyperJumpBattery = spacecraft.getHyperJumpBattery();
        }
        smartBombCount = spacecraft.getSmartBombStock();
        HP = spacecraft.getHealthPoint();
    }

    public int getHyperJumpBattery() {
        return hyperJumpBattery;
    }

    public void setHyperJumpBattery(int hyperJumpBattery) {
        this.hyperJumpBattery = hyperJumpBattery;
    }

    public int getSmartBombCount() {
        return smartBombCount;
    }

    public void setSmartBombCount(int smartBombCount) {
        this.smartBombCount = smartBombCount;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
