-- Exercise 2: Error Handling

-- Scenario 1: SafeTransferFunds - transfer funds between two accounts safely
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_from_balance NUMBER;
BEGIN
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account
    FOR UPDATE;

    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ' || p_from_account);
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount, LastModified = SYSDATE
    WHERE AccountID = p_from_account;

    UPDATE Accounts
    SET Balance = Balance + p_amount, LastModified = SYSDATE
    WHERE AccountID = p_to_account;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Destination account ' || p_to_account || ' not found');
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of ' || p_amount || ' from account ' || p_from_account ||
                          ' to account ' || p_to_account || ' completed successfully.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('SafeTransferFunds', 'Source account ' || p_from_account || ' not found');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: source account not found. Transaction rolled back.');
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('SafeTransferFunds', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM || '. Transaction rolled back.');
END;
/

-- Scenario 2: UpdateSalary - increase salary by a given percentage
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percentage  IN NUMBER
) AS
    v_exists NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM Employees WHERE EmployeeID = p_employee_id;

    IF v_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Employee ID ' || p_employee_id || ' does not exist');
    END IF;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated successfully for employee ' || p_employee_id);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('UpdateSalary', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Scenario 3: AddNewCustomer - insert a new customer, handling duplicate IDs
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_dob         IN DATE,
    p_balance     IN NUMBER
) AS
    v_duplicate_customer EXCEPTION;
    PRAGMA EXCEPTION_INIT(v_duplicate_customer, -1);
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_customer_id || ' added successfully.');

EXCEPTION
    WHEN v_duplicate_customer THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('AddNewCustomer', 'Customer ID ' || p_customer_id || ' already exists');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' already exists. Insertion prevented.');
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('AddNewCustomer', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
