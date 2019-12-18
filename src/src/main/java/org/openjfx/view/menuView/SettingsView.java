package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.menuEntities.Settings;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvHeadingLabel;

import java.util.ArrayList;
import java.util.List;

public class SettingsView extends VBox {

    private List<Image> images = Assets.getInstance().getMenuAssets().getColorOptions();
    private Slider volumeSlider;
    private Label player1Label, player2Label;
    private GridPane gridPane;
    private TextField up1KeySel, down1KeySel, right1KeySel, left1KeySel, hyperjump1KeySel, smartbomb1KeySel;
    private TextField up2KeySel, down2KeySel, right2KeySel, left2KeySel, hyperjump2KeySel, smartbomb2KeySel;
    private Label upLabel, downLabel, rightLabel, leftLabel, hyperjumpLabel, smartbombLabel;
    private HBox radioButtons, hBoxImages;
    private double width, height;
    private FiyuvBottomMenu bottomMenu;
    private RadioButton selection1Button, selection2Button, selection3Button;
    private ToggleGroup group;
    private FiyuvHeadingLabel headingLabel;
    private ArrayList<ImageView> imageViews;
    private VBox vboxForVolumeAndColors, vboxSelectionView;
    private HBox hboxForGridPaneAndVBox;
    private  Label keySelection, colorSelection, volumeLabel;

    private Settings settings = Settings.getInstance();

    public SettingsView(double width, double height){
        this.width = width;
        this.height = height;

        vboxSelectionView = new VBox();
        vboxForVolumeAndColors = new VBox();
        hboxForGridPaneAndVBox = new HBox();

        bottomMenu = new FiyuvBottomMenu("Menu", "Save");

        volumeSlider = new Slider();
        volumeLabel = new Label("Volume");
        colorSelection = new Label("Menu Themes");

        VBox volume = new VBox();
        volume.getChildren().addAll(volumeLabel, volumeSlider);
        volume.setAlignment(Pos.CENTER);
        volume.setSpacing(height / 20);

        createGridPane();
        createSelectionView();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);


        keySelection = new Label("Key Selection");

        vBox.getChildren().addAll(keySelection, gridPane);
        vBox.setSpacing(height / 30);

        volumeSlider.setValue(settings.getVolume());

        vboxForVolumeAndColors.getChildren().addAll(vboxSelectionView, volume);
        hboxForGridPaneAndVBox.getChildren().addAll(vBox, vboxForVolumeAndColors);

        hboxForGridPaneAndVBox.setAlignment(Pos.CENTER);
        vboxForVolumeAndColors.setAlignment(Pos.CENTER);
        vboxSelectionView.setAlignment(Pos.CENTER);

        vboxForVolumeAndColors.setSpacing(height / 5);

        this.getChildren().addAll(headingLabel, hboxForGridPaneAndVBox, bottomMenu);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setPrefSize(width / 2, height / 2 );
        this.setSpacing(height / 8);
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPadding(new Insets(width / 13, width / 13, width / 13, width / 13));

    }

    private void createSelectionView(){

        radioButtons = new HBox();
        hBoxImages = new HBox();

        selection1Button = new RadioButton();
        selection2Button = new RadioButton();
        selection3Button = new RadioButton();
        selection1Button.setToggleGroup(group);
        selection2Button.setToggleGroup(group);
        selection3Button.setToggleGroup(group);

        selection1Button.setSelected(true);

        headingLabel = new FiyuvHeadingLabel("Settings");

        radioButtons.getChildren().addAll(selection1Button, selection2Button, selection3Button);

        imageViews = new ArrayList<ImageView>();
        for(int i = 0; i < images.size(); i++){
            ImageView imageView = new ImageView(images.get(i));
            imageViews.add(i, imageView);
            hBoxImages.getChildren().add(imageView);
        }

        radioButtons.setAlignment(Pos.CENTER);
        hBoxImages.setAlignment(Pos.CENTER);

        for (ImageView imageView : imageViews) {
            imageView.setFitWidth(width / 15);
            imageView.setFitWidth(width / 15);
        }
        for (ImageView imageView : imageViews) {
            imageView.setFitHeight(width / 15);
            imageView.setFitHeight(width / 15);
        }

        radioButtons.setSpacing(width/8);
        hBoxImages.setSpacing(width/15);

        vboxSelectionView.setSpacing(height / 20);

        vboxSelectionView.getChildren().addAll(colorSelection, hBoxImages, radioButtons);

    }


    private void createGridPane(){
        gridPane = new GridPane();
        createLabelsForGridPane();
        createTextFieldsForPlayer1Keys();
        createTextFieldsForPlayer2Keys();
        addLabelsIntoGridPane();
        addPlayer1KeysIntoGridPane();
        addPlayer2KeysIntoGridPane();

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(20);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(35);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(35);
        gridPane.getColumnConstraints().addAll(column0, column1, column2);
    }

    private void createLabelsForGridPane(){
        player1Label = new Label("Player 1");
        player2Label = new Label("Player 2");
        upLabel = new Label("UP: ");
        downLabel = new Label("DOWN: ");
        rightLabel = new Label("RIGHT: ");
        leftLabel = new Label("LEFT: ");
        hyperjumpLabel = new Label("HYPER JUMP: ");
        smartbombLabel = new Label("SMART BOMB: ");
    }


    private void addLabelsIntoGridPane(){
        addItemIntoGridPane(player1Label, 1, 0);
        addItemIntoGridPane(player2Label, 2, 0);
        addItemIntoGridPane(upLabel, 0, 1);
        addItemIntoGridPane(downLabel, 0, 2);
        addItemIntoGridPane(rightLabel, 0 , 3);
        addItemIntoGridPane(leftLabel, 0, 4);
        addItemIntoGridPane(hyperjumpLabel, 0 , 5);
        addItemIntoGridPane(smartbombLabel, 0, 6);
    }

    private void createTextFieldsForPlayer1Keys(){
        up1KeySel = new TextField("\u2191");
        down1KeySel = new TextField("\u2193");
        right1KeySel = new TextField("\u2192");
        left1KeySel = new TextField("\u2190");
        hyperjump1KeySel = new TextField("1");
        smartbomb1KeySel = new TextField("2");
    }
    private void createTextFieldsForPlayer2Keys(){
        up2KeySel = new TextField("W");
        down2KeySel = new TextField("S");
        right2KeySel = new TextField("D");
        left2KeySel = new TextField("A");
        hyperjump2KeySel = new TextField("Z");
        smartbomb2KeySel = new TextField("X");
    }


    private void addPlayer1KeysIntoGridPane(){
        addItemIntoGridPane(up1KeySel, 1, 1);
        addItemIntoGridPane(down1KeySel, 1, 2);
        addItemIntoGridPane(right1KeySel, 1, 3);
        addItemIntoGridPane(left1KeySel, 1, 4);
        addItemIntoGridPane(hyperjump1KeySel, 1, 5);
        addItemIntoGridPane(smartbomb1KeySel, 1, 6);
    }

    private void addPlayer2KeysIntoGridPane(){
        addItemIntoGridPane(up2KeySel, 2, 1);
        addItemIntoGridPane(down2KeySel, 2, 2);
        addItemIntoGridPane(right2KeySel, 2, 3);
        addItemIntoGridPane(left2KeySel, 2, 4);
        addItemIntoGridPane(hyperjump2KeySel, 2, 5);
        addItemIntoGridPane(smartbomb2KeySel, 2, 6);
    }

    private void addItemIntoGridPane(Node item, int column, int row){
        if(item != null)
            gridPane.add(item, column, row);
    }


   public FiyuvButton getSaveButton(){
       return bottomMenu.getButton2();
   }

   public FiyuvButton getMenuButton(){
       return bottomMenu.getButton1();
   }


   public double getVolume(){
        return volumeSlider.getValue();
   }

}
