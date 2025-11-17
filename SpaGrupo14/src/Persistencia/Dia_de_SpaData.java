package Persistencia;

import Modelo.Cliente;
import Modelo.Dia_de_Spa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Dia_de_SpaData {

    private Connection con = null;

    public Dia_de_SpaData() {
        con = Conexion.getConectar();
    }

    public void agregarDiaSpa(Dia_de_Spa ds) {

        String sql = "INSERT INTO dia_de_spa (fecha_hora, preferencias, cod_cliente, estado, monto) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, Date.valueOf(ds.getFechaYHora()));
            ps.setString(2, ds.getPreferencias());
            ps.setInt(3, ds.getCliente().getCod_cliente());
            ps.setBoolean(4, ds.getEstado());
            ps.setDouble(5, ds.getMonto());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                ds.setCodPack(rs.getInt(1));
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error agregarDiaSpa: " + ex);
        }
    }

    public List<Dia_de_Spa> listarDia_de_Spa() {
        List<Dia_de_Spa> lista = new ArrayList<>();
        String sql = "SELECT * FROM dia_de_spa WHERE estado = 1";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            ClienteData cd = new ClienteData();

            while (rs.next()) {
                Dia_de_Spa ds = new Dia_de_Spa();
                ds.setCodPack(rs.getInt("cod_pack"));
                ds.setFechaYHora(rs.getDate("fecha_hora").toLocalDate());
                ds.setPreferencias(rs.getString("preferencias"));

                Cliente cli = cd.buscarClientePorID(rs.getInt("cod_cliente"));
                ds.setCliente(cli);

                ds.setMonto(rs.getDouble("monto"));
                ds.setEstado(rs.getBoolean("estado"));

                lista.add(ds);
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error listarDia_de_Spa: " + ex);
        }
        return lista;
    }

    public List<Dia_de_Spa> listarDiaSpa(String dniCliente, LocalDate inicio, LocalDate fin) {

        List<Dia_de_Spa> lista = new ArrayList<>();

        String sql = "SELECT ds.* FROM dia_de_spa ds JOIN cliente c ON ds.cod_cliente = c.cod_cliente WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        if (dniCliente != null && !dniCliente.isEmpty()) {
            sql += "AND c.dni = ? ";
            params.add(Integer.parseInt(dniCliente));
        }
        if (inicio != null) {
            sql += "AND ds.fecha_hora >= ? ";
            params.add(Date.valueOf(inicio));
        }
        if (fin != null) {
            sql += "AND ds.fecha_hora <= ? ";
            params.add(Date.valueOf(fin));
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            ClienteData cd = new ClienteData();

            while (rs.next()) {
                Dia_de_Spa ds = new Dia_de_Spa();

                ds.setCodPack(rs.getInt("cod_pack"));
                ds.setFechaYHora(rs.getDate("fecha_hora").toLocalDate());
                ds.setPreferencias(rs.getString("preferencias"));

                Cliente c = cd.buscarClientePorID(rs.getInt("cod_cliente"));
                ds.setCliente(c);

                ds.setMonto(rs.getDouble("monto"));
                ds.setEstado(rs.getBoolean("estado"));

                lista.add(ds);
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error listarDiaSpa filtrado: " + ex);
        }

        return lista;
    }

    public void eliminarDiadeSpa(int codPack) {
        String sql = "DELETE FROM dia_de_spa WHERE cod_pack = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codPack);
            ps.executeUpdate();
            ps.close();

            System.out.println("Dia de spa eliminado");

        } catch (SQLException ex) {
            System.out.println("Error eliminarDiaSpa: " + ex);
        }
    }
    public void actualizarDiaSpa(Dia_de_Spa c) {

        String sql = "UPDATE dia_de_spa SET fecha_hora=?, preferencias=?, cod_cliente=?, estado=?, monto=? WHERE cod_pack=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(c.getFechaYHora()));
            ps.setString(2, c.getPreferencias());
            ps.setInt(3, c.getCliente().getCod_cliente());
            ps.setBoolean(4, c.getEstado());
            ps.setDouble(5, c.getMonto());
            ps.setInt(6, c.getCodPack());

            ps.executeUpdate();
            ps.close();
            System.out.println("Dia actualizado correctamente");

        } catch (SQLException ex) {
            System.out.println("Error al actualizar el dia: " + ex);
        }
}
    
    public Dia_de_Spa buscarDia(int codPack) {
        Dia_de_Spa ds = null;
        String sql = "SELECT * FROM dia_de_spa WHERE cod_pack=?";

        try {
            ClienteData cd = new ClienteData();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codPack);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ds = new Dia_de_Spa();
                ds.setCodPack(rs.getInt("cod_pack"));
                ds.setFechaYHora(rs.getDate("fecha_hora").toLocalDate());
                ds.setPreferencias(rs.getString("preferencias"));

                Cliente cliente = cd.buscarClientePorID(rs.getInt("cod_cliente"));
                ds.setCliente(cliente);

                ds.setEstado(rs.getBoolean("estado"));
                ds.setMonto(rs.getDouble("monto"));
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error buscar dia spa: " + ex);
        }

        return ds;
    }
    public List<Dia_de_Spa> listarPorFecha(LocalDate fecha) {
    List<Dia_de_Spa> lista = new ArrayList<>();

    String sql = "SELECT * FROM dia_de_spa WHERE DATE(fecha_hora) = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, java.sql.Date.valueOf(fecha));
        ResultSet rs = ps.executeQuery();

        ClienteData cd = new ClienteData();

        while (rs.next()) {
            Dia_de_Spa ds = new Dia_de_Spa();

            ds.setCodPack(rs.getInt("cod_pack"));

            ds.setFechaYHora(
                rs.getTimestamp("fecha_hora").toLocalDateTime().toLocalDate()
            );

            ds.setPreferencias(rs.getString("preferencias"));
            ds.setEstado(rs.getBoolean("estado"));
            ds.setMonto(rs.getDouble("monto"));

            int idCliente = rs.getInt("cod_cliente");

           
            ds.setCliente(cd.buscarClientePorID(idCliente));

            lista.add(ds);
        }

        ps.close();

    } catch (SQLException ex) {
        System.out.println("Error al listar por fecha: " + ex);
    }

    return lista;
}
}

