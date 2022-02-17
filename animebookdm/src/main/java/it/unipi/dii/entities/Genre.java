/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.entities;

/**
 *
 * @author Stefano
 */
public class Genre {
    private String name;
    private int count;
    
    public Genre(String name, int present){
        this.name = name;
        this.count = present;
    }
    
    public int getCount(){
        return count;
    }

    public void setCount(int newCount){
        count = newCount;
    }
    
    public String getName(){
        return name;
    }
}
