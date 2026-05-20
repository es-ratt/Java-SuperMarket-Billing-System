package supermarket;

import java.util.Scanner; //we import scanner class to take user input

public class Main {
    public static void main(String[] args) {
        // Create Scanner object to take user input
        Scanner sc = new Scanner(System.in); // to take keyboard input

        // Display login options
        System.out.println("Login as:");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.print("Choice: ");

        // Read user choice
        int choice = sc.nextInt(); 

        // Variable to store login role (admin/user)
        String role = null;

        // Check user choice and call login method accordingly
        if (choice == 1) {
            role = LoginSystem.login("admin"); // Attempt admin login
        } else if (choice == 2) {
            role = LoginSystem.login("user"); // Attempt user login
        }
        // using method calling + static method 

        // If login successful (role is not null), open billing menu
        if (role != null) {
            new BillingSystem(role).menu(); //created object to call method
            //called .menu method to start main system
            
        } else {
            // If login failed, show error message
            System.out.println("Invalid Login!");
        }
    }
}