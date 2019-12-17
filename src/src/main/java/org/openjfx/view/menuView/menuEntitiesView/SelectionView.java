package org.openjfx.view.menuView.menuEntitiesView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SelectionView extends VBox{

    private final ToggleGroup group = new ToggleGroup();
    private RadioButton image1, image2, image3;
    private HBox radioButtons, hBoxImages, hBoxButtons;
    ArrayList<ImageView> imageViews;

    public SelectionView(ArrayList<Image> images, double width, double height){

        radioButtons = new HBox();
        hBoxImages = new HBox();
        hBoxButtons = new HBox();

        image1 = new RadioButton();
        image2 = new RadioButton();
        image3 = new RadioButton();
        image1.setToggleGroup(group);
        image2.setToggleGroup(group);
        image3.setToggleGroup(group);

        imageViews = new ArrayList<>();

        for(int i = 0; i < images.size(); i++){
            imageViews.add(new ImageView(images.get(i)));
            hBoxImages.getChildren().add(imageViews.get(i));
        }

        image1.setSelected(true);

        for(int i = 0; i < 3; i++) {
            imageViews.get(i).setFitHeight(height / 3);
            imageViews.get(i).setFitWidth(height / 3);
        }
        radioButtons.getChildren().addAll(image1, image2, image3);

        //hBoxButtons.getChildren().addAll(backButton, nextButton);

        this.getChildren().addAll(hBoxImages, radioButtons, hBoxButtons);
        radioButtons.setSpacing(width/3);
        radioButtons.setAlignment(Pos.CENTER);
        hBoxImages.setAlignment(Pos.CENTER);
        //hBoxButtons.setSpacing(width/2);
        hBoxImages.setSpacing(width/5);
        //hBoxButtons.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(40,40,40,40));
        this.setSpacing(height/3);
        this.setAlignment(Pos.CENTER);

        //selectionView.setFillWidth(width);

    }

    //  public VBox getSelectionView(){
    //      return selectionView;
    //}


    public int getSelectedImage(){
        if(image1.isSelected())
            return 0;
        else if(image2.isSelected()){
            return 1;
        }
        else{
            return 2;
        }
    }


}
