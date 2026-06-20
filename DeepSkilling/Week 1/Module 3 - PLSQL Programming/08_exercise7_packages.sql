-- Exercise 7: Packages

-- Scenario 1: CustomerManagement package
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer (
        p_customer_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_dob         IN DATE,
        p_balance     IN NUMBER
    );

    PROCEDURE UpdateCustomerDetails (
        p_customer_id IN NUMBER,
        p_name        IN VARCHAR2 DEFAULT NULL,
        p_balance     IN NUMBER DEFAULT NULL
    );

    FUNCTION GetCustomerBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer (
        p_customer_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_dob         IN DATE,
        p_balance     IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer ' || p_customer_id || ' added.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' already exists.');
    END AddCustomer;

    PROCEDURE UpdateCustomerDetails (
        p_customer_id IN NUMBER,
        p_name        IN VARCHAR2 DEFAULT NULL,
        p_balance     IN NUMBER DEFAULT NULL
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = COALESCE(p_name, Name),
            Balance = COALESCE(p_balance, Balance)
        WHERE CustomerID = p_customer_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Customer ' || p_customer_id || ' updated.');
        END IF;
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

-- Scenario 2: EmployeeManagement package
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee (
        p_employee_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER,
        p_department  IN VARCHAR2
    );

    PROCEDURE UpdateEmployeeDetails (
        p_employee_id IN NUMBER,
        p_position    IN VARCHAR2 DEFAULT NULL,
        p_salary      IN NUMBER DEFAULT NULL,
        p_department  IN VARCHAR2 DEFAULT NULL
    );

    FUNCTION CalculateAnnualSalary (
        p_employee_id IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee (
        p_employee_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER,
        p_department  IN VARCHAR2
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_employee_id, p_name, p_position, p_salary, p_department, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee ' || p_employee_id || ' hired.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' already exists.');
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails (
        p_employee_id IN NUMBER,
        p_position    IN VARCHAR2 DEFAULT NULL,
        p_salary      IN NUMBER DEFAULT NULL,
        p_department  IN VARCHAR2 DEFAULT NULL
    ) IS
    BEGIN
        UPDATE Employees
        SET Position = COALESCE(p_position, Position),
            Salary = COALESCE(p_salary, Salary),
            Department = COALESCE(p_department, Department)
        WHERE EmployeeID = p_employee_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Employee ' || p_employee_id || ' updated.');
        END IF;
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary (
        p_employee_id IN NUMBER
    ) RETURN NUMBER IS
        v_monthly_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_monthly_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;
        RETURN v_monthly_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

-- Scenario 3: AccountOperations package
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount (
        p_account_id   IN NUMBER,
        p_customer_id  IN NUMBER,
        p_account_type IN VARCHAR2,
        p_balance      IN NUMBER
    );

    PROCEDURE CloseAccount (
        p_account_id IN NUMBER
    );

    FUNCTION GetTotalBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount (
        p_account_id   IN NUMBER,
        p_customer_id  IN NUMBER,
        p_account_type IN VARCHAR2,
        p_balance      IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ' || p_account_id || ' opened for customer ' || p_customer_id);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Account ID ' || p_account_id || ' already exists.');
    END OpenAccount;

    PROCEDURE CloseAccount (
        p_account_id IN NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: Account ID ' || p_account_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Account ' || p_account_id || ' closed.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error closing account: ' || SQLERRM);
    END CloseAccount;

    FUNCTION GetTotalBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM Accounts
        WHERE CustomerID = p_customer_id;
        RETURN v_total;
    END GetTotalBalance;

END AccountOperations;
/
