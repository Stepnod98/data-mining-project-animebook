/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Stefano
 */
public class GUIManager extends Application {
    private static AppLayout appLayout;
    private static AnimeListLayout animeListLayout;
    private static AnimeLayout animeLayout;
    private static LoginLayout loginLayout;
    private static AnimeListManager animeListManager;
    private static AnimeManager animeManager;
    private static LoginManager loginManager;
    private static User current_user;
    public static Scene scene;
    public static Group root;
    public static Pane p;
    public static Node[] newNode; //for cleaning issues

    @Override
    public void start(Stage stage){
        root = setUI();
        scene = new Scene(root, 800, 600);
        stage.setTitle("AnimeBook");
        stage.setScene(scene);
        loginManager = new LoginManager(loginLayout);
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
        animeManager.setEvents();
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
        animeListManager.setEvents();
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
        loginManager.setEvents();
    }
    
    public static void openSignUpManager(){
        root.getChildren().clear();
        loginLayout = new LoginLayout(true);
        Node[] tmp;
        tmp = loginLayout.getSignUpNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        loginManager = new LoginManager(loginLayout);
        loginManager.setSignUpEvents();
    }
    
    public static void openAnimeList(AnimeListLayout dm){
        root.getChildren().clear();
        animeListLayout = new AnimeListLayout();
        Node[] tmp;
        tmp = animeListLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        root.getChildren().add(dm.getTableNodes());
       
        animeListManager = new AnimeListManager(animeListLayout);
        animeListManager.setEvents();
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
        loginManager.setEvents();
        return root;
    } 
    
    public static String getCurrentUser(){
        return current_user.getUsername();
    }
    
    public static void main(String args[]){
        Application.launch(args);
    }
    
}
