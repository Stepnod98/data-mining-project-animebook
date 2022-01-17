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

/**
 *
 * @author Stefano
 */
public class AnimeLayout {
    private Label view;
    protected Button viewAnimes;
    private Label find;
    private static TextField animeToFind;
    protected Button findAnime;
    private Label viewRecommended;
    protected Button viewRecommendedAnimes;
    private TableView table;
    private ObservableList<AnimeRow> observableList;
    private VBox vbox;
    private Label result;
    protected Button back;
    public AnimeLayout(){
        view = new Label("View All Animes:");
        view.setLayoutX(50);
        view.setLayoutY(40);
        viewAnimes = new Button("VIEW");
    	viewAnimes.setLayoutY(40);
    	viewAnimes.setLayoutX(250);
    	viewAnimes.setMaxWidth(300);
        find = new Label("Find an Anime:");
        find.setLayoutX(50);
        find.setLayoutY(80);
        animeToFind = new TextField();
        animeToFind.setLayoutX(150);
        animeToFind.setLayoutY(80);
        animeToFind.setFocusTraversable(false);
        animeToFind.setMaxWidth(200);
        findAnime = new Button("FIND");
    	findAnime.setLayoutY(80);
    	findAnime.setLayoutX(320);
    	findAnime.setMaxWidth(300);
        viewRecommended = new Label("View Recommended Animes:");
        viewRecommended.setLayoutX(50);
        viewRecommended.setLayoutY(120);
        viewRecommendedAnimes = new Button("VIEW");
    	viewRecommendedAnimes.setLayoutY(120);
    	viewRecommendedAnimes.setLayoutX(250);
    	viewRecommendedAnimes.setMaxWidth(300);
        table = new TableView();
        vbox = new VBox();
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = { view, viewAnimes, find, animeToFind, findAnime, viewRecommended, 
                            viewRecommendedAnimes, vbox, back};
    	return returnNode;
    }
    
    public static String getAnimeToFind(){
        return animeToFind.getText();
    }
    
    public TextField getAnimeToBrowseTf(){
        return animeToFind;
    }
    
    public Button getViewAnimes(){
        return viewAnimes;
    }
    
    public Button getFindAnime(){
        return findAnime;
    }
    
    public Button getBack(){
        return back;
    }
    
    public TableView getTable(){
        return table;
    }
    
    public void showAnimeDetails(List<String> list){
        TableColumn columnAnime = new TableColumn("Title");
        TableColumn columnAdd = new TableColumn();
        table.getColumns().addAll(columnAnime, columnAdd);
        List<AnimeRow> anilist = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            anilist.add(new AnimeRow(list.get(i)));
        }
        observableList = FXCollections.observableArrayList(anilist);
        columnAnime.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        columnAdd.setCellValueFactory(
                new PropertyValueFactory<>("add")
        );
        
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.setLayoutY(240);
        vbox.setLayoutX(60);
        vbox.setMaxHeight(148);
        vbox.getChildren().addAll(table);
    }
    
    public void showAnimeResults(List<String> list){
        TableColumn columnAnime = new TableColumn("TITLE");
        TableColumn columnScore = new TableColumn("YOUR SCORE");
        TableColumn columnAdd = new TableColumn();
        table.getColumns().addAll(columnAnime, columnScore, columnAdd);
        List<AnimeRow> anilist = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            anilist.add(new AnimeRow(list.get(i)));
        }
        observableList = FXCollections.observableArrayList(anilist);
        columnAnime.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        
        columnScore.setCellValueFactory(
                new PropertyValueFactory<>("score")
        );
        
        columnAdd.setCellValueFactory(
                new PropertyValueFactory<>("add")
        );
        
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.setLayoutY(200);
        vbox.setLayoutX(60);
        vbox.setMaxHeight(350);
        vbox.setMinWidth(500);
        vbox.getChildren().addAll(table);
    }
    
    
    public void showListResults(BorderPane bp, int x, int y){
        if(bp == null){
            return;
        }
        vbox.setLayoutX(x);
        vbox.setLayoutY(y);
        vbox.setMaxHeight(120);
        vbox.setMaxWidth(150);
        vbox.setMinWidth(100);
        vbox.getChildren().addAll(bp);
    }
    
    public void clearLayout(){
        vbox.getChildren().clear();
        table.getColumns().clear();
    }
    
    public Node getTableNodes() {
    	return vbox;
    }
}