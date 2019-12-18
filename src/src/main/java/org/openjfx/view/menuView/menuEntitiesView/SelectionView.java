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


        this.getChildren().addAll(hBoxImages, radioButtons, hBoxButtons);
        radioButtons.setSpacing(width/3);
        hBoxImages.setSpacing(width/5);
        this.setPadding(new Insets(40,40,40,40));
        this.setSpacing(height/3);
    }

}
