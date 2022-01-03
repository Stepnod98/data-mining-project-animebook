package it.unipi.dii.dm.exercises.entities;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class Anime {

    public int uid;
    public String title;
    public int episodes;
    public int members;
    public int popularity;
    public int score;
    public ArrayList<Genre> genres;

    public Anime(Document doc, ArrayList<Genre> genres){
        this.uid = doc.getInteger("uid");
        this.title = doc.getString("title");
        this.episodes = doc.getInteger("episodes");
        this.members = doc.getInteger("members");
        this.popularity = doc.getInteger("popularity");
        this.score = doc.getInteger("score");
        this.genres = genres;
    }

    public Anime(Document doc){
        this.uid = doc.getInteger("uid");
        this.title = doc.getString("title");
        this.episodes = doc.getInteger("episodes");
        this.members = doc.getInteger("members");
        this.popularity = doc.getInteger("popularity");
        this.score = doc.getInteger("score");
        this.genres = genreExtractor((Document)doc.get("genres"));
    }

    public ArrayList<Genre> genreExtractor(Document genreList){//Devo passargli la lista dei generi
        ArrayList<Genre> tags = new ArrayList<>();
        ArrayList<String> tagList = createTags();
        for(String selectedGenre : tagList){
            Genre temp = new Genre(selectedGenre, genreList.getBoolean(selectedGenre));
            tags.add(temp);
        }
        return tags;
    }

    public void printGenres(){
        System.out.println("Genres:");
        for(Genre gen : this.genres)
            System.out.println(gen.name+": "+gen.value);
    }

    public void setGenres(String name){
        int index = this.genres.indexOf(name);
        this.genres.get(index).setValue();
    }

    public int getUid(){return this.uid;}

    public String getTitle(){return this.title;}

    public int getEpisodes(){return this.episodes;}

    public int getMembers(){return this.members;}

    public int getPopularity(){return this.popularity;}

    public int getScore(){return this.score;}

    public ArrayList<Genre> getGenres() {
        return this.genres;
    }

    private ArrayList<String> createTags(){
        ArrayList<String> taglist = new ArrayList<>();
        taglist.add("Action");
        taglist.add("Adventure");
        taglist.add("Cars");
        taglist.add("Comedy");
        taglist.add("Dementia");
        taglist.add("Demons");
        taglist.add("Drama");
        taglist.add("Ecchi");
        taglist.add("Fantasy");
        taglist.add("Game");
        taglist.add("Harem");
        taglist.add("Hentai");
        taglist.add("Historical");
        taglist.add("Horror");
        taglist.add("Josei");
        taglist.add("Kids");
        taglist.add("Magic");
        taglist.add("Martial Arts");
        taglist.add("Mecha");
        taglist.add("Military");
        taglist.add("Music");
        taglist.add("Mystery");
        taglist.add("Parody");
        taglist.add("Police");
        taglist.add("Psychological");
        taglist.add("Romance");
        taglist.add("Samurai");
        taglist.add("School");
        taglist.add("Sci-Fi");
        taglist.add("Seinen");
        taglist.add("Shoujo");
        taglist.add("Shoujo Ai");
        taglist.add("Shounen");
        taglist.add("Shounen Ai");
        taglist.add("Slice of Life");
        taglist.add("Space");
        taglist.add("Sports");
        taglist.add("Super Power");
        taglist.add("Supernatural");
        taglist.add("Thriller");
        taglist.add("Vampire");
        taglist.add("Yaoi");
        taglist.add("Yuri");

        return taglist;
    }

}
