package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import models.PerformanceModel;
import objets_bdd.PerformanceMatch;

public class PerformanceView extends JPanel {
    private PerformanceModel performanceModel;
    private JTable performanceTable;
    private JButton addButton, editButton, deleteButton;

    public PerformanceView() {
        // Initialisation du modèle
        performanceModel = new PerformanceModel();

        // Configuration du panel
        setLayout(new BorderLayout());

        // Création des composants
        performanceTable = new JTable();
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(new JScrollPane(performanceTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données
        loadPerformances();

        // Ajouter des écouteurs d'événements
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPerformance();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPerformance();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePerformance();
            }
        });
    }

    private void loadPerformances() {
        try {
            List<PerformanceMatch> performances = performanceModel.getAll();
            String[] columnNames = {"ID", "Buts", "Date Match", "Passes Décisives", "Tirs Cadrés", "Dribbles Réussis", "xG"};
            Object[][] data = new Object[performances.size()][7];

            for (int i = 0; i < performances.size(); i++) {
                PerformanceMatch performance = performances.get(i);
                data[i][0] = performance.id;
                data[i][1] = performance.buts;
                data[i][2] = performance.dateMatch;
                data[i][3] = performance.passesDecisives;
                data[i][4] = performance.tirsCadres;
                data[i][5] = performance.dribblesReussis;
                data[i][6] = performance.xg;
            }

            performanceTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des performances : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPerformance() {
    	PerformanceForm form = new PerformanceForm(null, performanceModel, false, null);
        form.setVisible(true);
        loadPerformances(); // Recharger les données après ajout
    }

    private void editPerformance() {
    	int selectedRow = performanceTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) performanceTable.getValueAt(selectedRow, 0);
            try {
            	PerformanceMatch performance = performanceModel.get(id);
                if (performance != null) {
                    PerformanceForm form = new PerformanceForm(null, performanceModel, true, performance);
                    form.setVisible(true);
                    loadPerformances(); // Recharger les données après modification
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération du performance : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un performance à modifier", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deletePerformance() {
        int selectedRow = performanceTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) performanceTable.getValueAt(selectedRow, 0);
            try {
                if (performanceModel.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Performance supprimée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    loadPerformances();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une performance à supprimer", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }
}