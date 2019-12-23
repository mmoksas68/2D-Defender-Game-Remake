package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.openjfx.assetManager.Assets;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;

public class Credits extends BorderPane{

    private FiyuvButton backButton;
    private ImageView image;

    public Credits(){
        backButton = new FiyuvButton("Menu");
        this.setBottom(backButton);
        backButton.setAlignment(Pos.BOTTOM_LEFT);
        image = new ImageView(Assets.getInstance().getMenuAssets().getCredits());

        image.setFitWidth(500);
        image.setFitWidth(800);

        this.setCenter(image);

        this.setPadding(new Insets(40,40,40,40));
        this.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));


        this.getStyleClass().add("menu-root");
    }

    public FiyuvButton getMenuButton(){
        return backButton;
    }

}
