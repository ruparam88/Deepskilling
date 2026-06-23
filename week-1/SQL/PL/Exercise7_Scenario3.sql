CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_acc_id IN NUMBER, p_cust_id IN NUMBER, p_type IN VARCHAR2, p_bal IN NUMBER);
    PROCEDURE CloseAccount(p_acc_id IN NUMBER);
    FUNCTION GetTotalBalance(p_cust_id IN NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_acc_id IN NUMBER, p_cust_id IN NUMBER, p_type IN VARCHAR2, p_bal IN NUMBER) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_acc_id, p_cust_id, p_type, p_bal, SYSDATE);
        COMMIT;
    END OpenAccount;

    PROCEDURE CloseAccount(p_acc_id IN NUMBER) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_acc_id;
        COMMIT;
    END CloseAccount;

    FUNCTION GetTotalBalance(p_cust_id IN NUMBER) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_total FROM Accounts WHERE CustomerID = p_cust_id;
        RETURN NVL(v_total, 0);
    END GetTotalBalance;
END AccountOperations;
/
