package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonView extends Button {

    public ButtonView (String name, double x, double y, EventHandler event) {
        super(name);
        setPrefHeight( 50);
        setPrefWidth( 150);
        setLayoutX( x);
        setLayoutY( y);
        setOnAction( event);
    }
}
