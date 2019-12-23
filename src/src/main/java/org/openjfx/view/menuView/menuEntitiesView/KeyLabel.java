package org.openjfx.view.menuView.menuEntitiesView;

import javafx.scene.control.Label;

public class KeyLabel extends Label {
    public KeyLabel(String text){
        setText(text);
        this.getStyleClass().add("key-label");
    }


}
