package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import models.ConsultationModel;
import objets_bdd.Consultation;

public class ConsultationView extends JFrame {
    private ConsultationModel consultationModel;
    private JTable consultationTable;
    private JButton addButton, editButton, deleteButton;

    public ConsultationView() {
        // Initialisation du modèle
        consultationModel = new ConsultationModel();

        // Configuration de la fenêtre
        setTitle("Gestion des Consultations");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des composants
        consultationTable = new JTable();
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(consultationTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données
        loadConsultations();

        // Ajouter des écouteurs d'événements
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addConsultation();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editConsultation();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteConsultation();
            }
        });
    }

    private void loadConsultations() {
        try {
            List<Consultation> consultations = consultationModel.getAll();
            String[] columnNames = {"ID", "Détails", "Résultats", "Traitements", "ID Joueur", "ID Médecin"};
            Object[][] data = new Object[consultations.size()][6];

            for (int i = 0; i < consultations.size(); i++) {
                Consultation consultation = consultations.get(i);
                data[i][0] = consultation.id;
                data[i][1] = consultation.details;
                data[i][2] = consultation.resultats;
                data[i][3] = consultation.traitements;
                data[i][4] = consultation.idJoueur;
                data[i][5] = consultation.idMedecin;
            }

            consultationTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des consultations : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addConsultation() {
        ConsultationForm form = new ConsultationForm(this, consultationModel, false, null);
        form.setVisible(true);
        loadConsultations(); // Recharger les données après ajout
    }

    private void editConsultation() {
        int selectedRow = consultationTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) consultationTable.getValueAt(selectedRow, 0);
            try {
                Consultation consultation = consultationModel.get(id);
                if (consultation != null) {
                    ConsultationForm form = new ConsultationForm(this, consultationModel, true, consultation);
                    form.setVisible(true);
                    loadConsultations(); // Recharger les données après modification
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération de la consultation : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une consultation à modifier", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteConsultation() {
        int selectedRow = consultationTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) consultationTable.getValueAt(selectedRow, 0);
            try {
                if (consultationModel.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Consultation supprimée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    loadConsultations();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une consultation à supprimer", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConsultationView().setVisible(true);
            }
        });
    }
}