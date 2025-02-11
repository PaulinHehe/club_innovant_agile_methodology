package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import models.ContratModel;
import objets_bdd.Contrat;

public class ContratView extends JPanel {
    private ContratModel contratModel;
    private JTable contratTable;
    private JButton addButton, editButton, deleteButton;

    public ContratView() {
        // Initialisation du modèle
        contratModel = new ContratModel();

//        // Configuration de la fenêtre
//        setTitle("Gestion des Contrats");
        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);

        // Création des composants
        contratTable = new JTable();
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(contratTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données
        loadContrats();

        // Ajouter des écouteurs d'événements
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContrat();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editContrat();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContrat();
            }
        });
    }

    private void loadContrats() {
        try {
            List<Contrat> contrats = contratModel.getAll();
            String[] columnNames = {"ID", "Type", "Date Début", "Date Fin", "Salaire", "Primes", "Devise", "ID Joueur"};
            Object[][] data = new Object[contrats.size()][8];

            for (int i = 0; i < contrats.size(); i++) {
                Contrat contrat = contrats.get(i);
                data[i][0] = contrat.id;
                data[i][1] = contrat.type;
                data[i][2] = contrat.dateDebut;
                data[i][3] = contrat.dateFin;
                data[i][4] = contrat.salaire;
                data[i][5] = contrat.primes;
                data[i][6] = contrat.devise;
                data[i][7] = contrat.idJoueur;
            }

            contratTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des contrats : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addContrat() {
        ContratForm form = new ContratForm(null, contratModel, false, null);
        form.setVisible(true);
        loadContrats(); // Recharger les données après ajout
    }

    private void editContrat() {
        int selectedRow = contratTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) contratTable.getValueAt(selectedRow, 0);
            try {
                Contrat contrat = contratModel.get(id);
                if (contrat != null) {
                    ContratForm form = new ContratForm(null, contratModel, true, contrat);
                    form.setVisible(true);
                    loadContrats(); // Recharger les données après modification
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération du contrat : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un contrat à modifier", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteContrat() {
        int selectedRow = contratTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) contratTable.getValueAt(selectedRow, 0);
            try {
                if (contratModel.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Contrat supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    loadContrats();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un contrat à supprimer", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContratView().setVisible(true);
            }
        });
    }
}