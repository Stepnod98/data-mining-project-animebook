/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Stefano
 */
public class GUIManager extends Application {
    private static AppLayout appLayout;
    private static AppLayoutManager appManager;
    private static AnimeListLayout animeListLayout;
    private static AnimeLayout animeLayout;
    private static LoginLayout loginLayout;
    private static AnimeListManager animeListManager;
    private static AnimeManager animeManager;
    private static LoginManager loginManager;
    private static GenreSelection genreSelection;
    private static GenreSelectionManager genreSelectionManager;
    private static SignUpLayout signUpLayout;
    private static SignUpManager signUpManager;
    private static User current_user;
    public static Scene scene;
    public static Group root;
    public static Pane p;
    public static Node[] newNode; //for cleaning issues

    @Override
    public void start(Stage stage){
        root = setUI();
        scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("file:style/loginStyle.css");
        scene.setFill(Color.DARKCYAN);
        stage.setTitle("AnimeBook");
        stage.setScene(scene);
        loginManager = new LoginManager(loginLayout);
        MongoDBManager.connectionStarter("mongodb://localhost:27017", "animebook");
        stage.setOnCloseRequest(e -> MongoDBManager.connectionCloser());
        stage.show();
    }
    public static void openAppManager(){
        root.getChildren().clear();
        appLayout = new AppLayout();
        Node[] tmp;
        tmp = appLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:style/animeStyle.css");
        scene.setFill(Color.LIGHTBLUE);
        appManager = new AppLayoutManager(appLayout, current_user);
    }
    
    public static void openAnimeManager(){
        root.getChildren().clear();
        animeLayout = new AnimeLayout();
        Node[] tmp;
        tmp = animeLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        animeManager = new AnimeManager(animeLayout);
    }
    
     
    public static void openAnimeList(){
        root.getChildren().clear();
        animeListLayout = new AnimeListLayout();
        Node[] tmp;
        tmp = animeListLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        animeListManager = new AnimeListManager(animeListLayout);
    }
    
    
    public static void openLoginManager(){
        root.getChildren().clear();
        loginLayout = new LoginLayout();
        Node[] tmp;
        tmp = loginLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        loginManager = new LoginManager(loginLayout);
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:style/loginStyle.css");
        scene.setFill(Color.DARKCYAN);
    }
    
    public static void openSignUpManager(){
        root.getChildren().clear();
        signUpLayout = new SignUpLayout();
        Node[] tmp;
        tmp = signUpLayout.getSignUpNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        signUpManager = new SignUpManager(signUpLayout);
    }
    
    public static void openGenreSelection(){
        root.getChildren().clear();
        genreSelection = new GenreSelection();
        List<Node> tmp = new ArrayList<>();
        tmp = genreSelection.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        genreSelectionManager = new GenreSelectionManager(genreSelection, current_user);
        GenreSelectionManager.setEvents();
       
    }
    
    public static void openGenreSelectionEditor(){
        root.getChildren().clear();
        genreSelection = new GenreSelection();
        List<Node> tmp = new ArrayList<>();
        tmp = genreSelection.getUNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        genreSelectionManager = new GenreSelectionManager(genreSelection, current_user);
        GenreSelectionManager.setSettingsEvents();
       
    }
    
    public static void addNode(Node node){
        root.getChildren().add(node);
    }

    public static  void removeNode(Node node){
        root.getChildren().remove(node);
    }
    
    public static void clearAnimeBoxes(){
        if(animeLayout.getAnimeBox() != null){
            removeNode(animeLayout.getAnimeBox());
        }
    }
    
    public static Group setUI() {
        loginLayout = new LoginLayout();
        Group root = new Group();
        Node[] tmp;

        tmp = loginLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        loginManager = new LoginManager(loginLayout);
        return root;
    } 
    
    public static String getCurrentUser(){
        return current_user.getUsername();
    }


    public static User getCurrent(){
        return current_user;
    }
    
    public static void setCurrentUser(User user){
        current_user = user;
    }
    
    public static void main(String args[]){
        Application.launch(args);
    }
    
}
