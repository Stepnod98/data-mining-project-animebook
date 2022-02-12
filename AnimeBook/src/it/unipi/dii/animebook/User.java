/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

//import org.bson.Document;

import java.util.ArrayList;

public class User{

    private String username;
    public String email;
    public String pwd;
    public String firstName;
    public String lastName;
    private ArrayList<AnimeListElem> animeList;
    
    public User(String username,String pwd ,String firstName, String lastName, String email){
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String username){
        this.username = username;
    }
    
    /*public User(Document doc){
        this.username = doc.getString("profile");
        if((ArrayList<Document>)doc.get("animelist") != null)
            this.animeList = getAnimeListfromDoc((ArrayList<Document>)doc.get("animelist"));
        else
            this.animeList = new ArrayList<>();
    }
*/
    public String getUsername(){return this.username;}
/*
    public ArrayList<AnimeListElem> getAnimeList(){return this.animeList;}

    public ArrayList<AnimeListElem> getAnimeListfromDoc(ArrayList<Document> animeListDoc){
        ArrayList<AnimeListElem> animelist = new ArrayList<>();
        for(Document d : animeListDoc) {
            AnimeListElem elem = new AnimeListElem(d);
            animelist.add(elem);
        }
        return animelist;
    }

    public void printAnimeList(){
        for(AnimeListElem a : this.animeList)
            System.out.println("Anime: "+a.getTitle()+" Score: "+a.score);
    }

    public ArrayList<Document> removeAnimeListElement(String animeTitle){
        for(AnimeListElem e :animeList){
            if(animeTitle.equals(e.getTitle())){
                animeList.remove(e);
                System.out.println("removeAnimeListElement - Delete operation - OK");
                return createAnimeList(this.animeList);
            }
        }
        System.out.println("removeAnimeListElement - Delete operation - FAIL ");
        return null;
    }

    public ArrayList<Document> addNewAnimeListElement(AnimeListElem e){
        animeList.add(e);
        return createAnimeList(this.animeList);
    }

    public ArrayList<Document> addNewAnimeListElementFromList(String animeTitle){
        AnimeListElem e = new AnimeListElem(animeTitle, 0);
        animeList.add(e);
        return createAnimeList(this.animeList);
    }

    public ArrayList<Document> setAnimeScore(String animeTitle, int score){
        for(AnimeListElem e :animeList){
            if(animeTitle.equals(e.getTitle())){
                animeList.get(animeList.indexOf(e)).setScore(score);
                System.out.println("setAnimeScore - SET operation - OK");
                return createAnimeList(this.animeList);
            }
        }
        System.out.println("setAnimeScore - SET operation - FAIL ");
        return null;
    }

    public ArrayList<Document> createAnimeList(ArrayList<AnimeListElem> list){
        ArrayList<Document> listofAnime = new ArrayList<>();
        for(AnimeListElem a : list){
            Document rv = new Document("anime", a.getTitle())
                    .append("score", a.getScore());
            listofAnime.add(rv);
        }
        return listofAnime;
    }*/
}
