import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données contenues dans la table article
 * @version 1.1
 * */
public class ArticleDAO {

	/**
	 * Paramètres de connexion à la base de données oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN="bdd1";
	final static String PASS="bdd1";


	/**
	 * Constructeur de la classe
	 * 
	 */
	public ArticleDAO()
	{
		// chargement du pilote de bases de données
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e2) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}
	

	/**
	 * Permet d'ajouter un article dans la table article
	 * la référence de l'article est produite automatiquement par la base de données en utilisant une séquence
	 * Le mode est auto-commit par défaut : chaque insertion est validée
	 * @param nouvArticle l'article à ajouter
	 * @return le nombre de ligne ajoutées dans la table
	 */
	public int ajouter(Article nouvArticle)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {

			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("INSERT INTO article (art_reference, art_designation, art_pu_ht, art_qte_stock) VALUES (article_seq.NEXTVAL, ?, ?, ?)");
			ps.setString(1,nouvArticle.getDesignation());
			ps.setDouble(2,nouvArticle.getPuHt());
			ps.setInt(3,nouvArticle.getQteStock());

			//Exécution de la requête
			retour=ps.executeUpdate();


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du preparedStatement et de la connexion
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;

	}

	/**
	 * Permet de récupérer un article à partir de sa référence
	 * @param reference la référence de l'article à récupérer
	 * @return l'article
	 * @return null si aucun article ne correspond à cette référence
	 */
	public Article getArticle(int reference)
	{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Article retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM article WHERE art_reference = ?");
			ps.setInt(1,reference);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			if(rs.next())
				retour=new Article(rs.getInt("art_reference"),rs.getString("art_designation"),rs.getDouble("art_pu_ht"),rs.getInt("art_qte_stock"));


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;

	}

	/**
	 * Permet de récupérer tous les articles stockés dans la table article
	 * @return une ArrayList d'Articles
	 */
	public List<Article> getListeArticles()
	{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Article> retour=new ArrayList<Article>();

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM article");

			//on exécute la requête
			rs=ps.executeQuery();
			//on parcourt les lignes du résultat
			while(rs.next())
				retour.add(new Article(rs.getInt("art_reference"),rs.getString("art_designation"),rs.getDouble("art_pu_ht"),rs.getInt("art_qte_stock")));


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du rs, du preparedStatement et de la connexion
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;

	}

	//main permettant de tester la classe
	public static void main(String[] args)  throws SQLException {

		 ArticleDAO articleDAO=new ArticleDAO();
		//test de la méthode ajouter
		Article a = new Article("Set de 2 raquettes de ping-pong",149.9,10);
		int retour=articleDAO.ajouter(a);

		System.out.println(retour+ " lignes ajoutées");

		//test de la méthode getArticle
		Article a2 = articleDAO.getArticle(1);
		System.out.println(a2);

		 //test de la méthode getListeArticles
		List<Article> liste=articleDAO.getListeArticles();
		//System.out.println(liste);
		for(Article art : liste)
		{
			System.out.println(art.toString());
		}

	}
}
