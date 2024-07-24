package com.yiyostore.yiyostore_administracion_negocio.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectData {

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

    public void selectAllClientes() {
        String sql = "SELECT id, nombre, nombreEnFacebook, fechaDePrimeraCompra, numeroTelefono, notas FROM Cliente";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                   rs.getString("nombre") + "\t" +
                                   rs.getString("nombreEnFacebook") + "\t" +
                                   rs.getString("fechaDePrimeraCompra") + "\t" +
                                   rs.getString("numeroTelefono") + "\t" +
                                   rs.getString("notas"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        SelectData app = new SelectData();
        app.selectAllClientes();
    }
}
