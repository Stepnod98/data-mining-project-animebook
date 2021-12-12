package it.unipi.dii.dm.exercises;

import weka.attributeSelection.*;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

import java.util.Random;

public class FeatureSelectionNew {
    public static void main(String args[]) throws Exception{
        String [] names = new String[4];
        Instances[] vettTrain = new Instances [4];// vector of datasets
        Instances [] vettTest = new Instances [4];// vector of datasets

        double [] vettErrTr = new double [4];//vector for the classification errors on the training sets
        double [] vettErrTs = new double [4];//vector for the classification errors on the test sets

        double bestErrTr=100;//maximum value of the classification error
        double bestErrTs=100;//maximum value of the classification error

        //reading dataset
        ConverterUtils.DataSource src = new ConverterUtils.DataSource("C:\\Program Files\\Weka-3-8-5\\data\\irisTrain.arff");
        Instances data = src.getDataSet();
        data.randomize(new Random());
        Instances train = data.trainCV(5, 0);
        Instances test = data.testCV(5,0);
        train.setClassIndex(train.numAttributes()-1);
        test.setClassIndex(test.numAttributes()-1);

        //first method
        AttributeSelection filter1 = new AttributeSelection();
        CfsSubsetEval eval1 = new CfsSubsetEval();
        GreedyStepwise search1 = new GreedyStepwise();
        filter1.setEvaluator(eval1);
        filter1.setSearch(search1);
        filter1.setInputFormat(train);
        vettTrain[0] = Filter.useFilter(train, filter1);
        vettTest[0] = Filter.useFilter(test, filter1);
        names[0] = "Greedy";
        System.out.println(vettTrain[0]);
        System.out.println(vettTest[0]);
        //second method
        AttributeSelection filter2 = new AttributeSelection();
        PrincipalComponents eval2 = new PrincipalComponents();
        Ranker search2 = new Ranker();
        filter2.setEvaluator(eval2);
        filter2.setSearch(search2);
        vettTrain[1] = Filter.useFilter(train, filter2);
        vettTest[1] = Filter.useFilter(test, filter2);
        names[1] = "PCA";
        System.out.println(vettTrain[1]);
        System.out.println(vettTest[1]);
        //third method
        AttributeSelection filter3 = new AttributeSelection();
        InfoGainAttributeEval eval3 = new InfoGainAttributeEval();
        Ranker search3 = new Ranker();
        filter3.setEvaluator(eval3);
        filter3.setSearch(search3);
        vettTrain[2] = Filter.useFilter(train, filter3);
        vettTest[2] = Filter.useFilter(test, filter3);
        names[2] = "InfoGain";
        System.out.println(vettTrain[2]);
        System.out.println(vettTest[2]);

        vettTrain[3] = train;
        vettTest[3] = test;
        System.out.println(vettTrain[3]);
        System.out.println(vettTest[3]);
        names[3]="Original";

        int indBestTr = 0;
        int indBestTs = 0;

        for(int i = 0; i < 4; i++){
            vettTrain[i].setClassIndex(vettTrain[i].numAttributes()-1);
            vettTest[i].setClassIndex(vettTest[i].numAttributes()-1);
            J48 clsf = new J48();
            clsf.buildClassifier(vettTrain[i]);
            Evaluation evaltr = new Evaluation(vettTrain[i]);
            Evaluation evalts = new Evaluation(vettTest[i]);
            evaltr.evaluateModel(clsf, vettTrain[i]);
            evalts.evaluateModel(clsf, vettTest[i]);
            vettErrTr[i] = evaltr.pctIncorrect();
            vettErrTs[i] = evalts.pctIncorrect();
            if(vettErrTr[i] < bestErrTr){
                bestErrTr = vettErrTr[i];
                indBestTr = i;
            }
            if(vettErrTs[i] < bestErrTs){
                bestErrTs = vettErrTs[i];
                indBestTs = i;
            }
        }
        System.out.print("Errors on the Training set: ");
        for (int i=0; i<4; i++){
            System.out.print(vettErrTr[i]);
            System.out.print('\t');

        }

        System.out.print("\nErrors on the Test set: ");
        for (int i=0; i<4; i++){
            System.out.print(vettErrTs[i]);
            System.out.print('\t');
        }

        System.out.print("\nThe best feature selection algorithm (Training) is: ");
        System.out.println(names[indBestTr]);
        System.out.print("The Training Error is equal to: ");
        System.out.println(bestErrTr);

        System.out.print("The best feature selection algorithm (Test) is: ");
        System.out.println(names[indBestTs]);
        System.out.print("The Test Error is equal to: ");
        System.out.println(bestErrTs);


    }
}
