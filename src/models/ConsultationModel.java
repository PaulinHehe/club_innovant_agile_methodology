package models;

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
}
