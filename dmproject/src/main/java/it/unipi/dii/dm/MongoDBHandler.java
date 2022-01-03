package it.unipi.dii.dm.exercises;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class MongoDBHandler {

    public static void connectionStarter(String connection, String database){
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
    }
}