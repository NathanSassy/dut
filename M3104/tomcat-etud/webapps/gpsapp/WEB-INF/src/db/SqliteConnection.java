/*
 * Développement d'Applicatons Web côté Serveur - M3104
 *
 * class SqliteConnection.java
 */

package db;

import java.sql.*;
import org.sqlite.JDBC;

/**
 * Cette classe (type singleton !!) établit la connexion entre une
 * application Java et une BDD mysql.
 */
public class SqliteConnection {


    private String dsn = "jdbc:sqlite:" + System.getProperty("user.dir") + "/webapps/gpsapp/db/gps_web_app.db";
    private java.sql.Connection connect;
    private static SqliteConnection theInst = null;

    /**
     * Constructeur privé !
     * Etablissement de la connexion.
     */
    private SqliteConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.getConnection();
    }

    /**
     * Méthode statique de récupération de l'instance unique.
     * @return - l'instance de SqliteConnection.
     */
    public static SqliteConnection getInstance() {
        if (SqliteConnection.theInst == null) {
            SqliteConnection.theInst = new SqliteConnection();
        }
        return SqliteConnection.theInst;
    }

    /**
     * Renvoie l'objet qui a établi la connexion avec la BDD.
     * @return - l'objet qui a établi la connexion.
     */
    public java.sql.Connection getConnection() {
        try {
            connect = DriverManager.getConnection(dsn);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connect;
    }

    /**
     * Ferme la connexion avec la BDD.
     */
    public void closeConnection() {
       try
		{
			if(connect != null) {
				connect.close();
			}
		}
		catch(SQLException err)
		{
			System.out.println(err.getMessage());
		}
    }


    /**
     * Lanceur qui teste la classe SqliteConnection
     */
    public static void main(String[] args) {
        SqliteConnection theObj = SqliteConnection.getInstance();
        theObj.closeConnection();
    }

}
