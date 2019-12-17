package org.openjfx.view.menuView;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.openjfx.model.menuEntities.HighScore;
import org.openjfx.model.menuEntities.HighScoreInfo;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvBottomMenu;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvButton;
import org.openjfx.view.menuView.menuEntitiesView.FiyuvHeadingLabel;
//import org.openjfx.view.menuEntitiesView.FiyuvHeadingLabel;

public class HighScoresView extends BorderPane {

    TableView<HighScore> table1, table2, table3, table4, table5, table6;
    TableColumn<HighScore, Integer> numberColumn;
    TableColumn<HighScore, Integer> scoreColumn1;
    TableColumn<HighScore, Integer> numberColumn2;
    TableColumn<HighScore, Integer> scoreColumn2;
    HighScoreInfo highScores;
    private TabPane tabPane;
    private Tab level1, level2, level3;

    private FiyuvBottomMenu bottomMenu;

    private FiyuvHeadingLabel heading;

    public HighScoresView(){

        this.getStylesheets().add("Style1.css");
        highScores = HighScoreInfo.getInstance();

        createColumns();
        createTables();
        createBottomMenu();

        tabPane = new TabPane();
        level1 = new Tab("Level 1 Scores");
        level2 = new Tab("Level 2 Scores");
        level3 = new Tab("Level 3 Scores");


        heading = new FiyuvHeadingLabel("High Scores");
        this.setTop(heading);
        VBox vBox = new VBox();
        VBox vBox2 = new VBox();

        HBox hBoxLevel1 = new HBox();
        HBox hBoxLevel2 = new HBox();
        HBox hBoxLevel3 = new HBox();

        hBoxLevel1.getChildren().addAll(table1, table2);
       // hBoxLevel1.setSpacing(50);

        hBoxLevel2.getChildren().addAll(table3, table4);
     //   hBoxLevel2.setSpacing(200);

        hBoxLevel3.getChildren().addAll(table5, table6);
       // hBoxLevel3.setSpacing(200);


        tabPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        tabPane.setMaxSize(500, 500);



        level1.setContent(hBoxLevel1);
        level2.setContent(hBoxLevel2);
        level3.setContent(hBoxLevel3);

        tabPane.getTabs().addAll(level1, level2, level3);

        //this.getChildren().addAll(tabPane, bottomMenu, heading);

        this.setCenter(tabPane);

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        this.setBottom(bottomMenu);

        //tabPane.getStyleClass().add("Style1.css");

    }

    private void createBottomMenu(){
        bottomMenu = new FiyuvBottomMenu("Back");
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

        numberColumn.setStyle("-fx-background-color: BLACK");
        table3.getColumns().addAll(numberColumn, scoreColumn1);
        table4.getColumns().addAll(numberColumn2, scoreColumn2);

        table5.getColumns().addAll(numberColumn, scoreColumn1);
        table6.getColumns().addAll(numberColumn2, scoreColumn2);


    }

    public FiyuvButton getMenuButton(){
        return bottomMenu.getButton1();
    }


    private void addColumnIntoTable(TableColumn column, TableView<HighScore> table){
        table.getColumns().add(column);
    }

}