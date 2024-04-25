package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.example.entities.Event;
import org.example.entities.User;
import org.example.service.EventService;
import org.example.service.UserService;
import org.example.tools.DBconnexion;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interface {


    @FXML
    private GridPane grid;

    @FXML
    private GridPane grid1;

    @FXML
    private Label id;
    @FXML
    private TextField tf_log;
    @FXML
    private Pane pn_home;

    @FXML
    private Pane pn_index;

    @FXML
    private Pane pn_part;

    @FXML
    private Pane pn_signin;

    @FXML
    private Pane pn_signup;

    @FXML
    private Pane pn_update;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_email1;

    @FXML
    private TextField tf_fn;

    @FXML
    private TextField tf_fn1;

    @FXML
    private TextField tf_ln;

    @FXML
    private TextField tf_ln1;

    @FXML
    private TextField tf_num;

    @FXML
    private TextField tf_num1;

    @FXML
    private TextField tf_pass;

    @FXML
    private TextField tf_pass1;
    User tmpp = new User();
    UserService us = new UserService();
    EventService es = new EventService();

    @FXML
    void signup(ActionEvent event) {
        pn_signin.toFront();
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void toSignup(ActionEvent event) {
        pn_signup.toFront();
    }

    @FXML
    void topart(ActionEvent event) {
        grid1.getChildren().clear();
        displayg1();
        pn_part.toFront();
    }

    @FXML
    void toUpdate(ActionEvent event) {
        pn_update.toFront();
    }

    @FXML
    void tosignin(ActionEvent event) {
        pn_signin.toFront();
    }

    @FXML
    void toSignin(ActionEvent event) {
        pn_signin.toFront();
    }

    @FXML
    void login(ActionEvent event) {
        pn_home.toFront();
        pn_index.toFront();
        id.setText(tf_log.getText());
        ResultSet resultSet = us.SelectionnerSingle(Integer.parseInt(id.getText()));
        try {
            while (resultSet.next()) {
                tmpp = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("roles"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("prenom"),
                        resultSet.getInt("tel"),
                        resultSet.getInt("is_banned")
                );
            }
            tf_email1.setText(tmpp.getEmail());
            tf_pass1.setText(tmpp.getPassword());
            tf_fn1.setText(tmpp.getPrenom());
            tf_ln1.setText(tmpp.getName());
            tf_num1.setText(String.valueOf(tmpp.getTel()));
        } catch (SQLException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void toHome(ActionEvent event) {
        System.out.println("helo");
        pn_home.toFront();
        pn_update.toBack();
        pn_part.toBack();
        displayg();
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
        String idu = id.getText();
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
                    itemController.setDataF(ppppp,idu);
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

    private void displayg1() {
        ///////////////////////////////////////////////////////////////
        ObservableList<Event> l2 = FXCollections.observableArrayList();
        ResultSet resultSet2 = es.Getallparticipation(Integer.valueOf(id.getText()));
        l2.clear();
        Event pppp = new Event();
        l2.add(pppp);
        int column = 0;
        int row = 2;
        String idu = id.getText();
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
                    itemController.setDataU(ppppp,idu);
                    if (column == 1) {
                        column = 0;
                        row++;
                    }
                    grid1.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid1.setMaxWidth(Region.USE_PREF_SIZE);
                    //set grid height
                    grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid1.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void refresh(ActionEvent event) {
        grid.getChildren().clear();
        displayg();
    }


}
