package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.DonneePsychologique;
import objets_bdd.DonneePsychologique;

public class DonneePsychologiqueModel extends Model {
    public DonneePsychologiqueModel() {
        this.table = "donnees_psychologiques";
        this.idField = "id";
    }

        private DonneePsychologique getDonneePsychologique(ResultSet data) throws SQLException
    {
    	DonneePsychologique donneesPsychologique = new DonneePsychologique();
		donneesPsychologique.id = data.getInt("id");
		donneesPsychologique.profilPsychologique = data.getString("profilPsychologique");
		donneesPsychologique.suiviMental = data.getString("suiviMental");
		donneesPsychologique.strategiePreparation = data.getString("strategiePreparation");
		donneesPsychologique.idConsultation = data.getInt("idConsultation");

		return donneesPsychologique;
    }

    public DonneePsychologique get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getDonneePsychologique(data);
    	return null;
    }
    
    public List<DonneePsychologique> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<DonneePsychologique> retour = new ArrayList<DonneePsychologique>();
    	while(data != null && data.next())
    		retour.add(this.getDonneePsychologique(data));
    	
    	return retour;
    }
    
    public List<DonneePsychologique> getAllFromJoueur(int idJoueur) throws SQLException {
        List<DonneePsychologique> donneesPsychologiques = new ArrayList<>();

        // Requête SQL pour récupérer les données psychologiques du joueur
        String sql = "SELECT * FROM " + table + " WHERE idJoueur = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idJoueur); // Associe l'ID du joueur au paramètre de la requête
            ResultSet data = stmt.executeQuery();

            // Parcourir les résultats et les mapper à des objets DonneePsychologique
            while (data.next()) {
                donneesPsychologiques.add(getDonneePsychologique(data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propager l'exception pour la gestion des erreurs
        }

        return donneesPsychologiques;
    }
}
