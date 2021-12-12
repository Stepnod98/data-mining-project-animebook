package it.unipi.dii.dm.exercises;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


/*
 Ex1 Working with WEKA Java Code II

 Write a Java program, which performs the following steps:

1. Reads a training and a test set from two specified file paths
2. Defines the Instances objects for the datasets and set the class index (use the setClassIndex method of the class Instances)
3. Defines and train a J48 decision tree without performing the pruning
4. Evaluates the accuracy of the decision tree both on the training and test set
5. Prints the results of the accuracy evaluations */


public class Classificazione {
    public static void main (String args[]) throws Exception
    {
        // Reading Training Data
        DataSource source = new DataSource("/Users/pietroducange/Desktop/CorsoDM/dataset/irisTrain.arff"); //C:\Program Files\Weka-3-8-5\data
        Instances train = source.getDataSet();
        train.setClassIndex(train.numAttributes() - 1);
        // Building the classifier
        String [] options =new String[1];
        options[0] = "-U";
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(train);
        // Evaluation on the training set
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(tree,train);
        System.out.println(eval.toSummaryString("Results Training:\n", false));
        // Evaluation on the test set
        DataSource source2 = new DataSource("/Users/pietroducange/Desktop/CorsoDM/dataset/irisTest.arff");
        Instances test = source2.getDataSet();
        test.setClassIndex(test.numAttributes() - 1);
        Evaluation evalTs = new Evaluation(train);
        evalTs.evaluateModel(tree,test);
        System.out.println(evalTs.toSummaryString("Results Test:\n", false));
        System.out.println(evalTs.toMatrixString());
        System.out.println(evalTs.pctCorrect());



    }
}
