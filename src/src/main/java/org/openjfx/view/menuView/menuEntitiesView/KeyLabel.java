package org.openjfx.view.menuView.menuEntitiesView;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class KeyLabel extends Label {
    public KeyLabel(String text){
        setText(text);
        this.getStyleClass().add("key-label");
    }


}
