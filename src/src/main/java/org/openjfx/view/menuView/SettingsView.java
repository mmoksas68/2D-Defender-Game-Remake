package org.openjfx.view.menuView;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvHeadingLabel;

public class SettingsView extends VBox {
    private Slider volumeSlider;
    private Label player1Label, player2Label;
    private GridPane gridPane;
    private TextField up1KeySel, down1KeySel, right1KeySel, left1KeySel,
            hyperjump1KeySel, smartbomb1KeySel;
    private TextField up2KeySel, down2KeySel, right2KeySel, left2KeySel,
            hyperjump2KeySel, smartbomb2KeySel;

    private Label settingsLabel;
    private Label upLabel, downLabel, rightLabel, leftLabel, hyperjumpLabel, smartbombLabel;
    ImageView[] imageViews;
   // private SelectionView selectionView;
    private FiyuvBottomMenu hBox;
    private VBox vbox;
    private TextField player1Inputs[], player2Inputs[];

    public SettingsView(){
        imageViews = new ImageView[3];
        imageViews[0] = new ImageView(new Image("file:assets/images/colors/color1.jpg"));
        imageViews[1] = new ImageView(new Image("file:assets/images/colors/color2.png"));
        imageViews[2] = new ImageView(new Image("file:assets/images/colors/color3.jpg"));
        //this.setAlignment(Pos.CENTER);
        createSettingsLabel();
        createGridPane();
        createVolumeSlider();
        createButtonHBox();
        vbox = new VBox();
        //selectionView = new SelectionView(imageViews, 1200, 100);

        vbox.getChildren().add(volumeSlider);
        //vbox.getChildren().addAll(volumeSlider, selectionView);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(gridPane, vbox);
        this.getChildren().addAll(settingsLabel, hbox2, hBox);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(1200, 200);

        this.setSpacing(50);
    }

    private void createButtonHBox(){
        hBox = new FiyuvBottomMenu("Menu", "Save");


        //createButtons();
        //hBox.getChildren().addAll(backButton, saveButton);
    }

    private void createVolumeSlider(){
        volumeSlider = new Slider(0, 100, 50);
    }

    private void createSettingsLabel(){
        settingsLabel = new FiyuvHeadingLabel("SETTINGS");
        //    settingsLabel.setFont(new Font("Arial Bold", 85));
        //  settingsLabel.textFillProperty();
        //settingsLabel.setPrefSize(800,200);
        // settingsLabel.setAlignment(Pos.CENTER);
        // settingsLabel.setTextFill(Color.WHITE);
        //settingsLabel.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void createGridPane(){
        gridPane = new GridPane();
        createLabelsForGridPane();
        createTextFieldsForPlayer1Keys();
        createTextFieldsForPlayer2Keys();
        addLabelsIntoGridPane();
        addPlayer1KeysIntoGridPane();
        addPlayer2KeysIntoGridPane();
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

   /* public void createHBoxForColorOptions(){

    }

    public void createHBoxForRadioButtons(){

    }

    public void createRadioButtons(){
        radioButton1 = new RadioButton();
        radioButton2 = new RadioButton();
        radioButton3 = new RadioButton();
    }

    public addRadioButtonsIntoHBox(){
    }
*/
   public FiyuvButton getSaveButton(){
       return hBox.getButton2();
   }

   public FiyuvButton getMenuButton(){
       return hBox.getButton1();
   }

}
