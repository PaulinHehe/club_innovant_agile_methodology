package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.PerformanceMatch;

public class PerformanceModel extends Model {
    public PerformanceModel() {
        this.table = "performances";
        this.idField = "id";
    }
    
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

    public PerformanceMatch get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getPerformanceMatch(data);
    	return null;
    }
    
    public List<PerformanceMatch> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<PerformanceMatch> retour = new ArrayList<PerformanceMatch>();
    	while(data != null && data.next())
    		retour.add(this.getPerformanceMatch(data));
    	
    	return retour;
    }
}
