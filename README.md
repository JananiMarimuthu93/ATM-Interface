# ATM Interface in Java (Console-Based Application)

## 🌟 Project Overview
This project is a console-based ATM system written in **Java**. It simulates the functionalities of a real ATM, allowing users to perform various banking operations such as logging in, withdrawing, depositing, transferring money, checking balance, and viewing transaction history.

---

## 🛠️ Features
- **Login System**: Secure login with User ID and PIN.
- **Withdraw**: Withdraw money from the account.
- **Deposit**: Deposit money into the account.
- **Transfer**: Transfer money between accounts.
- **Transaction History**: View a detailed record of transactions.
- **Change PIN**: Update the PIN securely.
- **Logout**: End the session safely.

---

## 🔧 Technologies Used
- **Programming Language**: Core Java
- **Tools**: JDK 21
- **IDE**: Eclipse
- **Version Control**: Git & GitHub

---

## 🗂️ Project Structure

```plaintext
ATM-Interface/
│
├── src/                  # Source code directory
│   ├── atmServices/      # Contains the main ATM logic and services
│   │   ├── ATM.java      # Main class to run the ATM interface
│   │   ├── Bank.java     # Contains logic for bank transactions and accounts
│   │   └── BankTransaction.java  # Handles transaction operations
│   └── models/           # Contains the model classes for Account and AccountHolder
│       ├── Account.java  # Account class to handle account-related data
│       └── AccountHolder.java  # Class for managing account holder details
│
├── README.md             # Project documentation file
└── .gitignore            # Git ignore file
```
## How to Run
1. **Install Java**: Ensure JDK 21+ is installed on your machine.

2. **Clone Repository**:
   ```bash
   git clone  git clone https://github.com/JananiMarimuthu93/ATM-Interface.git
    ```
   ```bash
   cd ATM-Interface
   ```
3. **Compile**:
   ```bash
   javac -d . src/**/*.java
   ```
4. **Run the Program**:
   ```bash
   java atmServices.ATM
   ```

## 📊 Sample Workflow
- Start the program.
- Enter your User ID and PIN to log in.
- Choose an operation:
  - Withdraw: Enter the amount to withdraw.
  - Deposit: Enter the amount to deposit.
  - Transfer: Provide the destination account number and amount.
  - Transaction History: View your account's transaction records.
- Logout when finished.

## ✅ Test Cases
- **Login**
  - ✅ Valid User ID and PIN: Successful login.
  - ❌ Invalid PIN: Retry prompt with 3 attempts.
- **Transactions**
  - ✅ Sufficient balance: Successful transaction.
  - ❌ Insufficient balance: Error message.

## 🔮 Future Enhancements
- Add GUI for a better user experience.
- Implement database integration for account storage.
- Add support for multiple ATMs.

