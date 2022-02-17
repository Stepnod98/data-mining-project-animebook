/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.layout;

import java.util.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 *
 * @author Stefano
 */
public class GenreSelection {
    private Label selectGenres;
    private String [] genres = {"Action","Adventure","Comedy","Dementia","Demons","Drama","Fantasy","Game",
                                "Historical","Horror","Magic","Mecha","Military","Music","Mystery","Parody",
                                "Police","Psychological","Romance","School","Sci-Fi","Seinen","Shoujo",
                                "Shounen","Slice of Life","Space","Sports","Super Power",
                                "Supernatural","Thriller"};
    
    protected List<CheckBox> cblist = new ArrayList<>();
    private Button confirm;
    private Button back;
    public GenreSelection(){
        selectGenres = new Label("Select your favorites genres");
        selectGenres.setLayoutX(20);
        selectGenres.setLayoutY(30);
        confirm = new Button("CONFIRM");
        confirm.setLayoutX(40);
        confirm.setLayoutY(560);
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
    }
    
    private void addSelection(){
        int x = 40;
        int y = 80;
        for(int i = 0; i < genres.length; i++){
            CheckBox cb = new CheckBox(genres[i]);
            cb.setIndeterminate(false);
            cb.setLayoutX(x);
            cb.setLayoutY(y);
            if(x >= 640){
                x = 40;
                y+=40;
            }
            else{
                x+=100;
            }
            cblist.add(cb);
        }
    }
    
    private void loadSelection(){
        int x = 40;
        int y = 80;
        for(int i = 0; i < genres.length; i++){
            CheckBox cb = new CheckBox(genres[i]);
            cb.setIndeterminate(false);
            cb.setLayoutX(x);
            cb.setLayoutY(y);
            if(x >= 640){
                x = 40;
                y+=40;
            }
            else{
                x+=100;
            }
            cblist.add(cb);
        }
    }
    
    public List getNodes() {
        addSelection();
        List<Node> returnNode = new ArrayList<>();
        for(int i = 0; i < cblist.size(); i++){
            returnNode.add(cblist.get(i));
        }
        returnNode.add(selectGenres);
        returnNode.add(confirm);
        returnNode.add(back);
        return returnNode;
    }
    
    public List getUNodes() {
        loadSelection();
        List<Node> returnNode = new ArrayList<>();
        for(int i = 0; i < cblist.size(); i++){
            returnNode.add(cblist.get(i));
        }
        returnNode.add(selectGenres);
        returnNode.add(confirm);
        returnNode.add(back);
        return returnNode;
    }
    
    public Button getConfirm(){
        return confirm;
    }
    
    public Button getBack(){
        return back;
    }
    
    public List getCheckBoxList(){
        return cblist;
    }

}
