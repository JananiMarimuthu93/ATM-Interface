package atmServices;

import java.time.LocalDateTime;

public class BankTransaction {
    private String transactionType;
    private double amount;
    private LocalDateTime timestamp;
    private double balance;

    // Constructor
    public BankTransaction(String transactionType, double amount, LocalDateTime timestamp, double balance) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = timestamp;
        this.balance = balance;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getBalance() {
        return balance;
    }

    // Override toString to provide a readable transaction summary
    @Override
    public String toString() {
        return String.format("Transaction: %s | Amount: %.2f | Time: %s | Balance: %.2f", 
                             transactionType, amount, timestamp, balance);
    }
}
