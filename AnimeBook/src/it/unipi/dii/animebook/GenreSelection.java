/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import static it.unipi.dii.animebook.LoginManager.login;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 *
 * @author Stefano
 */
public class GenreSelection {
    private Label selectGenres;
    // create the data to show in the CheckComboBox 
    private String [] genres = {"Action","Adventure","Cars","Comedy","Dementia","Demons","Drama","Ecchi","Fantasy","Game",
                                "Harem","Hentai","Historical","Horror","Josei","Kids","Magic","Martial Arts","Mecha",
                                "Military","Music","Mystery","Parody","Police","Psychological","Romance","Samurai","School",
                                "Sci-Fi","Seinen","Shoujo","Shoujo Ai","Shounen","Shounen Ai","Slice of Life","Space",
                                "Sports","Super Power","Supernatural","Thriller","Vampire","Yaoi","Yuri"};
    
    //"Action","Adventure","Cars","Comedy","Dementia","Demons","Drama","Ecchi","Fantasy","Game","Harem","Hentai","Historical","Horror","Josei","Kids","Magic","Martial Arts","Mecha","Military","Music","Mystery","Parody","Police","Psychological","Romance","Samurai","School","Sci-Fi","Seinen","Shoujo","Shoujo Ai","Shounen","Shounen Ai","Slice of Life","Space","Sports","Super Power","Supernatural","Thriller","Vampire","Yaoi","Yuri"
    protected List<CheckBox> cblist = new ArrayList<>();
    private Button confirm;
    private Button back;
    public GenreSelection(){
        selectGenres = new Label("Select your favorites genres");
        selectGenres.setLayoutX(20);
        selectGenres.setLayoutY(30);
        confirm = new Button("CONFIRM");
        confirm.setLayoutX(40);
        confirm.setLayoutY(560);
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
    }
    
    public void addSelection(){
        int x = 40;
        int y = 80;
        for(int i = 0; i < genres.length; i++){
            CheckBox cb = new CheckBox(genres[i]);
            cb.setIndeterminate(false);
            cb.setLayoutX(x);
            cb.setLayoutY(y);
            if(x >= 640){
                x = 40;
                y+=40;
            }
            else{
                x+=100;
            }
            cblist.add(cb);
        }
    }
    public List getNodes() {
        addSelection();
        List<Node> returnNode = new ArrayList<>();
        for(int i = 0; i < cblist.size(); i++){
            returnNode.add(cblist.get(i));
        }
        returnNode.add(selectGenres);
        returnNode.add(confirm);
        returnNode.add(back);
        return returnNode;
    }
    
    public Button getConfirm(){
        return confirm;
    }
    
    public Button getBack(){
        return back;
    }
    
        /*final ObservableList<String> strings = FXCollections.observableArrayList();
        for(int i = 0; i <= 100; i++) {
            strings.add("Item " + i);
        }

        // Create the CheckComboBox with the data 
        final CheckComboBox<String> checkComboBox = new CheckComboBox<String>(strings);

        // and listen to the relevant events (e.g. when the selected indices or 
        // selected items change).
        checkComboBox.getCheckModel().getSelectedItems().addListener(new ListChangeListener<String>() {
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                System.out.println(checkComboBox.getCheckModel().getSelectedItems());
            }
        });*/
}
