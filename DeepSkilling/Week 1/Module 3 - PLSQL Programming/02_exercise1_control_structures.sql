-- Exercise 1: Control Structures

-- Scenario 1: Apply 1% discount to loan interest rates for customers above 60
BEGIN
    FOR cust IN (SELECT CustomerID, DOB FROM Customers) LOOP
        IF TRUNC(MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12) > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - (InterestRate * 0.01)
            WHERE CustomerID = cust.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Set IsVIP = 'Y' for customers with balance over $10,000
BEGIN
    FOR cust IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF cust.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'Y'
            WHERE CustomerID = cust.CustomerID;
        ELSE
            UPDATE Customers
            SET IsVIP = 'N'
            WHERE CustomerID = cust.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Print reminders for loans due within the next 30 days
BEGIN
    FOR loan_rec IN (
        SELECT l.LoanID, l.CustomerID, l.EndDate, c.Name
        FROM Loans l
        JOIN Customers c ON c.CustomerID = l.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Loan ' || loan_rec.LoanID || ' for customer ' || loan_rec.Name ||
            ' is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD')
        );
    END LOOP;
END;
/
