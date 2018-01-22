package miw.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Transaction {
    public void run() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet result = null;
        String url = "jdbc:mysql://localhost:3306/spring";
        String user = "root";
        String pass = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, pass);
            sentencia = conexion.createStatement();
            Logger.getLogger(this.getClass().getName()).info("OK. Driver cargado");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(this.getClass().getName()).info("Imposible cargar el driver: " + e.getMessage());
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).info("Imposible conectar: " + e.getMessage());
        }

        try {
            Logger.getLogger(this.getClass().getName()).info("Transaction...");
            // begin
            conexion.setAutoCommit(false);

            // sentencias SQL
            sentencia = conexion.createStatement();
            sentencia.executeUpdate("INSERT tabla1 VALUES (8,'Trans1')");
            sentencia.executeUpdate("INSERT tabla1 VALUES (8,'Trans2')");

            // Si se llega a este punto, todo ha ido bien
            conexion.commit();
        } catch (SQLException e) {
            try {
                // Hay problemas, se deshace todo
                conexion.rollback();
                Logger.getLogger("OK. Deshaciendo por rollback... " + e.getMessage());
            } catch (SQLException e1) {
                Logger.getLogger("ERROR (rollback): " + e1.getMessage());
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                Logger.getLogger("ERROR (commit): " + e);
            }
        }
        try {
            result = sentencia.executeQuery("SELECT * FROM tabla1");
            while (result.next())
                Logger.getLogger("id1: " + result.getLong("id1") + ", nombre: " + result.getString("nombre"));
        } catch (SQLException e) {
            Logger.getLogger("Consulta Fallida: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Transaction().run();
    }
}
