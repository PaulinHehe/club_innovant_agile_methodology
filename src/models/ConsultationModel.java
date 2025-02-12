package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.Consultation;

public class ConsultationModel extends Model {
    public ConsultationModel() {
        this.table = "consultations";
        this.idField = "id";
    }
    
    private Consultation getConsultation(ResultSet data) throws SQLException
    {
    	Consultation nc = new Consultation();
		nc.id = data.getInt("id");
		nc.details = data.getString("details");
		nc.resultats = data.getString("resultats");
		nc.traitements = data.getString("traitements");
		nc.idJoueur = data.getInt("idjoueurs");
		nc.idMedecin = data.getInt("idmedecin");

		return nc;
    }

    public Consultation get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getConsultation(data);
    	return null;
    }
    
    public List<Consultation> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<Consultation> retour = new ArrayList<Consultation>();
    	while(data != null && data.next())
    		retour.add(this.getConsultation(data));
    	
    	return retour;
    }
    
    public List<Consultation> getAllFromJoueur(int idJoueur) throws SQLException {
        List<Consultation> consultations = new ArrayList<>();

        // Requête SQL pour récupérer les consultations du joueur
        String sql = "SELECT * FROM " + table + " WHERE idjoueurs = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idJoueur); // Associe l'ID du joueur au paramètre de la requête
            ResultSet data = stmt.executeQuery();

            // Parcourir les résultats et les mapper à des objets Consultation
            while (data.next()) {
                consultations.add(getConsultation(data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propager l'exception pour la gestion des erreurs
        }

        return consultations;
    }
}
