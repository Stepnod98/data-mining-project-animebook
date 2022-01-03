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
        MongoDBManager.getAnimes();
        GUIManager.openAnimeManager();
    }
    
    public static void findAnime(){
        String title = animeLayout.getAnimeToFind();
        MongoDBManager.findAnime(title);
        GUIManager.openAnimeManager();
    }
    
    public static void viewRecommendedAnimes(){
        
    }
    
    public static void setEvents(){
       /*animeLayout.findDeck.setOnAction((ActionEvent ev)->{findDeck();});
        animeLayout.removeDeck.setOnAction((ActionEvent ev)->{removeDeck();});
        animeLayout.findTopCards.setOnAction((ActionEvent ev)->{findTopXCard();});*/	
        animeLayout.findAnime.setOnAction((ActionEvent ev)->{findAnime();});
        animeLayout.viewAnimes.setOnAction((ActionEvent ev)->{viewAnimes();}); 
        animeLayout.viewRecommendedAnimes.setOnAction((ActionEvent ev)->{viewRecommendedAnimes();}); 
        animeLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
}
