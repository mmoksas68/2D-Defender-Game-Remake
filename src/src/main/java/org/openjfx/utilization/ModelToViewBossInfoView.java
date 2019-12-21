package org.openjfx.utilization;

import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.Boss.BossOne;
import org.openjfx.model.bossEntities.Boss.BossThree;
import org.openjfx.model.bossEntities.Boss.BossTwo;

public class ModelToViewBossInfoView {

    private int MAX_HP;
    private int HP;

    public ModelToViewBossInfoView(Boss boss)
    {
        HP = boss.getHealthPoint();
        findType( boss);
    }


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void findType( Boss boss){
        if( boss instanceof BossOne)
            MAX_HP = BossOne.getMaxHealthPoint();
        else if( boss instanceof BossTwo)
            MAX_HP = BossTwo.getMaxHealthPoint();
        else if( boss instanceof BossThree)
            MAX_HP = BossThree.getMaxHealthPoint();
    }

    public int getMAX_HP() {
        return MAX_HP;
    }
}
