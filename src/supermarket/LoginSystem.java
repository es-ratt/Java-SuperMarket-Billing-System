package supermarket;

import java.io.*;  //for file handling (FileReader, BufferedReader)
import java.util.*; // to use scanner

public class LoginSystem {
    //It's a static method beacause we call it without object 

    // Method to handle login for a given role (admin/user)
    public static String login(String role) {
        // Create Scanner to take user input
        Scanner sc = new Scanner(System.in);

        // Ask for username
        System.out.print("Username: ");
        String user = sc.nextLine();

        // Ask for password
        System.out.print("Password: ");
        String pass = sc.nextLine();

        try {
            // Open file containing user data
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            String line; //to store line

            // Read file line by line
            while ((line = br.readLine()) != null) {
                // Split data using comma (username,password,role)
                String[] data = line.split(",");

                // Check if entered credentials match file data
                if (data[0].equals(user) && data[1].equals(pass) && data[2].equals(role)) {
                    return role; // Login successful, return role
                }
            }
        } catch (Exception e) {
            // Handle file reading errors
            System.out.println("File error!");
        }

        // Return null if login fails
        return null;
    }
}