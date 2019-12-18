package org.openjfx.view.menuView.menuEntitiesView;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

import java.util.ArrayList;

public class FiyuvBottomMenu extends HBox {
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private String buttonName1, buttonName2;
    private FiyuvButton button1, button2;
    private ArrayList<FiyuvButton> buttonList;
    public FiyuvBottomMenu(String ...buttonName){
        double width = primaryScreenBounds.getWidth();
        buttonList = new ArrayList<FiyuvButton>();
        createButtons(buttonName);

        this.setAlignment(Pos.CENTER);
        this.setSpacing( (width - buttonList.get(0).getSize()*2 )* 7 / 12);
    }

    private void createButtons(String ...buttonName){
        for(String button:buttonName){
            buttonList.add(new FiyuvButton(button));
        }
        addButtonsIntoHBox();
    }

    public void addButtonsIntoHBox(){
        for(FiyuvButton button:buttonList){
            this.getChildren().add(button);
        }
    }

    public FiyuvButton getButton1(){
        return buttonList.get(0);
    }

    public FiyuvButton getButton2(){
        return buttonList.get(1);
    }
}
