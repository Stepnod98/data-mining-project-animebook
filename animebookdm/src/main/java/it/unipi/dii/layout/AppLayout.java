/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.layout;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Stefano
 */
public class AppLayout {
    private Label title;
    private Button bAnimeList;
    private Button bAnime;
    private Button bSettings;
    private Button bLogout;
    public AppLayout(){
        title = new Label("Welcome in AnimeBook!");
        title.setLayoutX(180);
        title.setLayoutY(20);
        title.setId("title");
        bAnimeList = new Button("YOUR ANIME LIST");
        bAnimeList.setLayoutX(280);
        bAnimeList.setLayoutY(110);
        bAnimeList.setMinWidth(200);
        bAnime = new Button("SEARCH ANIMES");
        bAnime.setLayoutX(280);
        bAnime.setLayoutY(160);
        bAnime.setMinWidth(200);
        bSettings = new Button("EDIT GENRES");
        bSettings.setLayoutX(280);
        bSettings.setLayoutY(210);
        bSettings.setMinWidth(200);
        bLogout = new Button("LOGOUT");
        bLogout.setLayoutX(280);
        bLogout.setLayoutY(260);
        bLogout.setMinWidth(200);
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = {title, bAnimeList, bAnime, bSettings, bLogout};
    	return returnNode;
    }
    
    public Button getbAnime(){
        return bAnime;
    }
    
    public Button getbAnimeList(){
        return bAnimeList;
    }
    
    public Button getbSettings(){
        return bSettings;
    }
    
    public Button getbLogout(){
        return bLogout;
    }
}
