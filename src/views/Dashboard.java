package views;

import views.JoueurView;

import javax.swing.JPanel;

import views.ContratView;
import views.PerformanceView;
import views.UtilisateurView;

public class Dashboard extends javax.swing.JFrame {

   // private JPanel dashboard; // Panel principal
    private JPanel JoueurView; // Panel pour les joueurs
    private JPanel ContratView; // Panel pour les contrats
    private JPanel PerformanceView; // Panel pour les performances
    private JPanel UtilisateurView; // Panel pour les utilisateurs

    public Dashboard() {
        initComponents();
        initPanels(); // Initialiser les panneaux
    }

    private void initPanels() {
        // Créer les panneaux
        JoueurView = new JoueurView();
        ContratView = new ContratView();
        PerformanceView = new PerformanceView();
        UtilisateurView = new UtilisateurView();

        // Ajouter les panneaux au dashboard
        dashboard.add(JoueurView, "joueur");
        dashboard.add(ContratView, "contrat");
        dashboard.add(PerformanceView, "performance");
        dashboard.add(UtilisateurView, "utilisateur");

        // Masquer tous les panneaux au démarrage
        showPanel("dashboard"); // Afficher le tableau de bord par défaut
    }

    private void showPanel(String panelName) {
        // Masquer tous les panneaux
        JoueurView.setVisible(false);
        ContratView.setVisible(false);
        PerformanceView.setVisible(false);
        UtilisateurView.setVisible(false);

        // Afficher le panneau demandé
        switch (panelName) {
            case "joueur":
                JoueurView.setVisible(true);
                break;
            case "contrat":
                ContratView.setVisible(true);
                break;
            case "performance":
                PerformanceView.setVisible(true);
                break;
            case "utilisateur":
                UtilisateurView.setVisible(true);
                break;
            default:
                // Afficher le tableau de bord par défaut
                break;
        }
    }

    private void initComponents() {
        sidebar = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        dashboard = new javax.swing.JPanel(); // Panel principal

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        sidebar.setBackground(new java.awt.Color(45, 55, 145));

        jButton6.setText("Évaluations");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setText("Utilisateurs");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Statistiques");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Club innovant");

        jButton2.setText("Tableau de bord");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Joueurs");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Contrats");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebarLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(sidebarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(61, 61, 61)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(422, Short.MAX_VALUE))
        );

        dashboard.setLayout(new java.awt.CardLayout()); // Utiliser CardLayout pour gérer les panneaux

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        showPanel("joueur"); // Afficher le panneau des joueurs
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        showPanel("contrat"); // Afficher le panneau des contrats
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        showPanel("performance"); // Afficher le panneau des performances
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        showPanel("utilisateur"); // Afficher le panneau des utilisateurs
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // (À implémenter)
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        showPanel("dashboard"); // Revenir au tableau de bord
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JPanel dashboard;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel sidebar;
}