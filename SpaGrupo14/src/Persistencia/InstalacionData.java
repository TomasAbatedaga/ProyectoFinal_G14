/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Instalacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;


/**
 *
 * @author Dani45
 */
public class InstalacionData {
    private Connection con = null;
    
    public InstalacionData(){
        con = Conexion.getConectar();
    }
    public void guardarIntalacion(Instalacion instalacion){
        String sql = "INSERT INTO instalacion (nombre, detalle_uso, precio, estado) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        
try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, instalacion.getNombre());
            ps.setString(2, instalacion.getDetalleUso());
            ps.setDouble(3, instalacion.getPrecio());
            ps.setBoolean(4, instalacion.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                instalacion.setCodInstal(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Instalacion agregada con exito. Codigo: " + instalacion.getCodInstal());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Instalacion (Guardar): " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
            }
        }
    }
    public void modificarInstalacion(Instalacion instalacion){
        String sql = "UPDATE instalacion SET nombre = ?, detalle_uso = ?, precio = ?, estado = ? WHERE cod_instalacion = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, instalacion.getNombre());
            ps.setString(2, instalacion.getDetalleUso());
            ps.setDouble(3, instalacion.getPrecio());
            ps.setBoolean(4, instalacion.isEstado());
            ps.setInt(5, instalacion.getCodInstal());
            
            int fila = ps.executeUpdate();

            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Instalacion actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la Instalación para actualizar.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Instalacion (Modificar): " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {

            }
        }
    }
    public void eliminarInstalacion(int id){
      String sql = "DELETE FROM instalacion WHERE cod_instalacion= ? " ;
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Instalacion eliminada con exito");
       } catch (SQLException ex) {
            System.out.println("Error al borrar la Instalacion" + ex);
        }
        
    }
    
    public List<Instalacion> listarInstalaciones(){
        ArrayList<Instalacion> lista = new ArrayList<>();
        String sql = "SELECT cod_instalacion, nombre, detalle_uso, precio, estado FROM instalacion WHERE estado = 1"; 
        
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Instalacion instalacion = new Instalacion();
                instalacion.setCodInstal(rs.getInt("cod_instalacion"));
                instalacion.setNombre(rs.getString("nombre"));
                instalacion.setDetalleUso(rs.getString("detalle_uso"));
                instalacion.setPrecio(rs.getDouble("precio"));
                instalacion.setEstado(rs.getBoolean("estado"));
                lista.add(instalacion);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Instalacion (Listar): " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {

            }
        }
        return lista;
    }

    public Instalacion buscarInstalacion(int cod_Instalacion) {
        Instalacion i = null;
        String sql = "SELECT * FROM instalacion WHERE cod_instalacion = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cod_Instalacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                i = new Instalacion();
                i.setCodInstal(rs.getInt("cod_instalacion"));
                i.setNombre(rs.getString("nombre"));
                i.setDetalleUso(rs.getString("detalle_uso"));
                i.setPrecio(rs.getDouble("precio"));
                i.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("No existe ese código de Instalaciones: " + ex);
        }
        return i;
    }
    
   public List<Instalacion> obtenerInstalacionesMasUsadas(String nombreSeleccionado) {
    List<Instalacion> lista = new ArrayList<>();
    String sql = """
        SELECT i.cod_instalacion, i.nombre, i.detalle_uso, i.precio,
                       COUNT(s.cod_sesion) AS cont
                FROM instalacion i
                JOIN sesion s ON i.cod_instalacion = s.cod_instalacion
                WHERE i.nombre = ? AND s.estado = 1
                GROUP BY i.cod_instalacion
                ORDER BY cont DESC
    """;

    try (PreparedStatement ps = con.prepareStatement(sql)) {
         ps.setString(1, nombreSeleccionado);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Instalacion i = new Instalacion();
            i.setCodInstal(rs.getInt("cod_instalacion"));
            i.setNombre(rs.getString("nombre"));
            i.setDetalleUso(rs.getString("detalle_uso"));
            i.setPrecio(rs.getDouble("precio"));
            //arranca el contador
            i.setCont(rs.getInt("cont"));
            
            lista.add(i);
        }
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, "Error, no se pueden ver las instalaciones mas usadas: " + ex.getMessage());
    }

    return lista;
}

}