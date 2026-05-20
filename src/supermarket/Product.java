package supermarket;

public class Product {
    // Product ID
    int id;

    // Product name
    String name;

    // Price per unit
    double price;

    // Available quantity
    int quantity;

    // Constructor to initialize product details
    public Product(int id, String name, double price, int quantity) {
        this.id = id;           // Set product ID
        this.name = name;       // Set product name
        this.price = price;     // Set product price
        this.quantity = quantity; // Set product quantity
    }

    // Convert product data into a string format for file storage
    public String toFileString() {
        // Format: id,name,price,quantity
        return id + "," + name + "," + price + "," + quantity;
    }
}