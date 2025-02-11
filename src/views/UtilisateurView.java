package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import models.UtilisateurModel;
import objets_bdd.Utilisateur;
import objets_bdd.Utilisateur;

public class UtilisateurView extends JPanel {
    private UtilisateurModel utilisateurModel;
    private JTable utilisateurTable;
    private JButton addButton, editButton, deleteButton;

    public UtilisateurView() {
        // Initialisation du modèle
        utilisateurModel = new UtilisateurModel();

        // Configuration du panel
        setLayout(new BorderLayout());

        // Création des composants
        utilisateurTable = new JTable();
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(new JScrollPane(utilisateurTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données
        loadUtilisateurs();

        // Ajouter des écouteurs d'événements
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUtilisateur();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUtilisateur();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUtilisateur();
            }
        });
    }

    private void loadUtilisateurs() {
        try {
            List<Utilisateur> utilisateurs = utilisateurModel.getAll();
            String[] columnNames = {"ID", "Nom", "Prénom", "Username", "Mot de passe", "Rôle"};
            Object[][] data = new Object[utilisateurs.size()][6];

            for (int i = 0; i < utilisateurs.size(); i++) {
                Utilisateur utilisateur = utilisateurs.get(i);
                data[i][0] = utilisateur.id;
                data[i][1] = utilisateur.nom;
                data[i][2] = utilisateur.prenom;
                data[i][3] = utilisateur.username;
                data[i][4] = utilisateur.mdp;
                data[i][5] = utilisateur.role;
            }

            utilisateurTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des utilisateurs : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addUtilisateur() {
    	UtilisateurForm form = new UtilisateurForm(null, utilisateurModel, false, null);
        form.setVisible(true);
        loadUtilisateurs(); // Recharger les données après ajout
    }

    private void editUtilisateur() {
    	int selectedRow = utilisateurTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) utilisateurTable.getValueAt(selectedRow, 0);
            try {
                Utilisateur utilisateur = utilisateurModel.get(id);
                if (utilisateur != null) {
                    UtilisateurForm form = new UtilisateurForm(null, utilisateurModel, true, utilisateur);
                    form.setVisible(true);
                    loadUtilisateurs(); // Recharger les données après modification
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération du utilisateur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un utilisateur à modifier", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteUtilisateur() {
        int selectedRow = utilisateurTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) utilisateurTable.getValueAt(selectedRow, 0);
            try {
                if (utilisateurModel.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Utilisateur supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    loadUtilisateurs();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un utilisateur à supprimer", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }
}