/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
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
public class EspecialidadData {
    private Connection con = null;
    
    public EspecialidadData(){
        con = Conexion.getConectar();
    }
    
    public void agregarCliente(Especialidad e){
        
        String sql = "INSERT INTO especialidad(nombre) VALUES (?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getNombre());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                e.setCod_especialidad(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Especialidad ingresada con exito");
            }
        }catch(SQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null, "No se pudo agregar la Especialidad");
            
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }    
    }
    
    public List<Especialidad> listarEspecialidad(){
        Especialidad e = null;
        List<Especialidad> listaEspecialidad = new ArrayList<>();
        String sql = "SELECT * from especialidad";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                e = new Especialidad();
                e.setCod_especialidad(rs.getInt("cod_especialidad"));
                e.setNombre(rs.getString("nombre"));
                listaEspecialidad.add(e);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }   
        return listaEspecialidad;
    }
    
    public Especialidad buscarEspecialidad(int codEsp){
        Especialidad e = null;
        String sql = "SELECT * FROM especialidad WHERE cod_especialidad = ?";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, codEsp);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                e = new Especialidad();
                ps.setInt(1, e.getCod_especialidad());
                ps.setString(2, e.getNombre());
            }
        } catch (SQLException ex) {
            System.out.println("No existe ese codigo de especialidad" + ex);
        }
        return e;
    }
    
}
