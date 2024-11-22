package models;

public class AccountHolder {
    private String userId;
    private String accountNumber;

    public AccountHolder(String userId, String accountNumber) {
        this.userId = userId;
        this.accountNumber = accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
