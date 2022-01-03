package it.unipi.dii.dm.exercises.entities;

import org.bson.Document;

public class AnimeListElem {
    public String title;
    public int score;

    public AnimeListElem(Document doc){
        this.title = doc.getString("anime");
        this.score = doc.getInteger("score");
    }

    public AnimeListElem(String title, int score){
        this.title = title;
        this.score = score;
    }

    public int getScore(){return this.score;}

    public String getTitle(){return this.title;}

    public void setScore(int score){this.score = score;}

}
