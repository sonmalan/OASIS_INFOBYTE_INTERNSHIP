import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
        transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited $" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return false;
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawn $" + amount);
            return true;
        }
    }

    public boolean transfer(double amount, BankAccount recipient) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return false;
        } else {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transferred $" + amount + " to " + recipient);
            return true;
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

class ATM {
    private BankAccount userAccount;
    private String username;
    private String password;

    public ATM(BankAccount account, String username, String password) {
        userAccount = account;
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String inputUsername, inputPassword;

        System.out.print("Enter your username: ");
        inputUsername = scanner.nextLine();
        System.out.print("Enter your password: ");
        inputPassword = scanner.nextLine();

        if (authenticate(inputUsername, inputPassword)) {
            int choice;

            do {
                displayMenu();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        if (userAccount.withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful. Remaining balance: $" + userAccount.getBalance());
                        }
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        userAccount.deposit(depositAmount);
                        System.out.println("Deposit successful. New balance: $" + userAccount.getBalance());
                        break;
                    case 3:
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        System.out.print("Enter recipient's initial balance: ");
                        double recipientBalance = scanner.nextDouble();
                        BankAccount recipientAccount = new BankAccount(recipientBalance);
                        if (userAccount.transfer(transferAmount, recipientAccount)) {
                            System.out.println("Transfer successful. Remaining balance: $" + userAccount.getBalance());
                        }
                        break;
                    case 4:
                        System.out.println("Current balance: $" + userAccount.getBalance());
                        break;
                    case 5:
                        System.out.println("Transaction History:");
                        List<String> history = userAccount.getTransactionHistory();
                        for (String transaction : history) {
                            System.out.println(transaction);
                        }
                        break;
                    case 6:
                        System.out.println("Exiting. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 6);
        } else {
            System.out.println("Authentication failed. Incorrect username or password.");
        }

        scanner.close();
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter initial account balance: ");
        double initialBalance = scanner.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount, username, password);

        atm.start();

        scanner.close();
    }
}
