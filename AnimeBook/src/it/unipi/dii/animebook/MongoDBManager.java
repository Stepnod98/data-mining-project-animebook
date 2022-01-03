/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import java.util.*;

/**
 *
 * @author Stefano
 */
public class MongoDBManager {
    public static List getAnimeList(){
        List<Anime> mal = new ArrayList<>();
        return mal;
    }
    
    public static List getAnimes(){
        List<Anime> mal = new ArrayList<>();
        return mal;
    }
    
    public static Anime findAnime(String t){
        Anime anime = new Anime(t, 9);
        return anime;
    }
    
    public static void removeFromMal(String t){
        
    }
    
    public static void updateScore(String title, int score){
        
    }
}
