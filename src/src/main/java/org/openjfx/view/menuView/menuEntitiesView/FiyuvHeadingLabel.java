package org.openjfx.view.menuView.menuEntitiesView;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class FiyuvHeadingLabel extends Label {
    public FiyuvHeadingLabel(String text){
       // setPrefHeight(400);
        //setPrefWidth(600);
        setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        setFont(Font.font("Blue", 23));
    }
}
