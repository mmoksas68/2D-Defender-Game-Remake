package org.openjfx.utilization;

import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.Buff.BuffTypes;

public class ModelToViewBuff extends ModelToView{
    private BuffTypes type;

    public ModelToViewBuff(Buff buff) {
        super(buff);
        type = buff.getBuffType();
    }
}
