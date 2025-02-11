package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import models.DonneeMedicaleModel;
import objets_bdd.DonneeMedicale;

public class DonneeMedicaleView extends JPanel {
    private DonneeMedicaleModel donneeMedicaleModel;
    private JTable donneeMedicaleTable;
    private JButton addButton, editButton, deleteButton;

    public DonneeMedicaleView() {
        // Initialisation du modèle
        donneeMedicaleModel = new DonneeMedicaleModel();

        // Configuration de la fenêtre
//        setTitle("Gestion des Données Médicales");
        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);

        // Création des composants
        donneeMedicaleTable = new JTable();
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(donneeMedicaleTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données
        loadDonneesMedicales();

        // Ajouter des écouteurs d'événements
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDonneeMedicale();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDonneeMedicale();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDonneeMedicale();
            }
        });
    }

    private void loadDonneesMedicales() {
        try {
            List<DonneeMedicale> donneesMedicales = donneeMedicaleModel.getAll();
            String[] columnNames = {"ID", "Allergies", "Vaccins", "ID Consultation"};
            Object[][] data = new Object[donneesMedicales.size()][4];

            for (int i = 0; i < donneesMedicales.size(); i++) {
                DonneeMedicale donneeMedicale = donneesMedicales.get(i);
                data[i][0] = donneeMedicale.id;
                data[i][1] = donneeMedicale.allergies;
                data[i][2] = donneeMedicale.vaccins;
                data[i][3] = donneeMedicale.idConsultation;
            }

            donneeMedicaleTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des données médicales : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addDonneeMedicale() {
        DonneeMedicaleForm form = new DonneeMedicaleForm((JFrame)this.getParent(), donneeMedicaleModel, false, null);
        form.setVisible(true);
        loadDonneesMedicales(); // Recharger les données après ajout
    }

    private void editDonneeMedicale() {
        int selectedRow = donneeMedicaleTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) donneeMedicaleTable.getValueAt(selectedRow, 0);
            try {
                DonneeMedicale donneeMedicale = donneeMedicaleModel.get(id);
                if (donneeMedicale != null) {
                    DonneeMedicaleForm form = new DonneeMedicaleForm((JFrame)this.getParent(), donneeMedicaleModel, true, donneeMedicale);
                    form.setVisible(true);
                    loadDonneesMedicales(); // Recharger les données après modification
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération de la donnée médicale : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une donnée médicale à modifier", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteDonneeMedicale() {
        int selectedRow = donneeMedicaleTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) donneeMedicaleTable.getValueAt(selectedRow, 0);
            try {
                if (donneeMedicaleModel.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Donnée médicale supprimée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    loadDonneesMedicales();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une donnée médicale à supprimer", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DonneeMedicaleView().setVisible(true);
            }
        });
    }
}