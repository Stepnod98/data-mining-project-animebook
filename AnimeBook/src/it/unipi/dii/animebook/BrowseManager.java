/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.animebook;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Stefano
 */

public class BrowseManager {
    public static BorderPane viewList(TextField tf, List l){
        ObservableList<String> data = FXCollections.observableArrayList(l); //add the values of the list!!
        FilteredList<String> filteredData = new FilteredList<>(data, s -> false);
        //Set the filter Predicate whenever the filter changes.
        String filter = tf.getText(); 
        if(filter == null || filter.length() == 0) {
            filteredData.setPredicate(s -> false);
            return null;
        }
        else {
            filteredData.setPredicate(s -> s.contains(filter));
        }
        ListView<String> lv = new ListView<>(filteredData);
        for(int i = 0; i < filteredData.size(); i++){
            String current = filteredData.get(i);
            lv.getSelectionModel().getSelectedItem();
            lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    tf.setText(lv.selectionModelProperty().getValue().getSelectedItem());
                }
            });
        }
        BorderPane content = new BorderPane(lv);
        //content.setTop(tf);
        /*if(filteredData.isEmpty()){
            content.setMaxHeight(0);
        }*/
        return content;
    }
}

/*public class BrowseManager {
    public static BorderPane viewList(TextField tf, List l){
        ObservableList<String> data = FXCollections.observableArrayList(l); //add the values of the list!!
        FilteredList<String> filteredData = new FilteredList<>(data, s -> false);

        //Set the filter Predicate whenever the filter changes.
        //tf.textProperty().addListener((obs, oldValue, newValue)->{
            String filter = tf.getText(); 
            if(filter == null || filter.length() == 0) {
                filteredData.setPredicate(s -> false);
                return null;
            }
            else {
                filteredData.setPredicate(s -> s.contains(filter));
            }
        //});
        BorderPane content = new BorderPane(new ListView<>(filteredData));
        //content.setTop(tf);
        if(filteredData.isEmpty()){
            content.setMaxHeight(0);
        }
        return content;
        (observable, oldValue, newValue) -> {
            filteredData.setPredicate(client ->{
                // If filter text is empty, display all persons.
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                // Compare first name and last name of every client with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if(client.getFirstname().toLowerCase().contains(lowerCaseFilter)){
                    return true; //filter matches first name
                }else if(client.getLastname().toLowerCase().contains(lowerCaseFilter)){
                    return true; //filter matches last name
                }
                return false; //Does not match
            });
        });

        //Wrap the FilteredList in a SortedList.
        //SortedList<Client> sortedData = new SortedList<>(filteredData);

        //put the sorted list into the listview
        //clientListView.setItems(sortedData);
    }
}*/
