/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

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
        //List<String> l = MongoDBManager.getAnimes();
        List<String> l = new ArrayList<>();
        l.add("Gintama");
        l.add("One Piece");
        l.add("HunterXHunter");
        l.add("Naruto");
        animeLayout.showAnimeResults(l);
        setTableEvents();
        animeLayout.printLog("Anime shown correctly!");
    }
    
   /* public static void findAnime(String inTitle){
        animeLayout.clearLayout();
        String title = animeLayout.getAnimeToFind();
        MongoDBManager.findAnimeListElem(title);
        GUIManager.openAnimeManager();
    }*/
    
    public static void viewRecommendedAnimes(){
        animeLayout.clearLayout();
        GUIManager.clearAnimeBoxes();
    }
    
    /*public static void viewListToBrowse(){
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
    
    }*/
    
    public static void addAnime(String title, int score){
        System.out.println("Aggiungo " + title + " con score: " + score);
        //MongoDBManager.addAnime(title); -> da scrivere, aggiunge l'anime alla MAL senza voto
        //oppure va aggiunto che si apre una finestra in cui poterlo inserire con il voto
        animeLayout.printLog("Anime " + title + " added!");
    }
    
    public void setEvents(){
        /*
        animeLayout.getAnimeToBrowseTf().textProperty().addListener((obs, oldValue, newValue)->{
            viewListToBrowse();
        });*/
        animeLayout.getFindAnime().setOnAction((ActionEvent ev)->{
            animeLayout.clearLayout();
            GUIManager.clearAnimeBoxes();
            if(MongoDBManager.checkAnime(animeLayout.getAnimeToFind())){
                setAnimeBox();
                GUIManager.addNode(animeLayout.getAnimeBox());
            }
            else{
                animeLayout.printError("Anime not found!");
            }
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
                    List<String> l = new ArrayList<>();
                    l.add("ad");
                    l.add("eb");
                    l.add("cgf");
                    l.add("adedf");
                    l.add("tel");
                    animeLayout.updateBrowseResults(l);//MongoDBManager.findAnime(newValue));
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
            
        }
    }
    
    private void setAnimeBox(){
        String title = animeLayout.getAnimeToFind();
        animeLayout.showAnimeFindResults(title);
        animeLayout.printLog("Anime found!");
        HBox hBox = (HBox) animeLayout.getAnimeBox().getChildren().get(animeLayout.getAnimeBox().getChildren().size()-1);
        HBox hBox2 = (HBox) animeLayout.getAnimeBox().getChildren().get(animeLayout.getAnimeBox().getChildren().size()-2);
        ((Button) hBox.getChildren().get(0)).setText("ADD");
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
