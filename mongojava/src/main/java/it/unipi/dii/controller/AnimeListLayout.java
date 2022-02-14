/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

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
    protected Button addAnime;
    protected Button removeAnime;
    protected Button viewAnimeList;
    private TableView table;
    private ObservableList<AnimeListRow> observableList;
    private VBox vbox;
    protected Button back;
    public AnimeListLayout(){
        table = new TableView();
        TableColumn columnAnime = new TableColumn("Title");
        TableColumn columnScore = new TableColumn("Score");
        TableColumn columnRemove = new TableColumn();
        TableColumn columnUpdateScore = new TableColumn("Update Score");
        TableColumn columnUpdate = new TableColumn();
        table.getColumns().addAll(columnAnime, columnScore, columnRemove, columnUpdateScore, columnUpdate);
        List<AnimeListRow> anilist = new ArrayList<>();
        List<AnimeListElem> list = MongoDBManager.getAnimeList();
        for(int i = 0; i < list.size(); i++){
            anilist.add(new AnimeListRow(list.get(i).getTitle(), list.get(i).getScore()));
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
        vbox.setMaxHeight(550);
        vbox.setMinWidth(700);
        vbox.getChildren().addAll(table);

        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
    }

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
    
    public void clearLayout(){
        vbox.getChildren().clear();
        table.getColumns().clear();
    }

    public Node getTableNodes() {
    	return vbox;
    }
   
}
   