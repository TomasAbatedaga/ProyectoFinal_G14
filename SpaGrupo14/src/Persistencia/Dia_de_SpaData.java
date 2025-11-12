package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Cliente;
import Modelo.Dia_de_Spa;
import Modelo.Sesion;
import Persistencia.Conexion;
import Persistencia.ClienteData;


public class Dia_de_SpaData {

    private Connection con = null;

    public Dia_de_SpaData() {

        con = Conexion.getConectar();
    }

    public void agregarDiaSpa(Dia_de_Spa ds) {

        String sql = "INSERT into dia_de_spa (fecha_hora, preferencias, cod_cliente, estado, monto) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(ds.getFechaYHora()));
            ps.setString(2, ds.getPreferencias());
            ps.setInt(3, ds.getCliente().getCod_cliente());
            ps.setBoolean(4, ds.getEstado());
            ps.setDouble(5, ds.getMonto());

            int registros = ps.executeUpdate();
            System.out.println(registros);
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

                ds.setCodPack(rs.getInt(1));
                System.out.println("El Dia de spa fue agregado");

            } else {
                System.out.println("Error al cargar este dia de spa");
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }
    }
    
    public List<Dia_de_Spa> listarDia_de_Spa(){
        Dia_de_Spa ds = null;
        List<Dia_de_Spa> DiasDeSpa = new ArrayList<>();
        String sql = "SELECT * from dia_de_spa WHERE estado = 1";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ds = new Dia_de_Spa();
                ds.setCodPack(rs.getInt("cod_pack"));
                ds.setFechaYHora(rs.getDate("fecha_hora").toLocalDate());
                ds.setPreferencias(rs.getString("preferencias"));
                Cliente cliente = new Cliente();
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                ds.setCliente(cliente);
                //ds.getCliente().setCod_cliente(rs.getInt("cod_cliente"));
                ds.setMonto(rs.getDouble("monto"));
                ds.setEstado(rs.getBoolean("estado"));
                
                DiasDeSpa.add(ds);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }   
        return DiasDeSpa;
    }

    public List<Dia_de_Spa> listarDiaSpa(String dniCliente, java.time.LocalDate fechaInicio, java.time.LocalDate fechaFin) {
    List<Dia_de_Spa> listadoDiaDeSpa = new ArrayList<>();
    
    String sql = "SELECT ds.*, c.dni, c.nombre, c.apellido "
               + "FROM dia_de_spa ds JOIN cliente c ON ds.cod_cliente = c.cod_cliente "
               + "WHERE 1=1 ";
    
    List<Object> parametros = new ArrayList<>();
    
    if (dniCliente != null && !dniCliente.isEmpty()) {
        sql += "AND c.dni = ? "; 
        parametros.add(dniCliente);
    }
    
    if (fechaInicio != null) {
        sql += "AND ds.fecha_hora >= ? ";
        parametros.add(java.sql.Date.valueOf(fechaInicio));
    }
    if (fechaFin != null) {
        sql += "AND ds.fecha_hora <= ? ";
        if (fechaInicio != null && fechaFin.isBefore(fechaInicio)) {
        }
        parametros.add(java.sql.Date.valueOf(fechaFin));
    }
    
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        
        for (int i = 0; i < parametros.size(); i++) {
            ps.setObject(i + 1, parametros.get(i));
        }

        try (ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Dia_de_Spa unDiasdeSpa = new Dia_de_Spa();
                
                unDiasdeSpa.setCodPack(rs.getInt("cod_cliente"));
                unDiasdeSpa.setFechaYHora(rs.getDate("fecha_hora").toLocalDate());
                unDiasdeSpa.setPreferencias(rs.getString("preferencias"));
                
                Cliente cliente = new Cliente();
                cliente.setCod_cliente(rs.getInt("cod_cliente")); 
                cliente.setDni(rs.getInt("dni"));
                cliente.setNombreCompleto(rs.getString("nombre completo"));
                unDiasdeSpa.setCliente(cliente);
                
                unDiasdeSpa.setEstado(rs.getBoolean("estado"));
                unDiasdeSpa.setMonto(rs.getDouble("monto"));

                listadoDiaDeSpa.add(unDiasdeSpa);
            }
        }
        
    } catch (SQLException ex) {
        System.out.println("Error al listar los dias: " + ex);
    }
    return listadoDiaDeSpa;
}

    public void eliminarDiadeSpa(int codigodelpaquete) {

        String sql = "DELETE from dia_de_spa WHERE codPack = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigodelpaquete);
            ps.executeUpdate();
            ps.close();
            System.out.println("Tu dia de Spa se ha eliminado");

        } catch (SQLException ex) {

            System.out.println("Error al eliminar el dia: " + ex);

        }

    }

    public void actualizarDiaSpa(Dia_de_Spa c) {

        String sql = "UPDATE dia_de_spa SET fecha_hora= ? ,preferencias= ? , codCli = ? , estado= ?, monto = ? WHERE codPack = ?";

        try {
            ClienteData cd = new ClienteData();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(c.getFechaYHora()));
            ps.setString(2, c.getPreferencias());
            ps.setInt(3, c.getCliente().getCod_cliente());
            ps.setBoolean(4, c.getEstado());
            ps.setDouble(5, c.getMonto());

            ps.executeUpdate();
            ps.close();
            System.out.println("Dia actualizado correctamente");

        } catch (SQLException ex) {
            System.out.println("Error al actualizar el Dia: " + ex);
        }
    }

    public void altaLogica(Dia_de_Spa c) {

        String sql = "UPDATE dia_de_spa SET estado=1 WHERE codPack=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getCodPack());
            ps.executeUpdate();
            ps.close();
            System.out.println("Dia dado de alta");

        } catch (SQLException ex) {
            System.out.println("Error al dar de alta " + ex);
        }
    }

    public void bajaLogica(Dia_de_Spa c) {

        String sql = "UPDATE dia_de_spa SET estado=0 WHERE codPack=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getCodPack());
            ps.executeUpdate();
            ps.close();
            System.out.println("Dia dado de baja");

        } catch (SQLException ex) {
            System.out.println("Error al dar de baja " + ex);
        }
    }

    public Dia_de_Spa buscarDia(int codPack) {
        Cliente c = null;
        String sql = "SELECT * FROM dia_de_spa WHERE codPack=?";
        PreparedStatement ps;
        Dia_de_Spa unDiasdeSpa = new Dia_de_Spa();
        try {
            ClienteData cd = new ClienteData();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codPack);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                unDiasdeSpa.setCodPack(rs.getInt("codPack"));
                unDiasdeSpa.setFechaYHora(rs.getDate("FechaYHora").toLocalDate());
                unDiasdeSpa.setPreferencias(rs.getString("preferencias"));
                Cliente cliente = cd.buscarCliente(rs.getInt("cod_cliente"));
                unDiasdeSpa.setCliente(cliente);
                unDiasdeSpa.setEstado(rs.getBoolean("estado"));
                unDiasdeSpa.setMonto(rs.getDouble("monto"));

            }
            System.out.println(c.toString());

        } catch (SQLException ex) {
            System.out.println("No existe ese cliente" + ex);
        }
        return unDiasdeSpa;
    }
}
