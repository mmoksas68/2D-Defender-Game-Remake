package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.menuEntities.PassedLevelInfo;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvHeadingLabel;
import org.openjfx.view.menuView.menuEntitiesView.SubHeaderLabel;

import java.util.ArrayList;
import java.util.List;

public class LevelSelection extends VBox {
    private List<Image> images = Assets.getInstance().getMenuAssets().getBackgrounds();
    private final ToggleGroup group = new ToggleGroup();
    private RadioButton selection1Button, selection2Button, selection3Button;
    private HBox radioButtons, hBoxImages, hBoxLevelLabels;
    private FiyuvBottomMenu bottomMenu;
    private double width, height;
    private List<ImageView> imageViews;
    private FiyuvHeadingLabel headingLabel;
    private Label levelLabel1, levelLabel2, levelLabel3;
    private PassedLevelInfo passedLevelInfo = PassedLevelInfo.getInstance();

    public LevelSelection(double width, double height){
        radioButtons = new HBox();
        hBoxImages = new HBox();
        bottomMenu = new FiyuvBottomMenu("Back", "Start");
        hBoxLevelLabels = new HBox();

        levelLabel1 = new SubHeaderLabel("Level 1");
        levelLabel2 = new SubHeaderLabel("Level 2");
        levelLabel3 = new SubHeaderLabel("Level 3");

        hBoxLevelLabels.setSpacing(350);
        hBoxLevelLabels.setAlignment(Pos.CENTER);


        hBoxLevelLabels.getChildren().addAll(levelLabel1, levelLabel2, levelLabel3);


        this.width = width;
        this.height = height;


        selection1Button = new RadioButton();
        selection2Button = new RadioButton();
        selection3Button = new RadioButton();
        selection1Button.setToggleGroup(group);
        selection2Button.setToggleGroup(group);
        selection3Button.setToggleGroup(group);

        selection1Button.setSelected(true);

        headingLabel = new FiyuvHeadingLabel("Level Selection");

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
        disableRadioButtons();


        this.getChildren().addAll(headingLabel, hBoxLevelLabels, hBoxImages, radioButtons, bottomMenu);
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("menu-root");
    }

    private void disableRadioButtons(){
        if(!passedLevelInfo.getIsLevelPassed(1)) {
            selection2Button.setDisable(true);
            selection3Button.setDisable(true);
        }
        else if(!passedLevelInfo.getIsLevelPassed(2))
            selection3Button.setDisable(true);
    }

    private void designView() {
        for (ImageView imageView : imageViews) {
            imageView.setFitWidth(250);
            imageView.setFitWidth(250);
        }
        for (ImageView imageView : imageViews) {
            imageView.setFitHeight(250);
            imageView.setFitHeight(250);
        }

        radioButtons.setSpacing(450);
        radioButtons.setAlignment(Pos.CENTER);
        hBoxImages.setSpacing(200);


        this.setSpacing(55);
    }

    public int getSelectedItem(){
        if(selection1Button.isSelected())
            return 1;
        else if(selection2Button.isSelected()){
            return 2;
        }
        else{
            return 3;
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


    public void enableLevel2(){
        selection2Button.setDisable(false);
    }

    public void enableLevel3(){
        selection3Button.setDisable(false);
    }


}
