package models;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import atmServices.BankTransaction;

public class Account {
    private String accountNumber;
    private double balance;
    private String pin;
    private List<BankTransaction> transactions;

    // Constructor
    public Account(String accountNumber, double balance, String pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }

    // Getter for account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Getter for pin
    public String getPin() {
        return pin;
    }

    // Set new pin (with old pin validation)
    public boolean setPin(String oldPin, String newPin) {
        if (this.pin.equals(oldPin) && newPin != null && !newPin.isEmpty()) {
            this.pin = newPin;
            return true;  // Pin changed successfully
        }
        return false;  // Old pin doesn't match or new pin is invalid
    }

    // Record a transaction
    public void recordTransaction(String type, double amount) {
        // Create a transaction with the current balance
        BankTransaction transaction = new BankTransaction(type, amount, LocalDateTime.now(), balance);
        transactions.add(transaction);
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            recordTransaction("Withdrawal", amount);
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            recordTransaction("Deposit", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Get transaction history
    public List<BankTransaction> getTransactionHistory() {
        return transactions;
    }

    // Override toString for better account info display
    @Override
    public String toString() {
        return "Account [Account Number: " + accountNumber + ", Balance: " + balance + "]";
    }
}
