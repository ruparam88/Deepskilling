CREATE OR REPLACE PROCEDURE TransferFunds (
    p_source_id IN NUMBER,
    p_target_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_source_id;
    
    IF v_balance >= p_amount THEN
        UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_source_id;
        UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_target_id;
        COMMIT;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient balance for transfer.');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('One of the accounts does not exist.');
END TransferFunds;
/
