/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

/**
 *
 * @author Stefano
 */

import javafx.beans.property.*;
import org.bson.Document;


public class AnimeListElem {
    //private final SimpleStringProperty title;
    //private final SimpleIntegerProperty score;
    private String title;
    private int score;
    
    public AnimeListElem(String title, int score){
        //this.title = new SimpleStringProperty(title);
        //this.score = new SimpleIntegerProperty(score);
        this.title = title;
        this.score = score;
    }
    
    

    public AnimeListElem(Document doc){
        this.title = doc.getString("anime");
        this.score = doc.getInteger("score");
    }
    

    public String getTitle(){
        return title;
        //.get();
    }
    
    public int getScore(){
        return score;
        //.get();
    }
    
    public void setScore(int score){this.score = score;}
}
