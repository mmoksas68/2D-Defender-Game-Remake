package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.openjfx.model.menuEntities.HighScore;
import org.openjfx.model.menuEntities.HighScoreInfo;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvHeadingLabel;

public class HighScoresView extends VBox {

    TableView<HighScore> table1, table2, table3, table4, table5, table6;
    TableColumn<HighScore, Integer> numberColumn;
    TableColumn<HighScore, Integer> scoreColumn1;
    TableColumn<HighScore, Integer> numberColumn2, numberColumn3, numberColumn4, numberColumn5, numberColumn6;
    TableColumn<HighScore, Integer> scoreColumn2, scoreColumn3, scoreColumn4, scoreColumn5, scoreColumn6;
    HighScoreInfo highScores;
    private TabPane tabPane;
    private Tab level1, level2, level3;

    private FiyuvButton menuButton;

    private FiyuvHeadingLabel heading;

    public HighScoresView(){

        highScores = HighScoreInfo.getInstance();

        createColumns();
        createTables();

        menuButton = new FiyuvButton("Menu");
        tabPane = new TabPane();
        level1 = new Tab("Level 1 Scores");
        level2 = new Tab("Level 2 Scores");
        level3 = new Tab("Level 3 Scores");


        heading = new FiyuvHeadingLabel("High Scores");
        this.getChildren().add(heading);
        heading.setAlignment(Pos.CENTER);

        HBox hBoxLevel1 = new HBox();
        HBox hBoxLevel2 = new HBox();
        HBox hBoxLevel3 = new HBox();

        hBoxLevel1.getChildren().addAll(table1, table2);
       // hBoxLevel1.setSpacing(50);

        hBoxLevel2.getChildren().addAll(table3, table4);
     //   hBoxLevel2.setSpacing(200);

        hBoxLevel3.getChildren().addAll(table5, table6);
       // hBoxLevel3.setSpacing(200);


        tabPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        tabPane.setMaxSize(500, 500);



        level1.setContent(hBoxLevel1);
        level2.setContent(hBoxLevel2);
        level3.setContent(hBoxLevel3);

        tabPane.getTabs().addAll(level1, level2, level3);

        //this.getChildren().addAll(tabPane, bottomMenu, heading);

        this.getChildren().add(tabPane);

        this.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));

        HBox hbox = new HBox();
        hbox.getChildren().add(menuButton);
        menuButton.setAlignment(Pos.BOTTOM_LEFT);

        this.getChildren().add(hbox);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(100);
        this.getStyleClass().add("menu-root");

        //tabPane.getStyleClass().add("Style1.css");

    }



   private void createColumns(){
        numberColumn = new TableColumn<HighScore, Integer>("#");
        scoreColumn1 = new TableColumn<HighScore, Integer>("One Player Game Score");
        numberColumn2 = new TableColumn<HighScore, Integer>("#");
        scoreColumn2 = new TableColumn<HighScore, Integer>("Two Player Game Score");

        numberColumn.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("ranking"));
        numberColumn2.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("ranking"));
        scoreColumn1.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));
        scoreColumn2.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));

       numberColumn3 = new TableColumn<HighScore, Integer>("#");
       scoreColumn3 = new TableColumn<HighScore, Integer>("One Player Game Score");
       numberColumn4 = new TableColumn<HighScore, Integer>("#");
       scoreColumn4 = new TableColumn<HighScore, Integer>("Two Player Game Score");

       numberColumn3.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("ranking"));
       numberColumn4.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("ranking"));
       scoreColumn3.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));
       scoreColumn4.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));

       numberColumn5 = new TableColumn<HighScore, Integer>("#");
       scoreColumn5 = new TableColumn<HighScore, Integer>("One Player Game Score");
       numberColumn6 = new TableColumn<HighScore, Integer>("#");
       scoreColumn6 = new TableColumn<HighScore, Integer>("Two Player Game Score");

       numberColumn5.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("ranking"));
       numberColumn6.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("ranking"));
       scoreColumn5.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));
       scoreColumn6.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));

    }

    private void createTables(){
        table1 = new TableView<HighScore>(highScores.getLevel1Scores()[0]);
        table2 = new TableView<HighScore>(highScores.getLevel1Scores()[1]);
        table3 = new TableView<>(highScores.getLevel2Scores()[0]);
        table4 = new TableView<>(highScores.getLevel2Scores()[1]);
        table5 = new TableView<>(highScores.getLevel3Scores()[0]);
        table6 = new TableView<>(highScores.getLevel3Scores()[1]);

        table1.getColumns().addAll(numberColumn, scoreColumn1);
        table2.getColumns().addAll(numberColumn2, scoreColumn2);
/*
        table1.setPrefHeight(200);
        table2.setPrefHeight(200);
        table1.setPrefWidth(200);
        table2.setPrefWidth(200);

*/

        table3.getColumns().addAll(numberColumn3, scoreColumn3);
        table4.getColumns().addAll(numberColumn4, scoreColumn4);

        table5.getColumns().addAll(numberColumn5, scoreColumn5);
        table6.getColumns().addAll(numberColumn6, scoreColumn6);


    }

    public Button getMenuButton(){
        return menuButton;
    }


}