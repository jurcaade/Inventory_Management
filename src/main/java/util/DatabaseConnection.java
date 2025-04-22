package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_FILE = "inventory.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS products (" +
                            "id INTEGER PRIMARY KEY, " +
                            "name TEXT NOT NULL, " +
                            "description TEXT, " +
                            "price REAL NOT NULL, " +
                            "quantity INTEGER NOT NULL, " +
                            "category TEXT, " +
                            "supplierId INTEGER, " +
                            "FOREIGN KEY (supplierId) REFERENCES suppliers(id))"
            );

            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS suppliers (" +
                            "id INTEGER PRIMARY KEY, " +
                            "name TEXT NOT NULL, " +
                            "contact_info TEXT, " +
                            "address TEXT)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
