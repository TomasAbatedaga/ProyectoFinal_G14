/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.Date;
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
 * @author facue
 */
public class ClienteData {
    private Connection con = null;
    
    public ClienteData(){
        con = Conexion.getConectar();
    }
    public void agregarCliente(Cliente c){
        
        String sql = "INSERT INTO cliente(dni, nombre_completo, telefono, edad, afecciones, estado) VALUES (?, ?, ?, ?, ?,?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombreCompleto());
            ps.setString(3, c.getTelefono());
            ps.setInt(4, c.getEdad());
            ps.setString(5, c.getAfecciones());
            ps.setBoolean(6, c.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                c.setCodCli(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Cliente ingresado con exito");
            }
        }catch(SQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null, "no se pudo agregar al cliente");
            
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }    
    }
    public List<Cliente> listarCliente(){
        Cliente c = null;
        List<Cliente> clientela = new ArrayList<>();
        String sql = "SELECT cod_cliente, dni, nombre_completo, telefono, edad, afecciones, estado from Cliente WHERE estado = 1";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                c = new Cliente();
                c.setCodCli(rs.getInt("cod_cliente"));
                c.setDni(rs.getInt("dni"));
                c.setNombreCompleto(rs.getString("nombre_completo"));
                c.setTelefono(rs.getString("telefono"));
                c.setEdad(rs.getInt("edad"));
                c.setAfecciones(rs.getString("afecciones"));
                c.setEstado(rs.getBoolean("estado"));
                
                clientela.add(c);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }   
        return clientela;
    }
    
    public Cliente buscarCliente(int dni){
        Cliente c = null;
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                c = new Cliente();
                ps.setInt(1, c.getCodCli());
                ps.setInt(2, c.getDni());
                ps.setString(3, c.getNombreCompleto());
                ps.setString(4, c.getTelefono());
                ps.setInt(5, c.getEdad());
                ps.setString(6, c.getAfecciones());
                ps.setBoolean(7, c.isEstado());
            }
        } catch (SQLException ex) {
            System.out.println("No existe ese dni" + ex);
        }
        return c;
    }
    
    
    public void actualizarCliente(Cliente c){
        
        String sql = "UPDATE cliente SET nombre_completo=?, telefono=?, edad=?, afecciones=?, estado=? WHERE dni=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setString(1, c.getNombreCompleto());
                ps.setString(2, c.getTelefono());
                ps.setInt(3, c.getEdad());
                ps.setString(4, c.getAfecciones());
                ps.setBoolean(5, c.isEstado());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"Cliente Actualizado con exito");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    public void eliminarCliente(int dni){
        
        String sql = "DELETE FROM cliente WHERE dni=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ps.executeUpdate();
            ps.close();
             JOptionPane.showMessageDialog(null, "Cliente eliminado con exito");
        } catch (SQLException ex) {
            System.out.println("Error al borrar Cliente" + ex);
        }
    }
    
    public void altaLogica(Cliente c){
        
        String sql = "UPDATE cliente SET estado=1 WHERE dni=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getDni());
            ps.executeUpdate();
            ps.close();
            System.out.println("Cliente dado de alta correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    public void bajaLogica(Cliente c){
        
        String sql = "UPDATE cliente SET estado=0 WHERE dni=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getDni());
            ps.executeUpdate();
            ps.close();
            System.out.println("Cliente dado de baja correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    
}
