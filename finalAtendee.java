/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
/**
 *
 * @author _STARS_
 */
public class finalAtendee {
    

    public void dec_balance(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance or invalid amount.");
        }
    }

    public double get_balance() {
        return balance;
    }
    private Wallet wallet;
    public Wallet getWallet() {
        return wallet;
    }
    /*
    Attributes :
    username (string)
    password (string)
    year of birth (int)
    address (string)
    balance (double)
    interests (enum)
    gender (enum)
     */
    /*
    methods :
    create account 
    setters
    to string 
    check enough balance 
    buy ticket 
    display profile 
    constructor & no arg constructor
    */
    Scanner input = new Scanner(System.in);

    public enum Gender { MALE, FEMALE, OTHER }
    public enum Interest { SPORTS, ART, HISTORY, NONE }

    private String username;
    private String password;
    private int yearOfBirth;
    private String address;
    private double balance;
    private Gender gender;
    private List<String> interests = new ArrayList<>(); // Generalized interests    
    public finalAtendee(String username, String password, int yearOfBirth, String address, double balance,
    Gender gender, List<String> interests) {
    this.username = username;
    this.password = password;
    this.yearOfBirth = yearOfBirth;
    this.address = address;
    this.balance = balance;
    this.gender = gender;
    this.interests = interests;
}
    
// Removed duplicate constructor to resolve the compile error
    public finalAtendee() {
        this("unknown", "undefined", 0, "unknown", 0, Gender.OTHER, new ArrayList<>());
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }   
    public String getAddress() {
        return address;
    }
    public double getBalance() {
        return balance;
    }
     public List<String> getInterests() {
        return interests;
    }

    // Removed duplicate setInterests method to resolve the compile error
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = 2025 - yearOfBirth; 
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            System.out.println("balance can't be negative");
        } else {
            this.balance = balance;
        }
    }

    public void setGenders(Gender gender) {
        this.gender = gender;
    }

    public void setInterests(List<String> interests) {
        this.interests = new ArrayList<>(interests); // Replace old interests with new ones
    }

    @Override
    public String toString() {
        return "Atendee{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", yearOfBirth=" + yearOfBirth +
               ", address='" + address + '\'' +
               ", balance=" + balance +
               ", gender=" + gender +
               ", interests=" + interests +
               '}';
    }

    public void displayProfile() {
        System.out.println(this.toString());
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }
    public void createAccount(finalAtendee atendee) {
        System.out.println("Welcome, please enter the following details:");
        

        boolean validGender = false;
        while (!validGender) { // طالما البوليان غلط خش اللوب
            System.out.println("Enter your gender: (MALE, FEMALE, OTHER)");
            String genderInput = input.nextLine();
            try {
                this.setGenders(Gender.valueOf(genderInput.toUpperCase()));
                validGender = true; // حمدالله على السلامة
            } catch (IllegalArgumentException e) { // لو مدخلش جندر مظبوط
                System.out.println("Invalid gender. Please enter MALE, FEMALE, or OTHER.");
            }
        }

        System.out.println("Enter username:");
        String nameInput = input.nextLine();
        atendee.setUsername(nameInput);

        System.out.println("Enter password:");
        String passwordInput = input.nextLine();
        atendee.setPassword(passwordInput);

        System.out.println("Enter address:");
        String addressInput = input.nextLine();
        atendee.setAddress(addressInput);

        System.out.println("Enter year of birth: (it will save your age)");
        int yearInput = input.nextInt();
        input.nextLine();  
        atendee.setYearOfBirth(yearInput);

        System.out.println("Enter your balance:");
        double balanceInput = input.nextDouble();
        input.nextLine();  
        atendee.setBalance(balanceInput);

        System.out.println("Enter your interests (at least 3, separated by commas):");
        String interestsInput = input.nextLine();
        String[] interestsArray = interestsInput.split(",");
        List<String> interestList = new ArrayList<>();

        for (String interest : interestsArray) {
            interestList.add(interest.trim());
        }

        if (interestList.size() < 3) {
            System.out.println("You must enter at least 3 interests. Please try again.");
        } else {
            atendee.setInterests(interestList);
            System.out.println("Interests saved successfully!");
        }}

    public void checkEnoughBalance(double ticket) {
        if (this.balance < ticket) {
            System.out.println("Insufficient balance");
        } else {
            System.out.println("Sufficient balance");
        }
    }

    public void buyTicket(double ticket) {
        this.balance -= ticket;
        System.out.println("Ticket bought successfully");
    }

   
}


