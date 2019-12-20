package org.openjfx.view.menuView.menuEntitiesView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FiyuvHeadingLabel extends Label {

    //private final String FONT_PATH = "assets/userInterfaceResources/kenvector_future.ttf";
    private final String FONT_PATH = "assets/userInterfaceResources/font.ttf";
    private Background background;
    public FiyuvHeadingLabel(String text, double width, double height){
        setPrefHeight(height);
        setPrefWidth(width);
        setText(text);
        setWrapText(true);
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 45));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 45));
        }
        this.getStyleClass().add("heading-label");
    }

    public FiyuvHeadingLabel(String text){
        setPrefHeight(100);
        setPrefWidth(800);

        setAlignment(Pos.CENTER);
        ///setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);

       try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 45));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 45));
       }
        this.getStyleClass().add("heading-label");
    }
}
