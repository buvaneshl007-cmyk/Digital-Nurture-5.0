-- Exercise 5: Triggers

-- Scenario 1: UpdateCustomerLastModified - set LastModified to SYSDATE on update
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- Scenario 2: LogTransaction - insert an AuditLog record for every new transaction
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, Amount, TransactionType, LoggedAt)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END;
/

-- Scenario 3: CheckTransactionRules - validate withdrawals/deposits before insert
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = :NEW.AccountID;

    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > v_balance THEN
        RAISE_APPLICATION_ERROR(-20010, 'Withdrawal amount exceeds account balance.');
    END IF;

    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20011, 'Deposit amount must be positive.');
    END IF;

    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20012, 'Withdrawal amount must be positive.');
    END IF;
END;
/
