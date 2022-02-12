/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Stefano
 */
public class GenreSelectionManager {
    private static GenreSelection genreSelection;
    private User user;
    public GenreSelectionManager(GenreSelection genreSelection, User user){
        this.genreSelection = genreSelection;
        this.user = user;
    }
    
    public static void confirmRegistration(){
        List<Genre> genres = new ArrayList<>();
        List<CheckBox> cblist = genreSelection.getCheckBoxList();
        for(int i = 0; i < cblist.size(); i++){
            if(cblist.get(i).isSelected()){
                //System.out.println("Genere: " + cblist.get(i).getText() );
                genres.add(new Genre(cblist.get(i).getText(), 1));
            }
        }
        User profile = new User(GUIManager.getCurrentUser(), genres);
        MongoDBManager.insertProfile(profile);
        GUIManager.openLoginManager();
    }
    
    public static void confirmEdit(){
        List<Genre> genres = new ArrayList<>();
        List<CheckBox> cblist = genreSelection.getCheckBoxList();
        for(int i = 0; i < cblist.size(); i++){
            if(cblist.get(i).isSelected()){
                //System.out.println("Genere: " + cblist.get(i).getText() );
                genres.add(new Genre(cblist.get(i).getText(), 1));
            }
        }
        GUIManager.getCurrent().addAnimeGenres(genres);
        List<Genre> userGenres = GUIManager.getCurrent().getGenres();
        MongoDBManager.updateGenres(userGenres);
        GUIManager.openAppManager();
    }
    
    public static void setEvents(){
        genreSelection.getConfirm().setOnAction((ActionEvent ev)->{confirmRegistration();});
        genreSelection.getBack().setOnAction((ActionEvent ev)->{GUIManager.openSignUpManager();});
    }
    
    public static void setSettingsEvents(){
        genreSelection.getConfirm().setOnAction((ActionEvent ev)->{confirmEdit();});
        genreSelection.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
}
