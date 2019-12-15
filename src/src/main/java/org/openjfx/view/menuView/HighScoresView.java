package org.openjfx.view.menuView;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
//import org.openjfx.view.menuEntitiesView.FiyuvHeadingLabel;

public class HighScoresView extends BorderPane {

   /* TableView<HighScore> table1, table2, table3, table4;
    TableColumn<HighScore, Integer> numberColumn;
    TableColumn<HighScore, Integer> scoreColumn1;
    TableColumn<HighScore, Integer> numberColumn2;
    TableColumn<HighScore, Integer> scoreColumn2;
    //HighScores highScores;
    private TabPane tabPane;
    private Tab level1, level2, level3;
*/
   // private FiyuvHeadingLabel heading;

    //public HighScoreView() {
       // highScores = new HighScores();

     /*   createColumns();
        createTables();

       // tabPane = new TabPane();
      //  level1 = new Tab("Level 1 Scores");
        //level2 = new Tab("Level 2 Scores");
        //level3 = new Tab("Level 3 Scores");

    //    heading = new FiyuvHeadingLabel("High Scores");
        //this.setTop(heading);
        VBox vBox = new VBox();
        VBox vBox2 = new VBox();
        HBox hBox = new HBox();

        hBox.getChildren().addAll(table1, table2);
        hBox.setSpacing(200);

      //  level1.setContent(hBox);
        //level2.setContent(table2);

        //tabPane.getTabs().addAll(level1, level2, level3);

        //this.setCenter(tabPane);

        //this.getChildren().addAll(table1, table2);

    }
    */


   /* public void createColumns(){
        numberColumn = new TableColumn<HighScore, Integer>("#");
        scoreColumn1 = new TableColumn<HighScore, Integer>("One Player Game Score");
        numberColumn2 = new TableColumn<HighScore, Integer>("#");
        scoreColumn2 = new TableColumn<HighScore, Integer>("Two Player Game Score");

        numberColumn.setMinWidth(200);
        scoreColumn1.setMinWidth(200);
        scoreColumn2.setMinWidth(200);

        numberColumn.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("number"));
        numberColumn2.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("number"));
        scoreColumn1.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));
        scoreColumn2.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));

    }

    private void createTables(){
        table1 = new TableView<>(highScores.getOnePlayLevel1());
        table2 = new TableView<>(highScores.getOnePlayLevel2());
        table1.getColumns().addAll(numberColumn, scoreColumn1);
        table2.getColumns().addAll(numberColumn2, scoreColumn2);
    }

    private void addColumnIntoTable(TableColumn column, TableView<HighScore> table){
        table.getColumns().add(column);
    }

*/

}