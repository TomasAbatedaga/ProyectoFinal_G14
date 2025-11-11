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
            ps.setDouble(3, instalacion.getPrecio30M());
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
            ps.setDouble(3, instalacion.getPrecio30M());
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
       String sql = "UPDATE instalacion SET estado = 'Inactivo' WHERE cod_instalacion = ?";
        PreparedStatement ps = null;
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Instalación dada de baja lógicamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la Instalación para dar de baja.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Instalacion (Eliminar): " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {

            }
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
                instalacion.setPrecio30M(rs.getDouble("precio"));
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
}