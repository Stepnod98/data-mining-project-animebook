/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.controller;

import java.security.NoSuchAlgorithmException;

import it.unipi.dii.dbmanager.MongoDBManager;
import it.unipi.dii.entities.User;
import it.unipi.dii.layout.LoginLayout;
import javafx.event.ActionEvent;

/**
 *
 * @author Stefano
 */
public class LoginManager {
    private static LoginLayout loginLayout;
    public LoginManager(LoginLayout loginLayout){
        this.loginLayout = loginLayout;
        setEvents();
    }
    public static void login(){
        //GUIManager.openAppManager();
        String username = loginLayout.getUsername();
        String password = encrypt(loginLayout.getPassword());
        if(username.isEmpty() || password.isEmpty() || !MongoDBManager.checkUser(username,password)){
            loginLayout.printError("User\\pass not correct");
        }else{
            User current = new User(username);
            GUIManager.setCurrentUser(current);
            current.setGenres(MongoDBManager.getUserGenres());
            GUIManager.setCurrentUser(current);
            GUIManager.openAppManager();
        }

    }
    
    public static void logout(){
        GUIManager.openLoginManager();
    }
    
    public static void signup(){
        GUIManager.openSignUpManager();
    }
    
    private static String encrypt(String pass){
        java.security.MessageDigest d = null;
        try {
            d = java.security.MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        d.reset();
        d.update(pass.getBytes());
        return new String(d.digest());
    }


    public static void setEvents(){
        loginLayout.getLogin().setOnAction((ActionEvent ev)->{login();});
        loginLayout.getSignUp().setOnAction((ActionEvent ev)->{signup();});
    }

}
