package it.unipi.dii.datamining;/*import Entities.Anime;
import Entities.AnimeListElem;
import Entities.Genre;
import Entities.User;*/

import it.unipi.dii.entities.AnimeListElem;
import it.unipi.dii.dbmanager.MongoDBManager;
import it.unipi.dii.entities.User;

import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

public class Recommender {

    public static List<String> getMostPopularAnime(int userlist[]){ //counts most popular anime belonging to a cluster and returns them
        ArrayList<User> clusterUserList = new ArrayList<>(); //contains all users belonging to a cluster
        for(int index= 0 ; index < userlist.length; index++){ //retrieve every user belonging to the cluster
            clusterUserList.add(MongoDBManager.findUserbyUserID(userlist[index])); //get the user
        }
        List<String> mostPopularAnime = animeCounter(clusterUserList);
        return mostPopularAnime;
    }

    public static List<String> animeCounter(ArrayList<User> clusterUserList){
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
        for(User u : clusterUserList){ //iterates between all users belonging to a cluster
            for(AnimeListElem a : MongoDBManager.getUserAnimeList(u.getUsername())){ //iterates between every user's animelist
                if(freq.containsKey(a.getTitle())){//if the anime is already counted in the list, increase its counter by one
                    int occurrences = freq.get(a.getTitle());
                    freq.put(a.getTitle(), occurrences+1);
                }
                else{ //else add it to the list
                    freq.put(a.getTitle(), 1);
                }
            }
        }
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        freq.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        List<String> mostPopularAnime = reverseSortedMap.keySet().stream().limit(30).collect(Collectors.toList()); //take first ten elements

        return mostPopularAnime;
    }


    /*public static void main(String[] args) throws Exception {
        //---Connect to the MongoDB---
        ConnectionString uri = new ConnectionString("mongodb://localhost:27017");
        MongoClient mongoClient = MongoClients.create(uri); //no arguments means = localhost:27017
        MongoDatabase db = mongoClient.getDatabase("animebook");

        //double[] elementCoords = {10,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //piacciono action adventure

        ArrayList<String> genreList = new ArrayList<>();
*/
        /*genreList.add("Action");
        genreList.add("Adventure");*/
        //genreList.add("Comedy");
        //genreList.add("Demons");
        //genreList.add("Drama");
        /*genreList.add("Fantasy");
        genreList.add("Game");
        genreList.add("Historical");
        genreList.add("Horror");
        genreList.add("Magic");
        genreList.add("Mecha");
        genreList.add("Military");
        genreList.add("Music");
        genreList.add("Mystery");
        genreList.add("Parody");
        genreList.add("Police");
        genreList.add("Psychological");
        genreList.add("Romance");
        genreList.add("School");
        genreList.add("Sci-Fi");
        genreList.add("Seinen");
        genreList.add("Shoujo");
        genreList.add("Shounen");
        genreList.add("Slice of Life");
        genreList.add("Space");
        genreList.add("Sports");
        genreList.add("Super Power");
        genreList.add("Supernatural");
        genreList.add("Thriller");*/
/*
        double[] elementCoords = AccuracyModule.setElementCoords(genreList); //create dummy user genrelist

        System.out.println(Arrays.toString(elementCoords));

        int[] clusterElements = Clustering.kmeansAssignment(elementCoords); //returns all elements of the cluster in which the user has been assigned


        List<String> test= getMostPopularAnime(db, clusterElements); //get most popular anime of the cluster

        double accuracy = AccuracyModule.measureAccuracy(db, test, genreList); //measure the number of correct predicted elements by the clustering

        System.out.println("Accuracy = " +accuracy);

        mongoClient.close();
    }*/
}
