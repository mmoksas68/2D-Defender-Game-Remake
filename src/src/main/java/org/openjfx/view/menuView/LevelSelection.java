package org.openjfx.view.menuView;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import org.openjfx.assetManager.Assets;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;

import java.util.ArrayList;

public class LevelSelection extends VBox {
    private Assets assets = Assets.getInstance();
    private final ToggleGroup images = new ToggleGroup();
    private RadioButton image1, image2, image3;
    private HBox radioButtons, hBoxImages, hBoxButtons;
    // private ImageView imageViews[] = (imageManager).getSpaceCraftView();
    private FiyuvButton nextButton, backButton;
    //private SelectionView selectionView;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    ArrayList<String> buttonNames;

    private HBox hBox;
    FiyuvBottomMenu bottomMenu;

    public LevelSelection(){
        //selectionView = new SelectionView(imageViews, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        bottomMenu = new FiyuvBottomMenu("Back", "Start");
        this.getChildren().add(bottomMenu);
        //this.getChildren().addAll(selectionView, fiyuvButtonHBox);
    }

    //public ImageView getSelectedImage(){
    //return selectionView.getSelectedImage();
    //}

    public FiyuvButton getNextButton(){
        return bottomMenu.getButton2();
    }

    public FiyuvButton getBackButton(){
        return bottomMenu.getButton1();
    }
}
