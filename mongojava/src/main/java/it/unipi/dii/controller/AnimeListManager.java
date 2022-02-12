/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;

/**
 *
 * @author Stefano
 */
public class AnimeListManager {
    private static AnimeListLayout animeListLayout;
    public AnimeListManager(AnimeListLayout animeListLayout){
        this.animeListLayout = animeListLayout;
        setEvents();
   }
    
    public static void updateScore(String title, int newScore){
        MongoDBManager.updateScore(title, newScore);
        GUIManager.openAnimeList();
    }
    
    public static void removeAnime(String title, int score){
        if(score >= 6) {
            List<Genre> genres = MongoDBManager.getGenres(title);
            GUIManager.getCurrent().removeAnimeGenres(genres);
            List<Genre> newGenres = GUIManager.getCurrent().getGenres();
            System.out.println(GUIManager.getCurrent().getGenres());
            MongoDBManager.updateGenres(newGenres);
        }
        MongoDBManager.removeFromMal(title);
        GUIManager.openAnimeList();
    }
   
    public void setEvents(){
        for(int i = 0; i < animeListLayout.getTable().getItems().size(); i++){
            AnimeListRow row = (AnimeListRow)animeListLayout.getTable().getItems().get(i);
            row.getRemove().setText("REMOVE");
            row.getRemove().setOnAction((ActionEvent ev) -> {
                //chiama il metodo relativo all'evento
                String title = row.getTitle();
                int score = row.getScore();
                row.getRemove().setDisable(true);
                removeAnime(title, score);
            });
            row.getUpdateScore().textProperty().addListener((obs, oldValue, newValue)->{
           
            });
            row.getUpdate().setText("UPDATE");
            row.getUpdate().setOnAction((ActionEvent ev) -> {
                //chiama il metodo relativo all'evento
                String title = row.getTitle();
                int newScore = parseInt(row.getUpdateText());
                updateScore(title, newScore);
            });
        }
        animeListLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});

    }
}
