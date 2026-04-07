package com.elsantiapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.elsantiapp.db.ConexionDB;
import com.elsantiapp.model.Cliente;

public class ClienteDAO {
	
	//LISTAR TODOS LOS CLIENTES
	public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getDate("fecha_creacion").toLocalDate(),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("localidad"),
                        rs.getBoolean("barrio_privado"),
                        rs.getString("calle_barrio"),
                        rs.getInt("altura_lote"),                        
                        rs.getString("color")        
                );
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
	
	//LISTAR TODOS LOS CLIENTES ACTIVOS
	public List<Cliente> obtenerClientesActivos() {
	    List<Cliente> lista = new ArrayList<>();
	    String sql = "SELECT * FROM clientes WHERE activo = ?";

	    try (Connection conn = ConexionDB.conectar();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setBoolean(1, true);

	        try (ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	                Cliente c = new Cliente(
	                        rs.getInt("id"),
	                        rs.getDate("fecha_creacion").toLocalDate(),
	                        rs.getString("nombre"),
	                        rs.getString("telefono"),
	                        rs.getString("email"),
	                        rs.getString("localidad"),
	                        rs.getBoolean("barrio_privado"),
	                        rs.getString("calle_barrio"),
	                        rs.getInt("altura_lote"),
	                        rs.getString("color")
	                );
	                lista.add(c);
	            }

	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	
	//AGREGAR UN NUEVO CLIENTE
	public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes(fecha_creacion, nombre, telefono, email, localidad, barrio_privado, calle_barrio, altura_lote, color, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

        	pstmt.setDate(1, java.sql.Date.valueOf(cliente.getFechaCreacion()));
        	pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());
            pstmt.setString(5, cliente.getLocalidad());
            pstmt.setBoolean(6, cliente.isBarrioPrivado());
            pstmt.setString(7, cliente.getCalleBarrio());
            pstmt.setInt(8, cliente.getAlturaLote());
            pstmt.setString(9, cliente.getColor());
            pstmt.setBoolean(10, cliente.isActivo());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//MODIFICAR CLIENTE EXISTENTE
	public void modificarCliente(Cliente cliente) {
	    String sql = "UPDATE clientes SET fecha_creacion=?, nombre=?, telefono=?, email=?, localidad=?, barrio_privado=?, calle_barrio=?, altura_lote=?, color=?, activo=? WHERE id=?";

	    try (Connection conn = ConexionDB.conectar();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setDate(1, java.sql.Date.valueOf(cliente.getFechaCreacion()));
	        pstmt.setString(2, cliente.getNombre());
	        pstmt.setString(3, cliente.getTelefono());
	        pstmt.setString(4, cliente.getEmail());
	        pstmt.setString(5, cliente.getLocalidad());
	        pstmt.setBoolean(6, cliente.isBarrioPrivado());
	        pstmt.setString(7, cliente.getCalleBarrio());
	        pstmt.setInt(8, cliente.getAlturaLote());
	        pstmt.setString(9, cliente.getColor());
	        pstmt.setBoolean(10, cliente.isActivo());
	        pstmt.setInt(11, cliente.getId());

	        pstmt.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//ELIMINAR CLIENTE (ACTIVO = FALSE)
	public void eliminarCliente(int id) {
	    String sql = "UPDATE clientes SET activo = false WHERE id = ?";

	    try (Connection conn = ConexionDB.conectar();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, id);

	        pstmt.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
