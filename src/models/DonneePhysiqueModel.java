package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.DonneePhysique;

public class DonneePhysiqueModel extends Model {
    public DonneePhysiqueModel() {
        this.table = "donnees_physiques";
        this.idField = "id";
    }

    private DonneePhysique getDonneePhysique(ResultSet data) throws SQLException
    {
    	DonneePhysique donneesPhysique = new DonneePhysique();
		donneesPhysique.id = data.getInt("id");
		donneesPhysique.vitesseMax = data.getFloat("vitesseMax");
		donneesPhysique.distanceParcourue = data.getFloat("distanceParcourue");
		donneesPhysique.nbSprints = data.getInt("nombreSprints");
		donneesPhysique.vo2Max = data.getFloat("vo2Max");
		donneesPhysique.acceleration = data.getFloat("acceleration");
		donneesPhysique.puissance = data.getFloat("puissance");
		donneesPhysique.idjoueur = data.getInt("idJoueur");

		return donneesPhysique;
    }

    public DonneePhysique get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getDonneePhysique(data);
    	return null;
    }
    
    public List<DonneePhysique> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<DonneePhysique> retour = new ArrayList<DonneePhysique>();
    	while(data != null && data.next())
    		retour.add(this.getDonneePhysique(data));
    	
    	return retour;
    }
    
    public List<DonneePhysique> getAllFromJoueur(int idJoueur) throws SQLException {
        List<DonneePhysique> donneesPhysiques = new ArrayList<>();

        // Requête SQL pour récupérer les données physiques du joueur
        String sql = "SELECT * FROM " + table + " WHERE idJoueur = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idJoueur); // Associe l'ID du joueur au paramètre de la requête
            ResultSet data = stmt.executeQuery();

            // Parcourir les résultats et les mapper à des objets DonneePhysique
            while (data.next()) {
                donneesPhysiques.add(getDonneePhysique(data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propager l'exception pour la gestion des erreurs
        }

        return donneesPhysiques;
    }
}
