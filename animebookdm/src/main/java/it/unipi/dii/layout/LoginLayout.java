/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.layout;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;

/**
 *
 * @author Stefano
 */
public class LoginLayout {
    private Label user;
    private TextField username;
    private Label pwd;
    private PasswordField password;
    protected Button login;
    protected Button signUp;
    protected Button sign;
    private Label log;
    private TextField logText;
    protected Button back;
    public LoginLayout(){
        logText = new TextField();
        logText.setLayoutX(280);
        logText.setLayoutY(350);
        logText.setMinWidth(240);
        logText.setEditable(false);
        log = new Label("Log:");
        log.setLayoutX(230);
        log.setLayoutY(350);
        user = new Label("Insert Username");
        user.setLayoutX(330);
        user.setLayoutY(40);
        username = new TextField();
        username.setLayoutX(310);
        username.setLayoutY(80);
        username.setFocusTraversable(false);
        username.setMaxWidth(200);
        pwd = new Label("Insert Password");
        pwd.setLayoutX(330);
        pwd.setLayoutY(120);
        password = new PasswordField();
        password.setLayoutX(310);
        password.setLayoutY(160);
        password.setFocusTraversable(false);
        password.setMaxWidth(200);
        login = new Button("LOGIN");
    	login.setLayoutY(250);
    	login.setLayoutX(300);
    	login.setMinWidth(80);
        signUp = new Button("SIGN");
    	signUp.setLayoutY(250);
    	signUp.setLayoutX(400);
    	signUp.setMinWidth(80);
    }

    public void printError(String err){
        logText.setText(err);
        logText.setStyle("-fx-text-inner-color: red;");
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = {user, username, pwd, password, login, signUp, log, logText};
    	return returnNode;
    }
     
    public String getUsername(){
        return username.getText();
    }
    
    public String getPassword(){
        return password.getText();
    }

    public Button getLogin(){return login;}

    public Button getSignUp(){return signUp;}
}
