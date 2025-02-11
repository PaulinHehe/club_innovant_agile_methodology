
import java.sql.*;

import java.util.ArrayList;

import java.util.List;

/**
 * 
 * Classe d'accès aux données contenues dans la table article
 * 
 * @version 1.1
 * 
 */

public class ArticleDAO {

	/**
	 * 
	 * Paramètres de connexion à la base de données MySQL
	 * 
	 * URL, LOGIN et PASS sont des constantes
	 * 
	 */

	final static String URL = "jdbc:mysql://localhost:3306/stocks"; // Replace with your database name

	final static String LOGIN = "root"; // Replace with your MySQL username

	final static String PASS = ""; // Replace with your MySQL password

	/**
	 * 
	 * Constructeur de la classe
	 * 
	 */

	public ArticleDAO() {

// Chargement du pilote de bases de données

		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL JDBC driver

		} catch (ClassNotFoundException e2) {

			System.err.println(
					"Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");

		}

	}

	/**
	 * 
	 * Permet d'ajouter un article dans la table article
	 * 
	 * La référence de l'article est produite automatiquement par la base de données
	 * en utilisant une séquence
	 * 
	 * Le mode est auto-commit par défaut : chaque insertion est validée
	 * 
	 * @param nouvArticle l'article à ajouter
	 * 
	 * @return le nombre de lignes ajoutées dans la table
	 * 
	 */

	public int ajouter(Article nouvArticle) {

		Connection con = null;

		PreparedStatement ps = null;

		int retour = 0;

// Connexion à la base de données

		try {

// Tentative de connexion

			con = DriverManager.getConnection(URL, LOGIN, PASS);

// Préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion

// Les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle

			ps = con.prepareStatement(
					"INSERT INTO article (designation, pu_ht, qtestock) VALUES (?, ?, ?)");

			ps.setString(1, nouvArticle.getDesignation());

			ps.setDouble(2, nouvArticle.getPuHt());

			ps.setInt(3, nouvArticle.getQteStock());

// Exécution de la requête

			retour = ps.executeUpdate();

		} catch (Exception ee) {

			ee.printStackTrace();

		} finally {

// Fermeture du preparedStatement et de la connexion

			try {

				if (ps != null)
					ps.close();

			} catch (Exception t) {
			}

			try {

				if (con != null)
					con.close();

			} catch (Exception t) {
			}

		}

		return retour;

	}

	/**
	 * 
	 * Permet de récupérer un article à partir de sa référence
	 * 
	 * @param reference la référence de l'article à récupérer
	 * 
	 * @return l'article
	 * 
	 * @return null si aucun article ne correspond à cette référence
	 * 
	 */

	public Article getArticle(int reference) {

		Connection con = null;

		PreparedStatement ps = null;

		ResultSet rs = null;

		Article retour = null;

// Connexion à la base de données

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);

			ps = con.prepareStatement("SELECT * FROM article WHERE reference = ?");

			ps.setInt(1, reference);

// On exécute la requête

			rs = ps.executeQuery();

// Passe à la première (et unique) ligne retournée

			if (rs.next())

				retour = new Article(rs.getInt("reference"), rs.getString("designation"),
						rs.getDouble("pu_ht"), rs.getInt("qtestock"));

		} catch (Exception ee) {

			ee.printStackTrace();

		} finally {

// Fermeture du ResultSet, du PreparedStatement et de la Connection

			try {

				if (rs != null)
					rs.close();

			} catch (Exception t) {
			}

			try {

				if (ps != null)
					ps.close();

			} catch (Exception t) {
			}

			try {

				if (con != null)
					con.close();

			} catch (Exception t) {
			}

		}

		return retour;

	}

	/**
	 * 
	 * Permet de récupérer tous les articles stockés dans la table article
	 * 
	 * @return une ArrayList d'Articles
	 * 
	 */

	public List<Article> getListeArticles() {

		Connection con = null;

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<Article> retour = new ArrayList<Article>();

// Connexion à la base de données

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);

			ps = con.prepareStatement("SELECT * FROM article");

// On exécute la requête

			rs = ps.executeQuery();

// On parcourt les lignes du résultat

			while (rs.next())

				retour.add(new Article(rs.getInt("reference"), rs.getString("designation"),
						rs.getDouble("pu_ht"), rs.getInt("qtestock")));

		} catch (Exception ee) {

			ee.printStackTrace();

		} finally {

// Fermeture du rs, du preparedStatement et de la connexion

			try {

				if (rs != null)
					rs.close();

			} catch (Exception t) {
			}

			try {

				if (ps != null)
					ps.close();

			} catch (Exception t) {
			}

			try {

				if (con != null)
					con.close();

			} catch (Exception t) {
			}

		}

		return retour;

	}

// Main permettant de tester la classe

	public static void main(String[] args) throws SQLException {

		ArticleDAO articleDAO = new ArticleDAO();

// Test de la méthode ajouter

		Article a = new Article("Set de 2 raquettes de ping-pong", 149.9, 10);

		int retour = articleDAO.ajouter(a);

		System.out.println(retour + " lignes ajoutées");

// Test de la méthode getArticle

		Article a2 = articleDAO.getArticle(1);

		System.out.println(a2);

// Test de la méthode getListeArticles

		List<Article> liste = articleDAO.getListeArticles();

		for (Article art : liste) {

			System.out.println(art.toString());

		}

	}

}
