package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import org.openjfx.assetManager.Assets;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvHeadingLabel;

import java.util.ArrayList;
import java.util.List;

public class SpacecraftSelection extends VBox {
    private List<Image> images = Assets.getInstance().getPreBossAssets().getSpacecraft();
    private final ToggleGroup group = new ToggleGroup();
    private RadioButton selection1Button, selection2Button, selection3Button;
    private HBox radioButtons, hBoxImages;
    private FiyuvBottomMenu bottomMenu;
    private double width, height;
    private List<ImageView> imageViews;
    private FiyuvHeadingLabel headingLabel;

    public SpacecraftSelection(double width, double height, String heading){
        radioButtons = new HBox();
        hBoxImages = new HBox();
        bottomMenu = new FiyuvBottomMenu("Back", "Next");

        this.width = width;
        this.height = height;

        selection1Button = new RadioButton();
        selection2Button = new RadioButton();
        selection3Button = new RadioButton();
        selection1Button.setToggleGroup(group);
        selection2Button.setToggleGroup(group);
        selection3Button.setToggleGroup(group);

        selection1Button.setSelected(true);

        headingLabel = new FiyuvHeadingLabel(heading);

        radioButtons.getChildren().addAll(selection1Button, selection2Button, selection3Button);

        imageViews = new ArrayList<ImageView>();
        for(int i = 0; i < images.size(); i++){
            ImageView imageView = new ImageView(images.get(i));
            imageViews.add(i, imageView);
            hBoxImages.getChildren().add(imageView);
        }

        radioButtons.setAlignment(Pos.CENTER);
        hBoxImages.setAlignment(Pos.CENTER);


        this.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));

        designView();

        this.getChildren().addAll(headingLabel, hBoxImages, radioButtons, bottomMenu);
        this.setAlignment(Pos.CENTER);
    }

    private void designView() {
        for (ImageView imageView : imageViews) {
            imageView.setFitWidth(width / 7);
            imageView.setFitWidth(width / 7);
        }
        for (ImageView imageView : imageViews) {
            imageView.setFitHeight(width / 7);
            imageView.setFitHeight(width / 7);
        }

        radioButtons.setSpacing(width/3);
        hBoxImages.setSpacing(width/5);


        this.setPadding(new Insets(width/ 13, width / 13, width/13, width / 13));
        this.setSpacing(height/15);
    }

    public int getSelectedItem(){
        if(selection1Button.isSelected())
            return 0;
        else if(selection2Button.isSelected()){
            return 1;
        }
        else{
            return 2;
        }
    }

    public void setWidthHeight(double width, double height){
        this.width = width;
        this.height = height;
        designView();
    }
    public FiyuvButton getNextButton(){
        return bottomMenu.getButton2();
    }

    public FiyuvButton getBackButton(){
        return bottomMenu.getButton1();
    }

}
