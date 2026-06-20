-- Exercise 6: Cursors

-- Scenario 1: GenerateMonthlyStatements - print each customer's transactions for the current month
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, t.TransactionID, t.Amount, t.TransactionType, t.TransactionDate
        FROM Customers c
        JOIN Accounts a ON a.CustomerID = c.CustomerID
        JOIN Transactions t ON t.AccountID = a.AccountID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ORDER BY c.CustomerID, t.TransactionDate;

    v_current_customer NUMBER := -1;
BEGIN
    FOR stmt_rec IN GenerateMonthlyStatements LOOP
        IF stmt_rec.CustomerID <> v_current_customer THEN
            DBMS_OUTPUT.PUT_LINE('--- Statement for ' || stmt_rec.Name || ' (Customer ' || stmt_rec.CustomerID || ') ---');
            v_current_customer := stmt_rec.CustomerID;
        END IF;
        DBMS_OUTPUT.PUT_LINE('  ' || TO_CHAR(stmt_rec.TransactionDate, 'YYYY-MM-DD') ||
                              ' | ' || stmt_rec.TransactionType || ' | ' || stmt_rec.Amount);
    END LOOP;
END;
/

-- Scenario 2: ApplyAnnualFee - deduct an annual maintenance fee from every account
DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE;

    v_annual_fee CONSTANT NUMBER := 25;
BEGIN
    FOR acct_rec IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - v_annual_fee,
            LastModified = SYSDATE
        WHERE AccountID = acct_rec.AccountID;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fee applied to all accounts.');
END;
/

-- Scenario 3: UpdateLoanInterestRates - update interest rates per a new policy
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate FROM Loans FOR UPDATE;

    v_new_rate NUMBER;
BEGIN
    FOR loan_rec IN UpdateLoanInterestRates LOOP
        -- Example policy: cap all interest rates at 6%, otherwise leave unchanged
        IF loan_rec.InterestRate > 6 THEN
            v_new_rate := 6;
        ELSE
            v_new_rate := loan_rec.InterestRate;
        END IF;

        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE LoanID = loan_rec.LoanID;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Loan interest rates updated according to new policy.');
END;
/
