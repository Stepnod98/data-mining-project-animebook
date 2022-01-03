/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Stefano
 */
public class UserLayout {
    private Label title;
    private Label insertUser;
    private static TextField username;
    private static TextField password;

    public UserLayout() {
    	title = new Label("SNAKE");
        title.setId("title");
        title.setLayoutX(10);
        title.setLayoutY(10);
        insertUser = new Label("Insert username:");
        insertUser.setId("insertUser");
        insertUser.setLayoutX(400);
        insertUser.setLayoutY(10);
        username = new TextField("USER NAME");
    	username.setLayoutY(30);
    	username.setLayoutX(400);
    	username.setMaxWidth(200);
        username.setFocusTraversable(false);
        password = new TextField("PASSWORD");
    	password.setLayoutY(30);
    	password.setLayoutX(400);
    	password.setMaxWidth(200);
        password.setFocusTraversable(false);
    }
    
    public Node[] getTopNodes() {
    	Node[] returnNode = {title, insertUser, username};
    	return returnNode;
    }
    public static String getUsername(){
        return username.getText();
    }
}
