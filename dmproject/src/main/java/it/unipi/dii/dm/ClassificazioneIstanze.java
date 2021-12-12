package it.unipi.dii.dm.exercises;

/* Ex2: Working with WEKA Java Code II

 * Write a Java program, which performs the following steps:

1. By using the test set previously loaded, generates an unlabeled dataset (use the setClassMissing method of the class Instance)

2. Classifies each instance of the unlabeled dataset by using the classifier defined in Exercise I

3. For each instance, prints the actual and the estimated class


 */

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassificazioneIstanze {
    public static void main(String args[]) throws Exception{
        DataSource src = new DataSource("C:\\Program Files\\Weka-3-8-5\\data\\irisTrain.arff");
        Instances train = src.getDataSet();
        train.setClassIndex(train.numAttributes() - 1);
        // Building the classifier
        String [] options = new String[1];
        options[0] = "-U";
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(train);
        DataSource src2 = new DataSource("C:\\Program Files\\Weka-3-8-5\\data\\irisTest.arff");
        Instances test = src2.getDataSet();
        test.setClassIndex(test.numAttributes() - 1);
        Instances unlabeled = new Instances (test);
        for(int i = 0; i < test.numInstances(); i++){
            unlabeled.instance(i).setClassMissing();
        }
        System.out.println("Unlabeled:\n");
        System.out.println(unlabeled);

        //Classifying unlabeled instances
        System.out.println("\nClassifying instances:\n");

        for (int i = 0; i < unlabeled.numInstances();i++){
            System.out.print("Instance ");
            System.out.print(i);
            System.out.print("\nEstimated Class: ");
            System.out.println(tree.classifyInstance(unlabeled.instance(i)));
            System.out.print("Actual Class: ");
            System.out.println(test.instance(i).classValue());

        }
    }
}
