package org.openjfx.utilization;

import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.Boss.BossOne;
import org.openjfx.model.bossEntities.Boss.BossThree;
import org.openjfx.model.bossEntities.Boss.BossTwo;

public class ModelToViewBoss extends ModelToView{
    private int bossType;
    public ModelToViewBoss (Boss boss) {
        super(boss);
        findType( boss);
    }

    private void findType (Boss boss) {
        if( boss instanceof BossOne)
            bossType = 1;
        else if( boss instanceof BossTwo)
            bossType = 2;
        else if( boss instanceof BossThree)
            bossType = 3;
    }

    public int getBossType() {
        return bossType;
    }
}
