/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import javafx.event.ActionEvent;

/**
 *
 * @author Stefano
 */
public class LoginManager {
    private static LoginLayout loginLayout;
    public LoginManager(LoginLayout loginLayout){
        this.loginLayout = loginLayout;
    }
    public static void login(){
        GUIManager.openAppManager();
    }
    
    public static void logout(){
        GUIManager.openLoginManager();
    }
    
    public static void checkCredentials(){
        
    }
    
    public static void signup(){
        GUIManager.openSignUpManager();
    }
    
    public static void setEvents(){
        loginLayout.login.setOnAction((ActionEvent ev)->{login();});	
        loginLayout.signUp.setOnAction((ActionEvent ev)->{signup();});
    }
    
    public static void setSignUpEvents(){
        loginLayout.sign.setOnAction((ActionEvent ev)->{GUIManager.openGenreSelection();}); 
        loginLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
    }
    
    /*
    LoginLayout loginLayout;

    public LoginManager(LoginLayout loginLayout){
        this.loginLayout = loginLayout;
        setEvents();
    }

    public void login(){

        String username = loginLayout.getUsername();
        String password = encrypt(loginLayout.getPassword());
        if(username.isEmpty() || password.isEmpty() || !MongoDBManager.checkUser(username,password)){
            loginLayout.printError("User\\pass not correct");
        }else{
            GUIManager.setCurrentUser(new User(username, MongoDBManager.getDecks(username)));
            GUIManager.openAppManager();
        }
    }

    public void setEvents(){
        loginLayout.getLogin().setOnAction((ActionEvent ev)-> login());
        loginLayout.getSignUp().setOnAction((ActionEvent ev)-> GUIManager.openSignUpManager());
    }
    
    public static void logout(){
        GUIManager.openLoginManager();
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
    */
}
