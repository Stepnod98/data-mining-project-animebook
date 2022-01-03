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
public class User {
    private String username;
    private List<String> animeList = new ArrayList<String>();
    public User(String username, ArrayList<String> list){
        this.username = username;
        animeList = list;
    }
    public String getUsername(){
        return username;
    }
}
