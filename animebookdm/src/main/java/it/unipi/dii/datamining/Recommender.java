package it.unipi.dii.datamining;

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
}
