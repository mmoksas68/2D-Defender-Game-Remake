package org.openjfx.view.menuView.menuEntitiesView;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class FiyuvHeadingLabel extends Label {

    public FiyuvHeadingLabel(String text) {
        setPrefHeight(100);
        setPrefWidth(800);

        setAlignment(Pos.CENTER);
        ///setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        this.getStyleClass().add("heading-label");
    }
}
