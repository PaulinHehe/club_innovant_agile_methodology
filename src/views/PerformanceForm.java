package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;

import models.PerformanceModel;
import objets_bdd.PerformanceMatch;

public class PerformanceForm extends JDialog {
    private JTextField butsField, dateMatchField, passesDecisivesField, tirsCadresField, dribblesReussisField, xgField;
    private JButton saveButton, cancelButton;
    private PerformanceModel performanceModel;
    private PerformanceMatch performance; // Pour la modification
    private boolean isEditMode; // true si on est en mode modification

    public PerformanceForm(JFrame parent, PerformanceModel performanceModel, boolean isEditMode, PerformanceMatch performance) {
        super(parent, isEditMode ? "Modifier Performance" : "Ajouter Performance", true);
        this.performanceModel = performanceModel;
        this.isEditMode = isEditMode;
        this.performance = performance;

        // Configuration de la fenêtre
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Création des composants
        butsField = new JTextField(20);
        dateMatchField = new JTextField(20);
        passesDecisivesField = new JTextField(20);
        tirsCadresField = new JTextField(20);
        dribblesReussisField = new JTextField(20);
        xgField = new JTextField(20);

        saveButton = new JButton("Enregistrer");
        cancelButton = new JButton("Annuler");

        // Layout
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        formPanel.add(new JLabel("Buts :"));
        formPanel.add(butsField);
        formPanel.add(new JLabel("Date Match :"));
        formPanel.add(dateMatchField);
        formPanel.add(new JLabel("Passes Décisives :"));
        formPanel.add(passesDecisivesField);
        formPanel.add(new JLabel("Tirs Cadrés :"));
        formPanel.add(tirsCadresField);
        formPanel.add(new JLabel("Dribbles Réussis :"));
        formPanel.add(dribblesReussisField);
        formPanel.add(new JLabel("xG :"));
        formPanel.add(xgField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Si on est en mode modification, on pré-remplit les champs
        if (isEditMode && performance != null) {
            butsField.setText(String.valueOf(performance.buts));
            dateMatchField.setText(performance.dateMatch.toString());
            passesDecisivesField.setText(String.valueOf(performance.passesDecisives));
            tirsCadresField.setText(String.valueOf(performance.tirsCadres));
            dribblesReussisField.setText(String.valueOf(performance.dribblesReussis));
            xgField.setText(String.valueOf(performance.xg));
        }

        // Ajouter des écouteurs d'événements
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePerformance();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });
    }

    private void savePerformance() {
        // Récupérer les valeurs des champs
        int buts = Integer.parseInt(butsField.getText());
        Timestamp dateMatch = Timestamp.valueOf(dateMatchField.getText());
        int passesDecisives = Integer.parseInt(passesDecisivesField.getText());
        int tirsCadres = Integer.parseInt(tirsCadresField.getText());
        int dribblesReussis = Integer.parseInt(dribblesReussisField.getText());
        float xg = Float.parseFloat(xgField.getText());

        try {
            if (isEditMode && performance != null) {
                // Mode modification
                performance.buts = buts;
                performance.dateMatch = dateMatch;
                performance.passesDecisives = passesDecisives;
                performance.tirsCadres = tirsCadres;
                performance.dribblesReussis = dribblesReussis;
                performance.xg = xg;

                if (performanceModel.update(performance.id, new String[]{"buts", "date_match", "passes_decisives", "tirs_cadres", "dribbles_reussis", "xg"},
                        new Object[]{buts, dateMatch, passesDecisives, tirsCadres, dribblesReussis, xg})) {
                    JOptionPane.showMessageDialog(this, "Performance modifiée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mode ajout
                PerformanceMatch newPerformance = new PerformanceMatch();
                newPerformance.buts = buts;
                newPerformance.dateMatch = dateMatch;
                newPerformance.passesDecisives = passesDecisives;
                newPerformance.tirsCadres = tirsCadres;
                newPerformance.dribblesReussis = dribblesReussis;
                newPerformance.xg = xg;

                if (performanceModel.create(new String[]{"buts", "date_match", "passes_decisives", "tirs_cadres", "dribbles_reussis", "xg"},
                        new Object[]{buts, dateMatch, passesDecisives, tirsCadres, dribblesReussis, xg})) {
                    JOptionPane.showMessageDialog(this, "Performance ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
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