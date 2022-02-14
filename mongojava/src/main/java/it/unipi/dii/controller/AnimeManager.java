/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Stefano
 */
public class AnimeManager {
    private static AnimeLayout animeLayout;
    public AnimeManager(AnimeLayout animeLayout){
        this.animeLayout = animeLayout;
        setEvents();
        setBrowseEvents();
        
    }
   
    public void viewAnimes(){
        animeLayout.clearLayout();
        GUIManager.clearAnimeBoxes();
        List<String> l = MongoDBManager.getAnimes();
        animeLayout.showAnimeResults(l);
        setTableEvents();
        animeLayout.printLog("Anime shown correctly!");
    }
    
   public void findAnime(String inTitle){
        if(MongoDBManager.checkAnime(inTitle)){
            animeLayout.clearLayout();
            GUIManager.clearAnimeBoxes();
            setAnimeBox(inTitle);
            GUIManager.addNode(animeLayout.getAnimeBox());
        }
        else{
            animeLayout.printError("Anime not found!");
        }
    }
    
    public void viewRecommendedAnimes(){
        animeLayout.clearLayout();
        GUIManager.clearAnimeBoxes();
        double[] elementCoords = GUIManager.getCurrent().getGenresFreq();
        try {
            int[] clusterElements = Clustering.kmeansAssignment(elementCoords);
            List<String> foundList = Recommender.getMostPopularAnime(clusterElements);
            animeLayout.showAnimeResults(foundList);
            setTableEvents();
            animeLayout.printLog("Recommended animes shown correctly!");
        }
        catch(Exception ex){ex.printStackTrace();}
    }
    
    public static void addAnime(String title, int score){
        System.out.println("Aggiungo " + title + " con score: " + score);
        if(MongoDBManager.checkAnimeinList(title)){
            animeLayout.printError("Anime " + title + " not inserted, may already be present in your list!");
            return;
        }
        if(!MongoDBManager.storeAnimeListElement(GUIManager.getCurrentUser(), title, score)){
            animeLayout.printError("Anime " + title + " not inserted, may already be present in your list!");
            return;
        }
        List<Genre> genres = MongoDBManager.getGenres(title);
        if(score >= 6){
            System.out.println(GUIManager.getCurrent().getGenres());
            GUIManager.getCurrent().addAnimeGenres(genres);
            //GUIManager.setCurrentUser(new User(GUIManager.getCurrentUser(), GUIManager.getCurrent().getGenres()));
            List<Genre> newGenres = GUIManager.getCurrent().getGenres();
            System.out.println(GUIManager.getCurrent().getGenres());
            MongoDBManager.updateGenres(newGenres);
        }
        animeLayout.printLog("Anime " + title + " added!");
    }
    
    public void setEvents(){
        animeLayout.getFindAnime().setOnAction((ActionEvent ev)->{
            findAnime(animeLayout.getAnimeToFind());
        });
        animeLayout.getViewAnimes().setOnAction((ActionEvent ev)->{viewAnimes();}); 
        animeLayout.viewRecommendedAnimes.setOnAction((ActionEvent ev)->{viewRecommendedAnimes();});
        animeLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
        animeLayout.getAnimeToBrowseTf().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    animeLayout.getBrowseResults().setVisible(false);
                }
            }
        });


        animeLayout.getAnimeToBrowseTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    animeLayout.getBrowseResults().setVisible(false);
                }else {
                    animeLayout.updateBrowseResults(MongoDBManager.findAnimeList(newValue));
                }
            }
        });
        
        animeLayout.getBrowseResults().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    animeLayout.getBrowseResults().setVisible(true);
                }else {
                    animeLayout.getBrowseResults().setVisible(false);
                }
            }
        });


    }

    private void setBrowseEvents() {
        animeLayout.getBrowseResults().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseTasks());
            }
        });
    }

    private void browseTasks(){
        if(animeLayout.getBrowseResults().getSelectionModel().getSelectedItem() != null) {
            String selected = animeLayout.getBrowseResults().getSelectionModel().getSelectedItem();
            animeLayout.getBrowseResults().getSelectionModel().clearSelection();
            animeLayout.getAnimeToBrowseTf().setText(selected);
            animeLayout.getBrowseResults().setVisible(false);
        }
    }

    
    public void setTableEvents(){
        for(int i = 0; i < animeLayout.getTable().getItems().size(); i++){
            AnimeRow row = (AnimeRow)animeLayout.getTable().getItems().get(i);
            row.getAdd().setText("ADD");
            row.getAdd().setOnAction((ActionEvent ev) -> {
                //chiama il metodo relativo all'evento
                String title = row.getTitle();
                if(isNumeric(row.getScore().getText()) && ((parseInt(row.getScore().getText()) <= 10)
                        && (parseInt(row.getScore().getText()) > 0) ) ){
                    addAnime(title, parseInt(row.getScore().getText()));
                    row.getAdd().setDisable(true);
                }
                else{
                    animeLayout.printError("Insert a score between 1 and 10!");
                }
            });
            row.getView().setText("VIEW");
            row.getView().setOnAction((ActionEvent ev) -> {
                //chiama il metodo relativo all'evento
                String title = row.getTitle();
                findAnime(title);
            });
        }
    }
    
    private void setAnimeBox(String title){
        int episodes = MongoDBManager.getAnimeEps(title);
        int members = MongoDBManager.getAnimeMembers(title);
        int score = MongoDBManager.getAnimeUserScore(title);
        animeLayout.showAnimeFindResults(title, episodes, members, score);
        animeLayout.printLog("Anime found!");
        HBox hBox = (HBox) animeLayout.getAnimeBox().getChildren().get(animeLayout.getAnimeBox().getChildren().size()-1);
        HBox hBox2 = (HBox) animeLayout.getAnimeBox().getChildren().get(animeLayout.getAnimeBox().getChildren().size()-2);
        ((Button) hBox.getChildren().get(0)).setText("ADD");
        if(MongoDBManager.checkAnimeinList(title)){
            hBox.getChildren().get(0).setDisable(true);
        }
        ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
            if(isNumeric(((TextField) hBox2.getChildren().get(0)).getText()) 
                    && ((parseInt(((TextField) hBox2.getChildren().get(0)).getText()) <= 10)
                    && (parseInt(((TextField) hBox2.getChildren().get(0)).getText()) > 0) )){
                addAnime(title, parseInt(((TextField) hBox2.getChildren().get(0)).getText()));
                hBox.getChildren().get(0).setDisable(true);
            }
            else{
                animeLayout.printError("Insert a score between 1 and 10!");
            }
        });
    }
    
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException | ClassCastException e){
            return false;
        }
    }
}
