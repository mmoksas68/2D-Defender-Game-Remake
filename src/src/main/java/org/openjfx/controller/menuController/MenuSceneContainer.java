package org.openjfx.controller.menuController;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.menuEntities.HighScore;
import org.openjfx.model.menuEntities.HighScoreInfo;
import org.openjfx.view.menuView.*;

public class MenuSceneContainer {
    private MainMenu mainMenu;
    private HighScoresView highScoresView;
    private Credits credits;
    private HowToPlay howToPlay;
    private SettingsView settings;
    private SpacecraftSelection spacecraftSelection1, spacecraftSelection2;
    private LevelSelection levelSelection;

    public MenuSceneContainer(){
        mainMenu = new MainMenu();
        highScoresView = new HighScoresView();
        credits = new Credits();
        howToPlay = new HowToPlay();
        settings = new SettingsView();
        spacecraftSelection1 = new SpacecraftSelection();
        spacecraftSelection2 = new SpacecraftSelection();
        levelSelection = new LevelSelection();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Credits getCredits() {
        return credits;
    }


    public HighScoresView getHighScoresView() {
        return highScoresView;
    }

    public HowToPlay getHowToPlay() {
        return howToPlay;
    }

    public SettingsView getSettings() {
        return settings;
    }

    public SpacecraftSelection getSpacecraftSelection1() {
        return spacecraftSelection1;
    }
    public SpacecraftSelection getSpacecraftSelection2(){
        return spacecraftSelection2;
    }
    public LevelSelection getLevelSelection(){
        return levelSelection;
    }
}
