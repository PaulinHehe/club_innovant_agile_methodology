package views;

import models.ConsultationModel;
import models.DonneeMedicaleModel;
import models.DonneePhysiqueModel;
import models.DonneePsychologiqueModel;
import models.JoueurModel;
import objets_bdd.DonneeMedicale;
import objets_bdd.DonneePhysique;
import objets_bdd.DonneePsychologique;
import objets_bdd.Joueur;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationView extends JPanel {
    private JList<String> joueursList;
    private DefaultListModel<String> joueursListModel;
    private JTabbedPane onglets;
    private JLabel physiquesLabel, medicalesLabel, psychologiquesLabel;
    private JButton precedentPhysique, suivantPhysique;
    private JButton precedentMedical, suivantMedical;
    private JButton precedentPsychologique, suivantPsychologique;

    private List<Joueur> joueurs;
    private List<DonneePhysique> donneesPhysiques;
    private List<DonneeMedicale> donneesMedicales;
    private List<DonneePsychologique> donneesPsychologiques;
    private int indexPhysique, indexMedical, indexPsychologique;

    private JoueurModel joueurModel;
    private DonneePhysiqueModel physiqueModel;
    private DonneeMedicaleModel medicalModel;
    private DonneePsychologiqueModel psychologiqueModel;

    public ConsultationView() throws SQLException {
        joueurModel = new JoueurModel();
        physiqueModel = new DonneePhysiqueModel();
        medicalModel = new DonneeMedicaleModel();
        psychologiqueModel = new DonneePsychologiqueModel();
        
        joueurs = new ArrayList<>();
        donneesPhysiques = new ArrayList<>();
        donneesMedicales = new ArrayList<>();
        donneesPsychologiques = new ArrayList<>();

        setLayout(new BorderLayout());

        // Panel gauche : Liste des joueurs
        joueursListModel = new DefaultListModel<>();
        joueursList = new JList<>(joueursListModel);
        joueursList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        joueursList.setBorder(new TitledBorder("Liste des joueurs"));
        joueursList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
					chargerDonneesJoueur(joueursList.getSelectedIndex());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        JScrollPane scrollJoueurs = new JScrollPane(joueursList);
        scrollJoueurs.setPreferredSize(new Dimension(250, getHeight()));
        add(scrollJoueurs, BorderLayout.WEST);

        // Panel droit : Onglets
        onglets = new JTabbedPane();
        onglets.addTab("Physiques", creerOngletPhysiques());
        onglets.addTab("Médicales", creerOngletMedicales());
        onglets.addTab("Psychologiques", creerOngletPsychologiques());
        add(onglets, BorderLayout.CENTER);

        // Charger les joueurs au démarrage
        chargerJoueurs();
    }

    private JPanel creerOngletPhysiques() {
        JPanel panel = new JPanel(new BorderLayout());
        physiquesLabel = new JLabel("Aucune donnée physique disponible.");
        physiquesLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(physiquesLabel, BorderLayout.CENTER);

        JPanel boutonsPanel = new JPanel(new FlowLayout());
        precedentPhysique = new JButton("<< Plus ancien");
        suivantPhysique = new JButton("Plus récent >>");
        precedentPhysique.addActionListener(e -> naviguerPhysique(-1));
        suivantPhysique.addActionListener(e -> naviguerPhysique(1));
        boutonsPanel.add(precedentPhysique);
        boutonsPanel.add(suivantPhysique);
        panel.add(boutonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel creerOngletMedicales() {
        JPanel panel = new JPanel(new BorderLayout());
        medicalesLabel = new JLabel("Aucune donnée médicale disponible.");
        medicalesLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(medicalesLabel, BorderLayout.CENTER);

        JPanel boutonsPanel = new JPanel(new FlowLayout());
        precedentMedical = new JButton("<< Plus ancien");
        suivantMedical = new JButton("Plus récent >>");
        precedentMedical.addActionListener(e -> naviguerMedical(-1));
        suivantMedical.addActionListener(e -> naviguerMedical(1));
        boutonsPanel.add(precedentMedical);
        boutonsPanel.add(suivantMedical);
        panel.add(boutonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel creerOngletPsychologiques() {
        JPanel panel = new JPanel(new BorderLayout());
        psychologiquesLabel = new JLabel("Aucune donnée psychologique disponible.");
        psychologiquesLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(psychologiquesLabel, BorderLayout.CENTER);

        JPanel boutonsPanel = new JPanel(new FlowLayout());
        precedentPsychologique = new JButton("<< Plus ancien");
        suivantPsychologique = new JButton("Plus récent >>");
        precedentPsychologique.addActionListener(e -> naviguerPsychologique(-1));
        suivantPsychologique.addActionListener(e -> naviguerPsychologique(1));
        boutonsPanel.add(precedentPsychologique);
        boutonsPanel.add(suivantPsychologique);
        panel.add(boutonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void chargerJoueurs() throws SQLException {
        joueursListModel.clear();
        joueurs.clear();

        // Récupérer tous les joueurs depuis la base de données
        joueurs = joueurModel.getAll();
       
        for(Joueur j : joueurs) {
        	System.out.println(j.nom);
        }
    }

    private void chargerDonneesJoueur(int index) throws SQLException {
        if (index < 0 || index >= joueurs.size()) return;

        Joueur joueur = joueurs.get(index);

        // Charger les données physiques, médicales et psychologiques du joueur
        donneesPhysiques = physiqueModel.getAllFromJoueur(joueur.id);
        donneesMedicales = medicalModel.getAllFromJoueur(joueur.id);
        donneesPsychologiques = psychologiqueModel.getAllFromJoueur(joueur.id);

        // Afficher les données les plus récentes
        indexPhysique = donneesPhysiques.size() - 1;
        indexMedical = donneesMedicales.size() - 1;
        indexPsychologique = donneesPsychologiques.size() - 1;

        afficherDonneePhysique();
        afficherDonneeMedicale();
        afficherDonneePsychologique();
    }

    private void afficherDonneePhysique() {
        if (indexPhysique >= 0 && indexPhysique < donneesPhysiques.size()) {
            DonneePhysique dp = donneesPhysiques.get(indexPhysique);
            physiquesLabel.setText("<html>Vitesse max: " + dp.vitessMax + "<br>Distance parcourue: " + dp.distanceParcourue + "<br>Nombre de sprints: " + dp.nbSprints + "<br>VO2max: " + dp.vo2Max + "<br>Accélération: " + dp.acceleration + "<br>Puissance: " + dp.puissance + "</html>");
        } else {
            physiquesLabel.setText("Aucune donnée physique disponible.");
        }
    }

    private void afficherDonneeMedicale() {
        if (indexMedical >= 0 && indexMedical < donneesMedicales.size()) {
            DonneeMedicale dm = donneesMedicales.get(indexMedical);
            medicalesLabel.setText("<html>Allergies: " + dm.allergies + "<br>Vaccins: " + dm.vaccins + "</html>");
        } else {
            medicalesLabel.setText("Aucune donnée médicale disponible.");
        }
    }

    private void afficherDonneePsychologique() {
        if (indexPsychologique >= 0 && indexPsychologique < donneesPsychologiques.size()) {
            DonneePsychologique dp = donneesPsychologiques.get(indexPsychologique);
            psychologiquesLabel.setText("<html>Profil psychologique: " + dp.profilPsychologique + "<br>Suivi mental: " + dp.suiviMental + "<br>Stratégies de préparation: " + dp.strategiePreparation + "<br>Historique des consultations: " /*+ dp.getHistoriqueConsultations()*/ + "</html>");
        } else {
            psychologiquesLabel.setText("Aucune donnée psychologique disponible.");
        }
    }

    private void naviguerPhysique(int delta) {
        indexPhysique += delta;
        if (indexPhysique < 0) indexPhysique = 0;
        if (indexPhysique >= donneesPhysiques.size()) indexPhysique = donneesPhysiques.size() - 1;
        afficherDonneePhysique();
    }

    private void naviguerMedical(int delta) {
        indexMedical += delta;
        if (indexMedical < 0) indexMedical = 0;
        if (indexMedical >= donneesMedicales.size()) indexMedical = donneesMedicales.size() - 1;
        afficherDonneeMedicale();
    }

    private void naviguerPsychologique(int delta) {
        indexPsychologique += delta;
        if (indexPsychologique < 0) indexPsychologique = 0;
        if (indexPsychologique >= donneesPsychologiques.size()) indexPsychologique = donneesPsychologiques.size() - 1;
        afficherDonneePsychologique();
    }
}