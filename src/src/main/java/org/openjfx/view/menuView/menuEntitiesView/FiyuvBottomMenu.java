package org.openjfx.view.menuView.menuEntitiesView;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class FiyuvBottomMenu extends HBox {
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private String buttonName1, buttonName2;
    private FiyuvButton button1, button2;
    public FiyuvBottomMenu(String buttonName1, String buttonName2){
        double width = primaryScreenBounds.getWidth();
        this.buttonName1 = buttonName1;
        this.buttonName2 = buttonName2;
        createButtons();
        this.setAlignment(Pos.CENTER);
        this.setSpacing( (width - button1.getSize()*2 )* 7 / 12);
    }

    private void createButtons(){
        button1 = new FiyuvButton(buttonName1);
        button2 = new FiyuvButton(buttonName2);

        this.getChildren().addAll(button1, button2);
    }


    public FiyuvButton getButton1(){
        return button1;
    }

    public FiyuvButton getButton2(){
        return button2;
    }
}
