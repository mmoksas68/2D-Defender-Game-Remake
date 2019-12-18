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

        levelLabel1 = new Label("Level 1");
        levelLabel2 = new Label("Level 2");
        levelLabel3 = new Label("Level 3");

        levelLabel1.setFont(Font.font("Verdana", 25));
        levelLabel2.setFont(Font.font("Verdana", 25));
        levelLabel3.setFont(Font.font("Verdana", 25));
        levelLabel1.setTextFill(Color.BLACK);
        levelLabel2.setTextFill(Color.BLACK);
        levelLabel3.setTextFill(Color.BLACK);


        hBoxLevelLabels.setSpacing(width*11/40);
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
    }

    private void disableRadioButtons(){
        if(!passedLevelInfo.isLevel1()) {
            selection2Button.setDisable(true);
            selection3Button.setDisable(true);
        }
        else if(!passedLevelInfo.isLevel2())
            selection3Button.setDisable(true);

    }


    private void designView() {
        for (ImageView imageView : imageViews) {
            imageView.setFitWidth(width / 6);
            imageView.setFitWidth(width / 6);
        }
        for (ImageView imageView : imageViews) {
            imageView.setFitHeight(width / 6);
            imageView.setFitHeight(width / 6);
        }

        radioButtons.setSpacing(width / 3 );
        radioButtons.setAlignment(Pos.CENTER);
        hBoxImages.setSpacing(width/6);


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


    public void enableLevel2(){
        selection2Button.setDisable(false);
    }

    public void enableLevel3(){
        selection3Button.setDisable(false);
    }


}
