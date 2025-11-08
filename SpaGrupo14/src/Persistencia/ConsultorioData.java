
package Persistencia;

import Modelo.Consultorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsultorioData {
    private Connection con = null;
    
    public ConsultorioData(){
        con = Conexion.getConectar();
    }
    public void agregarConsultorio(Consultorio consultorio){
        
        String sql = "INSERT INTO consultorio(nro_consultorio, uso ,equipamiento,estado) VALUES (?, ?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, consultorio.getNroConsultorio());
            ps.setString(2, consultorio.getUsos());
            ps.setString(3, consultorio.getEquipamiento());
            ps.setBoolean(4, consultorio.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                consultorio.setCodConsultorio(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Consultorio ingresado con exito");
            }
        }catch(SQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null, "no se pudo agregar al consultorio");
            
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }    
    }
    public List<Consultorio> listarConsultorios(){
        Consultorio consultorio = null;
        List<Consultorio> consultorios = new ArrayList<>();
        String sql = "SELECT cod_consultorio, nro_consultorio, uso, equipamiento, estado from consultorio WHERE estado = 1";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                consultorio = new Consultorio();
                consultorio.setCodConsultorio(rs.getInt("cod_consultorio"));
                consultorio.setNroConsultorio(rs.getInt("nro_consultorio"));
                consultorio.setUsos(rs.getString("uso"));
                consultorio.setEquipamiento(rs.getString("equipamiento"));
                consultorio.setEstado(rs.getBoolean("estado"));                
                consultorios.add(consultorio);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }   
        return consultorios;
    }
    
    public Consultorio buscarConsultorio(int codigoConsultorio){
        Consultorio consultorio = null;
        String sql = "SELECT * FROM consultorio WHERE cod_consultorio = ?";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoConsultorio);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                consultorio = new Consultorio();
                consultorio.setCodConsultorio(rs.getInt(1));
                consultorio.setNroConsultorio(rs.getInt(2));
                consultorio.setUsos(rs.getString(3));
                consultorio.setEquipamiento(rs.getString(4));
                consultorio.setEstado(rs.getBoolean(5));
            }
        } catch (SQLException ex) {
            System.out.println("No existe ese consultorio" + ex);
        }
        return consultorio;
    }
    
    
    public void actualizarConsultorio(Consultorio consultorio){
        
        String sql = "UPDATE consultorio SET  cod_consultorio=? , uso=?, equipamiento=?, estado=? WHERE nro_consultorio=? ";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, consultorio.getCodConsultorio());
                           
                ps.setString(2, consultorio.getUsos());
                ps.setString(3, consultorio.getEquipamiento());
                ps.setBoolean(4, consultorio.isEstado());
                ps.setInt(5, consultorio.getNroConsultorio());
                
            int filas = ps.executeUpdate();
            if (filas > 0)
                   JOptionPane.showMessageDialog(null,"Consultorio Actualizado con exito");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }    
    
    public void eliminarConsultorio(int codigoConsultorio){
        
        String sql = "DELETE FROM consultorio WHERE cod_consultorio=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigoConsultorio);
            ps.executeUpdate();
            ps.close();
             JOptionPane.showMessageDialog(null, "Consultorio eliminado con exito");
        } catch (SQLException ex) {
            System.out.println("Error al borrar Consultorio" + ex);
        }
    }
    
    public void altaLogica(Consultorio consultorio){
        
        String sql = "UPDATE consultorio SET estado=1 WHERE cod_consultorio=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, consultorio.getCodConsultorio());
            ps.executeUpdate();
            ps.close();
            System.out.println("Consultorio dado de alta correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    public void bajaLogica(Consultorio consultorio){
        
        String sql = "UPDATE consultorio SET estado=0 WHERE cod_consultorio=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, consultorio.getCodConsultorio());
            ps.executeUpdate();
            ps.close();
            System.out.println("Cliente dado de baja correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    
}
