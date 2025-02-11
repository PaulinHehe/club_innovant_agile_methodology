package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.Contrat;

public class ContratModel extends Model {
    public ContratModel() {
        this.table = "contrats";
        this.idField = "id";
    }

    private Contrat getContrat(ResultSet data) throws SQLException
    {
    	Contrat contrat = new Contrat();
		contrat.id = data.getInt("id");
		contrat.type = data.getInt("type");
		contrat.dateDebut = data.getTimestamp("dateDebut");
		contrat.dateFin = data.getTimestamp("dateFin");
		contrat.salaire = data.getFloat("salaire");
		contrat.prime = data.getFloat("prime");
		contrat.devise = data.getString("devise");
		contrat.idJoueur = data.getInt("idJoueur");

		return contrat;
    }

    public Contrat get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getContrat(data);
    	return null;
    }
    
    public List<Contrat> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<Contrat> retour = new ArrayList<Contrat>();
    	while(data != null && data.next())
    		retour.add(this.getContrat(data));
    	
    	return retour;
    }
}
