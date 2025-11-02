/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Cliente;
import Modelo.EspecialidadEnum;
import Modelo.Tratamiento;
import Persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author facue
 */
public class TratamientoData {

    private Connection con = null;

    public TratamientoData() {
        con = Conexion.getConectar();
    }

    public void agregarTratamiento(Tratamiento t) {
        String sql = "INSERT INTO tratamiento(nombre, tipo, detalle, duracion, costo, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getTipo().name());
            ps.setString(3, t.getDetalle());
            ps.setInt(4, t.getDuracion());
            ps.setDouble(5, t.getCosto());
            ps.setBoolean(6, t.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setCodTratam(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "tratamiento ingresado con exito");
            }
            ps.close();
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "no se pudo agregar el tratamiento");
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }
    }

    public void actualizarTratamiento(Tratamiento t) {
        String sql = "UPDATE tratamiento SET nombre=?, tipo=?, detalle=?, duracion=?, costo=?, estado=? WHERE cod_tratamiento=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getTipo().name());
            ps.setString(3, t.getDetalle());
            ps.setInt(4, t.getDuracion());
            ps.setDouble(5, t.getCosto());
            ps.setBoolean(6, t.isEstado());
            ps.setInt(7, t.getCodTratam());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Tratamiento actualizado con éxito");
        } catch (SQLException ex) {
            System.out.println("Error de actualización: " + ex);
        }
    }

    public List<Tratamiento> listarTratamientos() {
        Tratamiento t = null;
        List<Tratamiento> tratamientos = new ArrayList<>();
        String sql = "SELECT cod_tratamiento, nombre, tipo, detalle, duracion, costo, estado FROM tratamiento WHERE estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t = new Tratamiento();
                t.setCodTratam(rs.getInt("cod_tratamiento"));
                t.setNombre(rs.getString("nombre"));
                t.setTipo(EspecialidadEnum.valueOf(rs.getString("tipo")));
                t.setDetalle(rs.getString("detalle"));
                t.setDuracion(rs.getInt("duracion"));
                t.setCosto(rs.getDouble("costo"));
                t.setEstado(rs.getBoolean("estado"));
                tratamientos.add(t);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }
        return tratamientos;
    }

    public Tratamiento buscarTratamiento(int codTratamiento) {
        Tratamiento t = null;
        String sql = "SELECT * FROM tratamiento WHERE cod_tratamiento = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codTratamiento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = new Tratamiento();
                t.setCodTratam(rs.getInt("cod_tratamiento"));
                t.setNombre(rs.getString("nombre"));
                t.setTipo(EspecialidadEnum.valueOf(rs.getString("tipo")));
                t.setDetalle(rs.getString("detalle"));
                t.setDuracion(rs.getInt("duracion"));
                t.setCosto(rs.getDouble("costo"));
                t.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("No existe ese código de tratamiento: " + ex);
        }
        return t;
    }

    public void eliminarTratamiento(int codigo) {
        String sql = "DELETE FROM tratamiento WHERE cod_tratamiento=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "tratamiento eliminado con exito");
        } catch (SQLException ex) {
            System.out.println("Error al borrar el tratamiento" + ex);
        }
    }

    public void altaLogica(Tratamiento t) {
        String sql = "UPDATE tratamiento SET estado=1 WHERE cod_tratamiento=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getCodTratam());
            ps.executeUpdate();
            ps.close();
            System.out.println("Tratamiento dado de alta correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }

    public void bajaLogica(Tratamiento t) {
        String sql = "UPDATE tratamiento SET estado=0 WHERE cod_tratamiento=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getCodTratam());
            ps.executeUpdate();
            ps.close();
            System.out.println("Tratamiento dado de baja correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
}
