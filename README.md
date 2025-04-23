# 🏪 Inventory Management System

A Java-based inventory management system using SQLite for data management.

## 📋 Overview
This application provides a comprehensive solution for managing inventory, products, and supplier relationships through a console interface.

## ✨ Features

### 📦 Product Management
* Add new products
* Update existing products
* Delete products
* Search products by ID
* List all products in inventory

### 🧾 Supplier Management
* Add new suppliers
* Update supplier information
* Delete suppliers
* View all suppliers
* View products by supplier

### 🧭 Console Menu Example

```text
Menu:
1. Add product
2. Update product
3. Delete product
4. View all products
5. Search product by ID
6. Add supplier
7. Update supplier
8. Delete supplier
9. View all suppliers
10. View products by supplier
11. Exit
Choose an option:
```
## 🛠 Technology Stack
* **Java** (JDK 18+) - Core language
* **SQLite** - Database
* **Maven** - Dependency management
* **JDBC** - Database connectivity

## 📥 System Requirements
1. Java JDK 18 or newer
2. Maven
3. SQLite

## ⚙️ Setup & Installation

### 1. Clone Project
```bash
git clone https://github.com/yourusername/inventory-management.git
```
### 2. Build Project
```bash
mvn clean install
```
### 3. Run Application
```bash
java -jar target/inventory-management-1.0-SNAPSHOT.jar
```

## 📚 Database Structure

### Products Table
* ID (Primary Key)
* Name
* Description
* Price
* Stock Quantity
* Category
* Supplier ID (Foreign Key)

### Suppliers Table
* ID (Primary Key)
* Name
* Contact Information
* Address
   

## 🔧 Development
Project implements:
* DAO (Data Access Object) Pattern
* Data validation
* Exception handling
* Referential integrity

## 📝 License
This project is licensed under the MIT License.