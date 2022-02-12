/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

/**
 *
 * @author Stefano
 */
public class Clustering {
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
