package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.utilization.ModelToViewSpecialAbility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MarkerView extends SpecialAbilityView {
    private static String resource = "resources/dead_head.png";
    public MarkerView(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH)  {
        super( modelToViewSpecialAbility , resource, scaleW, scaleH);
    }
}
