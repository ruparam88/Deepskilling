CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transaction amount must be positive.');
    END IF;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :NEW.AccountID;
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20002, 'Withdrawal amount exceeds account balance.');
        END IF;
    END IF;
END;
/
