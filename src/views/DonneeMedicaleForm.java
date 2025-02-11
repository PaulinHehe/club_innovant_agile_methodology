package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import models.DonneeMedicaleModel;
import objets_bdd.DonneeMedicale;

public class DonneeMedicaleForm extends JDialog {
    private JTextField allergiesField, vaccinsField, idConsultationField;
    private JButton saveButton, cancelButton;
    private DonneeMedicaleModel donneeMedicaleModel;
    private DonneeMedicale donneeMedicale; // Pour la modification
    private boolean isEditMode; // true si on est en mode modification

    public DonneeMedicaleForm(JFrame parent, DonneeMedicaleModel donneeMedicaleModel, boolean isEditMode, DonneeMedicale donneeMedicale) {
        super(parent, isEditMode ? "Modifier Donnée Médicale" : "Ajouter Donnée Médicale", true);
        this.donneeMedicaleModel = donneeMedicaleModel;
        this.isEditMode = isEditMode;
        this.donneeMedicale = donneeMedicale;

        // Configuration de la fenêtre
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Création des composants
        allergiesField = new JTextField(20);
        vaccinsField = new JTextField(20);
        idConsultationField = new JTextField(20);

        saveButton = new JButton("Enregistrer");
        cancelButton = new JButton("Annuler");

        // Layout
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Allergies :"));
        formPanel.add(allergiesField);
        formPanel.add(new JLabel("Vaccins :"));
        formPanel.add(vaccinsField);
        formPanel.add(new JLabel("ID Consultation :"));
        formPanel.add(idConsultationField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Si on est en mode modification, on pré-remplit les champs
        if (isEditMode && donneeMedicale != null) {
            allergiesField.setText(donneeMedicale.allergies);
            vaccinsField.setText(donneeMedicale.vaccins);
            idConsultationField.setText(String.valueOf(donneeMedicale.idConsultation));
        }

        // Ajouter des écouteurs d'événements
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDonneeMedicale();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });
    }

    private void saveDonneeMedicale() {
        // Récupérer les valeurs des champs
        String allergies = allergiesField.getText();
        String vaccins = vaccinsField.getText();
        int idConsultation = Integer.parseInt(idConsultationField.getText());

        try {
            if (isEditMode && donneeMedicale != null) {
                // Mode modification
                donneeMedicale.allergies = allergies;
                donneeMedicale.vaccins = vaccins;
                donneeMedicale.idConsultation = idConsultation;

                if (donneeMedicaleModel.update(donneeMedicale.id, new String[]{"allergies", "vaccins", "idConsultation"},
                        new Object[]{allergies, vaccins, idConsultation})) {
                    JOptionPane.showMessageDialog(this, "Donnée médicale modifiée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mode ajout
                DonneeMedicale newDonneeMedicale = new DonneeMedicale();
                newDonneeMedicale.allergies = allergies;
                newDonneeMedicale.vaccins = vaccins;
                newDonneeMedicale.idConsultation = idConsultation;

                if (donneeMedicaleModel.create(new String[]{"allergies", "vaccins", "idConsultation"},
                        new Object[]{allergies, vaccins, idConsultation})) {
                    JOptionPane.showMessageDialog(this, "Donnée médicale ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID de consultation valide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}