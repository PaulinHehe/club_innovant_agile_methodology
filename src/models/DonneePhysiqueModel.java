package models;

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
}
