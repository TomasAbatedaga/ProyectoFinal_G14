
package Persistencia;
import java.sql.*;

public class Conexion {
    private static final String URL = "jdbc:mariaDB://127.0.0.1";
    private static final String DB = "gp14_spa";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection connection;
    
    private Conexion(){
    }
    
    public static Connection getConexion(){
          if(connection == null){
              try{
          Class.forName("org.mariaDB.jdbc.Driver");
          connection = DriverManager.getConnection(URL+DB, USER, PASS);
                  System.out.println("conexion establecida");
              } catch (ClassNotFoundException ex) {
                  System.out.println("Driver no encontrado");
              } catch (SQLException ex) {
                  System.out.println("Error de conexion con la base de datos");
              }
              }
        
        return connection;
    }
}
