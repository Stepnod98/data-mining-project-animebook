/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

import weka.clusterers.ClusterEvaluation;

import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;
import java.io.IOException;
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
        System.out.println("TEST: "+dataset.numAttributes());

        //Remove unnecessary attributes
        Remove removeFilter = new Remove();
        removeFilter.setAttributeIndicesArray(Utility.indexes);
        //removeFilter.setInvertSelection(true);
        removeFilter.setInputFormat(dataset);
        Instances clusteringDataset = Filter.useFilter(dataset, removeFilter);
        //System.out.println("TEST updated dataset: "+clusteringDataset.numAttributes());


        SimpleKMeans clusterer = new SimpleKMeans(); // new instance of clusterer
        clusterer.setPreserveInstancesOrder(true); //preserve order of every instance
        clusterer.setNumClusters(Utility.k); //K to be defined
        clusterer.buildClusterer(clusteringDataset); // build the clusterer

        //Create hashmap with all centroids coordinates
        for(int i = 0; i< Utility.k; i++){
            double[] centroidcoords;
            Instance a = clusterer.getClusterCentroids().get(i);
            centroidcoords = a.toDoubleArray();
            //System.out.println("TEST array: "+ Arrays.toString(centroidcoords));
            centroids.put(i, centroidcoords);
        }

        int[] assignments = clusterer.getAssignments();//get the array of cluster assignments
        double[] clusterDistribution = clusterer.getClusterSizes(); //get cluster distribution

        System.out.println("Info: "+Arrays.toString(assignments));
        System.out.println("Info: "+Arrays.toString(clusterDistribution));

        //Assignment test

        //double[] elementCoords = {10000,100000,10000,10000,10000,10000,110000,100000,100000,100002,100003,100002,1000056,100000,100001,100000,100000,100004,100002,100004,100000,100004,100001,100005,100001,100000,100000,100001,100000};
        //double[] elementCoords2 = {24.503039513677813, 13.196048632218845, 18.393617021276597, 13.55775075987842, 14.984802431610943, 15.448328267477203, 1.296352583586626, 5.655015197568389, 19.3951367781155, 6.300911854103344, 1.8920972644376899, 2.1322188449848025, 1.5288753799392096, 24.630699088145896, 1.2234042553191489, 3.3313069908814588, 13.045592705167174, 7.5, 5.355623100303951, 9.290273556231003, 7.968085106382978, 3.5851063829787235, 12.458966565349543, 3.907294832826748, 0.8085106382978723, 0.30243161094224924, 4.1534954407294835, 41.96656534954408, 6.7522796352583585};
        //double[] elementCoords = {10,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //piacciono action adventure

        int cluster = Utility.assignCluster(elementCoords,centroids);//elementCoords double array

        System.out.println("TEST assignment "+cluster);

        //retrieve cluster elements
        int[] test = Utility.findClusterUsers(assignments, clusterDistribution[cluster], cluster);

        System.out.println("Ho preso la roba giusta? "+Arrays.toString(test));
        return test;
    }

    /*public static void main (String args[]) throws Exception 
	{
	 // Reading Training Data 
		 DataSource source = new DataSource("/Users/pietroducange/Desktop/CorsoDM/dataset/irisTrain.arff");
	 Instances train = source.getDataSet();
	 train.setClassIndex(train.numAttributes() - 1);

	 Remove filter = new Remove(); 
	 filter.setAttributeIndices("" + (train.classIndex() + 1)); 
	 filter.setInputFormat(train);
	 Instances trainClus = Filter.useFilter(train, filter);
	  
	 // Building the Clusterer
	 SimpleKMeans clusterer = new SimpleKMeans(); // new instance of clusterer 
	 clusterer.setNumClusters(3);
	 clusterer.buildClusterer(trainClus); // build the clusterer
	 //Evaluation of the clusterer
	 ClusterEvaluation eval = new ClusterEvaluation(); 
	 eval.setClusterer(clusterer); 
	 eval.evaluateClusterer(train);
	System.out.println(eval.clusterResultsToString());
	
	 // Preparation of the unlabeled instances
	 DataSource source2 = new DataSource("/Users/pietroducange/Desktop/CorsoDM/dataset/irisTest.arff");
	 Instances test = source2.getDataSet();
	 test.setClassIndex(train.numAttributes() - 1);

	 Instances unlabeled = Filter.useFilter(test, filter);


	 
	 System.out.println("Unlabeled:\n");
	 System.out.println(unlabeled);

	
	
	//Assign a cluster to unlabeled instances
	 System.out.println("\nAssigning a cluster to instances:\n");

	 int clust;
	 for (int i = 0; i < unlabeled.numInstances();i++){
		 System.out.print("Instance ");
		 System.out.print(i);
		 System.out.print("\nEstimated Cluster: ");
		 try {
		 clust=clusterer.clusterInstance(unlabeled.instance(i));
		 System.out.println(clust);
		 }
		 catch (Exception ee){
			 System.out.println("Pattern not assigned!!!");

		 }
		 System.out.print("Actual Class: ");
		 System.out.println(test.instance(i).classValue());

	 }

	 
	}
    */
}
