/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Stefano
 */
public class AnimeRow {
    private final SimpleStringProperty title;
    private TextField score;
    private Button add;
    private Button view;
    public AnimeRow(String title) {
        this.title = new SimpleStringProperty(title);
        this.score = new TextField();
        this.add = new Button();
        this.view = new Button();
    }

    public String getTitle(){
        return title.get();
    }

    public void setTitle(String name){
        title.set(name);
    }

    public Button getAdd() {
        return add;
    }
    
    public void setAdd(Button button){
        this.add = button;
    }
    
    public TextField getScore(){
        return score;
    }
    
    public void setScore(TextField Score){
        this.score = score;
    }

    public Button getView(){ return view;}

    public void setView(Button button){this.view = button;}
}
