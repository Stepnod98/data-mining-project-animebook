/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.datamining;

import it.unipi.dii.utils.Utility;

import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
/**
 *
 * @author Stefano
 */
public class Clustering {
    //Create kmeans clustering and returns all cluster members of the cluster assigned to the selected instance (the passed user)
    public static int[] kmeansAssignment (double[] elementCoords) throws Exception {
        HashMap<Integer, double[]> centroids = new HashMap<>();


        // Load CSV Dataset
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(Utility.csvDatasetPath));
        Instances dataset = loader.getDataSet();
        //System.out.println("TEST: "+dataset.numAttributes());

        //Remove unnecessary attributes
        Remove removeFilter = new Remove();
        removeFilter.setAttributeIndicesArray(Utility.indexes);
        removeFilter.setInputFormat(dataset);
        Instances clusteringDataset = Filter.useFilter(dataset, removeFilter);


        SimpleKMeans clusterer = new SimpleKMeans(); // new instance of clusterer
        clusterer.setPreserveInstancesOrder(true); //preserve order of every instance
        clusterer.setNumClusters(Utility.k); //K to be defined
        clusterer.buildClusterer(clusteringDataset); // build the clusterer

        //Create hashmap with all centroids coordinates
        for(int i = 0; i< Utility.k; i++){
            double[] centroidcoords;
            Instance a = clusterer.getClusterCentroids().get(i);
            centroidcoords = a.toDoubleArray();
            centroids.put(i, centroidcoords);
        }

        int[] assignments = clusterer.getAssignments();//get the array of cluster assignments
        double[] clusterDistribution = clusterer.getClusterSizes(); //get cluster distribution


        int cluster = Utility.assignCluster(elementCoords,centroids);//elementCoords double array

        //retrieve cluster elements
        int[] test = Utility.findClusterUsers(assignments, clusterDistribution[cluster], cluster);

        return test;
    }
}
