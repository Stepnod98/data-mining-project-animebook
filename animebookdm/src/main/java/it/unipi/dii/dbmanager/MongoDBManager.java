/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.dbmanager;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import it.unipi.dii.entities.Anime;
import it.unipi.dii.entities.AnimeListElem;
import it.unipi.dii.controller.GUIManager;
import it.unipi.dii.entities.Genre;
import it.unipi.dii.entities.User;
import org.bson.Document;
import org.bson.conversions.Bson;


import java.util.*;

/**
 *
 * @author Stefano
 */
public class MongoDBManager {
    private static MongoClient mongoClient;
    private static MongoDatabase db;
    private MongoCollection<Document> collection;
    public static void connectionStarter(String connection, String database){
        ConnectionString uri = new ConnectionString(connection);
        mongoClient = MongoClients.create(uri);//senza argomento gira su localhost:27017
        db = mongoClient.getDatabase(database);
    }

    //Method that finds a user, given his/her username
    public static User findUser(String username){
        MongoCollection<Document> users = db.getCollection("profiles");
        Bson filter = Filters.eq("profile", username);
        try (MongoCursor<Document> cursor = users.find(filter).iterator()) {
            Document doc = cursor.next();
            return new User(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    public static User findUserbyUserID(int uid){
        MongoCollection<Document> users = db.getCollection("profiles");
        Bson filter = Filters.eq("userid", uid);
        try (MongoCursor<Document> cursor = users.find(filter).iterator()) {
            Document doc = cursor.next();
            return new User(doc);
        }
        catch(NoSuchElementException ex){return new User("Null");}
        catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    //Method that find first 50 anime
    public static ArrayList<Anime> browseAnime(MongoDatabase db){
        MongoCollection<Document> anime = db.getCollection("animes");
        ArrayList<Anime> foundAnime = new ArrayList<>();
        try (MongoCursor<Document> cursor = anime.find().limit(50).iterator()) {
            while (cursor.hasNext()) {//iterates between all reviews (A)
                Document doc = cursor.next();
                Anime a = new Anime(doc);
                foundAnime.add(a);
                String username = doc.getString("profile");
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return foundAnime;
    }

    //Method that finds an anime, given it's title
    public static Anime findAnime(MongoDatabase db, String title){
        MongoCollection<Document> anime = db.getCollection("animes");
        Bson filter = Filters.eq("title", title);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Anime(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    //Method that finds an anime, given it's uid
    public static Anime findAnime(MongoDatabase db, int uid){
        MongoCollection<Document> anime = db.getCollection("animes");
        Bson filter = Filters.eq("uid", uid);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Anime(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    //Method that deletes an animelist element from the corresponding user
    public static boolean removeAnimeListElement(String userName, String animeTitle) {
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(userName);
        ArrayList<Document> updatedAnimeList = target.removeAnimeListElement(animeTitle);
        Bson filter = Filters.eq("profile", userName);
        Bson setter = Updates.set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(filter, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    //Method that update an anime score from a user animelist
    public static boolean updateAnimeScore(String userName, String animeTitle, int score) {
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(userName);
        ArrayList<Document> updatedAnimeList = target.setAnimeScore(animeTitle, score);
        Bson filter = Filters.eq("profile", userName);
        Bson setter = Updates.set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(filter, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    public static boolean checkUser(String username, String pwd){

        MongoCollection<Document> collection = db.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username","login.password"));
        Bson filter = Filters.and(Filters.eq("login.username",username),Filters.eq("login.sha1",pwd));

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        return user.size() == 1;

    }

    public static boolean checkEmail(String email){

        MongoCollection<Document> collection = db.getCollection("profiles");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.email"));
        Bson filter = Filters.eq("login.email",email);

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        return user.size() == 1;
    }

    public static List<AnimeListElem> getAnimeList(){
        List<AnimeListElem> mal = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection("profiles");

        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("animelist"));
        try(MongoCursor<Document> cursor = collection.find(Filters.eq("profile", GUIManager.getCurrentUser())).projection(projectionFields).iterator()){
            Document doc = cursor.next();
            System.out.println(doc);
            List<Document> animelist = doc.getList("animelist", Document.class);
            System.out.println(animelist);
            for(int i = 0; i < animelist.size(); i++) {
                mal.add(new AnimeListElem(animelist.get(i).getString("anime"), animelist.get(i).getInteger("score")));
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return mal;
    }

    public static List<AnimeListElem> getUserAnimeList(String username){
        List<AnimeListElem> mal = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection("profiles");

        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("animelist"));
        try(MongoCursor<Document> cursor = collection.find(Filters.eq("profile", username)).projection(projectionFields).iterator()){
            Document doc = cursor.next();
            System.out.println(doc);
            List<Document> animelist = doc.getList("animelist", Document.class);
            System.out.println(animelist);
            for(int i = 0; i < animelist.size(); i++) {
                mal.add(new AnimeListElem(animelist.get(i).getString("anime"), animelist.get(i).getInteger("score")));
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return mal;
    }

    public static List getAnimes(){
        MongoCollection<Document> anime = db.getCollection("animes");
        List<String> foundAnime = new ArrayList<>();
        try (MongoCursor<Document> cursor = anime.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                foundAnime.add(doc.getString("title"));
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return foundAnime;
    }


    //Method that stores a new animelist element to the corresponding user
    public static boolean storeAnimeListElement(String userName, String animeTitle, int score) {
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(userName);
        if(duplicatesChecker(target, animeTitle)){
            System.out.println("Anime already present");
            return false;
        }
        ArrayList<Document> updatedAnimeList = target.addNewAnimeListElementFromList(animeTitle);
        updatedAnimeList = target.setAnimeScore(animeTitle, score);
        Bson filter = Filters.eq("profile", userName);
        Bson setter = Updates.set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(Filters.eq("profile", userName), Updates.set("animelist", updatedAnimeList));
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    //Method that checks if an anime is aldready present inside a user animelist
    public static boolean duplicatesChecker(User target, String animeTitle){
        if(target.getAnimeList() == null){
            return false;
        }
        for(AnimeListElem e : target.getAnimeList()){
            if(e.getTitle().equals(animeTitle))
                return true;
        }
        return false;
    }

    public static List findAnimeList(String inTitle){
        MongoCollection<Document> anime = db.getCollection("animes");
        List<String> animeList = new ArrayList<>();
        if(inTitle.length() < 4){
            return animeList;
        }
        Bson filter = Filters.regex("title", inTitle);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            while(cursor.hasNext()){
                Document doc = cursor.next();
                animeList.add(doc.getString("title"));
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return animeList;
    }

    public static int getAnimeEps(String inTitle){
        MongoCollection<Document> anime = db.getCollection("animes");
        int eps;
        Bson filter = Filters.eq("title", inTitle);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            eps = doc.getInteger("episodes");
            return eps;
        } catch (Exception ex) {ex.printStackTrace();}
        return 0;
    }

    public static int getAnimeUserScore(String inTitle){
        MongoCollection<Document> anime = db.getCollection("animes");
        int score;
        Bson filter = Filters.eq("title", inTitle);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            score = doc.getInteger("score");
            return score;
        } catch (Exception ex) {ex.printStackTrace();}
        return 0;
    }

    public static int getAnimeMembers(String inTitle){
        MongoCollection<Document> anime = db.getCollection("animes");
        int members;
        Bson filter = Filters.eq("title", inTitle);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            members = doc.getInteger("members");
            return members;
        } catch (Exception ex) {ex.printStackTrace();}
        return 0;
    }


    public static void removeFromMal(String title){
        MongoCollection<Document> users = db.getCollection("profiles");
        Document query = new Document().append("profile",  GUIManager.getCurrentUser());
        Bson update = Updates.pull("animelist", new Document("anime", title));
        try {
            UpdateResult result = users.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void updateScore(String animeTitle, int score){
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(GUIManager.getCurrentUser());
        ArrayList<Document> updatedAnimeList = target.setAnimeScore(animeTitle, score);
        Bson filter = Filters.eq("profile", GUIManager.getCurrentUser());
        Bson setter = Updates.set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(filter, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            //return false;
        }
        //return true;
    }

    public static void addUser(User user){
        MongoCollection<Document> users = db.getCollection("login");
        Document login = new Document("username", user.getUsername()).append("sha1", user.pwd);
        Document name = new Document("first", user.firstName).append("last", user.lastName);

        Document doc = new Document("name", name).append("email", user.email).append("login", login);

        users.insertOne(doc);
    }

    public static void insertProfile(User user){
        MongoCollection<Document> users = db.getCollection("profiles");
        int action = 0, adventure = 0, comedy = 0, demons = 0, drama = 0, fantasy = 0, game = 0,
                historical = 0, horror = 0, magic = 0, mecha = 0, military = 0, music = 0, mystery = 0,
                parody = 0, police = 0, psychological = 0, romance = 0, school = 0, scifi = 0, seinen = 0,
                shoujo = 0, shonen = 0, sol = 0, space = 0, sports = 0, superpower = 0,
                supernatural = 0, thriller = 0;
        List<Genre> genres = user.getGenres();
        for(Genre g: genres){
            if(g.getName().equals("Adventure")){
                adventure = 10;
            }
            else if(g.getName().equals("Action")){
                action = 10;
            }
            else if(g.getName().equals("Comedy")){
                comedy = 10;
            }
            else if(g.getName().equals("Demons")){
                demons = 10;
            }
            else if(g.getName().equals("Drama")){
                drama = 10;
            }
            else if(g.getName().equals("Fantasy")){
                fantasy = 10;
            }
            else if(g.getName().equals("Game")){
                game = 10;
            }
            else if(g.getName().equals("Historical")){
                historical = 10;
            }
            else if(g.getName().equals("Horror")){
                horror = 10;
            }
            else if(g.getName().equals("Magic")){
                magic = 10;
            }
            else if(g.getName().equals("Mecha")){
                mecha = 10;
            }
            else if(g.getName().equals("Military")){
                military = 10;
            }
            else if(g.getName().equals("Music")){
                music = 10;
            }
            else if(g.getName().equals("Mystery")){
                mystery = 10;
            }
            else if(g.getName().equals("Parody")){
                parody = 10;
            }
            else if(g.getName().equals("Police")){
                police = 10;
            }
            else if(g.getName().equals("Psychological")){
                psychological = 10;
            }
            else if(g.getName().equals("Romance")){
                romance = 10;
            }
            else if(g.getName().equals("School")){
                school = 10;
            }
            else if(g.getName().equals("Sci-Fi")){
                scifi = 10;
            }
            else if(g.getName().equals("Seinen")){
                seinen = 10;
            }
            else if(g.getName().equals("Shoujo")){
                shoujo = 10;
            }
            else if(g.getName().equals("Shounen")){
                shonen = 10;
            }
            else if(g.getName().equals("Slice of Life")){
                sol = 10;
            }
            else if(g.getName().equals("Space")){
                space = 10;
            }
            else if(g.getName().equals("Sports")){
                sports = 10;
            }
            else if(g.getName().equals("Superpower")){
                superpower = 10;
            }
            else if(g.getName().equals("Supernatural")){
                supernatural = 10;
            }
            else if(g.getName().equals("Thriller")){
                thriller = 10;
            }
        }
        Document genre = new Document("Action", action).append("Adventure", adventure)
                .append("Comedy", comedy).append("Demons", demons).append("Drama", drama)
                .append("Fantasy", fantasy).append("Game", game).append("Historical", historical)
                .append("Horror", horror).append("Magic", magic).append("Mecha", mecha)
                .append("Military", military).append("Music", music).append("Mystery", mystery)
                .append("Parody", parody).append("Police", police).append("Psychological", psychological)
                .append("Romance", romance).append("School", school).append("Sci-Fi", scifi)
                .append("Seinen", seinen).append("Shoujo", shoujo).append("Shounen", shonen)
                .append("Slice of Life", sol).append("Space", space).append("Sports", sports)
                .append("Super Power", superpower).append("Supernatural", supernatural).append("Thriller", thriller);

        Document doc = new Document("profile", user.getUsername())//.append("animelist", new Arraylist<AnimeListElem>())
                .append("genres", genre);

        users.insertOne(doc);
    }

    public static void updateGenres(List<Genre> genres){
        MongoCollection<Document> users = db.getCollection("profiles");
        int action = 0, adventure = 0, comedy = 0, demons = 0, drama = 0, fantasy = 0, game = 0,
                historical = 0, horror = 0, magic = 0, mecha = 0, military = 0, music = 0, mystery = 0,
                parody = 0, police = 0, psychological = 0, romance = 0, school = 0, scifi = 0, seinen = 0,
                shoujo = 0, shonen = 0, sol = 0, space = 0, sports = 0, superpower = 0,
                supernatural = 0, thriller = 0;
        for(Genre g: genres){
            if(g.getName().equals("Adventure")){
                adventure = g.getCount();
            }
            else if(g.getName().equals("Action")){
                action = g.getCount();
            }
            else if(g.getName().equals("Comedy")){
                comedy = g.getCount();
            }
            else if(g.getName().equals("Demons")){
                demons = g.getCount();
            }
            else if(g.getName().equals("Drama")){
                drama = g.getCount();
            }
            else if(g.getName().equals("Fantasy")){
                fantasy = g.getCount();
            }
            else if(g.getName().equals("Game")){
                game = g.getCount();
            }
            else if(g.getName().equals("Historical")){
                historical = g.getCount();
            }
            else if(g.getName().equals("Horror")){
                horror = g.getCount();
            }
            else if(g.getName().equals("Magic")){
                magic = g.getCount();
            }
            else if(g.getName().equals("Mecha")){
                mecha = g.getCount();
            }
            else if(g.getName().equals("Military")){
                military = g.getCount();
            }
            else if(g.getName().equals("Music")){
                music = g.getCount();
            }
            else if(g.getName().equals("Mystery")){
                mystery = g.getCount();
            }
            else if(g.getName().equals("Parody")){
                parody = g.getCount();
            }
            else if(g.getName().equals("Police")){
                police = g.getCount();
            }
            else if(g.getName().equals("Psychological")){
                psychological = g.getCount();
            }
            else if(g.getName().equals("Romance")){
                romance = g.getCount();
            }
            else if(g.getName().equals("School")){
                school = g.getCount();
            }
            else if(g.getName().equals("Sci-Fi")){
                scifi = g.getCount();
            }
            else if(g.getName().equals("Seinen")){
                seinen = g.getCount();
            }
            else if(g.getName().equals("Shoujo")){
                shoujo = g.getCount();
            }
            else if(g.getName().equals("Shounen")){
                shonen = g.getCount();
            }
            else if(g.getName().equals("Slice of Life")){
                sol = g.getCount();
            }
            else if(g.getName().equals("Space")){
                space = g.getCount();
            }
            else if(g.getName().equals("Sports")){
                sports = g.getCount();
            }
            else if(g.getName().equals("Super Power")){
                superpower = g.getCount();
            }
            else if(g.getName().equals("Supernatural")){
                supernatural = g.getCount();
            }
            else if(g.getName().equals("Thriller")){
                thriller = g.getCount();
            }
        }
        Document genre = new Document("Action", action).append("Adventure", adventure)
                .append("Comedy", comedy).append("Demons", demons).append("Drama", drama)
                .append("Fantasy", fantasy).append("Game", game).append("Historical", historical)
                .append("Horror", horror).append("Magic", magic).append("Mecha", mecha)
                .append("Military", military).append("Music", music).append("Mystery", mystery)
                .append("Parody", parody).append("Police", police).append("Psychological", psychological)
                .append("Romance", romance).append("School", school).append("Sci-Fi", scifi)
                .append("Seinen", seinen).append("Shoujo", shoujo).append("Shounen", shonen)
                .append("Slice of Life", sol).append("Space", space).append("Sports", sports)
                .append("Super Power", superpower).append("Supernatural", supernatural).append("Thriller", thriller);
        Document query = new Document().append("profile",  GUIManager.getCurrentUser());
        Bson update = Updates.combine(Updates.set("genres", genre));
        try {
            UpdateResult result = users.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static boolean checkAnime(String inTitle){
        MongoCollection<Document> anime = db.getCollection("animes");
        Bson filter = Filters.eq("title", inTitle);
        List<Document> animelist = anime.find(filter).into(new ArrayList<Document>());
        return animelist.size() == 1;
    }

    public static boolean checkAnimeinList(String inTitle){
        MongoCollection<Document> anime = db.getCollection("profiles");
        List<String> mal = new ArrayList<>();
        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("animelist"));
        try(MongoCursor<Document> cursor = anime.find(Filters.eq("profile", GUIManager.getCurrentUser())).projection(projectionFields).iterator()){
            Document doc = cursor.next();
            List<Document> animelist = doc.getList("animelist", Document.class);
            for(int i = 0; i < animelist.size(); i++) {
                if(inTitle.equals(animelist.get(i).getString("anime"))) {
                    mal.add(animelist.get(i).getString("anime"));
                }
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return mal.size() == 1;
    }

    public static List<Genre> getGenres(String title){
        MongoCollection<Document> animes = db.getCollection("animes");
        List<Genre> genres = new ArrayList<>();
        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("genres"));
        Bson filter = Filters.eq("title", title);
        try (MongoCursor<Document> cursor = animes.find(filter).projection(projectionFields).iterator()){
            Document d = cursor.next();
            Document doc = (Document) d.get("genres");
            if(doc.getBoolean("Action")){
                genres.add(new Genre("Action", 1));
            }
            if(doc.getBoolean("Adventure")){
                genres.add(new Genre("Adventure", 1));
            }
            if(doc.getBoolean("Comedy")){
                genres.add(new Genre("Comedy", 1));
            }
            if(doc.getBoolean("Demons")){
                genres.add(new Genre("Demons", 1));
            }
            if(doc.getBoolean("Drama")){
                genres.add(new Genre("Drama", 1));
            }
            if(doc.getBoolean("Fantasy")){
                genres.add(new Genre("Fantasy", 1));
            }
            if(doc.getBoolean("Game")){
                genres.add(new Genre("Game", 1));
            }
            if(doc.getBoolean("Historical")){
                genres.add(new Genre("Historical", 1));
            }
            if(doc.getBoolean("Horror")){
                genres.add(new Genre("Horror", 1));
            }
            if(doc.getBoolean("Magic")){
                genres.add(new Genre("Magic", 1));
            }
            if(doc.getBoolean("Mecha")){
                genres.add(new Genre("Mecha", 1));
            }
            if(doc.getBoolean("Military")){
                genres.add(new Genre("Military", 1));
            }
            if(doc.getBoolean("Music")){
                genres.add(new Genre("Music", 1));
            }
            if(doc.getBoolean("Mystery")){
                genres.add(new Genre("Mystery", 1));
            }
            if(doc.getBoolean("Parody")){
                genres.add(new Genre("Parody", 1));
            }
            if(doc.getBoolean("Police")){
                genres.add(new Genre("Police", 1));
            }
            if(doc.getBoolean("Psychological")){
                genres.add(new Genre("Psychological", 1));
            }
            if(doc.getBoolean("Romance")){
                genres.add(new Genre("Romance", 1));
            }
            if(doc.getBoolean("School")){
                genres.add(new Genre("School", 1));
            }
            if(doc.getBoolean("Sci-Fi")){
                genres.add(new Genre("Sci-Fi", 1));
            }
            if(doc.getBoolean("Seinen")){
                genres.add(new Genre("Seinen", 1));
            }
            if(doc.getBoolean("Shoujo")){
                genres.add(new Genre("Shoujo", 1));
            }
            if(doc.getBoolean("Shounen")){
                genres.add(new Genre("Shounen", 1));
            }
            if(doc.getBoolean("Slice of Life")){
                genres.add(new Genre("Slice of Life", 1));
            }
            if(doc.getBoolean("Space")){
                genres.add(new Genre("Space", 1));
            }
            if(doc.getBoolean("Sports")){
                genres.add(new Genre("Sports", 1));
            }
            if(doc.getBoolean("Super Power")){
                genres.add(new Genre("Super Power", 1));
            }
            if(doc.getBoolean("Supernatural")){
                genres.add(new Genre("Supernatural", 1));
            }
            if(doc.getBoolean("Thriller")){
                genres.add(new Genre("Thriller", 1));
            }

        }
        catch(Exception ex){ex.printStackTrace();}
        return genres;
    }

    public static List<Genre> getUserGenres(){
        MongoCollection<Document> animes = db.getCollection("profiles");
        List<Genre> genres = new ArrayList<>();
        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("genres"));
        Bson filter = Filters.eq("profile", GUIManager.getCurrentUser());
        try (MongoCursor<Document> cursor = animes.find(filter).projection(projectionFields).iterator()){
            Document d = cursor.next();
            Document doc = (Document) d.get("genres");
            genres.add(new Genre("Action", doc.getInteger("Action")));
            genres.add(new Genre("Adventure", doc.getInteger("Adventure")));
            genres.add(new Genre("Comedy", doc.getInteger("Comedy")));
            genres.add(new Genre("Demons", doc.getInteger("Demons")));
            genres.add(new Genre("Drama", doc.getInteger("Drama")));
            genres.add(new Genre("Fantasy", doc.getInteger("Fantasy")));
            genres.add(new Genre("Game", doc.getInteger("Game")));
            genres.add(new Genre("Historical", doc.getInteger("Historical")));
            genres.add(new Genre("Horror", doc.getInteger("Horror")));
            genres.add(new Genre("Magic", doc.getInteger("Magic")));
            genres.add(new Genre("Mecha", doc.getInteger("Mecha")));
            genres.add(new Genre("Military", doc.getInteger("Military")));
            genres.add(new Genre("Music", doc.getInteger("Music")));
            genres.add(new Genre("Mystery", doc.getInteger("Mystery")));
            genres.add(new Genre("Parody", doc.getInteger("Parody")));
            genres.add(new Genre("Police", doc.getInteger("Police")));
            genres.add(new Genre("Psychological", doc.getInteger("Psychological")));
            genres.add(new Genre("Romance", doc.getInteger("Romance")));
            genres.add(new Genre("School", doc.getInteger("School")));
            genres.add(new Genre("Sci-Fi", doc.getInteger("Sci-Fi")));
            genres.add(new Genre("Seinen", doc.getInteger("Seinen")));
            genres.add(new Genre("Shoujo", doc.getInteger("Shoujo")));
            genres.add(new Genre("Shounen", doc.getInteger("Shounen")));
            genres.add(new Genre("Slice of Life", doc.getInteger("Slice of Life")));
            genres.add(new Genre("Space", doc.getInteger("Space")));
            genres.add(new Genre("Sports", doc.getInteger("Sports")));
            genres.add(new Genre("Super Power", doc.getInteger("Super Power")));
            genres.add(new Genre("Supernatural", doc.getInteger("Supernatural")));
            genres.add(new Genre("Thriller", doc.getInteger("Thriller")));
        }
        catch(Exception ex){ex.printStackTrace();}
        return genres;
    }

    public static void connectionCloser(){
        mongoClient.close();
    }

}
