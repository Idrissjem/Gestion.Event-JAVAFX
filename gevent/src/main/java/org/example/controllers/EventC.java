package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.example.entities.Event;
import javafx.fxml.FXML;
import org.example.entities.Participation;
import org.example.service.EventService;
import org.example.service.ParticipationService;

import java.io.IOException;
import java.time.LocalDate;

public class EventC {

    @FXML
    private Label id;

    @FXML
    private Label iduu;

    @FXML
    private DatePicker tfdate;

    @FXML
    private TextArea tflieu;

    @FXML
    private TextArea tfnom;

    @FXML
    private Label date;

    @FXML
    private TextArea desc;

    @FXML
    private Label lieu;

    @FXML
    private Label nom;

    private Event event= new Event();

    @FXML
    private Button todelete;

    @FXML
    private Button toupdate;

    @FXML
    private Button toparticiper;

    @FXML
    private Button toupdate1;

    EventService es = new EventService();

    ParticipationService ps = new ParticipationService();


    @FXML
    void update(ActionEvent event) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        if (tfnom.getText().isEmpty() || tflieu.getText().isEmpty() ||desc.getText().isEmpty() ||tfdate.getValue().isBefore(currentDate)||tfdate.getValue()==null) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        String description = desc.getText();
        String nom = tfnom.getText();
        String lieu = tflieu.getText();
        String date = String.valueOf(tfdate.getValue());
        Event p = new Event(Integer.parseInt(id.getText()),nom,description,"default",lieu,date);
        es.modifierEntite(p);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Valider");
        alert.setHeaderText(null);
        alert.setContentText("Evenement modifier avec success !");
        alert.showAndWait();

        tfnom.setVisible(false);
        tflieu.setVisible(false);
        tfdate.setVisible(false);
        desc.setEditable(false);
        toupdate.setVisible(true);
        toupdate1.setVisible(false);

    }

    @FXML
    void todelete(ActionEvent event) {
        try {
            int eventId = Integer.parseInt(id.getText());

            // Create a confirmation dialog
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.setContentText("etes vous sure de vouloir supprimer cet evenment?");

            // Add "OK" and "Cancel" buttons to the dialog
            confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            // Show the confirmation dialog and wait for the user's response
            ButtonType userResponse = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

            // If the user clicked "OK" in the confirmation dialog, proceed with the deletion
            if (userResponse == ButtonType.OK) {
                // Create a new User instance with the provided ID
                Event eventToDelete = new Event(eventId,"","","","","");

                // Call the method to delete the user entity
                es.supprimerEntite(eventToDelete);
                /*
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Dashboard.fxml"));
                Dashboard itemController = fxmlLoader.getController();
                ActionEvent e = new ActionEvent();
                itemController.refresh(e);

                 */
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID entered by the user is not a valid integer
            // Display an error message or handle it as appropriate for your application
            e.printStackTrace(); // Or log the error
        }
    }

    @FXML
    void toupdate(ActionEvent event) {
        desc.setEditable(true);
        tfnom.setVisible(true);
        tflieu.setVisible(true);
        tfdate.setVisible(true);
        toupdate1.setVisible(true);
        toupdate.setVisible(false);

    }

    @FXML
    void participer(ActionEvent event) {
        if(toparticiper.getText()=="Supprimer")
        {
            System.out.println(id.getText());
            Participation p = new Participation(Integer.valueOf(id.getText()),1,Integer.valueOf(iduu.getText()));
            ps.supprimerEntite(p);
        }else {
            Participation p = new Participation(Integer.valueOf(id.getText()),Integer.valueOf(iduu.getText()));
            ps.ajouterEntite(p);
        }
    }

    public void setData(Event q) {
        this.event = q;
        id.setText(String.valueOf(q.getId()));
        nom.setText(q.getNom());
        desc.setText(q.getDescription());
        date.setText("Date :"+q.getDate());
        lieu.setText("Lieu : "+q.getLieu());
        tflieu.setText(q.getDate());
        tfnom.setText(q.getNom());
        desc.setEditable(false);
        tfnom.setVisible(false);
        tflieu.setVisible(false);
        tfdate.setVisible(false);
        toupdate.setVisible(true);
        toupdate1.setVisible(false);
        toparticiper.setVisible(false);
    }


    public void setDataF(Event q,String idu) {
        this.event = q;
        id.setText(String.valueOf(q.getId()));
        nom.setText(q.getNom());
        desc.setText(q.getDescription());
        date.setText("Date :"+q.getDate());
        lieu.setText("Lieu : "+q.getLieu());
        tflieu.setText(q.getDate());
        tfnom.setText(q.getNom());
        desc.setEditable(false);
        tfnom.setVisible(false);
        tflieu.setVisible(false);
        tfdate.setVisible(false);
        toupdate.setVisible(false);
        toupdate1.setVisible(false);
        todelete.setVisible(false);
        iduu.setText(idu);
    }

    public void setDataU(Event q,String idu) {
        this.event = q;
        id.setText(String.valueOf(q.getId()));
        nom.setText(q.getNom());
        desc.setText(q.getDescription());
        date.setText("Date :"+q.getDate());
        lieu.setText("Lieu : "+q.getLieu());
        tflieu.setText(q.getDate());
        tfnom.setText(q.getNom());
        desc.setEditable(false);
        tfnom.setVisible(false);
        tflieu.setVisible(false);
        tfdate.setVisible(false);
        toupdate.setVisible(false);
        toupdate1.setVisible(false);
        todelete.setVisible(false);
        toparticiper.setText("Supprimer");
        iduu.setText(idu);
    }

}
