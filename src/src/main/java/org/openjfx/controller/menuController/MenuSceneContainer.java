package org.openjfx.controller.menuController;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.stage.Screen;
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
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    public MenuSceneContainer(){
        mainMenu = new MainMenu();
        highScoresView = new HighScoresView();
        credits = new Credits();
        howToPlay = new HowToPlay();
        settings = new SettingsView(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        spacecraftSelection1 = new SpacecraftSelection(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight(), "Spacecraft Selection 1");
        spacecraftSelection2 = new SpacecraftSelection(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight(), "Spacecraft Selection 2");
        levelSelection = new LevelSelection(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
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
