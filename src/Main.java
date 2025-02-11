
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import models.JoueurModel;
import objets_bdd.Joueur;

public class Main {
    public static void main(String[] args) {
        JoueurModel joueur = new JoueurModel();
        String[] columns = {"nom", "prenom", "dateNaissance", "nationalite", "numeroLicence", "numeroMaillot", "position", "taille", "poids", "piedFort"};
        Object[] values = {"Mbappé", "Kylian", "1998-12-20", "France", 123456, 7, "Attaquant", 1.78, 73, "Droitier"};

        try {
        	List<Joueur> data = joueur.getAll();
			//Joueur data = (Joueur) joueur.get(10);
			System.out.println(data.get(9).nom);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
    }
}
