package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.example.service.EventService;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.entities.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
public class Dashboard {

    @FXML
    private GridPane grid;

    @FXML
    private Pane pn_eventlist;

    @FXML
    private Pane pn_addevent;

    @FXML
    private DatePicker tf_date;

    @FXML
    private TextArea tf_desc;

    @FXML
    private TextField tf_lieu;

    @FXML
    private TextField tf_nom;

    EventService es = new EventService();

    @FXML
    void addevent(ActionEvent event) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        if (tf_nom.getText().isEmpty() || tf_desc.getText().isEmpty() ||tf_lieu.getText().isEmpty() ||tf_date.getValue().isBefore(currentDate)||tf_date.getValue()==null) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        String description = tf_desc.getText();
        String nom = tf_nom.getText();
        String lieu = tf_lieu.getText();
        String date = String.valueOf(tf_date.getValue());
        Event p = new Event(nom,description,"default",lieu,date);
        es.ajouterEntite(p);
        tf_nom.clear();
        tf_desc.clear();
        tf_lieu.clear();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Valider");
        alert.setHeaderText(null);
        alert.setContentText("Evenement ajouter !");
        alert.showAndWait();

        pn_eventlist.toFront();
    }

    @FXML
    public void initialize() {
        grid.getChildren().clear();
        displayg();
    }

    @FXML
    void refresh(ActionEvent event) {
        grid.getChildren().clear();
        displayg();
    }

    @FXML
    void tocreateevent(ActionEvent event) {
        pn_addevent.toFront();
    }

    @FXML
    void toeventlist(ActionEvent event) {
        pn_eventlist.toFront();
    }

    private void displayg() {
        ///////////////////////////////////////////////////////////////
        ObservableList<Event> l2 = FXCollections.observableArrayList();
        ResultSet resultSet2 = es.Getall();
        l2.clear();
        Event pppp = new Event();
        l2.add(pppp);
        int column = 0;
        int row = 2;
        if (l2.size() > 0) {

        }
        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Event.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    EventC itemController = fxmlLoader.getController();
                    int id=resultSet2.getInt("id");
                    String nom =resultSet2.getString("nom");
                    String decsription=resultSet2.getString("description");
                    String lieu=resultSet2.getString("lieu");
                    String date=resultSet2.getString("date");
                    Event ppppp = new Event(id,nom,decsription,"",date,lieu);
                    itemController.setData(ppppp);
                    if (column == 1) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}



