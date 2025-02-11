package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import models.UtilisateurModel;
import objets_bdd.Utilisateur;

public class UtilisateurForm extends JDialog {
    private JTextField nomField, prenomField, usernameField, mdpField, roleField;
    private JButton saveButton, cancelButton;
    private UtilisateurModel utilisateurModel;
    private Utilisateur utilisateur; // Pour la modification
    private boolean isEditMode; // true si on est en mode modification

    public UtilisateurForm(JFrame parent, UtilisateurModel utilisateurModel, boolean isEditMode, Utilisateur utilisateur) {
        super(parent, isEditMode ? "Modifier Utilisateur" : "Ajouter Utilisateur", true);
        this.utilisateurModel = utilisateurModel;
        this.isEditMode = isEditMode;
        this.utilisateur = utilisateur;

        // Configuration de la fenêtre
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Création des composants
        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        usernameField = new JTextField(20);
        mdpField = new JTextField(20);
        roleField = new JTextField(20);

        saveButton = new JButton("Enregistrer");
        cancelButton = new JButton("Annuler");

        // Layout
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("Nom :"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom :"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Username :"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Mot de passe :"));
        formPanel.add(mdpField);
        formPanel.add(new JLabel("Rôle :"));
        formPanel.add(roleField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Si on est en mode modification, on pré-remplit les champs
        if (isEditMode && utilisateur != null) {
            nomField.setText(utilisateur.nom);
            prenomField.setText(utilisateur.prenom);
            usernameField.setText(utilisateur.username);
            mdpField.setText(utilisateur.mdp);
            roleField.setText(utilisateur.role);
        }

        // Ajouter des écouteurs d'événements
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUtilisateur();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });
    }

    private void saveUtilisateur() {
        // Récupérer les valeurs des champs
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String username = usernameField.getText();
        String mdp = mdpField.getText();
        String role = roleField.getText();

        try {
            if (isEditMode && utilisateur != null) {
                // Mode modification
                utilisateur.nom = nom;
                utilisateur.prenom = prenom;
                utilisateur.username = username;
                utilisateur.mdp = mdp;
                utilisateur.role = role;

                if (utilisateurModel.update(utilisateur.id, new String[]{"nom", "prenom", "username", "mdp", "role"},
                        new Object[]{nom, prenom, username, mdp, role})) {
                    JOptionPane.showMessageDialog(this, "Utilisateur modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mode ajout
                Utilisateur newUtilisateur = new Utilisateur();
                newUtilisateur.nom = nom;
                newUtilisateur.prenom = prenom;
                newUtilisateur.username = username;
                newUtilisateur.mdp = mdp;
                newUtilisateur.role = role;

                if (utilisateurModel.create(new String[]{"nom", "prenom", "username", "mdp", "role"},
                        new Object[]{nom, prenom, username, mdp, role})) {
                    JOptionPane.showMessageDialog(this, "Utilisateur ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}