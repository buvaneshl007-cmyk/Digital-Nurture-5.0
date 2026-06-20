-- Exercise 4: Functions

-- Scenario 1: CalculateAge - returns a customer's age in years given their DOB
CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

-- Scenario 2: CalculateMonthlyInstallment - returns the monthly installment for a loan
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount    IN NUMBER,
    p_annual_rate    IN NUMBER, -- e.g. 5 means 5%
    p_duration_years IN NUMBER
) RETURN NUMBER
IS
    v_monthly_rate NUMBER;
    v_num_payments NUMBER;
    v_installment  NUMBER;
BEGIN
    v_monthly_rate := (p_annual_rate / 100) / 12;
    v_num_payments := p_duration_years * 12;

    IF v_monthly_rate = 0 THEN
        v_installment := p_loan_amount / v_num_payments;
    ELSE
        v_installment := p_loan_amount * v_monthly_rate /
                          (1 - POWER(1 + v_monthly_rate, -v_num_payments));
    END IF;

    RETURN ROUND(v_installment, 2);
END;
/

-- Scenario 3: HasSufficientBalance - returns TRUE/FALSE for a given account/amount
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    RETURN v_balance >= p_amount;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/

-- Example usage (PL/SQL functions returning BOOLEAN cannot be called directly
-- from SQL, so wrap the call in a PL/SQL block to print the result):
BEGIN
    DBMS_OUTPUT.PUT_LINE('Age of customer 1: ' || CalculateAge(TO_DATE('1985-05-15', 'YYYY-MM-DD')));
    DBMS_OUTPUT.PUT_LINE('Monthly installment: ' || CalculateMonthlyInstallment(5000, 5, 5));

    IF HasSufficientBalance(1, 500) THEN
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance for 500.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1 does NOT have sufficient balance for 500.');
    END IF;
END;
/
