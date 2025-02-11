package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objets_bdd.Utilisateur;

public class UtilisateurModel extends Model {
    public UtilisateurModel() {
        this.table = "utilisateurs";
        this.idField = "id";
    }
    
    private Utilisateur getUtilisateur(ResultSet data) throws SQLException
    {
    	Utilisateur nu = new Utilisateur();
		nu.id = data.getInt("id");
		nu.nom = data.getString("nom");
		nu.prenom = data.getString("prenom");
		nu.username = data.getString("username");
		nu.mdp = data.getString("mdp");
		nu.role = data.getString("role");

		return nu;
    }

    public Utilisateur get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getUtilisateur(data);
    	return null;
    }
    
    public List<Utilisateur> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<Utilisateur> retour = new ArrayList<Utilisateur>();
    	while(data != null && data.next())
    		retour.add(this.getUtilisateur(data));
    	
    	return retour;
    }
}
