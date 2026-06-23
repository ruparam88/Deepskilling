CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, LogDate, Action)
    VALUES (:NEW.TransactionID, SYSDATE, 'INSERT');
END;
/
