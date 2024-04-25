package org.example.service;

import org.example.entities.Event;
import org.example.tools.DBconnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EventService implements ICrud<Event>{
    Connection cnx2;
    public EventService() {
        cnx2 = DBconnexion.getInstance().getCnx();
    }

    public ResultSet SelectionnerSingle(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `event` WHERE `id` ="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
    @Override
    public void ajouterEntite(Event p) {
        String req1 = "INSERT INTO `event`( `nom`, `description`, `lieu`, `date`,`image`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement st = cnx2.prepareStatement(req1);
            st.setString(1, p.getNom());
            st.setString(2, p.getDescription());
            st.setString(3,p.getLieu());
            st.setString(4, p.getDate());
            st.setString(5, p.getImage());
            st.executeUpdate();
            System.out.println("event ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Event> afficherEntite() {
        return null;
    }

    @Override
    public void modifierEntite(Event p) {
        String requet = "UPDATE event SET nom=?, description=?,date=?,lieu=? WHERE id =?";
        try {
            PreparedStatement st = cnx2.prepareStatement(requet);
            st.setInt(5,p.getId());
            st.setString(1, p.getNom());
            st.setString(2, p.getDescription());
            st.setString(3, p.getDate());
            st.setString(4, p.getLieu());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Modification réussie");
            } else {
                System.out.println("Aucune modification effectuée. Vérifiez l'ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimerEntite(Event p) {
        String requet = "DELETE FROM event WHERE id =?";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requet);
            pst.setInt(1, p.getId());  // Assuming getQuizId() returns the Quiz ID
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Suppression réussie");
            } else {
                System.out.println("Aucune suppression effectuée. Vérifiez l'ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet Getall() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `event`";
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }

    public ResultSet Getallparticipation(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `event` JOIN `participation` ON event.id = participation.event_id AND participation.user_id="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }
}

