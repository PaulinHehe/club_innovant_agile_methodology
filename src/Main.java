import views.ConsultationView;

import javax.swing.*;

import models.JoueurModel;
import objets_bdd.Joueur;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Utiliser SwingUtilities.invokeLater pour garantir que l'interface graphique
        // est créée et mise à jour sur le thread de l'interface utilisateur (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            try {
                // Créer une nouvelle fenêtre (JFrame)
                JFrame frame = new JFrame("Gestion des Consultations");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer l'application lorsque la fenêtre est fermée
                frame.setSize(800, 600); // Définir la taille de la fenêtre

                // Créer une instance de ConsultationView
                ConsultationView consultationView = new ConsultationView();

                // Ajouter ConsultationView à la fenêtre
                frame.add(consultationView);

                // Centrer la fenêtre sur l'écran
                frame.setLocationRelativeTo(null);

                // Rendre la fenêtre visible
                frame.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors du chargement des données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    	
    	
    }
}