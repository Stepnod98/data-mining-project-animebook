package it.unipi.dii.controller;

import it.unipi.dii.entities.User;
import it.unipi.dii.layout.AppLayout;
import javafx.event.ActionEvent;

public class AppLayoutManager {

    private final AppLayout appLayout;
    private final User user;

    public AppLayoutManager(AppLayout appLayout, User user){
        this.appLayout = appLayout;
        this.user = user;
        setEvents();
    }

    private void setEvents(){
        appLayout.getbAnimeList().setOnAction((ActionEvent ev)->{GUIManager.openAnimeList();}); 
        appLayout.getbAnime().setOnAction((ActionEvent ev)->{GUIManager.openAnimeManager();});
        appLayout.getbLogout().setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
        appLayout.getbSettings().setOnAction((ActionEvent ev)->{GUIManager.openGenreSelectionEditor();});
    }

}
