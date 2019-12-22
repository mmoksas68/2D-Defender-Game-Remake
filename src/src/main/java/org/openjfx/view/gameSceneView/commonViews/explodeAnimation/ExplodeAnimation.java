package org.openjfx.view.gameSceneView.commonViews.explodeAnimation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.utilization.ModelToView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExplodeAnimation {
    private Long ID;
    private ImageView[] imageViewList;
    private int imageViewPeriod = 5;
    private int imageViewTimer;
    private int current = 0;

    public ExplodeAnimation(ModelToView modelToView, double viewLeft, double scaleW, double scaleH){
        ID = modelToView.getID();
        imageViewList = new ImageView[11];
        String imageUrl = "assets/images/animations/Explosion1/Explosion1_";
        for(int i = 0; i < 11; i++)
        {
            try {
                imageViewList[i] = new ImageView(new Image(new FileInputStream(imageUrl + (i+1) + ".png" )));
                imageViewList[i].setFitHeight(modelToView.getHitboxHeight() * scaleH);
                imageViewList[i].setFitWidth(modelToView.getHitboxWidth() * scaleW);
                imageViewList[i].setTranslateX((modelToView.getLocationX() - viewLeft) * scaleW);
                imageViewList[i].setTranslateY(modelToView.getLocationY() * scaleH);
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
