package org.openjfx.view.menuView;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends VBox {

    private final static int MENU_BTN_START_X = 750;
    private final static int MENU_BTN_START_Y = 50;

    private List<FiyuvButton> menuButtons;

    //Variables
    private FiyuvButton singlePlayerBtn;
    private FiyuvButton twoPlayersBtn;
    private FiyuvButton howToPlayBtn;
    private FiyuvButton settingsBtn;
    private FiyuvButton creditsBtn;
    private FiyuvButton exitBtn;

    public MainMenu(){
        menuButtons = new ArrayList<>();
        setAlignment(Pos.CENTER);
        createButtons();
    }

    private void createButtons(){
        createSinglePlayerBtn();
        createTwoPlayersBtn();
        createHowToPlayBtn();
        createSettingsBtn();
        createCreditsBtn();
        createExitBtn();
    }

    private void addMenuButton(FiyuvButton btn){
        btn.setLayoutX(MENU_BTN_START_X);
        btn.setLayoutY(MENU_BTN_START_Y + menuButtons.size() * 100);
        menuButtons.add(btn);
        getChildren().add(btn);
    }

    private void createSinglePlayerBtn(){
        singlePlayerBtn = new FiyuvButton("Single");
        addMenuButton(singlePlayerBtn);
    }

    private void createTwoPlayersBtn(){
        twoPlayersBtn = new FiyuvButton("TwoPlayer");
        addMenuButton(twoPlayersBtn);
    }

    private void createHowToPlayBtn(){
        howToPlayBtn = new FiyuvButton("HowtoPlay");
        addMenuButton(howToPlayBtn);
    }

    private void createSettingsBtn(){
        settingsBtn = new FiyuvButton("Settings");
        addMenuButton(settingsBtn);
    }

    private void createCreditsBtn(){
        creditsBtn = new FiyuvButton("Credits");
        addMenuButton(creditsBtn);
    }

    private void createExitBtn(){
        exitBtn = new FiyuvButton("Exit");
        addMenuButton(exitBtn);
    }

    public FiyuvButton getSinglePlayerBtn(){
        return singlePlayerBtn;
    }

    public FiyuvButton getTwoPlayersBtn(){
        return twoPlayersBtn;
    }

    public FiyuvButton getHowToPlayBtn(){
        return howToPlayBtn;
    }

    public FiyuvButton getSettingsBtn(){
        return settingsBtn;
    }

    public FiyuvButton getCreditsBtn(){
        return creditsBtn;
    }

    public FiyuvButton getExitBtn(){
        return exitBtn;
    }
}
