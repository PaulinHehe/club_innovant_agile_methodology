package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import objets_bdd.DonneePhysique;
import objets_bdd.Joueur;

/**
 * Modèle pour gérer les opérations CRUD sur la table des joueurs.
 * Hérite de la classe abstraite Model et fournit des méthodes spécifiques pour les joueurs.
 */
public class JoueurModel extends Model {
	/**
     * Constructeur de JoueurModel.
     * Initialise le nom de la table et le champ ID.
     */
    public JoueurModel() {
        this.table = "joueurs";
        this.idField = "id";
    }
    
    /**
     * Convertit un ResultSet en objet Joueur.
     *
     * @param data Le ResultSet contenant les données d'un joueur.
     * @return Un objet Joueur.
     * @throws SQLException Si une erreur SQL survient.
     */
    private Joueur getJoueur(ResultSet data) throws SQLException
    {
    	Joueur nj = new Joueur();
		nj.id = data.getInt("id");
		nj.nom = data.getString("nom");
		nj.prenom = data.getString("prenom");
		nj.dateNaissance = data.getTimestamp("dateNaissance");
		nj.numeroLicence = data.getInt("numeroLicence");
		nj.numeroMaillot = data.getInt("numeroMaillot");
		nj.position = data.getString("position");
		nj.taille = data.getFloat("taille");
		nj.poids = data.getFloat("poids");
		nj.piedFort = data.getString("piedFort");

		return nj;
    }

    /**
     * Récupère un joueur par son ID.
     *
     * @param id L'ID du joueur à récupérer.
     * @return Un objet Joueur correspondant à l'ID, ou null si non trouvé.
     * @throws SQLException Si une erreur SQL survient.
     */
    public Joueur get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getJoueur(data);
    	return null;
    }
    
    /**
     * Récupère tous les joueurs de la base de données.
     *
     * @return Une liste de tous les joueurs.
     * @throws SQLException Si une erreur SQL survient.
     */
    public List<Joueur> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<Joueur> retour = new ArrayList<Joueur>();
    	while(data != null && data.next())
    		retour.add(this.getJoueur(data));
    	
    	return retour;
    }

	
}
