/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

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
    private TableView<String> table = new TableView<String>();
    private ObservableList<String> observableList;
    private VBox vbox;
    private Label result;
    protected Button back;
    public AnimeLayout(){
        view = new Label("View All Animes:");
        view.setLayoutX(50);
        view.setLayoutY(40);
        viewAnimes = new Button("VIEW");
    	viewAnimes.setLayoutY(40);
    	viewAnimes.setLayoutX(220);
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
    	viewRecommendedAnimes.setLayoutX(220);
    	viewRecommendedAnimes.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = { view, viewAnimes, find, animeToFind, findAnime, viewRecommended, viewRecommendedAnimes, back};
    	return returnNode;
    }
    
    public static String getAnimeToFind(){
        return animeToFind.getText();
    }
    
    public void showCardResults(List<String> list){
        TableColumn<String, String> column = new TableColumn("Card Title");
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
    
    public Node getTableNodes() {
    	return vbox;
    }
}