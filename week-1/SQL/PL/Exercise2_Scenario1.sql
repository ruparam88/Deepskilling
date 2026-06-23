CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_account FOR UPDATE;
    
    IF v_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;
    
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), p_from_account, SYSDATE, p_amount, 'Withdrawal');
    
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES ((SELECT NVL(MAX(TransactionID), 0) + 2 FROM Transactions), p_to_account, SYSDATE, p_amount, 'Deposit');

    COMMIT;
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in source account.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Account not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END SafeTransferFunds;
/
