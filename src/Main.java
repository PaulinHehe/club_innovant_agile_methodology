import models.JoueurModel;

public class Main {
    public static void main(String[] args) {
        JoueurModel joueur = new JoueurModel();
        String[] columns = {"nom", "prenom", "dateNaissance", "nationalite", "numeroLicence", "numeroMaillot", "position", "taille", "poids", "piedFort"};
        Object[] values = {"Mbappé", "Kylian", "1998-12-20", "France", 123456, 7, "Attaquant", 1.78, 73, "Droitier"};

        if (joueur.create(columns, values)) {
            System.out.println("Joueur ajouté avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout du joueur.");
        }
    }
}
