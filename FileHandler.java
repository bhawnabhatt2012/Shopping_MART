package fileProcessor;

import shoppingMart.Product;
import user.Login;
import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    // Function signUp to create account for nmew users and store it in UserList.txt
    public void signUp(int index, String title, String firstName, String lastName,
                       String emailId, String address, String password, long mobileNumber)
            throws IOException {

        try (FileWriter fw = new FileWriter("UserList.txt", true); // access UserList.txt
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // Write the new user details
            out.println(Integer.toString(index) + ".");
            out.println("Title : " + title);
            out.println("First Name : " + firstName);
            out.println("Last Name : " + lastName);
            out.println("Email Id : " + emailId);
            out.println("Address : " + address);
            out.println("Password : " + password);
            out.println("Mobile Number : " + Long.toString(mobileNumber));
        } finally {
            System.out.println("User creation Successful, now you can login "
                    + "using your Email Id and Password");
        }
    }

    public ArrayList<Login> readUser() throws IOException {
        ArrayList<Login> userList;
        try {
            BufferedReader br = new BufferedReader(new FileReader("UserList.txt"));
            Login login = new Login();
            String line;
            int counter = 2;
            StringBuilder builder = new StringBuilder(Integer.toString(counter));
            builder.append(".");
            login.setIndex(1);
            userList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Title : ")) {
                    login.setTitle(line.substring(8));
                } else if (line.startsWith("First Name : ")) {
                    login.setFirstName(line.substring(13));
                } else if (line.startsWith("Last Name : ")) {
                    login.setLastName(line.substring(12));
                } else if (line.startsWith("Email Id : ")) {
                    login.setEmailId(line.substring(11));
                } else if (line.startsWith("Address : ")) {
                    login.setAddress(line.substring(10));
                } else if (line.startsWith("Password : ")) {
                    login.setPassword(line.substring(11));
                } else if (line.startsWith("Mobile Number : ")) {
                    login.setMobileNumber(Long.parseLong(line.substring(16)));
                } else if (line.startsWith(builder.toString())) {
                    userList.add(login);
                    login.setIndex(counter++);
                    login = new Login();
                    builder = new StringBuilder(Integer.toString(counter));
                    builder.append(".");
                }
            }
            login.setIndex(counter++);
            userList.add(login);
            login = new Login();
        } finally {
            System.out.println("Reading users done...");
        }
        return userList;
    }

    // Function readFlights to check and retrieve info about flights from FlightList.txt
    public ArrayList<Product> readProducts() throws IOException {
        ArrayList<Product> products;

        try {
            BufferedReader br = new BufferedReader(new FileReader("ProductList.txt"));
            String line;
            products = new ArrayList<>();
            Product product = new Product();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Item Name : ")) {
                    product.setItemName(line.substring(12));
                } else if (line.startsWith("Product Description : ")) {
                    product.setDescription(line.substring(22));
                } else if (line.startsWith("Price : ")) {
                    product.setPrice(Integer.parseInt(line.substring(8)));
                } else if (line.startsWith("Count : ")) {
                    product.setCount(Integer.parseInt(line.substring(8)));
                } else if (line.startsWith("Index : ")) {
                    product.setId(Integer.parseInt(line.substring(8)));
                    products.add(product);
                    product = new Product();
                }
            }
        } finally {
            System.out.println("Reading products done...");
        }
        return products;
    }
}