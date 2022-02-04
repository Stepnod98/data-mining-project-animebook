/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

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
        setEvents();
    }
    
    public static void confirmRegistration(){
        List<String> genres = new ArrayList<>();
        List<CheckBox> cblist = genreSelection.getCheckBoxList();
        for(int i = 0; i < cblist.size(); i++){
            if(cblist.get(i).isSelected()){ //????
                genres.add(cblist.get(i).getText());
            }
        }
        GUIManager.openLoginManager();
    }
    
    public static void setEvents(){
        genreSelection.getConfirm().setOnAction((ActionEvent ev)->{confirmRegistration();});
        genreSelection.getBack().setOnAction((ActionEvent ev)->{GUIManager.openSignUpManager();});
    }
}
