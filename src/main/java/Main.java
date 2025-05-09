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
                    viewProductsBySupplier();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add product");
        System.out.println("2. Update product");
        System.out.println("3. Delete product");
        System.out.println("4. View all products");
        System.out.println("5. Search product by ID");
        System.out.println("6. Add supplier");
        System.out.println("7. Update supplier");
        System.out.println("8. Delete supplier");
        System.out.println("9. View all suppliers");
        System.out.println("10. View products by supplier");
        System.out.println("11. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid option: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    private static int getValidIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
    }

    private static double getValidDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }
    }

    private static void addProduct() {
        System.out.println("\nEnter product details:");
        int id = getValidIntInput("ID: ");
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        double price = getValidDoubleInput("Price: ");
        int quantity = getValidIntInput("Stock quantity: ");
        scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        int supplierId = getValidIntInput("Supplier ID: ");
        scanner.nextLine();

        Product product = new Product(id, name, description, price, quantity, category, supplierId);
        try {
            if (!supplierDAO.supplierExists(supplierId)) {
                System.out.println("Supplier with ID " + supplierId + " does not exist!");
                return;
            }
            productDAO.addProduct(product);
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void updateProduct() {
        System.out.print("\nEnter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Product product = productDAO.getProductById(id);
            if (product != null) {
                System.out.print("New name: ");
                String name = scanner.nextLine();
                System.out.print("New description: ");
                String description = scanner.nextLine();
                System.out.print("New price: ");
                double price = scanner.nextDouble();
                System.out.print("New stock quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                System.out.print("New category: ");
                String category = scanner.nextLine();
                System.out.print("New supplier ID: ");
                int supplierId = scanner.nextInt();
                scanner.nextLine();

                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setQuantityInStock(quantity);
                product.setCategory(category);
                product.setSupplierId(supplierId);

                if (!supplierDAO.supplierExists(supplierId)) {
                    System.out.println("Supplier with ID " + supplierId + " does not exist!");
                    return;
                }

                productDAO.updateProduct(product);
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteProduct() {
        System.out.print("\nEnter product ID to delete: ");
        int id = scanner.nextInt();
        try {
            productDAO.deleteProduct(id);
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewAllProducts() {
        try {
            List<Product> products = productDAO.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("No products found.");
                return;
            }

            System.out.println("\nProducts:");
            for (Product product : products) {
                Supplier supplier = supplierDAO.getSupplierById(product.getSupplierId());
                System.out.printf(
                        "Product [ID=%d, Name=%s, SupplierID=%d], Supplier: %s%n",
                        product.getId(),
                        product.getName(),
                        product.getSupplierId(),
                        supplier != null ? supplier.getName() : "Unknown"
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
    }


    private static void findProductById() {
        System.out.print("\nEnter product ID to search: ");
        int id = scanner.nextInt();
        try {
            Product product = productDAO.getProductById(id);
            if (product != null) {
                System.out.println("Product found: " + product);
            } else {
                System.out.println("Product with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addSupplier() {
        System.out.println("\nEnter supplier details:");
        int id = getValidIntInput("ID: ");
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Contact info: ");
        String contactInfo = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        Supplier supplier = new Supplier(id, name, contactInfo, address);
        try {
            supplierDAO.addSupplier(supplier);
            System.out.println("Supplier added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void updateSupplier() {
        System.out.print("\nEnter supplier ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Supplier supplier = supplierDAO.getSupplierById(id);
            if (supplier != null) {
                System.out.print("New name: ");
                String name = scanner.nextLine();
                System.out.print("New contact info: ");
                String contactInfo = scanner.nextLine();
                System.out.print("New address: ");
                String address = scanner.nextLine();

                supplier.setName(name);
                supplier.setContactInfo(contactInfo);
                supplier.setAddress(address);

                supplierDAO.updateSupplier(supplier);
                System.out.println("Supplier updated successfully!");
            } else {
                System.out.println("Supplier with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteSupplier() {
        System.out.print("\nEnter supplier ID to delete: ");
        int id = scanner.nextInt();
        try {
            supplierDAO.deleteSupplier(id);
            System.out.println("Supplier deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierDAO.getAllSuppliers();
            System.out.println("\nSuppliers:");
            for (Supplier supplier : suppliers) {
                System.out.println(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void viewProductsBySupplier() {
        System.out.print("\nEnter supplier ID: ");
        int supplierId = scanner.nextInt();
        try {
            if (!supplierDAO.supplierExists(supplierId)) {
                System.out.println("Supplier with ID " + supplierId + " does not exist!");
                return;
            }

            List<Product> products = productDAO.getProductsBySupplier(supplierId);
            System.out.println("\nProducts for supplier " + supplierId + ":");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}