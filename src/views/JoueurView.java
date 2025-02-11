package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import models.JoueurModel;
import objets_bdd.Joueur;

public class JoueurView extends JPanel {
    private JoueurModel joueurModel;
    private JTable joueurTable;
    private JButton addButton, editButton, deleteButton;

    public JoueurView() {
        // Initialisation du modèle
        joueurModel = new JoueurModel();

        // Configuration du panel
        setLayout(new BorderLayout());

        // Création des composants
        joueurTable = new JTable();
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(new JScrollPane(joueurTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données
        loadJoueurs();

        // Ajouter des écouteurs d'événements
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJoueur();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editJoueur();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteJoueur();
            }
        });
    }

    private void loadJoueurs() {
        try {
            List<Joueur> joueurs = joueurModel.getAll();
            String[] columnNames = {"ID", "Nom", "Prénom", "Date Naissance", "Numéro Licence", "Numéro Maillot", "Position", "Taille", "Poids", "Pied Fort"};
            Object[][] data = new Object[joueurs.size()][10];

            for (int i = 0; i < joueurs.size(); i++) {
                Joueur joueur = joueurs.get(i);
                data[i][0] = joueur.id;
                data[i][1] = joueur.nom;
                data[i][2] = joueur.prenom;
                data[i][3] = joueur.dateNaissance;
                data[i][4] = joueur.numeroLicence;
                data[i][5] = joueur.numeroMaillot;
                data[i][6] = joueur.position;
                data[i][7] = joueur.taille;
                data[i][8] = joueur.poids;
                data[i][9] = joueur.piedFort;
            }

            joueurTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des joueurs : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addJoueur() {
    	JoueurForm form = new JoueurForm(null, joueurModel, false, null);
        form.setVisible(true);
        loadJoueurs(); // Recharger les données après ajout
    }

    private void editJoueur() {
    	int selectedRow = joueurTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) joueurTable.getValueAt(selectedRow, 0);
            try {
                Joueur joueur = joueurModel.get(id);
                if (joueur != null) {
                    JoueurForm form = new JoueurForm(null, joueurModel, true, joueur);
                    form.setVisible(true);
                    loadJoueurs(); // Recharger les données après modification
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération du joueur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un joueur à modifier", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteJoueur() {
        int selectedRow = joueurTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) joueurTable.getValueAt(selectedRow, 0);
            try {
                if (joueurModel.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Joueur supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    loadJoueurs();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un joueur à supprimer", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }
}