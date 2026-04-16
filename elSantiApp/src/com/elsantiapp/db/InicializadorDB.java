package com.elsantiapp.db;

import java.sql.Connection;
import java.sql.Statement;

public class InicializadorDB {
    public static void inicializar() {
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement()) {

        	String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
        		    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
        		    "fecha_creacion DATE NOT NULL," +
        		    "nombre TEXT NOT NULL," +
        		    "telefono TEXT NOT NULL," +
        		    "email TEXT," +
        		    "localidad TEXT," +
        		    "barrio_privado INTEGER NOT NULL DEFAULT 0," +
        		    "calle_barrio TEXT," +
        		    "altura_lote INTEGER," +
        		    "color TEXT NOT NULL,"+
        		    "activo INTEGER NOT NULL DEFAULT 1" +
        		    ");";

        	stmt.execute(sqlClientes);
            
            String sqlTareas = "CREATE TABLE IF NOT EXISTS tareas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "detalle TEXT," +
                    "precio DECIMAL(10,2) NOT NULL DEFAULT 0.00," +
                    "unidad TEXT NOT NULL," +
                    "activo INTEGER NOT NULL DEFAULT 1" +
                    ");";

            stmt.execute(sqlTareas);
            
            String sqlServicios = "CREATE TABLE IF NOT EXISTS servicios (" +
            	    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            	    "cliente_id INT NOT NULL, "+
            	    "fecha_creacion DATE NOT NULL," +
            	    "fecha_programada DATE NOT NULL," +
            	    "tipo TEXT," +
            	    "precio REAL NOT NULL DEFAULT 0," +
            	    "gastos REAL NOT NULL DEFAULT 0," +
            	    "monto_final REAL NOT NULL DEFAULT 0," +
            	    "estado TEXT," +
            	    "FOREIGN KEY (cliente_id) REFERENCES clientes(id)" +
            	    ");";
            
            stmt.execute(sqlServicios);
            
            String sqlServicioDetalles = "CREATE TABLE IF NOT EXISTS servicio_detalles (" +
            	    "servicio_id INT NOT NULL," +
            	    "trabajo_id INT NOT NULL," +
            	    "cantidad INT NOT NULL DEFAULT 1," +
            	    "estado TEXT NOT NULL," +
            	    "PRIMARY KEY (servicio_id, trabajo_id)," +
            	    "FOREIGN KEY (servicio_id) REFERENCES servicios(id)," +
            	    "FOREIGN KEY (trabajo_id) REFERENCES trabajos(id)" +
            	    ");";
            
            stmt.execute(sqlServicioDetalles);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 }
