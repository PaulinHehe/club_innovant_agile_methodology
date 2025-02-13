package views;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.sql.Timestamp;

import models.ContratModel;
import objets_bdd.Contrat;

public class ContratForm extends JDialog {
    private JTextField typeField, salaireField, primeField, deviseField, idJoueurField;
    private JFormattedTextField dateDebutField, dateFinField;
    private JButton saveButton, cancelButton;
    private ContratModel contratModel;
    private Contrat contrat; // Pour la modification
    private boolean isEditMode; // true si on est en mode modification

    public ContratForm(JFrame parent, ContratModel contratModel, boolean isEditMode, Contrat contrat) {
        super(parent, isEditMode ? "Modifier Contrat" : "Ajouter Contrat", true);
        this.contratModel = contratModel;
        this.isEditMode = isEditMode;
        this.contrat = contrat;

        // Configuration de la fenêtre
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Création des composants
        typeField = new JTextField(20);
        salaireField = new JTextField(20);
        primeField = new JTextField(20);
        deviseField = new JTextField(20);
        idJoueurField = new JTextField(20);
        
        try {
        	dateDebutField = new JFormattedTextField(new MaskFormatter("####-##-## ##:##:##"));
        	dateFinField = new JFormattedTextField(new MaskFormatter("####-##-## ##:##:##"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        saveButton = new JButton("Enregistrer");
        cancelButton = new JButton("Annuler");

        // Layout
        JPanel formPanel = new JPanel(new GridLayout(8, 2));
        formPanel.add(new JLabel("Type :"));
        formPanel.add(typeField);
        formPanel.add(new JLabel("Salaire :"));
        formPanel.add(salaireField);
        formPanel.add(new JLabel("Prime :"));
        formPanel.add(primeField);
        formPanel.add(new JLabel("Devise :"));
        formPanel.add(deviseField);
        formPanel.add(new JLabel("ID Joueur :"));
        formPanel.add(idJoueurField);
        formPanel.add(new JLabel("Debut du contrat:"));
        formPanel.add(dateDebutField);
        
        formPanel.add(new JLabel("Fin du contrat:"));
        formPanel.add(dateFinField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Si on est en mode modification, on pré-remplit les champs
        if (isEditMode && contrat != null) {
            typeField.setText(String.valueOf(contrat.type));
            salaireField.setText(String.valueOf(contrat.salaire));
            primeField.setText(String.valueOf(contrat.primes));
            deviseField.setText(contrat.devise);
            idJoueurField.setText(String.valueOf(contrat.idJoueur));
            dateDebutField.setText(contrat.dateDebut.toString());
            dateFinField.setText(contrat.dateFin.toString());
        }

        // Ajouter des écouteurs d'événements
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveContrat();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });
    }

    private void saveContrat() {
        // Récupérer les valeurs des champs
        int type = Integer.parseInt(typeField.getText());
        float salaire = Float.parseFloat(salaireField.getText());
        float prime = Float.parseFloat(primeField.getText());
        String devise = deviseField.getText();
        int idJoueur = Integer.parseInt(idJoueurField.getText());
        String dateDebut = dateDebutField.getText();
        String dateFin = dateFinField.getText();

        try {
            if (isEditMode && contrat != null) {
                // Mode modification
                contrat.type = type;
                contrat.salaire = salaire;
                contrat.primes = prime;
                contrat.devise = devise;
                contrat.idJoueur = idJoueur;
                contrat.dateDebut = Timestamp.valueOf(dateDebut);
                contrat.dateFin = Timestamp.valueOf(dateFin);

                if (contratModel.update(contrat.id, new String[]{"type", "salaire", "primes", "devise", "idJoueur", "dateDebut", "dateFin"},
                        new Object[]{type, salaire, prime, devise, idJoueur, dateDebut, dateFin})) {
                    JOptionPane.showMessageDialog(this, "Contrat modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mode ajout
                Contrat newContrat = new Contrat();
                newContrat.type = type;
                newContrat.salaire = salaire;
                newContrat.primes = prime;
                newContrat.devise = devise;
                newContrat.idJoueur = idJoueur;
                contrat.dateDebut = Timestamp.valueOf(dateDebut);
                contrat.dateFin = Timestamp.valueOf(dateFin);

                if (contratModel.create(new String[]{"type", "salaire", "primes", "devise", "idJoueur", "dateDebut", "dateFin"},
                        new Object[]{type, salaire, prime, devise, idJoueur, dateDebut, dateFin})) {
                    JOptionPane.showMessageDialog(this, "Contrat ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
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