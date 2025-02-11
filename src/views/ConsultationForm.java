package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import models.ConsultationModel;
import objets_bdd.Consultation;

public class ConsultationForm extends JDialog {
    private JTextField detailsField, resultatsField, traitementsField, idJoueurField, idMedecinField;
    private JButton saveButton, cancelButton;
    private ConsultationModel consultationModel;
    private Consultation consultation; // Pour la modification
    private boolean isEditMode; // true si on est en mode modification

    public ConsultationForm(JFrame parent, ConsultationModel consultationModel, boolean isEditMode, Consultation consultation) {
        super(parent, isEditMode ? "Modifier Consultation" : "Ajouter Consultation", true);
        this.consultationModel = consultationModel;
        this.isEditMode = isEditMode;
        this.consultation = consultation;

        // Configuration de la fenêtre
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Création des composants
        detailsField = new JTextField(20);
        resultatsField = new JTextField(20);
        traitementsField = new JTextField(20);
        idJoueurField = new JTextField(20);
        idMedecinField = new JTextField(20);

        saveButton = new JButton("Enregistrer");
        cancelButton = new JButton("Annuler");

        // Layout
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("Détails :"));
        formPanel.add(detailsField);
        formPanel.add(new JLabel("Résultats :"));
        formPanel.add(resultatsField);
        formPanel.add(new JLabel("Traitements :"));
        formPanel.add(traitementsField);
        formPanel.add(new JLabel("ID Joueur :"));
        formPanel.add(idJoueurField);
        formPanel.add(new JLabel("ID Médecin :"));
        formPanel.add(idMedecinField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Si on est en mode modification, on pré-remplit les champs
        if (isEditMode && consultation != null) {
            detailsField.setText(consultation.details);
            resultatsField.setText(consultation.resultats);
            traitementsField.setText(consultation.traitements);
            idJoueurField.setText(String.valueOf(consultation.idJoueur));
            idMedecinField.setText(String.valueOf(consultation.idMedecin));
        }

        // Ajouter des écouteurs d'événements
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConsultation();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });
    }

    private void saveConsultation() {
    	// Récupérer les valeurs des champs
        String details = detailsField.getText();
        String resultats = resultatsField.getText();
        String traitements = traitementsField.getText();
        int idJoueur = Integer.parseInt(idJoueurField.getText());
        int idMedecin = Integer.parseInt(idMedecinField.getText());

        try {
            if (isEditMode && consultation != null) {
                // Mode modification
                consultation.details = details;
                consultation.resultats = resultats;
                consultation.traitements = traitements;
                consultation.idJoueur = idJoueur;
                consultation.idMedecin = idMedecin;

                if (consultationModel.update(consultation.id, new String[]{"details", "resultats", "traitements", "idjoueurs", "idmedecin"},
                        new Object[]{details, resultats, traitements, idJoueur, idMedecin})) {
                    JOptionPane.showMessageDialog(this, "Consultation modifiée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mode ajout
                Consultation newConsultation = new Consultation();
                newConsultation.details = details;
                newConsultation.resultats = resultats;
                newConsultation.traitements = traitements;
                newConsultation.idJoueur = idJoueur;
                newConsultation.idMedecin = idMedecin;

                if (consultationModel.create(new String[]{"details", "resultats", "traitements", "idjoueurs", "idmedecin"},
                        new Object[]{details, resultats, traitements, idJoueur, idMedecin})) {
                    JOptionPane.showMessageDialog(this, "Consultation ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides pour ID Joueur et ID Médecin", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}