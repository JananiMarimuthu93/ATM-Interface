package atmServices;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.Account;
import models.AccountHolder;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, AccountHolder> accountHolders = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void addAccountHolder(AccountHolder accountHolder) {
        accountHolders.put(accountHolder.getUserId(), accountHolder);
    }

   
    public Account getAccount(String userId) {
        // Fetch account holder based on the user ID
        AccountHolder accountHolder = accountHolders.get(userId);

        // Check if account holder exists and the account number is valid
        if (accountHolder != null) {
            String accountNumber = accountHolder.getAccountNumber();
            
            // Fetch account using account number
            Account account = accounts.get(accountNumber);
            
            if (account != null) {
                return account;  // Return the account if found
            }
        }
        return null;  // Return null if no account is found
    }


    // Authenticate user method
    public boolean authenticateUser(String userId, String pin) {
        AccountHolder accountHolder = accountHolders.get(userId);
        if (accountHolder != null) {
            Account account = accounts.get(accountHolder.getAccountNumber());
            if (account != null && account.getPin().equals(pin)) {
                return true;
            }
        }
        return false;
    }
    
   


}
