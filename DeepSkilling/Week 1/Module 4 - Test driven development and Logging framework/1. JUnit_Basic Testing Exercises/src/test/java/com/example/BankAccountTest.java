package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures,
 * Setup and Teardown Methods in JUnit.
 */
public class BankAccountTest {

    private BankAccount account;

    // Setup method: runs before EACH test method
    @Before
    public void setUp() {
        System.out.println("Setting up test fixture...");
        account = new BankAccount(100.0); // common starting state for every test
    }

    // Teardown method: runs after EACH test method
    @After
    public void tearDown() {
        System.out.println("Tearing down test fixture...");
        account = null;
    }

    @Test
    public void testDeposit() {
        // Arrange
        double depositAmount = 50.0;
        double expectedBalance = 150.0;

        // Act
        account.deposit(depositAmount);

        // Assert
        assertEquals(expectedBalance, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() {
        // Arrange
        double withdrawAmount = 30.0;
        double expectedBalance = 70.0;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        assertEquals(expectedBalance, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        // Arrange
        double withdrawAmount = 1000.0;

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> account.withdraw(withdrawAmount));
    }

    @Test
    public void testDepositNegativeAmount() {
        // Arrange
        double depositAmount = -10.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> account.deposit(depositAmount));
    }

    @Test
    public void testTransactionLogRecordsDeposit() {
        // Arrange
        int logSizeBefore = account.getTransactionLog().size();

        // Act
        account.deposit(25.0);

        // Assert
        assertEquals(logSizeBefore + 1, account.getTransactionLog().size());
    }
}
