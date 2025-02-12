package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.Contrat;

/**
 * Modèle pour gérer les opérations CRUD sur la table des contrats.
 * Hérite de la classe abstraite Model et fournit des méthodes spécifiques pour les contrats.
 */
public class ContratModel extends Model {
	/**
     * Constructeur de ContratModel.
     * Initialise le nom de la table et le champ ID.
     */
    public ContratModel() {
        this.table = "contrats";
        this.idField = "id";
    }

    /**
     * Convertit un ResultSet en objet Contrat.
     *
     * @param data Le ResultSet contenant les données d'un contrat.
     * @return Un objet Contrat.
     * @throws SQLException Si une erreur SQL survient.
     */
    private Contrat getContrat(ResultSet data) throws SQLException
    {
    	Contrat contrat = new Contrat();
		contrat.id = data.getInt("id");
		contrat.type = data.getInt("type");
		contrat.dateDebut = data.getTimestamp("dateDebut");
		contrat.dateFin = data.getTimestamp("dateFin");
		contrat.salaire = data.getFloat("salaire");
		contrat.primes = data.getFloat("primes");
		contrat.devise = data.getString("devise");
		contrat.idJoueur = data.getInt("idjoueur");

		return contrat;
    }

    /**
     * Récupère un contrat par son ID.
     *
     * @param id L'ID du contrat à récupérer.
     * @return Un objet Contrat correspondant à l'ID, ou null si non trouvé.
     * @throws SQLException Si une erreur SQL survient.
     */
    public Contrat get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getContrat(data);
    	return null;
    }
    
    /**
     * Récupère tous les contrats de la base de données.
     *
     * @return Une liste de tous les contrats.
     * @throws SQLException Si une erreur SQL survient.
     */
    public List<Contrat> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<Contrat> retour = new ArrayList<Contrat>();
    	while(data != null && data.next())
    		retour.add(this.getContrat(data));
    	
    	return retour;
    }
}
