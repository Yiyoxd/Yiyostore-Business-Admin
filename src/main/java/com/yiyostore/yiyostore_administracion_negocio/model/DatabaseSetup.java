package com.yiyostore.yiyostore_administracion_negocio.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTables() {
        String url = "jdbc:sqlite:test.db";

        String createClienteTable = "CREATE TABLE IF NOT EXISTS Cliente (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre TEXT NOT NULL,\n"
                + " nombreEnFacebook TEXT,\n"
                + " direccionId INTEGER,\n"
                + " fechaDePrimeraCompra DATE,\n"
                + " numeroTelefono TEXT,\n"
                + " notas TEXT,\n"
                + " FOREIGN KEY (direccionId) REFERENCES Direccion(id)\n"
                + ");";

        String createProductoTable = "CREATE TABLE IF NOT EXISTS Producto (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre TEXT NOT NULL,\n"
                + " descripcion TEXT,\n"
                + " precio DECIMAL(10, 2) NOT NULL,\n"
                + " categoriaId INTEGER,\n"
                + " FOREIGN KEY (categoriaId) REFERENCES Categoria(id)\n"
                + ");";

        String createPedidoTable = "CREATE TABLE IF NOT EXISTS Pedido (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " clienteId INTEGER NOT NULL,\n"
                + " fecha DATE NOT NULL,\n"
                + " metodoPagoId INTEGER,\n"
                + " total DECIMAL(10, 2) NOT NULL,\n"
                + " FOREIGN KEY (clienteId) REFERENCES Cliente(id),\n"
                + " FOREIGN KEY (metodoPagoId) REFERENCES MetodoPago(id)\n"
                + ");";

        String createProductoPedidoTable = "CREATE TABLE IF NOT EXISTS ProductoPedido (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " pedidoId INTEGER NOT NULL,\n"
                + " productoId INTEGER NOT NULL,\n"
                + " cantidad INTEGER NOT NULL,\n"
                + " precio DECIMAL(10, 2) NOT NULL,\n"
                + " FOREIGN KEY (pedidoId) REFERENCES Pedido(id),\n"
                + " FOREIGN KEY (productoId) REFERENCES Producto(id)\n"
                + ");";

        String createCategoriaTable = "CREATE TABLE IF NOT EXISTS Categoria (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre TEXT NOT NULL,\n"
                + " descripcion TEXT\n"
                + ");";

        String createDireccionTable = "CREATE TABLE IF NOT EXISTS Direccion (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " calle TEXT NOT NULL,\n"
                + " numero TEXT NOT NULL,\n"
                + " colonia TEXT,\n"
                + " ciudad TEXT NOT NULL,\n"
                + " estado TEXT NOT NULL,\n"
                + " codigoPostal TEXT NOT NULL\n"
                + ");";

        String createMetodoPagoTable = "CREATE TABLE IF NOT EXISTS MetodoPago (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre TEXT NOT NULL,\n"
                + " descripcion TEXT\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createClienteTable);
            stmt.execute(createProductoTable);
            stmt.execute(createPedidoTable);
            stmt.execute(createProductoPedidoTable);
            stmt.execute(createCategoriaTable);
            stmt.execute(createDireccionTable);
            stmt.execute(createMetodoPagoTable);

            System.out.println("Tables have been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewDatabase("test.db");
        createTables();
    }
}
