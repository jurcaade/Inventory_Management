package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_FILE = "inventory.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            String dropProductsTable = "DROP TABLE IF EXISTS products";
            String dropSuppliersTable = "DROP TABLE IF EXISTS suppliers";
        
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(dropProductsTable);
                stmt.execute(dropSuppliersTable);
            }

            String createSuppliersTable = """
            CREATE TABLE IF NOT EXISTS suppliers (
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                contact_info TEXT,
                address TEXT
            )
        """;

            String createProductsTable = """
            CREATE TABLE IF NOT EXISTS products (
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                description TEXT,
                price REAL NOT NULL,
                quantity_in_stock INTEGER NOT NULL,
                category TEXT,
                supplier_id INTEGER,
                FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
            )
        """;

            try (Statement stmt = conn.createStatement()) {
                // Create tables in order of dependencies
                stmt.execute(createSuppliersTable);
                stmt.execute(createProductsTable);
                System.out.println("Database tables created successfully");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }


}