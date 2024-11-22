package atmServices;

import java.util.List;
import java.util.Scanner;
import models.Account;
import models.AccountHolder;


public class ATM {
    private static final int MAX_ATTEMPTS = 3;
    private int attemptCount = 0;
    private Bank bank;
    private Account currentAccount;

    // Constructor
    public ATM(Bank bank) {
        this.bank = bank;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM System!");

        while (attemptCount < MAX_ATTEMPTS) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            if (bank.authenticateUser(userId, pin)) {
                currentAccount = bank.getAccount(userId);  // Fetch user's account details
                if (currentAccount != null) {
                    System.out.println("Login successful!");
                    performTransactions(scanner);
                    return;  // Successfully logged in, exit loop
                } else {
                    System.out.println("Account not found for User ID: " + userId);
                }
            } else {
                attemptCount++;
                System.out.println("Incorrect PIN. You have " + (MAX_ATTEMPTS - attemptCount) + " attempts left.");
            }
        }

        System.out.println("Maximum attempts reached. Your account is locked for security.");
    }

    // Perform various ATM operations
    private void performTransactions(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Withdraw\n2. Deposit\n3. Transfer\n4. Balance Inquiry\n5. Transaction History\n6. Change PIN\n7. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            switch (choice) {
                case 1:
                    withdraw(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    transfer(scanner);
                    break;
                case 4:
                    balanceInquiry();
                    break;
                case 5:
                    transactionHistory();
                    break;
                case 6:
                    changePIN(scanner);
                    break;
                case 7:
                    logout();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Withdraw money
    private void withdraw(Scanner scanner) {
        checkCurrentAccount(); // Ensure an account is logged in

        if (currentAccount == null) return; // If no account, return early

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= currentAccount.getBalance() && amount > 0) {
            currentAccount.withdraw(amount);
            currentAccount.recordTransaction("Withdrawal", amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    // Deposit money
    private void deposit(Scanner scanner) {
        checkCurrentAccount(); // Ensure an account is logged in

        if (currentAccount == null) return; // If no account, return early

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentAccount.deposit(amount);
            currentAccount.recordTransaction("Deposit", amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void transfer(Scanner scanner) {
        checkCurrentAccount(); // Ensure an account is logged in

        if (currentAccount == null) return; // If no account, return early

        System.out.print("Enter the destination user ID: ");
        String destinationUserId = scanner.nextLine();
        Account destinationAccount = bank.getAccount(destinationUserId);

        // Verify if the destination account exists
        if (destinationAccount == null) {
            System.out.println("Invalid destination user ID.");
            return;
        }

        // Ask for the transfer amount
        System.out.print("Enter the transfer amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        // Check if source account has enough balance
        if (currentAccount.getBalance() >= amount && amount > 0) {
            // Perform the transfer: withdraw from source and deposit to destination
            currentAccount.withdraw(amount);
            destinationAccount.deposit(amount);

            // Record transactions for both accounts
            currentAccount.recordTransaction("Transfer to " + destinationAccount.getAccountNumber(), amount);
            destinationAccount.recordTransaction("Transfer from " + currentAccount.getAccountNumber(), amount);

            // Print success message
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Check account balance
    private void balanceInquiry() {
        checkCurrentAccount(); // Ensure an account is logged in

        if (currentAccount == null) return; // If no account, return early

        System.out.println("Your current balance is: " + currentAccount.getBalance());
    }

    // View transaction history
    private void transactionHistory() {
        checkCurrentAccount(); // Ensure an account is logged in

        if (currentAccount == null) return; // If no account, return early

        List<BankTransaction> transactionList = currentAccount.getTransactionHistory();
        if (transactionList.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (BankTransaction transaction : transactionList) {
                System.out.println(transaction.getTransactionType() + " of amount " + transaction.getAmount() + " on " + transaction.getTimestamp());
            }
        }
    }

    // Change PIN
    private void changePIN(Scanner scanner) {
        checkCurrentAccount(); // Ensure an account is logged in

        if (currentAccount == null) return; // If no account, return early

        System.out.print("Enter your current PIN: ");
        String currentPin = scanner.nextLine();

        if (currentPin.equals(currentAccount.getPin())) {
            System.out.print("Enter new PIN: ");
            String newPin = scanner.nextLine();
            currentAccount.setPin(currentPin, newPin);
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Incorrect PIN. Unable to change PIN.");
        }
    }

    // Logout and reset state
    private void logout() {
        System.out.println("You have logged out successfully.");
        attemptCount = 0;  // Reset attempts on logout
        currentAccount = null;  // Reset current account
    }

    // Helper method to check if an account is logged in
    private void checkCurrentAccount() {
        if (currentAccount == null) {
            System.out.println("No account is currently logged in.");
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        ATM atm = new ATM(bank);

        AccountHolder holder1 = new AccountHolder("Deepika18", "31894713"); 
        Account account1 = new Account("31894713", 2000, "1808");
        bank.addAccount(account1);
        bank.addAccountHolder(holder1);

        AccountHolder holder2 = new AccountHolder("Janani09", "49209472");
        Account account2 = new Account("49209472", 3000, "0903");
        bank.addAccount(account2);
        bank.addAccountHolder(holder2);
        atm.start();
    }

    
}
