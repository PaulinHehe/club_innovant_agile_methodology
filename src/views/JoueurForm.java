package views;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import models.JoueurModel;
import objets_bdd.Joueur;

public class JoueurForm extends JDialog {
    private JTextField nomField, prenomField, dateNaissanceField, numeroLicenceField, numeroMaillotField, positionField, tailleField, poidsField, piedFortField, nationaliteField;
    private JButton saveButton, cancelButton;
    private JoueurModel joueurModel;
    private Joueur joueur; // Pour la modification
    private boolean isEditMode; // true si on est en mode modification

    public JoueurForm(JFrame parent, JoueurModel joueurModel, boolean isEditMode, Joueur joueur) {
        super(parent, isEditMode ? "Modifier Joueur" : "Ajouter Joueur", true);
        this.joueurModel = joueurModel;
        this.isEditMode = isEditMode;
        this.joueur = joueur;

        // Configuration de la fenêtre
        setSize(400, 400);
        setLocationRelativeTo(parent);

        // Création des composants
        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        try {
			dateNaissanceField = new JFormattedTextField(new MaskFormatter("####-##-## ##:##:##"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        numeroLicenceField = new JTextField(20);
        numeroMaillotField = new JTextField(20);
        positionField = new JTextField(20);
        tailleField = new JTextField(20);
        poidsField = new JTextField(20);
        piedFortField = new JTextField(20);
        nationaliteField = new JTextField(20);

        saveButton = new JButton("Enregistrer");
        cancelButton = new JButton("Annuler");

        // Layout
        JPanel formPanel = new JPanel(new GridLayout(10, 2));
        formPanel.add(new JLabel("Nom :"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom :"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Date Naissance :"));
        formPanel.add(dateNaissanceField);
        formPanel.add(new JLabel("Numéro Licence :"));
        formPanel.add(numeroLicenceField);
        formPanel.add(new JLabel("Numéro Maillot :"));
        formPanel.add(numeroMaillotField);
        formPanel.add(new JLabel("Position :"));
        formPanel.add(positionField);
        formPanel.add(new JLabel("Taille :"));
        formPanel.add(tailleField);
        formPanel.add(new JLabel("Poids :"));
        formPanel.add(poidsField);
        formPanel.add(new JLabel("Pied Fort :"));
        formPanel.add(piedFortField);
        formPanel.add(new JLabel("Nationalité :"));
        formPanel.add(nationaliteField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Si on est en mode modification, on pré-remplit les champs
        if (isEditMode && joueur != null) {
            nomField.setText(joueur.nom);
            prenomField.setText(joueur.prenom);
            dateNaissanceField.setText(joueur.dateNaissance.toString());
            numeroLicenceField.setText(String.valueOf(joueur.numeroLicence));
            numeroMaillotField.setText(String.valueOf(joueur.numeroMaillot));
            positionField.setText(joueur.position);
            tailleField.setText(String.valueOf(joueur.taille));
            poidsField.setText(String.valueOf(joueur.poids));
            piedFortField.setText(joueur.piedFort);
            nationaliteField.setText(joueur.nationalite);
        }

        // Ajouter des écouteurs d'événements
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveJoueur();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });
    }

    private void saveJoueur() {
        // Récupérer les valeurs des champs
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        Timestamp dateNaissance = Timestamp.valueOf(dateNaissanceField.getText());
        int numeroLicence = Integer.parseInt(numeroLicenceField.getText());
        int numeroMaillot = Integer.parseInt(numeroMaillotField.getText());
        String position = positionField.getText();
        float taille = Float.parseFloat(tailleField.getText());
        float poids = Float.parseFloat(poidsField.getText());
        String piedFort = piedFortField.getText();
        String nationalite = nationaliteField.getText();

        try {
            if (isEditMode && joueur != null) {
                // Mode modification
                joueur.nom = nom;
                joueur.prenom = prenom;
                joueur.dateNaissance = dateNaissance;
                joueur.numeroLicence = numeroLicence;
                joueur.numeroMaillot = numeroMaillot;
                joueur.position = position;
                joueur.taille = taille;
                joueur.poids = poids;
                joueur.piedFort = piedFort;
                joueur.nationalite = nationalite;

                if (joueurModel.update(joueur.id, new String[]{"nom", "nationalite", "prenom", "dateNaissance", "numeroLicence", "numeroMaillot", "position", "taille", "poids", "piedFort"},
                        new Object[]{nom, nationalite, prenom, dateNaissance, numeroLicence, numeroMaillot, position, taille, poids, piedFort})) {
                    JOptionPane.showMessageDialog(this, "Joueur modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mode ajout
                Joueur newJoueur = new Joueur();
                newJoueur.nom = nom;
                newJoueur.prenom = prenom;
                newJoueur.dateNaissance = dateNaissance;
                newJoueur.numeroLicence = numeroLicence;
                newJoueur.numeroMaillot = numeroMaillot;
                newJoueur.position = position;
                newJoueur.taille = taille;
                newJoueur.poids = poids;
                newJoueur.piedFort = piedFort;
                newJoueur.nationalite = nationalite;

                if (joueurModel.create(new String[]{"nom", "nationalite", "prenom", "dateNaissance", "numeroLicence", "numeroMaillot", "position", "taille", "poids", "piedFort"},
                        new Object[]{nom, nationalite, prenom, dateNaissance, numeroLicence, numeroMaillot, position, taille, poids, piedFort})) {
                    JOptionPane.showMessageDialog(this, "Joueur ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}