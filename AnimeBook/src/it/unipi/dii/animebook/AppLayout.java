/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

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
    private Button bLogout;
    public AppLayout(){
        title = new Label("Welcome in AnimeBook!");
        title.setLayoutX(280);
        title.setLayoutY(20);
        bAnimeList = new Button("YOUR ANIME LIST");
        bAnimeList.setLayoutX(280);
        bAnimeList.setLayoutY(80);
        bAnime = new Button("SEARCH ANIMES");
        bAnime.setLayoutX(280);
        bAnime.setLayoutY(130);
        bLogout = new Button("LOGOUT");
        bLogout.setLayoutX(280);
        bLogout.setLayoutY(180);
        bAnimeList.setOnAction((ActionEvent ev)->{GUIManager.openAnimeList();}); 
        bAnime.setOnAction((ActionEvent ev)->{GUIManager.openAnimeManager();});
        bLogout.setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});           
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = {title, bAnimeList, bAnime, bLogout};
    	return returnNode;
    }
}
