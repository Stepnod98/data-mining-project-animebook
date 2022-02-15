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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private ListView<String> browseResults;
    private VBox vbox;
    private VBox animeBox;
    private Label result;
    protected Button back;
    private Label log;
    private TextField logText;
       
    public AnimeLayout(){
        logText = new TextField();
        logText.setLayoutX(480);
        logText.setLayoutY(80);
        logText.setMinWidth(240);
        logText.setEditable(false);
        log = new Label("Log:");
        log.setLayoutX(480);
        log.setLayoutY(60);
        view = new Label("View All Animes:");
        view.setLayoutX(50);
        view.setLayoutY(40);
        viewAnimes = new Button("VIEW");
    	viewAnimes.setLayoutY(40);
    	viewAnimes.setLayoutX(330);
    	viewAnimes.setMaxWidth(300);
        viewAnimes.setMinWidth(80);
        find = new Label("Find an Anime:");
        find.setLayoutX(50);
        find.setLayoutY(80);
        animeToFind = new TextField();
        animeToFind.setLayoutX(155);
        animeToFind.setLayoutY(80);
        animeToFind.setFocusTraversable(false);
        animeToFind.setMaxWidth(200);
        findAnime = new Button("FIND");
    	findAnime.setLayoutY(80);
    	findAnime.setLayoutX(330);
    	findAnime.setMaxWidth(300);
        findAnime.setMinWidth(80);
        viewRecommended = new Label("View Recommended Animes:");
        viewRecommended.setLayoutX(50);
        viewRecommended.setLayoutY(120);
        viewRecommendedAnimes = new Button("VIEW");
    	viewRecommendedAnimes.setLayoutY(120);
    	viewRecommendedAnimes.setLayoutX(330);
    	viewRecommendedAnimes.setMaxWidth(300);
        viewRecommendedAnimes.setMinWidth(80);
        browseResults = new ListView<>();
        browseResults.setLayoutY(105);
        browseResults.setLayoutX(150);
        browseResults.setMaxWidth(animeToFind.getPrefWidth());
        browseResults.setMaxHeight(120);
        browseResults.setVisible(false);
        table = new TableView();
        vbox = new VBox();
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
    }
    
    public void updateBrowseResults(List<String> result){
        browseResults.getItems().clear();
        if(result.isEmpty() ){
            browseResults.setVisible(false);
        }else{
            browseResults.setVisible(true);
            browseResults.getItems().addAll(result);
        }
    }

    public void showAnimeResults(List<String> list){
        TableColumn columnAnime = new TableColumn("TITLE");
        TableColumn columnScore = new TableColumn("YOUR SCORE");
        TableColumn columnAdd = new TableColumn();
        TableColumn columnView = new TableColumn();
        table.getColumns().addAll(columnAnime, columnScore, columnAdd, columnView);
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
        columnView.setCellValueFactory(
                new PropertyValueFactory<>("view")
        );
        
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.setLayoutY(200);
        vbox.setLayoutX(60);
        vbox.setMaxHeight(350);
        vbox.setMinWidth(650);
        vbox.getChildren().addAll(table);
    }
    
    public void showAnimeFindResults(String title, int episodes, int members, int score){

        animeBox = new VBox();

        HBox titleBox = new HBox();
        Label usernameLabel = new Label("Title: ");
        usernameLabel.setStyle("-fx-font-weight: bold;");
        Text usernameText = new Text(title);

        titleBox.getChildren().addAll(usernameLabel, usernameText);
        titleBox.setStyle("-fx-font-size: 15");

        HBox epsBox = new HBox();
        Label epsLabel = new Label("Episodes: ");
        epsLabel.setStyle("-fx-font-weight: bold;");
        Text epsText = new Text("");
        if(episodes == 0){
            epsText.setText("N.A.");
        }
        else{
            epsText.setText(""+episodes);
        }

        epsBox.getChildren().addAll(epsLabel, epsText);
        epsBox.setStyle("-fx-font-size: 15");
        epsBox.setPadding(new Insets(10, 0,0, 0));

        HBox membersBox = new HBox();
        Label membersLabel = new Label("Members: ");
        membersLabel.setStyle("-fx-font-weight: bold;");
        Text membersText = new Text(""+members);

        membersBox.getChildren().addAll(membersLabel, membersText);
        membersBox.setStyle("-fx-font-size: 15");
        membersBox.setPadding(new Insets(10, 0,0, 0));

        HBox userScoreBox = new HBox();
        Label userScoreLabel = new Label("User's Score: ");
        userScoreLabel.setStyle("-fx-font-weight: bold;");
        Text userScoreText = new Text("");
        if(score == 0){
            userScoreText.setText("N.A.");
        }
        else{
            userScoreText.setText(""+score);
        }

        userScoreBox.getChildren().addAll(userScoreLabel, userScoreText);
        userScoreBox.setStyle("-fx-font-size: 15");
        userScoreBox.setPadding(new Insets(10, 0,0, 0));

        HBox scoreBox = new HBox();
        TextField tf = new TextField();
        tf.setPromptText("Insert Score");
        tf.setPrefSize(120, 20);
        scoreBox.getChildren().addAll(tf);
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setPadding(new Insets(10, 0,0, 0));    
        
        HBox commandBox = new HBox();
        Button action = new Button("ACTION");

        action.setPrefSize(100, 20);

        commandBox.getChildren().addAll(action);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.setPadding(new Insets(30, 0,0, 0));

        HBox closeBox = new HBox();
        Button close = new Button("ACTION");

        close.setPrefSize(100, 20);

        closeBox.getChildren().addAll(close);
        closeBox.setAlignment(Pos.CENTER);
        closeBox.setPadding(new Insets(10, 0,0, 0));
        
        animeBox.getChildren().addAll(titleBox, epsBox, membersBox, userScoreBox, scoreBox, commandBox, closeBox);


        animeBox.setLayoutY(200);
        animeBox.setLayoutX(60);
        animeBox.setMinWidth(250);
        animeBox.setMinHeight(100);
        animeBox.setStyle("-fx-background-color: MINTCREAM;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");

    }
    
    public void clearLayout(){
        vbox.getChildren().clear();
        table.getColumns().clear();
        logText.clear();
    }
    
    public void printError(String err){
        logText.setText(err);
        logText.setStyle("-fx-text-inner-color: red;");
    }

    public void printLog(String log){
        logText.setText(log);
        logText.setStyle("-fx-text-inner-color: green;");
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = { view, viewAnimes, find, animeToFind, findAnime, viewRecommended, 
                            viewRecommendedAnimes, browseResults, vbox, logText, log, back};
    	return returnNode;
    }
    
    public String getAnimeToFind(){
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
    
    public ListView<String> getBrowseResults() {
        return browseResults;
    }
    
    public VBox getAnimeBox() {
        return animeBox;
    }
    
    public TextField getLogText() {
        return logText;
    }
    
    public Node getTableNodes() {
    	return vbox;
    }
}