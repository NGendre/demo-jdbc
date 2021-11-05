package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao {

	private static final String DB_URL;
    private static final String DB_LOGIN;
    private static final String DB_PASSWORD;
    
    static {
        ResourceBundle props = ResourceBundle.getBundle( "db" );
        DB_URL = props.getString( "db.url" );
        DB_LOGIN = props.getString( "db.login" );
        DB_PASSWORD = props.getString( "db.password" );
    }
    
	
	@Override
	public List<Article> extraire() {
		ArrayList<Article> listeArticle = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PASSWORD ); Statement st = connection.createStatement() ) {
            connection.setAutoCommit( false );
            ResultSet rs = st.executeQuery( "SELECT * FROM article");
            connection.commit();
            while (rs.next()) {
            	PreparedStatement ps = connection.prepareStatement("SELECT * FROM fournisseur WHERE ID=?");
            	ps.setInt(1, rs.getInt("id"));
            	ResultSet rsFournisseur = ps.executeQuery();
            	Fournisseur f = new Fournisseur(rsFournisseur.getInt("ID"), rsFournisseur.getString("NOM"));
            	Article a = new Article(rs.getInt("ID"),rs.getString("REF"), rs.getString("DESIGNATION"), f);
            	listeArticle.add(a);
			}
            //System.out.println(listeArticle);
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
		return listeArticle;
	}

	@Override
	public void insert(Article article) {
		try (Connection connection = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PASSWORD ); Statement st = connection.createStatement() ) {
            connection.setAutoCommit( false );
            PreparedStatement ps = connection.prepareStatement("INSERT INTO article (REF,DESIGNATION,PRIX,ID_FOU) VALUES (?,?,?,?)");
            ps.setString(1, article.getRef());
            ps.setString(2, article.getDesignation());
            ps.setDouble(3, article.getPrix());
            ps.setInt(4, article.getFournisseur().getId());
            ps.executeUpdate();
            connection.commit();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Article article) {
		// TODO Auto-generated method stub
		return false;
	}

}
