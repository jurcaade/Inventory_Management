package dao;

import model.Supplier;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    // CREATE - Adaugă un furnizor nou
    public void addSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO suppliers (id, name, contact_info, address) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplier.getSupplierId());
            stmt.setString(2, supplier.getName());
            stmt.setString(3, supplier.getContactInfo());
            stmt.setString(4, supplier.getAddress());

            stmt.executeUpdate();
        }
    }

    // READ - Obține toți furnizorii
    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Supplier s = new Supplier();
                s.setSupplierId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setContactInfo(rs.getString("contact_info"));
                s.setAddress(rs.getString("address"));

                suppliers.add(s);
            }
        }
        return suppliers;
    }

    // UPDATE - Actualizează un furnizor
    public void updateSupplier(Supplier supplier) throws SQLException {
        String sql = "UPDATE suppliers SET name = ?, contact_info = ?, address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContactInfo());
            stmt.setString(3, supplier.getAddress());
            stmt.setInt(4, supplier.getSupplierId());

            stmt.executeUpdate();
        }
    }

    // DELETE - Șterge un furnizor
    public void deleteSupplier(int supplierId) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplierId);
            stmt.executeUpdate();
        }
    }

    // READ - Găsește furnizor după ID
    public Supplier getSupplierById(int supplierId) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE id = ?";
        Supplier supplier = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplierId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setContactInfo(rs.getString("contact_info"));
                supplier.setAddress(rs.getString("address"));
            }
        }
        return supplier;
    }

    // EXTRA: Verifică dacă un furnizor există
    public boolean supplierExists(int supplierId) throws SQLException {
        String sql = "SELECT 1 FROM suppliers WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplierId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}