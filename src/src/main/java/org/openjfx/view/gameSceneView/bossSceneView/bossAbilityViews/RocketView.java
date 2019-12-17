package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews;

import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewSpecialAbility;

public class RocketView extends SpecialAbilityView {
    private static String resource = "resources/rocket.png";
    public RocketView(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH)  {
        super( modelToViewSpecialAbility , resource, scaleW, scaleH);
    }

    @Override
    public void refresh(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH) {
        double rotateAmount = Math.toDegrees(Math.acos(modelToViewSpecialAbility.getYdir()) );
        setRotate( -rotateAmount);
        super.refresh( modelToViewSpecialAbility, scaleW, scaleH);
    }
}
