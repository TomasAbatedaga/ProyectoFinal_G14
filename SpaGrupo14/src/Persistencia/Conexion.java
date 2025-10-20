
package Persistencia;
import java.sql.*;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {
    
    private static final String URL="jdbc:mariadb://localhost/";
    private static final String DB="gp14_spa";
    private static final String USUARIO="root";
    private static final String CLAVE="";
    private static org.mariadb.jdbc.Connection conexion;

    public Conexion() {
    }
    
    public static org.mariadb.jdbc.Connection getConectar(){
        if(conexion == null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion=(org.mariadb.jdbc.Connection) DriverManager.getConnection(URL+DB,USUARIO,CLAVE);
               // JOptionPane.showMessageDialog(null, "Se conectó");
                
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Problemas accediendo a la clase "+e.getMessage());
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos "+ex.getMessage());
            }
        }
        return conexion;
    }
}
