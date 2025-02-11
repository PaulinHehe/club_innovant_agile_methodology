package club_interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class Main extends JFrame {
    
    public Main() {
        init_interface();  
    }
    
    JPanel _mainPanel;
    JPanel _sidebar;

    private void init_interface() {
    	this.setTitle("Dashboard");
		this.setSize(1600, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		_sidebar = new JPanel();
		_sidebar.setBackground(new Color(45, 55, 145));
        _sidebar.setPreferredSize(new Dimension(200, this.getHeight()));
		_sidebar.setLayout(new BoxLayout(_sidebar, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("CLUB INNOVANT");
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        _sidebar.add(title);

		_mainPanel.add(_sidebar);
		
		 String[] menuItems = {"Tableau de bord", "Joueurs", "Contrats", "Statistiques", "Médical", "Évaluations", "Paramètres"};
         for (String item : menuItems) {
             JButton button = new JButton(item);
             button.setAlignmentX(Component.CENTER_ALIGNMENT);
             _sidebar.add(button);
         }

         // Contenu principal
         JPanel mainPanel = new JPanel();
         mainPanel.setLayout(new BorderLayout());

         // Section Statistiques
         JPanel statsPanel = new JPanel(new GridLayout(1, 4, 1, 1));
         statsPanel.add(createStatPanel("Effectif Total", "32", Color.LIGHT_GRAY));
         statsPanel.add(createStatPanel("Joueurs Blessés", "4", Color.PINK));
         statsPanel.add(createStatPanel("Risque Élevé", "7", Color.YELLOW));
         statsPanel.add(createStatPanel("Contrats à Renouveler", "3", Color.GREEN));

         // Tableau Joueurs
         String[] columnNames = {"Joueur", "Poste", "Âge", "Statut", "Score de Risque", "Actions"};
         Object[][] data = {
             {"Kylian Mbappé", "Attaquant", 24, "Actif", "15%", "Éditer"},
             {"Antoine Griezmann", "Milieu Offensif", 32, "Repos", "45%", "Éditer"},
             {"Paul Pogba", "Milieu", 30, "Blessé", "75%", "Éditer"}
         };
         JTable table = new JTable(new DefaultTableModel(data, columnNames));
         JScrollPane tableScroll = new JScrollPane(table);

         // Ajouter composants au panel principal
         _mainPanel.add(statsPanel, BorderLayout.NORTH);
         _mainPanel.add(tableScroll, BorderLayout.CENTER);

         // Ajouter au frame
         this.add(_sidebar, BorderLayout.WEST);
         this.add(_mainPanel, BorderLayout.CENTER);
    }
    
    private static JPanel createStatPanel(String title, String value, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setSize(new Dimension(10, 10));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);
        return panel;
    }
    
    public static void main(String[] args)
	{
		new Main();
    }
}
