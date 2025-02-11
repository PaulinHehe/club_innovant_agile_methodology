package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import models.DonneePhysiqueModel;
import objets_bdd.DonneePhysique;

public class DonneePhysiqueView extends JFrame {
    private DonneePhysiqueModel donneePhysiqueModel;
    private JTable donneePhysiqueTable;
    private JButton addButton, editButton, deleteButton;

    public DonneePhysiqueView() {
        // Initialisation du modèle
        donneePhysiqueModel = new DonneePhysiqueModel();

        // Configuration de la fenêtre
        setTitle("Gestion des Données Physiques");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des composants
        donneePhysiqueTable = new JTable();
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(donneePhysiqueTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données
        loadDonneesPhysiques();

        // Ajouter des écouteurs d'événements
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDonneePhysique();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDonneePhysique();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDonneePhysique();
            }
        });
    }

    private void loadDonneesPhysiques() {
        try {
            List<DonneePhysique> donneesPhysiques = donneePhysiqueModel.getAll();
            String[] columnNames = {"ID", "Vitesse Max", "Distance Parcourue", "Nb Sprints", "VO2 Max", "Accélération", "Puissance", "ID du Joueur"};
            Object[][] data = new Object[donneesPhysiques.size()][8];

            for (int i = 0; i < donneesPhysiques.size(); i++) {
                DonneePhysique donneePhysique = donneesPhysiques.get(i);
                data[i][0] = donneePhysique.id;
                data[i][1] = donneePhysique.vitesseMax;
                data[i][2] = donneePhysique.distanceParcourue;
                data[i][3] = donneePhysique.nbSprints;
                data[i][4] = donneePhysique.vo2Max;
                data[i][5] = donneePhysique.acceleration;
                data[i][6] = donneePhysique.puissance;
                data[i][7] = donneePhysique.idjoueur;
            }

            donneePhysiqueTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des données physiques : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addDonneePhysique() {
        DonneePhysiqueForm form = new DonneePhysiqueForm(this, donneePhysiqueModel, false, null);
        form.setVisible(true);
        loadDonneesPhysiques(); // Recharger les données après ajout
    }

    private void editDonneePhysique() {
        int selectedRow = donneePhysiqueTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) donneePhysiqueTable.getValueAt(selectedRow, 0);
            try {
                DonneePhysique donneePhysique = donneePhysiqueModel.get(id);
                if (donneePhysique != null) {
                    DonneePhysiqueForm form = new DonneePhysiqueForm(this, donneePhysiqueModel, true, donneePhysique);
                    form.setVisible(true);
                    loadDonneesPhysiques(); // Recharger les données après modification
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération de la donnée physique : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une donnée physique à modifier", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteDonneePhysique() {
        int selectedRow = donneePhysiqueTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) donneePhysiqueTable.getValueAt(selectedRow, 0);
            try {
                if (donneePhysiqueModel.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Donnée physique supprimée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    loadDonneesPhysiques();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une donnée physique à supprimer", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DonneePhysiqueView().setVisible(true);
            }
        });
    }
}