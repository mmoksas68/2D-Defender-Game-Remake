package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.openjfx.assetManager.Assets;
import org.openjfx.model.menuEntities.Settings;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;

public class HowToPlay extends BorderPane {

    private FiyuvButton button;
    private ImageView image;

    public HowToPlay(){
        button = new FiyuvButton("Menu");
        this.setBottom(button);
        this.getStyleClass().add("menu-root");

        button.setAlignment(Pos.BOTTOM_LEFT);

        setImage();

        image.setFitWidth(1000);

        //this.setCenter(image);

        this.setPadding(new Insets(40,40,40,40));

    }


    public FiyuvButton getMenuButton(){
        return button;
    }

    public void setImage(){
        if(Settings.getInstance().getTheme() == 2) {
            image = new ImageView(Assets.getInstance().getMenuAssets().getHowtoPlay());
            this.setCenter(image);
        }
        else {
            image = new ImageView(Assets.getInstance().getMenuAssets().getHowtoPlay2());
            this.setCenter(image);
        }
    }

}
