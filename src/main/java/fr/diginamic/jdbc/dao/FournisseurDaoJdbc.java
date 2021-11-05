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

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao{
	
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
	public List<Fournisseur> extraire() {
		ArrayList<Fournisseur> listeFournisseur = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PASSWORD ); Statement st = connection.createStatement() ) {
            connection.setAutoCommit( false );
            ResultSet rs = st.executeQuery( "SELECT * FROM fournisseur");
            connection.commit();
            while (rs.next()) {
            	Fournisseur f = new Fournisseur(rs.getInt("ID"), rs.getString("NOM"));
            	listeFournisseur.add(f);
			}
            System.out.println(listeFournisseur);
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
		return listeFournisseur;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		try (Connection connection = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PASSWORD ); Statement st = connection.createStatement() ) {
            connection.setAutoCommit( false );
            PreparedStatement ps = connection.prepareStatement("INSERT INTO fournisseur (NOM) VALUES (?)");
            ps.setString(1, fournisseur.getNom());
            ps.executeUpdate();
            connection.commit();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
		
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		int ret = 0;
		try (Connection connection = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PASSWORD ); Statement st = connection.createStatement() ) {
            connection.setAutoCommit( false );
            PreparedStatement ps = connection.prepareStatement("UPDATE fournisseur SET NOM=? WHERE NOM=?");
            ps.setString(1, nouveauNom);
            ps.setString(2, ancienNom);
            ret = ps.executeUpdate();
            connection.commit();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
		return ret;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		try (Connection connection = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PASSWORD ); Statement st = connection.createStatement() ) {
            connection.setAutoCommit( false );
            PreparedStatement ps = connection.prepareStatement("DELETE FROM fournisseur WHERE NOM=?");
            ps.setString(1, fournisseur.getNom());
            ps.executeUpdate();
            connection.commit();
            return true;
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
		return false;
	}

}
