/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import Modelo.Sesion;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author abate
 */
public class SesionData {
    private Connection con = null;
    
    public SesionData(){
        con = Conexion.getConectar();
    }
    
    public void agregarSesion(Sesion s){
        
        String sql = "INSERT INTO sesion(fecha_hora_inicio, fecha_hora_fin, cod_tratamiento, cod_consultorio, cod_masajista, cod_pack, cod_instalacion,estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTime(1, Time.valueOf(s.getFechaHoraInicio().toLocalTime()));
            ps.setTime(2, Time.valueOf(s.getFechaHoraFin().toLocalTime()));
            ps.setInt(3, s.getTratamiento().getCodTratam());
            ps.setInt(4, s.getConsultorio().getCodConsultorio());
            ps.setInt(5, s.getMasajista().getCod_Masajista());
            ps.setInt(6, s.getDiaDeSpa().getCodPack());
            ps.setInt(7, s.getInstalaciones().getCodInstal());
            ps.setBoolean(8, s.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                s.setCodSesion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Sesion ingresada con exito");
            }
        }catch(SQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null, "No se pudo agregar la sesion");
            
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }    
    }
    
    public List<Sesion> listarSesion(){
        Sesion s = null;
        List<Sesion> sesiones = new ArrayList<>();
        String sql = "SELECT * from Sesion s"
                + "JOIN tratamiento t ON s.cod_tratamiento = t.cod_tratamiento"
                + "JOIN consultorio c ON s.cod_consultorio = c.cod_consultorio"
                + "JOIN masajista m ON s.cod_masajista = m.cod_masajista"
                + "JOIN dia_de_spa ds ON s.cod_pack = ds.cod_pack WHERE estado = 1";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                s = new Sesion();
                s.setCodSesion(rs.getInt("cod_sesion"));
                s.setFechaHoraInicio(rs.getTime("fecha_hora_inicio"));
                s.setFechaHoraFin(rs.getTime("fecha_hora_fin"));
                s.getTratamiento().setCodTratam(rs.getInt("cod_tratamiento"));
                s.getConsultorio().setCodConsultorio(rs.getInt("cod_consultorio"));
                s.getMasajista().setCod_Masajista(rs.getInt("cod_masajista"));
                s.getDiaDeSpa().setCodPack(rs.getInt("cod_pack"));
                s.getInstalaciones().setCodInstal(rs.getInt("cod_instalacion"));
                s.setEstado(rs.getBoolean("estado"));
                
                sesiones.add(s);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }   
        return sesiones;
    }
    public List<Sesion> listarSesionesPorCodPack(int codPack) {
        List<Sesion> lista = new ArrayList<>();

        String sql = "SELECT s.cod_sesion, s.fecha_hora_inicio, s.fecha_hora_fin, "
                + "t.cod_tratamiento, t.nombre AS tratamientoNombre, "
                + "m.cod_masajista, m.nombre_completo AS masajistaNombre, "
                + "c.cod_consultorio, c.nro_consultorio AS consultorioNumero, "
                + "i.cod_instalacion, i.nombre AS instalacionNombre "
                + "FROM sesion s "
                + "LEFT JOIN tratamiento t ON s.cod_tratamiento = t.cod_tratamiento "
                + "LEFT JOIN masajista m ON s.cod_masajista = m.cod_masajista "
                + "LEFT JOIN consultorio c ON s.cod_consultorio = c.cod_consultorio "
                + "LEFT JOIN instalacion i ON s.cod_instalacion = i.cod_instalacion "
                + "WHERE s.cod_pack = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codPack);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Sesion s = new Sesion();
                s.setCodSesion(rs.getInt("cod_sesion"));

                // === HORARIOS (DATETIME -> Time) ===
                java.sql.Timestamp ini = rs.getTimestamp("fecha_hora_inicio");
                java.sql.Timestamp fin = rs.getTimestamp("fecha_hora_fin");

                if (ini != null) {
                    s.setFechaHoraInicio(Time.valueOf(ini.toLocalDateTime().toLocalTime()));
                }
                if (fin != null) {
                    s.setFechaHoraFin(Time.valueOf(fin.toLocalDateTime().toLocalTime()));
                }

              
                Tratamiento t = new Tratamiento();
                t.setCodTratam(rs.getInt("cod_tratamiento"));
                t.setNombre(rs.getString("tratamientoNombre"));
                s.setTratamiento(t);

               
                Masajista m = new Masajista();
                m.setCod_Masajista(rs.getInt("cod_masajista"));
                m.setNombreCompleto(rs.getString("masajistaNombre"));
                s.setMasajista(m);

           
                Consultorio c = new Consultorio();
                c.setCodConsultorio(rs.getInt("cod_consultorio"));
                c.setNroConsultorio(rs.getInt("consultorioNumero"));
                s.setConsultorio(c);

           
                Instalacion inst = new Instalacion();
                inst.setCodInstal(rs.getInt("cod_instalacion"));
                inst.setNombre(rs.getString("instalacionNombre"));
                s.setInstalaciones(inst);

                lista.add(s);
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error listar sesiones por Dia Spa: " + ex);
        }

        return lista;
    }
    
    public Sesion buscarSesiones(int codSesion){
        Sesion s = null;
        String sql = "SELECT * FROM sesion WHERE cod_sesion = ?";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, codSesion);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                s = new Sesion();
                s.setCodSesion(rs.getInt("cod_sesion"));
                s.setFechaHoraInicio(rs.getTime("fecha_hora_inicio"));
                s.setFechaHoraFin(rs.getTime("fecha_hora_fin"));
                Tratamiento t = new Tratamiento();
                t.setCodTratam(rs.getInt("cod_tratamiento"));
                s.setTratamiento(t);
                Consultorio c = new Consultorio();
                c.setCodConsultorio(rs.getInt("cod_consultorio"));
                s.setConsultorio(c);
                Masajista m = new Masajista();
                m.setCod_Masajista(rs.getInt("cod_masajista"));
                s.setMasajista(m);
                Dia_de_Spa ds = new Dia_de_Spa();
                ds.setCodPack(rs.getInt("cod_pack"));
                s.setDiaDeSpa(ds);
                Instalacion i = new Instalacion();
                i.setCodInstal(rs.getInt("cod_instalacion"));
                s.setInstalaciones(i);
                s.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("No existe ese codigo de sesion" + ex);
        }
        return s;
    }
    
    public void actualizarSesion(Sesion s){
        
        String sql = "UPDATE sesion SET fecha_hora_inicio=?, fecha_hora_fin=?, cod_tratamiento=?"
               + ", cod_consultorio=?, cod_masajista=?, cod_pack=?, cod_instalacion=?, estado=? WHERE cod_sesion=?";
        
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setTime(1, Time.valueOf(s.getFechaHoraInicio().toLocalTime()));
            ps.setTime(2, Time.valueOf(s.getFechaHoraFin().toLocalTime()));
            ps.setInt(3, s.getTratamiento().getCodTratam());
            ps.setInt(4, s.getConsultorio().getCodConsultorio());
            ps.setInt(5, s.getMasajista().getCod_Masajista());
            ps.setInt(6, s.getDiaDeSpa().getCodPack());
            ps.setInt(7, s.getInstalaciones().getCodInstal());
            ps.setBoolean(8, s.isEstado());
            ps.setInt(9, s.getCodSesion());
            
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Sesion Actualizado con exito");
            }
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    public void eliminarSesion(int codSesion){
        
        String sql = "DELETE FROM sesion WHERE cod_sesion=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codSesion);
            int filaSeleccionada = ps.executeUpdate();
            if (filaSeleccionada > 0) {
                JOptionPane.showMessageDialog(null, "Sesion eliminada con exito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro ninguna sesion con ese codigo");
            }
            ps.close();
             
        } catch (SQLException ex) {
            System.out.println("Error al borrar la Sesion" + ex);
        }
    }
    
    public void altaLogica(Sesion s){
        
        String sql = "UPDATE sesion SET estado=1 WHERE cod_sesion=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, s.getCodSesion());
            ps.executeUpdate();
            ps.close();
            System.out.println("Sesion dada de alta correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    public void bajaLogica(Sesion s){
        
        String sql = "UPDATE sesion SET estado=0 WHERE cod_sesion=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, s.getCodSesion());
            ps.executeUpdate();
            ps.close();
            System.out.println("Sesion dada de baja correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
    }
    
    public boolean masajistaOcupado(int idMasajista, LocalDate fecha, Time horaInicioNueva, Time horaFinNueva) {
        String sql = "SELECT COUNT(*) FROM sesion s " +
                     "JOIN dia_de_spa ds ON s.cod_pack = ds.cod_pack " +
                     "WHERE s.cod_masajista = ? " +      // 1. El masajista
                     "AND ds.fecha_hora = ? " +         // 2. La fecha (del Dia de Spa)
                     "AND (s.fecha_hora_inicio < ? AND s.fecha_hora_fin > ?)"; // 3. La logica de superposición de tiempo

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMasajista);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
            ps.setTime(3, horaFinNueva);
            ps.setTime(4, horaInicioNueva);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es > 0, significa que esta ocupado
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al verificar disponibilidad de masajista: " + ex.getMessage());
        }
        return true; // si hay un error retorno ocupado
    }


    public boolean consultorioOcupado(int idConsultorio, LocalDate fecha, Time horaInicioNueva, Time horaFinNueva) {
        String sql = "SELECT COUNT(*) FROM sesion s " +
                     "JOIN dia_de_spa ds ON s.cod_pack = ds.cod_pack " +
                     "WHERE s.cod_consultorio = ? " +
                     "AND ds.fecha_hora = ? " +
                     "AND (s.fecha_hora_inicio < ? AND s.fecha_hora_fin > ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idConsultorio);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
            ps.setTime(3, horaFinNueva);
            ps.setTime(4, horaInicioNueva);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // true si esta ocupado
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al verificar disponibilidad de consultorio: " + ex.getMessage());
        }
        return true; // si hay algun error retorna ocupado
    }
    
    public boolean instalacionOcupada(int idInstalacion, LocalDate fecha, Time horaInicioNueva, Time horaFinNueva) {
        String sql = "SELECT COUNT(*) FROM sesion s " +
                     "JOIN dia_de_spa ds ON s.cod_pack = ds.cod_pack " +
                     "WHERE s.cod_instalacion = ? " + 
                     "AND ds.fecha_hora = ? " +
                     "AND (s.fecha_hora_inicio < ? AND s.fecha_hora_fin > ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idInstalacion);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
            ps.setTime(3, horaFinNueva);
            ps.setTime(4, horaInicioNueva);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al verificar disponibilidad de instalación: " + ex.getMessage());
        }
        return true;
    }
    
    public boolean masajistaOcupado(int idMasajista, LocalDate fecha, Time horaInicioNueva, Time horaFinNueva, int idSesionAExcluir) {
        String sql = "SELECT COUNT(*) FROM sesion s " +
                     "JOIN dia_de_spa ds ON s.cod_pack = ds.cod_pack " +
                     "WHERE s.cod_masajista = ? AND ds.fecha_hora = ? " +
                     "AND (s.fecha_hora_inicio < ? AND s.fecha_hora_fin > ?) " +
                     "AND s.cod_sesion != ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMasajista);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
            ps.setTime(3, horaFinNueva);
            ps.setTime(4, horaInicioNueva);
            ps.setInt(5, idSesionAExcluir); // <-- El ID a ignorar

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            ps.close();
        } catch (SQLException ex) { System.out.println("Error al verificar disponibilidad: " + ex.getMessage()); }
        return true; 
    }

    public boolean consultorioOcupado(int idConsultorio, LocalDate fecha, Time horaInicioNueva, Time horaFinNueva, int idSesionAExcluir) {
        String sql = "SELECT COUNT(*) FROM sesion s " +
                     "JOIN dia_de_spa ds ON s.cod_pack = ds.cod_pack " +
                     "WHERE s.cod_consultorio = ? AND ds.fecha_hora = ? " +
                     "AND (s.fecha_hora_inicio < ? AND s.fecha_hora_fin > ?) " +
                     "AND s.cod_sesion != ?"; // <-- Excluir esta sesión
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idConsultorio);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
            ps.setTime(3, horaFinNueva);
            ps.setTime(4, horaInicioNueva);
            ps.setInt(5, idSesionAExcluir); // <-- El ID a ignorar

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            ps.close();
        } catch (SQLException ex) { System.out.println("Error al verificar disponibilidad: " + ex.getMessage()); }
        return true; 
    }

    public boolean instalacionOcupada(int idInstalacion, LocalDate fecha, Time horaInicioNueva, Time horaFinNueva, int idSesionAExcluir) {
        String sql = "SELECT COUNT(*) FROM sesion s " +
                     "JOIN dia_de_spa ds ON s.cod_pack = ds.cod_pack " +
                     "WHERE s.cod_instalacion = ? AND ds.fecha_hora = ? " +
                     "AND (s.fecha_hora_inicio < ? AND s.fecha_hora_fin > ?) " +
                     "AND s.cod_sesion != ?"; // <-- Excluir esta sesión
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idInstalacion);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
            ps.setTime(3, horaFinNueva);
            ps.setTime(4, horaInicioNueva);
            ps.setInt(5, idSesionAExcluir); // <-- El ID a ignorar

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            ps.close();
        } catch (SQLException ex) { System.out.println("Error al verificar disponibilidad: " + ex.getMessage()); }
        return true; 
    }
}
