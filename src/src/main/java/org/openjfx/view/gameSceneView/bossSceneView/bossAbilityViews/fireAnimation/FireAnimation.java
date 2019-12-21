package org.openjfx.view.gameSceneView.bossSceneView.bossAbilityViews.fireAnimation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.model.bossEntities.BossAbility.Marker;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewSpecialAbility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FireAnimation {
    private Long ID;
    private ImageView[] imageViewList;
    private int imageViewPeriod = 15;
    private int imageViewTimer;
    private int current = 0;

    public FireAnimation(ModelToViewSpecialAbility modelToView, double scaleW, double scaleH){
        ID = modelToView.getID();
        imageViewList = new ImageView[8];
        String imageUrl = "assets/images/animations/fireAnimations/fire_";
        for(int i = 0; i < 8; i++)
        {
            try {
                imageViewList[i] = new ImageView(new Image(new FileInputStream(imageUrl + (i+1) + ".png" )));
                imageViewList[i].setFitHeight(modelToView.getHitboxHeight() * scaleH);
                imageViewList[i].setFitWidth(modelToView.getHitboxWidth()* scaleW);
                imageViewList[i].setTranslateX((modelToView.getLocationX() ) * scaleW);
                imageViewList[i].setTranslateY((modelToView.getLocationY() ) * scaleH);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public ImageView[] getImageViewList() {
        return imageViewList;
    }

    public void setImageViewList(ImageView[] imageViewList) {
        this.imageViewList = imageViewList;
    }

    public int getImageViewPeriod() {
        return imageViewPeriod;
    }

    public void setImageViewPeriod(int imageViewPeriod) {
        this.imageViewPeriod = imageViewPeriod;
    }

    public int getImageViewTimer() {
        return imageViewTimer;
    }

    public void setImageViewTimer(int imageViewTimer) {
        this.imageViewTimer = imageViewTimer;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
