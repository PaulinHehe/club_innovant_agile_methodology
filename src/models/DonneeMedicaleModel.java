package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.DonneeMedicale;

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
}
