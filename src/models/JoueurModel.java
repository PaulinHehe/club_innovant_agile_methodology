package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import objets_bdd.Joueur;

public class JoueurModel extends Model {
    public JoueurModel() {
        this.table = "joueurs";
        this.idField = "id";
    }
    
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

    public Joueur get(int id) throws SQLException {
    	ResultSet data = super.find(id);
    	
    	while(data.next())
    		return this.getJoueur(data);
    	return null;
    }
    
    public List<Joueur> getAll() throws SQLException {
    	ResultSet data = super.all();
    	List<Joueur> retour = new ArrayList<Joueur>();
    	while(data != null && data.next())
    		retour.add(this.getJoueur(data));
    	
    	return retour;
    }
}
