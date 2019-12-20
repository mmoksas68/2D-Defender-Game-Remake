package org.openjfx.view.menuView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.menuEntities.Settings;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvHeadingLabel;
import org.openjfx.view.menuView.menuEntitiesView.KeyTextField;
import java.util.ArrayList;
import java.util.List;

public class SettingsView extends VBox {

    private List<Image> images = Assets.getInstance().getMenuAssets().getColorOptions();
    private Slider volumeSlider;
    private Label player1Label, player2Label;
    private GridPane gridPane;
    private Label upLabel, downLabel, rightLabel, leftLabel, hyperjumpLabel, smartbombLabel, fireLabel;
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
    private KeyTextField[] player1Keys, player2Keys;
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
        this.getStyleClass().add("menu-root");

    }

    private void createSelectionView(){

        radioButtons = new HBox();
        hBoxImages = new HBox();

        group = new ToggleGroup();

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
        fireLabel = new Label("FIRE: ");
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
        addItemIntoGridPane(fireLabel, 0, 5);
        addItemIntoGridPane(hyperjumpLabel, 0 , 6);
        addItemIntoGridPane(smartbombLabel, 0, 7);
    }

    private void createTextFieldsForPlayer1Keys() {
        player1Keys = new KeyTextField[7];
        player1Keys[0] = new KeyTextField(settings.getUp());
        player1Keys[1] = new KeyTextField(settings.getDown());
        player1Keys[2] = new KeyTextField(settings.getRight());
        player1Keys[3] = new KeyTextField(settings.getLeft());
        player1Keys[4] = new KeyTextField(settings.getFire());
        player1Keys[5] = new KeyTextField(settings.getHyperJump());
        player1Keys[6] = new KeyTextField(settings.getSmartBomb());
    }
    private void createTextFieldsForPlayer2Keys(){
        player2Keys = new KeyTextField[7];
        player2Keys[0] = new KeyTextField(settings.getUp2());
        player2Keys[1] = new KeyTextField(settings.getDown2());
        player2Keys[2]  = new KeyTextField(settings.getRight2());
        player2Keys[3] = new KeyTextField(settings.getLeft2());
        player2Keys[4] = new KeyTextField(settings.getFire2());
        player2Keys[5]  = new KeyTextField(settings.getHyperJump2());
        player2Keys[6] = new KeyTextField(settings.getSmartBomb2());

    }


    private void addPlayer1KeysIntoGridPane(){
        addItemIntoGridPane(player1Keys[0], 1, 1);
        addItemIntoGridPane(player1Keys[1], 1, 2);
        addItemIntoGridPane(player1Keys[2], 1, 3);
        addItemIntoGridPane(player1Keys[3], 1, 4);
        addItemIntoGridPane(player1Keys[4], 1, 5);
        addItemIntoGridPane(player1Keys[5], 1, 6);
        addItemIntoGridPane(player1Keys[6], 1, 7);
    }

    private void addPlayer2KeysIntoGridPane(){
        addItemIntoGridPane(player2Keys[0], 2, 1);
        addItemIntoGridPane(player2Keys[1], 2, 2);
        addItemIntoGridPane(player2Keys[2], 2, 3);
        addItemIntoGridPane(player2Keys[3], 2, 4);
        addItemIntoGridPane(player2Keys[4], 2, 5);
        addItemIntoGridPane(player2Keys[5], 2, 6);
        addItemIntoGridPane(player2Keys[6], 2, 7);
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

   public KeyCode getUp(){
        return player1Keys[0].getKeyCode();
   }

   public KeyCode getDown(){
       return player1Keys[1].getKeyCode();
   }

   public KeyCode getRight(){
       return player1Keys[2].getKeyCode();
   }

   public KeyCode getLeft(){
       return player1Keys[3].getKeyCode();
   }

    public KeyCode getFire(){
        return player1Keys[4].getKeyCode();
    }


   public KeyCode getHyperJump(){
       return player1Keys[5].getKeyCode();
   }

   public KeyCode getSmartBomb(){
       return player1Keys[6].getKeyCode();
   }

    public KeyCode getUp2(){
        return player2Keys[0].getKeyCode();
    }

    public KeyCode getDown2(){
        return player2Keys[1].getKeyCode();
    }

    public KeyCode getRight2(){
        return player2Keys[2].getKeyCode();
    }

    public KeyCode getLeft2(){
        return player2Keys[3].getKeyCode();
    }

    public KeyCode getFire2(){
        return player2Keys[4].getKeyCode();
    }

    public KeyCode getHyperJump2(){
        return player2Keys[5].getKeyCode();
    }

    public KeyCode getSmartBomb2(){
        return player2Keys[6].getKeyCode();
    }


   public double getVolume(){
        return volumeSlider.getValue();
   }

   public int getSelectedTheme(){
        if(selection1Button.isSelected())
            return 0;
        else if(selection2Button.isSelected())
            return 1;
        else
            return 2;
   }


}
