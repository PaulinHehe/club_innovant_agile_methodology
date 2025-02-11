package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.DonneeMedicale;
import objets_bdd.DonneePhysique;

public class DonneeMedicaleModel extends Model {
    public DonneeMedicaleModel() {
        this.table = "donnees_medicales";
        this.idField = "id";
    }

    private DonneeMedicale getDonneeMedicale(ResultSet data) throws SQLException
    {
    	DonneeMedicale donneesMedicale = new DonneeMedicale();
		donneesMedicale.id = data.getInt("id");
		donneesMedicale.allergies = data.getString("allergies");
		donneesMedicale.vaccins = data.getString("vaccins");
		donneesMedicale.idConsultation = data.getInt("idConsultation");

		return donneesMedicale;
    }

    public DonneeMedicale get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getDonneeMedicale(data);
    	return null;
    }
    
    public List<DonneeMedicale> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<DonneeMedicale> retour = new ArrayList<DonneeMedicale>();
    	while(data != null && data.next())
    		retour.add(this.getDonneeMedicale(data));
    	
    	return retour;
    }
    
    public List<DonneeMedicale> getAllFromJoueur(int idJoueur) throws SQLException {
        List<DonneeMedicale> donneeMedicale = new ArrayList<>();

        // Requête SQL pour récupérer les données physiques du joueur
        String sql = "SELECT * FROM " + table + " WHERE idJoueur = ?";
        
        
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idJoueur); // Associe l'ID du joueur au paramètre de la requête
            ResultSet data = stmt.executeQuery();

            // Parcourir les résultats et les mapper à des objets DonneePhysique
            while (data.next()) {
            	donneeMedicale.add(getDonneeMedicale(data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propager l'exception pour la gestion des erreurs
        }

        return donneeMedicale;
    }
}
