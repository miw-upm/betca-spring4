package miw.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class HelloJDBC {
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
            sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS tabla1 (id1 INT PRIMARY KEY,nombre CHAR(20) DEFAULT '-')");
            Logger.getLogger(this.getClass().getName()).info("OK. Tabla creada");
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).info("Creación de tabla fallida: " + e.getMessage());
        }

        try {
            sentencia.executeUpdate("INSERT tabla1 (id1) VALUES (3)");
            sentencia.executeUpdate("INSERT tabla1 VALUES (4,'Jesús')");
            sentencia.executeUpdate("INSERT tabla1 VALUES (5,'Juan')");
            Logger.getLogger(this.getClass().getName()).info("OK. Datos introducidos en la tabla");
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).info("Insercción de datos de tabla fallida: " + e.getMessage());
        }

        try {
            result = sentencia.executeQuery("SELECT * FROM tabla1");
            while (result.next()) {
                Logger.getLogger(this.getClass().getName()).info("    id1: " + result.getLong("id1") + ", nombre: " + result.getString("nombre"));
            }
            Logger.getLogger(this.getClass().getName()).info("OK. Datos consultados en la tabla");
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).info("Consulta Fallida: " + e.getMessage());
        }

        try {
            sentencia.executeUpdate("DELETE FROM tabla1  WHERE id1=3");
            sentencia.executeUpdate("DELETE FROM tabla1  WHERE id1=4");
            sentencia.executeUpdate("DELETE FROM tabla1  WHERE id1=5");
            Logger.getLogger(this.getClass().getName()).info("OK. Datos borrados de la tabla");
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).info("Borrado de datos de tabla fallida: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new HelloJDBC().run();
    }
}
