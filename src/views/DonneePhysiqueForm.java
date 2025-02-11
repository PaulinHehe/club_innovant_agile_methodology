package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import models.DonneePhysiqueModel;
import objets_bdd.DonneePhysique;

public class DonneePhysiqueForm extends JDialog {
    private JTextField vitessMaxField, distanceParcourueField, nbSprintsField, vo2MaxField, accelerationField, puissanceField, idJoueurField;
    private JButton saveButton, cancelButton;
    private DonneePhysiqueModel donneePhysiqueModel;
    private DonneePhysique donneePhysique; // Pour la modification
    private boolean isEditMode; // true si on est en mode modification

    public DonneePhysiqueForm(JFrame parent, DonneePhysiqueModel donneePhysiqueModel, boolean isEditMode, DonneePhysique donneePhysique) {
        super(parent, isEditMode ? "Modifier Donnée Physique" : "Ajouter Donnée Physique", true);
        this.donneePhysiqueModel = donneePhysiqueModel;
        this.isEditMode = isEditMode;
        this.donneePhysique = donneePhysique;

        // Configuration de la fenêtre
        setSize(400, 400);
        setLocationRelativeTo(parent);

        // Création des composants
        vitessMaxField = new JTextField(20);
        distanceParcourueField = new JTextField(20);
        nbSprintsField = new JTextField(20);
        vo2MaxField = new JTextField(20);
        accelerationField = new JTextField(20);
        puissanceField = new JTextField(20);
        idJoueurField = new JTextField(20);

        saveButton = new JButton("Enregistrer");
        cancelButton = new JButton("Annuler");

        // Layout
        JPanel formPanel = new JPanel(new GridLayout(8, 2));
        formPanel.add(new JLabel("Vitesse Max :"));
        formPanel.add(vitessMaxField);
        formPanel.add(new JLabel("Distance Parcourue :"));
        formPanel.add(distanceParcourueField);
        formPanel.add(new JLabel("Nb Sprints :"));
        formPanel.add(nbSprintsField);
        formPanel.add(new JLabel("VO2 Max :"));
        formPanel.add(vo2MaxField);
        formPanel.add(new JLabel("Accélération :"));
        formPanel.add(accelerationField);
        formPanel.add(new JLabel("Puissance :"));
        formPanel.add(puissanceField);
        formPanel.add(new JLabel("ID Joueur :"));
        formPanel.add(idJoueurField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Si on est en mode modification, on pré-remplit les champs
        if (isEditMode && donneePhysique != null) {
            vitessMaxField.setText(String.valueOf(donneePhysique.vitesseMax));
            distanceParcourueField.setText(String.valueOf(donneePhysique.distanceParcourue));
            nbSprintsField.setText(String.valueOf(donneePhysique.nbSprints));
            vo2MaxField.setText(String.valueOf(donneePhysique.vo2Max));
            accelerationField.setText(String.valueOf(donneePhysique.acceleration));
            puissanceField.setText(String.valueOf(donneePhysique.puissance));
            idJoueurField.setText(String.valueOf(donneePhysique.idjoueur));
        }

        // Ajouter des écouteurs d'événements
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDonneePhysique();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });
    }

    private void saveDonneePhysique() {
        // Récupérer les valeurs des champs
        float vitessMax = Float.parseFloat(vitessMaxField.getText());
        float distanceParcourue = Float.parseFloat(distanceParcourueField.getText());
        int nbSprints = Integer.parseInt(nbSprintsField.getText());
        float vo2Max = Float.parseFloat(vo2MaxField.getText());
        float acceleration = Float.parseFloat(accelerationField.getText());
        float puissance = Float.parseFloat(puissanceField.getText());
        int idJoueur = Integer.parseInt(idJoueurField.getText());

        try {
            if (isEditMode && donneePhysique != null) {
                // Mode modification
                donneePhysique.vitesseMax = vitessMax;
                donneePhysique.distanceParcourue = distanceParcourue;
                donneePhysique.nbSprints = nbSprints;
                donneePhysique.vo2Max = vo2Max;
                donneePhysique.acceleration = acceleration;
                donneePhysique.puissance = puissance;
                donneePhysique.idjoueur = idJoueur;

                if (donneePhysiqueModel.update(donneePhysique.id, new String[]{"vitessMax", "distanceParcourue", "nombreSprints", "vo2Max", "acceleration", "puissance", "id Joueur"},
                        new Object[]{vitessMax, distanceParcourue, nbSprints, vo2Max, acceleration, puissance, idJoueur})) {
                    JOptionPane.showMessageDialog(this, "Donnée physique modifiée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mode ajout
                DonneePhysique newDonneePhysique = new DonneePhysique();
                newDonneePhysique.vitesseMax = vitessMax;
                newDonneePhysique.distanceParcourue = distanceParcourue;
                newDonneePhysique.nbSprints = nbSprints;
                newDonneePhysique.vo2Max = vo2Max;
                newDonneePhysique.acceleration = acceleration;
                newDonneePhysique.puissance = puissance;
                newDonneePhysique.idjoueur = idJoueur;

                if (donneePhysiqueModel.create(new String[]{"vitessMax", "distanceParcourue", "nombreSprints", "vo2Max", "acceleration", "puissance", "id Joueur"},
                        new Object[]{vitessMax, distanceParcourue, nbSprints, vo2Max, acceleration, puissance, idJoueur})) {
                    JOptionPane.showMessageDialog(this, "Donnée physique ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
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