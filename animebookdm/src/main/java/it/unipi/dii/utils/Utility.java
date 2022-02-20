package it.unipi.dii.utils;
import java.util.*;

public class Utility {
    public static String csvDatasetPath = "C:/Users/Andrea/IdeaProjects/data-mining-project-animebook/animebookdm/db/UserDBWekaReady.csv";
    //public static String csvDatasetPath = "/Users/Stefano/IdeaProjects/animebookdm/db/UserDBWekaReady.csv";
    public static int[] indexes = {0,1};
    public static int k = 21; // k means value

    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public static int assignCluster(double[] coordinates, HashMap<Integer, double[]> centroids){
        double maxSimilarity = cosineSimilarity(coordinates, centroids.get(0));
        //System.out.println("distance cluster 0: "+maxSimilarity);
        int cluster = 0;
        for (int key = 1; key< centroids.size(); key++) {
            Double distanceCosine = cosineSimilarity(coordinates, centroids.get(key));
            //System.out.println("distance cluster"+key+" : "+distanceCosine);
            if(maxSimilarity < distanceCosine){
                cluster = key;
                maxSimilarity = distanceCosine;
            }
        }
        return cluster;
    }

    //Get all elements from that cluster
    public static int[] findClusterUsers(int[] clusterElements, double size, int clusterNumber){
        int users[] = new int[(int)size];
        int userIndex = 0;
        for(int i = 0; i<clusterElements.length; i++){
            if(clusterElements[i]==clusterNumber){
                users[userIndex] = i;
                userIndex++;
            }
        }
        return users;
    }

}
