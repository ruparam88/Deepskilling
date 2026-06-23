CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_dob IN DATE, p_balance IN NUMBER);
    PROCEDURE UpdateCustomer(p_id IN NUMBER, p_name IN VARCHAR2);
    FUNCTION GetBalance(p_id IN NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_dob IN DATE, p_balance IN NUMBER) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
    END AddCustomer;

    PROCEDURE UpdateCustomer(p_id IN NUMBER, p_name IN VARCHAR2) IS
    BEGIN
        UPDATE Customers SET Name = p_name, LastModified = SYSDATE WHERE CustomerID = p_id;
        COMMIT;
    END UpdateCustomer;

    FUNCTION GetBalance(p_id IN NUMBER) RETURN NUMBER IS
        v_bal NUMBER;
    BEGIN
        SELECT Balance INTO v_bal FROM Customers WHERE CustomerID = p_id;
        RETURN v_bal;
    END GetBalance;
END CustomerManagement;
/
