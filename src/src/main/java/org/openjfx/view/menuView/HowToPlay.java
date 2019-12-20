package org.openjfx.view.menuView;

import javafx.scene.layout.BorderPane;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;

public class HowToPlay extends BorderPane {

    FiyuvButton button;

    public HowToPlay(){
        button = new FiyuvButton("Menu");
        this.setBottom(button);
        this.getStyleClass().add("menu-root");
    }


    public FiyuvButton getMenuButton(){
        return button;
    }

}
