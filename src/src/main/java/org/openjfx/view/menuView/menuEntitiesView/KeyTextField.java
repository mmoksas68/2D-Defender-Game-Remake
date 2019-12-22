package org.openjfx.view.menuView.menuEntitiesView;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class KeyTextField extends TextField {
    private KeyCode keyCode;
    public KeyTextField(KeyCode keyCode) {
        setPrefWidth(100);
        setPrefHeight(40);
        this.keyCode = keyCode;
        this.setText(keyCode.toString());
        createListener();
        this.getStyleClass().add("key-text-field");
    }

    private void createListener() {

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(e.getCode() == KeyCode.BACK_SPACE) {
                    setText(getText());
                    editableProperty().set(false);
                }

                else if (e.getCode() == KeyCode.ESCAPE) {
                    setText(getText());
                    editableProperty().setValue(false);
                }

                else if(e.getCode() == KeyCode.UNDEFINED){
                    setText(getText());
                    editableProperty().setValue(false);
                }

                else if(e.getCode().isLetterKey()){
                    setText(e.getCode().getChar().toUpperCase());
                    keyCode = e.getCode();
                    editableProperty().setValue(false);
                }

                else {
                    keyCode = e.getCode();
                    setText(e.getCode().toString());
                    editableProperty().setValue(false);
                }
            }

        });
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
        this.setText(keyCode.toString());
    }

}
