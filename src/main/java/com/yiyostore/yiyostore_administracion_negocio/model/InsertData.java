package com.yiyostore.yiyostore_administracion_negocio.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertCliente(String nombre, String nombreEnFacebook, int direccionId, String fechaDePrimeraCompra, String numeroTelefono, String notas) {
        String sql = "INSERT INTO Cliente(nombre, nombreEnFacebook, direccionId, fechaDePrimeraCompra, numeroTelefono, notas) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, nombreEnFacebook);
            pstmt.setInt(3, direccionId);
            pstmt.setString(4, fechaDePrimeraCompra);
            pstmt.setString(5, numeroTelefono);
            pstmt.setString(6, notas);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        InsertData app = new InsertData();
        app.insertCliente("Juan Perez", "juanperez", 1, "2024-07-23", "+1234567890", "Cliente preferencial");
    }
}
