package supermarket;

import java.io.*;
import java.util.*;

public class FileHandler {

    // Load all products from product.txt file
    public static List<Product> loadProducts() {
        List<Product> list = new ArrayList<>();

        try {
            // Open file for reading
            BufferedReader br = new BufferedReader(new FileReader("product.txt"));
            String line;

            // Read file line by line
            while ((line = br.readLine()) != null) {

                // Split line into fields: id, name, price, quantity
                String[] d = line.split(",");

                // Create Product object and add to list
                list.add(new Product(
                        Integer.parseInt(d[0]),
                        d[1],
                        Double.parseDouble(d[2]),
                        Integer.parseInt(d[3])
                ));
            }

            br.close(); // Close file
        } catch (Exception e) {
            // Handle file errors
            System.out.println("Error loading products!");
        }

        return list;
    }

    // Overwrite product.txt with updated product list
    public static void overwriteProducts(List<Product> products) {
        try {
            // Open file for writing (overwrite mode)
            BufferedWriter bw = new BufferedWriter(new FileWriter("product.txt"));

            // Write each product in file format
            for (Product p : products) {
                bw.write(p.toFileString());
                bw.newLine();
            }

            bw.close(); // Close file
        } catch (Exception e) {
            // Handle file errors
            System.out.println("Error saving products!");
        }
    }

    // Save sales record into sales.txt (append mode)
    public static void saveSale(List<CartItem> cart, double total) {
        try {
            // Open file in append mode
            BufferedWriter bw = new BufferedWriter(new FileWriter("sales.txt", true));

            bw.write("===== BILL =====");
            bw.newLine();

            // Write each cart item
            for (CartItem c : cart) {
                bw.write(c.product.name +
                        " x" + c.quantity +
                        " = " + c.getTotal());
                bw.newLine();
            }

            // Write total amount
            bw.write("TOTAL: " + total);
            bw.newLine();

            // Write timestamp
            bw.write("TIME: " + new java.util.Date());
            bw.newLine();

            bw.write("=================");
            bw.newLine();
            bw.newLine();

            bw.close(); // Close file

        } catch (Exception e) {
            // Handle file errors
            System.out.println("Error saving sales!");
        }
    }
}