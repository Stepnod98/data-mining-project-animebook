/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import javafx.event.ActionEvent;

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
        setEvents();
    }
    
    public static void confirmRegistration(){
        GUIManager.openLoginManager();
    }
    
    public static void setEvents(){
        genreSelection.getConfirm().setOnAction((ActionEvent ev)->{confirmRegistration();});
        genreSelection.getBack().setOnAction((ActionEvent ev)->{GUIManager.openSignUpManager();});
    }
}
