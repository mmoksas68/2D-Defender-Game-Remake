package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.utilization.ModelToViewSpecialAbility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MarkerView extends SpecialAbilityView {
    private FireView fireView;
    public MarkerView(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH)  {
        super( modelToViewSpecialAbility, scaleW, scaleH);
      //  fireView = new FireView();

    }

    @Override
    public void refresh(ModelToViewSpecialAbility modelToViewSpecialAbility, double scaleW, double scaleH) {
        super.refresh(modelToViewSpecialAbility, scaleW, scaleH);
      /*  fireView.setFitHeight( modelToViewSpecialAbility.getHitboxHeight());
        fireView.setFitWidth( modelToViewSpecialAbility.getHitboxWidth());
        fireView.setTranslateX( modelToViewSpecialAbility.getLocationX());
        fireView.setTranslateY(modelToViewSpecialAbility.getLocationY());*/
    }
}
