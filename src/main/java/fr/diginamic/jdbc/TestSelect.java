package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {
	private static final String DB_URL;
    private static final String DB_LOGIN;
    private static final String DB_PASSWORD;
    
    static {
        ResourceBundle props = ResourceBundle.getBundle( "db" );
        DB_URL = props.getString( "db.url" );
        DB_LOGIN = props.getString( "db.login" );
        DB_PASSWORD = props.getString( "db.password" );
    }
    
    public static void main(String[] args) {
    	
	}
}
