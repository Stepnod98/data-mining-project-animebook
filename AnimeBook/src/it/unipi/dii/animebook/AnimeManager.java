/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Stefano
 */
public class AnimeManager {
    private static AnimeLayout animeLayout;
    public AnimeManager(AnimeLayout animeLayout){
        this.animeLayout = animeLayout;
        
    }
   
    public static void viewAnimes(){
        animeLayout.clearLayout();
        //List<String> l = MongoDBManager.getAnimes();
        List<String> l = new ArrayList<>();
        l.add("Gintama");
        l.add("One Piece");
        l.add("HunterXHunter");
        l.add("Naruto");
        animeLayout.showAnimeResults(l);
        setTableEvents();
    }
    
    public static void findAnime(){
        animeLayout.clearLayout();
        /*String title = animeLayout.getAnimeToFind();
        MongoDBManager.findAnimeListElem(title);
        GUIManager.openAnimeManager();*/
    }
    
    public static void viewRecommendedAnimes(){
        
    }
    
    public static void viewListToBrowse(){
           animeLayout.clearLayout();
            //List<String> l = MongoDBManager.findAnimeList(deckManagerLayout.getDeckToBrowse());
            List<String> l = new ArrayList<>();
            l.add("ad");
            l.add("eb");
            l.add("cgf");
            l.add("adedf");
            l.add("tel");
            BorderPane bp = BrowseManager.viewList(animeLayout.getAnimeToBrowseTf(), l);
            animeLayout.showListResults(bp, 150, 100);
    
    }
    
    public static void addAnime(String title){
        System.out.println("Aggiungo" + title);
        //MongoDBManager.addAnime(title); -> da scrivere, aggiunge l'anime alla MAL senza voto
        //oppure va aggiunto che si apre una finestra in cui poterlo inserire con il voto
    }
    
    public static void setEvents(){
       /*animeLayout.findDeck.setOnAction((ActionEvent ev)->{findDeck();});
        animeLayout.removeDeck.setOnAction((ActionEvent ev)->{removeDeck();});
        animeLayout.findTopCards.setOnAction((ActionEvent ev)->{findTopXCard();});*/
        
        animeLayout.getAnimeToBrowseTf().textProperty().addListener((obs, oldValue, newValue)->{
            viewListToBrowse();
        });
        animeLayout.getFindAnime().setOnAction((ActionEvent ev)->{findAnime();});
        animeLayout.getViewAnimes().setOnAction((ActionEvent ev)->{viewAnimes();}); 
        animeLayout.viewRecommendedAnimes.setOnAction((ActionEvent ev)->{viewRecommendedAnimes();}); 
        animeLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
    
    public static void setTableEvents(){
        for(int i = 0; i < animeLayout.getTable().getItems().size(); i++){
            AnimeRow row = (AnimeRow)animeLayout.getTable().getItems().get(i);
            row.getAdd().setText("ADD");
            row.getAdd().setOnAction((ActionEvent ev) -> {
                //chiama il metodo relativo all'evento
                String title = row.getTitle();
                row.getAdd().setDisable(true);
                addAnime(title);
            });
            
        }
    }
}
