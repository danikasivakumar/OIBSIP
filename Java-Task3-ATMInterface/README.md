# ATM Interface – Java

## Project Overview

This project is a console-based ATM Interface developed using Java. It simulates basic ATM operations such as user authentication, balance checking, deposit, withdrawal, money transfer, and transaction history.

## Features

* User ID and PIN authentication
* Check account balance
* Deposit money
* Withdraw money
* Transfer money to another account
* Transaction history
* Input validation
* Insufficient funds validation
* Secure login with limited attempts
* Exit option

## Technologies Used

* Java
* Object-Oriented Programming
* Collections – HashMap and ArrayList
* VS Code
* Git and GitHub

## Project Structure

```text
Java-Task3-ATMInterface/
│
├── ATM.java
├── Account.java
├── Bank.java
├── Main.java
├── Transaction.java
├── .gitignore
└── README.md
```

## Sample Login

**User ID:** 1001
**PIN:** 1234

Another sample account:

**User ID:** 1002
**PIN:** 5678

## How to Run

Compile the Java files:

```bash
javac Account.java Bank.java Transaction.java ATM.java Main.java
```

Run the application:

```bash
java Main
```

## Tested Operations

The following operations were successfully tested:

* Login authentication
* Check balance
* Deposit
* Withdrawal
* Transaction history
* Exit

## Sample Output

```text
Login successful!
Welcome, 1001!

Current Balance: Rs.10000.00

Deposit successful.
Current balance: Rs.10500.00

Withdrawal successful.
Remaining balance: Rs.10300.00
```

## Author

Danika Sivakumar

## Internship

Oasis Infobyte – Java Development Internship
