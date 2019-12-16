package org.openjfx.view.menuView;

import javafx.scene.layout.BorderPane;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;

public class Credits extends BorderPane {

    FiyuvBottomMenu bottomMenu;

    public Credits(){
        bottomMenu = new FiyuvBottomMenu("Menu");
        this.getChildren().add(bottomMenu);
    }

    public FiyuvButton getMenuButton(){
        return bottomMenu.getButton1();
    }

}
