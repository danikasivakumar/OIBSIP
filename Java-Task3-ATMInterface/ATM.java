import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Account currentAccount;
    private ArrayList<Transaction> transactions;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.transactions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("=================================");
        System.out.println("          WELCOME TO ATM");
        System.out.println("=================================");

        if (!login()) {
            System.out.println("Access denied.");
            System.out.println("Thank you for using the ATM.");
            return;
        }

        showMenu();
    }

    private boolean login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account account = bank.authenticate(userId, pin);

            if (account != null) {
                currentAccount = account;

                System.out.println("\nLogin successful!");
                System.out.println("Welcome, " + userId + "!\n");

                return true;
            }

            attempts++;

            System.out.println("Invalid User ID or PIN.");

            if (attempts < 3) {
                System.out.println("Attempts remaining: "
                        + (3 - attempts));
            }
        }

        return false;
    }

    private void showMenu() {

        int choice = 0;

        do {
            System.out.println("\n=================================");
            System.out.println("             ATM MENU");
            System.out.println("=================================");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    checkBalance();
                    break;

                case 6:
                    System.out.println("\nThank you for using the ATM.");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);
    }

    private void withdraw() {

        System.out.print("Enter withdrawal amount: Rs.");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (currentAccount.withdraw(amount)) {

            transactions.add(
                    new Transaction(
                            "WITHDRAW",
                            amount,
                            "Cash withdrawn"
                    )
            );

            System.out.println("Withdrawal successful.");
            System.out.println("Remaining balance: Rs."
                    + String.format("%.2f",
                    currentAccount.getBalance()));

        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    private void deposit() {

        System.out.print("Enter deposit amount: Rs.");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        currentAccount.deposit(amount);

        transactions.add(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        "Cash deposited"
                )
        );

        System.out.println("Deposit successful.");
        System.out.println("Current balance: Rs."
                + String.format("%.2f",
                currentAccount.getBalance()));
    }

    private void transfer() {

        System.out.print("Enter recipient account ID: ");
        String recipientId = scanner.nextLine();

        Account recipient = bank.findAccount(recipientId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (recipient.getAccountId()
                .equals(currentAccount.getAccountId())) {

            System.out.println(
                    "You cannot transfer to your own account."
            );
            return;
        }

        System.out.print("Enter transfer amount: Rs.");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (currentAccount.withdraw(amount)) {

            recipient.deposit(amount);

            transactions.add(
                    new Transaction(
                            "TRANSFER",
                            amount,
                            "Transferred to account " + recipientId
                    )
            );

            System.out.println("Transfer successful.");
            System.out.println("Remaining balance: Rs."
                    + String.format("%.2f",
                    currentAccount.getBalance()));

        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    private void checkBalance() {

        System.out.println("\nCurrent Balance: Rs."
                + String.format("%.2f",
                currentAccount.getBalance()));
    }

    private void showTransactionHistory() {

        System.out.println("\n=================================");
        System.out.println("       TRANSACTION HISTORY");
        System.out.println("=================================");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}