package it.unipi.dii.dm.exercises.entities;

public class Genre {

    public String name;
    boolean value;

    public Genre(String name, boolean value){
        this.name = name;
        this.value = value;
    }

    public boolean getValue(){return this.value;}

    public void setValue(){value= !value;}//if true->false and vice-versa


}
