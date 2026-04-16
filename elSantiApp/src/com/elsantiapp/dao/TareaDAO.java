package com.elsantiapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.elsantiapp.db.ConexionDB;
import com.elsantiapp.model.Tarea;

public class TareaDAO {
	
	//OBTENER TODAS LS TAREAS
	public List<Tarea> obtenerTareas() {
		
		List<Tarea> lista = new ArrayList<>();
		String sql = "SELECT * FROM tareas";
		
		try(Connection conn = ConexionDB.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				
				Tarea t = new Tarea(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("detalle"),
						rs.getBigDecimal("precio"),
						rs.getString("unidad")
				);
				lista.add(t);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	//LISTAR TODAS LAS TAREAS ACTIVAS
	public List<Tarea> obtenerTareasActivas() {
		
		List<Tarea> lista = new ArrayList<>();
		String sql = "SELECT * FROM tareas WHERE activo = ?";
		
		try(Connection conn = ConexionDB.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
			pstmt.setBoolean(1, true);
			
			try(ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					
					Tarea t = new Tarea(
							rs.getInt("id"),
							rs.getString("nombre"),
							rs.getString("detalle"),
							rs.getBigDecimal("precio"),
							rs.getString("unidad")
					);
					lista.add(t);
				}
			}	
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	//AGREGAR UNA NUEVA TAREA
	public void agregarTarea(Tarea tarea) {
        String sql = "INSERT INTO tareas(nombre, detalle, precio, unidad, activo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

        	pstmt.setString(1, tarea.getNombre());
        	pstmt.setString(2, tarea.getDetalle());
            pstmt.setBigDecimal(3, tarea.getPrecio());
            pstmt.setString(4, tarea.getUnidad());
            pstmt.setBoolean(5, tarea.isActivo());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//MODIFICAR TAREA EXISTENTE
	public void modificarTarea(Tarea tarea) {
	    String sql = "UPDATE tareas SET nombre = ?, detalle = ?, precio = ?, unidad = ?, activo = ? WHERE id = ?";

	    try (Connection conn = ConexionDB.conectar();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, tarea.getNombre());
	        pstmt.setString(2, tarea.getDetalle());
	        pstmt.setBigDecimal(3, tarea.getPrecio());
	        pstmt.setString(4, tarea.getUnidad());
	        pstmt.setBoolean(5, tarea.isActivo());
	        pstmt.setInt(6, tarea.getId());

	        pstmt.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//ELIMINAR TAREA (ACTIVO = FALSE)
	public void eliminarTarea(int id) {
		
		String sql = "UPDATE tareas SET activo = false WHERE id = ?";
		
		try(Connection conn = ConexionDB.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
