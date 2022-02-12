/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;
/*
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
*/

import java.util.*;

/**
 *
 * @author Stefano
 */
public class MongoDBManager {
    
   /*  public static void connectionStarter(String connection, String database){
        ConnectionString uri = new ConnectionString(connection);
        MongoClient mongoClient = MongoClients.create(uri);//senza argomento gira su localhost:27017
        MongoDatabase db = mongoClient.getDatabase(database);
    }

    //Method that finds a user, given his/her username
    public static User findUser(MongoDatabase db, String username){
        MongoCollection<Document> users = db.getCollection("profiles");
        Bson filter = eq("profile", username);
        try (MongoCursor<Document> cursor = users.find(filter).iterator()) {
            Document doc = cursor.next();
            return new User(doc);
        } catch (Exception ex) {ex.printStackTrace();}
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
        Bson filter = eq("title", title);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Anime(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    //Method that finds an anime, given it's uid
    public static Anime findAnime(MongoDatabase db, int uid){
        MongoCollection<Document> anime = db.getCollection("animes");
        Bson filter = eq("uid", uid);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Anime(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    //Method that stores a new animelist element to the corresponding user
    public static boolean storeAnimeListElement(MongoDatabase db, String userName, String animeTitle) {
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(db, userName);
        if(duplicatesChecker(target, animeTitle)){
            System.out.println("Anime already present");
            return false;
        }
        ArrayList<Document> updatedAnimeList = target.addNewAnimeListElementFromList(animeTitle);
        Bson filter = eq("profile", userName);
        Bson setter = set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(eq("profile", userName), set("animelist", updatedAnimeList));
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    //Method that deletes an animelist element from the corresponding user
    public static boolean removeAnimeListElement(MongoDatabase db, String userName, String animeTitle) {
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(db, userName);
        ArrayList<Document> updatedAnimeList = target.removeAnimeListElement(animeTitle);
        Bson filter = eq("profile", userName);
        Bson setter = set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(filter, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    //Method that update an anime score from a user animelist
    public static boolean updateAnimeScore(MongoDatabase db, String userName, String animeTitle, int score) {
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(db, userName);
        ArrayList<Document> updatedAnimeList = target.setAnimeScore(animeTitle, score);
        Bson filter = eq("profile", userName);
        Bson setter = set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(filter, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    //Method that checks if an anime is aldready present inside a user animelist
    public static boolean duplicatesChecker(User target, String animeTitle){
        for(AnimeListElem e : target.getAnimeList()){
            if(e.getTitle().equals(animeTitle))
                return true;
        }
        return false;
    }

    public static void connectionCloser(){
        mongoClient.close();
    }*/
    /*
    public static boolean checkUser(String username, String pwd){

        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username","login.password"));
        Bson filter = Filters.and(Filters.eq("login.username",username),Filters.eq("login.sha1",pwd));

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        return user.size() == 1;
         
    }
    
    public static boolean checkEmail(String email){

        MongoCollection<Document> collection = database.getCollection("users");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.email"));
        Bson filter = Filters.eq("login.email",email);

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        return user.size() == 1;
    }
    
    public static void addUser(User user){

        MongoCollection<Document> collection = database.getCollection("login");

        Document login = new Document("username", user.username).append("sha1", user.pwd);
        Document name = new Document("first", user.firstName).append("last", user.lastName);

        Document doc = new Document("name", name).append("email", user.email).append("login", login);

        collection.insertOne(doc);

    }
    
    */
    
    public static List getAnimeList(){
        List<AnimeListElem> mal = new ArrayList<>();
        /*
        MongoCollection<Document> collection = database.getCollection("profiles");

        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("title"));
        List<Document> animes = collection.find(Filter.eq("profile", GUIManager.getCurrentUser())).projection(projectionFields).into(new ArrayList<Document>());

        List<String> animeList = new ArrayList<>();
        for(int i = 0; i < animes.size(); i++){
            animeList.add(new AnimeListElem((String) animes.get(i).get("animelist).get("anime"),
                    (int) animes.get(i).get("animelist").get("score"));
        }
        return animeList;
        
        */
        return mal;
    }
    
    public static List getAnimes(){
        List<AnimeListElem> mal = new ArrayList<>();
        /*
        MongoCollection<Document> anime = db.getCollection("animes");
        List<Anime> foundAnime = new ArrayList<>();
        try (MongoCursor<Document> cursor = anime.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Anime a = new Anime(doc);
                foundAnime.add(a);
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return foundAnime;
        */
        return mal;
    }
    
    public static AnimeListElem findAnimeListElem(String t){
        AnimeListElem anime = new AnimeListElem(t, 9);
        /*
        
        */
        return anime;
    }
    
    public static List findAnimeList(String inTitle){
        //MongoCollection<Document> anime = database.getCollection("anime");
        List<String> animeList = new ArrayList<>();
        /*Bson filter = regex("title", inTitle);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            animeList.add(new Anime(doc).getTitle());
        } catch (Exception ex) {ex.printStackTrace();}*/
        return animeList;
    }
    
    
    public static void removeFromMal(String title){
        //MongoCollection<Document> users = db.getCollection("profiles");
        /*Document query = new Document().append("profile",  GUIManager.getCurrentUser());
        Bson update = pull("animelist.anime", title);
	try {
            UpdateResult result = users.updateOne(query, update);
        } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
        }
       */
    }
    
    public static void updateScore(String title, int score){
        //MongoCollection<Document> users = db.getCollection("profiles");
        /*User target = findUser(db, userName);
        ArrayList<Document> updatedAnimeList = target.setAnimeScore(animeTitle, score);
        Bson filter = eq("profile", GUIManager.getCurrentUser());
        Bson setter = set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(filter, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;*/
    }
    
    public static void addUser(User user){
        //MongoCollection<Document> users = db.getCollection("profiles");
        /*
        Document login = new Document("username", user.username).append("sha1", user.pwd);
        Document name = new Document("first", user.firstName).append("last", user.lastName);

        Document doc = new Document("name", name).append("email", user.email).append("login", login);

        collection.insertOne(doc);
        */
    }
    
    public static boolean checkAnime(String inTitle){
        //MongoCollection<Document> anime = database.getCollection("anime");
        //Bson filter = eq("title", inTitle);
        //List<Document> animelist = anime.find(filter).into(new ArrayList<Document>());
        //return animelist.size() == 1;
        //return false;
        return true;
    }
    
    public static void insertGenres(List genres){
        
    }
    
}
