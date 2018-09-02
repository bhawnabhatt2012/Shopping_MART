package user;

import shoppingMart.Cart;
import shoppingMart.Payment;
import shoppingMart.Product;
import shoppingMart.ShoppingMart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login extends Payment.Portal implements Payment {

    // data members to store user details
    private String title;
    private String firstName;
    private String lastName;
    private String emailId;
    private String address;
    private String password;
    private long mobileNumber;
    private int index;

    public void accountDetails(Login user) {    // Prints account details
        System.out.println(String.format("%s %s %s", user.getTitle(), user.getFirstName(), user.getLastName()));
        System.out.println(String.format("Email Id : %s", user.getEmailId()));
        System.out.println(String.format("Address : %s", user.getAddress()));
        System.out.println(String.format("Mobile Number : %s", user.getMobileNumber()));
    }

    public boolean verifyUser(String emailId, String password) {    // Verify user using password and username
        return (this.getEmailId().equalsIgnoreCase(emailId) && this.getPassword().equals(password));
    }

    // Menu for users for various activities
    public void userPortal(ArrayList<Login> userList, ArrayList<Product> products) throws IOException,
            InputMismatchException {

        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("Welcome %s %s",
                userList.get(index).getTitle(),
                userList.get(index).getLastName()));

        System.out.println("1. View your account details");
        System.out.println("2. View Products");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Logout");

        while (true) {
            int total = 0;
            System.out.println();
            System.out.print("Enter Your Choice : ");
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    accountDetails(userList.get(index));
                    break;
                case 2:
                    for (Product p : products) {
                        System.out.println();
                        System.out.println("Item ID : " + p.getId());
                        System.out.println("Item Name : " + p.getItemName());
                        System.out.println("Item Description : " + p.getDescription());
                        System.out.println("Item Price : " + p.getPrice());
                    }
                    System.out.println();
                    System.out.println("Enter Item ID");
                    int id = scanner.nextInt();
                    int count = products.get(id - 1).getCount();
                    if (count < 1) {
                        System.out.println("Item out of stock");
                    } else {
                        cart.addProductID(id);
                        products.get(id - 1).setCount(count-1);
                        System.out.println("Item : " + products.get(id - 1).getItemName() + " added to cart");
                    }
                    break;
                case 3:
                    System.out.println("Items in your cart are : ");
                    for (Integer e : cart.getList()) {
                        System.out.println();
                        System.out.println("Item : " + products.get(e - 1).getItemName());
                        System.out.println("Price : " + products.get(e - 1).getPrice());
                        total += products.get(e - 1).getPrice();
                    }
                    System.out.println("Total Cost : " + total);
                    break;
                case 4:
                    prepPayment(total);
                    break;
                case 5:
                    System.out.println("Logged out!, Do you want to login again?");
                    String[] args = new String[2];
                    ShoppingMart.main(args);
                    break;
            }
        }
    }

    // Login menu to check for existing user
    public void performLogin(ArrayList<Login> userList, ArrayList<Product> products) throws IOException,
            InputMismatchException {

        boolean validUser = false;
        Scanner scanner = new Scanner(System.in);

        String localEmailId, localPassword;
        int localIndex = 0;

        while (!validUser) {
            System.out.println("Enter Your email id : ");
            localEmailId = scanner.nextLine();
            System.out.println("Enter your password : ");
            localPassword = scanner.nextLine();
            for (Login l : userList) {
                validUser = l.verifyUser(localEmailId, localPassword);
                localIndex = l.getIndex();
                if (validUser) {
                    break;
                }
            }
            if (!validUser) {
                System.out.println("Enter valid login");
            } else {
                localIndex -= 2;
                userPortal(userList, products);
            }
        }
    }

    @Override
    //Menu for Payment
    public void prepPayment(int total) throws ClassCastException, InputMismatchException {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            System.out.println("Enter 16 Digit Card Number");
            String sample = sc.next();
            if (sample.length() == 16) {
                setCardNumber(Long.parseLong(sample));
            } else {
                System.out.println("Enter a valid card number");
                continue;
            }
            System.out.println("Enter Expiry Month");
            sample = sc.next();
            if (sample.length() == 2) {
                setExpiryMonth(Integer.parseInt(sample));
            } else {
                System.out.println("Enter a valid expiry month");
                continue;
            }
            System.out.println("Enter Expiry Year");
            sample = sc.next();
            if (sample.length() == 2) {
                setExpiryYear(Integer.parseInt(sample));
            } else {
                System.out.println("Enter a valid expiry year");
                continue;
            }
            System.out.println("Enter your Name");
            setName(sc.next());
            flag = true;
        }
        System.out.println("Details Entered Successfully...");
        System.out.println("Accessing payment gateway..");
        System.out.println("Amount Paid : " + total);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}