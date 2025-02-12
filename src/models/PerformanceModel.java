package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.PerformanceMatch;

/**
 * Modèle pour gérer les opérations CRUD sur la table des performances.
 * Hérite de la classe abstraite Model et fournit des méthodes spécifiques pour les performances.
 */
public class PerformanceModel extends Model {
	/**
     * Constructeur de PerformanceModel.
     * Initialise le nom de la table et le champ ID.
     */
    public PerformanceModel() {
        this.table = "performances";
        this.idField = "id";
    }

    /**
     * Convertit un ResultSet en objet PerformanceMatch.
     *
     * @param data Le ResultSet contenant les données d'une performance.
     * @return Un objet PerformanceMatch.
     * @throws SQLException Si une erreur SQL survient.
     */
    private PerformanceMatch getPerformanceMatch(ResultSet data) throws SQLException
    {
    	PerformanceMatch np = new PerformanceMatch();
		np.id = data.getInt("id");
		np.buts = data.getInt("buts");
		np.dateMatch = data.getTimestamp("date_match");
		np.passesDecisives = data.getInt("passes_decisives");
		np.tirsCadres = data.getInt("tirs_cadres");
		np.dribblesReussis = data.getInt("dribbles_reussis");
		np.xg = data.getFloat("xg");

		return np;
    }

    /**
     * Récupère une performance par son ID.
     *
     * @param id L'ID de la performance à récupérer.
     * @return Un objet PerformanceMatch correspondant à l'ID, ou null si non trouvé.
     * @throws SQLException Si une erreur SQL survient.
     */
    public PerformanceMatch get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getPerformanceMatch(data);
    	return null;
    }

    /**
     * Récupère toutes les performances de la base de données.
     *
     * @return Une liste de toutes les performances.
     * @throws SQLException Si une erreur SQL survient.
     */
    public List<PerformanceMatch> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<PerformanceMatch> retour = new ArrayList<PerformanceMatch>();
    	while(data != null && data.next())
    		retour.add(this.getPerformanceMatch(data));
    	
    	return retour;
    }
}
