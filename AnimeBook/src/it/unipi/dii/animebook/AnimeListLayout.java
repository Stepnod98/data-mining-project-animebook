/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Stefano
 */
public class AnimeListLayout {
    private Label add;
    private static TextField animeToAdd;
    protected Button addAnime;
    private Label remove;
    private static TextField animeToRemove;
    protected Button removeAnime;
    private Label view;
    private static TextField rating;
    protected Button viewAnimeList;
    private TableView table;
    private ObservableList<AnimeListRow> observableList;
    private VBox vbox;
    private Label result;
    protected Button back;
    public AnimeListLayout(){
        /*add = new Label("Add to List:");
        add.setLayoutX(50);
        add.setLayoutY(40);
        animeToAdd = new TextField();
        animeToAdd.setLayoutX(150);
        animeToAdd.setLayoutY(40);
        animeToAdd.setFocusTraversable(false);
        animeToAdd.setMaxWidth(200);
        rating = new TextField("Insert Rating");
        rating.setLayoutX(320);
        rating.setLayoutY(40);
        rating.setFocusTraversable(false);
        rating.setMaxWidth(100);
        addAnime = new Button("ADD");
    	addAnime.setLayoutY(40);
    	addAnime.setLayoutX(450);
    	addAnime.setMaxWidth(300);
        remove = new Label("Remove from list:");
        remove.setLayoutX(50);
        remove.setLayoutY(80);
        animeToRemove = new TextField();
        animeToRemove.setLayoutX(150);
        animeToRemove.setLayoutY(80);
        animeToRemove.setFocusTraversable(false);
        animeToRemove.setMaxWidth(200);
        removeAnime = new Button("REMOVE");
    	removeAnime.setLayoutY(80);
    	removeAnime.setLayoutX(320);
    	removeAnime.setMaxWidth(300);
        view = new Label("View Anime List");
        view.setLayoutX(80);
        view.setLayoutY(120);
        viewAnimeList = new Button("VIEW");
    	viewAnimeList.setLayoutY(120);
    	viewAnimeList.setLayoutX(180);
    	viewAnimeList.setMaxWidth(300);*/
        table = new TableView();
        TableColumn columnAnime = new TableColumn("Title");
        TableColumn columnScore = new TableColumn("Score");
        TableColumn columnRemove = new TableColumn();
        TableColumn columnUpdateScore = new TableColumn("Update Score");
        TableColumn columnUpdate = new TableColumn();
        table.getColumns().addAll(columnAnime, columnScore, columnRemove, columnUpdateScore, columnUpdate);
        List<AnimeListRow> anilist = new ArrayList<>();
        //List<AnimeListElem> l = MongoDBManager.getAnimeList();
        List<AnimeListElem> l = new ArrayList<>();
        l.add(new AnimeListElem("app1",1));
        l.add(new AnimeListElem("app2",2));
        l.add(new AnimeListElem("app3",3));
        l.add(new AnimeListElem("app4",4));
        l.add(new AnimeListElem("app5",5));
        for(int i = 0; i < l.size(); i++){
            anilist.add(new AnimeListRow(l.get(i).getTitle(), l.get(i).getScore()));
        }
        observableList = FXCollections.observableArrayList(anilist);
        columnAnime.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        columnScore.setCellValueFactory(
                new PropertyValueFactory<>("score")
        );
        columnRemove.setCellValueFactory(
                new PropertyValueFactory<>("remove")
        );
        columnUpdateScore.setCellValueFactory(
                new PropertyValueFactory<>("updateScore")
        );
        columnUpdate.setCellValueFactory(
                new PropertyValueFactory<>("update")
        );
        
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox = new VBox();
        vbox.setLayoutY(40);
        vbox.setLayoutX(40);
        vbox.setFillWidth(true);
        vbox.setMaxHeight(450);
        vbox.setMinWidth(600);
        vbox.getChildren().addAll(table);
         /*
        findDeckRecTable = new TableView();

        TableColumn column = new TableColumn("Deck title");
        TableColumn columnLike = new TableColumn("Action");

        findDeckRecTable.getColumns().addAll(column, columnLike);

        List<Row> recResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            recResult.add(new Row(list.get(i)));
        }

        observableList = FXCollections.observableArrayList(recResult);

        column.setCellValueFactory(
                new PropertyValueFactory<Row,String>("info")
        );

        columnLike.setCellValueFactory(
                new PropertyValueFactory<Row,String>("button")
        );


        findDeckRecTable.setItems(observableList);
        findDeckRecTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        visualize = new VBox();
        visualize.setLayoutY(300);
        visualize.setLayoutX(20);
        visualize.setFillWidth(true);
        visualize.setMaxHeight(182);
        visualize.getChildren().addAll(findDeckRecTable);

        */
        /*observableList.addAll(new AnimeListElem("app1",1),
                                new AnimeListElem("app2", 2), 
                                new AnimeListElem("app3", 3), 
                                new AnimeListElem("app4", 4),
                                new AnimeListElem("app5", 5));
        table.setItems(observableList);

        TableColumn<AnimeListElem, String> colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        TableColumn<AnimeListElem, Integer> colScore = new TableColumn<>("Score");
        colScore.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.getColumns().addAll(colTitle, colScore);

        //addButtonToTable();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox = new VBox();
        vbox.setLayoutY(40);
        vbox.setLayoutX(40);
        vbox.setMaxHeight(280);
        vbox.getChildren().addAll(table);
        */
       
        
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
    }
    
   /* public Node[] getNodes() {
    	Node[] returnNode = { add, animeToAdd, addAnime, remove, animeToRemove, removeAnime, 
                            view, rating, viewAnimeList, back};
    	return returnNode;
    }*/
    public Node[] getNodes() {
    	Node[] returnNode = { vbox, back};
    	return returnNode;
    }
    
    public TableView getTable(){
        return table;
    }
    
    public Button getBack(){
        return back;
    }
    
    public static String getAnimeToAdd(){
        return animeToAdd.getText();
    }
    
    public void clearLayout(){
        vbox.getChildren().clear();
        table.getColumns().clear();
    }
    
    
    
    
    /*public void showResults(List<String> list){
        TableColumn<String, String> column = new TableColumn("AnimeList");
        column.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue()));
        table.getColumns().add(column);
        observableList = FXCollections.observableArrayList(list);	
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox = new VBox();
        vbox.setLayoutY(240);
        vbox.setLayoutX(60);
        vbox.setMaxHeight(148);
        vbox.getChildren().addAll(table);
    }
    
    public void showDeckResults(List<String> list){
        TableColumn<String, String> column = new TableColumn("Deck Title");
        column.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue()));
        table.getColumns().add(column);
        observableList = FXCollections.observableArrayList(list);	
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox = new VBox();
        vbox.setLayoutY(240);
        vbox.setLayoutX(440);
        vbox.setMaxHeight(180);
        vbox.getChildren().addAll(table);
    }
    */
    public Node getTableNodes() {
    	return vbox;
    }
    
    
    /*
    
   public class TableViewSample extends Application {

    private final TableView<Data> table = new TableView<>();
    private final ObservableList<Data> tvObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Tableview with button column");
        stage.setWidth(600);
        stage.setHeight(600);

        setTableappearance();

        fillTableObservableListWithSampleData();
        table.setItems(tvObservableList);

        TableColumn<Data, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Data, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.getColumns().addAll(colId, colName);

        addButtonToTable();

        Scene scene = new Scene(new Group(table));

        stage.setScene(scene);
        stage.show();
    }

    private void setTableappearance() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(600);
        table.setPrefHeight(600);
    }

    private void fillTableObservableListWithSampleData() {

        tvObservableList.addAll(new Data(1, "app1"),
                                new Data(2, "app2"), 
                                new Data(3, "app3"), 
                                new Data(4, "app4"),
                                new Data(5, "app5"));
    }
*/
    /*private void addButtonToTable() {
        TableColumn<AnimeListElem, Void> colRemBtn = new TableColumn("");

        Callback<TableColumn<AnimeListElem, Void>, TableCell<AnimeListElem, Void>> cellFactory = new Callback<TableColumn<AnimeListElem, Void>, TableCell<AnimeListElem, Void>>() {
            @Override
            public TableCell<AnimeListElem, Void> call(final TableColumn<AnimeListElem, Void> param) {
                final TableCell<AnimeListElem, Void> cell = new TableCell<AnimeListElem, Void>() {

                    private final Button removeBtn = new Button("REMOVE");

                    {
                        removeBtn.setOnAction((ActionEvent event) -> {
                            //AnimeListManager.removeAnime();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(removeBtn);
                        }
                    }
                };
                return cell;
            }
        };
        
        colRemBtn.setCellFactory(cellFactory);
        table.getColumns().add(colRemBtn);
        
        TableColumn<AnimeListElem, Void> colScoreTxt = new TableColumn("Update Score");
        Callback<TableColumn<AnimeListElem, Void>, TableCell<AnimeListElem, Void>> cellFactory3 = new Callback<TableColumn<AnimeListElem, Void>, TableCell<AnimeListElem, Void>>() {
            @Override
            public TableCell<AnimeListElem, Void> call(final TableColumn<AnimeListElem, Void> param) {
                final TableCell<AnimeListElem, Void> cell = new TableCell<AnimeListElem, Void>() {

                    private final TextField scoreTxt = new TextField("");

                    {
                        scoreTxt.setOnAction((ActionEvent event) -> {
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(scoreTxt);
                        }
                    }
                };
                return cell;
            }
        };
        colScoreTxt.setCellFactory(cellFactory3);
        table.getColumns().add(colScoreTxt);
        
        TableColumn<AnimeListElem, Void> colScoreBtn = new TableColumn("");
        Callback<TableColumn<AnimeListElem, Void>, TableCell<AnimeListElem, Void>> cellFactory2 = new Callback<TableColumn<AnimeListElem, Void>, TableCell<AnimeListElem, Void>>() {
            @Override
            public TableCell<AnimeListElem, Void> call(final TableColumn<AnimeListElem, Void> param) {
                final TableCell<AnimeListElem, Void> cell = new TableCell<AnimeListElem, Void>() {

                    private final Button scoreBtn = new Button("UPDATE");

                    {
                        scoreBtn.setOnAction((ActionEvent event) -> {
                           //AnimeListManager.updateScore();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(scoreBtn);
                        }
                    }
                };
                return cell;
            }
        };
        colScoreBtn.setCellFactory(cellFactory2);
        table.getColumns().add(colScoreBtn);
        

    }
*/
    
    /*
    
    public void showDeckRecResults(List<String> list){
        findDeckRecTable = new TableView();

        TableColumn column = new TableColumn("Deck title");
        TableColumn columnLike = new TableColumn("Action");

        findDeckRecTable.getColumns().addAll(column, columnLike);

        List<Row> recResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            recResult.add(new Row(list.get(i)));
        }

        observableList = FXCollections.observableArrayList(recResult);

        column.setCellValueFactory(
                new PropertyValueFactory<Row,String>("info")
        );

        columnLike.setCellValueFactory(
                new PropertyValueFactory<Row,String>("button")
        );


        findDeckRecTable.setItems(observableList);
        findDeckRecTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        visualize = new VBox();
        visualize.setLayoutY(300);
        visualize.setLayoutX(20);
        visualize.setFillWidth(true);
        visualize.setMaxHeight(182);
        visualize.getChildren().addAll(findDeckRecTable);

    }
    */
   
}
   