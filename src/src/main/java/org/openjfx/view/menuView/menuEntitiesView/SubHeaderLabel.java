package org.openjfx.view.menuView.menuEntitiesView;

import javafx.scene.control.Label;

public class SubHeaderLabel extends Label {
    public SubHeaderLabel(String text){
        setText(text);
        getStyleClass().add("sub-header");
    }

}
