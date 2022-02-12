/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

//import org.bson.Document;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class User{

    private String username;
    public String email;
    public String pwd;
    public String firstName;
    public String lastName;
    private ArrayList<AnimeListElem> animeList;
    private List<Genre> genres;
    
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
    
    public User(String username, List<Genre> genres){
        this.username = username;
        this.genres = new ArrayList<>();
        this.genres = genres;
        this.animeList = new ArrayList<>();
    }
    
    public User(Document doc){
        this.username = doc.getString("profile");
        if((ArrayList<Document>)doc.get("animelist") != null)
            this.animeList = getAnimeListfromDoc((ArrayList<Document>)doc.get("animelist"));
        else
            this.animeList = new ArrayList<>();
        /*if((ArrayList<Document>)doc.get("genres") != null)
            this.genres = getAnimeListfromDoc((ArrayList<Document>)doc.get("genres"));
        else
            this.genres = new ArrayList<>();*/
    }

    public String getUsername(){return this.username;}
    
    public List<Genre> getGenres(){return this.genres;}
    

    public ArrayList<AnimeListElem> getAnimeList(){return this.animeList;}

    public ArrayList<AnimeListElem> getAnimeListfromDoc(ArrayList<Document> animeListDoc){
        ArrayList<AnimeListElem> animelist = new ArrayList<>();
        for(Document d : animeListDoc) {
            AnimeListElem elem = new AnimeListElem(d);
            animelist.add(elem);
        }
        return animelist;
    }

    /*public ArrayList<AnimeListElem> getGenresfromDoc(ArrayList<Document> genreDoc){
        ArrayList<AnimeListElem> genrelist = new ArrayList<>();
        for(Document d : genreDoc) {
            Genre elem = new Genre(d);
            genrelist.add(elem);
        }
        return genrelist;
    }*/

    public void printAnimeList(){
        for(AnimeListElem a : this.animeList)
            System.out.println("Anime: "+a.getTitle()+" Score: "+a.getScore());
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
    }

    public void setGenres(List<Genre> genres){
        this.genres = genres;
    }

    public void addAnimeGenres(List<Genre> animeGenres){
        int action = 0, adventure = 0, comedy = 0, demons = 0, drama = 0, fantasy = 0, game = 0,
                historical = 0, horror = 0, magic = 0, mecha = 0, military = 0, music = 0, mystery = 0,
                parody = 0, police = 0, psychological = 0, romance = 0, school = 0, scifi = 0, seinen = 0,
                shoujo = 0, shonen = 0, sol = 0, space = 0, sports = 0, superpower = 0,
                supernatural = 0, thriller = 0;
        for(Genre g: animeGenres){
            if(g.getName().equals("Adventure")){
                adventure = 1;
            }
            else if(g.getName().equals("Action")){
                action = 1;
            }
            else if(g.getName().equals("Comedy")){
                comedy = 1;
            }
            else if(g.getName().equals("Demons")){
                demons = 1;
            }
            else if(g.getName().equals("Drama")){
                drama = 1;
            }
            else if(g.getName().equals("Fantasy")){
                fantasy = 1;
            }
            else if(g.getName().equals("Game")){
                game = 1;
            }
            else if(g.getName().equals("Historical")){
                historical = 1;
            }
            else if(g.getName().equals("Horror")){
                horror = 1;
            }
            else if(g.getName().equals("Magic")){
                magic = 1;
            }
            else if(g.getName().equals("Mecha")){
                mecha = 1;
            }
            else if(g.getName().equals("Military")){
                military = 1;
            }
            else if(g.getName().equals("Music")){
                music = 1;
            }
            else if(g.getName().equals("Mystery")){
                mystery = 1;
            }
            else if(g.getName().equals("Parody")){
                parody = 1;
            }
            else if(g.getName().equals("Police")){
                police = 1;
            }
            else if(g.getName().equals("Psychological")){
                psychological = 1;
            }
            else if(g.getName().equals("Romance")){
                romance = 1;
            }
            else if(g.getName().equals("School")){
                school = 1;
            }
            else if(g.getName().equals("Sci-Fi")){
                scifi = 1;
            }
            else if(g.getName().equals("Seinen")){
                seinen = 1;
            }
            else if(g.getName().equals("Shoujo")){
                shoujo = 1;
            }
            else if(g.getName().equals("Shounen")){
                shonen = 1;
            }
            else if(g.getName().equals("Slice of Life")){
                sol = 1;
            }
            else if(g.getName().equals("Space")){
                space = 1;
            }
            else if(g.getName().equals("Sports")){
                sports = 1;
            }
            else if(g.getName().equals("Super Power")){
                superpower = 1;
            }
            else if(g.getName().equals("Supernatural")){
                supernatural = 1;
            }
            else if(g.getName().equals("Thriller")){
                thriller = 1;
            }
        }

        for(Genre g: genres){
            if(g.getName().equals("Adventure")){
                int newCount = g.getCount() + adventure;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Action")){
                int newCount = g.getCount() + action;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Comedy")){
                int newCount = g.getCount() + comedy;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Demons")){
                int newCount = g.getCount() + demons;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Drama")){
                int newCount = g.getCount() + drama;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Fantasy")){
                int newCount = g.getCount() + fantasy;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Game")){
                int newCount = g.getCount() + game;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Historical")){
                int newCount = g.getCount() + historical;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Horror")){
                int newCount = g.getCount() + horror;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Magic")){
                int newCount = g.getCount() + magic;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Mecha")){
                int newCount = g.getCount() + mecha;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Military")){
                int newCount = g.getCount() + military;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Music")){
                int newCount = g.getCount() +  music;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Mystery")){
                int newCount = g.getCount() + mystery;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Parody")){
                int newCount = g.getCount() + parody;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Police")){
                int newCount = g.getCount() + police;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Psychological")){
                int newCount = g.getCount() + psychological;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Romance")){
                int newCount = g.getCount() + romance;
                g.setCount(newCount);
            }
            else if(g.getName().equals("School")){
                int newCount = g.getCount() + school;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Sci-Fi")){
                int newCount = g.getCount() + scifi;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Seinen")){
                int newCount = g.getCount() + seinen;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Shoujo")){
                int newCount = g.getCount() + shoujo;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Shounen")){
                int newCount = g.getCount() + shonen;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Slice of Life")){
                int newCount = g.getCount() + sol;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Space")){
                int newCount = g.getCount() + space;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Sports")){
                int newCount = g.getCount() + sports;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Super Power")){
                int newCount = g.getCount() + superpower;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Supernatural")){
                int newCount = g.getCount() + supernatural;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Thriller")){
                int newCount = g.getCount() + thriller;
                g.setCount(newCount);
            }
        }
    }

    public void removeAnimeGenres(List<Genre> animeGenres){
        int action = 0, adventure = 0, comedy = 0, demons = 0, drama = 0, fantasy = 0, game = 0,
                historical = 0, horror = 0, magic = 0, mecha = 0, military = 0, music = 0, mystery = 0,
                parody = 0, police = 0, psychological = 0, romance = 0, school = 0, scifi = 0, seinen = 0,
                shoujo = 0, shonen = 0, sol = 0, space = 0, sports = 0, superpower = 0,
                supernatural = 0, thriller = 0;
        for(Genre g: animeGenres){
            if(g.getName().equals("Adventure")){
                adventure = 1;
            }
            else if(g.getName().equals("Action")){
                action = 1;
            }
            else if(g.getName().equals("Comedy")){
                comedy = 1;
            }
            else if(g.getName().equals("Demons")){
                demons = 1;
            }
            else if(g.getName().equals("Drama")){
                drama = 1;
            }
            else if(g.getName().equals("Fantasy")){
                fantasy = 1;
            }
            else if(g.getName().equals("Game")){
                game = 1;
            }
            else if(g.getName().equals("Historical")){
                historical = 1;
            }
            else if(g.getName().equals("Horror")){
                horror = 1;
            }
            else if(g.getName().equals("Magic")){
                magic = 1;
            }
            else if(g.getName().equals("Mecha")){
                mecha = 1;
            }
            else if(g.getName().equals("Military")){
                military = 1;
            }
            else if(g.getName().equals("Music")){
                music = 1;
            }
            else if(g.getName().equals("Mystery")){
                mystery = 1;
            }
            else if(g.getName().equals("Parody")){
                parody = 1;
            }
            else if(g.getName().equals("Police")){
                police = 1;
            }
            else if(g.getName().equals("Psychological")){
                psychological = 1;
            }
            else if(g.getName().equals("Romance")){
                romance = 1;
            }
            else if(g.getName().equals("School")){
                school = 1;
            }
            else if(g.getName().equals("Sci-Fi")){
                scifi = 1;
            }
            else if(g.getName().equals("Seinen")){
                seinen = 1;
            }
            else if(g.getName().equals("Shoujo")){
                shoujo = 1;
            }
            else if(g.getName().equals("Shounen")){
                shonen = 1;
            }
            else if(g.getName().equals("Slice of Life")){
                sol = 1;
            }
            else if(g.getName().equals("Space")){
                space = 1;
            }
            else if(g.getName().equals("Sports")){
                sports = 1;
            }
            else if(g.getName().equals("Super Power")){
                superpower = 1;
            }
            else if(g.getName().equals("Supernatural")){
                supernatural = 1;
            }
            else if(g.getName().equals("Thriller")){
                thriller = 1;
            }
        }

        for(Genre g: genres){
            if(g.getName().equals("Adventure")){
                int newCount = g.getCount() - adventure;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Action")){
                int newCount = g.getCount() - action;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Comedy")){
                int newCount = g.getCount() - comedy;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Demons")){
                int newCount = g.getCount() - demons;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Drama")){
                int newCount = g.getCount() - drama;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Fantasy")){
                int newCount = g.getCount() - fantasy;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Game")){
                int newCount = g.getCount() - game;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Historical")){
                int newCount = g.getCount() - historical;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Horror")){
                int newCount = g.getCount() - horror;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Magic")){
                int newCount = g.getCount() - magic;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Mecha")){
                int newCount = g.getCount() - mecha;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Military")){
                int newCount = g.getCount() - military;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Music")){
                int newCount = g.getCount() -  music;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Mystery")){
                int newCount = g.getCount() - mystery;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Parody")){
                int newCount = g.getCount() - parody;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Police")){
                int newCount = g.getCount() - police;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Psychological")){
                int newCount = g.getCount() - psychological;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Romance")){
                int newCount = g.getCount() - romance;
                g.setCount(newCount);
            }
            else if(g.getName().equals("School")){
                int newCount = g.getCount() - school;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Sci-Fi")){
                int newCount = g.getCount() - scifi;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Seinen")){
                int newCount = g.getCount() - seinen;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Shoujo")){
                int newCount = g.getCount() - shoujo;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Shounen")){
                int newCount = g.getCount() - shonen;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Slice of Life")){
                int newCount = g.getCount() - sol;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Space")){
                int newCount = g.getCount() - space;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Sports")){
                int newCount = g.getCount() - sports;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Super Power")){
                int newCount = g.getCount() - superpower;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Supernatural")){
                int newCount = g.getCount() - supernatural;
                g.setCount(newCount);
            }
            else if(g.getName().equals("Thriller")){
                int newCount = g.getCount() - thriller;
                g.setCount(newCount);
            }
        }
    }

}
