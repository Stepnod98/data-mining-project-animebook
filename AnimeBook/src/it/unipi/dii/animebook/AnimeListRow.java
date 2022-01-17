/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AnimeListRow {
    private final SimpleStringProperty title;
    private final SimpleIntegerProperty score;
    private Button remove;
    private TextField updateScore;
    private Button update;

    public AnimeListRow(String title, int score) {
        this.title = new SimpleStringProperty(title);
        this.score = new SimpleIntegerProperty(score);
        this.remove = new Button();
        this.updateScore = new TextField();
        this.update = new Button();
    }

    public String getTitle(){
        return title.get();
    }

    public void setTitle(String name){
        title.set(name);
    }

    public int getScore(){
        return score.get();
    }
    
    public Button getRemove() {
        return remove;
    }

    public void setRemoveButton(Button button){
        this.remove = button;
    }
    
    public Button getUpdate() {
        return update;
    }

    public void setUpdateButton(Button button){
        this.update = button;
    }
    
    public String getUpdateText(){
        return updateScore.getText();
    }
    
    public TextField getUpdateScore(){
        return updateScore;
    }

}