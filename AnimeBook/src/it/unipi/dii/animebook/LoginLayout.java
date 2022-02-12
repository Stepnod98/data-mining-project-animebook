/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;
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
    private Label confPwd;
    private PasswordField confirmPassword;
    private Label fName;
    private TextField firstName;
    private Label lName;
    private TextField lastName;
    private Label email;
    private TextField emailAddress;
    protected Button login;
    protected Button signUp;
    protected Button sign;
    protected Button back;
    public LoginLayout(){
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
    
    public LoginLayout(boolean signup){
        fName = new Label("First Name");
        fName.setLayoutX(80);
        fName.setLayoutY(40);
        firstName = new TextField();
        firstName.setLayoutX(80);
        firstName.setLayoutY(80);
        firstName.setFocusTraversable(false);
        firstName.setMaxWidth(200);
        lName = new Label("Last Name");
        lName.setLayoutX(80);
        lName.setLayoutY(120);
        lastName = new TextField();
        lastName.setLayoutX(80);
        lastName.setLayoutY(160);
        lastName.setFocusTraversable(false);
        lastName.setMaxWidth(200);
        user = new Label("Insert Username");
        user.setLayoutX(80);
        user.setLayoutY(200);
        username = new TextField();
        username.setLayoutX(80);
        username.setLayoutY(240);
        username.setFocusTraversable(false);
        username.setMaxWidth(200);
        email = new Label("Insert Email");
        email.setLayoutX(280);
        email.setLayoutY(40);
        emailAddress = new TextField();
        emailAddress.setLayoutX(280);
        emailAddress.setLayoutY(80);
        emailAddress.setFocusTraversable(false);
        emailAddress.setMaxWidth(200);
        pwd = new Label("Insert Password");
        pwd.setLayoutX(280);
        pwd.setLayoutY(120);
        password = new PasswordField();
        password.setLayoutX(280);
        password.setLayoutY(160);
        password.setFocusTraversable(false);
        password.setMaxWidth(200);
        confPwd = new Label("Confirm Password");
        confPwd.setLayoutX(280);
        confPwd.setLayoutY(200);
        confirmPassword = new PasswordField();
        confirmPassword.setLayoutX(280);
        confirmPassword.setLayoutY(240);
        confirmPassword.setFocusTraversable(false);
        confirmPassword.setMaxWidth(200);
        sign = new Button("NEXT");
    	sign.setLayoutY(280);
    	sign.setLayoutX(80);
    	sign.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = {user, username, pwd, password, login, signUp};
    	return returnNode;
    }
    
    public Node[] getSignUpNodes() {
    	Node[] returnNode = {user, username, confPwd, confirmPassword, pwd, password, fName, firstName, lName, lastName,
                                email, emailAddress, sign, back};
    	return returnNode;
    }
     
    public String getUsername(){
        return username.getText();
    }
    
    public String getPassword(){
        return password.getText();
    }
}
