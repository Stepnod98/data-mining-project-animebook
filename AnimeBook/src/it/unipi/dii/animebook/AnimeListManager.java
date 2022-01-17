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
    
    public static void updateScore(String title, int newScore){
        System.out.println("Videogiochi" + title + newScore);
        MongoDBManager.updateScore(title, newScore);
        GUIManager.openAnimeList(animeListLayout);
    }
    
    public static void removeAnime(String title){
        System.out.println("Groda" + title);
        MongoDBManager.removeFromMal(title);
        GUIManager.openAnimeList(animeListLayout);
    }
   
    public static void setEvents(){
        for(int i = 0; i < animeListLayout.getTable().getItems().size(); i++){
            AnimeListRow row = (AnimeListRow)animeListLayout.getTable().getItems().get(i);
            row.getRemove().setText("REMOVE");
            row.getRemove().setOnAction((ActionEvent ev) -> {
                //chiama il metodo relativo all'evento
                String title = row.getTitle();
                row.getRemove().setDisable(true);
                removeAnime(title);
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
       /* private void setEvents(){
        socialLayout.getShareDeck().setOnAction((ActionEvent ev)->{share();});
        socialLayout.getFindUser().setOnAction((ActionEvent ev)->{findUser();});
        socialLayout.getFindDeck().setOnAction((ActionEvent ev)->{findDeck();});
        socialLayout.getBack().setOnAction((ActionEvent ev)->{
            GUIManager.openAppManager();});
        socialLayout.getViewRecDeck().setOnAction((ActionEvent ev)->{
            socialLayout.showDeckRecResults(Neo4jManager.getRecommendedDecks(currentUser));
            setLikeEvents();
            socialLayout.printLog("Recommended users shown");
            GUIManager.addNode(socialLayout.getTableNodes());
        });
        socialLayout.getViewRecUser().setOnAction((ActionEvent ev)->{
            socialLayout.showUserRecResults(Neo4jManager.getRecommendedUsers(currentUser));
            setFollowEvents();
            socialLayout.printLog("Recommended deck shown");
            GUIManager.addNode(socialLayout.getTableNodes());
        });
    }

    private void setFollowEvents(){

        for(int i = 0; i < socialLayout.getFindUserRecTable().getItems().size(); i++){
            Row row = (Row) socialLayout.getFindUserRecTable().getItems().get(i);
            row.getButton().setText("Follow");
            row.getButton().setOnAction((ActionEvent ev) -> {
                follow(new User(row.getInfo()));
                row.getButton().setDisable(true);
            });
        }
    }

    private void setLikeEvents(){

        for(int i = 0; i < socialLayout.getFindDeckRecTable().getItems().size(); i++){
            Row row = (Row) socialLayout.getFindDeckRecTable().getItems().get(i);
            row.getButton().setText("Like");
            row.getButton().setOnAction((ActionEvent ev) -> {
                like(new Deck(row.getInfo()));
                row.getButton().setDisable(true);
            });
        }
    }
        animeListLayout.findDeck.setOnAction((ActionEvent ev)->{findDeck();});
        animeListLayout.removeDeck.setOnAction((ActionEvent ev)->{removeDeck();});
        animeListLayout.findTopCards.setOnAction((ActionEvent ev)->{findTopXCard();});	
        animeListLayout.findTopECards.setOnAction((ActionEvent ev)->{findTopXECard();});
        animeListLayout.findMagicTrapDecks.setOnAction((ActionEvent ev)->{findMagicTrapDeck();});*/ 
       // animeListLayout.findArchetypeDecks.setOnAction((ActionEvent ev)->{removeAnime();});
        
    }
}
