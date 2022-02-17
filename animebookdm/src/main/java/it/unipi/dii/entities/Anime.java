/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.entities;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stefano
 */
public class Anime {
    private String title;
    private List<String> genres = new ArrayList<>();
    private int episodes;
    private int members;
    private double score;
    public Anime(String title, List genres, int episodes, int members, double score){
        this.title = title;
        this.genres = genres;
        this.episodes = episodes;
        this.members = members;
        this.score = score;
    }
    

    public Anime(Document doc){
        this.title = doc.getString("anime");
        /*List<Document> documentList = Arrays.asList((Document) doc.get("genre"));
        for(int i = 0; i < documentList.size(); i++){
            this.genres.add((documentList.get(i).toString()));
        }*/
        this.episodes = doc.getInteger("episodes");
        this.members = doc.getInteger("members");
        this.score = doc.getInteger("score");
    }
    

    
    public String getTitle(){
        return title;
    }
    
    public List getGenres(){
        return genres;
    }
    
    public int getEpisodes(){
        return episodes;
    }
    
    public int getMembers(){
        return members;
    }
    
    public double getScore(){
        return score;
    }
}
