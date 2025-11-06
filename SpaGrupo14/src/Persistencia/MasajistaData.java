/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Cliente;
import Modelo.EspecialidadEnum;
import Modelo.Masajista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author abate
 */
public class MasajistaData {
    
    private Connection con = null;
    
    public MasajistaData(){
        con = Conexion.getConectar();
    }
    
    public void agregarMasajista(Masajista m){
        
        String sql = "INSERT INTO masajista(matricula, nombre_completo, telefono, especialidad, estado) VALUES (?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getMatricula());
            ps.setString(2, m.getNombreCompleto());
            ps.setString(3, m.getTelefono());
            ps.setString(4, m.getEspecialidad().name());
            ps.setBoolean(5, m.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                m.setCodMasajista(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Masajista ingresado con exito");
            }
        }catch(SQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null, "No se pudo agregar al Masajista");
            
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }    
    }
    
    public List<Masajista> listarMasajista(){
        Masajista m = null;
        List<Masajista> listaMasajistas = new ArrayList<>();
        String sql = "SELECT cod_masajista, matricula, nombre_completo, telefono, especialidad, estado from masajista WHERE estado = 1";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                m = new Masajista();
                m.setCodMasajista(rs.getInt("cod_masajista"));
                m.setMatricula(rs.getInt("matricula"));
                m.setNombreCompleto(rs.getString("nombre_completo"));
                m.setTelefono(rs.getString("telefono"));
                m.setEspecialidad(EspecialidadEnum.valueOf(rs.getString("especialidad")));
                m.setEstado(rs.getBoolean("estado"));
                
                listaMasajistas.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }   
        return listaMasajistas;
    }
    
    public Masajista buscarMasajista(int matricula){
        Masajista m = null;
        String sql = "SELECT * FROM masajista WHERE matricula = ?";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, matricula);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                m = new Masajista();
                ps.setInt(1, m.getCodMasajista());
                ps.setInt(2, m.getMatricula());
                ps.setString(3, m.getNombreCompleto());
                ps.setString(4, m.getTelefono());
                m.setEspecialidad(EspecialidadEnum.valueOf(rs.getString("especialidad")));
                ps.setBoolean(6, m.isEstado());
            }
        } catch (SQLException ex) {
            System.out.println("No existe ese nombre" + ex);
        }
        return m;
    }
    
    public void actualizarMasajista(Masajista m){
        
        String sql = "UPDATE masajista SET matricula=?, nombre_completo=?, telefono=?, especialidad=?, estado=? WHERE cod_masajista=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setInt(1, m.getMatricula());
                ps.setString(2, m.getNombreCompleto());
                ps.setString(3, m.getTelefono());
                ps.setString(4, m.getEspecialidad().name());
                ps.setBoolean(5, m.isEstado());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"Masajista actualizado con exito");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    public void eliminarMasajista(int matricula){
        
        String sql = "DELETE FROM masajista WHERE matricula=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matricula);
            ps.executeUpdate();
            ps.close();
             JOptionPane.showMessageDialog(null, "Masajista eliminado con exito");
        } catch (SQLException ex) {
            System.out.println("Error al borrar el masajista" + ex);
        }
    }
    
    public void altaLogica(Masajista m){
        
        String sql = "UPDATE masajista SET estado=1 WHERE matricula=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getMatricula());
            ps.executeUpdate();
            ps.close();
            System.out.println("Masajista dado de alta correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    public void bajaLogica(Masajista m){
        
        String sql = "UPDATE cliente SET estado=0 WHERE matricula=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getMatricula());
            ps.executeUpdate();
            ps.close();
            System.out.println("Masajista dado de baja correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
}
