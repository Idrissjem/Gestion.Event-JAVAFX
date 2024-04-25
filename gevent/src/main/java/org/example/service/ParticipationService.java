package org.example.service;

import org.example.entities.Participation;
import org.example.tools.DBconnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParticipationService implements ICrud<Participation>{
    Connection cnx2;
    public ParticipationService() {
        cnx2 = DBconnexion.getInstance().getCnx();
    }

    public ResultSet SelectionnerSingle(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `participation` WHERE `id` ="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
    @Override
    public void ajouterEntite(Participation p) {
        String req1 = "INSERT INTO `participation`( `event_id`, `user_id`) VALUES (?,?)";
        try {
            PreparedStatement st = cnx2.prepareStatement(req1);
            st.setInt(1, p.getEvent_id());
            st.setInt(2, p.getUser_id());
            st.executeUpdate();
            System.out.println("participation ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Participation> afficherEntite() {
        return null;
    }

    @Override
    public void modifierEntite(Participation p) {

    }

    @Override
    public void supprimerEntite(Participation p) {
        String requet = "DELETE  FROM `participation` WHERE `user_id` = ? AND `event_id` = ?";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requet);
            pst.setInt(1, p.getUser_id());  // Assuming getQuizId() returns the Quiz ID
            pst.setInt(2, p.getEvent_id());  // Assuming getQuizId() returns the Quiz ID
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
            String req = "SELECT * FROM `participation`";
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }
}

