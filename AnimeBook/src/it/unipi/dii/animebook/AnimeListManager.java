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
public class AnimeListManager {
    private static AnimeListLayout animeListLayout;
    public AnimeListManager(AnimeListLayout animeListLayout){
        this.animeListLayout = animeListLayout;
        
   }
    
    public static void updateScore(){
        int newScore = 0;
        MongoDBManager.updateScore(null, newScore);
        GUIManager.openAnimeList(animeListLayout);
    }
    
    public static void removeAnime(){
        MongoDBManager.removeFromMal(null);
        GUIManager.openAnimeList(animeListLayout);
    }
   
    public static void setEvents(){
       /*animeListLayout.findDeck.setOnAction((ActionEvent ev)->{findDeck();});
        animeListLayout.removeDeck.setOnAction((ActionEvent ev)->{removeDeck();});
        animeListLayout.findTopCards.setOnAction((ActionEvent ev)->{findTopXCard();});	
        animeListLayout.findTopECards.setOnAction((ActionEvent ev)->{findTopXECard();});
        animeListLayout.findMagicTrapDecks.setOnAction((ActionEvent ev)->{findMagicTrapDeck();});*/ 
       // animeListLayout.findArchetypeDecks.setOnAction((ActionEvent ev)->{removeAnime();});
        animeListLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
}
