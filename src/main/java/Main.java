import dao.ProductDAO;
import dao.SupplierDAO;
import model.Product;
import model.Supplier;
import util.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ProductDAO productDAO = new ProductDAO();
    private static SupplierDAO supplierDAO = new SupplierDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inițializarea bazei de date
        DatabaseConnection.initializeDatabase();

        while (true) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewAllProducts();
                    break;
                case 5:
                    findProductById();
                    break;
                case 6:
                    addSupplier();
                    break;
                case 7:
                    updateSupplier();
                    break;
                case 8:
                    deleteSupplier();
                    break;
                case 9:
                    viewAllSuppliers();
                    break;
                case 10:
                    System.out.println("Ieșire...");
                    return;
                default:
                    System.out.println("Opțiune invalidă! Încercați din nou.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nMeniu:");
        System.out.println("1. Adăugare produs");
        System.out.println("2. Actualizare produs");
        System.out.println("3. Ștergere produs");
        System.out.println("4. Vizualizare toate produsele");
        System.out.println("5. Căutare produs după ID");
        System.out.println("6. Adăugare furnizor");
        System.out.println("7. Actualizare furnizor");
        System.out.println("8. Ștergere furnizor");
        System.out.println("9. Vizualizare toți furnizorii");
        System.out.println("10. Ieșire");
        System.out.print("Alege o opțiune: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Introducere invalidă. Te rog introdu o opțiune validă: ");
            scanner.next(); // Consuma inputul invalid
        }
        return scanner.nextInt();
    }

    private static void addProduct() {
        System.out.println("\nIntroducere detalii produs:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumă linia rămasă
        System.out.print("Nume: ");
        String name = scanner.nextLine();
        System.out.print("Descriere: ");
        String description = scanner.nextLine();
        System.out.print("Preț: ");
        double price = scanner.nextDouble();
        System.out.print("Cantitate în stoc: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consumă linia rămasă
        System.out.print("Categorie: ");
        String category = scanner.nextLine();

        Product product = new Product(id, name, description, price, quantity, category);
        try {
            productDAO.addProduct(product);
            System.out.println("Produs adăugat cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProduct() {
        System.out.print("\nIntroducere ID produs de actualizat: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumă linia rămasă
        try {
            Product product = productDAO.getProductById(id);
            if (product != null) {
                System.out.print("Nume nou: ");
                String name = scanner.nextLine();
                System.out.print("Descriere nouă: ");
                String description = scanner.nextLine();
                System.out.print("Preț nou: ");
                double price = scanner.nextDouble();
                System.out.print("Cantitate nouă în stoc: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();  // Consumă linia rămasă
                System.out.print("Categorie nouă: ");
                String category = scanner.nextLine();

                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setQuantityInStock(quantity);
                product.setCategory(category);

                productDAO.updateProduct(product);
                System.out.println("Produs actualizat cu succes!");
            } else {
                System.out.println("Produsul cu ID-ul " + id + " nu a fost găsit.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteProduct() {
        System.out.print("\nIntroducere ID produs de șters: ");
        int id = scanner.nextInt();
        try {
            productDAO.deleteProduct(id);
            System.out.println("Produs șters cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewAllProducts() {
        try {
            List<Product> products = productDAO.getAllProducts();
            System.out.println("\nProduse:");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void findProductById() {
        System.out.print("\nIntroducere ID produs de căutat: ");
        int id = scanner.nextInt();
        try {
            Product product = productDAO.getProductById(id);
            if (product != null) {
                System.out.println("Produs găsit: " + product);
            } else {
                System.out.println("Produsul cu ID-ul " + id + " nu a fost găsit.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addSupplier() {
        System.out.println("\nIntroducere detalii furnizor:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumă linia rămasă
        System.out.print("Nume: ");
        String name = scanner.nextLine();
        System.out.print("Informații contact: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Adresă: ");
        String address = scanner.nextLine();

        Supplier supplier = new Supplier(id, name, contactInfo, address);
        try {
            supplierDAO.addSupplier(supplier);
            System.out.println("Furnizor adăugat cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateSupplier() {
        System.out.print("\nIntroducere ID furnizor de actualizat: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumă linia rămasă
        try {
            Supplier supplier = supplierDAO.getSupplierById(id);
            if (supplier != null) {
                System.out.print("Nume nou: ");
                String name = scanner.nextLine();
                System.out.print("Informații contact noi: ");
                String contactInfo = scanner.nextLine();
                System.out.print("Adresă nouă: ");
                String address = scanner.nextLine();

                supplier.setName(name);
                supplier.setContactInfo(contactInfo);
                supplier.setAddress(address);

                supplierDAO.updateSupplier(supplier);
                System.out.println("Furnizor actualizat cu succes!");
            } else {
                System.out.println("Furnizorul cu ID-ul " + id + " nu a fost găsit.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteSupplier() {
        System.out.print("\nIntroducere ID furnizor de șters: ");
        int id = scanner.nextInt();
        try {
            supplierDAO.deleteSupplier(id);
            System.out.println("Furnizor șters cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierDAO.getAllSuppliers();
            System.out.println("\nFurnizori:");
            for (Supplier supplier : suppliers) {
                System.out.println(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
