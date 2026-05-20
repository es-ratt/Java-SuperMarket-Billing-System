package supermarket;

import java.util.*;

public class BillingSystem {

    // Stores current user role (admin/user)
    String role;

    // List of all products loaded from file
    List<Product> products;

    // Cart to store selected items
    List<CartItem> cart = new ArrayList<>();

    // Scanner for user input
    Scanner sc = new Scanner(System.in);

    // Constructor initializes role and loads products
    public BillingSystem(String role) {
        this.role = role;
        products = FileHandler.loadProducts();
    }

    // Show menu based on role
    public void menu() {
        if (role.equals("admin")) {
            adminMenu(); // Admin options
        } else {
            userMenu();  // User options
        }
    }

    // Menu for normal users
    void userMenu() {
        while (true) {
            System.out.println("\n1. Show Product");
            System.out.println("2. Add to Cart");
            System.out.println("3. Generate Bill");
            System.out.println("4. Search Product");
            System.out.println("5. Exit");

            int ch = sc.nextInt();

            // Handle user choice
            switch (ch) {
                case 1 -> showProducts();
                case 2 -> addToCart();
                case 3 -> generateBill();
                case 4 -> searchProduct();
                case 5 -> { return; } // Exit menu
            }
        }
    }

    // Menu for admin users
    void adminMenu() {
        while (true) {
            System.out.println("\n1. Show Product");
            System.out.println("2. Add Product");
            System.out.println("3. Sales Report");
            System.out.println("4. Exit");

            int ch = sc.nextInt();

            // Handle admin choice
            switch (ch) {
                case 1 -> showProducts();
                case 2 -> addProduct();
                case 3 -> showSales();
                case 4 -> { return; } // Exit menu
            }
        }
    }

    // Add or update a product (admin only)
    void addProduct() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Quantity: ");
        int quantity = sc.nextInt();

        boolean found = false;

        // Check if product already exists
        for (Product p : products) {
            if (p.id == id) {
                // Update existing product
                p.quantity += quantity;
                p.price = price;
                found = true;
                break;
            }
        }

        // If product not found, add new product
        if (!found) {
            products.add(new Product(id, name, price, quantity));
        }

        // Save updated product list to file
        FileHandler.overwriteProducts(products);
        System.out.println("Product saved!");
    }

    // Display all products
    void showProducts() {
        for (Product p : products) {
            System.out.println(p.id + " " + p.name + " " + p.price + " Stock: " + p.quantity);
        }
    }

    // Add selected product to cart
    void addToCart() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        // Search product by ID
        for (Product p : products) {
            if (p.id == id) {

                System.out.print("Quantity: ");
                int q = sc.nextInt();

                // Check if enough stock is available
                if (q <= p.quantity) {
                    cart.add(new CartItem(p, q)); // Add to cart

                    p.quantity -= q; // Reduce stock
                    FileHandler.overwriteProducts(products); // Update file

                    System.out.println("Added to cart!");
                } else {
                    System.out.println("Not enough stock!");
                }
                return;
            }
        }

        // If product ID not found
        System.out.println("Product not found!");
    }

    // Generate bill for items in cart
    void generateBill() {
        double total = 0;

        // Calculate total and print each item
        for (CartItem c : cart) {
            System.out.println(c.product.name + " x" + c.quantity + " = " + c.getTotal());
            total += c.getTotal();
        }

        System.out.println("Total = " + total);

        // Save sale to file
        FileHandler.saveSale(cart, total);

        // Clear cart after billing
        cart.clear();
    }

    // Search product by name
    void searchProduct() {
        sc.nextLine(); // Clear buffer
        System.out.print("Enter name: ");
        String name = sc.nextLine().toLowerCase();

        boolean found = false;

        // Search for matching product names
        for (Product p : products) {
            if (p.name.toLowerCase().contains(name)) {
                System.out.println(p.id + " " + p.name + " " + p.price + " Stock: " + p.quantity);
                found = true;
            }
        }

        // If no product found
        if (!found) {
            System.out.println("Product not found!");
        }
    }

    // Show sales report info
    void showSales() {
        System.out.println("Check sales.txt file for full report");
    }
}