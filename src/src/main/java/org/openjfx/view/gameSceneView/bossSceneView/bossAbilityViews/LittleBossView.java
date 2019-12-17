package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.utilization.ModelToViewSpecialAbility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LittleBossView extends SpecialAbilityView {

    private static String resource = "resources/boss1.png";
    public LittleBossView(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH)  {
        super( modelToViewSpecialAbility , resource, scaleW, scaleH);
    }
    @Override
    public void refresh(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH) {
     //   double rotateAmount = Math.asin( -modelToViewSpecialAbility.getXdir());
        setRotate( getRotate() + 25);
        super.refresh( modelToViewSpecialAbility, scaleW, scaleH);
    }

}
